package com.lumata.e4o.gui.customercare;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class CustomerCareServicesForm  extends CustomerCareForm {

	public CustomerCareServicesForm ( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	protected CustomerCareServicesForm openServicesTab() throws FormException {
		
		clickId( "gwt-debug-BtnCCBarInfoServices" );
		
		return this;
		
	}
	
	@Override
	public CustomerCareServicesForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
	
	}
	
	public CustomerCareServicesForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
	
	}
	
	public CustomerCareServicesForm selectByIdAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByIdAndVisibleText( name, text );	
		
		return this;
		
	}
	
}
