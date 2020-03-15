package ParabankPageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DriverMaster.MasterWebDriver;

public class LoggedInPage extends MasterWebDriver {

	public LoggedInPage() throws IOException {
		super();
	}

	public static String welcomeFirstNameAndLastNameTxt = ".//*[@id='leftPanel']/p";
	public static String accountServicesTxt = ".//*[@id='leftPanel']/h2";
	public static String openNewAccountLink = ".//*[@id='leftPanel']/ul/li[1]/a";
	public static String accountOverviewLink = ".//div[@id='bodyPanel']/div[1]/ul/li[2]/a";
	public static String transferFundsLink = ".//*[@id='leftPanel']/ul/li[3]/a";
	public static String billPayLink = ".//*[@id='leftPanel']/ul/li[4]/a";
	public static String findTransactionsLink = ".//*[@id='leftPanel']/ul/li[5]/a";
	public static String updateContactInfoLink = ".//*[@id='leftPanel']/ul/li[6]/a";
	public static String requestLoanLink = ".//*[@id='leftPanel']/ul/li[7]/a";
	public static String logoutLink = ".//*[@id='leftPanel']/ul/li[8]/a";

	private static List<WebElement> accountServicesLinks_LIST = new ArrayList<WebElement>();
	
	
	
	
	public void validateAllAccountservicesLinks() {

		accountServicesLinks_LIST.add(returnWebElementByXpath(welcomeFirstNameAndLastNameTxt));
		accountServicesLinks_LIST.add(returnWebElementByXpath(accountOverviewLink));
		accountServicesLinks_LIST.add(returnWebElementByXpath(transferFundsLink));
		accountServicesLinks_LIST.add(returnWebElementByXpath(transferFundsLink));
		accountServicesLinks_LIST.add(returnWebElementByXpath(billPayLink));
		accountServicesLinks_LIST.add(returnWebElementByXpath(findTransactionsLink));
		accountServicesLinks_LIST.add(returnWebElementByXpath(updateContactInfoLink));
		accountServicesLinks_LIST.add(returnWebElementByXpath(requestLoanLink));
		accountServicesLinks_LIST.add(returnWebElementByXpath(logoutLink));

		for(int i = 0; i<accountServicesLinks_LIST.size();i++) { 
			
			WebElement element = accountServicesLinks_LIST.get(i);
			String elementTxt = element.getText();
			System.out.println("AccountService Link txt : "+ elementTxt);
						
		}	
		
	}

	
	public void clickAccountOverViewLink() throws InterruptedException, IOException {
		
		waitAndClickElementByXpath(accountOverviewLink);
		
	}
	
	public void clickLogout() throws InterruptedException, IOException {
		
		waitAndClickElementByXpath(logoutLink);
	}
	
	
}
