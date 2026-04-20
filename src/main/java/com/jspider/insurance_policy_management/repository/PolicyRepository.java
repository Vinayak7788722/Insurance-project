package com.jspider.insurance_policy_management.repository;

import com.jspider.insurance_policy_management.entity.Policy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

}
