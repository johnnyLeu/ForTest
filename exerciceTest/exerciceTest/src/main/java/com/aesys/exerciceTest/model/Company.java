package com.aesys.exerciceTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company() {}

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

// Getter Methods

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("catchPhrase")
    public String getCatchPhrase() {
        return catchPhrase;
    }

    @JsonProperty("bs")
    public String getBs() {
        return bs;
    }

    // Setter Methods
    @JsonProperty("name")
    public void setName( String name ) {
        this.name = name;
    }

    @JsonProperty("catchPhrase")
    public void setCatchPhrase( String catchPhrase ) {
        this.catchPhrase = catchPhrase;
    }

    @JsonProperty("bs")
    public void setBs( String bs ) {
        this.bs = bs;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", catchPhrase='" + catchPhrase + '\'' +
                ", bs='" + bs + '\'' +
                '}';
    }
}