package ParabankPageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DriverMaster.MasterWebDriver;

public class TransactionDetailsPage extends MasterWebDriver {

	public TransactionDetailsPage() throws IOException {
		super();
	}

	
	public static String transactionIDTxtValue = ".//*[@id='rightPanel']/table/tbody/tr[1]/td[2]";
	public static String amountTxtValue = ".//*[@id='rightPanel']/table/tbody/tr[5]/td[2]";

	public String getTransactionID() {
		
		String text=getTextFromElement(transactionIDTxtValue);
		return text;
	}
	
	public String getAmountTxtValue() {
		
		String text=getTextFromElement(amountTxtValue);
		return text;
		
	}
	
	
}
