package selenium.casestudy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Addtocart {
	WebDriver driver;
	@BeforeTest
	public void beforetest()
	{
		driver=UtilityClass.getDriver("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
		Assert.assertEquals(driver.getTitle(), "Home");
		driver.findElement(By.linkText("SignIn")).click();
		  driver.findElement(By.name("userName")).sendKeys("sasi123");
		  driver.findElement(By.name("password")).sendKeys("123456");
		  driver.findElement(By.name("Login")).click();
		  Assert.assertEquals(driver.getTitle(), "Home");
	}
  @Test
  public void cart() {
	  // driver.findElement(By.name("products")).sendKeys("Headphone");
	  //  driver.findElement(By.xpath("/html/body/div[1]/form/input")).click();
	  // driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();
	  // driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();
	  // driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();
	  //driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click();
	 
	  
	  
	  //WebElement cursor =driver.findElement(By.linkText("All Categories"));
	  Actions act= new Actions(driver);
	  act.moveToElement(driver.findElement(By.linkText("All Categories"))).build().perform();
	  // act.moveToElement(cursor).build().perform();
	 
	  //WebElement move =driver.findElement(By.linkText("Electronics"));
	  // Actions abc= new Actions(driver);
	  act.moveToElement(driver.findElement(By.linkText("Home Appliances"))).build().perform();
	  // abc.moveToElement(move).build().perform();
	  driver.findElement(By.linkText("Home Appliances")).click();
	  act.moveToElement(driver.findElement(By.linkText("Ceiling"))).build().perform();
	  driver.findElement(By.linkText("Ceiling")).click();
	//*[@id="submenuul11292"]/li[1]/a/span
	 
	  //WebElement dot=driver.findElement(By.partialLinkText("Head"));
 	  //Actions bcd=new Actions(driver);
	  // bcd.moveToElement(driver.findElement(By.partialLinkText("Head"))).build().perform();
	  // bcd.moveToElement(dot).build().perform();
	  // driver.findElement(By.partialLinkText("Head")).click();
	 
	
	 
	
	 
  }
}
