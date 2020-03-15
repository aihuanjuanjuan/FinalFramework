package UnitTests;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import GlobalObjects.GlobalObjects;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class RestAssurePractice {

	@Test
	public void resreqpostcall() {

		/*
		 * rest call endpoint header request body
		 */

		// Endpoint
		String end = "https://reqres.in";
		String resource = "/api/register";

		RestAssured.baseURI = end;

		// headers
		HashMap<String, String> headers = new HashMap<String, String>();

		headers.put("Content-Type", "application/json");

		RequestSpecification request = RestAssured.given();

		request.headers(headers);

		// request body

		JSONObject requestbody = new JSONObject();

		requestbody.put("email", "eve.holt@reqres.in");
		requestbody.put("password", "13134534564");

		request.body(requestbody.toString());

		request.log().all();
		Response respons = request.post(resource);

		System.out.println(respons.getStatusCode());
		System.out.println(respons.getBody().asString());
		
		io.restassured.path.json.JsonPath js=respons.jsonPath();
		
		String token=js.get("token");
		int id=js.getInt("id");
		System.out.println("Here is my token from response  ::::   "+token);
		
		/*
		 * validate status code 200, response body include token key, token itself is 16 digit string, id is not empty
		 */
		
		if(respons.getStatusCode()==200 && respons.getBody().asString().contains("token")
				&& token.length()==16 && id !=0) {
			GlobalObjects.Status="Passed";
		}else {
			GlobalObjects.Status="Failed";
			
			Assert.assertEquals(200, respons.getStatusCode());
			Assert.assertTrue(respons.getBody().asString().contains("token"));
			
			Assert.assertEquals(16, token.length());
			Assert.assertNotSame(0, id);
			
		
			
		}
		
		System.out.println("this test cases   :::    "+GlobalObjects.Status);
		

	}

}
