package com.aesys.exerciceTest;



import com.aesys.exerciceTest.model.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserControllerTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetUsers() {
        System.out.println("Inizio test: testGetUsers");

        Response getResponse = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200) // Verifica che il codice di stato sia 200 OK
                .body("$", is(not(empty()))) // Verifica che il corpo della risposta non sia vuoto
                .body("size()", greaterThan(0)) // Verifica che ci siano elementi nella risposta
                .extract().response();

        System.out.println("Risposta ricevuta: " + getResponse.getBody().asString());

        // Deserializzazione della risposta in un array di oggetti User
        User[] users = getResponse.getBody().as(User[].class);

        // Creazione dell'oggetto User da controllare
        User specificUser = new User();
        specificUser.setName("Patricia Lebsack");

        // Verifica che lo specifico utente esista nella lista
        boolean userExists = false;
        for (User user : users) {
            if (user.getName().equals(specificUser.getName())) {
                userExists = true;
                break;
            }
        }

        if (userExists) {
            System.out.println("Utente " + specificUser.getName() + " trovato.");
        } else {
            System.out.println("Utente " + specificUser.getName() + " non trovato.");
        }

        // Assert che verifica se l'utente esiste
        assert userExists;

        System.out.println("Fine test: testGetUsers");
    }
}

