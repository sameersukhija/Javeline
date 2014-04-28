package com.lumata.expression.operators.testing.functional;

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
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm;

public class EFOGC_6_Support_IMM_Triggers_Events {

	private static final Logger logger = LoggerFactory.getLogger(EFOGC_6_Support_IMM_Triggers_Events.class);
	
	//private final String DEFAULT_USER = "marco_auchan";
	
	SeleniumWebDriver seleniumWebDriver; 
	Environment env;
	//List list;
	//User e4bUser;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
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
	public void check_imm_triggers() {

        //Assert.assertTrue( CampaignModelForm.open(seleniumWebDriver, 30000, 500) );
        //Assert.assertTrue( CampaignModelForm.create(seleniumWebDriver, 30000, 500) );

    }
	
}
