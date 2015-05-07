package com.lumata.e4o.gui.customercare;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class CustomerCareBonusesForm  extends CustomerCareForm {

	public CustomerCareBonusesForm ( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	protected CustomerCareBonusesForm openBonusTab() throws FormException {
		
		clickId( "gwt-debug-BtnCCBarInfoBonuses" );
		
		return this;
		
	}
	
	@Override
	public CustomerCareBonusesForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
	
	}
	
	public CustomerCareBonusesForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
	
	}
	
	public CustomerCareBonusesForm selectByIdAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByIdAndVisibleText( name, text );	
		
		return this;
		
	}
	
}
