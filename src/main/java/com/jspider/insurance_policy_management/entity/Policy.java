package com.jspider.insurance_policy_management.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String policyName;

	private Double premiumAmount;

	private Integer tenure;

	private String description;

	private String coverageType;

	private String duration;

	private String terms;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public Double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(Double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdBy, description, id, policyName, premiumAmount, tenure, coverageType, duration, terms);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Policy other = (Policy) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(policyName, other.policyName)
				&& Objects.equals(premiumAmount, other.premiumAmount) && Objects.equals(tenure, other.tenure)
				&& Objects.equals(coverageType, other.coverageType) && Objects.equals(duration, other.duration)
				&& Objects.equals(terms, other.terms);
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", policyName=" + policyName + ", premiumAmount=" + premiumAmount + ", tenure="
				+ tenure + ", description=" + description + ", coverageType=" + coverageType + ", duration=" + duration
				+ ", terms=" + terms + ", createdBy=" + createdBy + "]";
	}
	
	
	
	

}
