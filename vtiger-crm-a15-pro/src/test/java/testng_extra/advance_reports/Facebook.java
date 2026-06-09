package testng_extra.advance_reports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Facebook {
	@Test
	public void login() throws InterruptedException {

//		report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./advance_reports/rep1.html");
		spark.config().setDocumentTitle("fb_dummy");
		spark.config().setReportName("dummy_01");
		spark.config().setTheme(Theme.STANDARD);

		ExtentReports report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("key1", "value1");
		report.setSystemInfo("key2", "value2");
		report.setSystemInfo("key3", "value3");
		Thread.sleep(3000);

		ExtentTest test = report.createTest("login");
		test.log(Status.PASS, "Passed...");
		test.log(Status.FAIL, "Failed...");
		test.log(Status.SKIP, "Skipped...");
		test.log(Status.WARNING, "Warning...");
		test.log(Status.INFO, "Information...");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		Thread.sleep(3000);
		driver.quit();
//		report backup
		report.flush();
	}
}
