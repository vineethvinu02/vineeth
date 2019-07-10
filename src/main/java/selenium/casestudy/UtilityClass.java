package selenium.casestudy;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;

	public class UtilityClass 
	{
	static WebDriver driver;
	public static WebDriver getDriver(String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Training_C2a.04.30\\Desktop\\Drivers\\chromedriver.exe");
			driver= new ChromeDriver();
//			driver.get("https://www.google.com");
			}
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.firefox.driver","C:\\Users\\Training_C2a.04.30\\Desktop\\Drivers\\geckodriver.exe");
			driver= new FirefoxDriver();
			driver.get("https://www.google.com");
		}
		else if(browser.equalsIgnoreCase("internet explorer"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\Users\\Training_C2a.04.30\\Desktop\\Drivers\\IEDriverServer.exe");
			driver =new InternetExplorerDriver();
			driver.get("https://www.google.com");
		}
			else
			{
				System.out.println("invalid ");
			}
			return driver;
			
		}
	}

