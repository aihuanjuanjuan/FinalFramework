package UnitTests;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SuccessfulLogin_parabank {
	
	
	
	@Test()
	public void successfulLogin_parabank() throws ClientProtocolException, IOException, SAXException, ParserConfigurationException {
		
		String ENDPOINT = "https://parabank.parasoft.com/parabank/services/ParaBank";
		
		String requestbody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.parabank.parasoft.com/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <ser:login>\n" + 
				"         <ser:username>sami212</ser:username>\n" + 
				"         <ser:password>sami212</ser:password>\n" + 
				"      </ser:login>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
		
		//set string entity 
		StringEntity stringEntity = new StringEntity(requestbody,"UTF-8");
		stringEntity.setChunked(true);
		
		//create http post and tell http post the endpoint you want to hit
		HttpPost httpPost = new HttpPost(ENDPOINT);
		
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
		String actualID = returnxmlvalue(responsebody, "id", 0);
		
		System.out.println("actual id : "+actualID);
	}

	
	
	
	public static String returnxmlvalue(String xml, String key, int i)
			throws SAXException, IOException, ParserConfigurationException {
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource src = new InputSource();
		src.setCharacterStream(new StringReader(xml));

		Document doc = builder.parse(src);
		String value = doc.getElementsByTagName(key).item(i).getTextContent();
		return value;
	}
}
