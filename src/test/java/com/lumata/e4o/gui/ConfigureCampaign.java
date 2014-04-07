package com.lumata.e4o.gui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.configuration.ConfigurationDAO;
import com.lumata.expression.operators.exceptions.CampaignException;
import com.lumata.expression.operators.exceptions.CampaignModelException;
import com.lumata.expression.operators.exceptions.CommoditiesException;
import com.lumata.expression.operators.gui.administration.CommoditiesForm;
import com.lumata.expression.operators.gui.campaigns.CampaignCreationForm;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm;
import com.lumata.expression.operators.gui.security.Authorization;
import com.lumata.expression.operators.json.campaigns.CampaignCfg;
import com.lumata.expression.operators.json.campaigns.CampaignModelCfg;
import com.lumata.expression.operators.pojo.configuration.ConfigurationTypes;

public class ConfigureCampaign {

	private static final Logger logger = LoggerFactory.getLogger( ConfigureCampaign.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 500;
	
	SeleniumWebDriver seleniumWebDriver;
	Environment env;
	Mysql mysqlGlobal;
	Mysql mysqlTenant;
	JSONObject commodities;
		
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant, @Optional("superman") String user ) throws EnvironmentException, CommoditiesException, JSONSException, IOFileException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		// Create environment configuration
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		// Create mysql connections with global and tenant database
		mysqlGlobal = new Mysql( env.getDataSource( "global" ) );		
		mysqlTenant = new Mysql( env.getDataSource( tenant ) );
		
		// Load Commodities configuration to set
		//commodities = JSONUtils.loadJSONResource( "input/commodities", "external_bonus.json" );
		
		// Create Selenium WebDriver instance
		//seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowser( browser ), env.getLink() );
		//seleniumWebDriver.windowMaximize();
		
		// Login
		//Assert.assertTrue(Authorization.login(seleniumWebDriver, env.getUserName( user ), env.getPassword( user ), TIMEOUT, ATTEMPT_TIMEOUT));
		
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		//seleniumWebDriver.setTestName( method.getName() ); 	
	}
		
	@Parameters({"tenant"})
	@Test(enabled=false, priority = 1 )
	public void setRetryStrategy( @Optional("qa") String tenant ) {
		
		// No options
		Map<String, Object> options = new HashMap<String, Object>();
		
		// Insert Retry Strategy
		ConfigurationDAO asynchronousTenantINPlugin = new ConfigurationDAO( ConfigurationTypes.STANDARD_RETRY, options );
				
		if( !asynchronousTenantINPlugin.check( mysqlTenant ) ) { asynchronousTenantINPlugin.insert( mysqlTenant ); }
		
		Assert.assertTrue( asynchronousTenantINPlugin.check( mysqlTenant ) );		
	
    }
	
	@Parameters({"tenant"})
	@Test(enabled=false, priority = 2 )
	public void insertUnknownMSISDN( @Optional("qa") String tenant ) {

		// Unknown msisdn list
		ArrayList<String> subscribers = new ArrayList<String>();
		//subscribers.add( "331234560" );
		subscribers.add( "331234561" );
				
		Map<String, Object> options = new HashMap<String, Object>();
		options.put( "subscribers" , subscribers );
		
		// Insert unknown msisdn list
		ConfigurationDAO asynchronousGlobalINPlugin = new ConfigurationDAO( ConfigurationTypes.QA_UNKNOWN_MSISDN, options );
				
		asynchronousGlobalINPlugin.insert( mysqlGlobal );
		
		Assert.assertTrue( asynchronousGlobalINPlugin.check( mysqlGlobal ) );
			
    }
			
	@Parameters({"tenant"})
	@Test(enabled=false, priority = 3 )
	public void setCommodities( @Optional("qa") String tenant ) {
			
		Assert.assertTrue( CommoditiesForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );		
		Assert.assertTrue( CommoditiesForm.addCommodities(seleniumWebDriver, commodities, TIMEOUT, ATTEMPT_TIMEOUT) );
		
	}
	
	@Test( enabled = false, priority = 4 )
	public void loadCampaignModel() throws CampaignModelException {

		CampaignModelCfg cm_bonus = new CampaignModelCfg( "input/campaigns", "cm_bonus", IOLoadingType.RESOURCE );
				
		Assert.assertTrue( CampaignModelForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );
		Assert.assertTrue( CampaignModelForm.create(seleniumWebDriver, cm_bonus, TIMEOUT, ATTEMPT_TIMEOUT) );	
				
    }
	
	@Parameters({"tenant"})
	@Test(enabled=false, priority = 5 )
	public void setCampaigns( @Optional("qa") String tenant ) throws CampaignException {
			
		Assert.assertTrue( CampaignCreationForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );		
		Assert.assertTrue( CampaignCreationForm.create(seleniumWebDriver, new CampaignCfg( "input/campaigns", "campaign_cm_bonus", IOLoadingType.RESOURCE ), TIMEOUT, ATTEMPT_TIMEOUT) );
				
	}
	
	@AfterClass
	public void end() {
		// Logout
		//Authorization.logout(seleniumWebDriver);
	}
		
}
