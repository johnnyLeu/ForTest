package com.aesys.exerciceTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo GeoObject;

    public Address() {}

    public Address(String street, String suite, String city, String zipcode, Geo geoObject) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        GeoObject = geoObject;
    }

    @JsonProperty("street")
    public String getStreet() {
        return street;
    }

    @JsonProperty("street")
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonProperty("suite")
    public String getSuite() {
        return suite;
    }

    @JsonProperty("suite")
    public void setSuite(String suite) {
        this.suite = suite;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("zipcode")
    public String getZipcode() {
        return zipcode;
    }

    @JsonProperty("zipcode")
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @JsonProperty("geo")
    public Geo getGeo() {
        return GeoObject;
    }

    @JsonProperty("geo")
    public void setGeo(Geo geo) {
        this.GeoObject = geo;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", GeoObject=" + GeoObject +
                '}';
    }
}