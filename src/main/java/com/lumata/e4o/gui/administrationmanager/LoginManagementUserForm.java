package com.lumata.e4o.gui.administrationmanager;

import com.lumata.common.testing.selenium.SeleniumWebDriver;

import com.lumata.e4o.exceptions.FormException;

public class LoginManagementUserForm extends LoginManagementForm {

	public LoginManagementUserForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}
	
	public LoginManagementUserForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-administration-loginManagement" );
		
		return this;
		
	}
	
}
