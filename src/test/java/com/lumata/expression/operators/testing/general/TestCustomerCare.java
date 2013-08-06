package com.lumata.expression.operators.testing.general;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.EnvLoadingType;
import com.lumata.expression.operators.gui.customercare.CustomerCare;
import com.lumata.expression.operators.gui.security.Authorization;


public class TestCustomerCare {

	private static final Logger logger = LoggerFactory.getLogger(TestCustomerCare.class);
	
	SeleniumWebDriver seleniumWebDriver; 
	Environment env;
	//List list;
	//User e4bUser;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, EnvLoadingType.RESOURCE );
		
		seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowser( browser ), env.getLink() );
		seleniumWebDriver.windowMaximize();
		
		Assert.assertTrue(Authorization.login(seleniumWebDriver, env.getUserName( user ), env.getPassword( user ), 60000, 500));
		
	}
	
	/* 	Initialize Test Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Test()
	public void open_customer_care() {

		Assert.assertTrue( CustomerCare.open(seleniumWebDriver, 30000, 500) );		
	
    }
	
}
