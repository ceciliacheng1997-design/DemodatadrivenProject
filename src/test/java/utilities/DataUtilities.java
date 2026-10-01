package utilities;

import java.util.Hashtable;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.apache.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle.WaitForSelectorOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import extentlisteners.ExtentListeners;

import com.aventstack.extentreports.model.Log;

public class DataUtilities {
	 private static final Logger log = Logger.getLogger(DataUtilities.class);

	    static {
	        // 如果 root logger 还没有任何 appender，说明 log4j 还没初始化
	        if (!Logger.getRootLogger().getAllAppenders().hasMoreElements()) {
	            PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
	        }
	    }

	public static Object[][] getDataFromExcel(String excelname, String sheetname, String targetDatasourcefile) {
		ExcelReader excelReader =new ExcelReader(excelname);
		int totalrowcount=excelReader.getRowCount(sheetname);
		System.out.println("Row count: " + totalrowcount);
		String targetDatasource=targetDatasourcefile;
		int targetDatastartRow=0;
		int targetDataendRow=0;
		for (int i=1; i<=totalrowcount; i++) {
			if (excelReader.getCellData(sheetname, 0, i).equals(targetDatasource)) {
				targetDatastartRow=i+2;
				break;
			}
		}
		System.out.println("Target data start row: " + targetDatastartRow);
		for (int j=targetDatastartRow; j<=totalrowcount+1; j++) {
			if (excelReader.getCellData(sheetname, 0, j).equals("")) {
				targetDataendRow=j-1;
				break;
		}
		}
		//System.out.println("Target data end row: " + targetDataendRow);
		int targetDatatotalRow=targetDataendRow-targetDatastartRow+1;
		//System.out.println("Target data totalrow " + targetDatatotalRow);
		
		int totalColcount=0;
		while(!excelReader.getCellData(sheetname, totalColcount, targetDatastartRow-1).equals("")) {
			totalColcount++;
			
		}
		//System.out.println("Target data totalcol " + totalColcount);
		Object[][] data=new Object[targetDatatotalRow][1];
		for (int i=targetDatastartRow;i<=targetDataendRow;i++) {
			Hashtable<String,String> map=new Hashtable<String,String>();
			for (int j=0;j<totalColcount;j++) {
				String key=excelReader.getCellData(sheetname,j,targetDatastartRow-1);
				String value=excelReader.getCellData(sheetname,j,i);
				map.put(key, value);	
			}
			data[i-targetDatastartRow][0]=map;
		}
		return data;
	}
	
	public static Boolean ischeckRunnable(ExcelReader excelReader, String suitename,String sheetName1, String testCaseName,String dataRunmode) {
		if (isSuiteRunnable(suitename) && isTestCaseRunnable(excelReader, sheetName1, testCaseName) && isDataRunnable(dataRunmode)) {
			return true;
		}
	log.warn("Suite name, Test case name or Data runmode is not runnable");
	throw new SkipException("Suite name, Test case name or Data runmode is not runnable");
	}
	
	public static Boolean isSuiteRunnable(String suiteName) {
		ExcelReader excelReader = new ExcelReader(Constant.SUITES_FILE);
		int totalrowCount = excelReader.getRowCount(Constant.SUITES_SHEETNAME);
		for (int i=2; i<=totalrowCount;i++) {
			String suite=excelReader.getCellData(Constant.SUITES_SHEETNAME, 0,i);
			if (suite.equalsIgnoreCase(suiteName)) {
				String runmode=excelReader.getCellData(Constant.SUITES_SHEETNAME, 1,i);
				if (runmode.equalsIgnoreCase("Y")) {
					System.out.println("Suite name: " + suiteName + " is runnable");
					return true;
				} else {
					System.out.println("Suite name: " + suiteName + " is not runnable");
					return false;
				}
			}
		
		}
		log.error("Suite name not found in the suites sheet: " + suiteName);
		throw new RuntimeException("Suite name not found in the suites sheet: " + suiteName);	
	}
	public static Boolean isTestCaseRunnable(ExcelReader excelReader, String sheetName, String testCaseName) {
		int totalRowCount = excelReader.getRowCount(sheetName);
		for (int i=2;i<=totalRowCount;i++) {
			String testcase=excelReader.getCellData(sheetName, 0, i);
			if (testcase.equalsIgnoreCase(testCaseName)) {
				String runmode=excelReader.getCellData(sheetName, 1, i);
				if (runmode.equalsIgnoreCase("Y")) {
					System.out.println("Test case name: " + testCaseName + " is runnable");
					return true;
				} else {
					System.out.println("Test case name: " + testCaseName + " is not runnable");
					return false;
				}
			}
		
		}
		log.error("Suite name not found in the suites sheet: " + testCaseName);	
		throw new RuntimeException("Suite name not found in the suites sheet: " + testCaseName);	
	}
	
	public static Boolean isDataRunnable(String runmode) { 
		if (runmode.equalsIgnoreCase("Y")) {
			return true;
		} else if (runmode.equalsIgnoreCase("N")) {
			System.out.println("Data is not runnable");
			return false;
		}else {
			log.error("Runmode value is not valid: " + runmode);
			throw new RuntimeException("Runmode value is not valid: " + runmode);
		}
}
}
