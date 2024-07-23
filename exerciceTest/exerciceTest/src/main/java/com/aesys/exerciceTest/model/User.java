package com.aesys.exerciceTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address addressObject;
    private String phone;
    private String website;
    private Company companyObject;

    public User() {}

    public User(int id, Company companyObject, String website, String phone, Address addressObject, String email, String username, String name) {
        this.id = id;
        this.companyObject = companyObject;
        this.website = website;
        this.phone = phone;
        this.addressObject = addressObject;
        this.email = email;
        this.username = username;
        this.name = name;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("address")
    public Address getAddressObject() {
        return addressObject;
    }

    @JsonProperty("address")
    public void setAddressObject(Address address) {
        this.addressObject = address;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonProperty("company")
    public Company getCompanyObject() {
        return companyObject;
    }

    @JsonProperty("company")
    public void setCompanyObject(Company company) {
        this.companyObject = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", addressObject=" + addressObject +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", companyObject=" + companyObject +
                '}';
    }
}








































