package ParabankPageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DriverMaster.MasterWebDriver;

public class WelcomePage extends MasterWebDriver {

	// welcome page is the page the server send you too after you registering a new account

	public WelcomePage() throws IOException {
		super();
	}

	public static String successfulRegistrationMessageTxt = ".//*[@id='rightPanel']/p";

	public static String welcomeLinkUsernameTxt = ".//*[@id='rightPanel']/h1";

	public static String welcomelinkFirstlastnameTxt = ".//*[@id='leftPanel']/p";

	public void validateSuccessfulRegistrationMessage() {

		
		String actualText = getTextFromElement(successfulRegistrationMessageTxt);

		String expectedText = "Your account was created successfully. You are now logged in.";

		
		if (actualText.equals(expectedText)) {
			System.out.println("validateSuccessfulRegistrationMessage : PASSED");
		} else {
			System.out.println("validateSuccessfulRegistrationMessage : FAILED");
		}

	}
	
	public void validate_Welcome_Text_With_First_and_lastName(String firstname, String lastname) {
		
		
		
		String firstnameLastname_WelcomeTxt = getTextFromElement(welcomelinkFirstlastnameTxt);
		
		System.out.println("firstnameLastname_WelcomeTxt : "+firstnameLastname_WelcomeTxt);
		
	}

	
	
	
}
