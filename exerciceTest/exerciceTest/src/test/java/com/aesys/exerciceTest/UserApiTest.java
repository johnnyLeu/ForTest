package com.aesys.exerciceTest;

import com.aesys.exerciceTest.model.Address;
import com.aesys.exerciceTest.model.Company;
import com.aesys.exerciceTest.model.Geo;
import com.aesys.exerciceTest.model.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.aesys.exerciceTest.utilities.UserApiTestUtil.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserApiTest {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}

	@Test
	public void testGetUser() {
		String userId = "1"; // Parametro dinamico

		// Effettua una richiesta GET con un parametro di percorso
		Response getOneResponse = RestAssured
				.given()
				.pathParam("id", userId) // Parametro di percorso
				.when()
				.get("/users/{id}") // URL parametrizzato
				.then()
				.statusCode(HttpStatus.SC_OK) // Controlla che lo status code sia 200
				.extract()
				.response(); // Estrai la risposta

		// Stampa la risposta per debug
		System.out.println(getOneResponse.getBody().asString() + "\n");
	}

	@Test
	public void testGetUsers() {
		Response getResponse = given()
				.when()
				.get("/users")
				.then()
				.statusCode(HttpStatus.SC_OK) // Verifica che il codice di stato sia 200 OK
				.body("$", is(not(empty()))) // Verifica che il corpo della risposta non sia vuoto
				.body("size()", greaterThan(0)) // Verifica che ci siano elementi nella risposta
				.extract().response();

		var users = Arrays.stream(getResponse.as(User[].class)).toList();
		// List<User> users = Arrays.asList(usersArray);

		// Verifica che la lista non sia vuota con gli assert
		assertFalse(users.isEmpty(), "La lista degli utenti non dovrebbe essere vuota");

		// Verifica che la dimensione della lista sia quella prevista (in questo caso 10), con gli assert
		assertEquals(10, users.size(), "La dimensione della lista dovrebbe essere 10");

		System.out.println(getResponse.getBody().asString() + "\n");
	}

	@Test
	public void testCreateUser() {
		// Crea un nuovo utente
		Company company = new Company("new company", "new catch phrase", "new bs");
		Geo geo = new Geo("23.3333", "-21,1908");
		Address address = new Address("new street", "new suite", "new city", "new zipcode", geo);
		User newUser = getNewUser(11, company, "new.com", "351962243", address, geo, "new@gmail.com", "newUsername","newName");

		// Invia una richiesta POST per creare un nuovo utente
		Response postResponse = given()
				.header("Content-Type", "application/json")
				.body(newUser)
				.when()
				.post("/users")
				.then()
				.statusCode(HttpStatus.SC_CREATED)
				.body("id", notNullValue())
				.extract().response();

		// Verifica che l'ID del nuovo utente sia presente nella risposta
		User newUserId = postResponse.as(User.class);
		System.out.println("Created User ID: " + newUserId.toString());
	}

	@Test
	public void testExistingUser() {
		User userToFind = userToFind();

		Response getResponse = given()
				.when()
				.get("/users")
				.then()
				.statusCode(HttpStatus.SC_OK) // Verifica che il codice di stato sia 200 OK
				.body("$", is(not(empty()))) // Verifica che il corpo della risposta non sia vuoto
				.body("size()", greaterThan(0)) // Verifica che ci siano elementi nella risposta
				.extract().response();

		var users = Arrays.stream(getResponse.as(User[].class)).toList();
		// List<User> users = Arrays.asList(usersArray);

		// Verifica che la lista non sia vuota con gli assert
		assertFalse(users.isEmpty(), "La lista degli utenti non dovrebbe essere vuota");

		// Verifica che la dimensione della lista sia quella prevista (in questo caso 10), con gli assert
		assertEquals(10, users.size(), "La dimensione della lista dovrebbe essere 10");

		boolean userFound = false;

		for (User user : users) {
			if (user.getId() == userToFind.getId()) {
				verifyUserDetails(user, userToFind.getId(), userToFind.getName(), userToFind.getUsername(), userToFind.getEmail(), userToFind.getPhone(), userToFind.getWebsite());
				verifyAddress(user.getAddressObject(), userToFind.getAddressObject());
				verifyCompany(user.getCompanyObject(), userToFind.getCompanyObject());
				userFound = true;
				break;
			}
		}
		System.out.println(userToFind.toString());

		if(userFound)
			System.out.println("\nL'utente specificato è stato trovato nella lista");

		assertTrue(userFound, "\nL'utente specificato non è stato trovato nella lista");
	}

	@Test
	public void testNotExistingUser() {
		User notExistingUser = notExistingUser();

		Response getResponse = given()
				.when()
				.get("/users")
				.then()
				.statusCode(HttpStatus.SC_OK) // Verifica che il codice di stato sia 200 OK
				.body("$", is(not(empty()))) // Verifica che il corpo della risposta non sia vuoto
				.body("size()", greaterThan(0)) // Verifica che ci siano elementi nella risposta
				.extract().response();

		var users = Arrays.stream(getResponse.as(User[].class)).toList();
		// List<User> users = Arrays.asList(usersArray);

		// Verifica che la lista non sia vuota con gli assert
		assertFalse(users.isEmpty(), "La lista degli utenti non dovrebbe essere vuota");

		// Verifica che la dimensione della lista sia quella prevista (in questo caso 10), con gli assert
		assertEquals(10, users.size(), "La dimensione della lista dovrebbe essere 10");

		boolean userFound = false;

		for (User user : users) {
			if (user.getId() == notExistingUser.getId()) {
				verifyUserDetailsNoId(user, notExistingUser.getName(), notExistingUser.getUsername(), notExistingUser.getEmail(), notExistingUser.getPhone(), notExistingUser.getWebsite());
				verifyAddress(user.getAddressObject(), notExistingUser.getAddressObject());
				verifyCompany(user.getCompanyObject(), notExistingUser.getCompanyObject());
				userFound = true;
				break;
			}
		}
		System.out.println(notExistingUser.toString());

		if(!userFound)
			System.out.println("L'utente specificato non è stato trovato nella lista");

		assertFalse(userFound, "L'utente specificato, che non dovrebbe esistere, è stato trovato nella lista");
	}

	@Test
	public void getUsersNot404() {
		Response getResponse = given()
				.when()
				.get("/users")
				.then()
				.statusCode(not(HttpStatus.SC_NOT_FOUND)) // Verifica che il codice di stato non sia 404 NOT FOUND
				.body("$", is(not(empty()))) // Verifica che il corpo della risposta non sia vuoto
				.body("size()", greaterThan(0)) // Verifica che ci siano elementi nella risposta
				.extract().response();

		System.out.println(getResponse.getBody().asString() + "\n");
	}

}
