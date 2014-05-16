package com.lumata.e4o.gui.administrationmanager;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class LoginManagementAgencyForm extends LoginManagementForm {

	public LoginManagementAgencyForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}
	
	public LoginManagementAgencyForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-administration-loginManagement-agency" );
		
		return this;
		
	}	
	
	
	
}
