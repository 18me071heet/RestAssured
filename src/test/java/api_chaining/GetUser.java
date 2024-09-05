package api_chaining;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {

	
	@Test()
	void test_get(ITestContext context) {
		
		
		int id = (Integer) context.getAttribute("user_id");
		
		String bearer_token = "64463e7b04002637ed6bb44d4fa0f8f89636e523f69452bb52decfd89a8f03fc";
		
		 given()
		.headers("Authorization","Bearer "+bearer_token)
		.pathParam("id",id)
		
		.when()
		.get("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
		.statusCode(200)
		.log().all();
		
		
	}
}
