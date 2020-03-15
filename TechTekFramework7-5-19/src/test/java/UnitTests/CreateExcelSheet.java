package UnitTests;

import java.io.IOException;

import org.testng.annotations.Test;

import Lib.Lib;

public class CreateExcelSheet {

	@Test()
	public void createExcelWithColumns() throws IOException {
		
		String timeStamp = Lib.getcurrentdateyyMMddHHmm();
		String excelPath = "/Users/sami/Desktop/eclipse-workspace2k19/TechTekFramework7-5-19/AutomationResults/Regression/";
		Lib.create_SetupExcelResultsSheet(excelPath+timeStamp+".xlsx");	
		
	}
	
}
