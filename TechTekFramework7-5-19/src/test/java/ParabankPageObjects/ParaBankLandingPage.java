package ParabankPageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DriverMaster.MasterWebDriver;

public class ParaBankLandingPage extends MasterWebDriver {

	public ParaBankLandingPage() throws IOException {
		super();
		
	}
	
	public static String registerBtn = ".//div[@id='loginPanel']/p[2]/a";
	public static String loginField = ".//*[@id='loginPanel']/form/div[1]/input";
	public static String passwordField = ".//*[@id='loginPanel']/form/div[2]/input";
	public static String loginBtn = ".//*[@id='loginPanel']/form/div[3]/input";

	
	
	public void clickRegisterBtn() throws InterruptedException, IOException {
		
		waitAndClickElementByXpath(registerBtn);
	}
	
	public void enterUsername(String username) throws Exception {
		
		sendKeysToWebElementByXpath(loginField, username);
		
	}
	
	public void enterPassword(String password) throws Exception {
		
		sendKeysToWebElementByXpath(passwordField, password);

	}
	
	
	public void clickLoginBtn() throws IOException, InterruptedException {
		
		//clickOnElementUsingCustomTimeout(loginBtn, driver, 10);
		waitAndClickElementByXpath(loginBtn);

	}
	

	
	public WelcomePage login(String username, String password) throws Exception {
		
		sendKeysToWebElementByXpath(loginField, username);
		sendKeysToWebElementByXpath(passwordField, password);
		clickOnElementUsingCustomTimeout(loginBtn, driver, 10);
		waitAndClickElementByXpath(loginBtn);

		return new WelcomePage();
	}
	
}
