package testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestBase {
	
	public static WebDriver driver;
	ExtentReports reports;
	ExtentTest test;
	public static Properties CONFIG = null;
	public static Properties LOCATORS = null;
	
	public static boolean isLoggedIn =false;
	
	
	public WebDriver launchBrowser(String browser) {
		
		if(driver == null) {
			
			if(browser.equalsIgnoreCase("Chrome")) {
				Map prefs = new HashMap();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				ChromeOptions options = new ChromeOptions();
				options.setPageLoadStrategy(PageLoadStrategy.NONE);
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-notifications");
				options.addArguments("--start-maximized");
				options.addArguments("ignore-certificate-erors");
				options.addArguments("disable-geolocation");
			
				
				driver = new ChromeDriver(options);
				
			}else if(browser.equalsIgnoreCase("firefox")) {
				
				FirefoxOptions options = new FirefoxOptions();
				options.setPageLoadStrategy(PageLoadStrategy.NONE);
				options.addPreference("geo.enabled", false);
				options.addPreference("dom.webnotifications.enabled", false);
				options.addPreference("app.update.enabled", false);
				
				FirefoxProfile fProfile = new FirefoxProfile();
				fProfile.setPreference("dom.webnotifications.enabled", false);
				fProfile.setPreference("permissions.default.desktop-notification", 2); 
				options.setProfile(fProfile);
				
				
				driver = new FirefoxDriver(options);
				
			}else if(browser.equalsIgnoreCase("edge")) {
				Map prefs = new HashMap();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				
				EdgeOptions options = new EdgeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.setPageLoadStrategy(PageLoadStrategy.NONE);			
				options.addArguments("--disable-notifications");
				options.addArguments("--start-maximized");
				options.addArguments("ignore-certificate-errors");
				
				options.addArguments("disable-geolocation");
			
				
				driver = new EdgeDriver(options);
				
			}else if(browser.equalsIgnoreCase("ie")) {
				
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				DesiredCapabilities cap = new DesiredCapabilities();
				options.merge(cap);		
				driver = new InternetExplorerDriver();
			}
		}
		
		FileInputStream file = null;
		CONFIG =new Properties();
		String configURL = System.getProperty("user.dir")+"//src//config//config.properties";
		
		try {
			 file = new FileInputStream(configURL);
			try {
				CONFIG.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOCATORS = new Properties();
		String locatorsURL = System.getProperty("user.dir")+"//src//locators//locators.properties";
		try {
			file = new FileInputStream(locatorsURL);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			LOCATORS.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
		
	}
	
	@Rule
	public TestName name = new TestName();
	
	@Before
	public void init() {
		
		reports = ExtentManager.getReports();
		test = reports.createTest("Test executed : "+ name.getMethodName());			
	}
	
	@After
	public void quit() {
		reports.flush();
	}
	
	public void log(String msg) {
		System.out.println(msg);
		test.log(Status.INFO, msg);
	}
	
	public void logFail(String msg) {	
		test.log(Status.FAIL, msg);
		System.out.println("Test Failed : " +name.getMethodName());
	}
	
	public void logPass(String msg) {	
		test.log(Status.PASS, msg);
		System.out.println("Passed : " +name.getMethodName());
	}
	
	public void logSkip(String msg) {	
		test.log(Status.SKIP, msg);
		System.out.println("Skipped Test : " +name.getMethodName());
	}
	
	public static void waitForPageToLoad() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i=0;
		// page to load 100% -- wait for max 20 secs
		while(i!=10){
			String state = (String)js.executeScript("return document.readyState;");
			System.out.println(state);// complete, loading, interactive	
			if(state.equals("complete"))
				break;
			else
				Thread.sleep(2000);	
			i++;
		}
		// check for jquery/ajax status
		
		i=0;
		while(i!=10){
			Long d= (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);//0,1
			if(d.longValue() == 0 )
				break;
			else
				Thread.sleep(2000);
			i++;
		}		
	}
	
	public static WebElement getObject( String value) {
		
		try {
		if(value.endsWith("xpath")) {
			return driver.findElement(By.xpath(LOCATORS.getProperty(value)));
			
		}else if (value.endsWith("css")) {
			return driver.findElement(By.cssSelector(LOCATORS.getProperty(value)));
			
		}else if  (value.endsWith("id")) {
			return driver.findElement(By.id(LOCATORS.getProperty(value)));
			
		}else if  (value.endsWith("name")) {
			return driver.findElement(By.name(LOCATORS.getProperty(value)));
			
		}else if  (value.endsWith("tagname")) {
			return driver.findElement(By.tagName(LOCATORS.getProperty(value)));
			
		}else if  (value.endsWith("classname")) {
			return driver.findElement(By.className(value));
			
		}else if  (value.endsWith("linktext")) {
			return driver.findElement(By.linkText(value));
		}
		
		}catch(Throwable t) {
			System.out.println("No element found "+ t.getStackTrace());
			t.printStackTrace();
		
	}
	
		return null;
	}
	
public static String encryptDecrypt(String password) {		
		
		String decrptData  = password;		
		byte[] decodeBytes = Base64.getDecoder().decode(decrptData.getBytes());			
		return new String(decodeBytes);
		
	}


public static void driverWait() throws InterruptedException {
	synchronized (driver){
		driver.wait(8000);			
	}
	
}

}
