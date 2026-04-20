package com.jspider.insurance_policy_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jspider.insurance_policy_management.entity.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByCustomerId(Long customerId);
    List<Claim> findByStatus(String status);
    List<Claim> findByPolicyAssignmentCustomerAgentId(Long agentId);
}