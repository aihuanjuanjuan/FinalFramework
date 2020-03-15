package CapsuleCRMPageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DriverMaster.MasterWebDriver;

public class NavBar extends MasterWebDriver {

	public NavBar() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String peopleAndOrganizationsLink = ".//*[@class='nav-bar-section nav-bar-main is-teams-launch']/nav/div[2]";
	public static String casesLink = ".//*[@class='nav-bar-section nav-bar-main is-teams-launch']//span[5]";
	public static String accountNameTopRightNavBar = "//span[@class='nav-bar-username']";
	public static String accountSettingsLinkFromDropDownMenu = "//a[contains(text(),'Account Settings')]";

	// func : click cases link
	public void clickCasesLink() throws InterruptedException, IOException {

		waitAndClickElementByXpath(casesLink);
	}

	// func : click peopleAndOrganizations link
	public void clickpeopleAndOrganizationsLink() throws InterruptedException, IOException {

		waitAndClickElementByXpath(peopleAndOrganizationsLink);
	}

	public void clickAccountNameTopRightNavBar() throws InterruptedException, IOException {

		waitAndClickElementByXpath(accountNameTopRightNavBar);

	}

	public void clickAccountSettingsLink() throws InterruptedException, IOException {
		waitAndClickElementByXpath(accountSettingsLinkFromDropDownMenu);

	}

}
