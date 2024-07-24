package com.aesys.exerciceTest.dtoObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@lombok.Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    @JsonProperty("year")
    int year;
    @JsonProperty("price")
    double price;
    @JsonProperty("CPU model")
    String cpuModel;
    @JsonProperty("Hard disk size")
    String hdSize;
    @JsonProperty("color")
    String color;
    @JsonProperty("capacity")
    private String capacity;
    @JsonProperty("Capacity")
    private String capacityM;
    @JsonProperty("capacity GB")
    private String capacityGB;
    @JsonProperty("Screen size")
    private String screenSize;

}

