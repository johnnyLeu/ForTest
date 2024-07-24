package com.aesys.exerciceTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Object {

    private String id;
    private String name;
    @JsonProperty("data")
    private Data data;
}
