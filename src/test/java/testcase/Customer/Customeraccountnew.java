package testcase.Customer;

import java.util.Hashtable;

import org.testng.annotations.Test;

import utilities.DataProviderutilities;

public class Customeraccountnew {
	@Test(dataProviderClass=DataProviderutilities.class,dataProvider="SuitesCustomer")
	public void CustomerAccountdetail(Hashtable<String, String> data) throws InterruptedException {
		System.out.println(data.get("runmode")+data.get("firstname")+data.get("lastname")+data.get("balance"));
		
	}

}
