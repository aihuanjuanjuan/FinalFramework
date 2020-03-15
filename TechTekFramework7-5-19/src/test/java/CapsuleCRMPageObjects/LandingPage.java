package CapsuleCRMPageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DriverMaster.MasterWebDriver;

public class LandingPage extends MasterWebDriver {
	
	public LandingPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}



	//this page url is https://capsulecrm.com/
	
	//login button
	public static String loginBtn = ".//a[@class='main-nav__link main-nav__link--brand-secondary js-login']";

	
	
	//click login func 
	/**
	 * click login button
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	
	public void clickLogin() throws InterruptedException, IOException{
		
		WebElement loginButton = returnWebElementByXpath(loginBtn);
		if(loginButton.isDisplayed()) {
			System.out.println("true");
		}else {
			System.out.println("False");
		}
		waitAndClickElementByXpath(loginBtn);
	}
	

}
