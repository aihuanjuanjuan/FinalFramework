package UnitTests;

import java.io.File;

import org.testng.annotations.Test;

import Lib.Lib;

public class GenerateTimeStampFolder {
	

	
	@Test()
	public void generateTimeStampFolder() {
		
		String path = "/Users/sami/Desktop/eclipse-workspace2k19/TechTekFramework7-5-19/AutomationResults/Regression/";
		String currentDate = Lib.getcurrentdateyyMMddHHmm();
		String logFolderPath = createLogFolder(path, currentDate);

		System.out.println("Log Folder path : "+logFolderPath);
		
		
	}
	
	
	public static String createLogFolder(String path,String newFolderName) {

		File dir = new File(path+newFolderName);

		dir.mkdir();

		return dir.getAbsolutePath();

	}

}
