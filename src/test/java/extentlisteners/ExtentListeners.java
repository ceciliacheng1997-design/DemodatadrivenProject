package extentlisteners;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.BaseTest;


public class ExtentListeners implements ITestListener, ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager.createInstance(".\\reports\\" + fileName);

	public static ExtentTest test;

	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();
	
	private static Properties OR= new Properties();
	private static FileInputStream fis;
	private static Logger log;
	
	public static String getOR(String key) {
        String value = OR.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Locator key not found in OR.properties: " + key);
        }
        return value;
	}
	
	public void onStart(ISuite suite) {
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
        try (FileInputStream fis = new FileInputStream("./src/test/resources/properties/OR.properties")) {
            OR.load(fis);
        }
		// TODO Auto-generated method stub
 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log=Logger.getLogger(ExtentListeners.class);

	}
	
	
	
	public static ExtentTest getExtent() {
		
		return testReport.get();
	}

	public void onTestStart(ITestResult result) {

		test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		
		testReport.set(test);
	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		getExtent().pass(m);

	}

	public void onTestFailure(ITestResult result) {

		/// test.fail(result.getThrowable().getMessage());
		try {
			ExtentManager.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " FAILED" + "</b>";

		getExtent().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>",
				MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.fileName).build());

		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		getExtent().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		getExtent().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}



	String messageBody;


	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}
	
/*	
	public void onFinish(ISuite suite) {

		
		MonitoringMail mail = new MonitoringMail();
		
		
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/PlaywrightProject/Extent_20Reports/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	*/

}
