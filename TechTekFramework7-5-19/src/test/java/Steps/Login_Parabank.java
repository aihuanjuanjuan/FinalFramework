package Steps;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.xml.sax.SAXException;

import DriverMaster.MasterWebDriver;
import Services.LoginWebService_Parabank;
import cucumber.api.java.en.Then;

public class Login_Parabank extends MasterWebDriver {


	public Login_Parabank() throws IOException {
		super();
	}

	
	
	@Then("^execute successful Parabank login \"([^\"]*)\"$")
	public void execute_sucessful_Login_Parabank(String testname) throws EncryptedDocumentException,
			InvalidFormatException, IOException, SAXException, ParserConfigurationException {

		// call webservice login function for parabank
		LoginWebService_Parabank.successfulLogin_parabank(testname);
	}

}
