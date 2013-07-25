package com.lumata.expression.operators.testing;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.EnvLoadingType;
import com.lumata.common.testing.system.Security;
import com.lumata.expression.operators.security.Authorization;

public class EFOGC_6_Support_IMM_Triggers_Events {

	private static final Logger logger = LoggerFactory.getLogger(EFOGC_6_Support_IMM_Triggers_Events.class);
	
	private final String DEFAULT_USER = "marco_auchan";
	
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
	
		Assert.assertTrue(Authorization.login(seleniumWebDriver, env.getUserName( user ), env.getPassword( user ), 30000, 500));
		
	}
	
	/* 	Initialize Test Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	
	
	@Test()
    public void encrypt_decrypt_password() {

        String password = "password";

        String encrypted_password = Security.encrypt( password );

        String decrypted_password = Security.decrypt( encrypted_password );

        Assert.assertEquals( password, decrypted_password );

    }
	
}
