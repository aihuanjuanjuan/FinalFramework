package CapsuleCRMPageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DriverMaster.MasterWebDriver;

public class PeopleAndOrganizationsPage extends MasterWebDriver {

	public PeopleAndOrganizationsPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String addPersonBtn = ".//div[@class='page-header']/div[@class='page-header-right']/a[1]";
	public String titleDropDownBtn = "//select[@name='party:j_id108:j_id116']";
	public String firstNameTxtField = "party:fnDecorate:fn";
	public String lastNameTxtField = "party:lnDecorate:ln";
	public String jobTitleTxtField = "party:roleDecorate:jobTitle";
	public String organizationTxtField = "party:orgDecorate:org";
	public String tagsTxtField = "party:tagsDecorate:tagComboBox";
	public String phoneNumTxtField = "party:j_id325:0:phnDecorate:number";
	public String phoneNumDropDown = "//select[@name='party:j_id325:0:phnDecorate:j_id328']";
	public String addAnotherPhoneNumber = "//span[contains(text(),'+ Ad‌d an‌other ph‌one nu‌mber')]";
	public String emailAddressTxtField = "party:j_id342:0:emlDecorate:nmbr";
	public String addAnotherEmailAddress = "//span[contains(text(),'+ Ad‌d an‌other em‌ail ad‌dress')]";
	public String emailAddressDropDown = "//select[@name='party:j_id342:0:emlDecorate:j_id345']";
	public String webiteAndSocialNetworkstxtField = "party:j_id370:0:webDecorate:web";
	public String webiteAndSocialNetworksDropDown = "//select[@id='party:j_id370:0:webDecorate:service']";
	public String addAddressOption = "//span[contains(text(),'Ad‌d an ad‌dress')]";
	public String addressTxtField = "//textarea[@id='party:j_id389:0:strDecorate:str']";
	public String addressDropDown = "//select[@name='party:j_id389:0:j_id438']";
	public String cityTownTxtField = "party:j_id389:0:ctyDecorate:cty";
	public String stateCountytxtField = "party:j_id389:0:rgnDecorate:dist";
	public String zipPostalTxtField = "party:j_id389:0:ctyPstCd:pstCd";
	public String saveBtn = "party:save";

	public void clickAddPerson() throws InterruptedException, IOException {
		waitAndClickElementByXpath(addPersonBtn);
	}

	public void addNewPersonWithFullInfo(String title, String firstname, String lastname, String jobTitle,
			String organization, String tag, String phonNum, String email, String twitterHandle, String address,
			String city, String stateCounty, String zipCodePostal) throws Exception {

		waitAndClickElementByXpath(titleDropDownBtn);
		selectDropDownOptionByVisibleText(titleDropDownBtn, title);
		sendKeysToWebElementByXpath(firstNameTxtField, firstname);
		sendKeysToWebElementByXpath(lastNameTxtField, lastname);
		sendKeysToWebElementByXpath(jobTitleTxtField, jobTitle);
		sendKeysToWebElementByXpath(organizationTxtField, organization);
		sendKeysToWebElementByXpath(tagsTxtField, tag);
		sendKeysToWebElementByXpath(phoneNumTxtField, phonNum);
		sendKeysToWebElementByXpath(emailAddressTxtField, email);
		sendKeysToWebElementByXpath(webiteAndSocialNetworkstxtField, twitterHandle);
		waitAndClickElementByXpath(addAddressOption);
		sendKeysToWebElementByXpath(addressTxtField, address);
		selectDropDownOptionByVisibleText(addressDropDown, "Office");
		sendKeysToWebElementByXpath(cityTownTxtField, city);
		sendKeysToWebElementByXpath(stateCountytxtField, stateCounty);
		sendKeysToWebElementByXpath(zipPostalTxtField, zipCodePostal);
		waitAndClickElementByXpath(saveBtn);

	}

	/**
	 * addperson button title firstname lastname job title organization tag phone
	 * num'email save button
	 */

}
