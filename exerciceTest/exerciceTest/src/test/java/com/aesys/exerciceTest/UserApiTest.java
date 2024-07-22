package com.aesys.exerciceTest;

import com.aesys.exerciceTest.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		String newUser = "{\n" +
				"  \"name\": \"New User\",\n" +
				"  \"username\": \"newuser\",\n" +
				"  \"email\": \"newuser@example.com\",\n" +
				"  \"address\": {\n" +
				"    \"street\": \"123 New Street\",\n" +
				"    \"suite\": \"Suite 123\",\n" +
				"    \"city\": \"New City\",\n" +
				"    \"zipcode\": \"12345\",\n" +
				"    \"geo\": {\n" +
				"      \"lat\": \"-70.0000\",\n" +
				"      \"lng\": \"-50.0000\"\n" +
				"    }\n" +
				"  },\n" +
				"  \"phone\": \"1-234-567-8900\",\n" +
				"  \"website\": \"newuser.info\",\n" +
				"  \"company\": {\n" +
				"    \"name\": \"New Company\",\n" +
				"    \"catchPhrase\": \"New Company Catchphrase\",\n" +
				"    \"bs\": \"New Company Business\"\n" +
				"  }\n" +
				"}";

		// Invia una richiesta POST per creare un nuovo utente
		Response postResponse = given()
				.header("Content-Type", "application/json")
				.body(newUser)
				.when()
				.post("/users")
				.then()
				.statusCode(201)
				.extract().response();

		// Verifica che l'ID del nuovo utente sia presente nella risposta
		String newUserId = postResponse.jsonPath().getString("id");
		System.out.println("Created User ID: " + newUserId);
	}

	@Test
	public void testSerialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = """
                {
                    "name": "Florinda",
                    "email": "Florinda@mail.com",
                    "details": {
                        "displayAspectRatio": "97:3",
                        "audioConnector": "none"
                    }
                }
                """;

		// Deserializzazione
		User user = objectMapper.readValue(jsonString, User.class);

		// Verifica delle proprietà deserializzate
		assertEquals("Florinda", user.getName());
		assertEquals("Florinda@mail.com", user.getEmail());

		// Verifica del nodo JSON dinamico
		JsonNode details = user.getDetails();
		assertNotNull(details);
		assertEquals("97:3", details.get("displayAspectRatio").asText());
		assertEquals("none", details.get("audioConnector").asText());

		// Serializzazione
		String serializedJson = objectMapper.writeValueAsString(user);
		System.out.println("Serialized JSON: " + serializedJson);


	}
}

