package Steps;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.xml.sax.SAXException;

import DriverMaster.MasterWebDriver;
import Services.Deposit;
import Services.GetAccount;
import Services.GetTransaction;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DepositSteps_Parabank extends MasterWebDriver {

	public DepositSteps_Parabank() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	ParabankPageObjects.LandingPage landingPage = new ParabankPageObjects.LandingPage();
	ParabankPageObjects.RegistrationPage registrationPage = new ParabankPageObjects.RegistrationPage();
	ParabankPageObjects.WelcomePage welcomePage = new ParabankPageObjects.WelcomePage();
	ParabankPageObjects.LoggedInPage loggedinPage = new ParabankPageObjects.LoggedInPage();
	ParabankPageObjects.OverviewPage overviewPage = new ParabankPageObjects.OverviewPage();
	ParabankPageObjects.ActivityPage activityPage = new ParabankPageObjects.ActivityPage();
	ParabankPageObjects.TransactionDetailsPage transactionDetailsPage = new ParabankPageObjects.TransactionDetailsPage();

	// **********************************************************************
	// DepositSteps
	// **********************************************************************

	@Given("new user is registered")
	public void createNewAccount_For_Successful_Deposit_Scenario() throws Exception {

		initializeSelenium();
		loadUrl("https://parabank.parasoft.com/parabank/index.htm");

		landingPage.clickRegisterbtn();
		registrationPage.fillRegistrationForm();
		registrationPage.clickRegisterButton();
		loggedinPage.clickLogout();
		landingPage.login(RANDOMUSERNAME, PASSWORD);

	}

	@And("the account Balance should be (.*) dollars")
	public void validateAccountBalance_For_Successful_Deposit_Scenario(String expectedAccountBalance)
			throws IOException {

		String expectedBalance = "$" + expectedAccountBalance;
		overviewPage.validateBalance_BalanceTotal_AvailbleAmount(expectedBalance);

	}

	@When("the customer Deposits (.*) dollars")
	public void customerDepositsMoney(String expectedDepositAmount) throws ClientProtocolException, IOException, SAXException, ParserConfigurationException {

		// send request to get account
		accountID = overviewPage.getAccountID();
		getAccount_ResponseMap = GetAccount.getAccount_Request(accountID);
		String accountIDFromWebServiceResponse = getAccount_ResponseMap.get("id");
		Deposit.makeDeposit_SOAPRequest(accountIDFromWebServiceResponse,"10000");
		getAccount_ResponseMap = GetAccount.getAccount_Request(accountID);
		System.out.println("balance is now : "+ getAccount_ResponseMap.get("balance"));
	}

	@Then("the account Balance must be (.*) dollars")
	public void validateAccountBalanceAfterDeposit_For_Successful_Deposit_Scenario(String expectedDepositAmount) {

		
		
	}

	@Then("a new transaction should be Recorded \"([^\"]*)\"$")
	public void validateNewTransaction_For_Successful_Deposit_Scenario(String testname)
			throws IOException, InterruptedException, SAXException, ParserConfigurationException,
			EncryptedDocumentException, InvalidFormatException {



		driver.close();
		driver.quit();

	}

}
