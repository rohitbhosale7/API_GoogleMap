package resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.properties;

public class Utils extends properties {
	
	public static RequestSpecification RequestSpecBuild;
	ResponseSpecification ResponseSpecBuild;
	PrintStream stream;
	public RequestSpecification requestSpecification() throws IOException
	{
		if(RequestSpecBuild==null)
		{
	    stream = new PrintStream(new FileOutputStream("log.txt"));
	    RequestSpecBuild = new RequestSpecBuilder()
    			 .setBaseUri(getproperty("baseUrl"))
    			 .addQueryParam("key", "qaclick123")
    			 .setContentType(ContentType.JSON)
    			 .addFilter(RequestLoggingFilter.logRequestTo(stream))
    			 .addFilter(ResponseLoggingFilter.logResponseTo(stream)).build();
    	return RequestSpecBuild;
		}
		return RequestSpecBuild;
	}
	
	public ResponseSpecification responseSpecification()

	{
		ResponseSpecBuild = new ResponseSpecBuilder()
   			 .expectStatusCode(200)
   			 .expectContentType(ContentType.JSON).build();
		return ResponseSpecBuild;
		
	}
}
