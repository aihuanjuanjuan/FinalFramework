package ParabankPageObjects;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.openqa.selenium.support.FindBy;

import DriverMaster.MasterWebDriver;
import GlobalObjects.GlobalObjects;
import Lib.Lib;

public class RegistrationPage extends MasterWebDriver {

	public RegistrationPage() throws IOException {
		super();
	}

	// elements

	public static String firstnameField = ".//table[@class='form2']/tbody/tr[1]/td[2]/input";
	
	
	public static String lastnameField=  ".//table[@class='form2']/tbody/tr[2]/td[2]/input";

	public static String addressField = ".//table[@class='form2']/tbody/tr[3]/td[2]/input";

	public static String cityField= ".//table[@class='form2']/tbody/tr[4]/td[2]/input";

	public static String stateField = ".//table[@class='form2']/tbody/tr[5]/td[2]/input";

	public static String zipcodeField = ".//table[@class='form2']/tbody/tr[6]/td[2]/input";

	public static String phoneField = ".//table[@class='form2']/tbody/tr[7]/td[2]/input";

	public static String ssnField = ".//table[@class='form2']/tbody/tr[8]/td[2]/input";

	public static String usernameField = ".//table[@class='form2']/tbody/tr[10]/td[2]/input";

	public static String passwordField = ".//table[@class='form2']/tbody/tr[11]/td[2]/input";

	public static String confirmField = ".//table[@class='form2']/tbody/tr[12]/td[2]/input";

	public static String registerBtn = ".//table[@class='form2']/tbody/tr[13]/td[2]/input";

	// ~~~~~~~~~~~~ reusable action methods~~~~~~~~~

	public void fillRegistrationForm() throws Exception {

		RANDOMUSERNAME = Lib.generateRandomUsername(GlobalObjects.USERNAME);
		
		sendKeysToWebElementByXpath(firstnameField, FIRSTNAME);
		sendKeysToWebElementByXpath(lastnameField, LASTNAME);
		sendKeysToWebElementByXpath(addressField, ADDRESS);
		sendKeysToWebElementByXpath(cityField, CITY);
		sendKeysToWebElementByXpath(stateField, STATE);
		sendKeysToWebElementByXpath(zipcodeField, ZIPCODE);
		sendKeysToWebElementByXpath(phoneField, PHONENUM);
		sendKeysToWebElementByXpath(ssnField, SSN);
		sendKeysToWebElementByXpath(usernameField, RANDOMUSERNAME); //<--random username goes here
		sendKeysToWebElementByXpath(passwordField, PASSWORD);
		sendKeysToWebElementByXpath(confirmField, PASSWORD);


	}
	

	public void enterFirstname(String firstname) throws Exception {

		sendKeysToWebElementByXpath(firstnameField, firstname);
	}

	public void enterLastName(String lastname) throws Exception {

		sendKeysToWebElementByXpath(lastnameField, lastname);

	}

	public void enterAddress(String address) throws Exception {

		sendKeysToWebElementByXpath(addressField, address);

	}

	public void enterCity(String city) throws Exception {

		sendKeysToWebElementByXpath(cityField, city);

	}

	public void enterState(String state) throws Exception {

		sendKeysToWebElementByXpath(stateField, state);

	}

	public void enterZipCode(String zipCode) throws Exception {

		sendKeysToWebElementByXpath(zipcodeField, zipCode);

	}

	public void enterPhoneNum(String phoneNum) throws Exception {

		sendKeysToWebElementByXpath(phoneField, phoneNum);

	}

	public void enterSSN(String ssn) throws Exception {

		sendKeysToWebElementByXpath(ssnField, ssn);

	}

	public void enterUsername(String username) throws Exception {

		sendKeysToWebElementByXpath(usernameField, username);

	}

	public void enterPassword(String password) throws Exception {

		sendKeysToWebElementByXpath(passwordField, password);

	}

	public void enterConfirmPassword(String password) throws Exception {

		sendKeysToWebElementByXpath(confirmField, password);

	}
	
	public void clickRegisterButton() throws InterruptedException, IOException {
		
		waitAndClickElementByXpath(registerBtn);
	
	}


}










