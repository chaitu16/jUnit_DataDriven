package testcases;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Util.TestUtil;
import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.TesseractException;
import testbase.TestBase;

public class LoginTest extends TestBase {
	
	
	@Test
	public void loginTest() throws InterruptedException, IOException {
		
	launchBrowser("chrome");
	
	driver.get(CONFIG.getProperty("url"));
	waitForPageToLoad();
	
	TestUtil.doLogin(CONFIG.getProperty("user_email"), CONFIG.getProperty("user_password"));
	
	if(!isLoggedIn) {
		System.out.println("Not logged in");
	}
	
}
	
	
}
