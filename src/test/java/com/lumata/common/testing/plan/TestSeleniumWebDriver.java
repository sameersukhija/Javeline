package com.lumata.common.testing.plan;

import org.jboss.resteasy.client.ClientResponse;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.network.RestClient;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;

@Test( enabled = false )
public class TestSeleniumWebDriver {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestSeleniumWebDriver.class );
					
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

}
