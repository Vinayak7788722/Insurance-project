package com.jspider.insurance_policy_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jspider.insurance_policy_management.dto.CustomerCreationResponse;
import com.jspider.insurance_policy_management.entity.Customer;
import com.jspider.insurance_policy_management.entity.Policy;
import com.jspider.insurance_policy_management.entity.PolicyAssignment;
import com.jspider.insurance_policy_management.entity.Role;
import com.jspider.insurance_policy_management.entity.User;
import com.jspider.insurance_policy_management.repository.CustomerRepository;
import com.jspider.insurance_policy_management.repository.PolicyAssignmentRepository;
import com.jspider.insurance_policy_management.repository.PolicyRepository;
import com.jspider.insurance_policy_management.repository.UserRepository;

@Service
public class AgentService {

	private final CustomerRepository customerRepo;

	private final PolicyRepository policyRepo;

	private final PolicyAssignmentRepository assignmentRepo;

	private final UserRepository userRepo;

	private final PasswordEncoder passwordEncoder;

	public AgentService(CustomerRepository customerRepo, PolicyRepository policyRepo,
			PolicyAssignmentRepository assignmentRepo, UserRepository userRepo, PasswordEncoder passwordEncoder) {
		super();
		this.customerRepo = customerRepo;
		this.policyRepo = policyRepo;
		this.assignmentRepo = assignmentRepo;
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Agent creates a new customer and assigns themselves as the agent for that
	 * customer. Also creates a user account for the customer with a generated password.
	 * 
	 * @param customer
	 * @param agentEmail
	 * @return CustomerCreationResponse with customer and generated password
	 */
	 public CustomerCreationResponse createCustomer(Customer customer, String agentEmail) {

	        User agent = userRepo.findByEmail(agentEmail)
	                .orElseThrow(() -> new RuntimeException("Agent not found"));

	        // Generate a random password
	        String generatedPassword = UUID.randomUUID().toString().substring(0, 8);

	        // Create user account for customer
	        User customerUser = new User();
	        customerUser.setName(customer.getName());
	        customerUser.setEmail(customer.getEmail());
	        customerUser.setPassword(passwordEncoder.encode(generatedPassword));
	        customerUser.setRole(Role.ROLE_CUSTOMER);
	        userRepo.save(customerUser);

	        customer.setAgent(agent);
	        customer.setUser(customerUser);

	        customerRepo.save(customer);

	        return new CustomerCreationResponse(customer, generatedPassword);
	  }

	/**
	 * Agent can view all policies available in the system.
	 * 
	 * @return List of all policies
	 */
	public List<Policy> getAllPolicies() {
		return policyRepo.findAll();
	}

	/**
     * Agent can assign a policy to a customer. This creates a new PolicyAssignment
     * record with status ACTIVE and sets the start and end dates based on the
     * policy tenure.
     * 
     * @param customerId
     * @param policyId
     * @return
     */
	public PolicyAssignment assignPolicy(Long customerId, Long policyId) {

		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

		Policy policy = policyRepo.findById(policyId)
				.orElseThrow(() -> new RuntimeException("Policy not found with ID: " + policyId));

		PolicyAssignment pa = new PolicyAssignment();
		pa.setCustomer(customer);
		pa.setPolicy(policy);
		pa.setStatus("ACTIVE");
		pa.setStartDate(LocalDate.now());
		pa.setEndDate(LocalDate.now().plusMonths(policy.getTenure()));

		return assignmentRepo.save(pa);
	}
	
	public List<Customer> getCustomersByAgent(Long agentId) {
	    return customerRepo.findByAgentId(agentId);
	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public List<PolicyAssignment> getAllAssignments() {
		return assignmentRepo.findAll();
	}
}
