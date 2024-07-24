package com.aesys.exerciceTest.dtoObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Object {

    private int id;
    //private String id;
    private String name;
    private Data data;
}

/*
{
   "name": "Apple MacBook Pro 16",
   "data": {
      "year": 2019,
      "price": 1849.99,
      "CPU model": "Intel Core i9",
      "Hard disk size": "1 TB"
   }
}
*/


