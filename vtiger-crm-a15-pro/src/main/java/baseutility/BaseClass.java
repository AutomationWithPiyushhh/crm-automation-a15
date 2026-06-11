package baseutility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;
import object_repository.LoginPage;

public class BaseClass {
	
	public WebDriver driver;
	public static WebDriver sdriver;
//	public ExtentReports report;
//	@BeforeSuite
//	public void repConfig() {
////		report config
//		ExtentSparkReporter spark = new ExtentSparkReporter("./advance_reports/rep2.html");
//		spark.config().setDocumentTitle("vtiger_Crm");
//		spark.config().setReportName("first report");
//		spark.config().setTheme(Theme.STANDARD);
//
//		report  = new ExtentReports();
//		report.attachReporter(spark);
//		report.setSystemInfo("key1", "value1");
//		report.setSystemInfo("key2", "value2");
//		report.setSystemInfo("key3", "value3");
//	}

	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException, ParseException {
		// ==============================
		// Browser Setup
		// ==============================

//		get data from json file
		FileUtility fUtil = new FileUtility();
		String BROWSER = fUtil.getDataFromJsonFile("bro");

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("safari")) {
			driver = new SafariDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		sdriver = driver;
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@BeforeMethod
	public void login() throws FileNotFoundException, IOException, ParseException {
		// ==============================
		// Login to CRM Application
		// ==============================
		FileUtility fUtil = new FileUtility();
		String URL = fUtil.getDataFromJsonFile("url");
		String USERNAME = fUtil.getDataFromJsonFile("un");
		String PASSWORD = fUtil.getDataFromJsonFile("pwd");

		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.login(USERNAME, PASSWORD);

		System.out.println("Login successful");
	}

	@AfterMethod
	public void logout() {
		// ==============================
		// Logout from Application
		// ==============================
		WebElement profileIcon = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));

		WebDriverUtility wdUtil = new WebDriverUtility(driver);
		wdUtil.hover(profileIcon);

		driver.findElement(By.linkText("Sign Out")).click();

		System.out.println("Logout successful");
	}

	@AfterClass
	public void tearDown() {
		// ==============================
		// Close Browser
		// ==============================
		driver.quit();
		System.out.println("Browser closed successfully");
	}

//	@AfterSuite
//	public void repBackup() {
//		report.flush();
//	}
}
