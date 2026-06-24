package crm.contact;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import generic_utility.JavaUtility;
import generic_utility.WebDriverUtility;

public class CreatreContactTest {
	@Test
	public void createContactWithOrgTest() throws InterruptedException {
//		Open the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get("http://49.249.29.4:8888");

		// Login Vtiger Crm
		driver.findElement(By.xpath("//input[@name=\"user_name\"]")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name=\"user_password\"]")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id=\"submitButton\"]")).click();
		System.out.println("Successfully login");

//		create org => Accenture
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.cssSelector("[src='themes/softed/images/btnL3Add.gif']")).click();

		String orgName = "Accenture_" + JavaUtility.generateRandomNumber();
//		String orgName = "acc_123";
		WebElement orgField = driver.findElement(By.name("accountname"));
		orgField.sendKeys(orgName);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		Thread.sleep(3000);
		// Create opportunities
		driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();

		String Opportunity = "Testing";

		// fill the details
		driver.findElement(By.xpath("//input[@name=\"potentialname\"]")).sendKeys(Opportunity);

		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();

		String PID = driver.getWindowHandle();
		
		WebDriverUtility wdUtil = new WebDriverUtility(driver);
		wdUtil.switchToWindowByUrl("module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();

		Thread.sleep(2000);

//		dynamic xpath
//		driver.findElement(By.xpath("//a[text()='accenture']")).click();
//		driver.findElement(By.xpath("//a[text()='google']")).click();
//		driver.findElement(By.xpath("//a[text()='qspiders']")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

//		come back home
		wdUtil.switchToParentWindow(PID);
		// save the deatils
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		System.out.println("Successfully saved the Opportunities details");

	
		Thread.sleep(10000);
		driver.quit();

	}
}
