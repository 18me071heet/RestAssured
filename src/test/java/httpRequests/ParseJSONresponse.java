package httpRequests;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParseJSONresponse {
	
	
	// Approach 1

	//@Test(priority=1)
	void testApi() {
		
		given()
		.contentType(ContentType.JSON)
		.when()
		.get("http://localhost:3000/book")
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json")
		.body("book[1].title", equalTo("Rich Dad Poor Dad"));
		

	}
	
	
	// Approach 2 
	
	@Test(priority=2)
	void getResponse() {
		
		Response res = given()
				.contentType(ContentType.JSON)
				.when()
				.get("http://localhost:3000/book");
		
		Assert.assertEquals(res.getStatusCode(), 200);	
		
		String book_name =res.jsonPath().get("book[1].title").toString();
		Assert.assertEquals(book_name, "Rich Dad Poor Dad");
		
		
	}
	
	@Test(priority=3)
	void TestJsonResponseBody() {
		
		Response res = given()
				.contentType(ContentType.JSON)
				.when()
				.get("http://localhost:3000/book");
		
		// Get Title of all books
		
		JSONArray arr = new JSONArray(res.asString());
		
	/*	for(int i=0;i<arr.length();i++) {
			
		String book_title = arr.getJSONObject(i).get("title").toString();
		System.out.println(book_title);
			
		*/
		
		// Get total price of books
		
		double total_price = 0;
		
		for(int i=0;i<arr.length();i++) {
			
		String book_price=arr.getJSONObject(i).get("price").toString();
		
		total_price = total_price + Double.parseDouble(book_price);
		
		System.out.println("Total price of books is "+ total_price);
		
		// validation
		
		Assert.assertEquals(total_price, 62);
		
		}
		
		
		}
		
	}

