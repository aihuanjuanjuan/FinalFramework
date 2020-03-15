package Steps;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import DriverMaster.MasterWebDriver;
import ParabankPageObjects.HomePage;
import ParabankPageObjects.ParaBankLandingPage;
import ParabankPageObjects.RegistrationPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Registration_ParabankSteps extends MasterWebDriver {

	public Registration_ParabankSteps() throws IOException {
		super();
	}


	ParaBankLandingPage parabankLandingPage = new ParaBankLandingPage();
	RegistrationPage registrationPage = new RegistrationPage();
	HomePage homePage = new HomePage();
	
	@Given("^user fills out the registration form$")
	public void userFillsOutRegistrationForm() throws Exception {

		initializeSelenium();
		loadUrl("https://parabank.parasoft.com/parabank/index.htm");
		parabankLandingPage.clickRegisterBtn();

		registrationPage.fillRegistrationForm();
		registrationPage.clickRegisterButton();
		
		
		
	}

	@Then("^user should be logged in after submitting the registration form \"([^\"]*)\"$")
	public void UserSHouldBeLoggedinAfterSubmittingRegistrationForm(String testname) {


		homePage.validateSuccessfulRegistrationMessage();
//		homePage.validate_Welcome_Text_With_First_and_lastName(firstname, lastname);
		driver.close();
		driver.quit();
		
	}
	
	
	
	
	

}
