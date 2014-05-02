package com.lumata.common.testing.plan;

import org.jboss.resteasy.client.ClientResponse;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.network.RestClient;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;

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
	
	/**
	 * Core test
	 * 
	 * @param browser
	 * @param environment
	 * @throws EnvironmentException
	 */
	private void loadSeleniumFirefoxDriver( String browser, String environment, String gui_server) throws NetworkEnvironmentException 
	{		
		Reporter.log("Init \"Environment\" object", true);
		env_ = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env_ , "NetworkEnvironment is null during init phase!");

		Reporter.log("Init \"Server\" object", true);
		Server gui = env_.getServer(gui_server);
		Assert.assertNotNull( gui, "Gui Server object is null!");
		
		Reporter.log("Startup \"SeleniumWebDriver\" object.", true);
		seleniumWebDriver_ = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		Assert.assertNotNull( seleniumWebDriver_ , "SeleniumWebDriver is null!");
		
		seleniumWebDriver_.windowMaximize();
		seleniumWebDriver_.open("/");		
	}	
	
	@Parameters({"browser", "environment"})
	@Test( enabled=false )
	public void loadSeleniumWebDriver_1( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment, @Optional("actrule") String gui_server ) throws Exception {		
				
		NetworkEnvironment env = new NetworkEnvironment( environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env );
		
		Server gui = env.getServer( gui_server );
		
		SeleniumWebDriver seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		Assert.assertNotNull( seleniumWebDriver );
		seleniumWebDriver.windowMaximize();
		seleniumWebDriver.open("/");
		
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
		}
	}

}
