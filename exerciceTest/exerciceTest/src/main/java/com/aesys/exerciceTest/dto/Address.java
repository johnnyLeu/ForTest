package com.aesys.exerciceTest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {


    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GEO geo;


}
