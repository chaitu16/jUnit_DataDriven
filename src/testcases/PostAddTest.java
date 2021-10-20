package testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Util.TestUtil;
import junit.framework.Assert;
import testbase.TestBase;

public class PostAddTest extends TestBase {
	
	
	@Test 

	public void postAddTest() throws InterruptedException, IOException {
		
		launchBrowser("chrome");		
		driver.get(CONFIG.getProperty("url"));
		waitForPageToLoad();	
		if(!isLoggedIn) {
		TestUtil.doLogin(CONFIG.getProperty("user_email"), CONFIG.getProperty("user_password"));	
		}
		
		getObject("postAdd_css").click();
		driverWait();
		Actions action = new Actions(driver);	
		action.moveToElement(getObject("elec_app_xpath")).click().build().perform();
		getObject("more_btn_xpath").click();
		getObject("Room_heat_xpath").click();
		
		WebElement drpdwn = getObject("brand_drpdwn_xpath");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='Brand_name_selectWrap']/ul[@class='optionLists']")));
		
	
		List<WebElement> brands = drpdwn.findElements(By.tagName("li"));
		for(int i =0; i<brands.size();i++) {
			if(brands.get(i).getText().equals("Usha")) {
				brands.get(i).click();
			}
		}
		
		WebElement cond = getObject("condtion_drpdwn_xpath");	
		new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='Condition_selectWrap']/ul[@class='optionLists']")));
		driverWait();
		List<WebElement> condition = cond.findElements(By.tagName("li"));
		for(int i =0; i<condition.size();i++) {
			if(condition.get(i).getText().equals("Unboxed")) {
				condition.get(i).click();
			}
			
		}
		
		getObject("title_css").sendKeys("Mr. Ingenuity's Room Heater Super Quality");
		getObject("formtop_css").click();
		getObject("price_css").sendKeys("2000");
		new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(getObject("No_nego_chk_bx_xpath")));
		if(!getObject("No_nego_chk_bx_xpath").isSelected()){
			getObject("No_nego_chk_bx_xpath").click();
		}
		
		
		getObject("pincode_css").sendKeys("500001");
		
		
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("You_are")));
		getObject("youare_css").click();
		driverWait();
		WebElement youR = driver.findElement(By.xpath("//div[@id='You_are_selectWrap']/ul[@class='optionLists']"));
		
		List<WebElement> ur = youR.findElements(By.tagName("li"));
		for(int i =0;i<ur.size();i++) {			
			if(ur.get(i).getText().equals("Individual")) {
					ur.get(i).click();			
			}	
			
		
		getObject("postAdd_btn_css").click();
		
	
		
		
		
		}
}
	
	
}
