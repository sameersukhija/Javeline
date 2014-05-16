package com.lumata.e4o.gui.administrationmanager;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class LoginManagementForm extends AdministrationForm {

	public enum DefaultGroups {
		
		backoffice("backoffice"),
		customercare("customercare"),
		customercare_bonus("customercare_bonus"),
		customercare_campaign("customercare_campaign"),
		customercare_catalog("customercare_catalog"),
		dsi("dsi"),
		loginmanagement("loginmanagement"),
		marketing_campaign_loyalty("marketing_campaign&loyalty");
		
		private String value;
		
		DefaultGroups( String value ) {
			this.value = value;
		}
		
		public String value() { return this.value; }
		
	}
	
	public enum GroupAccessLevel { 
		MANAGER, EDITOR, READER 
	}
	
	public enum GroupProperties {
		isRemovable,
		canBeSelectedInGroupList,
		canUsersBeAdded,
		hasAgencies;
	}	
	
	public LoginManagementForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}
	
	public LoginManagementForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-administration-loginManagement" );
		
		return this;
		
	}	
	
}
