package com.jspider.insurance_policy_management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jspider.insurance_policy_management.entity.Claim;
import com.jspider.insurance_policy_management.entity.PolicyAssignment;
import com.jspider.insurance_policy_management.entity.User;
import com.jspider.insurance_policy_management.repository.ClaimRepository;
import com.jspider.insurance_policy_management.repository.CustomerRepository;
import com.jspider.insurance_policy_management.repository.PolicyAssignmentRepository;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final CustomerRepository customerRepository;
    private final PolicyAssignmentRepository assignmentRepository;

    public ClaimService(ClaimRepository claimRepository, CustomerRepository customerRepository,
                       PolicyAssignmentRepository assignmentRepository) {
        this.claimRepository = claimRepository;
        this.customerRepository = customerRepository;
        this.assignmentRepository = assignmentRepository;
    }

    // Submit a claim with documents and bank details
    public Claim submitClaim(Long assignmentId, String documentPath, String bankAccountNumber,
                            String bankIfscCode, String bankAccountHolder, User customer) {
        PolicyAssignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Policy assignment not found"));

        Claim claim = new Claim();
        claim.setPolicyAssignment(assignment);
        claim.setCustomer(customer);
        claim.setStatus("PENDING");
        claim.setDocumentPath(documentPath);
        claim.setBankAccountNumber(bankAccountNumber);
        claim.setBankIfscCode(bankIfscCode);
        claim.setBankAccountHolder(bankAccountHolder);
        claim.setCreatedDate(LocalDate.now());

        return claimRepository.save(claim);
    }

    // Get customer's claims
    public List<Claim> getCustomerClaims(User user) {
        return claimRepository.findByCustomerId(user.getId());
    }

    // Get pending claims for agent (agent views claims from their customers)
    public List<Claim> getPendingClaimsForAgent(Long agentId) {
        return claimRepository.findByPolicyAssignmentCustomerAgentId(agentId);
    }

    // Approve a claim
    public Claim approveClaim(Long claimId, User agent) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        if (!claim.getStatus().equals("PENDING")) {
            throw new RuntimeException("Only pending claims can be approved");
        }

        claim.setStatus("APPROVED");
        claim.setApprovedDate(LocalDate.now());
        claim.setApprovedByAgent(agent);

        return claimRepository.save(claim);
    }

    // Reject a claim
    public Claim rejectClaim(Long claimId, User agent) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        if (!claim.getStatus().equals("PENDING")) {
            throw new RuntimeException("Only pending claims can be rejected");
        }

        claim.setStatus("REJECTED");
        claim.setApprovedByAgent(agent);

        return claimRepository.save(claim);
    }
}