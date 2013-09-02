package com.lumata.expression.operators.gui.security;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class Authorization {

	private static final Logger logger = LoggerFactory.getLogger(Authorization.class);
	
	public static boolean login( SeleniumWebDriver selenium, String user, String password, long timeout, long interval ) {
		
		logger.info( Log.LOADING.createMessage( "Browser" ) );
		
		selenium.open("/");
		
		WebElement loginForm = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputLoginUsername", timeout, interval);
		if( loginForm == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "User not logged" ) ); return false; }	
				
		logger.info( Log.PUTTING.createMessage( "User ( " + user + " )" ) );
		
		selenium.type("id=gwt-debug-InputLoginUsername", user );
		selenium.type("id=gwt-debug-InputLoginPassword", password);
		selenium.click("id=gwt-debug-ButtonLoginAuthentication");
		
		logger.info( Log.CHECKING.createMessage( "for login success" ) );
		
		WebElement homeInfo = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-FormHomeInfo", timeout, interval);
		if( homeInfo == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "User not logged" ) ); return false; }	
			
		return true;
		
	}
	
	public static void logout( SeleniumWebDriver selenium ) {
			selenium.close();
			logger.info( "Logout" );
	}
	
}
