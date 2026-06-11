package crm.contact;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseutility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.WebDriverUtility;
import object_repository.HomePage;
import object_repository.LoginPage;

/**
 * Test Case: Create Contact in CRM Application
 * 
 * Author : Piyush Experience : Automation Tester | 2 Years Tool Stack : Java +
 * Selenium WebDriver
 * 
 * Objective: Automate the end-to-end flow of creating a new Contact in the CRM
 * application and validate successful creation.
 * 
 * Test Flow: 1. Launch Browser 2. Login to CRM Application 3. Navigate to
 * Contacts Module 4. Create New Contact 5. Validate Contact Creation 6. Close
 * Browser
 */

@Listeners(listeners_utility.List_Imp.class)
public class ContactTest extends BaseClass {

	@Test
	public void createContactTest()
			throws EncryptedDocumentException, FileNotFoundException, IOException, ParseException {

//		ExtentTest test = report.createTest("createContactTest");
		
//		get data from excel
		FileUtility fUtil = new FileUtility();
		String expectedLastName = fUtil.getDataFromExcelFile("contact", 1, 0);

		// ==============================
		// Navigate to Contacts Module
		// ==============================
		HomePage hp = new HomePage(driver);
		hp.getConLink().click();
		driver.findElement(By.cssSelector("img[title='Create Contact...']")).click();

		// ==============================
		// Create New Contact
		// ==============================

		driver.findElement(By.name("lastname")).sendKeys(expectedLastName);
		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();

		// ==============================
		// Validation
		// ==============================
		String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		
		boolean status = actualLastName.equals(expectedLastName);
		Assert.assertTrue(status);
		
//		if (actualLastName.equals(expectedLastName)) {
//			System.out.println("PASS : Contact Created Successfully");
//			System.out.println("Created Contact Last Name : " + actualLastName);
//			
//		} else {
//			System.out.println("FAIL : Contact Creation Failed");
//			System.out.println("Expected : " + expectedLastName);
//			System.out.println("Actual   : " + actualLastName);
//		}
	}
}