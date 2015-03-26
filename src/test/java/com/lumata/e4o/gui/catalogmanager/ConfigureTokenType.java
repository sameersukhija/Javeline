package com.lumata.e4o.gui.catalogmanager;

import java.lang.reflect.Method;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;

public class ConfigureTokenType {

	private static final Logger logger = LoggerFactory.getLogger( ConfigureTokenType.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 200;
	
	private final boolean testEnabled = true;
	
	private SeleniumWebDriver seleniumWebDriver;
	private NetworkEnvironment env;
			
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("tenant") String tenant, @Optional("superman") String user ) throws NetworkEnvironmentException, FormException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create Selenium WebDriver instance */
		Server gui = env.getServer( gui_server );
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		/** Login */
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).login( gui.getUser( user ) ).navigate() );
		
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Parameters({"tokenTypeList"})
	@Test( enabled=testEnabled, priority = 1 )
	public void configureTokeType( @Optional("tokenTypeList") String tokenTypeList ) throws FormException, JSONException, JSONSException {
		
		TokenTypeForm tokenTypeForm = new TokenTypeForm( seleniumWebDriver, new JSONTokenType( "input/catalogmanager/tokenTypes", tokenTypeList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( tokenTypeForm.openForm().addTokenTypes().close().navigate() );
		
	}
		
	@AfterClass
	public void end() throws FormException {
		
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).quit().navigate() );
		
	}
		
}
