package com.lumata.e4o.gui.customercare;


import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

public class CustomerCareForm  extends Form {

	public enum Tab {
		CustomerCare, 
		CustomerCare_Profile,
		CustomerCare_Service,
		CustomerCare_Bonus,
		CustomerCare_Campaign,
		CustomerCare_Purchase,
		CustomerCare_Token,
		CustomerCare_Loyalty,
		CustomerCare_History,
		CustomerCare_Script		  
	}
	
	public CustomerCareForm ( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	protected CustomerCareForm open() throws FormException {
		
		clickId( "gwt-debug-BarCaptionHomeCustomerCare" );
		
		return this;
		
	}
	
	public CustomerCareForm searchMsisdnByPhoneNumber( String prefix, String msisdn ) throws FormException {
		
		clickId( "gwt-debug-CheckCCPhoneNumber-input" ).
		selectByIdAndVisibleText( "gwt-debug-ListCCPrefix", prefix ).
		sendKeysById( "gwt-debug-InputCCSubscriberID", msisdn ).
		clickId( "gwt-debug-BtnCCSearch" );
				
		return this;
		
	}
	
	public CustomerCareForm searchMsisdnByIdentifier( String msisdn ) throws FormException {
		
		clickId( "gwt-debug-CheckCCIdentifier-input" ).
		sendKeysById( "gwt-debug-InputCCSubscriberID", msisdn ).
		clickId( "gwt-debug-BtnCCSearch" );
		
		return this;
		
	}
	
	@Override
	public CustomerCareForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
	
	}
	
	public CustomerCareForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
	
	}
	
	public CustomerCareForm selectByIdAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByIdAndVisibleText( name, text );	
		
		return this;
		
	}
	
}
