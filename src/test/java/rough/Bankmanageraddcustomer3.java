package rough;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;

import base.BaseTest;
import utilities.Constant;
import utilities.DataUtilities;
import utilities.ExcelReader;

public class Bankmanageraddcustomer3 {
	@Test(dataProvider="getdata")
	public void bankaddcustomer(Hashtable<String, String> data) throws InterruptedException {
		System.out.println(data.get("runmode")+data.get("username")+data.get("firstname")+data.get("postcode"));
		
	}
	@DataProvider(name="getdata")
	public Object[][] getData() {
		return DataUtilities.getDataFromExcel("./src/test/java/utilities/Excelmanager.xlsx",Constant.EXCEL_SHEET, "AddManagerTest");


}
}
