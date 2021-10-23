package datatable;

import testbase.TestBase;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestClass extends TestBase{
	static WebDriver driver ;

	public static void main(String[] args) throws Exception {		
		driver = new FirefoxDriver();
		driver.get("https://www.gulte.com/");
		driver.findElement(By.cssSelector("a[href='https://www.gulte.com/movienews']")).click();
		Thread.sleep(3000);
		takeSnapShot(driver, System.getProperty("user.dir")+"\\screenshots\\sshot.jpg");
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

