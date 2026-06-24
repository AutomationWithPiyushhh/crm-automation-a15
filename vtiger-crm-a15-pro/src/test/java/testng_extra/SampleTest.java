package testng_extra;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SampleTest {
	WebDriver driver ;
	@Test
	public void case1() throws InterruptedException {
		driver = new ChromeDriver();
		System.out.println("hi");
		Thread.sleep(2000);
		driver.quit(); 
	}
	
	@Test(enabled = false)
	public void case2() throws InterruptedException {
		driver = new FirefoxDriver();
		System.out.println("hellow");
		Thread.sleep(2000);
		driver.quit();
	}
	
	@Test
	public void case3() throws InterruptedException {
		driver = new ChromeDriver();
		System.out.println("how are you ???");
		Thread.sleep(2000);
		driver.quit();
	}
}
