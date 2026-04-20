package com.jspider.insurance_policy_management.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.insurance_policy_management.dto.PolicyRequest;
import com.jspider.insurance_policy_management.entity.Policy;
import com.jspider.insurance_policy_management.entity.User;
import com.jspider.insurance_policy_management.repository.UserRepository;
import com.jspider.insurance_policy_management.service.PolicyService;

@RestController
@RequestMapping("/policy")
public class PolicyController {

	private final PolicyService policyService;

	private final UserRepository userRepository;

	public PolicyController(PolicyService policyService, UserRepository userRepository) {
		super();
		this.policyService = policyService;
		this.userRepository = userRepository;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public Policy createPolicyController(@RequestBody PolicyRequest request, Authentication authentication) {

		System.out.println("Received PolicyRequest: " + request);
		System.out.println("PolicyName: " + request.getPolicyName());
		System.out.println("PremiumAmount: " + request.getPremiumAmount());
		
		// Get logged-in user
		String email = authentication.getName();
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("admin not logged in..."));

		// Convert DTO -> Entity
		Policy policy = new Policy();
		policy.setPolicyName(request.getPolicyName());
		policy.setPremiumAmount(request.getPremiumAmount());
		policy.setDescription(request.getDescription());
		policy.setCoverageType(request.getCoverageType());
		policy.setDuration(request.getDuration());
		policy.setTerms(request.getTerms());

		// Try to parse duration to tenure (integer)
		try {
			if (request.getDuration() != null && !request.getDuration().trim().isEmpty()) {
				policy.setTenure(Integer.parseInt(request.getDuration().trim()));
			}
		} catch (NumberFormatException e) {
			// If duration is not a valid number, set tenure to null
			policy.setTenure(null);
		}

		// Set createdBy automatically
		policy.setCreatedBy(user);

		return policyService.createPolicy(policy);
	}

	// ✅ Admin endpoints for policy management
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/policies")
	public List<Policy> getAllPoliciesForAdmin() {
		return policyService.getAllPolicies();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/admin/policies/{id}")
	public Policy updatePolicy(@PathVariable Long id, @RequestBody PolicyRequest request, Authentication authentication) {
		// Get logged-in admin user
		String email = authentication.getName();
		User admin = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("admin not logged in..."));

		// Find existing policy
		Policy existingPolicy = policyService.getAllPolicies().stream()
				.filter(p -> p.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Policy not found"));

		// Update policy fields
		existingPolicy.setPolicyName(request.getPolicyName());
		existingPolicy.setPremiumAmount(request.getPremiumAmount());
		existingPolicy.setDescription(request.getDescription());
		existingPolicy.setCoverageType(request.getCoverageType());
		existingPolicy.setDuration(request.getDuration());
		existingPolicy.setTerms(request.getTerms());

		// Try to parse duration to tenure (integer)
		try {
			if (request.getDuration() != null && !request.getDuration().trim().isEmpty()) {
				existingPolicy.setTenure(Integer.parseInt(request.getDuration().trim()));
			}
		} catch (NumberFormatException e) {
			// If duration is not a valid number, set tenure to null
			existingPolicy.setTenure(null);
		}

		return policyService.createPolicy(existingPolicy); // This will update since it has an ID
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/admin/policies/{id}")
	public String deletePolicy(@PathVariable Long id) {
		try {
			policyService.deletePolicy(id);
			return "Policy deleted successfully";
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete policy: " + e.getMessage());
		}
	}
}
