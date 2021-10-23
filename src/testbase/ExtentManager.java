package testbase;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	static ExtentReports reports;
	public static String screenShotsFolder = null;
	public static String reportsFolder = null;
	public static ExtentReports getReports() {
		
		if (reports == null) {
			reports = new ExtentReports();// This will initialize reports
			
			Date d = new Date();
			
			String path = System.getProperty("user.dir")+"/reports";
			
			 screenShotsFolder = System.getProperty("user.dir")+"/reports/"+d.toString().replaceAll(":", "-")+"/ScreenShots";
			 reportsFolder =  System.getProperty("user.dir")+"/reports/"+d.toString().replaceAll(":", "-");

			File f = new File(screenShotsFolder);
			f.mkdirs();
			
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportsFolder);
			

			sparkReporter.config().setReportName("jUnit Data Driven Tests");
			sparkReporter.config().setDocumentTitle("Automation Reports");
			sparkReporter.config().setTheme(Theme.STANDARD);
			sparkReporter.config().setEncoding("utf-8");
			
			reports.attachReporter(sparkReporter);	
			
		}
		
		return reports;
	}

}
