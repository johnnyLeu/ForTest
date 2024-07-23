package com.aesys.exerciceTest.model;

public class Geo {
    private String lat;
    private String lng;

    public Geo() {}

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

// Getter Methods

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    // Setter Methods

    public void setLat( String lat ) {
        this.lat = lat;
    }

    public void setLng( String lng ) {
        this.lng = lng;
    }
}
