package com.lumata.e4o.gui.administrationmanager;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class LoginManagementGroupForm extends LoginManagementForm {

	public LoginManagementGroupForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}
	
	public LoginManagementGroupForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-administration-loginManagement-group" );
		
		return this;
		
	}	
	
	
	
}
