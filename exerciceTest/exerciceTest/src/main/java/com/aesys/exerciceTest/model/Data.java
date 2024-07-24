package com.aesys.exerciceTest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonIgnoreProperties
public class Data {
    @JsonProperty("color")
    private final String color;
    @JsonProperty("Color")
    private final String colorMaiuscolo;
    @JsonProperty("capacity")
    private final String capacity;
    @JsonProperty("Capacity")
    private final String capacityMaiuscolo;
    @JsonProperty("capacity GB")
    private final Integer capacityGb;
    @JsonProperty("price")
    private final Double price;
    @JsonProperty("Price")
    private final String priceMaiuscolo;
    @JsonProperty("generation")
    private final String generation;
    @JsonProperty("Generation")
    private final String generationMaiuscolo;
    @JsonProperty("year")
    private final Integer year;
    @JsonProperty("CPU model")
    private final String cpuModel;
    @JsonProperty("Hard disk size")
    private final String hardDiskSize;
    @JsonProperty("Strap Colour")
    private final String strapColour;
    @JsonProperty("Case Size")
    private final String caseSize;
    @JsonProperty("Description")
    private final String description;
    @JsonProperty("Screen size")
    private final String screenSize;

    @JsonCreator
    private Data(Builder builder) {
        this.color = builder.color;
        this.colorMaiuscolo = builder.colorMaiuscolo;
        this.capacity = builder.capacity;
        this.capacityMaiuscolo = builder.capacityMaiuscolo;
        this.capacityGb = builder.capacityGb;
        this.price = builder.price;
        this.priceMaiuscolo = builder.priceMaiuscolo;
        this.generation = builder.generation;
        this.generationMaiuscolo = builder.generationMaiuscolo;
        this.year = builder.year;
        this.cpuModel = builder.cpuModel;
        this.hardDiskSize = builder.hardDiskSize;
        this.strapColour = builder.strapColour;
        this.caseSize = builder.caseSize;
        this.description = builder.description;
        this.screenSize = builder.screenSize;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        @JsonProperty("color")
        private String color;
        @JsonProperty("Color")
        private String colorMaiuscolo;
        @JsonProperty("capacity")
        private String capacity;
        @JsonProperty("Capacity")
        private String capacityMaiuscolo;
        @JsonProperty("capacity GB")
        private Integer capacityGb;
        @JsonProperty("price")
        private Double price;
        @JsonProperty("Price")
        private String priceMaiuscolo;
        @JsonProperty("generation")
        private String generation;
        @JsonProperty("Generation")
        private String generationMaiuscolo;
        @JsonProperty("year")
        private Integer year;
        @JsonProperty("CPU model")
        private String cpuModel;
        @JsonProperty("Hard disk size")
        private String hardDiskSize;
        @JsonProperty("Strap Colour")
        private String strapColour;
        @JsonProperty("Case Size")
        private String caseSize;
        @JsonProperty("Description")
        private String description;
        @JsonProperty("Screen size")
        private String screenSize;

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setColorMaiuscolo(String colorMaiuscolo) {
            this.colorMaiuscolo = colorMaiuscolo;
            return this;
        }

        public Builder setCapacity(String capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder setCapacityMaiuscolo(String capacityMaiuscolo) {
            this.capacityMaiuscolo = capacityMaiuscolo;
            return this;
        }

        public Builder setCapacityGb(Integer capacityGb) {
            this.capacityGb = capacityGb;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setPriceMaiuscolo(String priceMaiuscolo) {
            this.priceMaiuscolo = priceMaiuscolo;
            return this;
        }

        public Builder setGeneration(String generation) {
            this.generation = generation;
            return this;
        }

        public Builder setgenerationMaiuscolo(String generationMaiuscolo) {
            this.generationMaiuscolo = generationMaiuscolo;
            return this;
        }

        public Builder setYear(Integer year) {
            this.year = year;
            return this;
        }

        public Builder setCpuModel(String cpuModel) {
            this.cpuModel = cpuModel;
            return this;
        }

        public Builder setHardDiskSize(String hardDiskSize) {
            this.hardDiskSize = hardDiskSize;
            return this;
        }

        public Builder setStrapColour(String strapColour) {
            this.strapColour = strapColour;
            return this;
        }

        public Builder setCaseSize(String caseSize) {
            this.caseSize = caseSize;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setScreenSize(String screenSize) {
            this.screenSize = screenSize;
            return this;
        }

        public Data build() {
            return new Data(this);
        }
    }

    @Override
    public String toString() {
        return "Data{" +
                "color='" + color + '\'' +
                ", colorMaiuscolo='" + colorMaiuscolo + '\'' +
                ", capacity='" + capacity + '\'' +
                ", capacityMaiuscolo='" + capacityMaiuscolo + '\'' +
                ", capacityGb=" + capacityGb +
                ", price=" + price +
                ", priceMaiuscolo='" + priceMaiuscolo + '\'' +
                ", generation='" + generation + '\'' +
                ", generationMaiuscolo='" + generationMaiuscolo + '\'' +
                ", year=" + year +
                ", cpuModel='" + cpuModel + '\'' +
                ", hardDiskSize='" + hardDiskSize + '\'' +
                ", strapColour='" + strapColour + '\'' +
                ", caseSize='" + caseSize + '\'' +
                ", description='" + description + '\'' +
                ", screenSize='" + screenSize + '\'' +
                '}';
    }
}
