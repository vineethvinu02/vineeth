package selenium.casestudy;



import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import selenium.casestudy.UtilityClass;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class NewTest1 {

	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;

	@Test(priority = 1)
	public void testRegistration() {
		// sign up
		logger = extent.startTest("passed");
		driver.findElement(By.linkText("SignUp")).click();
		// user name and password

		driver.findElement(By.id("userName")).sendKeys("Vineeth202");
		driver.findElement(By.name("firstName")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='err']")).getText(), "Available");
		driver.findElement(By.name("firstName")).sendKeys("Vineeth");
		driver.findElement(By.name("lastName")).sendKeys("Guru");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Abcd1234");
		driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys("Abcd1234");
		driver.findElement(By.xpath("/html/body/main/div/div/form/fieldset/div/div[6]/div/div/label/span[1]")).click();
		// email and address
		driver.findElement(By.id("emailAddress")).sendKeys("vineeth@gmail.com");
		driver.findElement(By.name("mobileNumber")).sendKeys("9876543210");
		driver.findElement(By.name("dob")).sendKeys("09/23/1997");
		driver.findElement(By.name("address")).sendKeys("23 Park Avenue street");
		// Security Question
		Select SecurityQuestion = new Select(driver.findElement(By.name("securityQuestion")));
		SecurityQuestion.selectByVisibleText("What is your favour color?");
		driver.findElement(By.name("answer")).sendKeys("Sky Blue");
		driver.findElement(By.xpath("/html/body/main/div/div/form/fieldset/div/div[13]/div/input[1]")).click();
		Assert.assertEquals(driver.getTitle(), "Login");
		logger.log(LogStatus.PASS, "testRegistration Testcase is passed");
	}

	@Test(priority = 2)
	public void testLogin() {
		logger = extent.startTest("passed");
		driver.findElement(By.id("userName")).sendKeys("Vineeth202");
		driver.findElement(By.id("password")).sendKeys("Abcd1234");
		driver.findElement(By.name("Login")).click();
		// next_page
		Assert.assertEquals(driver.getTitle(), "Home");
		System.out.println("Login Test Successful !! ");
		logger.log(LogStatus.PASS, "testLogin Testcase is passed");
	}
	
	

	@Test(priority = 3)
	public void testCart() {

		logger = extent.startTest("passed");

		Actions cursor = new Actions(driver);
		cursor.moveToElement(driver.findElement(By.linkText("All Categories"))).build().perform();
		driver.findElement(By.linkText("Electronics")).click();
		WebDriverWait Wait = new WebDriverWait(driver, 100);
		Wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("Head Phone")));
		driver.findElement(By.linkText("Head Phone")).click();
		driver.findElement(By.linkText("Add to cart")).click();
		Assert.assertEquals(driver.getTitle(), "Search");
		  driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();
		Assert.assertEquals(driver.getTitle(), "View Cart");
		driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();
		Assert.assertEquals(driver.getTitle(), "Cart Checkout");
		driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click();
		logger.log(LogStatus.PASS, "cart Testcase is passed");
	}

	@Test(priority = 4)
	public void testPayment() {

		// For selecting bank through radio button
		logger = extent.startTest("passed");
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("payment-info")));

		driver.findElement(By.xpath("//*[@id=\"swit\"]/div[1]")).click();
		driver.findElement(By.id("btn")).click();
		Assert.assertEquals(driver.getTitle(), "Payment Gateway");

		// For netbanking login
		driver.findElement(By.name("username")).sendKeys("123456");
		driver.findElement(By.name("password")).sendKeys("Pass@456");
		driver.findElement(By.xpath("//input[@type='submit' and @value='LOGIN']")).click();
		Assert.assertEquals(driver.getTitle(), "Payment Gateway");

		//
		driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
		driver.findElement(By.xpath("//input[@type='submit' and @value='PayNow']")).click();
		Assert.assertEquals(driver.getTitle(), "Order Details");
		logger.log(LogStatus.PASS, "testPayment Testcase is passed");
	}

	@Test(priority = 5)
	public void testPayment1() {
		logger = extent.startTest("passed");
		// For selecting bank through radio button
		driver = UtilityClass.getDriver("chrome");
		driver.manage().window().maximize();
		driver.get("http://10.232.237.143:443/PaymentGateway/getOrderDetails.htm");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@id=\"swit\"]/div[1]")).click();
		driver.findElement(By.id("btn")).click();
		Assert.assertEquals(driver.getTitle(), "Payment Gateway");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// For netbanking login
		driver.findElement(By.name("username")).sendKeys("123456");
		driver.findElement(By.name("password")).sendKeys("Pass@456");
		driver.findElement(By.xpath("//input[@type='submit' and @value='LOGIN']")).click();
		Assert.assertEquals(driver.getTitle(), "Payment Gateway");

		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

		//Transpwd already exist in the text area
		
		// driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
		driver.findElement(By.xpath("//input[@type='submit' and @value='PayNow']")).click();
		// Assert.assertEquals(driver.getTitle(), "Order Details");
		Assert.assertNull(driver.findElement(By.name("transpwd")));
		logger.log(LogStatus.PASS, "testPayment1 Testcase is passed");
	}

	@BeforeTest
	public void StartReportbeforeTest() {
		// creating folder for reports
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/casestudy_Reports.html", true);
		extent.addSystemInfo("Host Name", "TestMeApp");
		extent.addSystemInfo("Environment", "Selenium Testing");
		extent.addSystemInfo("User Name", "Group-1");

		driver = UtilityClass.getDriver("chrome");
		driver.manage().window().maximize();
		driver.get("http://10.232.237.143:443/TestMeApp");

	}

	@AfterClass
	public void endReportAfterClass() {
		driver.quit();
		extent.close();
	}

	@AfterMethod
	public void getResultAfterMethod(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = NewTest1.getScreenshot(driver, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.endTest(logger);
		extent.flush();
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Failed_SS/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
