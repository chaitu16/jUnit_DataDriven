package Util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import datatable.Xls_Reader;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import testbase.TestBase;

public class TestUtil extends TestBase{
	
	byte[] decodeBytes = null;
	
	public static void doLogin(String userEmail , String userPassword) throws InterruptedException, IOException {
		
	if(isLoggedIn) {
		
		signOut();
		isLoggedIn = false;
		
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
		
		public static boolean isSkip(String testCase) {
			if(datatable == null) {
				datatable  = new Xls_Reader(System.getProperty("user.dir")+"\\src\\config\\Suite1.xlsx");
			}
	
			for(int rNum =2; rNum<=datatable.getRowCount("Test Cases");rNum++) {
				if((datatable.getCellData("Test Cases", "TCID", rNum)).equals(testCase)){
					if((datatable.getCellData("Test Cases", "Runmode", rNum)).equals("Y")){
						System.out.println("runtest");
						return false;
						
					}else {
						System.out.println("do not run");
						return true;
					}
				}
			}
			
			return true;
			
		}
		
		
		public static Object[][] getData(String testName){
			if(datatable == null) {
				datatable  = new Xls_Reader(System.getProperty("user.dir")+"\\src\\config\\Suite1.xlsx");
			}
			
			int rows = datatable.getRowCount(testName)-1;	
		
			if(rows <= 0) {					
				Object [][] testData = new Object [1][0];
				return testData;				
			}
			
			rows =datatable.getRowCount(testName);
			int cols = datatable.getColumnCount(testName);
			
			 Object[][] data = new Object[rows-1][cols];
			 
			for(int rNum =2 ; rNum <= rows ;rNum++) {
				for(int cNum =0 ; cNum < cols ;cNum++) {
					data [rNum-2] [cNum] = datatable.getCellData(testName, cNum, rNum);
					 
				}
			}
			return data;		
		}
		

			public static void takeSnapShot(WebDriver driver,String fileWithPath) throws Exception{
			//Convert web driver object to TakeScreenshot
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			//Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			//Move image file to new destination
			File DestFile=new File(fileWithPath);
			//Copy file at destination
			FileUtils.copyFile(SrcFile, DestFile);
}

		
}

