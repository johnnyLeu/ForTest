package com.aesys.exerciceTest;

import com.aesys.exerciceTest.model.Oggetto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.aesys.exerciceTest.utilities.ObjectApiTestUtil.newObject;
import static com.aesys.exerciceTest.utilities.ObjectApiTestUtil.updatedObject;
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
        String userId = "7";

        Response response = given()
                .pathParam("id", userId)
                .when()
                .get("/object/{id}")
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
        // Prepara i dati dell'oggetto aggiornato
        Oggetto updatedObject = updatedObject();
        String userId = "ff80818190db30490190e52f5b43118e";

        // Esegui la richiesta PUT
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .pathParam("id", userId)
                .body(updatedObject)
                .when()
                .put("/objects/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)  // Verifica che la risposta abbia codice di stato 200 OK
                .body("name", equalTo("newName"))  // Verifica che il nome sia stato aggiornato
                .body("data.color", equalTo("blue"))  // Verifica che i dati siano stati aggiornati
                .body("data.capacity", equalTo("256 GB"))  // Verifica che i dati siano stati aggiornati
                .body("data.price", equalTo(99.5F))  // Verifica che i dati siano stati aggiornati
                .body("data.year", equalTo(2029))  // Verifica che i dati siano stati aggiornati
                .extract()
                .response();

    }
}
