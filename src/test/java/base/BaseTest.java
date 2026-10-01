package base;

import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.apache.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle.WaitForSelectorOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

import extentlisteners.ExtentListeners;

public class BaseTest {
	private Playwright playwright;
	public Browser browser;
	public Page page;
	private static Properties OR= new Properties();
	private static FileInputStream fis;
	private static Logger log;
	
	private static ThreadLocal<Playwright> pw= new ThreadLocal<>();
	private static ThreadLocal<Browser> br= new ThreadLocal<>();
	private static ThreadLocal<Page> pg= new ThreadLocal<>();
	
	
	public static Playwright getPlaywright() {
		return pw.get();
	}
	
	public static Browser getBrowser() {
		return br.get();
	}
	
	public static Page getPage() {
		return pg.get();
	}
	
	@BeforeSuite(alwaysRun=true)
	public void setup() throws IOException {
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
        try (FileInputStream fis = new FileInputStream("./src/test/resources/properties/OR.properties")) {
            OR.load(fis);
        }
		log=Logger.getLogger(BaseTest.class);
		log.info("Corresponding information are loaded");
		
	}
	
	
	public Browser getBrowser(String browserName) {
		playwright = Playwright.create();
		pw.set(playwright);
		switch (browserName) {
		case "chrome":
			log.info("lauching "+browserName);
			return getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
		case "headless":
			log.info("lauching "+browserName);
			return getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(true));
		case "webkit":
			log.info("lauching "+browserName);
			return getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(true));
		case "firefox":
			log.info("lauching "+browserName);
			return getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false));
		default:
			log.error("Invalid BrowserName"+browserName);
			throw new IllegalArgumentException();
			
		}
		}
		
	
	public void navigate(Browser browser,String url) {
		this.browser=browser;
		br.set(browser);
		page=getBrowser().newPage();
		pg.set(page);
		getPage().navigate(url);
		log.info("launching the page");
	}
	
	public void clickkey(String locatorKey) {
		try {
			getPage().locator(OR.getProperty(locatorKey)).click();
			log.info("Clicking the locator"+locatorKey);
			ExtentListeners.getExtent().info("Clicking the locator"+locatorKey);
		}catch (Throwable t) {
			log.error("Clicking the locator "+locatorKey+"have error:"+t.getMessage());
			Assert.fail(t.getMessage());
			AssertJUnit.fail(t.getMessage());
		}
	}
	
	public void fillin(String locatorKey,String value) {
		try {
			getPage().locator(OR.getProperty(locatorKey)).fill(value);
			log.info("Filling the locator"+locatorKey+"with value"+value);
			ExtentListeners.getExtent().info("Filling the locator"+locatorKey+"with value"+value);
		}catch (Throwable t) {
			log.error("Fill in locator "+locatorKey+" with value"+value+" have error:"+t.getMessage());
			Assert.fail(t.getMessage());
			AssertJUnit.fail(t.getMessage());
		}
	}
	
	public void selectkey(String locatorKey, String value) {
		try {
			getPage().selectOption(OR.getProperty(locatorKey), new SelectOption().setLabel(value));
			log.info("Selecting the locator "+locatorKey+" with value "+value);
			ExtentListeners.getExtent().info("Selecting the locator "+locatorKey+" with value "+value);
		}catch (Throwable t) {
			log.error("Selecting the locator "+locatorKey+" with value "+value+" have error:"+t.getMessage());
			Assert.fail(t.getMessage());
			AssertJUnit.fail(t.getMessage());
		}
	}
	
	
	
	public boolean isElementPresent(String locatorKey) {
		try {
			getPage().waitForSelector(OR.getProperty(locatorKey));
			log.info("Finding the locator"+locatorKey);
			ExtentListeners.getExtent().info("Clicking the locator"+locatorKey);
			return true;
		}catch (Throwable t) {
			log.error("Clicking the locator "+locatorKey+"have error:"+t.getMessage());
			Assert.fail(t.getMessage());
			AssertJUnit.fail(t.getMessage());
			return false;
		}
	}
	@AfterMethod
	public void quit() {
		if (getPage()!=null){
		getPage().close();
		getBrowser().close();
		getPlaywright().close();
		}
		pg.remove();
		br.remove();
		pw.remove();
	}
}
