package com.aesys.exerciceTest.model;

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

    // Getter Methods

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Geo getGeo() {
        return GeoObject;
    }

    // Setter Methods

    public void setStreet( String street ) {
        this.street = street;
    }

    public void setSuite( String suite ) {
        this.suite = suite;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public void setZipcode( String zipcode ) {
        this.zipcode = zipcode;
    }

    public void setGeo( Geo geoObject ) {
        this.GeoObject = geoObject;
    }
}