package com.aesys.exerciceTest;


import com.aesys.exerciceTest.dtoUser.Address;

import com.aesys.exerciceTest.dtoUser.Company;
import com.aesys.exerciceTest.dtoUser.GEO;
import com.aesys.exerciceTest.dtoUser.User;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


public class UserTests1 {

    private String companyName = "companyName";
    private String catchPhrase = "catchPhrase";
    private String bs = "bs";
    Company company = new Company(companyName, catchPhrase, bs);

    private String geoLat = "-0.0000";
    private String geoLng = "0.0000";
    GEO geo = new GEO(geoLat, geoLng);

    private String street = "street";
    private String suite = "suite";
    private String city = "city";
    private String zipcode = "zipcode";

    Address address = new Address(street, suite, city, zipcode, geo);

    private String name = "name";
    private String username = "username";
    private String email = "email@gmail.com";
    private String phone = "phone";
    private String website = "website.org";

    User user = new User("0", name, username, email, address, phone, website, company);



    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }



   /* @Test
    public void takeAll() {
        given().when().get("/users")
                .then().log().all();
    }*/



    /* TEST 1
    Recuperare le informazioni dall'URL: https://jsonplaceholder.typicode.com/users
    Controllare che la risposta vada in success
    Controllare che il body della response non sia vuoto  */
    @Test
    public void testResponseAndStatusCode() {
        Response response = given()
                .when()
                .get("/users")
                .then()
                .assertThat()
                .statusCode(200) //
                .body("$", is(not(empty()))) // not empty
                .body("size()", greaterThan(0)) // there are elements
                .extract().response();
    }


    /* TEST 2
    Recuperare le informazioni dall'URL: https://jsonplaceholder.typicode.com/users
    Prendere un item dalla response e modificarlo, creandone uno nuovo
    Controllare che la risposta sia "Created" con relativo codice
    Controllare che nella response venga mostrato l'ID dell'elemento appena creato  */

    @Test
    public void createUser() {

        Response postResponse = given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();
        System.out.println("Created User: " + postResponse.getBody().asString());
    }





}
