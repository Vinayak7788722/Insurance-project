package com.jspider.insurance_policy_management.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PolicyAssignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Customer customer;

	@ManyToOne
	private Policy policy;

	private String status; // ACTIVE / EXPIRED

	private LocalDate startDate;

	private LocalDate endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, endDate, id, policy, startDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PolicyAssignment other = (PolicyAssignment) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(id, other.id) && Objects.equals(policy, other.policy)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "PolicyAssignment [id=" + id + ", customer=" + customer + ", policy=" + policy + ", status=" + status
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	

}
