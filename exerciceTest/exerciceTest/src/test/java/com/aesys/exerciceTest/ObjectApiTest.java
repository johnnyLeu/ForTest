package com.aesys.exerciceTest;

import com.aesys.exerciceTest.model.Oggetto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.aesys.exerciceTest.utilities.ObjectApiTestUtil.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.restful-api.dev";
    }

    @Test
    public void testGetObjects() {
        Response getResponse = given()
                .when()
                .get("/Oggettos")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK) // Verifica che il codice di stato sia 200 OK
                .body("$", is(not(empty()))) // Verifica che il corpo della risposta non sia vuoto
                .body("size()", greaterThan(0)) // Verifica che ci siano elementi nella risposta
                .extract().response();
    }

    @Test
    public void testObjectsByIds() {
        Response response = given()
                .param("id", 3, 5, 10)
                .get("/objects")
                .then()
                .statusCode(200)
                .extract()
                .response();

        var Oggettos = Arrays.asList(response.as(Oggetto[].class));

        System.out.println(response.getBody().prettyPrint() + "\n");

        assertThat(Oggettos, hasItems(
                hasProperty("id", is(3)),
                hasProperty("id", is(5)),
                hasProperty("id", is(10))
        ));
    }

    @Test
    public void testObjectById() {
        String userId = "ff80818190db30490190e53aa3cd11a0";

        Response response = given()
                .pathParam("id", userId)
                .when()
                .get("/objects/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        Oggetto Oggetto = response.getBody().as(Oggetto.class);

        assertEquals(Oggetto.getId(), response.getBody().as(Oggetto.class).getId());

        System.out.println(response.getBody().prettyPrint() + "\n");
    }

    @Test
    public void testCreateObject() {
        Oggetto obj = newObject();

        Response response = given()
                .header("Content-type", "application/json")
                .body(obj)
                .when()
                .post("/objects")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        Oggetto Oggetto = response.getBody().as(Oggetto.class);

        assertEquals(Oggetto.getId(), response.getBody().as(Oggetto.class).getId());

        System.out.println(response.getBody().prettyPrint() + "\n");
    }

    @Test
    public void testUpdateObject() {
        Oggetto updatedObject = updatedObject();
        String userId = "ff80818190db30490190e52f5b43118e";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .pathParam("id", userId)
                .body(updatedObject)
                .when()
                .put("/objects/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        String name = response.jsonPath().getString("name");
        String color = response.jsonPath().getString("data.color");
        String capacity = response.jsonPath().getString("data.capacity");
        Float price = response.jsonPath().getFloat("data.price");
        Integer year = response.jsonPath().getInt("data.year");

        assertEquals("newName", name);
        assertEquals("blue", color);
        assertEquals("256 GB", capacity);
        assertEquals(99.5F, price);
        assertEquals(2029, year);

        System.out.println(response.getBody().prettyPrint() + "\n");
    }

    @Test
    public void testPatchObject() {
        Oggetto patchedObject = partiallyUpdatedObject();
        String userId = "ff80818190db30490190e52f5b43118e";

        // Esegui la richiesta PUT
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .pathParam("id", userId)
                .body(patchedObject)
                .when()
                .patch("/objects/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        Integer year = response.jsonPath().getInt("data.year");

        assertEquals(2023, year);

        System.out.println(response.getBody().prettyPrint() + "\n");
    }

    @Test
    public void testDeleteObject() {
        String userId = "ff80818190db30490190e52f5b43118e";

        Response response = given()
                .pathParam("id", userId)
                .when()
                .delete("/objects/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        Integer year = response.jsonPath().getInt("data.year");

        assertEquals(2023, year);

        System.out.println(response.getBody().prettyPrint() + "\n");
    }
}
