package ParabankPageObjects;

import java.io.IOException;

import DriverMaster.MasterWebDriver;

public class LandingPage extends MasterWebDriver{

	public LandingPage() throws IOException {
		super();
	}

	public String registerBtn = ".//div[@id='loginPanel']/p[2]/a";
	public String loginField = ".//*[@id='loginPanel']/form/div[1]/input";
	public String passwordField = ".//*[@id='loginPanel']/form/div[2]/input";
	public String loginBtn = ".//*[@id='loginPanel']/form/div[3]/input";

	
	
	
	public void enterUsername(String username) throws Exception {
		
		sendKeysToWebElementByXpath(loginField, username);
		
	}
	
	public void enterPassword(String password) throws Exception {
		
		sendKeysToWebElementByXpath(passwordField, password);

	}
	
	
	public void clickLoginBtn() throws IOException, InterruptedException {
		
		waitAndClickElementByXpath(loginBtn);

	}
	
	public void clickRegisterbtn() throws InterruptedException, IOException {
		
		waitAndClickElementByXpath(registerBtn);

	}
	
	public void login(String username, String password) throws Exception {
		
		sendKeysToWebElementByXpath(loginField, username);
		sendKeysToWebElementByXpath(passwordField, password);
		waitAndClickElementByXpath(loginBtn);

	}
	
}
