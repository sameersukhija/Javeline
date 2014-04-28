package com.lumata.expression.operators.gui.campaigns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

public class CampaignsForm extends Form {

	private static final Logger logger = LoggerFactory.getLogger(CampaignsForm.class);
	
	public CampaignsForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	protected Form open() throws FormException {
		
		return clickId( "gwt-debug-BarCaptionHomeCampaign" );
		
	}
	
}
