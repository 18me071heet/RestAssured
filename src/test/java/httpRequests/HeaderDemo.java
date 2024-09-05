package httpRequests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderDemo {
	
	@Test
	void testHeaders() {
		
		given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.header("Content-Type","text/html; charset=ISO-8859-1")
		.header("Content-Encoding", "gzip")
		.header("Server", "gws")
		.log().all();

	}
	
	@Test
	void getHeaders() {
		
             Response res =given()
		
		                  .when()
		                  .get("https://www.google.com/");

              // Get Single Header Info.

              //String header_value= res.getHeader("Content-Type");
             // System.out.println("the value of Content type header is"+header_value);
           
              // Get All Headers Info.
              
             Headers myHeaders= res.getHeaders();
             
             for(Header hd:myHeaders) 
             
            {
            	 
            	 System.out.println(hd.getName()+"                 "+hd.getValue());
            	 
             }
	}

}
