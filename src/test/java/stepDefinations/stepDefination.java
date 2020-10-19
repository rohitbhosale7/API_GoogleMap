package stepDefinations;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ResourcesAPI;
import resources.TestDataBuild;
import resources.Utils;
import utils.ReusableMethods;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POJO.location;
import POJO.places;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefination extends Utils{
	
	RequestSpecification resp;
	Response responce;
	ResourcesAPI resourceApi;
	String respo, placeid;
	 JsonPath js;
	TestDataBuild testdata = new TestDataBuild();
	
	@Given("Add Place payload with {string} {string} {string}")
	public void add_Place_payload_with(String name, String language, String address) throws IOException {
	    	
	    	resp = given().spec(requestSpecification())
	    			.body(testdata.addPlacePayload(name,language,address));
	    }

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
	    resourceApi = ResourcesAPI.valueOf(resource);
		
		if(method.equalsIgnoreCase("POST"))
		{
	    	responce = resp.when().post(resourceApi.getResource())
	    			.then().spec(responseSpecification()).contentType(ContentType.JSON).extract().response();
		}
		

		if(method.equalsIgnoreCase("GET"))
		{
	    	responce = resp.when().get(resourceApi.getResource())
	    			.then().spec(responseSpecification()).contentType(ContentType.JSON).extract().response();
		}
		
	    	
	    }

	    @Then("^the API call got success with status code 200$")
	    public void the_api_call_got_success_with_status_code_200() throws Throwable {
	       assertEquals(responce.getStatusCode(),200);
	    	
	    }
	    
	    @Then("{string} in response body is {string}")
	    public void in_response_body_is(String key, String ExpectedValue) {
	    	respo = responce.asString();
	    	 System.out.println(respo);
	    	 js= ReusableMethods.rawToJson(respo);
		     placeid = js.get("place_id");
		     System.out.println(js.get(key).toString());
		 
	    	assertEquals(js.get(key).toString(), ExpectedValue);
	     
	    }
	    
	    @Then("verify placeId created maps to {string} using {string}")
	    public void verify_placeId_created_maps_to_using(String name, String resource) throws IOException {
	        // Write code here that turns the phrase above into concrete actions
	    	resp = given().spec(requestSpecification()).queryParam("place_id", placeid);
	    	user_calls_with_http_request(resource, "GET");
	    	respo = responce.asString();
	    	System.out.println(respo);
	        js= ReusableMethods.rawToJson(respo);
	        System.out.println(js.get("name"));
	        assertEquals(js.get("name"), name);
	     
	    	
	    }
	
}