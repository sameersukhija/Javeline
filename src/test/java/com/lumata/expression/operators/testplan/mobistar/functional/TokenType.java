package com.lumata.expression.operators.testplan.mobistar.functional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.gui.catalogue.TokenTypeForm;
import com.lumata.expression.operators.gui.security.Authorization;

public class TokenType {

	private static final Logger logger = LoggerFactory.getLogger( TokenType.class );
	private int TIMEOUT = 600000;
	private int ATTEMPT_INTERVAL = 500;
	
	SeleniumWebDriver seleniumWebDriver;
	Environment env;
	
	@Parameters({"browser", "environment", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
		seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowser( browser ), env.getLink() );
		seleniumWebDriver.windowMaximize();
		
		Assert.assertTrue(Authorization.login(seleniumWebDriver, env.getUserName( user ), env.getPassword( user ), 60000, 500));
		
	}
	
	@Parameters({"qa"})
	@Test
	public void createTokenType( @Optional("qa") String tenant1 ) {
		
		Assert.assertTrue( TokenTypeForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_INTERVAL) );
		
	}
	
}
