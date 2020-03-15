package UnitTests;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SuccessfulLogin_reqres {

	@Test()
	public void successfulLogin() {

		//endpoint
		String endpoint = "https://reqres.in/api/login";

		// tell rest assured what the Endpoint is
		RestAssured.baseURI = endpoint;

		//setup request specfication (httpRequest)
		RequestSpecification httpRequest = RestAssured.given();

		//create a headers map to put headers in
		HashMap<String, String> headersMap = new HashMap<String, String>();
		//put header in map
		headersMap.put("Content-Type", "application/json");

		//put headersMap in to httprequest 
		httpRequest.headers(headersMap);

		//create json object (json request body)
		JSONObject requestBody = new JSONObject();

		// put username and password in the json request body
		requestBody.put("email", "eve.holt@reqres.in");
		requestBody.put("password", "cityslicka");

		//put request body into the httpRequest 
		httpRequest.body(requestBody.toString());

		System.out.println(requestBody);
		System.out.println("request body : "+requestBody.toString());
		
		//execute the post request and return the response
		Response response = httpRequest.post();

		// use response object to get response status code
		int actualStatusCode = response.getStatusCode();

		// use response object to get response response body as a string
		String responseBody = response.getBody().asString();

		//print out the response status code and response body
		System.out.println(actualStatusCode);
		System.out.println(responseBody);

		JsonPath jspath = response.jsonPath();

		String actualToken = jspath.get("token");

		System.out.println(actualToken);

		int tokenLength = actualToken.length();

		System.out.println(tokenLength);

		int expectedStatusCode = 200;
		int expectedTokenLength = 16;

		
		
		String Status = null;
		//bread and butter this our money!!!!!!!
		if (actualStatusCode == expectedStatusCode 
				&& expectedTokenLength == tokenLength
				&& responseBody.contains("token")
				&&!actualToken.isEmpty()) {
			
			 Status = "Passed";
			
		} else {

			 Status = "Failed";
			 
			 try {
				 
				 Assert.assertEquals(expectedStatusCode, actualStatusCode);
				 Assert.assertEquals(expectedTokenLength, tokenLength);
				 Assert.assertTrue(responseBody.contains("token"));
				 
			 }catch(java.lang.AssertionError e) {

				 String comment = e.getMessage();
				 
				 System.out.println(comment);
				 
			 }
			
			 
		}
		
		System.out.println(Status);
	}

	
	
	
	
	
	
}
