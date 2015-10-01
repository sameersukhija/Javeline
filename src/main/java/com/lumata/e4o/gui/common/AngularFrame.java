package com.lumata.e4o.gui.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class AngularFrame extends Form {
	
	private static final Logger logger = LoggerFactory.getLogger(AngularFrame.class);
	
	public AngularFrame( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
				
	}	
	
	public static AngularFrame getInstance( SeleniumWebDriver selenium, long timeout, long interval ) {
		return new AngularFrame( selenium, timeout, interval );
	}
	
	public AngularFrame open() throws FormException {
		
		switchToFrameByName( "angularIframe')]",timeout,interval);
		
		return this;
		
	}
	
	public AngularFrame openByXPath(String xpath) throws FormException {
		
		switchToFrameByXPath( xpath,timeout,interval);
		
		return this;
		
	}
	
	public AngularFrame close() throws FormException {
		
		switchToDefaultContent();
		
		return this;
		
	}
	
}
