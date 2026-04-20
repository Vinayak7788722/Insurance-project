package com.jspider.insurance_policy_management.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.insurance_policy_management.dto.CustomerCreationResponse;
import com.jspider.insurance_policy_management.entity.Customer;
import com.jspider.insurance_policy_management.entity.Policy;
import com.jspider.insurance_policy_management.entity.PolicyAssignment;
import com.jspider.insurance_policy_management.service.AgentService;

@RestController
@RequestMapping("/agent")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React frontend)
public class AgentController {

	private final AgentService agentService;

	public AgentController(AgentService agentService) {
		super();
		this.agentService = agentService;
	}

	// ✅ Create Customer
	@PostMapping("/createCustomers")
	public CustomerCreationResponse createCustomer(@RequestBody Customer customer,
	                               Authentication authentication) {
		
		System.out.println("Authenticated user: " + authentication);

	    if (authentication == null || authentication.getName().equals("anonymousUser")) {
	        throw new RuntimeException("User not logged in");
	    }

	    String email = authentication.getName();

	    return agentService.createCustomer(customer, email);
	}

	// ✅ Get Policies
	@GetMapping("/policies")
	public List<Policy> getPolicies() {
		return agentService.getAllPolicies();
	}

	// ✅ Assign Policy
	@PostMapping("/assign")
	public PolicyAssignment assignPolicy(@RequestParam Long customerId, @RequestParam Long policyId) {
		return agentService.assignPolicy(customerId, policyId);
	}

	// ✅ Get Customers for a specific agent
	@GetMapping("/getcustomers/{agentId}")
	public List<Customer> getCustomers(@PathVariable Long agentId) {
		return agentService.getCustomersByAgent(agentId);
	}

	// ✅ Get all customers
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return agentService.getAllCustomers();
	}

	// ✅ Get all policy assignments
	@GetMapping("/assignments")
	public List<PolicyAssignment> getAllAssignments() {
		return agentService.getAllAssignments();
	}
}

