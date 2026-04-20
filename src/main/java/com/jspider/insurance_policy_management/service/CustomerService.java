package com.jspider.insurance_policy_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jspider.insurance_policy_management.entity.Customer;
import com.jspider.insurance_policy_management.entity.PolicyAssignment;
import com.jspider.insurance_policy_management.entity.User;
import com.jspider.insurance_policy_management.repository.CustomerRepository;
import com.jspider.insurance_policy_management.repository.PolicyAssignmentRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PolicyAssignmentRepository assignmentRepository;

    public CustomerService(CustomerRepository customerRepository, PolicyAssignmentRepository assignmentRepository) {
        this.customerRepository = customerRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public List<PolicyAssignment> getCustomerAssignments(User user) {
        Customer customer = customerRepository.findByUser(user);
        if (customer == null) {
            throw new RuntimeException("Customer not found for user");
        }
        return assignmentRepository.findByCustomerId(customer.getId());
    }

    public void claimPolicy(Long assignmentId, User user) {
        PolicyAssignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        // Check if the assignment belongs to the customer
        Customer customer = customerRepository.findByUser(user);
        if (!assignment.getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("Unauthorized to claim this policy");
        }

        // Update status to CLAIMED
        assignment.setStatus("CLAIMED");
        assignmentRepository.save(assignment);
    }
}