package selenium.casestudy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Registration {
	ExtentReports extent;

	ExtentTest logger;
	
	WebDriver driver;
	
	@Test(priority = 1)
	public void testRegistration() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Reports.html", true);

		extent.addSystemInfo("Host Name", "TestMe");

		extent.addSystemInfo("Environment", "Selenium Testing");

		extent.addSystemInfo("User Name", "Parthiban");
		logger = extent.startTest("passTest");
		// sign up
		driver.findElement(By.linkText("SignUp")).click();
		// user name and password

		driver.findElement(By.id("userName")).sendKeys("parthi");
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
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");

	}
	@BeforeMethod
	public void chrome()
	{
		driver = UtilityClass.getDriver("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp");
	}
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/RegistrationPassedScreenshots/" + screenshotName + dateName
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

			String screenshotPath = Registration.getScreenshot(driver, result.getName());

			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getName());

			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getThrowable());

			String screenshotPath = Registration.getScreenshot(driver, result.getName());

			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {

			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());

		}

		extent.endTest(logger);

		extent.flush();
driver.close();
	}


}
