package com.lumata.expression.operators.testing.gui.configuration;

import java.lang.reflect.Method;

import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
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
import com.lumata.e4o.exceptions.CampaignException;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.exceptions.OfferException;
import com.lumata.e4o.exceptions.TokenTypeException;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.expression.operators.gui.catalogue.TokenTypeForm;
import com.lumata.expression.operators.gui.catalogue.OffersForm;
import com.lumata.expression.operators.json.catalogue.OfferCfg;
import com.lumata.expression.operators.json.catalogue.TokenTypeCfg;

public class ConfigureOffers {

	private static final Logger logger = LoggerFactory.getLogger( ConfigureOffers.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 500;
	
	SeleniumWebDriver seleniumWebDriver;
	Environment env;
	OfferCfg offerCfg;
	
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant, @Optional("superman") String user ) throws EnvironmentException, OfferException, JSONSException, IOFileException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		// Create environment configuration
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		// Load Offer configuration to set
		offerCfg = new OfferCfg( "input/catalogue/offers", "offer_configuration", IOFileUtils.IOLoadingType.RESOURCE );
		
		// Create Selenium WebDriver instance
		seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowser( browser ), env.getLink() );
		seleniumWebDriver.windowMaximize();
		//((JavascriptExecutor)seleniumWebDriver.getWrappedDriver()).executeScript("window.resizeTo(1280,1024);");
		
		// Login
		Assert.assertTrue(Authorization.login(seleniumWebDriver, env.getUserName( user ), env.getPassword( user ), TIMEOUT, ATTEMPT_TIMEOUT));
					
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Parameters({"tenant"})
	@Test(enabled=true, priority = 1)
	public void configureOffers( @Optional("qa") String tenant ) throws TokenTypeException {
		
		Assert.assertTrue( OffersForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );
		Assert.assertTrue( OffersForm.create(seleniumWebDriver, offerCfg, TIMEOUT, ATTEMPT_TIMEOUT) );
		
		System.out.println( offerCfg.toString() );
				
	}
	
	
	/*
	@Parameters({"tenant"})
	@Test(enabled=false, priority = 1)
	public void selectOfferOptimisation( @Optional("qa") String tenant ) throws TokenTypeException {
		
		TokenTypeCfg tokenType = new TokenTypeCfg( "input/catalogue/token_type", "token_type_a", IOLoadingType.RESOURCE );
		
		Assert.assertTrue( TokenTypeForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );
		//Assert.assertTrue( TokenTypeForm.selectTokenTypeTab(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );
		//Assert.assertTrue( TokenTypeForm.addTokenType(seleniumWebDriver, tokenType, TIMEOUT, ATTEMPT_TIMEOUT) );
		//Assert.assertTrue( TokenTypeForm.close(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );
		//Assert.assertTrue( CampaignCreationForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );
				
	}
	
	@Parameters({"tenant"})
	@Test(enabled=false, priority = 2)
	public void setTokenType( @Optional("qa") String tenant ) throws CampaignException {
			
		//Assert.assertTrue( CampaignCreationForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );
		
		//Assert.assertTrue( CampaignCreationForm.create(seleniumWebDriver, new CampaignCfg( "input/campaigns", "campaign_cm_bonus", IOLoadingType.RESOURCE ), TIMEOUT, ATTEMPT_TIMEOUT) );
			
	}
	*/
	
}
