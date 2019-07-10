package selenium.casestudy;




import java.io.File;

import java.text.SimpleDateFormat;

import java.util.Date;


import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import org.testng.ITestResult;

import org.testng.SkipException;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;

import com.relevantcodes.extentreports.ExtentTest;

import com.relevantcodes.extentreports.LogStatus;


import selenium.casestudy.UtilityClass;


public class NewTest2 {
	
ExtentReports extent;
	
ExtentTest logger;
	
WebDriver driver;

	
@BeforeTest
	
public void startReport() {
		
extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Reports.html", true);
		
extent.addSystemInfo("Host Name", "TestMe");
		
extent.addSystemInfo("Environment", "Selenium Testing");
		
extent.addSystemInfo("User Name", "Lekha Nair");
	}

	
public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		
String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
TakesScreenshot ts = (TakesScreenshot) driver;
		
File source = ts.getScreenshotAs(OutputType.FILE);
		
String destination = System.getProperty("user.dir") + "/FailedScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@Test
	public void passTest() {
		logger = extent.startTest("passTest");
		Assert.assertTrue(true);
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");
	}

	@Test
	public void failTest() {
		logger = extent.startTest("failTest");
		driver = UtilityClass.getDriver("firefox");
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
		String title = driver.getTitle();
		Assert.assertEquals(title, "NoTitle");
		logger.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
	}

	@Test
	public void skipTest() {
		logger = extent.startTest("skipTest");
		throw new SkipException("Skipping - This is not ready for testing ");
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			
logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			
logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			
String screenshotPath = NewTest2.getScreenshot(driver, result.getName());
			
logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		
}
 else if (result.getStatus() == ITestResult.SKIP) {
			
logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		
}
		
extent.endTest(logger);
		
extent.flush();
	
}
	

	
@AfterTest
	
public void endReport() {
		
extent.close();
		
driver.close();
	
}

}
