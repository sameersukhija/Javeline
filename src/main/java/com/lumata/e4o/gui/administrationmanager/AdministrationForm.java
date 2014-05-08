package com.lumata.e4o.gui.administrationmanager;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

public class AdministrationForm extends Form {

	private static final Logger logger = LoggerFactory.getLogger(AdministrationForm.class);
	
	public AdministrationForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}
	/*
	protected boolean select( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BarCaptionHomeAdministration") );
		
		WebElement adminButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BarCaptionHomeAdministration", timeout, interval);
		if( adminButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Administration DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to open the Administration DashBoard") );
		adminButton.click();
		
		return true;
		
	}
	
	public boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		return MenuBar.select( selenium, new SectionImpl<MenuBar.HomeSections, String, String>(MenuBar.HomeSections.ADMINSTRATION, MenuBar.HomeSections.ADMINSTRATION.section_id_prefix, MenuBar.HomeSections.ADMINSTRATION.section_type), timeout, interval );
						
	}
	*/
	protected Form open() throws FormException {
		
		return clickId( "gwt-debug-BarCaptionHomeAdministration" );
		
	}
	
}
