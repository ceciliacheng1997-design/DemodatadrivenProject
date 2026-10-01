package rough;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;

import base.BaseTest;
import utilities.ExcelReader;

public class Bankmanageraddcustomer extends BaseTest {
	@Test(dataProvider="getdata")
	public void bankaddcustomer(String firstname, String lastname, String postcode) throws InterruptedException {
		Browser browser=getBrowser("chrome");
		navigate(browser,"https://www.way2automation.com/angularjs-protractor/banking/#/login");
		clickkey("bankmanager_css");
		clickkey("bankmanager_add_customer_css");
		fillin("bankcustomer_firstname_xpath",firstname);
		fillin("bankcustomer_lastname_xpath",lastname);
		fillin("bankcustomer_ps_xpath",postcode);
		Thread.sleep(2000);
		clickkey("bankcustomer_button_css");
		
	}
	@DataProvider(name="getdata")
	public Object[][] getData() {
		ExcelReader excelReader = new ExcelReader("./src/test/java/utilities/exceldata.xlsx");
		String sheetname = "Sheet1";
		int rowcount=excelReader.getRowCount(sheetname)-1;
		int colcount=excelReader.getColumnCount(sheetname);
		System.out.println("Row count: " + rowcount);
		System.out.println("Column count: " + colcount);
		Object[][] data = new Object[rowcount][colcount];
		for (int i = 2; i <= rowcount+1; i++) {
			for (int j = 0; j < colcount; j++) {
				data[i - 2][j] = excelReader.getCellData(sheetname, j, i);
			}
		}
		return data;
	}


}
