package com.lumata.e4o.gui.security;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.common.testing.system.Security;
import com.lumata.common.testing.system.User;

/**
 * This object provides static methods to handle :<br>
 * <li> login
 * <li> license
 * <li> logout
 */
public class Authorization {

	private static final Logger logger = LoggerFactory.getLogger(Authorization.class);
	
	public static boolean login( SeleniumWebDriver selenium, User user, long timeout, long interval ) {
		
		return Authorization.login( selenium, user.getUsername(), Security.decrypt( user.getPassword() ), timeout, interval );
		
	}
	
	public static boolean login( SeleniumWebDriver selenium, String user, String password, long timeout, long interval ) {
		
		logger.info( Log.LOADING.createMessage( "Browser" ) );

		WebElement loginForm = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputLoginUsername", timeout, interval);
		if( loginForm == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "User not logged" ) ); return false; }	
				
		logger.info( Log.PUTTING.createMessage( "User ( " + user + " )" ) );
		
		WebElement el = null;
		
		el = SeleniumUtils.findForComponentDisplayed(selenium, SearchBy.ID, "gwt-debug-InputLoginUsername");
		el.sendKeys(user);

		el = SeleniumUtils.findForComponentDisplayed(selenium, SearchBy.ID, "gwt-debug-InputLoginPassword");
		el.sendKeys(password);
		
		el = SeleniumUtils.findForComponentDisplayed(selenium, SearchBy.ID, "gwt-debug-ButtonLoginAuthentication");
		el.click();
		
		logger.info( Log.CHECKING.createMessage( "for login success" ) );
		
		WebElement homeInfo = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-FormHomeInfo", timeout, interval);
		if( homeInfo == null ) {			
			logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "User not logged" ) ); 
			return false; 
		}	
		
		Authorization.closeLicenseDialog( selenium, 1000, 100 );
		
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
	
	/**
	 * Click on logout and accept if alert popup is displayed
	 * 
	 * @param selenium
	 */
	public static void logout( SeleniumWebDriver selenium ) {
		
		logger.info( "Logout" );
		
		SeleniumUtils.findForComponentDisplayed( selenium, SeleniumUtils.SearchBy.XPATH, "//button[@title='Logout']").click();
		
		Alert confirmLogout = null;
		 
		try {
			confirmLogout = selenium.getWrappedDriver().switchTo().alert();
		    	
			if ( confirmLogout != null )
				confirmLogout.accept();
		} catch (NoAlertPresentException e) {
		  
			// nothing to do
		}
	}
	
}
