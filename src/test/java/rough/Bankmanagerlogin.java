package rough;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;

import base.BaseTest;

public class Bankmanagerlogin extends BaseTest {
	@Test
	public void bankmangerlogin() {
		Browser browser=getBrowser("chrome");
		navigate(browser,"https://www.way2automation.com/angularjs-protractor/banking/#/login");
		clickkey("bankmanager_css");
		Assert.assertTrue(isElementPresent("bankmanager_add_customer_css"),"Add customer button is not present");
		
	}


}
