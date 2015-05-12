package com.lumata.e4o.gui.catalogmanager;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.openqa.selenium.By;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.AngularFrame;

public class OfferOptimisationForm extends CatalogueManagerForm {

	//private static final Logger logger = LoggerFactory.getLogger(OfferOptimisationForm.class);
	
	public enum OfferOptimisationSection { 
		
		RULES("Rules"), 
		TOKEN_TYPE("Token type");
		
		private String value;
		
		OfferOptimisationSection( String value ) {
			this.value = value;
		}
		
		public String value() { return this.value; }
		
	}
	
	public OfferOptimisationForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
				
	}
	
	public OfferOptimisationForm open( OfferOptimisationSection offerOptimisationSection ) throws FormException {
		
		super.openForm();
		
		waitVisibleElementById( "gwt-debug-actrule-catalog-offerOptimisation" );
		
		clickId( "gwt-debug-actrule-catalog-offerOptimisation" ).		
		
		openAngularFrame();
		
		try { Thread.sleep( 3000 );  } catch( Exception e) {}
		waitVisibleElement(By.linkText(offerOptimisationSection.value()));
		clickLink( offerOptimisationSection.value() );
					
		return this;
		
	}

	public OfferOptimisationForm close() throws FormException {
		
		closeAngularFrame();
			
		return this;
		
	}
	
	protected OfferOptimisationForm openAngularFrame() throws FormException {
		
		AngularFrame.getInstance( selenium, timeout, interval ).open();
		
		return this;
		
	}
	
	protected OfferOptimisationForm closeAngularFrame() throws FormException {
		
		AngularFrame.getInstance( selenium, timeout, interval ).close();
		
		return this;
		
	}
	
	@Override
	public OfferOptimisationForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
		
	}
	
	@Override
	public OfferOptimisationForm clickLink( String link ) throws FormException {
		
		super.clickLink( link );
		
		return this;
		
	}
	
}
