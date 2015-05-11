package com.lumata.e4o.gui.customercare;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class CustomerCareProfileForm  extends CustomerCareForm {

	public CustomerCareProfileForm ( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	protected CustomerCareProfileForm openProfileTab() throws FormException {
		
		clickId( "gwt-debug-BtnCCBarInfoProfile" );
		
		return this;
		
	}
	
	@Override
	public CustomerCareProfileForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
	
	}
	
	public CustomerCareProfileForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
	
	}
	
	public CustomerCareProfileForm selectByIdAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByIdAndVisibleText( name, text );	
		
		return this;
		
	}
	
}
