package httpRequests;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;



public class DifferentWaysPostResponse {
	
	
	
	// Post request body by using HashMap
	
	//@Test
	void testPostbyHashMap() {
		
		HashMap data = new HashMap();
		data.put("name", "Gunjan");
		data.put("location","Rajkot");
		
		String hobbyar[] = {"Dancing","Sleeping"};
		data.put("hobby", hobbyar);
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name", equalTo("Gunjan"))
		.body("location", equalTo("Rajkot"))
		.body("hobby[0]", equalTo("Dancing"))
		.body("hobby[1]", equalTo("Sleeping"));
	}
	
	
	   // Post Request body by using org.Json library
	
	//@Test
	void testPostbyJsonLibrary() {
		
		
		JSONObject data = new JSONObject();
		data.put("name", "Bansri");
		data.put("location", "Jamnagar");
		
		String hobarr[] = {"Dancing","Playing"};
		data.put("hobby", hobarr);
		
		
		 given()
		.contentType("application/json")
		.body(data.toString())                     // Convert to string because we have using Json Library
		
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.log().all();
		 
		 
	}

	   // Post Request body by Using PoJo ( Plain Old Java Object )class
	
	//@Test
	void pojoResponse() {
		
		Pojo_Postrequest data = new Pojo_Postrequest();
		data.setName("Harsh");
		data.setLocation("Ahemdabad");
		
		String hobbyArr[]= {"eating","sleeping"};
		data.setHobby(hobbyArr);
		
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("Harsh"))
		.body("location", equalTo("Ahemdabad"))
		.body("hobby[0]", equalTo("eating"))
		.body("hobby[1]", equalTo("sleeping"))
		.log().all();

		
	}
	
	
	// Post Request body by using External Josn File
	
	@Test
	void testPostbyExternalJsonFile() throws FileNotFoundException {
		
	File f = new File(".\\body.json");
	
	FileReader fr = new FileReader(f);
	
	JSONTokener jt = new JSONTokener(fr);
	
	JSONObject data = new JSONObject(jt);
	
		
		given()
		.body(data.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.log().all();
	}

}
