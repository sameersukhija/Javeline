package com.lumata.common.testing.plan;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Browser;
import com.lumata.common.testing.system.Environment;

public class TestSeleniumWebDriver {
			
	private Environment env_ = null;
	private Browser browser_ = null;
	private SeleniumWebDriver seleniumWebDriver_ = null;
	
	@BeforeSuite
	@Parameters({"browser", "environment"})
	public void init( 	@Optional("FIREFOX") 		String browser, 
						@Optional("E4O_TESTING") 	String environment ) throws EnvironmentException 
	{	
		Reporter.log("Init \"Environment\" object", true);
		env_ = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env_ , "NetworkEnvironment is null during init phase!");
		
		Reporter.log("Init \"Browser\" object", true);
		browser_ = new Browser(env_.getBrowser(browser), browser);		
		Assert.assertNotNull( browser_ , "Browser is null during init phase!");
	}
	
	@Test
	public void loadSeleniumWebDriver() throws EnvironmentException 
	{		
		Reporter.log("Startup \"SeleniumWebDriver\" object.", true);
		seleniumWebDriver_ = new SeleniumWebDriver( browser_, env_.getLink() );
		Assert.assertNotNull( seleniumWebDriver_ , "SeleniumWebDriver is null!");
		
		seleniumWebDriver_.windowMaximize();
		seleniumWebDriver_.open("/");
		
//		SeleniumUtils.waitFor( 1000 );
//		
//		WebElement webElement = SeleniumUtils.findForComponentDisplayed( seleniumWebDriver_, SeleniumUtils.SearchBy.ID, "j_username", 30000, 500 );
//		
//		Assert.assertNotNull( webElement );
//		
//		SeleniumUtils.waitFor( 2000 );
//		
//		String url = env_.getLink() + "/resources/theme/images/expression_logo_white_ext.png";
//		
//		ClientResponse<String> response = RestClient.get( url );
//		
//		Assert.assertEquals( response.getStatus(), 200 );
		
	}	
	
	@AfterSuite
	public void tearDown() {

		if ( seleniumWebDriver_ != null ) {
			
			Reporter.log( "Close \"SeleniumWebDriver\" object.", true);
			seleniumWebDriver_.close();
		}
	}

}
