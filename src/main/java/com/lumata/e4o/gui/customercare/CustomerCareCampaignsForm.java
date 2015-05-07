package com.lumata.e4o.gui.customercare;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class CustomerCareCampaignsForm  extends CustomerCareForm {

	public CustomerCareCampaignsForm ( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	protected CustomerCareCampaignsForm openCampaignTab() throws FormException {
		
		clickId( "gwt-debug-BtnCCBarInfoCampaigns" );
		
		return this;
		
	}
	
	@Override
	public CustomerCareCampaignsForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
	
	}
	
	public CustomerCareCampaignsForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
	
	}
	
	public CustomerCareCampaignsForm selectByIdAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByIdAndVisibleText( name, text );	
		
		return this;
		
	}
	
}
