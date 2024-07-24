package com.aesys.exerciceTest;

import com.aesys.exerciceTest.model.*;
import com.aesys.exerciceTest.model.Object;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

public class ObjectApiTest {



    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.restful-api.dev";
    }

    @Test
    public void testGetObjects() {
        Response getResponse = given()
                .when()
                .get("/objects")
                .then()
                .statusCode(200) // Verifica che il codice di stato sia 200 OK
                .body("$", is(not(empty()))) // Verifica che il corpo della risposta non sia vuoto
                .body("size()", greaterThan(0)) // Verifica che ci siano elementi nella risposta
                .extract().response();

        System.out.println(getResponse.getBody().asString());


//        try {
//            assertThat(productNames, hasItem("Mrs. Dennis Schulist"));
//            System.out.println("Il nome 'Mrs. Dennis Schulist' è presente nella lista dei prodotti.");
//        } catch (AssertionError e) {
//            System.err.println("Il nome 'Mrs. Dennis Schulist' NON è presente nella lista dei prodotti.");
//            throw e;
//        }

        System.out.println("Fine test: testCheckUser");
    }


    @Test
    public void testGetObjectById() {
        String objectId =  "1"; // Parametro dinamico

        // Effettua una richiesta GET con un parametro di percorso
        Response getOneResponse = RestAssured
                .given()
                .pathParam("id", objectId) // Parametro di percorso
                .when()
                .get("objects/{id}") // URL parametrizzato
                .then()
                .statusCode(200) // Controlla che lo status code sia 200
                .extract()
                .response(); // Estrai la risposta

        // Stampa la risposta per debug
        System.out.println(getOneResponse.getBody().asString());

}

    @Test
    public void testGetObjectsById() {
        String[] objectIds =  {"1", "2", "3"}; // Parametro dinamico

        for (String objectId : objectIds) {
            System.out.println("Inizio test per oggetto con ID: " + objectId);
        // Effettua una richiesta GET con un parametro di percorso
        Response getOneResponse = RestAssured
                .given()
                .pathParam("id", objectId) // Parametro di percorso
                .when()
                .get("objects/{id}") // URL parametrizzato
                .then()
                .statusCode(200) // Controlla che lo status code sia 200
                .extract()
                .response(); // Estrai la risposta

        // Stampa la risposta per debug
        System.out.println(getOneResponse.getBody().asString());
        }
    }

    @Test
    public void createUser() {

        Data data = new Data(1998, 15000, "A7", "500");
        Object object = new Object("example", data);

        Response postResponse = given()
                .header("Content-Type", "application/json")
                .body(object)
                .when()
                .post("/objects")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        System.out.println("Created User: " + postResponse.getBody().asString());
    }

    @Test
    public void testUpdateObject() {
        String objectId = "ff80818190db30490190e4f6e26610f8"; // ID dell'oggetto da aggiornare

        // Crea un nuovo oggetto con le informazioni aggiornate
        Data data = new Data(2000, 15000, "A7", "500");
        Object updatedObject = new Object(objectId, "example", data);


        // Effettua una richiesta PUT per aggiornare l'oggetto
        Response putResponse = given()
                .contentType(ContentType.JSON)
                .body(updatedObject) // Corpo della richiesta
                .when()
                .put("https://api.restful-api.dev/objects/ff80818190db30490190e4f6e26610f8") // URL parametrizzato
                .then()
                .statusCode(200) // Controlla che lo status code sia 200
                .extract()
                .response(); // Estrai la risposta

        // Stampa la risposta per debug
        System.out.println("Risposta PUT: " + putResponse.getBody().asString());

        System.out.println("L'oggetto con ID " + objectId + " è stato aggiornato correttamente.");
    }
}

