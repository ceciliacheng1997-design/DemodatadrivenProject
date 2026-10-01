package testcase.Bankmanager;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;

import base.BaseTest;
import utilities.Constant;
import utilities.DataProviderutilities;
import utilities.DataUtilities;
import utilities.ExcelReader;

public class Bankmanageraddcustomer4 extends BaseTest {
	@Test(dataProviderClass=DataProviderutilities.class,dataProvider="SuitesBank")
	public void AddManagerTest(Hashtable<String, String> data) throws InterruptedException {
		ExcelReader excel=new ExcelReader(Constant.SUITES_BANK_FILE);
		System.out.println(DataUtilities.ischeckRunnable(excel, "BankManagerSuites", Constant.SUITES_BANK_SHEETNAME_TESTCASE, "AddManagerTest", data.get("runmode")));
		Browser browser=getBrowser(data.get("browser"));
		navigate(browser,"https://www.way2automation.com/angularjs-protractor/banking/#/login");
		clickkey("bankmanager_css");
		clickkey("bankmanager_add_customer_css");
		fillin("bankcustomer_firstname_xpath",data.get("firstname"));
		fillin("bankcustomer_lastname_xpath",data.get("lastname"));
		fillin("bankcustomer_ps_xpath","postcode");
		clickkey("bankcustomer_button_css");
		
	}
}
