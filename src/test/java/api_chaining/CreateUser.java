package api_chaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;



public class CreateUser {

	
	@Test
	void test_create(ITestContext context) {
	
		Faker faker = new Faker();
		
		
		JSONObject data = new JSONObject();
		data.put("name",faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inctive");
		
		String bearer_token = "64463e7b04002637ed6bb44d4fa0f8f89636e523f69452bb52decfd89a8f03fc";

		int id= given()
				
	            .headers("Authorization","Bearer "+bearer_token)
		        .contentType("application/json")
		        .body(data.toString())
		        
		        .when()
		        .post("https://gorest.co.in/public/v2/users")
		        .jsonPath().getInt("id");
		
		System.out.println("Generated id of user is : " +id);
		
		context.setAttribute("user_id",id);
		
	}
	
}
