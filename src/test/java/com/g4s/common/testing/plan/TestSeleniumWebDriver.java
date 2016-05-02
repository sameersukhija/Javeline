package com.g4s.common.testing.plan;

import org.jboss.resteasy.client.ClientResponse;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.g4s.common.testing.exceptions.NetworkEnvironmentException;
import com.g4s.common.testing.io.IOFileUtils;
import com.g4s.common.testing.network.RestClient;
import com.g4s.common.testing.selenium.SeleniumUtils;
import com.g4s.common.testing.selenium.SeleniumWebDriver;
import com.g4s.common.testing.selenium.SeleniumUtils.SearchBy;
import com.g4s.common.testing.system.Browser;
import com.g4s.common.testing.system.NetworkEnvironment;
import com.g4s.common.testing.system.Server;

public class TestSeleniumWebDriver {
			
	private NetworkEnvironment env_ = null;
	private SeleniumWebDriver seleniumWebDriver_ = null;
	
	/**
	 * This is basically an integration test to be launcher into project where Firefox profile folder
	 * is part of resources.
	 * 
	 * @author <a href="mailto:vincenzo.marrazzo@lumatagroup.com">Vincenzo Marrazzo</a>
	 *
	 */
	
	@Test
	@Parameters({"browser", "environment", "gui_server"})
	public void noProfileSectionIntoJSON( 	@Optional("FIREFOX") 				String browser, 
											@Optional("NO_PROFILE_BROWSER") 	String environment, 
											@Optional("better_than_actrule") 	String gui_server	) 
																				throws NetworkEnvironmentException 
	{	
		loadSeleniumFirefoxDriver(browser, environment, gui_server);
	}	
	
	@Test
	@Parameters({"browser", "environment", "gui_server"})
	public void realProfileIntoJSON( 		@Optional("FIREFOX") 				String browser, 
											@Optional("REAL_PROFILE_FOLDER") 	String environment, 
											@Optional("better_than_actrule") 	String gui_server	)
																				throws NetworkEnvironmentException 
	{	
		loadSeleniumFirefoxDriver(browser, environment, gui_server);
	}		
	
	@Test
	@Parameters({"browser", "environment", "gui_server"})
	public void unpackProfileFromJar( 		@Optional("FIREFOX") 				String browser, 
											@Optional("E4O_TESTING")		 	String environment, 
											@Optional("better_than_actrule") 	String gui_server	)
																				throws NetworkEnvironmentException 
	{	
		loadSeleniumFirefoxDriver(browser, environment, gui_server);
	}	
	
	@Test
	@Parameters({"browser", "environment", "gui_server"})
	public void realBinaryIntoJSON( 		@Optional("FIREFOX") 				String browser, 
											@Optional("BINARY_PROFILE") 		String environment, 
											@Optional("better_than_actrule") 	String gui_server	)
																				throws NetworkEnvironmentException 
	{	
		loadSeleniumFirefoxDriver(browser, environment, gui_server);
		
		Browser underTest = env_.getBrowser(gui_server, browser.toLowerCase());

		Assert.assertNotNull( underTest.getBinary(), "Binary object must be NOT null!");
	}		
	
	@Test
	@Parameters({"browser", "environment", "gui_server"})
	public void realBinaryMissingDes( 		@Optional("FIREFOX") 				String browser, 
											@Optional("BINARY_NO_DESC") 		String environment, 
											@Optional("better_than_actrule") 	String gui_server	)
																				throws NetworkEnvironmentException 
	{	
		loadSeleniumFirefoxDriver(browser, environment, gui_server);
		
		Browser underTest = env_.getBrowser(gui_server, browser.toLowerCase());

		Assert.assertNotNull( underTest.getBinary(), "Binary object must be NOT null!");
	}	
	
	/**
	 * Core test
	 * 
	 * @param browser
	 * @param environment
	 * @throws EnvironmentException
	 */
	private void loadSeleniumFirefoxDriver( String browser, String environment, String gui_server) throws NetworkEnvironmentException 
	{		
		Reporter.log("Init \"NetworkEnvironment\" object", true);
		env_ = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env_ , "NetworkEnvironment is null during init phase!");

		Reporter.log("Init \"Server\" object", true);
		Server gui = env_.getServer(gui_server);
		Assert.assertNotNull( gui, "Gui Server object is null!");
		
