package com.aesys.exerciceTest;

import com.aesys.exerciceTest.dtoObject.Data;
import com.aesys.exerciceTest.dtoObject.Object;
import com.aesys.exerciceTest.dtoUser.User;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;

public class RESTfulAPITest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.restful-api.dev";
    }


    @Test
    public void takeListOfAllObjects() {
        Response response = given()
                .when()
                .get("/objects")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("$", is(not(empty())))
                .body("size()", greaterThan(0))
                .extract().response();
    }


    @Test
    public void takeListOfObjectsByIds() {

        Response response = given()
                .when()
                .queryParam("id", "3")
                .queryParam("id", "5")
                .queryParam("id", "10")
                .get("/objects")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("$", is(not(empty())))
                .body("size()", equalTo(3)) //assertion: objects are tot 3
                .extract().response();

        List<Object> objects = response.jsonPath().getList("", Object.class);

        assertThat(objects, hasItems(
                hasProperty("id", is(3)),
                hasProperty("id", is(5)),
                hasProperty("id", is(10))));
    }


    @Test
    public void takeListOfObjectsByPathVarId() {

        Response response = given()
                .when()
                .get("/objects/{id}", 7)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("$", is(not(empty())))  //assertion
                .body("id", is("7")) //assertion: id is 7
                .extract().response();
    }


    @Test
    public void objectPostPutPatchDelete() {


        Data data = new Data(2019, 1849.99, "Intel Core i9", "1 TB", "", "", "", "", "");
        Object obj = new Object(0, "Apple MacBook Pro 16", data);



        System.out.println();
        System.out.println("==================POST==================");
        System.out.println();

        Response postResponse = given()
                .header("Content-Type", "application/json")
                .body(obj)
                .when()
                .post("/objects")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = new JsonPath(postResponse.body().asString());
        String id_param = jsonPath.getString("id");



        System.out.println();
        System.out.println("==================PUT(color=silver)==================");
        System.out.println();

        data.setColor("silver");
        obj.setData(data);

        Response putResponse = given()
                .header("Content-Type", "application/json")
                .body(obj)
                .when()
                .put("/objects/{id}", id_param)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response();


        System.out.println();
        System.out.println("==================PATCH(name)==================");
        System.out.println();


        obj.setName("Apple MacBook Pro 16 (Updated Name)");
        String jsonString = "{\"name\": \"" + obj.getName() + "\"}";


        Response patchResponse = given()
                .header("Content-Type", "application/json")
                .body(jsonString)
                .when()
                .patch("/objects/{id}", id_param)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response();


        System.out.println();
        System.out.println("==================DELETE==================");
        System.out.println();

        given()
                .when()
                .delete("/objects/{id}", id_param)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }


}
