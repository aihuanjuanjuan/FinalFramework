package CapsuleCRMPageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DriverMaster.MasterWebDriver;

public class LoginPage extends MasterWebDriver {

	// this page is https://<your user name here>.capsulecrm.com/login

	public LoginPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	// username
	public static String usernameField = "//input[@id='login:usernameDecorate:username']";

	// password
	public static String passwordField = "//input[@id='login:passwordDecorate:password']";

	// login button
	public static String loginBtn = "login:login";

	// login func
	/**
	 * enter username enter pass click login button
	 * 
	 * @throws Exception
	 */
	public void login(String username, String password) throws Exception {

		sendKeysToWebElementGenericLocatorType(usernameField, username, "xpath");
		sendKeysToWebElementGenericLocatorType(passwordField, password, "xpath");
		waitAndClickElementGenericPath(loginBtn, "id");

	}

}
