package ParabankPageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import DriverMaster.MasterWebDriver;
import Lib.Lib;

public class OverviewPage extends MasterWebDriver {

	public OverviewPage() throws IOException {
		super();
	}

	public static String accntColumnTxt = ".//*[@id='accountTable']/thead/tr/th[1]";
	public static String balanceColumnTxt = ".//*[@id='accountTable']/thead/tr/th[2]";
	public static String availbleAmountColumnTxt = ".//*[@id='accountTable']/thead/tr/th[3]";
	public static String accountIdLink = ".//*[@id='accountTable']/tbody/tr[1]/td[1]/a";
	public static String balanceValueTxt = ".//*[@id='accountTable']/tbody/tr[1]/td[2]";
	public static String availbleAmountTxt = ".//*[@id='accountTable']/tbody/tr[1]/td[3]";
	public static String totalBalanceTxt = ".//*[@id='accountTable']/tbody/tr[2]/td[2]/b";

	public String validateBalance_BalanceTotal_AvailbleAmount(String expectedBalance) throws IOException {

		String actualAvailableBalance = getAvaibleAmount();
		String actualTotalBalance = getTotalBalance();

		actualUIbalance = getBalanceValueTxt();
		if (actualUIbalance.equalsIgnoreCase(expectedBalance)
				&& actualAvailableBalance.equalsIgnoreCase(expectedBalance)
				&& actualTotalBalance.equalsIgnoreCase(expectedBalance)) {

			passedValidationCount++;
//			Lib.log("actualUIbalance :" + actualUIbalance + "vs expected expected Balance : " + expectedBalance
//					+ "Passed", "Withdraw", "Successful_Withdraw");
//			Lib.log("actualAvailableBalance :" + actualAvailableBalance + "vs expected expected Balance : "
//					+ expectedBalance + "Passed", "Withdraw", "Successful_Withdraw");
//			Lib.log("actualTotalBalance :" + actualTotalBalance + "vs expected expected Balance : " + expectedBalance
//					+ "Passed", "Withdraw", "Successful_Withdraw");
			System.out.println("validateBalance_BalanceTotal_AvailbleAmount Passed");

		} else {

			try {
				
				Assert.assertEquals(actualUIbalance, expectedBalance);
				Assert.assertEquals(actualAvailableBalance, expectedBalance);
				Assert.assertEquals(actualTotalBalance, expectedBalance);

			} catch (java.lang.AssertionError e) {

				System.out.println("validateBalance_BalanceTotal_AvailbleAmount step Failed : " + e.getMessage());
//				Lib.log("validateBalance_BalanceTotal_AvailbleAmount step Failed : " + e.getMessage(), "Withdraw",
//						"Successful_Withdraw");

				failedValidationCount++;
				failedValidationMessageList.add(e.getMessage());
			}
		}

		if (actualUIbalance.equalsIgnoreCase(expectedBalance)
				&& actualAvailableBalance.equalsIgnoreCase(expectedBalance)
				&& actualTotalBalance.equalsIgnoreCase(expectedBalance)) {

//			Lib.log("actualUIbalance :" + actualUIbalance + "vs expected expected Balance : " + expectedBalance
//					+ "Passed", "Withdraw", "Successful_Withdraw");
//			Lib.log("actualAvailableBalance :" + actualAvailableBalance + "vs expected expected Balance : "
//					+ expectedBalance + "Passed", "Withdraw", "Successful_Withdraw");
//			Lib.log("actualTotalBalance :" + actualTotalBalance + "vs expected expected Balance : " + expectedBalance
//					+ "Passed", "Withdraw", "Successful_Withdraw");

			System.out.println("validateBalance_BalanceTotal_AvailbleAmount Passed");
			passedValidationCount++;

		} else {

			try {

				Assert.assertEquals(expectedBalance, actualUIbalance);
				Assert.assertEquals(expectedBalance, actualAvailableBalance);
				Assert.assertEquals(expectedBalance, actualTotalBalance);

			} catch (java.lang.AssertionError e) {

//				Lib.log("validateBalance_BalanceTotal_AvailbleAmount : " + e.getMessage(), "Withdraw",
//						"Successful_Withdraw");
				System.out.println("validateBalance_BalanceTotal_AvailbleAmount Failed : " + e.getMessage());

				failedValidationCount++;
				failedValidationMessageList.add(e.getMessage());

			}

		}

		return actualUIbalance;
	}

	public String getBalanceValueTxt() {

		String text = getTextFromElement(balanceValueTxt);
		return text;
	}

	public String getAccountID() {
		
		String text = getTextFromElement(accountIdLink);
		return text;
	}

	public void clickAccountIDLink() throws InterruptedException, IOException {

		waitAndClickElementByXpath(accountIdLink);
	}

	public String getAvaibleAmount() {
		
		String text = getTextFromElement(availbleAmountTxt);
		return text;
	}

	public String getTotalBalance() {
		
		String text = getTextFromElement(totalBalanceTxt);
		return text;
	}

}
