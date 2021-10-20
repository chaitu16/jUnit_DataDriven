package Util;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;



import java.io.File;
import java.io.IOException;
import java.util.Base64;

import testbase.TestBase;

public class TestUtil extends TestBase{
	
	byte[] decodeBytes = null;
	
	public static void doLogin(String userEmail , String userPassword) throws InterruptedException, IOException {
		
	if(isLoggedIn) {
		
		//signOut();
		
	}else {		
				
//		driver.findElement(By.xpath(LOCATORS.getProperty("login_register_xpath"))).click();	
//		driver.findElement(By.xpath(LOCATORS.getProperty("email_mobile_xpath"))).sendKeys(userEmail);
//		driver.findElement(By.xpath(LOCATORS.getProperty("continue_btn_xpath"))).click();
//		driver.findElement(By.xpath(LOCATORS.getProperty("reg_password_xpath"))).sendKeys(encryptDecrypt(userPassword));
//		driver.findElement(By.xpath(LOCATORS.getProperty("login_btn_xpath"))).click();
		
//		getObject("xpath",LOCATORS.getProperty("login_register_xpath")).click();
		
		getObject("login_register_css").click();;
		getObject( "email_mobile_xpath").sendKeys(userEmail);
		getObject("continue_btn_xpath").click();
		Thread.sleep(1000);
		getObject("reg_password_xpath").sendKeys(encryptDecrypt(userPassword));
		getObject("login_btn_xpath").click();
		driverWait();
		
		String loggedInUser= driver.findElement(By.xpath(LOCATORS.getProperty("login_chk_user-xpath"))).getText();
		
		try {
			if(loggedInUser.equals("Ingenuity")) {
				isLoggedIn= true;
				System.out.println(loggedInUser);
			}
			
		}catch(Throwable t){
			isLoggedIn = false;
			System.out.println("User not Logged in");
		}
		
	
		
		
	}
}	

		public static void signOut() {
				if(isLoggedIn) {
				getObject("user_menu_css").click();
				getObject("singoutlink_css").click();
				
				}
		}
}

