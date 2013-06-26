package com.lumata.common.testing.plan;

import org.jboss.resteasy.client.ClientResponse;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.network.RestClient;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.EnvLoadingType;

public class TestSeleniumWebDriver {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestSeleniumWebDriver.class );
					
	@Parameters({"browser", "environment"})
	@Test()
	public void loadSeleniumWebDriver_1( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		
		Environment env = new Environment( environment, EnvLoadingType.RESOURCE );
		Assert.assertNotNull( env );
		
		SeleniumWebDriver seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowserProfile( browser ), env.getLink() );
		Assert.assertNotNull( seleniumWebDriver );
		seleniumWebDriver.windowMaximize();
		seleniumWebDriver.open("/");
		
		SeleniumUtils.waitFor( 1000 );
		
		WebElement webElement = SeleniumUtils.findForComponentDisplayed( seleniumWebDriver, SeleniumUtils.SearchBy.ID, "j_username", 30000, 500 );
		
		Assert.assertNotNull( webElement );
		
		SeleniumUtils.waitFor( 2000 );
		
		String url = env.getLink() + "/resources/theme/images/expression_logo_white_ext.png";
		
		ClientResponse<String> response = RestClient.get( url );
		
		Assert.assertEquals( response.getStatus(), 200 );
		
		seleniumWebDriver.close();
		
	}	

}
