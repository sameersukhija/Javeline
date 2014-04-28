package com.lumata.e4o.demo;

import java.lang.reflect.Method;

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
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.e4o.exceptions.CampaignException;
import com.lumata.e4o.exceptions.CampaignModelException;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.json.administration.JSONCommodities;
import com.lumata.expression.operators.gui.administration.CommoditiesForm;
import com.lumata.expression.operators.gui.campaigns.CampaignCreationForm;
import com.lumata.expression.operators.json.campaigns.CampaignCfg;

public class ConfigureCampaign {

	private static final Logger logger = LoggerFactory.getLogger( ConfigureCampaign.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 500;
	
	SeleniumWebDriver seleniumWebDriver;
	NetworkEnvironment env;
	Mysql mysqlGlobal;
	Mysql mysqlTenant;
			
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("actrule") String gui_server, @Optional("qa") String tenant, @Optional("superman") String user ) throws CommoditiesException, JSONSException, IOFileException, NetworkEnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create mysql connections with global and tenant database */
		mysqlGlobal = new Mysql( env.getDataSource( "global" ) );		
		mysqlTenant = new Mysql( env.getDataSource( tenant ) );
		
		/** Create Selenium WebDriver instance */
		Server gui = env.getServer( gui_server );
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		seleniumWebDriver.windowMaximize();
		
		/** Login */
		Assert.assertTrue(Authorization.login(seleniumWebDriver, gui.getUser( user ), TIMEOUT, ATTEMPT_TIMEOUT));
		
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Parameters({"tenant"})
	@Test(enabled=true, priority = 3 )
	public void setCommodities( @Optional("tenant") String tenant ) throws CommoditiesException, JSONSException, IOFileException, FormException {
		
		CommoditiesForm commoditiesForm = new CommoditiesForm( seleniumWebDriver, new JSONCommodities( "input/commodities", "commodities_list_1" ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( commoditiesForm.open()
							.addCommoditiesList()
							.navigate() 
		);
		
	}
	
	@Test( enabled = false, priority = 4 )
	public void loadCampaignModel() throws CampaignModelException, CommoditiesException, JSONSException, IOFileException, FormException {

//		CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver, new CampaignModelCfg( "input/campaigns", "campaign_model_list_1" ), TIMEOUT, ATTEMPT_TIMEOUT );
						
		//		Assert.assertTrue( campaignModelForm.open().create().navigate() );
		//Assert.assertTrue( CampaignModelForm.create(seleniumWebDriver, cm_bonus, TIMEOUT, ATTEMPT_TIMEOUT) );	
				
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
		
		mysqlGlobal.close();
		mysqlTenant.close();
		
	}
		
}
