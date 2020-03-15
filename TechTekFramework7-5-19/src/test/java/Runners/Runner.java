package Runners;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import DriverMaster.MasterWebDriver;
import GlobalObjects.GlobalObjects;
import Lib.Lib;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/Features", glue = "Steps", tags = { "@Login" })
public class Runner extends AbstractTestNGCucumberTests {

	public static ExtentHtmlReporter extentReporter;
	public static ExtentReports extent;

	@BeforeSuite()
	public void beforeSuite() throws IOException {

		GlobalObjects.companyUsername = System.getProperty("Username");
		GlobalObjects.Environment = System.getProperty("Environment");

		GlobalObjects.TIMESTAMP_RESULTSFOLDER = setupResultsTimeStampFolder();

		createAndSetupExcelResultsSheet();

		createLogFolders();

		String currentDate = Lib.getcurrentdateyyMMddHHmm();

		extentReporter = new ExtentHtmlReporter(
				GlobalObjects.TIMESTAMP_RESULTSFOLDER + "/" + "ExtentReport_" + currentDate + ".html");
		extent = new ExtentReports();

		// attach the reporter which we created in Step 1
		extent.attachReporter(extentReporter);

	}

	// before class
	@BeforeClass()
	public void beforeClass() throws IOException {

		getReqresTestData();
		getParaBankTestData();

	}

	@Parameters({ "osType", "browserType", "runLocal", "useGrid", "useAWS", "useSauceLabs", "accountStatus" })
	@BeforeMethod()
	public void beforeMethod(@Optional String osType, @Optional String browserType, @Optional String runLocal,
			@Optional String useGrid, @Optional String useAWS, @Optional String useSauceLabs,
			@Optional String accountStatus) throws IOException {

		GlobalObjects.osType = osType;
		GlobalObjects.browserType = browserType;
		GlobalObjects.runLocal = runLocal;
		GlobalObjects.useGrid = useGrid;
		GlobalObjects.useAWS = useAWS;
		GlobalObjects.useSauceLabs = useSauceLabs;
		GlobalObjects.accountStatus = accountStatus;

		

	}

//	@AfterMethod()
//	public void afterMethod() {
//		MasterWebDriver.driver.close();
//	}
//
//	// after class
//	@AfterClass
//	public void afterClass() {
//
//		MasterWebDriver.driver.quit();
//
//	}

	// after suite
	@AfterSuite
	public void afterSuite() {

		extent.flush();
		System.out.println("Flush out report and do some stuff after the whole suite.");

	}

	public static void getReqresTestData() throws IOException {

		System.out.println("inside function " + GlobalObjects.Environment);
		String ENV = GlobalObjects.Environment;
		int rowIndex = Lib.return_Match_RowNum_By_ENVIRONMENT_value(ENV, GlobalObjects.ReqresTestDataPath);

		String email = Lib.readExcelValue(rowIndex, 1, GlobalObjects.ReqresTestDataPath);
		String password = Lib.readExcelValue(rowIndex, 2, GlobalObjects.ReqresTestDataPath);

		GlobalObjects.testData_reqres.put("email", email);
		GlobalObjects.testData_reqres.put("password", password);

	}

