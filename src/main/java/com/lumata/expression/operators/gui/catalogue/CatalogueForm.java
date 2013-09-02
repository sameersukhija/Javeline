package com.lumata.expression.operators.gui.catalogue;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class CatalogueForm {

	private static final Logger logger = LoggerFactory.getLogger(CatalogueForm.class);
	
	protected static boolean select( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BarCaptionHomeCatalog") );
		
		WebElement catalogueButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BarCaptionHomeCatalog", timeout, interval);
		if( catalogueButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Catalogue DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for open the Catalogue DashBoard") );
		catalogueButton.click();
		
		return true;
		
	}

}
