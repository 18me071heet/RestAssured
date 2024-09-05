package httpRequests;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class ParseXMLResponse {

	@Test
	void testXMLResponse() {
		given()
		
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
		.statusCode(200)
		.header("Content-Type", "application/xml; charset=utf-8")
		.body("TravelerinformationResponse.page", equalTo(1))
		.body("TravelerinformationResponse.travlers.Travelerinformation[0].name",equalTo("Vijay Bharath Reddy"));
		
		// Approach 2
		
		Response res = given()
				
				.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
		
		
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, 1);
		
		String travelerName = res.xmlPath().get("TravelerinformationResponse.travlers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travelerName, "Vijay Bharath Reddy");
		
	}
	
	@Test
	void testXmlResponseBody() {
		
		Response res= given()
				.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlobj = new XmlPath(res.asString());
		
		// Verify Total number of travellers
		
		List<String> travellers= xmlobj.getList("TravelerinformationResponse.travlers.Travelerinformation");
		System.out.println("Total number of Travellers" + travellers);
		
		Assert.assertEquals(travellers.size(), 10);
		
		// Verify Travler name is present or not
		
		List<String> traveller_name = xmlobj.getList("TravelerinformationResponse.travlers.Travelerinformation.name");
		
		boolean status = false;
				
				for(String travelerName: traveller_name) {
					
					if(travelerName.equals("Vijay Bharath Reddy")) {
				
					status = true;
					break;
					}
					
					
				}
				
				Assert.assertEquals(status,true);

		
		
	}
	
	
	
}	
