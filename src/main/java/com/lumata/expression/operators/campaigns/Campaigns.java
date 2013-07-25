package com.lumata.expression.operators.campaigns;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class Campaigns {

	private static final Logger logger = LoggerFactory.getLogger(Campaigns.class);
	
	protected static boolean select( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BarCaptionHomeCampaign") );
		
		WebElement campaignButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BarCaptionHomeCampaign", timeout, interval);
		if( campaignButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Campaign DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for open the Campaign DashBoard") );
		selenium.click( "id=gwt-debug-BarCaptionHomeCampaign" );
		
		return true;
		
	}
	
}
