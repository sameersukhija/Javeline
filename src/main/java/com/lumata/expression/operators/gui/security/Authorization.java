package com.lumata.expression.operators.gui.security;

import org.openqa.selenium.NoSuchElementException;
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
		
		Authorization.closeLicenseDialog( selenium, timeout, interval );
		
		return true;
		
	}
	
	public static boolean closeLicenseDialog( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( "for close license message button" ) );
		
		try {
		
			WebElement closeDialogBtn = SeleniumUtils.findForComponentDisplayed( selenium, SeleniumUtils.SearchBy.XPATH, "html/body/div[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[3]/td/table/tbody/tr/td/button", timeout, interval );
												
			if( closeDialogBtn == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "The user cannot close license dialog" ) ); return false; }
		
			closeDialogBtn.click();
			
		} catch( NoSuchElementException e ) {}
		
		return true;
		
	}
	
	public static void logout( SeleniumWebDriver selenium ) {
			selenium.close();
			logger.info( "Logout" );
	}
	
}
