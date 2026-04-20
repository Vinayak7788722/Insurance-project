package com.jspider.insurance_policy_management.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private PolicyAssignment policyAssignment;

	@ManyToOne
	private User customer;

	private String status; // PENDING / APPROVED / REJECTED

	@Column(columnDefinition = "TEXT")
	private String documentPath;

	// adding new comment
	private String bankAccountNumber;
	private String bankIfscCode;
	private String bankAccountHolder;

	private LocalDate createdDate;
	private LocalDate approvedDate;

	@ManyToOne
	private User approvedByAgent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PolicyAssignment getPolicyAssignment() {
		return policyAssignment;
	}

	public void setPolicyAssignment(PolicyAssignment policyAssignment) {
		this.policyAssignment = policyAssignment;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankIfscCode() {
		return bankIfscCode;
	}

	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
	}

	public String getBankAccountHolder() {
		return bankAccountHolder;
	}

	public void setBankAccountHolder(String bankAccountHolder) {
		this.bankAccountHolder = bankAccountHolder;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(LocalDate approvedDate) {
		this.approvedDate = approvedDate;
	}

	public User getApprovedByAgent() {
		return approvedByAgent;
	}

	public void setApprovedByAgent(User approvedByAgent) {
		this.approvedByAgent = approvedByAgent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, status, createdDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Claim other = (Claim) obj;
		return Objects.equals(id, other.id) && Objects.equals(status, other.status)
				&& Objects.equals(createdDate, other.createdDate);
	}

	@Override
	public String toString() {
		return "Claim [id=" + id + ", status=" + status + ", createdDate=" + createdDate + ", approvedDate="
				+ approvedDate + "]";
	}
}