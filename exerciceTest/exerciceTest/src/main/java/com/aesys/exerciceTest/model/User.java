package com.aesys.exerciceTest.model;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address AddressObject;
    private String phone;
    private String website;
    private Company CompanyObject;

    public User() {}

    public User(Company companyObject, String phone, String website, String email, Address addressObject, String username, String name, int id) {
        CompanyObject = companyObject;
        this.phone = phone;
        this.website = website;
        this.email = email;
        AddressObject = addressObject;
        this.username = username;
        this.name = name;
        this.id = id;
    }

// Getter Methods

    public int getId() {
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

    public void setId( int id ) {
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