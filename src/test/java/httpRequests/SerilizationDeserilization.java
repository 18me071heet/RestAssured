package httpRequests;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Serialization => Converting Pojo Object into Json Object

public class SerilizationDeserilization {
	
	// POJO to Json (Serialization)

	//@Test
	void convertPojo2Json() throws JsonProcessingException {
		
		// Create Java object using Pojo class
		
		Pojo stupojo = new Pojo();
		stupojo.setName("Manthan");
		stupojo.setLocation("Jamnagar");
		
		String hobbyArr[]= {"banking","sleeping"};
		stupojo.setHobby(hobbyArr);
		
		// Convert java object ---> Json Object ( Serialization )
		
		ObjectMapper objMapper = new ObjectMapper();
		String jsonData =objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
		System.out.println(jsonData);
	}
	
	@Test
	void convertJson2Pojo() throws JsonMappingException, JsonProcessingException {
		
		String jsonData = "{\r\n"
				+ "  \"name\" : \"Manthan\",\r\n"
				+ "  \"location\" : \"Jamnagar\",\r\n"
				+ "  \"hobby\" : [ \"banking\", \"sleeping\" ]\r\n"
				+ "}";
		
		
		// Convert Json Data to Pojo Object
		
		ObjectMapper objMapper = new ObjectMapper();
		
		Pojo stupojo = objMapper.readValue(jsonData, Pojo.class);
		
		System.out.println("Name:" + stupojo.getName());
		System.out.println("Location:" + stupojo.getLocation());
		System.out.println("Hobby 1:" + stupojo.getHobby()[0]);
		System.out.println("Hobby 2:" + stupojo.getHobby()[1]);


	}
}
