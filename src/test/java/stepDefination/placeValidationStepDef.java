package stepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIresources;
import resources.BaseUtility;
import resources.TestDataBuild;

public class placeValidationStepDef extends BaseUtility {
	RequestSpecification res;
	ResponseSpecification ressp;
	Response response;
	static String place_id;

	
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String language, String address) throws IOException {
		
		 res = given().spec(requestSpecification())
				 .body(data.addPlacePayload(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		APIresources resourcesAPI =APIresources.valueOf(resource);
		System.out.println(resourcesAPI.getResource());
		
		 ressp = new ResponseSpecBuilder().expectStatusCode(200)
					.expectContentType(ContentType.JSON).build();
        
		 if(method.equalsIgnoreCase("POST")) 
        	 response = res.when().post(resourcesAPI.getResource());
		 else if(method.equalsIgnoreCase("GET"))
			 response = res.when().get(resourcesAPI.getResource());
				
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    
     		assertEquals(response.getStatusCode(),200);
		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
	
		
		assertEquals(getJsonPath(response, keyValue),ExpectedValue);
	    
	}
	
	@Then("varify place Id created map {string} using {string}")
	public void varify_place_Id_created_map_using(String expectedName, String resource) throws IOException {
		
		 place_id = getJsonPath(response, "place_id");
		 res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		 user_calls_with_http_request( resource, "GET");
		 String actualName = getJsonPath(response, "name");
		 assertEquals(actualName, expectedName);
		
	}
	
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	 
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
		
	}
	
	

}
