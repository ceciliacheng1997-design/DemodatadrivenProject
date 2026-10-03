package testcase.Customer;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import base.BaseTest;
import utilities.Constant;
import utilities.DataProviderutilities;
import utilities.DataUtilities;
import utilities.ExcelReader;

public class Customeraccountnew extends BaseTest{
	@Test(dataProviderClass=DataProviderutilities.class,dataProvider="SuitesCustomer")
	public void DespoiteTest(Hashtable<String, String> data) throws InterruptedException {
		ExcelReader excel=new ExcelReader(Constant.SUITES_CUSTOMER_FILE);
		DataUtilities.ischeckRunnable(excel, "CustomerSuites", Constant.SUITES_CUSTOMER_SHEETNAME_TESTCASE, "DespoiteTest", data.get("runmode"));
		Browser browser=getBrowser(data.get("browser"));
		navigate(browser,"https://www.way2automation.com/angularjs-protractor/banking/#/login");
		clickkey("customer_css");
		selectkey("customer_dropdown_css",data.get("name"));
		clickkey("customer_login_css");

		clickkey("customer_deposit_css");
		fillin("customer_deposit_amount_xpath",data.get("add"));
		clickkey("customer_deposit_button_css");
		
	}

}
