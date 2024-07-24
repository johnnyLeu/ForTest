package com.aesys.exerciceTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {

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
    private String generationMauscolo;
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

}
