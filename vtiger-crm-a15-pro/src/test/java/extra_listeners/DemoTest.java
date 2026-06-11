package extra_listeners;

import org.junit.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import baseutility.BaseClass;

@Listeners(listeners_utility.List_Imp.class)
public class DemoTest extends BaseClass {

	public ExtentReports report;

	@BeforeSuite
	public void repConfig() {
//		String time = JavaUtility.genCurrentTime();
////		report config
//		ExtentSparkReporter spark = new ExtentSparkReporter("./advance_reports/" + time + ".html");
//		spark.config().setDocumentTitle("fb_dummy");
//		spark.config().setReportName("dummy_01");
//		spark.config().setTheme(Theme.STANDARD);
//
//		report = new ExtentReports();
//		report.attachReporter(spark);
//		report.setSystemInfo("key1", "value1");
//		report.setSystemInfo("key2", "value2");
//		report.setSystemInfo("key3", "value3");
		System.out.println("this is before suite");
	}

	@Test
	public void createCity() {
		System.out.println("city created");
	}

	@Test
	public void modifyCity() {
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "modifyCity")
	public void deleteCity() {
		System.out.println("new city deleted");
	}

	@AfterSuite
	public void repBackup() {
//		report.flush();
		System.out.println("this is after suite");
	}

}
