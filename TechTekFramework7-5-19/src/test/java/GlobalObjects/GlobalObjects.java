package GlobalObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Parameters;
import org.w3c.dom.Document;

import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;

public class GlobalObjects {

	// define file paths here:
	
	
	public static String testData_ParabankPath = "/Users/sami/Desktop/Automation/sidrissi/Parabank/Properties/ParaBankTestData.xlsx";

	public static String ReqresTestDataPath = "/Users/sami/Desktop/Automation/bAlmanza/Reqres/Properties/reqresTestData.xlsx";
	public static String regressionFolderPath = "/Users/sami/Desktop/Automation/ssabir/Automation/AutomationResults/Regression/";
	public static String TIMESTAMP_RESULTSFOLDER;

	public static String osType;
	public static String browserType;
	public static String runLocal;
	public static String useGrid;
	public static String useAWS;
	public static String useSauceLabs;
	public static String accountStatus;

	
	public static String actualCurrentURL = null;
	public static String actualPageHeader = null;

	public static String expectedPageHeader = null;
	public static String expectedURLPATH = null;
	
	public static HashMap<String, Integer> resultsMap = new HashMap<String, Integer>();
	public static String rcTicket = null;
	public static String releaseNumber = null;
	public static String companyUsername = null;
	public static String PARABANK_LOG_FOLDER_PATH = null;
	public static String REQRES_LOG_FOLDER_PATH = null;
	public static String ParaBankLoginLogFolder = null;

	public static String LogsFOLDER_Path = null;

	public static String COMMENT = null;
	public static String requestBody = null;
	public static String RELEASE_NUMBER = null;
	public static String APPTYPE = null;
	public static String REGION = null;
	public static String randomUsername = null;
	public static String randomPassword = null;
	public static String ACCOUNTSTATUS = null;
	public static String FIRSTNAME = null;
	public static String LASTNAME = null;
	public static String ADDRESS = null;
	public static String CITY = null;
	public static String STATE = null;
	public static String ZIPCODE = null;
	public static String PHONENUM = null;
	public static String SSN = null;
	public static String USERNAME = null;
	public static String PASSWORD = null;

	public static int passed = 0;
	public static int failed = 0;

	public static String resultsFolder_testOutput = null;

	public static String path;
	public static String logpath;
	public static String excelResultsPath = null;
	public static String resultsFolder = null;

	public static Document document;
	public static HttpPost httpPost;
	public static HttpClient httpClient;
	public static StringEntity stringEntity;
	public static HttpResponse response = null;
	public static HttpEntity entity;
	public static String consumer = null;
	public static String StatusMessage = null;
	public static HttpResponse httpResponse = null;
	public static RequestSpecification httpRequest = null;
	public static String requestBodySoap = null;

	public static String responseBody = null;
	public static List<String> failedValidationMessageList = new ArrayList<String>();
	public static int responseStatusCode;
	public static int totalValidationCount;
	public static int passedValidationCount;
	public static int failedValidationCount;

	public static String Status = null;
	public static String comment = null;
	public static String runResultsFileName = null;

	public static String username_Excel = null;
	public static String password_Excel = null;

	public static String ExpiredRefreshToken = null;
	public static String ExpiredAccessToken = null;
	public static String ExpiredResetKey = null;

	public static String entityBody = null;

	public static String endpoint = null;
	public static String EndpointRunner = null;
	public static String Environment = null;

	public static String Username = null;
	public static String RANDOMUSERNAME = null;

	public static String password = null;
	public static int expectedResponseStatusCode = 0;
	public static String expectedResponseMessage = null;

	public static Map<String, String> headersMap = new HashMap<String, String>();
	public static Headers responseHeaders;

	public static HashMap<String, String> getAccount_ResponseMap = new HashMap<String, String>();
	public static HashMap<String, String> getAccounts_ResponseMap = new HashMap<String, String>();
	public static HashMap<String, String> getTransaction_ResponseMap = new HashMap<String, String>();
	public static HashMap<String, String> testData_parabank = new HashMap<String, String>();
	public static HashMap<String, String> testData_reqres = new HashMap<String, String>();

	public static HashMap<String, String> login_ResponseMap = new HashMap<String, String>();
	public static HashMap<String, String> getCustomer_ResponseMap = new HashMap<String, String>();

	public static String actualUIbalance = null;

	public static String accountID = null;
	public static String transactionID = null;
	public static String Comment = null;
	public static String expectedWithdrawAmount = null;
	public static String expectedBalance = null;
	public static String expectedDespositAmount = null;
	public static String actualWithdrawAmount = null;

}
