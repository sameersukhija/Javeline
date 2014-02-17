package com.lumata.expression.operators.gui.catalogue;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.common.AngularFrame;
import com.lumata.expression.operators.gui.common.MenuBar;
import com.lumata.expression.operators.gui.common.SectionImpl;


public class OfferOptimisationForm {

	public enum OfferOptimisationSection { 
		
		RULES("Rules"), 
		TOKEN_TYPE("Token type");
		
		private String value;
		
		OfferOptimisationSection( String value ) {
			this.value = value;
		}
		
		public String value() { return this.value; }
		
	}
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogueForm.class);
		
	public static boolean open( SeleniumWebDriver selenium, OfferOptimisationSection section, long timeout, long interval ) {
		
		if( !CatalogueForm.open(selenium, timeout, interval) ) { return false; }
		
		if( !MenuBar.select( selenium, new SectionImpl<MenuBar.CatalogSections, String, String>(MenuBar.CatalogSections.OFFER_OPTIMISATION, MenuBar.CatalogSections.OFFER_OPTIMISATION.section_id_prefix, MenuBar.CatalogSections.OFFER_OPTIMISATION.section_type), timeout, interval ) ) { return false; }
		
		if( !AngularFrame.open( selenium, timeout, interval ) ) { return false; }
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for link=" + section.value() ) );
		
		WebElement sectionForm = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.LINK, section.value(), timeout, interval);
		if( sectionForm == null ) { return false; }
			
		sectionForm.click();
				
		return true;
		
	}
	
	public static boolean close( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		return AngularFrame.close( selenium, timeout, interval );
				
	}

}
