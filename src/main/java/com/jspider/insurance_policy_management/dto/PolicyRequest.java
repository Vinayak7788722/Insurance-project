package com.jspider.insurance_policy_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PolicyRequest {

	@JsonProperty("policyName")
	private String policyName;

	@JsonProperty("premiumAmount")
    private Double premiumAmount;

	@JsonProperty("tenure")
    private Integer tenure;

	@JsonProperty("description")
    private String description;

	@JsonProperty("coverageType")
    private String coverageType;

	@JsonProperty("duration")
    private String duration;

	@JsonProperty("terms")
    private String terms;

    // Default constructor
    public PolicyRequest() {
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

	@Override
	public String toString() {
		return "PolicyRequest [policyName=" + policyName + ", premiumAmount=" + premiumAmount + ", tenure=" + tenure
				+ ", description=" + description + ", coverageType=" + coverageType + ", duration=" + duration
				+ ", terms=" + terms + "]";
	}
    
    
}
