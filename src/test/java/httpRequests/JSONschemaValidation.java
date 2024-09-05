package httpRequests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class JSONschemaValidation {
	@Test
	void jsonSchema() {
		
		 given()
		.when()
		.get("http://localhost:3000/book")
		.then()
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		
	}
}
