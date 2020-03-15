package DriverMaster;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cucumber.listener.Reporter;

import GlobalObjects.GlobalObjects;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MasterWebDriver extends GlobalObjects {

	private static String screenshotName;
	public static WebDriver driver;
	protected JavascriptExecutor jsExecutor;

	public MasterWebDriver() throws IOException {
		jsExecutor = ((JavascriptExecutor) driver);

	}

	public static void initializeSelenium() throws IOException {

		if (runLocal.equalsIgnoreCase("true")) {

			MasterWebDriver.getLocalDriver(osType, browserType);

		} else if (useGrid.equalsIgnoreCase("true")) {

		} else if (useSauceLabs.equalsIgnoreCase("true")) {

		} else {
			System.out.println("do something else");
		}
	}

	public static WebDriver getLocalDriver(String osType, String browserType) throws IOException {

		try {

			if (browserType.equalsIgnoreCase("chrome")) {

				if (osType.equalsIgnoreCase("win")) {
					WebDriverManager.chromedriver().setup();

					driver = new ChromeDriver();
				} else if (osType.equalsIgnoreCase("mac")) {
					WebDriverManager.chromedriver().setup();

					driver = new ChromeDriver();
				}

			} else if (browserType.equalsIgnoreCase("firefox")) {

				if (osType.equalsIgnoreCase("mac")) {

				} else if (osType.equalsIgnoreCase("win")) {

				}
				driver = new FirefoxDriver();

			}

		} catch (Exception e) {

			System.out.println("Unable to load browser: " + e.getMessage());

		}

		return driver;

	}

	public static void loadUrl(String url) throws Exception {

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public static WebElement returnWebElementByXpath(String xpath) {

		WebElement element = driver.findElement(By.xpath(xpath));
		return element;
	}

	public static void selectDropDownOptionByVisibleText(WebElement element, String value) {

		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public static void selectDropDownOptionByVisibleText(String elementLocator, String value) {

		WebElement element = driver.findElement(By.xpath(elementLocator));
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public static void selectDropDownOptionByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public static void selectOptionWithText(String textToSelect) {
		try {

			WebElement autoOptions = driver.findElement(By.xpath(".//div[@class='searchresult']/ul"));
			WebDriverWait wait = new WebDriverWait(driver, 7);
			boolean isDisplayed = autoOptions.isDisplayed();
			System.out.println(isDisplayed);
			wait.until(ExpectedConditions.visibilityOf(autoOptions));
			List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
			for (WebElement option : optionsToSelect) {
				System.out.println(option.getText());
				if (option.getText().equals(textToSelect)) {
					System.out.println("Trying to select: " + textToSelect);
					option.click();
					break;
				}
			}

		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public static ArrayList<WebElement> getListOfWebElementsByXpath(String locator) {

		ArrayList<WebElement> list = new ArrayList<WebElement>();
		list = (ArrayList<WebElement>) driver.findElements(By.xpath(locator));

		return list;

	}

	public static String getTextFromElement(String elementLocator) {

		String text = driver.findElement(By.xpath(elementLocator)).getText();

		return text;
	}

	/**********************************************************************************
	 ** CLICK METHODS
	 * 
	 * @throws IOException
	 **********************************************************************************/

	public static void waitAndClickElement(WebElement element) throws InterruptedException, IOException {
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < 10) {
			try {

				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(element)).click();// explicit wait
				System.out.println("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
				clicked = true;
			} catch (Exception e) {
				System.out.println("Unable to wait and click on WebElement, Exception: " + e.getMessage());
				Assert.fail(
						"Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
			}
			attempts++;
		}
		System.out.println("number of attempts to click on element : " + attempts);
	}

	public static void waitAndClickElementGenericPath(String elementLocator, String locatorType)
			throws InterruptedException, IOException {

		WebElement element = null;
		if (locatorType.equalsIgnoreCase("css")) {
			element = driver.findElement(By.cssSelector(elementLocator));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(elementLocator));
		} else if (locatorType.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(elementLocator));
		}
		System.out.println(element.isDisplayed());
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < 10) {
			try {

				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(element)).click();// explicit wait
				System.out.println("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
				clicked = true;
			} catch (Exception e) {
				System.out.println("Unable to wait and click on WebElement, Exception: " + e.getMessage());
				Assert.fail(
						"Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
			}
			attempts++;
		}
		System.out.println("number of attempts to click on element : " + attempts);
	}

	public static void waitAndClickElementByXpath(String elementLocator) throws InterruptedException, IOException {

		WebElement element = driver.findElement(By.xpath(elementLocator));
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < 10) {
			try {

				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(element)).click();// explicit wait
				System.out.println("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
				clicked = true;
			} catch (Exception e) {
				System.out.println("Unable to wait and click on WebElement, Exception: " + e.getMessage());
				Assert.fail(
						"Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
			}
			attempts++;
		}
		System.out.println("number of attempts to click on element : " + attempts);
	}

	public void waitAndClickElementsUsingByLocator(By by) throws InterruptedException {
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < 10) {
			try {

				WebDriverWait wait = new WebDriverWait(driver, 10);

				wait.until(ExpectedConditions.elementToBeClickable(by)).click();
				System.out
						.println("Successfully clicked on the element using by locator: " + "<" + by.toString() + ">");
				clicked = true;
			} catch (Exception e) {
				System.out.println(
						"Unable to wait and click on the element using the By locator, Exception: " + e.getMessage());
				Assert.fail("Unable to wait and click on the element using the By locator, element: " + "<"
						+ by.toString() + ">");
			}
			attempts++;
		}
	}

	public void clickOnTextFromDropdownList(WebElement list, String textToSearchFor) throws Exception {
		Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
		try {
			tempWait.until(ExpectedConditions.elementToBeClickable(list)).click();
			list.sendKeys(textToSearchFor);
			list.sendKeys(Keys.ENTER);
			System.out.println("Successfully sent the following keys: " + textToSearchFor
					+ ", to the following WebElement: " + "<" + list.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to send the following keys: " + textToSearchFor
					+ ", to the following WebElement: " + "<" + list.toString() + ">");
			Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
		}
	}

	public static void clickOnElementUsingCustomTimeout(String elementLocator, WebDriver driver, int timeout) {

		WebElement element = driver.findElement(By.xpath(elementLocator));
		try {
			final WebDriverWait customWait = new WebDriverWait(driver, timeout);
			customWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
			element.click();
			System.out.println("Successfully clicked on the WebElement, using locator: " + "<" + element + ">"
					+ ", using a custom Timeout of: " + timeout);
		} catch (Exception e) {
			System.out.println("Unable to click on the WebElement, using locator: " + "<" + element + ">"
					+ ", using a custom Timeout of: " + timeout);
			Assert.fail("Unable to click on the WebElement, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/

	/**********************************************************************************
	 ** ACTION METHODS
	 **********************************************************************************/

	public void actionMoveAndClick(WebElement element) throws Exception {
		Actions ob = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {

			wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			ob.moveToElement(element).click().build().perform();
			System.out.println("Successfully Action Moved and Clicked on the WebElement, using locator: " + "<"
					+ element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = element;

			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).isEnabled();
			if (elementPresent == true) {
				ob.moveToElement(elementToClick).click().build().perform();
				System.out.println(
						"(Stale Exception) - Successfully Action Moved and Clicked on the WebElement, using locator: "
								+ "<" + element.toString() + ">");
			}
		} catch (Exception e) {
			System.out.println("Unable to Action Move and Click on the WebElement, using locator: " + "<"
					+ element.toString() + ">");
			Assert.fail("Unable to Action Move and Click on the WebElement, Exception: " + e.getMessage());
		}
	}

	public void actionMoveAndClickByLocator(By element) throws Exception {
		Actions ob = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			if (elementPresent == true) {
				WebElement elementToClick = driver.findElement(element);
				ob.moveToElement(elementToClick).click().build().perform();
				System.out.println("Action moved and clicked on the following element, using By locator: " + "<"
						+ element.toString() + ">");
			}
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = driver.findElement(element);
			ob.moveToElement(elementToClick).click().build().perform();
			System.out
					.println("(Stale Exception) - Action moved and clicked on the following element, using By locator: "
							+ "<" + element.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to Action Move and Click on the WebElement using by locator: " + "<"
					+ element.toString() + ">");
			Assert.fail(
					"Unable to Action Move and Click on the WebElement using by locator, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/

	/**********************************************************************************
	 ** SEND KEYS METHODS /
	 **********************************************************************************/
	public static void sendKeysToWebElementGenericLocatorType(String elementLocator, String textToSend, String locatorType) throws Exception {
	
		WebElement element = null;
		if (locatorType.equalsIgnoreCase("css")) {
			element = driver.findElement(By.cssSelector(elementLocator));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(elementLocator));
		} else if (locatorType.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(elementLocator));
		}
		try {

			WaitUntilWebElementIsVisible(element);
			element.clear();
			element.sendKeys(textToSend);
			System.out.println("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<"
					+ element.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to locate WebElement: " + "<" + element.toString()
					+ "> and send the following keys: " + textToSend);
			Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
		}
	}
	
	
	public static void sendKeysToWebElementByXpath(String elementLocator, String textToSend) throws Exception {

		WebElement element = driver.findElement(By.xpath(elementLocator));

		try {

			WaitUntilWebElementIsVisible(element);
			element.clear();
			element.sendKeys(textToSend);
			System.out.println("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<"
					+ element.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to locate WebElement: " + "<" + element.toString()
					+ "> and send the following keys: " + textToSend);
			Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
		}
	}

	public static void sendKeys(String elementLocator, Keys key) {

		driver.findElement(By.xpath(elementLocator)).sendKeys(key);
	}

	/**********************************************************************************/
	/**********************************************************************************/

	/**********************************************************************************
	 ** JS METHODS & JS SCROLL
	 **********************************************************************************/
	public static void scrollToElementByWebElementLocator(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
			System.out.println(
					"Succesfully scrolled to the WebElement, using locator: " + "<" + element.toString() + ">");
		} catch (Exception e) {
			System.out.println("Unable to scroll to the WebElement, using locator: " + "<" + element.toString() + ">");
			Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
		}
	}

	public static void jsPageScroll(int numb1, int numb2) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(" + numb1 + "," + numb2 + ")");
			System.out.println("Succesfully scrolled to the correct position! using locators: " + numb1 + ", " + numb2);
		} catch (Exception e) {
			System.out
					.println("Unable to scroll to element using locators: " + "<" + numb1 + "> " + " <" + numb2 + ">");
			Assert.fail("Unable to manually scroll to WebElement, Exception: " + e.getMessage());
		}
	}

	public static void waitAndclickElementUsingJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			js.executeScript("arguments[0].click();", element);
			System.out
					.println("Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement staleElement = element;
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
			if (elementPresent == true) {
				js.executeScript("arguments[0].click();", elementPresent);
				System.out.println("(Stale Exception) Successfully JS clicked on the following WebElement: " + "<"
						+ element.toString() + ">");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Unable to JS click on the following WebElement: " + "<" + element.toString() + ">");
			Assert.fail("Unable to JS click on the WebElement, Exception: " + e.getMessage());
		}
	}

	public static void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**********************************************************************************/
	/**********************************************************************************/

	/**********************************************************************************
	 ** WAIT METHODS
	 **********************************************************************************/
	public static boolean WaitUntilWebElementIsVisible(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("WebElement is visible using locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			System.out.println("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
			Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
			return false;
		}
	}

	public static boolean WaitUntilWebElementIsVisibleUsingByLocator(By element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			System.out.println("Element is visible using By locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			System.out.println("WebElement is NOT visible, using By locator: " + "<" + element.toString() + ">");
			Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
			return false;
		}
	}

	public static boolean isElementClickable(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			System.out.println("WebElement is clickable using locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			System.out.println("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
			return false;
		}
	}

	public static boolean waitUntilPreLoadElementDissapears(By element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		return wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	/**********************************************************************************/
	/**********************************************************************************/

	/**********************************************************************************
	 ** PAGE METHODS
	 **********************************************************************************/

	public static String getCurrentURL() {
		try {
			String url = driver.getCurrentUrl();
			System.out.println("Found(Got) the following URL: " + url);
			return url;
		} catch (Exception e) {
			System.out.println("Unable to locate (Get) the current URL, Exception: " + e.getMessage());
			return e.getMessage();
		}
	}

	public static String waitForSpecificPage(String urlToWaitFor) {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			String url = driver.getCurrentUrl();
			wait.until(ExpectedConditions.urlMatches(urlToWaitFor));
			System.out.println("The current URL was: " + url + ", " + "navigated and waited for the following URL: "
					+ urlToWaitFor);
			return urlToWaitFor;
		} catch (Exception e) {
			System.out.println("Exception! waiting for the URL: " + urlToWaitFor + ",  Exception: " + e.getMessage());
			return e.getMessage();
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/

	/**********************************************************************************
	 ** ALERT & POPUPS METHODS
	 **********************************************************************************/
	public static void closePopups(By locator) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			for (WebElement element : elements) {
				if (element.isDisplayed()) {
					element.click();
					wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
					System.out.println("The popup has been closed Successfully!");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception! - could not close the popup!, Exception: " + e.toString());
			throw (e);
		}
	}

	public static boolean checkPopupIsVisible() {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			@SuppressWarnings("unused")
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			System.out.println("A popup has been found!");
			return true;
		} catch (Exception e) {
			System.err.println("Error came while waiting for the alert popup to appear. " + e.getMessage());
		}
		return false;
	}

	public static boolean isElementDislplayed(String elementLocator) {

		boolean isDisplayed = driver.findElement(By.xpath(elementLocator)).isDisplayed();

		return isDisplayed;
	}

	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void closeAlertPopupBox() throws AWTException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
		} catch (UnhandledAlertException f) {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			System.out.println("Unable to close the popup");
			Assert.fail("Unable to close the popup, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/

	/***
	 * EXTENT REPORT
	 ****************************************************************/
	public static String returnDateStamp(String fileExtension) {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
		return date;
	}

	public static void captureScreenshot() throws IOException, InterruptedException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		screenshotName = returnDateStamp(".jpg");

		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\output\\imgs\\" + screenshotName));

		Reporter.addStepLog("Taking a screenshot!");
		Reporter.addStepLog("<br>");
		Reporter.addStepLog("<a target=\"_blank\", href=" + returnScreenshotName() + "><img src="
				+ returnScreenshotName() + " height=200 width=300></img></a>");
	}

	public static String returnScreenshotName() {
		return (System.getProperty("user.dir") + "\\output\\imgs\\" + screenshotName).toString();
	}

	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;

		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;

			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}

		} finally {
			is.close();
			os.close();
		}
	}

	public static void copyLatestExtentReport() throws IOException {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_");
		File source = new File(System.getProperty("user.dir") + "\\output\\report.html");
		File dest = new File(System.getProperty("user.dir") + "\\output\\" + date.toString() + ".html");
		copyFileUsingStream(source, dest);
	}

	public static void refreshPage() {
		driver.close();
	}

}
