package com.lumata.e4o.gui.configuration;

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
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.SalesChannelsForm;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.gui.catalogmanager.RulesForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.json.gui.administrationmanager.JSONSalesChannels;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;

public class ConfigureCampaignToGiveTokens {

	private static final Logger logger = LoggerFactory.getLogger( ConfigureCampaignToGiveTokens.class );
	
	private final boolean testEnabled = true;
	
	private final boolean ADD_CHANNELS_ = true;
	private final boolean ADD_TOKEN_TYPES_ = true;
	private final boolean ADD_RULES_ = true;
	
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 50;
	
	SeleniumWebDriver seleniumWebDriver;
	NetworkEnvironment env;
	Mysql mysqlGlobal;
	Mysql mysqlTenant;
			
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_VM_NE") String environment, @Optional("actrule") String gui_server, @Optional("tenant") String tenant, @Optional("superman") String user ) throws CommoditiesException, JSONSException, IOFileException, NetworkEnvironmentException, FormException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create mysql connections with global and tenant database */
		//mysqlGlobal = new Mysql( env.getDataSource( "global" ) );		
		mysqlTenant = new Mysql( env.getDataSource( tenant ) );
		
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
	
	@Parameters({"salesChannelsList"})
	@Test( enabled=ADD_CHANNELS_, priority = 1 )
	public void configureSalesChannels( @Optional("salesChannelsList") String salesChannelsList ) throws FormException, JSONException, JSONSException {
		
		SalesChannelsForm salesChannelsForm = new SalesChannelsForm( seleniumWebDriver, new JSONSalesChannels( "input/administrationmanager/salesChannels", salesChannelsList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( salesChannelsForm.open().addSalesChannels().navigate() );
		
	}
	
	@Parameters({"tokenTypeList"})
	@Test( enabled=ADD_TOKEN_TYPES_, priority = 2 )
	public void configureTokeType( @Optional("tokenTypeList") String tokenTypeList ) throws FormException, JSONException, JSONSException {
		
		TokenTypeForm tokenTypeForm = new TokenTypeForm( seleniumWebDriver, new JSONTokenType( "input/catalogmanager/tokenTypes", tokenTypeList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( tokenTypeForm.openForm().addTokenTypes().close().navigate() );
		
	}
	
	@Parameters({"ruleList"})
	@Test( enabled=ADD_RULES_, priority = 3 )
	public void configureRules( @Optional("ruleList") String ruleList ) throws FormException, JSONException, JSONSException {
		
		RulesForm rulesForm = new RulesForm( seleniumWebDriver, new JSONRules( "input/catalogmanager/rules", ruleList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( rulesForm.openForm().addRules().close().navigate() );
		
	}

	@Parameters({"campaignModelList"})
	@Test( enabled = true, priority = 4 )
	public void configureCampaignModel( @Optional("campaignModelList") String campaignModelList ) throws JSONSException, FormException, JSONException {

		CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver, new JSONCampaignModel( "input/campaignmanager/campaignModels", "campaignModelList" ), TIMEOUT, ATTEMPT_TIMEOUT );
						
		Assert.assertTrue( campaignModelForm.openForm()
							.addCampaignModels()
							.navigate() 
		);		
				
    }
	
	@AfterClass
	public void end() {
		
		//Authorization.logout(seleniumWebDriver);
		
		mysqlGlobal.close();
		mysqlTenant.close();
		
	}
		
}
