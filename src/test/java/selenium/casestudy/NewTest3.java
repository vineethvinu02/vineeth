package selenium.casestudy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import org.testng.ITestResult;

import org.testng.SkipException;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class NewTest3 {
	ExtentReports extent;

	ExtentTest logger;
	WebDriver driver;

	

	@BeforeTest
	public void beforetest()

	{
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Reports.html", true);

		extent.addSystemInfo("Host Name", "TestMe");

		extent.addSystemInfo("Environment", "Selenium Testing");

		extent.addSystemInfo("User Name", "Lekha Nair");
		logger = extent.startTest("passTest");
		driver = UtilityClass.getDriver("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
		Assert.assertEquals(driver.getTitle(), "Home");
		driver.findElement(By.linkText("SignIn")).click();
		driver.findElement(By.name("userName")).sendKeys("vinu123");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("Login")).click();
		Assert.assertEquals(driver.getTitle(), "Home");
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");
	}

	@Test
	public void cart() {
		logger = extent.startTest("passTest");
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
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");
		
		
		//extent.close();
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/PassedScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		
	
		if (result.getStatus() == ITestResult.FAILURE) {

			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());

			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());

			String screenshotPath = NewTest3.getScreenshot(driver, result.getName());

			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getName());

			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getThrowable());

			String screenshotPath = NewTest3.getScreenshot(driver, result.getName());

			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {

			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());

		}

		extent.endTest(logger);

		extent.flush();
driver.close();
	}



	}