		Reporter.log("Startup \"SeleniumWebDriver\" object.", true);
		seleniumWebDriver_ = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		Assert.assertNotNull( seleniumWebDriver_ , "SeleniumWebDriver is null!");	
		
		try {
			Thread.sleep(3_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	

	@Test
	@Parameters({"browser", "environment", "gui_server"})
	public void loadSeleniumWebDriverLoginLogout( 	@Optional("FIREFOX") 	String browser, 
													@Optional("E4O_FAKE_NE") 	String environment, 
													@Optional("actrule") 	String gui_server ) throws NetworkEnvironmentException {		
				
		NetworkEnvironment env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env );
		
		Server gui = env.getServer( gui_server );
		
		seleniumWebDriver_ = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		Assert.assertNotNull( seleniumWebDriver_ );

		SeleniumUtils.waitFor( 1000 );
		
		WebElement el = null;
				
		el = SeleniumUtils.findForComponentDisplayed(seleniumWebDriver_, SearchBy.ID, "gwt-debug-InputLoginUsername");
		el.sendKeys("superman");
		
		el = SeleniumUtils.findForComponentDisplayed(seleniumWebDriver_, SearchBy.ID, "gwt-debug-InputLoginPassword");
		el.sendKeys("super2010Man");
				
		el = SeleniumUtils.findForComponentDisplayed(seleniumWebDriver_, SearchBy.ID, "gwt-debug-ButtonLoginAuthentication");
		el.click();
		
		SeleniumUtils.waitFor( 1000 );
		
		Alert confirmForceLogin = null;
		 
		try {
			
			confirmForceLogin = seleniumWebDriver_.selectAlert();
		    	
			if ( confirmForceLogin != null )
				confirmForceLogin.accept(); 
				
		} catch (NoAlertPresentException e) {
			// nothing to do
		}
		
		WebElement licenseDialog = null;
	
		try {
			licenseDialog = SeleniumUtils.findForComponentDisplayed(seleniumWebDriver_, SearchBy.XPATH, "//div[contains(text(),\"The license is invalid\")]");
		} catch ( NoSuchElementException e ) {
			
			// no license dialog box
			// nothing to do
		}
		
		if ( licenseDialog != null ) {
			licenseDialog.findElement(By.xpath("//div[contains(text(),\"The license is invalid\")]/ancestor::div[1]//button")).click();
		}
		
		el = SeleniumUtils.findForComponentDisplayed(seleniumWebDriver_, SearchBy.XPATH, "//button[contains(@id,'gwt-debug-Logout')]");
		el.click();
		
		SeleniumUtils.waitFor( 1000 );
		
		Alert confirmLogout = null;
		 
		try {
			confirmLogout = seleniumWebDriver_.getWrappedDriver().switchTo().alert();
		    	
			if ( confirmLogout != null )
				confirmLogout.accept();
		} catch (NoAlertPresentException e) {
		  
			// nothing to do
			Assert.assertTrue( false, "Missing popup event!");
		}
	}		
	
	@Parameters({"browser", "environment"})
	@Test( enabled = false )
	public void loadSeleniumWebDriver_1( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment, @Optional("actrule") String gui_server ) throws Exception {		
				
		NetworkEnvironment env = new NetworkEnvironment( environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env );
		
		Server gui = env.getServer( gui_server );
		
		SeleniumWebDriver seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		Assert.assertNotNull( seleniumWebDriver );
//		seleniumWebDriver.windowMaximize();
//		seleniumWebDriver.open("/");
		
		SeleniumUtils.waitFor( 1000 );
		
		WebElement webElement = SeleniumUtils.findForComponentDisplayed( seleniumWebDriver, SeleniumUtils.SearchBy.ID, "j_username", 30000, 500 );
		
		Assert.assertNotNull( webElement );
		
		SeleniumUtils.waitFor( 2000 );
		
		String url = gui.getLink() + "/resources/theme/images/expression_logo_white_ext.png";
		
		RestClient restClient = new RestClient();
		
		ClientResponse<String> response = restClient.get( url );
		
		Assert.assertEquals( response.getStatus(), 200 );
		
		seleniumWebDriver.close();
		
	}	

	@AfterMethod
	public void tearDown() {

		if ( seleniumWebDriver_ != null ) {
			
			Reporter.log( "Close \"SeleniumWebDriver\" object.", true);
			seleniumWebDriver_.close();
			seleniumWebDriver_ = null;
		}
	}

}
