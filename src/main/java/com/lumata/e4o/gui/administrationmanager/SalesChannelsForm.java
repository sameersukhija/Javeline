package com.lumata.e4o.gui.administrationmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class SalesChannelsForm {

	private static final Logger logger = LoggerFactory.getLogger(SalesChannelsForm.class);
	
	public static boolean open( SeleniumWebDriver selenium, /*SalsChannelsSection section,*/ long timeout, long interval ) {
	
		//if( !AdministrationForm.open(selenium, timeout, interval) ) { return false; }
		
		return true;
		
	}
	
}
