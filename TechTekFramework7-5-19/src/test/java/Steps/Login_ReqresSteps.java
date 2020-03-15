package Steps;

import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.testng.Assert;

import GlobalObjects.GlobalObjects;
import Lib.Lib;
import Runners.Runner;
import Services.Login_Reqres;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Login_ReqresSteps extends GlobalObjects {
	
	

	@Then("^execute successful login \"([^\"]*)\"$")
	public void execute_sucessful_Login(String testname) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		Login_Reqres.successfulLogin_Reqres(testname);
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
