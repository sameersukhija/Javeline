package com.lumata.e4o.gui.security;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Security;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

/**
 * This object provides static methods to handle :<br>
 * <li> login
 * <li> license
 * <li> logout
 */
public class Authorization extends Form {

	private static final Logger logger = LoggerFactory.getLogger(Authorization.class);
	
	public Authorization( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}
	
	public static Authorization getInstance( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		return new Authorization( selenium, timeout, interval );
	
	}
	
	public Authorization login( User user ) throws FormException {	
		
		return this.login( user.getUsername(), Security.decrypt( user.getPassword() ) );
		
	}
	
	public Authorization login( String user, String password ) throws FormException {
		
		clickId( "gwt-debug-InputLoginUsername" ).
		sendKeysById( "gwt-debug-InputLoginUsername", user ).
		sendKeysById( "gwt-debug-InputLoginPassword", password ).
		clickId( "gwt-debug-ButtonLoginAuthentication" ).
		clickId( "gwt-debug-FormHomeInfo" );
		
		//closeLicenseDialog();
		
		return this;
		
	}
	
	public Authorization closeLicenseDialog() throws FormException {
		
		try {
		
			clickXPath( "html/body/div[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[3]/td/table/tbody/tr/td/button" );
			
		} catch( NoSuchElementException e ) {}
		
		return this;
		
	}
	
	/**
	 * Click on logout and accept if alert popup is displayed
	 * 
	 * @param selenium
	 * @throws FormException 
	 */
	public Authorization logout() throws FormException {
		
		searchByXPath( "//button[@title='Logout']" );
		
		Alert confirmLogout = null;
		 
		try {
			
			confirmLogout = selenium.getWrappedDriver().switchTo().alert();
		    	
			if ( confirmLogout != null ) { confirmLogout.accept(); }
			
		} catch (NoAlertPresentException e) {
			// nothing to do
		}
		
		return this;
		
	}

}
