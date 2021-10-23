package testcases;

import java.io.IOException;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Util.TestUtil;
import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.TesseractException;
import testbase.ExtentManager;
import testbase.TestBase;

public class LoginTest extends TestBase {
	
/*	@Before
	public void beforeTest() {
		launchBrowser("firefox");
		if(TestUtil.isSkip("LoginTest")) {
			Assume.assumeTrue(false);
		}
	}
*/	
	
	@Test
	public void loginTest() throws InterruptedException, IOException {
		
		launchBrowser("firefox");
		driver.get(CONFIG.getProperty("url"));	
	
	
//	driver.get(CONFIG.getProperty("url"));
//	waitForPageToLoad();
	
	TestUtil.doLogin(CONFIG.getProperty("user_email"), CONFIG.getProperty("user_password"));
	
	if(!isLoggedIn) {
		System.out.println("Not logged in");
	}
	
}
	
	
}
