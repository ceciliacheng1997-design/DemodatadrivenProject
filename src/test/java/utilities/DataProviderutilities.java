package utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviderutilities {
	@DataProvider(name="SuitesBank")
	public Object[][] getData(Method m) {
		String testname=m.getName();
		return DataUtilities.getDataFromExcel(Constant.SUITES_BANK_FILE,Constant.SUITES_BANK_SHEETNAME, testname);


}
	@DataProvider(name="SuitesCustomer")
	public Object[][] getDatacustomer(Method m) {
		String testname=m.getName();
		return DataUtilities.getDataFromExcel(Constant.SUITES_CUSTOMER_FILE,Constant.SUITES_CUSTOMER_SHEETNAME, testname);


}

}
