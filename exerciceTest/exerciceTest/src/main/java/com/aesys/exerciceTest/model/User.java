package com.aesys.exerciceTest.model;

import com.fasterxml.jackson.databind.JsonNode;

public class User {
    private float id;
    private String name;
    private String username;
    private String email;
    private Address AddressObject;
    private String phone;
    private String website;
    private Company CompanyObject;
    private JsonNode details;

    public JsonNode getDetails() {
        return details;
    }

    public void setDetails(JsonNode details) {
        this.details = details;
    }

// Getter Methods

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return AddressObject;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return CompanyObject;
    }

    // Setter Methods

    public void setId( float id ) {
        this.id = id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public void setAddress( Address addressObject ) {
        this.AddressObject = addressObject;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }

    public void setWebsite( String website ) {
        this.website = website;
    }

    public void setCompany( Company companyObject ) {
        this.CompanyObject = companyObject;
    }
}