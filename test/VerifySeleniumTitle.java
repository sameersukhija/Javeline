package testAdvanceReporting;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.ISuite;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.testng.xml.XmlSuite;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.HTMLReporter;
import com.relevantcodes.extentreports.results.*;
import com.relevantcodes.extentreports.testngexamples.ExtentReporterNG;


public class VerifySeleniumTitle {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
private ExtentReports extent;
private Class<? extends Class> logger;
private List<XmlSuite> xmlSuites;
private List<ISuite> suites;
private String outputDirectory;

@BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl="http://learn-automation.com";
    
}
@Test
public void testVerifyTitle() throws Exception {
	 logger = ExtentReports.class.getClass();
	
	//logger("VerifySeleniumTitle").getClass();
	//logger.newInstance().("c:\\Reports\\TestReport.html");
	ExtentReporterNG.class.getClass();
	//ExtentReports.this.startTest();
	String outputDirectory ="c:\\Reports\\Test.html"; 
	//extent.startTest("Verify Page Title");
	 new ExtentReports(outputDirectory + File.separator + "Extent.html", true);
	// ExtentReports.this.startTest("Verify Page Title");
	driver.get(baseUrl);
  driver.manage().window().maximize();
  String title=driver.getTitle();
  Assert.assertTrue(title.contains("Selenium"));
  
}

}