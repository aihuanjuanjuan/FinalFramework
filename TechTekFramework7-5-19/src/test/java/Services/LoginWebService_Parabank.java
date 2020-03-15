package Services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocClassloadingException;
import org.testng.Assert;
import org.xml.sax.SAXException;

import GlobalObjects.GlobalObjects;
import Lib.Lib;

public class LoginWebService_Parabank extends GlobalObjects{
	
	
	
	public static void successfulLogin_parabank(String testname) throws SAXException, IOException, ParserConfigurationException, EncryptedDocumentException, InvalidFormatException {
		
		
		System.out.println("i am executing parabank testname : "+ testname);
		
		
		endpoint=getLoginParabank_Endpoint();
		
		String requestbody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.parabank.parasoft.com/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <ser:login>\n" + 
				"         <ser:username>user212</ser:username>\n" + 
				"         <ser:password>user212</ser:password>\n" + 
				"      </ser:login>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
		
		
		//add headers
		Map<String,String> headers = new HashMap<String,String>();
		
		
		//set string entity 
		StringEntity stringEntity = new StringEntity(requestbody,"UTF-8");
		stringEntity.setChunked(true);
		
		//create http post and tell http post the endpoint you want to hit
		HttpPost httpPost = new HttpPost(endpoint);
		
		//tell httppost the request body you want to send
		httpPost.setEntity(stringEntity);
		
		//Initialize httpclient (set up httpclient)
		HttpClient httpClient = null;
		httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(httpPost);
		
		//get the response body entity
		HttpEntity entity = response.getEntity();

		//get response status code
		int actualStatusCode = response.getStatusLine().getStatusCode();
		
		//get the response body using entityutils to string
		String responsebody = EntityUtils.toString(entity);
		
		System.out.println("actual Response Status code : "+ actualStatusCode);
		System.out.println("response body : "+ responsebody);
		
		//get the id from the response
		String actualID = Lib.returnxmlvalue(responsebody, "id", 0);
		
		System.out.println("actual id : "+actualID);
		
		/**
		 <id>15986</id>
				<firstName>Sami</firstName>
				<lastName>Sabir-idrissi</lastName>
				<address>
					<street>3100 South Manchester Street</street>
					<city>Falls Church</city>
					<state>Virginia</state>
					<zipCode>Jenkins</zipCode>
				</address>
				<phoneNumber>5715503399</phoneNumber>
				<ssn>4324234</ssn>
		 */
		
		//validation
		String expectedID = "15986";
		
		/**
		 * what to validate
		 * - status code
		 * - id
		 */
		
		if(actualStatusCode==200&&expectedID.equalsIgnoreCase(actualID)) {
			
			Status = "Passed";
			
		}else {
			
			
			Status = "Failed";
			
			try {
				
				Assert.assertEquals(actualStatusCode, 200);
				Assert.assertEquals(actualID, expectedID);

			}catch(java.lang.AssertionError e) {
				
				comment = e.getMessage();
				System.out.println("Assertion failed for successfulLogin parabank : "+ comment);
			}
			
		}
		
		
		//write results to log and excel
		Lib.logWriter_For_SOAP("Login", testname, requestbody, headers, endpoint, responsebody, 
				response, GlobalObjects.PARABANK_LOG_FOLDER_PATH);
		
		//excel result update
		Lib.excelwrite(excelResultsPath, new Object[] {Lib.getcurrentdate(),Environment,"Parabank","Active",
				"login",testname,Status,endpoint,comment});
		
	}
	
	
	public static String getLoginParabank_Endpoint() {
		
		
		
		if(Environment.equalsIgnoreCase("DEV")) {
			
			endpoint = "https://parabank.parasoft.com/parabank/services/ParaBank";
			
		}else if(Environment.equalsIgnoreCase("FT")) {
			
			endpoint = "https://parabank.parasoft.com/parabank/services/ParaBank";

			
		}
		
		return endpoint;
		
	}
	
	

}
