package com.jspider.insurance_policy_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jspider.insurance_policy_management.entity.Policy;
import com.jspider.insurance_policy_management.repository.PolicyRepository;

@Service
public class PolicyService {

	private final PolicyRepository policyRepository;

	public PolicyService(PolicyRepository policyRepository) {
		this.policyRepository = policyRepository;
	}

	public Policy createPolicy(Policy policy) {
		return policyRepository.saveAndFlush(policy);
	}

	public List<Policy> getAllPolicies() {
		return policyRepository.findAll();
	}

	public void deletePolicy(Long id) {
		policyRepository.deleteById(id);
	}
}
