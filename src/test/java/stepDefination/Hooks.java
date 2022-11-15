package stepDefination;

import java.io.IOException;

import cucumber.api.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//execute this code only when place id is null
				//write a code that will give you place id
		
		placeValidationStepDef stp = new placeValidationStepDef();
		if(placeValidationStepDef.place_id==null) {
		stp.add_Place_Payload("shubham", "english", "mumbai");
		stp.user_calls_with_http_request("AddPlaceAPI", "POST");
		stp.varify_place_Id_created_map_using("shubham", "getPlaceAPI");
		}
	}

}
