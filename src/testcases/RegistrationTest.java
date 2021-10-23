package testcases;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Util.TestUtil;
import datatable.Xls_Reader;
import testbase.ExtentManager;
import testbase.TestBase;


@RunWith(Parameterized.class)
public class RegistrationTest extends TestBase{
	
	public String email;
	public String username;
	public String phone;
	public String password;
	
	public RegistrationTest(String email , String username, String phone, String password) {
		
		this.email =email;
		this.username = username;
		this.phone= phone;
		this.password = password;
	}
	
	@Before
	public void beforeTest() {
		
		launchBrowser("firefox");
		
		if(TestUtil.isSkip("RegistrationTest")) {
			Assume.assumeTrue(false);
		}
		
	}
	
	

	
	@Test
	public void registerTest() throws InterruptedException {
		
//		launchBrowser("firefox");
		
		driver.get(CONFIG.getProperty("url"));	
//		waitForPageToLoad();	
		Thread.sleep(3000);
		getObject("login_register_xpath").click();	
//		getObject("email_mobile_xpath").sendKeys(CONFIG.getProperty("user_email"));
//		getObject("email_mobile_xpath").sendKeys("Abchello@world.com");
		Thread.sleep(3000);
		getObject("email_mobile_xpath").sendKeys(email);
		getObject("continue_btn_xpath").click();
//		getObject("reg_name_css").sendKeys("AbcMan");
		getObject("reg_name_css").sendKeys(username);
//		getObject("reg_mobileNum_css").sendKeys("9998889898");
		getObject("reg_mobileNum_css").sendKeys(phone);
//		getObject("reg_password_xpath").sendKeys(CONFIG.getProperty(encryptDecrypt("user_password")));
//		getObject("reg_chkbx_xpath").click();
//		getObject("reg_password_xpath").sendKeys("AbcAbcAbc123");
		getObject("reg_password_xpath").sendKeys(password);
		getObject("reg_cntinuebtn_xpath").click();
		Thread.sleep(5000);
		
		
		
	}
	
	@Parameters
	public static Collection<Object[]> readData(){		
		
	//	datatable = new Xls_Reader(System.getProperty("user.dir")+"\\src\\config\\Suite1.xlsx");
		Object [][]  data = TestUtil.getData("RegistrationTest");
		
/*		Object[][] para = new Object[2][4];
		
		//1st row
		para[0][0] = "sub3@suba.com";
		para[0][1] = "helloOne12";
		para[0][2] = "9345065988";
		para[0][3] = "pass12345";
		
		//2nd row
	
		para[1][0] = "sub4@suba.com";
		para[1][1] = "helloTwo32";
		para[1][2] = "9534465977";
		para[1][3] = "pass123456";

*/		
		return Arrays.asList(data);
	}
	
}
