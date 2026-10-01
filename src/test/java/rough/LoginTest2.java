package rough;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;

import base.BaseTest;

public class LoginTest2 extends BaseTest {
	@Test
	public void doLogin3() {
		Browser browser=getBrowser("chrome");
		navigate(browser,"https://www.yahoo.com.hk");
		//fillin("searchbox","hello world");
	}
	@Test
	public void logingmail3() {
		Browser browser=getBrowser("chrome");
		navigate(browser,"https://www.baidu.com");
		//fillin("loginid","trainer@way2automation.com");
	}



}
