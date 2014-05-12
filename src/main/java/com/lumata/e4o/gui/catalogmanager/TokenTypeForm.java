package com.lumata.e4o.gui.catalogmanager;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;

public class TokenTypeForm extends OfferOptimisationForm {

	private static final Logger logger = LoggerFactory.getLogger( TokenTypeForm.class);
	
	private JSONTokenType tokenTypeCfg;
	
    public enum ElementErrorActionType {

        RETURN_ERROR,
        ABORT,
        ADD_TIMESTAMP_TO_FIELD;

    }; 
	
	public TokenTypeForm( SeleniumWebDriver selenium, JSONTokenType tokenTypeCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.tokenTypeCfg = tokenTypeCfg;
		
	}	
	
	public TokenTypeForm open() throws FormException {
		
		super.open( OfferOptimisationSection.TOKEN_TYPE );
		
		return this;
		
	}

	public TokenTypeForm addTokenTypes() throws FormException, JSONException {
		
		JSONArray tokenTypes = tokenTypeCfg.getList();
		
		for( int tokenTypeIndex = 0; tokenTypeIndex < tokenTypes.length(); tokenTypeIndex++ ) {
			
			tokenTypeCfg.setTokenTypeById( tokenTypeIndex );
			
			if( tokenTypeCfg.getEnabled() ) {
			
				clickLink( "Add" ).
				configureTokenType().
				saveTokenType().
				manageErrorAction( tokenTypeCfg.getErrorActions().getString( "ELEMENT_ALREADY_EXISTS" ) );
				
			}
					
		}
		
		return this;
		
	}
	
	public TokenTypeForm configureTokenType() throws FormException {
		
		sendKeysByName( "name", tokenTypeCfg.getName() ).
		sendKeysByXPath( "//textarea[@ng-model='tokenType.description']", tokenTypeCfg.getDescription() ).
		sendKeysByXPath( "//input[@ng-model='tokenType.imageUrl']", tokenTypeCfg.getImageUrl() ).
		selectByNameAndVisibleText( "format", tokenTypeCfg.getFormat() ).
		selectByNameAndVisibleText( "validityUnit", tokenTypeCfg.getValidityUnit() ).
		sendKeysByName( "validity", tokenTypeCfg.getValidity() );
				
		if( tokenTypeCfg.getUsageUnlimited() == true ) {
			
			clickId( "usageUnlimited-1" ); 
		
		} else {
			
			clickId( "usageUnlimited-0" ).
			clearByName( "usage" ).
			sendKeysByName( "usage", tokenTypeCfg.getUsage() );
			
		}
				
		return this;
		
	}
	
	public TokenTypeForm manageErrorAction( String errorAction ) throws FormException {
		
		closeAngularFrame();
		
		try {
		
			searchByXPath( "//div[@class='gwt-DialogBox errorDialog']", 2000, 50 );
		
		} catch( FormException fe ) {
			
			// no error to manage
			
		}
			
		if( status ) {
		
			clickXPath( "//div[@class='gwt-DialogBox errorDialog']//button" ).
			openAngularFrame();
			
			switch( ElementErrorActionType.valueOf( errorAction ) ) {
			
				case RETURN_ERROR: {  
					
					throw new FormException( "Error in the form navigation" );
									
				}
				case ABORT: {  
										
					cancelTokenType();				
					
					break; 				
				}
				case ADD_TIMESTAMP_TO_FIELD: {  
					
					String name_with_timestamp = tokenTypeCfg.getName() + "_" + String.valueOf( TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) );
					
					tokenTypeCfg.setName( name_with_timestamp );					
					
					clearByName( "name" ).
					sendKeysByName( "name", tokenTypeCfg.getName() ).
					saveTokenType();					
					
					break; 				
				}
			
			}
		
		} else { openAngularFrame(); }
		
		return this;
		
	}
	
	public TokenTypeForm saveTokenType() throws FormException {
		
		super.clickName( "btn-add" );
		
		return this;
		
	}
	
	public TokenTypeForm cancelTokenType() throws FormException {
		
		super.clickXPath( "//a[@label='actrule-button-cancel']" );
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm clickName( String name ) throws FormException {
		
		super.clickName( name );
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm clickXPath( String xpath ) throws FormException {
		
		super.clickXPath( xpath );
		
		return this;
		
	}
	
	
	@Override
	public TokenTypeForm clickLink( String link ) throws FormException {
		
		super.clickLink( link );
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm sendKeysByName( String name, String text ) throws FormException {
		
		super.sendKeysByName( name, text );
		
		return this;
	
	}
	
	@Override
	public TokenTypeForm sendKeysByXPath( String xpath, String text ) throws FormException {
		
		super.sendKeysByXPath( xpath, text );
		
		return this;
	
	}
	
	@Override
	public TokenTypeForm sendKeysByLink( String link, String text ) throws FormException {
		
		super.sendKeysByLink( link, text );
		
		return this;
	
	}
	
	@Override
	public TokenTypeForm selectByName( String name, String label ) throws FormException {
		
		super.selectByName( name, label );
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm clearByName( String xpath ) throws FormException {
		
		super.clearByName( xpath );
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm typeByName( String name, String text ) throws FormException {
		
		super.typeByName( name, text );
		
		return this;
	
	}
	
	@Override
	public TokenTypeForm selectByNameAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByNameAndVisibleText( name, text );	
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm openAngularFrame() throws FormException {
		
		super.openAngularFrame();	
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm closeAngularFrame() throws FormException {
		
		super.closeAngularFrame();	
		
		return this;
		
	}


}
