package CapsuleCRMPageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DriverMaster.MasterWebDriver;

public class PartyPage extends MasterWebDriver {

	public PartyPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String title = "//span[@class='party-details-title']";

	public String getTitleText() {

		String text = getTextFromElement(title);

		return text;
	}

}
