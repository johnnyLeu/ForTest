package com.aesys.exerciceTest;

import com.aesys.exerciceTest.model.Address;
import com.aesys.exerciceTest.model.Company;
import com.aesys.exerciceTest.model.Geo;
import com.aesys.exerciceTest.model.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
				.statusCode(200) // Controlla che lo status code sia 200
				.extract()
				.response(); // Estrai la risposta

		// Stampa la risposta per debug
		System.out.println(getOneResponse.getBody().asString());
	}

	@Test
	public void testGetUsers() {
		Response getResponse = given()
				.when()
				.get("/users")
				.then()
				.statusCode(200) // Verifica che il codice di stato sia 200 OK
				.body("$", is(not(empty()))) // Verifica che il corpo della risposta non sia vuoto
				.body("size()", greaterThan(0)) // Verifica che ci siano elementi nella risposta
				.extract().response();

		System.out.println(getResponse.getBody().asString());
	}

	@Test
	public void testCreateUser() {
		// Recupera la lista degli utenti
		Response getResponse = given()
				.when()
				.get("/users")
				.then()
				.statusCode(200)
				.extract().response();

		// Ottieni il primo utente dalla lista
		String userId = getResponse.jsonPath().getString("id[0]");

		// Crea un nuovo utente basato sul primo utente
		User newUser = getNewUser();

		// Invia una richiesta POST per creare un nuovo utente
		Response postResponse = given()
				.header("Content-Type", "application/json")
				.body(newUser)
				.when()
				.post("/users")
				.then()
				.statusCode(201)
				.body("id", notNullValue())
				.extract().response();

		// Verifica che l'ID del nuovo utente sia presente nella risposta
		User newUserId = postResponse.as(User.class);
		System.out.println("Created User ID: " + newUserId.getId());
	}

	private static User getNewUser() {
		User newUser = new User();
		newUser.setName("New User");
		newUser.setUsername("newuser");
		newUser.setEmail("newuser@example.com");

		Address address = new Address();
		address.setStreet("123 New Street");
		address.setSuite("Suite 123");
		address.setCity("New City");
		address.setZipcode("12345");

		Geo geo = new Geo();
		geo.setLat("-70.0000");
		geo.setLng("-50.0000");
		address.setGeo(geo);

		newUser.setAddress(address);
		newUser.setPhone("1-234-567-8900");
		newUser.setWebsite("newuser.info");

		Company company = new Company();
		company.setName("New Company");
		company.setCatchPhrase("New Company Catchphrase");
		company.setBs("New Company Business");
		newUser.setCompany(company);
		return newUser;
	}
}

