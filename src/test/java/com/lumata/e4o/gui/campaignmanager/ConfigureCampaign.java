package com.lumata.e4o.gui.campaignmanager;

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

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.exceptions.CampaignException;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaigns;

public class ConfigureCampaign {

	private static final Logger logger = LoggerFactory.getLogger( ConfigureCampaign.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 50;
	
	SeleniumWebDriver seleniumWebDriver;
	NetworkEnvironment env;
	Mysql mysqlTenant;
			
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("actrule") String gui_server, @Optional("qa") String tenant, @Optional("superman") String user ) throws CommoditiesException, JSONSException, IOFileException, NetworkEnvironmentException, FormException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create mysql connections with global and tenant database */
		mysqlTenant = new Mysql( env.getDataSource( tenant ) );
		
		/** Create Selenium WebDriver instance */
		Server gui = env.getServer( gui_server );
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		/** Login */
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).login( gui.getUser( user ) ).navigate() );
		
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession( Method method ) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Parameters({"tenant"})
	@Test(enabled=true, priority = 1 )
	public void loadCampaigns() throws CampaignException, JSONSException, FormException, JSONException {
		
		CampaignsForm campaignForm = new CampaignsForm( seleniumWebDriver, new JSONCampaigns( "input/campaignmanager/campaigns", "campaignCMTemplate" ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		campaignForm.open().addCampaigns();
		
		
		//Assert.assertTrue( CampaignCreationForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );		
		//Assert.assertTrue( CampaignCreationForm.create(seleniumWebDriver, new CampaignCfg( "input/campaigns", "campaign_cm_bonus", IOLoadingType.RESOURCE ), TIMEOUT, ATTEMPT_TIMEOUT) );
				
	}
	
	@AfterClass
	public void end() {
		
		mysqlTenant.close();
		
	}
		
}
