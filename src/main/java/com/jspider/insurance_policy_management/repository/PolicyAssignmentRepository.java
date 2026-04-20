package com.jspider.insurance_policy_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspider.insurance_policy_management.entity.PolicyAssignment;

public interface PolicyAssignmentRepository extends JpaRepository<PolicyAssignment, Long> {
    List<PolicyAssignment> findByCustomerId(Long customerId);
}