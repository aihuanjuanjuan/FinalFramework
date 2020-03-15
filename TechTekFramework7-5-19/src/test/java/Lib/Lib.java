package Lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import GlobalObjects.GlobalObjects;
import io.restassured.response.Response;

public class Lib extends GlobalObjects {

	public static String projpath = null;
	public static String Writepath = null;
	public static List<HashMap<String, String>> ldapMap = null;

	public static String returnxmlvalue(String xml, String key, int i)
			throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource src = new InputSource();
		src.setCharacterStream(new StringReader(xml));

		Document doc = builder.parse(src);
		String value = doc.getElementsByTagName(key).item(i).getTextContent();
		return value;
	}

	public static List<String> returnListOfEnvironmentsToTest() {
		List<String> envList = new ArrayList<String>();

		envList.add("SIT");
		envList.add("DEV");
		envList.add("FT");
		envList.add("STAGING");
		envList.add("PERF");

		return envList;

	}

	public static String generateRandomUsername(String username) {
		// ~~~~~ make username unique by appendning random string just for testing
		// purposes so we wont get "User already created error message" ~~~~~
		String randomString = Lib.generateRandomString();

		GlobalObjects.RANDOMUSERNAME = username + randomString;

		System.out.println("**********************************");
		System.out.println("random string : " + randomString);
		System.out.println("random username : " + GlobalObjects.RANDOMUSERNAME);
		System.out.println("**********************************");
		return GlobalObjects.RANDOMUSERNAME;

	}

	public static String stringvalue(String xml, String key, int i)
			throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource src = new InputSource();
		src.setCharacterStream(new StringReader(xml));

		Document doc = builder.parse(src);
		String value = doc.getElementsByTagName(key).item(i).getTextContent();
		return value;
	}

	public static void createParaBankLogFolders() {

		// create log folder
		Lib.createFolder(GlobalObjects.logpath);

		// define all log folder name by service name or scenario name
		String Registration = GlobalObjects.path + "/Logs/Registration";
		String CreateNewAccount = GlobalObjects.path + "/Logs/CreateNewAccount";
		String Withdraw = GlobalObjects.path + "/Logs/Withdraw";
		String Deposit = GlobalObjects.path + "/Logs/Deposit";
		String TransferFunds = GlobalObjects.path + "/Logs/TransferFunds";

		// create service/scenario log folder
		Lib.createLogFolder_for_Service(Registration, "Registration");
		Lib.createLogFolder_for_Service(CreateNewAccount, "CreateNewAccount");
		Lib.createLogFolder_for_Service(Withdraw, "Withdraw");
		Lib.createLogFolder_for_Service(Deposit, "Deposit");
		Lib.createLogFolder_for_Service(Registration, "Registration");
		Lib.createLogFolder_for_Service(TransferFunds, "TransferFunds");

	}

	public static void setPermissions2(String strPath) {
		String path = strPath;
		File file = new File(path);
		if (file.exists()) {
			System.out.println("read=" + file.canRead());
			System.out.println("write=" + file.canWrite());
			System.out.println("Execute=" + file.canExecute());
			file.setWritable(true);
			file.setReadable(true);
			file.setExecutable(true);
		}
	}

	public static void setPermissions3(String strPath) {

		final File file = new File(strPath);
		System.out.println("file path to change permissions : " + strPath);
		file.setReadable(true, false);
		file.setExecutable(true, false);
		file.setWritable(true, false);
	}

	public static void setPermission(File file) throws IOException {
		Set<PosixFilePermission> perms = new HashSet<>();
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);
		perms.add(PosixFilePermission.OWNER_EXECUTE);

		perms.add(PosixFilePermission.OTHERS_READ);
		perms.add(PosixFilePermission.OTHERS_WRITE);
		perms.add(PosixFilePermission.OTHERS_EXECUTE);

		perms.add(PosixFilePermission.GROUP_READ);
		perms.add(PosixFilePermission.GROUP_WRITE);
		perms.add(PosixFilePermission.GROUP_EXECUTE);

		Files.setPosixFilePermissions(file.toPath(), perms);
	}

	public static String generateRandomString() {
		String mychars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder stringBuildr = new StringBuilder();
		Random rnd = new Random();
		while (stringBuildr.length() < 3) { // length of the random string.
			int index = (int) (rnd.nextFloat() * mychars.length());
			stringBuildr.append(mychars.charAt(index));
		}

		String characters = stringBuildr.toString();
		String string = "0";
		StringBuilder stringBuilder = new StringBuilder(string);

		String randomString = stringBuilder.append(characters).toString();

		return randomString;

	}

	
	
	
	public static String readexcelvalue(int rownm, int colnm, String testDataSheetPath) throws IOException {

		XSSFSheet sht = connectParaBankTestDataSheet();
		XSSFRow rw = sht.getRow(rownm);
		XSSFCell cell = rw.getCell(colnm);

		cell.setCellType(Cell.CELL_TYPE_STRING);
		System.out.println(cell.getStringCellValue());

		return cell.getStringCellValue();

	}

	public static int return_Match_RowNum_By_ENVIRONMENT_value(String env,String testdataPath) throws IOException {

		int i = 0;

		XSSFSheet sht = connectExcelResultsSheet(testdataPath);

		for (i = 0; i < sht.getPhysicalNumberOfRows(); i++) { // check each row
			
			XSSFRow rw = sht.getRow(i);
			
			
			if (returncellvalue(rw, 0).equalsIgnoreCase(env)) {
				System.out.println("we found matching row number   " + i);
				break;
			}
		}

		return i;
	}
	
	public static XSSFSheet connectExcel_ReturnSheetIndex0(String path) throws IOException {

	
		File fl = new File(path);

		FileInputStream fis = new FileInputStream(fl);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sht = wb.getSheetAt(0);
		return sht;
	}
	

	public static XSSFSheet connectParaBankTestDataSheet() throws IOException {

		String paraBankTestDatPath = "/Users/sami/Desktop/Automation/sidrissi/Parabank/Properties/ParaBankTestData.xlsx";
		setPermissions2(paraBankTestDatPath);

		File fl = new File(paraBankTestDatPath);

		FileInputStream fis = new FileInputStream(fl);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sht = wb.getSheetAt(0);
		return sht;
	}

	public static String returncellvalue(XSSFRow rw, int colnum) {

		XSSFCell cll = rw.getCell(colnum);

		return cll.getStringCellValue();

	}

	public static String getBrowserDriverPathProperties(String key) throws IOException {
		File fl = new File(
				"/Users/sami/Desktop/GitTechTekSolution/ParaBank/src/test/resources/Properties/BrowserDriverPath.properties/browserVersionsFilePath.properties");
		FileInputStream fio = new FileInputStream(fl);
		Properties prop = new Properties();
		prop.load(fio);

		return prop.getProperty(key);

	}

	public static String getEndpointProperties(String key) throws IOException {
		File fl = new File("src/resources/java/Properties/Endpoints/Endpoints.properties");
		FileInputStream fio = new FileInputStream(fl);
		Properties prop = new Properties();
		prop.load(fio);

		return prop.getProperty(key);
	}

	public static String CreateRunResultFolder(String testerDirName) throws IOException {

		projpath = "/Users/sami/Desktop/Automation/" + testerDirName + "/Parabank/Runs/";

		/// Users/sami/Desktop/Automation/sidrissi/Parabank/Runs
		File dir = new File(projpath + getcurrentdateyyMMddHHmm() + GlobalObjects.Environment);

		System.out.println("prject path : " + projpath + getcurrentdateyyMMddHHmm() + GlobalObjects.Environment);
		dir.mkdir();

		return dir.getAbsolutePath();
	}

	public static String getcurrentdateyyMMddHHmm() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmm");
		Date date = new Date();
		return dateFormat.format(date).toString();

	}
	
	public static String getcurrentdateyyMMddHH() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmm");
		Date date = new Date();
		return dateFormat.format(date).toString();

	}

	public static String createFolder(String path) {

		File dir = new File(path);

		System.out.println("creating log folder");
		dir.mkdir();
		System.out.println("log folder created");

		return dir.getAbsolutePath();

	}

	public static String createLogFolder_for_Service(String path, String service) {

		File dir = new File(path);

		dir.mkdir();

		return dir.getAbsolutePath();

	}

	public static void create_SetupExcelResultsSheet(String runResultsFileName) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Result");
		String[] headers = new String[] { "Date", "Enviornment", "Application", "Account Status", "Service Name",
				"Test Name", "Status", "Endpoint", "Comment" };

		// System.out.println("Creating excel");

		Row r = sheet.createRow(0);
		for (int rn = 0; rn < headers.length; rn++) {

			r.createCell(rn).setCellValue(headers[rn]);
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(runResultsFileName);
			workbook.write(outputStream);
			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String readproperties(String key) throws IOException {
		File fl = new File("src/test/resources/Properties/Endpoints/Endpoints.properties");
		FileInputStream fio = new FileInputStream(fl);
		Properties prop = new Properties();
		prop.load(fio);

		return prop.getProperty(key);

	}

	public static HashMap<String, Integer> getStatusDataReport(String path) throws IOException {

		XSSFSheet sht = Lib.getExcelResultsSheet(path);

		HashMap<String, Integer> resultsMap = new HashMap<String, Integer>();

		System.out.println("total rows : " + sht.getLastRowNum());
		for (int i = 0; i <= sht.getLastRowNum(); i++) {
			String status = readexcelvalue_GetTotalcaseAmountResult(i, 5, path);
			// System.out.println("iteration : " +i +" | Status = "+status);
			if (status.equalsIgnoreCase("Passed")) {
				GlobalObjects.passed++;
			} else if (status.equalsIgnoreCase("Failed")) {
				GlobalObjects.failed++;
			}
		}

		resultsMap.put("Passed", GlobalObjects.passed);
		resultsMap.put("Failed", GlobalObjects.failed);

		for (Entry<String, Integer> e : resultsMap.entrySet()) {
			// System.out.println(e.getKey()+" : "+e.getValue());
		}
		return resultsMap;
	}

	public static String readexcelvalue_GetTotalcaseAmountResult(int rownm, int colnm, String path) throws IOException {
		XSSFSheet sht = getExcelResultsSheet(path);
		XSSFRow rw = sht.getRow(rownm);
		XSSFCell cell = rw.getCell(colnm);

		cell.setCellType(Cell.CELL_TYPE_STRING);

		return cell.getStringCellValue();

	}

	public static XSSFSheet getExcelResultsSheet(String path) throws IOException {

		File fl = new File(path);

		FileInputStream fis = new FileInputStream(fl);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sht = workbook.getSheetAt(0);
		return sht;
	}

	public static String getcurrentdate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void excelwrite(String Filename, Object[] ob)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream inp = new FileInputStream(Filename);

		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		int num = sheet.getLastRowNum() + 1;
		System.out.println("row number is   " + num);
		// Row row = sheet.createRow(++num);

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();

		data.put("1", ob);
		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();

		// System.out.println("keysets are :::: " + keyset);

		// int rownum = 0;
		for (String key : keyset) {
			// this creates a new row in the sheet
			System.out.println(key);
			Row row = sheet.createRow(num++);

			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				// this line creates a cell in the next column of that row
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}

		}
		try {
			// this Writes the workbook gfgcontribute
			FileOutputStream fileOut = new FileOutputStream(Filename);
			wb.write(fileOut);
			fileOut.close();
			System.out.println("write.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mergeExcels(String excelPath1, String excelPath2) {
		try {

			// excel files
			FileInputStream excellFile1 = new FileInputStream(new File(excelPath1));
			FileInputStream excellFile2 = new FileInputStream(new File(excelPath2));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook1 = new XSSFWorkbook(excellFile1);
			XSSFWorkbook workbook2 = new XSSFWorkbook(excellFile2);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet1 = workbook1.getSheetAt(0);
			XSSFSheet sheet2 = workbook2.getSheetAt(0);

			// add sheet2 to sheet1
			addSheet(sheet1, sheet2);
			excellFile1.close();

			// save merged file
			File mergedFile = new File(excelPath2);
			if (!mergedFile.exists()) {
				mergedFile.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(mergedFile);
			workbook1.write(out);
			out.close();
			System.out.println("Files were merged succussfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void addSheet(XSSFSheet mergedSheet, XSSFSheet sheet) {
		// map for cell styles
		Map<Integer, XSSFCellStyle> styleMap = new HashMap<Integer, XSSFCellStyle>();

		// This parameter is for appending sheet rows to mergedSheet in the end
		int len = mergedSheet.getLastRowNum();

		for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {

			XSSFRow row = sheet.getRow(j);
			XSSFRow mrow = mergedSheet.createRow(len + j + 1);

			for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {

				XSSFCell cell = row.getCell(k);
				XSSFCell mcell = mrow.createCell(k);

				if (cell.getSheet().getWorkbook() == mcell.getSheet().getWorkbook()) {
					mcell.setCellStyle(cell.getCellStyle());
				} else {
					int stHashCode = cell.getCellStyle().hashCode();
					XSSFCellStyle newCellStyle = styleMap.get(stHashCode);
					if (newCellStyle == null) {
						newCellStyle = mcell.getSheet().getWorkbook().createCellStyle();
						newCellStyle.cloneStyleFrom(cell.getCellStyle());
						styleMap.put(stHashCode, newCellStyle);
					}
					mcell.setCellStyle(newCellStyle);
				}

				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_FORMULA:
					mcell.setCellFormula(cell.getCellFormula());
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					mcell.setCellValue(cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					mcell.setCellValue(cell.getStringCellValue());
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					mcell.setCellType(HSSFCell.CELL_TYPE_BLANK);
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					mcell.setCellValue(cell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					mcell.setCellErrorValue(cell.getErrorCellValue());
					break;
				default:
					mcell.setCellValue(cell.getStringCellValue());
					break;
				}
			}
		}

	}

	public static String smokeResultFolderCreator_W_Drive(String pathToCheckThenCreateIfDoesntExist) {

		File f = new File(pathToCheckThenCreateIfDoesntExist);
		String absolutePath = null;
		if (f.exists() && f.isDirectory()) {
			System.out.println("The Smoke Test Folder with current data already Exists ");
		} else {
			System.out.println("Folder doesnt Exist, Creating folder now.");

			File dir = new File(pathToCheckThenCreateIfDoesntExist);
			dir.mkdir();
			// System.out.println("hahha "+dir.getCanonicalPath());
			absolutePath = dir.getAbsolutePath();

		}

		return absolutePath;

	}

	public static String createSmokeResultsLogFolderCreator(String pathToCheckThenCreateIfDoesntExist) {

		File f = new File(pathToCheckThenCreateIfDoesntExist);
		String absolutePath = null;

		if (f.exists() && f.isDirectory()) {
			System.out.println("The Smoke Test LOG folder already exists ");
		} else {
			System.out.println("Folder doesnt Exist, Creating folder now.");

			File dir = new File(pathToCheckThenCreateIfDoesntExist);
			dir.mkdir();
			// System.out.println("hahha "+dir.getCanonicalPath());
			absolutePath = dir.getAbsolutePath();

		}

		return absolutePath;

	}

	public static void copyResults_to_Shared_Drive(String srcLocation, String srcDestination) {

		System.out.println("Lib.Writepath : " + srcLocation);

		File srcDir = new File(srcLocation);

		File destDir = new File(srcDestination);

		try {
			FileUtils.copyDirectory(srcDir, destDir);
			System.out.println("successfully copied :" + srcLocation);
			System.out.println("to destination folder : " + srcDestination);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static HashMap<String, String> returnListOfEnv_HashMap() {

		HashMap<String, String> environmentMap = new HashMap<String, String>();

		environmentMap.put("DEV", "DEV");
		environmentMap.put("SIT", "SIT");
		environmentMap.put("PDEV", "PDEV");
		environmentMap.put("FT", "FT");
		environmentMap.put("STAGING", "STAGING");

		return environmentMap;
	}

	public static void createAndWriteHPQCResultColumns(String excelFilePath) {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("HPQC_RESULTS");

		String[] headers = new String[] { "Tester", "Exec Date", "Exec Time", "Environment", "Test Instance",
				"Status" };

		// System.out.println("Creating excel");

		Row r = sheet.createRow(0);

		for (int rn = 0; rn < headers.length; rn++) {

			r.createCell(rn).setCellValue(headers[rn]);
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);
			workbook.close();
			System.out.println("wrote columns to excel");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File Not found");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO exception");
		}

	}

	public static String readExcelValue(int rownm, int colnm, String excelResultPath) throws IOException {
		
		
		XSSFSheet sht = connectExcelResultsSheet(excelResultPath);
		XSSFRow rw = sht.getRow(rownm);
		XSSFCell cell = rw.getCell(colnm);

		cell.setCellType(Cell.CELL_TYPE_STRING);
		// System.out.println(cell.getStringCellValue());

		return cell.getStringCellValue();

	}

	public static XSSFSheet connectExcelResultsSheet(String excelResultPath) throws IOException {

		File fl = new File(excelResultPath);

		FileInputStream fis = new FileInputStream(fl);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sht = wb.getSheetAt(0);
		return sht;

	}

	public static HashMap<String, Integer> returnPassFailResult(String region, String servicename, String status,
			String excelResultPath) throws IOException {

		HashMap<String, Integer> passFailMap = new HashMap<String, Integer>();
		int Passed = 0;
		int Failed = 0;
		int i = 0;

		XSSFSheet sht = connectExcelResultsSheet(excelResultPath);
		for (i = 0; i < sht.getPhysicalNumberOfRows(); i++) { // check each row

			XSSFRow rw = sht.getRow(i);

			for (int j = i; j < sht.getPhysicalNumberOfRows(); j++)

				if (returncellvalue(rw, 2).equalsIgnoreCase(region)
						&& returncellvalue(rw, 1).equalsIgnoreCase(servicename)) {

					passFailMap.put("Passed", 0);
					passFailMap.put("Failed", 0);

					// get status and store in hash map increment value
					System.out.println("we found matching row number   " + i);
					break;
				}
		}

		return passFailMap;
	}

	public static Document parseXMLFile_returnXmlDocument(String xmlPath)
			throws ParserConfigurationException, SAXException, IOException {

		File xmlFile = new File(xmlPath);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(xmlFile);
		document.getDocumentElement().normalize();

		return document;
	}

	public static void writeSourceToXmlFile(String xmlSource, String xmlPathLocation)
			throws SAXException, ParserConfigurationException, IOException, TransformerException {

		// Parse the given input
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(xmlPathLocation);

		transformer.transform(source, result);
	}

	public static String CreateResultFolder() throws IOException {
		String runsFolder = projpath + "DailyExecution\\Runs\\";
		File dir = new File(runsFolder + getcurrentdateyyMMddHHmm());
		dir.mkdir();

		return dir.getAbsolutePath();
	}

	public static String CreateRUN_ResultFolder() throws IOException {

		String SSPAG_Path = projpath + "DailyExecution\\Runs\\";

		File dir = new File(SSPAG_Path + getcurrentdateyyMMddHHmm());

		dir.mkdir();
		// System.out.println("hahha "+dir.getCanonicalPath());
		return dir.getAbsolutePath();

	}

	public static void logWriter1(String application, String serviceName, String testname, JSONObject requeyBody,
			Map<String, String> headerinfo, String endpoint, Response response, String resultsLogPath)
			throws FileNotFoundException, IOException {


		String currentDate = getcurrentdatenoformat();
		String testname_textFile_Path = resultsLogPath + "/" + application + "/" + serviceName+"/"+testname + "_" + currentDate + ".txt";
		
		System.out.println("******test file path = "+ testname_textFile_Path);
		
		File file = new File(testname_textFile_Path);
		
		System.out.println(file.toString());
		file.createNewFile(); // create the file
		
		System.out.println("file trying to write to is : " + testname_textFile_Path);
		
		PrintWriter out = new PrintWriter(testname_textFile_Path);
		
		out.println("Request:");
		out.println("");
		out.println("Endpoint is :: " + endpoint);
		out.println("");
		out.println("Headers: ");
		out.println(headerinfo);
		out.println("");
		out.println("Response body : ");
		out.println(requeyBody);
		out.println("");

		out.println("");
		out.println("************************************************************************************");
		out.println("Response:");
		out.println("");
		out.println("Response Headers : ");
		out.println("HTTP/1.1  " + response.statusCode() + " OK");
		out.println(response.getHeaders());
		out.println("");
		out.println("Response body : ");
		out.println(response.body().asString());
		
		out.close();
	}
	
	
	public static void logWriter_For_SOAP(String serviceName, String testname,
			String requestBody, Map<String, String> headerinfo,
			String endpoint, String responseBody, HttpResponse responseClient,
			String resultsLogPath) throws FileNotFoundException, IOException {

		// create txt file..
		String currentDate = getcurrentdatenoformat();
		
		String testnamePath = resultsLogPath + "/" + serviceName+"/"+ testname+"_"+currentDate+".txt";
		
		System.out.println("Soap writer is trying to write to : "+ testnamePath);
		File file = new File(testnamePath);
		file.createNewFile(); // create the file
		System.out.println("file created inside " + resultsLogPath + "/"
				+ serviceName);

		PrintWriter out = new PrintWriter(resultsLogPath + "/" + serviceName
				+ "/"+testname+"_"+currentDate+".txt");
		out.println("Request:");
		out.println("");
		out.println("Endpoint is :: " + endpoint);
		out.println("");
		out.println("Headers:");
		out.println(headerinfo);
		out.println("");
		out.println("Request body: ");

		out.println(requestBody);
		out.println("");

		out.println("");
		out.println("************************************************************************************");
		out.println("Response:");
		out.println("");
		out.println("Response status code:");
		out.println("HTTP/1.1  "
				+ responseClient.getStatusLine().getStatusCode() + " OK");
		out.println("Headers:");
		Header headersArray[] = responseClient.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		for (Entry<String, String> h : allHeaders.entrySet()) {
			String key = h.getKey();
			String val = h.getValue();
			out.println(key + " : " + val);
		}
		out.println("Response body:");
		out.println(responseBody);
		out.close();
	}


	public static void checkStatusIs200(Response res) {
		Assert.assertEquals(200, res.getStatusCode(), "Status Check Failed!");

	}

	public static String generateRandom_SXM_TestUser() {
		String mychars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder stringBuildr = new StringBuilder();
		Random rnd = new Random();
		while (stringBuildr.length() < 8) { // length of the random string.
			int index = (int) (rnd.nextFloat() * mychars.length());
			stringBuildr.append(mychars.charAt(index));
		}

		String characters = stringBuildr.toString();
		String sxmTestUser = "Test";
		StringBuilder stringBuilder = new StringBuilder(sxmTestUser);

		String randomTestUser = stringBuilder.append(characters).toString();

		return randomTestUser;

	}

	public static void cleanupexcel(String FILE_NAME)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream inp = new FileInputStream(FILE_NAME);
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		int num = sheet.getLastRowNum();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			deleteRow(sheet, row);
		}

		try {
			// this Writes the workbook gfgcontribute
			FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
			wb.write(fileOut);
			fileOut.close();
			System.out.println("clean up is done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static XSSFSheet connectexcel() throws IOException {

		File fl = new File("file name");
		FileInputStream fis = new FileInputStream(fl);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sht = wb.getSheetAt(0);
		return sht;
	}

	public String[] stringDelimiter(String strElements) {

		int element = countOccurance(strElements, "^");
		String[] strArrElement = new String[element + 1];

		Scanner scan = new Scanner(strElements);// /Female/18
		// initialize the string delimiter
		scan.useDelimiter("\\^");

		// Printing the tokenized Strings
		int tempValue = 0;
		while (scan.hasNext()) {
			strArrElement[tempValue] = scan.next();
			tempValue++;
		}
		// closing the scanner stream
		scan.close();

		return strArrElement;
	}

	public static int returnrowIndex(String Env, String Token, String appl, String Servicename) throws IOException {
		int i = 0;
		XSSFSheet sht = connectexcel();
		for (i = 0; i < sht.getPhysicalNumberOfRows(); i++) {
			XSSFRow rw = sht.getRow(i);
			if (returncellvalue(rw, 0).equalsIgnoreCase(Env) && returncellvalue(rw, 1).equalsIgnoreCase(Token)
					&& returncellvalue(rw, 2).equalsIgnoreCase(appl)
					&& returncellvalue(rw, 3).equalsIgnoreCase(Servicename)) {
				System.out.println("we found matching row number   " + i);
				break;
			}
		}

		return i;
	}

	public static String readexcelvalue(int rownm, int colnm) throws IOException {
		XSSFSheet sht = connectexcel();
		XSSFRow rw = sht.getRow(rownm);
		XSSFCell cell = rw.getCell(colnm);

		// System.out.println(cell.getStringCellValue());
		cell.setCellType(Cell.CELL_TYPE_STRING);

		return cell.getStringCellValue();

	}

	public static int countOccurance(String inputString, String key) {
		int index = 0;
		int fromIndex = 0;
		int result = 0;

		if (inputString == null || key == null) {
			return 0;
		}

		while ((index = inputString.indexOf(key, fromIndex)) != -1) {
			result++;
			fromIndex = index + key.length();
		}
		return result;
	}

	public static XSSFSheet createNewExcelWithNewSheet(String filePath, String sheetname) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetname);

		try {
			FileOutputStream outputStream = new FileOutputStream(filePath);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sheet;
	}

	public static void deleteRow(Sheet sheet, Row row) {
		int lastRowNum = sheet.getLastRowNum();
		int rowIndex = row.getRowNum();
		if (rowIndex >= 0 && rowIndex < lastRowNum) {
			sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
		}
		if (rowIndex == lastRowNum) {
			Row removingRow = sheet.getRow(rowIndex);
			if (removingRow != null) {
				sheet.removeRow(removingRow);
				System.out.println("Deleting.... ");
			}
		}
	}

	public static void writeExcel1(String date, String Env, String calltype, String app, String sername, String status,
			String token, String accstatus, String testname, String expRcode, String actRcode, String expScode,
			String actScode, String statusMsg, String endpoint, String RC, String release) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Result");
		Object[][] datatypes = {
				{ "Date", "Enviornment", "Call Type/Region", "Application", "Service Name", "Status", "Token",
						"Account Status", "Test Name", "Exp RCode", "Act RCode", "Exp SCode", "Act SCode",
						"Status Message", "Endpoint", "RC ticket", "Release Number" },
				{ date, Env, calltype, app, sername, status, token, accstatus, testname, expRcode, actRcode, expScode,
						actScode, statusMsg, endpoint, RC, release } };

		int rowNum = 0;
		System.out.println("Creating excel");

		for (Object[] datatype : datatypes) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (Object field : datatype) {
				Cell cell = row.createCell(colNum++);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}
		}

		try {
			FileOutputStream outputStream = new FileOutputStream("");
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}

	public static void writeExcel2(String File_name) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Result");
		String[] headers = new String[] { "Date", "Enviornment", "Region", "Application", "Service Name", "Status",
				"Token", "Account Status", "Test Name", "Exp RCode", "Act RCode", "Exp SCode", "Act SCode",
				"Status Message", "Endpoint", "RC ticket", "Release Number", "Comment" };

		// System.out.println("Creating excel");

		Row r = sheet.createRow(0);
		for (int rn = 0; rn < headers.length; rn++) {

			r.createCell(rn).setCellValue(headers[rn]);
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(File_name);
			workbook.write(outputStream);
			workbook.close();
			System.out.println("wrote columns to excel");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File Not found");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO exception");
		}

	}

	public void connectAccess() throws SQLException, ClassNotFoundException {
		// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		// String database =
		// "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=result.mdb;";

		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://d:/Result88.mdb;memory=false");
		// Connection conn = DriverManager.getConnection(database, "", "");
		Statement s = conn.createStatement();

		// create a table
		String tableName = "Result";
		String createTable = "SELECT * FROM Table1";

		String a = "INSERT INTO Table1( ID, Environment ) VALUES ('24', 'K2 SIT')";
		ResultSet rs = s.executeQuery(createTable);
		while (rs.next()) {
			System.out.println(rs.getString(2));
		}

		s.executeUpdate(a);
	}

	public static String getcurrentdatenoformat() {

		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void createExcelResultAndCreateColumnsForEachEnvironmentFolder(List<String> envList, String testerDirName,
			String appl) {

		for (int i = 0; i <= envList.size(); i++) {

			projpath = "D:\\Automation\\" + testerDirName + "\\K2Regression\\" + appl + "\\" + envList.get(i)
					+ "\\Runs\\";

		}

	}

	/**
	 * Rahul Sheety code refer to udemy class on getting test data based on testname
	 */
	public static ArrayList<String> getDataByTestCaseName(String testcaseName, String path) throws IOException {
		// fileInputStream argument
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Identify Testcases coloum by scanning the entire 1st row

				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						coloumn = k;

					}

					k++;
				}
				System.out.println(coloumn);

				//// once coloumn is identified then scan entire testcase coloum to identify
				//// purcjhase testcase row
				while (rows.hasNext()) {

					Row r = rows.next();

					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName)) {

						//// after you grab purchase testcase row = pull all the data of that row and
						//// feed into test

						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							int cellTypeString1 = c.CELL_TYPE_STRING;
							if (cellTypeString1 == 1) {

								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}

				}

			}
		}
		return a;

	}

	
	
	
	
	public static void log(String strLine, String ServiceName, String logFileName) throws IOException {

	      
	      File file = new File(GlobalObjects.logpath+"/"+ServiceName+"/"+logFileName+".txt");   
//	      System.out.println(file.getAbsolutePath().toString());
	      FileWriter fileWriter = new FileWriter(file,true);
	      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	      
	      bufferedWriter.write(strLine+"\n");
	      bufferedWriter.close();
	      fileWriter.close();
	
	}
	
	
	

}
