package api_chaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	@Test()
	void test_update(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Female");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "Active");
		
	    int id =  (Integer) context.getAttribute("user_id");
		
		String bearer_token = "64463e7b04002637ed6bb44d4fa0f8f89636e523f69452bb52decfd89a8f03fc";
		
		given()
		
		.headers("Authorization","Bearer "+bearer_token)
		.contentType("application/json")
		.body(data.toString())
		.pathParam("id",id)
		
		.when()
		.put("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
		.statusCode(201)
		.log().all();
		
		
		
	}

}

	
	


