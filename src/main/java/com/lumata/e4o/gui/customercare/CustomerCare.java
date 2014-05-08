package com.lumata.e4o.gui.customercare;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class CustomerCare {

	private static final Logger logger = LoggerFactory.getLogger(CustomerCare.class);
	
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
				
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BarCaptionHomeCustomerCare") );
		
		WebElement CCButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BarCaptionHomeCustomerCare", timeout, interval);
		if( CCButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Customer Care DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to open the Customer Care DashBoard") );
		CCButton.click();
		
		return true;
		
	}
	
}
