package com.lumata.expression.operators.gui.common;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class Buttons {

	private static final Logger logger = LoggerFactory.getLogger(Buttons.class);
		
	public enum Types { 
		
		BTN_ADD;
		
		//public String section_id_prefix = "gwt-debug-BarCaptionHome";
		//public SectionImpl.SectionNameType section_type = SectionImpl.SectionNameType.FIXED;
			
	};
			
	public static boolean click( SeleniumWebDriver selenium, IButton button, String containerClassName, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name=" + button.getButtonID()) );
		
		List<WebElement> buttonEl = SeleniumUtils.findListForComponentDisplayed(selenium, SeleniumUtils.SearchBy.CLASS_NAME, SeleniumUtils.SearchBy.NAME, containerClassName, button.getButtonID() );
		if( buttonEl == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the " + button.getButtonName() + " Button" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for open the " + button.getButtonName() + " Button" ) );
		buttonEl.get( 0 ).click();
		
		return true;
		
	}
	
}
