package com.jspider.insurance_policy_management.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.insurance_policy_management.entity.PolicyAssignment;
import com.jspider.insurance_policy_management.entity.User;
import com.jspider.insurance_policy_management.repository.UserRepository;
import com.jspider.insurance_policy_management.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;
    private final UserRepository userRepository;

    public CustomerController(CustomerService customerService, UserRepository userRepository) {
        this.customerService = customerService;
        this.userRepository = userRepository;
    }

    // ✅ Get customer's assigned policies
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/policies")
    public List<PolicyAssignment> getCustomerPolicies(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return customerService.getCustomerAssignments(user);
    }

    // ✅ Claim a policy
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/claim/{assignmentId}")
    public String claimPolicy(@PathVariable Long assignmentId, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        customerService.claimPolicy(assignmentId, user);
        return "Policy claimed successfully";
    }
}