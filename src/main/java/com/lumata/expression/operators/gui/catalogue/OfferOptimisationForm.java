package com.lumata.expression.operators.gui.catalogue;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.json.catalogue.TokenTypeCfg;

public class OfferOptimisationForm extends CatalogueForm {

	private static final Logger logger = LoggerFactory.getLogger(CatalogueForm.class);
		
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		if( !CatalogueForm.select(selenium, timeout, interval) ) { return false; }
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for xpath=html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[3]/table/tbody/tr[1]/td/table/tbody/tr/td[6]/table/tbody/tr[2]/td[2]") );
		
		WebElement offerOptimisationForm = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[3]/table/tbody/tr[1]/td/table/tbody/tr/td[6]/table/tbody/tr[2]/td[2]", timeout, interval);
		if( offerOptimisationForm == null ) { return false; }
			
		offerOptimisationForm.click();
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for class_name=gwt-Frame") );
		
		WebElement angularFrame = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.CLASS_NAME, "gwt-Frame", timeout, interval);
		if( angularFrame == null ) { return false; }
		
		selenium.getWrappedDriver().switchTo().frame(angularFrame);
				
		return true;
		
	}
	
	public static boolean close( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for parent frame") );
		
		selenium.selectFrame("relative=top");
		
		return true;
		
	}

}
