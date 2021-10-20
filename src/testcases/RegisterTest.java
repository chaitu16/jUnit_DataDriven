package testcases;

import java.time.Duration;
import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.TestBase;

public class RegisterTest extends TestBase{
	
	@Test
	public void registerTest() throws InterruptedException {
		
		launchBrowser("chrome");		
		driver.get(CONFIG.getProperty("url"));	
		waitForPageToLoad();	
		Thread.sleep(10000);
		getObject("login_register_xpath").click();	
//		getObject("email_mobile_xpath").sendKeys(CONFIG.getProperty("user_email"));
		getObject("email_mobile_xpath").sendKeys("Abchello@world.com");
		getObject("continue_btn_xpath").click();
		getObject("reg_name_css").sendKeys("AbcMan");
		getObject("reg_mobileNum_css").sendKeys("9998889898");
//		getObject("reg_password_xpath").sendKeys(CONFIG.getProperty(encryptDecrypt("user_password")));
//		getObject("reg_chkbx_xpath").click();
		getObject("reg_password_xpath").sendKeys("AbcAbcAbc123");
		getObject("reg_cntinuebtn_xpath").click();
		Thread.sleep(10000);

	}
	
	
}
