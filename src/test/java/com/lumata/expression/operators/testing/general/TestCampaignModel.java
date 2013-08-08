package com.lumata.expression.operators.testing.general;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
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
import com.lumata.expression.operators.exceptions.CampaignModelException;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm;
import com.lumata.expression.operators.gui.security.Authorization;
import com.lumata.expression.operators.model.json.CampaignModel;
import com.lumata.expression.operators.model.json.CampaignModel.CMLoadingType;


public class TestCampaignModel {

	private static final Logger logger = LoggerFactory.getLogger(TestCampaignModel.class);
	private int TIMEOUT = 600000;
	private int ATTEMPT_INTERVAL = 500;
	
	
	SeleniumWebDriver seleniumWebDriver; 
	Environment env;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, EnvLoadingType.RESOURCE );
		
		seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowser( browser ), env.getLink() );
		seleniumWebDriver.windowMaximize();
		
		Assert.assertTrue(Authorization.login(seleniumWebDriver, env.getUserName( user ), env.getPassword( user ), TIMEOUT, ATTEMPT_INTERVAL));
			
	}
	
	/* 	Initialize Test Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}	
	
	@Test( enabled = true )
	public void checkCampaignModelList() throws CampaignModelException {
		
		Assert.assertTrue( CampaignModelForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_INTERVAL) );
		
		ArrayList<Map<String, Object>> cmList = CampaignModelForm.getCampaignModelList(seleniumWebDriver, TIMEOUT, ATTEMPT_INTERVAL); 
		
		logger.info( "######### " + String.valueOf( cmList.size() ) );
		
		Map<String, Object> cmModel = cmList.get( 1 );
		
		((WebElement)cmModel.get( "edit" )).click();
		
		/*
		Assert.assertNotNull( cmList );
		
		for( int i = 0; i < cmList.size(); i++ ) {
			
			CampaignModel cm = cmList.get( i );
			
			logger.info( cm.getName() );
			
		}
		
		CampaignModel cmExists = new CampaignModel( CampaignModel.getBasicCampaignModel() );
		cmExists.setName( "CModelTokenGold" );
		
		Assert.assertTrue( CampaignModelForm.isModel( seleniumWebDriver, cmList, cmExists ) );
		
		cmExists.setName( "CModelTokenGoldNotExist" );
		
		Assert.assertFalse( CampaignModelForm.isModel( seleniumWebDriver, cmList, cmExists ) );
		*/
		
		
    }
	
	@Test( enabled = false )
	public void loadCampaignModel() throws CampaignModelException {

		CampaignModel cm_token_gold = new CampaignModel( "input/campaign_models", "cm_token_gold", CMLoadingType.RESOURCE );
		CampaignModel cm_token_silver = new CampaignModel( "input/campaign_models", "cm_token_silver", CMLoadingType.RESOURCE );
		CampaignModel cm_token_bronze = new CampaignModel( "input/campaign_models", "cm_token_bronze", CMLoadingType.RESOURCE );
				
		Assert.assertTrue( CampaignModelForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_INTERVAL) );
		Assert.assertTrue( CampaignModelForm.create(seleniumWebDriver, cm_token_gold, TIMEOUT, ATTEMPT_INTERVAL) );
		Assert.assertTrue( CampaignModelForm.create(seleniumWebDriver, cm_token_silver, TIMEOUT, ATTEMPT_INTERVAL) );
		Assert.assertTrue( CampaignModelForm.create(seleniumWebDriver, cm_token_bronze, TIMEOUT, ATTEMPT_INTERVAL) );		
				
    }
	
}
