package com.lumata.e4o.gui.catalogmanager;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm.ElementErrorActionType;
import com.lumata.e4o.gui.common.Form;
import com.lumata.e4o.json.gui.catalogmanager.JSONRule;

public class RuleForm extends OfferOptimisationForm {

	private static final Logger logger = LoggerFactory.getLogger(RuleForm.class);

	private JSONRule ruleCfg;
	
    public enum ElementErrorActionType {

        RETURN_ERROR,
        ABORT,
        ADD_TIMESTAMP_TO_FIELD;

    }; 
    
	public RuleForm( SeleniumWebDriver selenium, JSONRule ruleCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.ruleCfg = ruleCfg;
		
	}	
	
	public RuleForm open() throws FormException {
		
		super.open( OfferOptimisationSection.RULES );
		
		return this;
		
	}
	
	public RuleForm addRules() throws FormException, JSONException {
		
		JSONArray rules = ruleCfg.getList();
		
		for( int ruleIndex = 0; ruleIndex < rules.length(); ruleIndex++ ) {
			
			ruleCfg.setRuleById( ruleIndex );
			
			if( ruleCfg.getEnabled() ) {
			
				clickLink( "Add" ).
				configureRule().
				saveRule().
				manageErrorAction( ruleCfg.getErrorActions().getString( "ELEMENT_ALREADY_EXISTS" ) );
				
			}
					
		}
		
		return this;
		
	}
	
	public RuleForm configureRule() throws FormException, JSONException {
				
		sendKeysByName( "name", ruleCfg.getName() ).
		sendKeysByXPath( "//textarea[@ng-model='ruleset.description']", ruleCfg.getDescription() ).
		selectByNameAndVisibleText( "tokenType", ruleCfg.getTokenType() ).
		multiselectByXPathAndVisibleText( "//select[@multiple]", ruleCfg.getChannels() ).
		selectByNameAndVisibleText( "algorithm", ruleCfg.getOptimizationAlgorithm() );
		
		if( ruleCfg.getKeepOffersConsistentAcrossMultipleRedraws() ) { clickId( "keepOffersConsistent-1" ); }
		else { clickId( "keepOffersConsistent-0" ); }
		
		if( ruleCfg.getIncludePreviouslyAcceptedOffers() ) { clickId( "previousOffersDrawnIncluded-1" ); }
		else { clickId( "previousOffersDrawnIncluded-0" ); }
		
		if( ruleCfg.getAllowDuplicateOffers() ) { clickId( "duplicatedOfferWithinSingleDrawEnabled-1" ); }
		else { clickId( "duplicatedOfferWithinSingleDrawEnabled-0" ); }

		if( ruleCfg.getUnlimitedOffers() ) { clickId( "numOfOffersToDrawUnlimited-1" ); }
		else { 
			
			clickId( "numOfOffersToDrawUnlimited-0" ). 
			typeByName( "usage", ruleCfg.getMaximumNumberOfOffers() );
		
		}
		
		return this;
		
	}
	
	public RuleForm manageErrorAction( String errorAction ) throws FormException {
		
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
										
					cancelRule();				
					
					break; 				
				}
				case ADD_TIMESTAMP_TO_FIELD: {  
					
					String name_with_timestamp = ruleCfg.getName() + "_" + String.valueOf( TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) );
					
					ruleCfg.setName( name_with_timestamp );					
					
					clearByName( "name" ).
					sendKeysByName( "name", ruleCfg.getName() ).
					saveRule();					
					
					break; 				
				}
			
			}
		
		} else { openAngularFrame(); }
		
		status = true;
		
		return this;
		
	}
	
	public RuleForm saveRule() throws FormException {
		
		super.clickName( "btn-add" );
		
		return this;
		
	}
	
	public RuleForm cancelRule() throws FormException {
		
		super.clickXPath( "//a[@label='actrule-button-cancel']" );
		
		return this;
		
	}
	
	@Override
	public RuleForm clickName( String name ) throws FormException {
		
		super.clickName( name );
		
		return this;
		
	}
	
	@Override
	public RuleForm clickXPath( String xpath ) throws FormException {
		
		super.clickXPath( xpath );
		
		return this;
		
	}
	
	@Override
	public RuleForm clickLink( String link ) throws FormException {
		
		super.clickLink( link );
		
		return this;
		
	}
	
	@Override
	public RuleForm sendKeysByName( String name, String text ) throws FormException {
		
		super.sendKeysByName( name, text );
		
		return this;
	
	}
	
	@Override
	public RuleForm sendKeysByXPath( String xpath, String text ) throws FormException {
		
		super.sendKeysByXPath( xpath, text );
		
		return this;
	
	}
	
	@Override
	public RuleForm sendKeysByLink( String link, String text ) throws FormException {
		
		super.sendKeysByLink( link, text );
		
		return this;
	
	}
	
	@Override
	public RuleForm selectByName( String name, String label ) throws FormException {
		
		super.selectByName( name, label );
		
		return this;
		
	}
	
	@Override
	public RuleForm clearByName( String xpath ) throws FormException {
		
		super.clearByName( xpath );
		
		return this;
		
	}
	
	@Override
	public RuleForm typeByName( String name, String text ) throws FormException {
		
		super.typeByName( name, text );
		
		return this;
	
	}
	
	@Override
	public RuleForm multiselectByXPathAndVisibleText( String xpath, JSONArray list ) throws FormException {
		
		super.multiselectByVisibleText( SearchBy.XPATH, xpath, list );	
		
		return this;
		
	}
	
	@Override
	public RuleForm selectByNameAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByNameAndVisibleText( name, text );	
		
		return this;
		
	}
	
	@Override
	public RuleForm openAngularFrame() throws FormException {
		
		super.openAngularFrame();	
		
		return this;
		
	}
	
	@Override
	public RuleForm closeAngularFrame() throws FormException {
		
		super.closeAngularFrame();	
		
		return this;
		
	}
	
}
