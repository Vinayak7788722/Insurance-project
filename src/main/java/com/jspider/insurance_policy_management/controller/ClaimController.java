package com.jspider.insurance_policy_management.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.insurance_policy_management.entity.Claim;
import com.jspider.insurance_policy_management.entity.User;
import com.jspider.insurance_policy_management.repository.UserRepository;
import com.jspider.insurance_policy_management.service.ClaimService;

@RestController
@RequestMapping("/claim")
@CrossOrigin(origins = "http://localhost:3000")
public class ClaimController {

    private final ClaimService claimService;
    private final UserRepository userRepository;

    public ClaimController(ClaimService claimService, UserRepository userRepository) {
        this.claimService = claimService;
        this.userRepository = userRepository;
    }

    // Submit a claim (customer)
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/submit")
    public Claim submitClaim(
            @RequestParam Long assignmentId,
            @RequestParam String documentPath,
            @RequestParam String bankAccountNumber,
            @RequestParam String bankIfscCode,
            @RequestParam String bankAccountHolder,
            Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return claimService.submitClaim(assignmentId, documentPath, bankAccountNumber,
                bankIfscCode, bankAccountHolder, user);
    }

    // Get customer's claims
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/my-claims")
    public List<Claim> getCustomerClaims(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return claimService.getCustomerClaims(user);
    }

    // Get pending claims for agent
    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/pending")
    public List<Claim> getPendingClaims(Authentication authentication) {
        String email = authentication.getName();
        User agent = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Agent not found"));
        return claimService.getPendingClaimsForAgent(agent.getId());
    }

    // Approve a claim (agent)
    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/approve/{claimId}")
    public Claim approveClaim(@PathVariable Long claimId, Authentication authentication) {
        String email = authentication.getName();
        User agent = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Agent not found"));
        return claimService.approveClaim(claimId, agent);
    }

    // Reject a claim (agent)
    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/reject/{claimId}")
    public Claim rejectClaim(@PathVariable Long claimId, Authentication authentication) {
        String email = authentication.getName();
        User agent = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Agent not found"));
        return claimService.rejectClaim(claimId, agent);
    }
}