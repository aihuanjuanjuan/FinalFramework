package CapsuleCRMPageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DriverMaster.MasterWebDriver;

public class SiteAddressGoToPage extends MasterWebDriver {
	
	//this page is https://app.capsulecrm.com/siteaddress/goto
	
	public SiteAddressGoToPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	//site address field
	public String capsuleSiteAddressField = ".//*[@id='login-subdomain']";
	public String goToLoginBtn = ".//*[@id='login-button']";

	
	//enter site login func
	public void enterSiteLoginAddress(String siteAddress) throws Exception{
		
		sendKeysToWebElementByXpath(capsuleSiteAddressField, siteAddress);
		
	}
	
	//func click go to login	
	public void clickGoToLogin() throws InterruptedException, IOException{
		
		waitAndClickElementByXpath(goToLoginBtn);
		
	}

}
