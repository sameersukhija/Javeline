package com.lumata.e4o.gui.security;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;

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
 * <li> double login
 */
public class Authorization extends Form {

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
			
		maximize().		
		waitVisibleElementById( "gwt-debug-FormLogin" ).
		clickId( "gwt-debug-InputLoginUsername" ).
		sendKeysById( "gwt-debug-InputLoginUsername", user ).
		sendKeysById( "gwt-debug-InputLoginPassword", password ).
		clickId( "gwt-debug-ButtonLoginAuthentication" );
		
		// force a new login
		doubleSession(true);
		
		Boolean clickedDialog = closeLicenseDialog();
		
		// if a click occurred for license warning :
		// element "gwt-debug-FormHomeInfo" is already disappeared
		if ( !clickedDialog) { clickId( "gwt-debug-FormHomeInfo" ); }
		
		return this;

	}
	
	/**
	 * This methods looks for a popup related double session and handle it according <b>forceLogin</b> :<br>
	 * <li> if TRUE, it forces new login ( another session can be dropped by DUT ) 
	 * <li> if FALSE, it does not force a new login and throws an exception
	 * 
	 * @throws FormException 
	 */
	private void doubleSession(Boolean forceLogin) throws FormException {
		
		Alert confirmForceLogin = null;
		 
		try {
			
			confirmForceLogin = selenium.selectAlert();
		    	
			if ( confirmForceLogin != null ) {
				
				if ( forceLogin ) { 
					
					confirmForceLogin.accept();
				
				} else {
					
					throw new FormException( getClass().getSimpleName() + " finds a \"double session\" and CANNOT force login!");
				
				}
				
			}
			
		} catch (NoAlertPresentException e) {
			// nothing to do
		}
	}
	
	
	/**
	 * This method executes :<br>
	 * <li> it searches for license warning box and close it
	 * <li> if a click event occurs returns this information
	 * 
	 * @return Boolean information if a click event occurs
	 * 
	 * @throws FormException
	 */
	private Boolean closeLicenseDialog() throws FormException {
		
		Boolean closedLicenseDialog = null;
						
		try {
			
			long timeout = getTimeout();
			
			setTimeout( 1000 ).			
			clickXPath( "//div[@class='gwt-DialogBox errorDialog']//button" ).
			setTimeout( timeout );
						
			closedLicenseDialog = Boolean.TRUE;
			
		} catch( NoSuchElementException | FormException e ) {
			
			if ( e instanceof FormException )
				if ( e.getMessage().contains("errorDialog") ) {
					// do nothing
					
					closedLicenseDialog = Boolean.FALSE;
				}
				else // something happened
					throw e;
		}
		
		
		
		return closedLicenseDialog;
	}
	
	public boolean refresh() throws FormException {
		
		long expiredTime = 0;
		
		while ( expiredTime <= timeout ) {
			
			expiredTime = expiredTime + interval;
			
			try {
			
				searchById( "gwt-debug-InputLoginUsername", 1000, 50 );
			
			} catch( FormException fe ) {}
			
			if( status ) { return true; }
			
			selenium.refresh();
			
		}
		
		return false;
		
	}
	
	/**
	* Click on logout and accept if alert popup is displayed
	*
	* @param selenium
	* @throws FormException
	*/
	public Authorization logout() throws FormException {
		
		clickId( "gwt-debug-Logout E4O" );
			
		try {
			
			Alert confirmLogout = selenium.selectAlert();
			
			if ( confirmLogout != null ) { confirmLogout.accept(); }
		
		} catch (NoAlertPresentException e) {
			
			status = true;
		
		}
		
		return this;
	
	}

	/**
	* Logout and close browser
	*
	* @param selenium
	* @throws FormException
	*/
	public Authorization quit() throws FormException {
	
		logout();
	
		selenium.close();
	
		return this;
	
	}

}
