package com.lumata.expression.operators.gui.catalogue;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class OfferOptimisationForm extends CatalogueForm {

	private static final Logger logger = LoggerFactory.getLogger(CatalogueForm.class);
	
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		if( !CatalogueForm.select(selenium, timeout, interval) ) { return false; }
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for xpath=html/body/div[4]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[3]/td/table/tbody/tr/td[1]/button") );
		
		WebElement digestError = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "html/body/div[4]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[3]/td/table/tbody/tr/td[1]/button", timeout, interval);
		if( digestError != null ) { digestError.click(); }
		
		//xpath=(//button[@type='button'])[293]
		/*
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-InputCMCampaignModel") );
		
		WebElement campaignModelButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputCMCampaignModel", timeout, interval);
		if( campaignModelButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Campaign Model DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for open the Campaign Model DashBoard") );
		selenium.click( "id=gwt-debug-InputCMCampaignModel" );
		*/
		return true;
		
	}
	
}
