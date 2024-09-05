package httpRequests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {

	//@Test
	void testCookies() {
		
		given()
		
		
		.when()
		.get("https://google.com/")
		
		
		.then().cookie("AEC","djfkiejoqwjfldjvldfkowqkwneldc")
		.log().all();
	}
	
	@Test(priority=2)
	void getCookiesInfo() {
		
		Response res = given()
				.when()
				.get("https://www.google.com/");
		
		
		// Get Single Cookie Info
		
	//	String cookie_value=res.getCookie("AEC");
		//System.out.println("the value of AEC Cookie is"+cookie_value);
		
		// Get All Cookies Info
		Map<String,String> multiple_cookie = res.getCookies();
		//System.out.println(multiple_cookie.keySet()); // Return Cookies Keys
		
		for(String k : multiple_cookie.keySet()) {
			
			String cokkie_values = res.getCookie(k);
			System.out.println(k +"               "+cokkie_values);
		}
	}
}
