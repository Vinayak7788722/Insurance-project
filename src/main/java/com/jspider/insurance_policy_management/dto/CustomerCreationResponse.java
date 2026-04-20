package com.jspider.insurance_policy_management.dto;

import com.jspider.insurance_policy_management.entity.Customer;

public class CustomerCreationResponse {

    private Customer customer;
    private String generatedPassword;

    public CustomerCreationResponse() {}

    public CustomerCreationResponse(Customer customer, String generatedPassword) {
        this.customer = customer;
        this.generatedPassword = generatedPassword;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getGeneratedPassword() {
        return generatedPassword;
    }

    public void setGeneratedPassword(String generatedPassword) {
        this.generatedPassword = generatedPassword;
    }
}