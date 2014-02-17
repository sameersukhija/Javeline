package com.lumata.expression.operators.gui.common;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class AngularFrame {

	private static final Logger logger = LoggerFactory.getLogger(AngularFrame.class);
	
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
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
