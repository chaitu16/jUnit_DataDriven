package testcaptcha;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.io.FileHandler;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TestCaptcha {

	public static void main(String[] args) throws InterruptedException, IOException, TesseractException {
		 WebDriver driver = null;
		FirefoxOptions options = new FirefoxOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		options.addPreference("geo.enabled", false);
		options.addPreference("dom.webnotifications.enabled", false);
		options.addPreference("app.update.enabled", false);
		
		FirefoxProfile fProfile = new FirefoxProfile();
		fProfile.setPreference("dom.webnotifications.enabled", false);
		fProfile.setPreference("permissions.default.desktop-notification", 2); 
		options.setProfile(fProfile);
		
		
		driver = new FirefoxDriver(options);
		
		driver.get("https://quikr.com");
		driver.findElement(By.cssSelector("label.user-name")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='nls_formControl']/input[@type='text']")).sendKeys("ingenuity2021@tutanota.com");
		driver.findElement(By.xpath("//button[text()='Continue']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='nls_formControl']/input")).sendKeys("QuickerAdd@123!");
		driver.findElement(By.xpath("//button[@class='nls_primaryButton']")).click();
		Thread.sleep(6000);
		
		driver. findElement(By.cssSelector("a.nls_refresh_captcha")).click();
		WebElement imageCaptcha = driver.findElement(By.xpath(" //img[@class='nls_captchaimage']"));
		
		File src = imageCaptcha.getScreenshotAs(OutputType.FILE);
		
		String path = "C:\\Users\\Aditya\\Desktop\\Eclipse\\SeleniumWorkSpace\\SeleniumTestNGMavenPracticeExercises\\AA_junit_DataDrivenFramework\\captchaimages\\captcha.PNG";
		FileHandler.copy(src, new File(path));
		Thread.sleep(6000);

		Thread.sleep(10000);
		ITesseract image = new Tesseract();
		
		image.setTessVariable("user_defined_dpi", "270");
	//	image.setLanguage("eng");
	//	image.setPageSegMode(1);
	//	image.setOcrEngineMode(1);
		
		String str = image.doOCR(new File(path));
		String newCaptcha = str.trim().substring(0, 5);
		
		String captcha = newCaptcha.replaceAll("[^a-zA-Z0-9]", "");
		System.out.println(captcha);
			

		
		
		
		//     //div[@class='nls_formControl']/input
		
		
		
		


	}

}
