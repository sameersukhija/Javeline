package com.lumata.e4o.gui.customercare;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class CustomerCarePurchasesForm  extends CustomerCareForm {

	public CustomerCarePurchasesForm ( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	protected CustomerCarePurchasesForm openPurchasesTab() throws FormException {
		
		clickId( "gwt-debug-BtnCCBarInfoPurchases" );
		
		return this;
		
	}
	
	@Override
	public CustomerCarePurchasesForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
	
	}
	
	public CustomerCarePurchasesForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
	
	}
	
	public CustomerCarePurchasesForm selectByIdAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByIdAndVisibleText( name, text );	
		
		return this;
		
	}
	
}
