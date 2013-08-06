package com.lumata.expression.operators.gui.operations;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class Operations {

	private static final Logger logger = LoggerFactory.getLogger(Operations.class);
	
	protected static boolean select( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BarCaptionHomeOperations") );
		
		WebElement operationsButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BarCaptionHomeOperations", timeout, interval);
		if( operationsButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Operations DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to open the Operations DashBoard") );
		operationsButton.click();
		
		return true;
		
	}
	
}
