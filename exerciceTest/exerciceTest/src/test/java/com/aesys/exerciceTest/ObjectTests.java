package com.aesys.exerciceTest;

import config.ObjectConfig;
import config.ObjectEndpoint;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ObjectTests extends ObjectConfig {

    @Test
    public void getAllObjects() {
        given()
                .when()
                .get(ObjectEndpoint.ALL_OBJECTS)
                .then()
                .statusCode(200);
    }

    @Test
    public void getObjectsByIds() {
        Response response = given()
                .when()
                .param("id", 3,5,10)
                .get(ObjectEndpoint.ALL_OBJECTS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String jsonResponse = response.asString();
        System.out.println(jsonResponse);

        List<Object> objects = Arrays.asList(response.as(Object[].class));

        assertThat(objects, hasItems(
                hasProperty("id", is("3")),
                hasProperty("id", is("5")),
                hasProperty("id", is("10"))
        ));
    }

}