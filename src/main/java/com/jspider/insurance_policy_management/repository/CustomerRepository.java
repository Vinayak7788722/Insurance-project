package com.jspider.insurance_policy_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspider.insurance_policy_management.entity.Customer;
import com.jspider.insurance_policy_management.entity.User;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByAgentId(Long agentId);
    Customer findByUser(User user);
}