	public static void getParaBankTestData() throws IOException {

		int rowIndex = Lib.return_Match_RowNum_By_ENVIRONMENT_value(GlobalObjects.Environment,GlobalObjects.testData_ParabankPath);

		GlobalObjects.testData_parabank.put("firstname",
				Lib.readExcelValue(rowIndex, 3, GlobalObjects.testData_ParabankPath));

		GlobalObjects.testData_parabank.put("lastname",
				Lib.readExcelValue(rowIndex, 4, GlobalObjects.testData_ParabankPath));
		GlobalObjects.testData_parabank.put("address",
				Lib.readExcelValue(rowIndex, 5, GlobalObjects.testData_ParabankPath));
		GlobalObjects.testData_parabank.put("city",
				Lib.readExcelValue(rowIndex, 6, GlobalObjects.testData_ParabankPath));
		GlobalObjects.testData_parabank.put("state",
				Lib.readExcelValue(rowIndex, 7, GlobalObjects.testData_ParabankPath));
		GlobalObjects.testData_parabank.put("zipcode",
				Lib.readExcelValue(rowIndex, 8, GlobalObjects.testData_ParabankPath));
		GlobalObjects.testData_parabank.put("phonenumber",
				Lib.readExcelValue(rowIndex, 9, GlobalObjects.testData_ParabankPath));
		GlobalObjects.testData_parabank.put("ssn",
				Lib.readExcelValue(rowIndex, 10, GlobalObjects.testData_ParabankPath));
		GlobalObjects.testData_parabank.put("username",
				Lib.readExcelValue(rowIndex, 1, GlobalObjects.testData_ParabankPath));
		GlobalObjects.testData_parabank.put("password",
				Lib.readExcelValue(rowIndex, 2, GlobalObjects.testData_ParabankPath));

		GlobalObjects.FIRSTNAME = GlobalObjects.testData_parabank.get("firstname");
		GlobalObjects.LASTNAME = GlobalObjects.testData_parabank.get("lastname");
		GlobalObjects.ADDRESS = GlobalObjects.testData_parabank.get("address");
		GlobalObjects.CITY = GlobalObjects.testData_parabank.get("city");
		GlobalObjects.STATE = GlobalObjects.testData_parabank.get("state");
		GlobalObjects.ZIPCODE = GlobalObjects.testData_parabank.get("zipcode");
		GlobalObjects.PHONENUM = GlobalObjects.testData_parabank.get("phonenumber");
		GlobalObjects.SSN = GlobalObjects.testData_parabank.get("ssn");
		GlobalObjects.USERNAME = GlobalObjects.testData_parabank.get("username");
		GlobalObjects.PASSWORD = GlobalObjects.testData_parabank.get("password");

	}

	public static void createLogFolders() {
		// create log folder
		GlobalObjects.LogsFOLDER_Path = GlobalObjects.TIMESTAMP_RESULTSFOLDER + "/Logs";
		Lib.createFolder(GlobalObjects.LogsFOLDER_Path);

		String capsuleCrmLogsPath = GlobalObjects.LogsFOLDER_Path + "/CapsuleCRM";
		GlobalObjects.PARABANK_LOG_FOLDER_PATH = GlobalObjects.LogsFOLDER_Path + "/ParaBank";
		GlobalObjects.REQRES_LOG_FOLDER_PATH = GlobalObjects.LogsFOLDER_Path + "/Reqres";

		Lib.createFolder(capsuleCrmLogsPath);
		Lib.createFolder(GlobalObjects.PARABANK_LOG_FOLDER_PATH);
		Lib.createFolder(GlobalObjects.REQRES_LOG_FOLDER_PATH);

		// Login feature path for reqres
		String reqresLoginPath = Lib.createFolder(GlobalObjects.REQRES_LOG_FOLDER_PATH + "/Login");
		Lib.createFolder(GlobalObjects.PARABANK_LOG_FOLDER_PATH + "/Login");

		System.out.println(reqresLoginPath);

	}

	public static void createAndSetupExcelResultsSheet() throws IOException {

		String currentDate = Lib.getcurrentdateyyMMddHH();
		GlobalObjects.excelResultsPath = GlobalObjects.TIMESTAMP_RESULTSFOLDER + "/" + currentDate + ".xlsx";
		Lib.create_SetupExcelResultsSheet(GlobalObjects.excelResultsPath);

	}

	public static String setupResultsTimeStampFolder() {

		String currentDate = Lib.getcurrentdateyyMMddHHmm();
		GlobalObjects.TIMESTAMP_RESULTSFOLDER = Lib.createFolder(GlobalObjects.regressionFolderPath + currentDate);
		System.out.println("this is my resultsTimeStampFolder" + GlobalObjects.TIMESTAMP_RESULTSFOLDER);

		return GlobalObjects.TIMESTAMP_RESULTSFOLDER;
	}

}
