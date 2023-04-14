package dataProviderExcel_testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataProviderExcel_Utils.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_Testcases extends Utils {
	public static WebDriver driver;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	@BeforeMethod
	public void prelogin() {

		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://automationexercise.com/");
		driver.findElement(By.xpath("//a[text()=\" Signup / Login\"]")).click();

	}

	@Test(testName="validcases")
	public void valid() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.name("email")).sendKeys("apple22@gmail.com");
		driver.findElement(By.name("password")).sendKeys("test123");
		driver.findElement(By.xpath("//button[text()=\"Login\"]")).click();
		WebElement logout = driver.findElement(By.xpath("//a[text()=\" Logout\"]"));
		Assert.assertTrue(logout.isDisplayed());
	}

	@Test(dataProvider = "logindata",testName="invalidcases")
	public void invalid(String username, String password) throws Exception {
		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()=\"Login\"]")).click();

	}

	@DataProvider(name = "logindata")
	public Object[][] logindata() throws Exception {

		Object[][] datas =stringdata();
		return datas;
		
	}

	@AfterClass
	public void close() {
		driver.close();
	}

}
