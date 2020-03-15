package UnitTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetWeatherByName {
	
	
	
	@Test()
	public void testGetWeatherByName() {
		
		//Endpoint
		String ENDPOINT = "http://api.openweathermap.org/data/2.5/weather";
		
		//tell rest assured the endpoint
		RestAssured.baseURI = ENDPOINT;
		
		//setup request specification (httpRequest)
		RequestSpecification httpRequest = RestAssured.given();
		
		httpRequest.queryParam("q", "tokyo");
		httpRequest.queryParam("APPID", "7a45cdf142f7ce5f53e3996c7a27ff1d");
		/**
		 * (BASEURI)http://api.openweathermap.org/data/2.5/weather
		 * (Query starts here) ?q=Astana&APPID=7a45cdf142f7ce5f53e3996c7a27ff1d
		 */
		
		//send GET request
		Response response = httpRequest.get();
		
		//get the status code and response body
		int actualStatusCode = response.getStatusCode();
		String responseBody = response.getBody().asString();
		
		//print out status code and response body
		System.out.println("Actual Status Code : "+ actualStatusCode);
		System.out.println("Response body : "+ responseBody);
		
		//define expected results
		int expectedID = 440; //tokyo weather id
		
		//get inside the resposne body and extract data from body
		JsonPath jspath = response.jsonPath();
		
		int actualWeatherID = jspath.get("weather[0].id");
		
		System.out.println("Actual weather ID : "+ actualWeatherID);
		
		
		//validate the response information
		//this our bread and butter our money!!!
		String Status = null;
		if(actualStatusCode==200&&expectedID==actualWeatherID) {
			
			Status = "Passed";
		}else {
			
			Status = "Failed";
			
			try {
				
				Assert.assertEquals(200, actualStatusCode);
				Assert.assertEquals(actualWeatherID, expectedID);
			}catch(java.lang.AssertionError e){
				
				String comment = e.getMessage();
				
				System.out.println("Assertion failed : "+ comment);
				
				
			}
			
		}
		
		
		
		
		
		
		
		
		
		
	}

}
