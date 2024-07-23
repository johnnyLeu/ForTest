package com.aesys.exerciceTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geo {
    private String lat;
    private String lng;

    public Geo() {}

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

// Getter Methods
    @JsonProperty("lat")
    public String getLat() {
        return lat;
    }

    @JsonProperty("lng")
    public String getLng() {
        return lng;
    }

    // Setter Methods
    @JsonProperty("lat")
    public void setLat( String lat ) {
        this.lat = lat;
    }

    @JsonProperty("lng")
    public void setLng( String lng ) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
