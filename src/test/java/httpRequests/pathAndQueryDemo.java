package httpRequests;

import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class pathAndQueryDemo {
	
	@Test
	void demo() {
		
	//https://reqres.in/api/users?page=2&id=5
	
	given()
	.pathParam("myPath", "users") // Path Parameters
	.queryParam("page", "2")     // Query Parameters
	.queryParam("id", "5")
	
	
	.when()
	.get("https://reqres.in/api/{myPath}")
	
	
	.then().statusCode(200).log().all();
}


}
