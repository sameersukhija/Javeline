package com.lumata.e4o.gui.loyaltymanager;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;
import com.lumata.e4o.json.gui.loyaltymanager.LoyaltyManageCfg;

public class LoyaltyManagerForm extends Form {
	
	/**
	 * 
	 * @param selenium
	 * @param setupLoyaltiesManagement 
	 * @param timeout
	 * @param interval
	 */
	public LoyaltyManagerForm( SeleniumWebDriver selenium, LoyaltyManageCfg setupLoyaltiesManagement, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	/**
	 * 
	 * @return
	 * @throws FormException
	 */
	protected Form open() throws FormException {
		
		return clickId( "gwt-debug-actrule-loyalty" );
	}

}