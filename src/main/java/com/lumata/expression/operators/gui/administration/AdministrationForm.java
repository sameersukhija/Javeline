package com.lumata.expression.operators.gui.administration;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class AdministrationForm {

	private static final Logger logger = LoggerFactory.getLogger(AdministrationForm.class);
	
	protected static boolean select( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BarCaptionHomeAdministration") );
		
		WebElement adminButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BarCaptionHomeAdministration", timeout, interval);
		if( adminButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Administration DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to open the Administration DashBoard") );
		adminButton.click();
		
		return true;
		
	}
	
}
