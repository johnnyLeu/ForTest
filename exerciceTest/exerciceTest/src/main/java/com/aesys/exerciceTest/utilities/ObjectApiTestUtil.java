package com.aesys.exerciceTest.utilities;

import com.aesys.exerciceTest.model.Data;
import com.aesys.exerciceTest.model.Oggetto;

public class ObjectApiTestUtil {

    public static Oggetto newObject(){
        Data data = new Data.Builder()
                .setColor("Red")
                .setCapacity("256 GB")
                .setPrice(999.99)
                .setGeneration("5th")
                .setYear(2021)
                .setCpuModel("A15 Bionic")
                .build();

        return new Oggetto("newName", data);
    }

    public static Oggetto updatedObject(){
        Data data = new Data.Builder()
                .setColor("blue")
                .setCapacity("256 GB")
                .setPrice(99.5)
                .setYear(2029)
                .build();

        return new Oggetto("newName", data);
    }


}