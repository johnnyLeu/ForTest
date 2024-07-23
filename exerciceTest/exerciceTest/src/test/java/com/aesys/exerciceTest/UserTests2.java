package com.aesys.exerciceTest;

import com.aesys.exerciceTest.dto.User;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class UserTests2 {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    /*
    1. TEST CONTROLLO UTENTE ESISTENTE:
    richiamare la get users
    controllare status code 200
    controllare che la size non sia 0

    */

    @Test
    public void commonTest() {
        Response getResponse = given()
                .when()
                .get("/users")
                .then()
                .assertThat()
                .statusCode(200) //
                .body("$", is(not(empty()))) // not empty
                .body("size()", greaterThan(0)) // there are elements
                .extract().response();

        System.out.println(getResponse.getBody().asString());
    }


    /*
    controllare che esista uno user specifico (Patricia Lebsack)
    NB: lo user da controllare deve essere creato come oggetto User
    */

    @Test
    public void checkOnParticularUser() {

        Response response = given()
                .when()
                .get("/users")
                .then()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<User> allUsers = jsonPath.getList("", User.class);

        List<User> certainUser = allUsers.stream()
                .filter(u -> u.getName().equals("Patricia Lebsack"))
                .toList();

        assertNotNull(certainUser);


    }



    /*
    2. TEST CONTROLLO UTENTE NON ESISTENTE:
    richiamare la get users
    controllare status code 200
    controllare che la size non sia 0
    controllare che non esista uno user specifico (Dwayne Johnson)
    NB: lo user da controllare deve essere creato come oggetto User
    */


    @Test
    public void checkOnAbsenceOfParticularUser() {
        Response response = given()
                .when()
                .get("/users")
                .then()
                .assertThat()
                .statusCode(200) //
                .body("$", is(not(empty()))) // not empty
                .body("size()", greaterThan(0)) // there are elements
                .extract().response();


        JsonPath jsonPath = response.jsonPath();

        List<User> allUsers = jsonPath.getList("", User.class);

        List<User> certainUser = allUsers.stream()
                .filter(u -> u.getName().equals("Dwayne Johnson"))
                .toList();

        assertTrue(certainUser.isEmpty());
    }

    @Test
    public void check404StatusCode() {

        Response response = given()
                .when()
                .get("/users")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        // Assert that correct status code is returned.
        Assert.assertNotEquals(statusCode, 404);
    }



}



