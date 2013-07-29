package com.lumata.expression.operators.operations;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class TrafficGenerator extends Operations {

	private static final Logger logger = LoggerFactory.getLogger(TrafficGenerator.class);
	
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
				
		if( !TrafficGenerator.select(selenium, timeout, interval) ) { return false; }
				
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-InputOPTrafficGenerator") );
		
		WebElement trafficGeneratorButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputOPTrafficGenerator", timeout, interval);
		if( trafficGeneratorButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Traffic Generator DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to open the Traffic Generator DashBoard") );
		trafficGeneratorButton.click();		
		
		return true;
		
	}
	
	public static boolean setEvent( SeleniumWebDriver selenium, long timeout, long interval ) {
					
		JSONObject event = new JSONObject();
		
		try {
			
			event.put( "msisdn", "333669393643");
			//event.put(  )
		
		} catch( JSONException e ) {}
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-InputOPTrafficGenerator") );
		
		WebElement trafficGeneratorButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputOPTrafficGenerator", timeout, interval);
		if( trafficGeneratorButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Traffic Generator DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to open the Traffic Generator DashBoard") );
		trafficGeneratorButton.click();		
		
		// gwt-debug-BtnGUITrafficGeneratorEventExecute
		// gwt-debug-InputGUITrafficGeneratorEventSubID
		// gwt-debug-ListGUITrafficGeneratorEventClass
		// gwt-debug-BtnGUITrafficGeneratorEventParametersAdd
		// gwt-debug-InputGUITrafficGeneratorEventParameterName
		// gwt-debug-InputGUITrafficGeneratorEventParameterValue
		// gwt-debug-FormGUITrafficGeneratorEventParameterApply
		// assertAlert  Event generated!
		
		return true;
		
	}
	
}
