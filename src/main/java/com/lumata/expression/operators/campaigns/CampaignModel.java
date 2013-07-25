package com.lumata.expression.operators.campaigns;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class CampaignModel extends Campaigns {

	private static final Logger logger = LoggerFactory.getLogger(CampaignModel.class);
		
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		if( !CampaignModel.select(selenium, timeout, interval) ) { return false; }
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-InputCMCampaignModel") );
		
		WebElement campaignModelButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputCMCampaignModel", timeout, interval);
		if( campaignModelButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Campaign Model DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for open the Campaign Model DashBoard") );
		selenium.click( "id=gwt-debug-InputCMCampaignModel" );
		
		return true;
		
	}
	
	public static boolean create( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		// id=gwt-debug-BtnCampaignModelAdd
		// type id=gwt-debug-InputCampaignModelCreationName
		
		return true;
		
	}
	
}
