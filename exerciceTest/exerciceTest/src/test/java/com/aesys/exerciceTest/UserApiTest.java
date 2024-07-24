package com.aesys.exerciceTest;

import com.aesys.exerciceTest.model.Address;
import com.aesys.exerciceTest.model.Company;
import com.aesys.exerciceTest.model.Geo;
import com.aesys.exerciceTest.model.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

//	@Test
//	public void testGetUsers() {
//		User userToFound = getUserToFound();
//
//		Response getResponse = given()
//				.when()
//				.get("/users")
//				.then()
//				.statusCode(HttpStatus.OK.value()) // Verifica che il codice di stato sia 200 OK
//				.body("$", is(not(empty()))) // Verifica che il corpo della risposta non sia vuoto
//				.body("size()", greaterThan(0)) // Verifica che ci siano elementi nella risposta
//				.extract().response();
//
//		var users = Arrays.stream(getResponse.as(User[].class)).toList();
//		// List<User> users = Arrays.asList(usersArray);
//
//		// Verifica che la lista non sia vuota con gli assert
//		assertFalse(users.isEmpty(), "La lista degli utenti non dovrebbe essere vuota");
//
//		// Verifica che la dimensione della lista sia quella prevista (in questo caso 10), con gli assert
//		assertEquals(10, users.size(), "La dimensione della lista dovrebbe essere 10");
//
//
//		System.out.println(getResponse.getBody().asString());
//	}

	@Test
	public void testCreateUser() {
		// Crea un nuovo utente
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

	private void verifyUserDetails(User user, int expectedId, String expectedName, String expectedUsername, String expectedEmail, String expectedPhone, String expectedWebsite) {
		assertEquals(expectedId, user.getId(), "L'ID dell'utente non corrisponde");
		assertEquals(expectedName, user.getName(), "Il nome dell'utente non corrisponde");
		assertEquals(expectedUsername, user.getUsername(), "Il nome utente dell'utente non corrisponde");
		assertEquals(expectedEmail, user.getEmail(), "L'email dell'utente non corrisponde");
		assertEquals(expectedPhone, user.getPhone(), "Il numero di telefono dell' utente non corrisponde");
		assertEquals(expectedWebsite, user.getWebsite(), "Il sito web dell' utente non corrisponde");
	}

	private void verifyAddress(Address actualAddress, Address expectedAddress) {
		assertEquals(expectedAddress.getStreet(), actualAddress.getStreet(), "La via dell'indirizzo non corrisponde");
		assertEquals(expectedAddress.getSuite(), actualAddress.getSuite(), "La suite dell'indirizzo non corrisponde");
		assertEquals(expectedAddress.getCity(), actualAddress.getCity(), "La città dell'indirizzo non corrisponde");
		assertEquals(expectedAddress.getZipcode(), actualAddress.getZipcode(), "Il CAP dell'indirizzo non corrisponde");
		assertEquals(expectedAddress.getGeo().getLat(), actualAddress.getGeo().getLat(), "La latitudine dell'indirizzo non corrisponde");
		assertEquals(expectedAddress.getGeo().getLng(), actualAddress.getGeo().getLng(), "La longitudine dell'indirizzo non corrisponde");
	}

	private void verifyCompany(Company actualCompany, Company expectedCompany) {
		assertEquals(expectedCompany.getName(), actualCompany.getName(), "Il nome dell'azienda non corrisponde");
		assertEquals(expectedCompany.getCatchPhrase(), actualCompany.getCatchPhrase(), "Il motto dell'azienda non corrisponde");
		assertEquals(expectedCompany.getBs(), actualCompany.getBs(), "Il business dell'azienda non corrisponde");
	}
}

