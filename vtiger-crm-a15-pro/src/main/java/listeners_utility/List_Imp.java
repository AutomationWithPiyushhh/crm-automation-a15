package listeners_utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseutility.BaseClass;
import generic_utility.JavaUtility;

public class List_Imp implements ITestListener, ISuiteListener {

	public ExtentReports report;
	public ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("this is on start");
		String time = JavaUtility.genCurrentTime();
//		report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./advance_reports/" + time + ".html");
		spark.config().setDocumentTitle("fb_dummy");
		spark.config().setReportName("dummy_01");
		spark.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("key1", "value1");
		report.setSystemInfo("key2", "value2");
		report.setSystemInfo("key3", "value3");
	}
	
	
	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) { 
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName + " got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.FAIL, methodName + " Failed....");

		
		TakesScreenshot tks = (TakesScreenshot) BaseClass.sdriver;
		String ss = tks.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(ss, methodName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName + "skip...");
	}
	
	@Override
	public void onFinish(ISuite suite) {
		report.flush();
		System.out.println("this is on finish");
	}
}
