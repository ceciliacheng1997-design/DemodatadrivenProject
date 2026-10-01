package rough;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.microsoft.playwright.Browser;

import base.BaseTest;

public class LoginTest extends BaseTest {
	@Test
	public void doLogin() {
		Browser browser=getBrowser("chrome");
		navigate(browser,"https://www.google.com.hk");
		fillin("searchbox","hello world");
	}
	@Test
	public void logingmail() {
		Browser browser=getBrowser("firefox");
		navigate(browser,"https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Fservice%3Dmail%26flowName%3DGlifWebSignIn%26flowEntry%3DAccountChooser%26ec%3Dasw-gmail-globalnav-signin&dsh=S271804901%3A1784993420726269&uj=gafb-gmail_asw-def-zh-HK&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=Ac50bxuhO00a5ekwBNLpy9ix31sVUEUskT0HEk7M1rN0FvbAoUtKK1tD431wK_0I1rrXFxZ8TX5eQw");
		fillin("loginid","trainer@way2automation.com");
	}



}
