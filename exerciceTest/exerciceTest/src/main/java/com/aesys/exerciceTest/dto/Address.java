package com.aesys.exerciceTest.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {


    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GEO geo;


}
