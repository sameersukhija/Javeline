package com.lumata.e4o.gui.catalogmanager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.catalogmanager.JSONRuleChannel;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;

public class RulesForm extends OfferOptimisationForm {

	private JSONRules ruleCfg;
	
    public enum ElementErrorActionType {

        RETURN_ERROR,
        ABORT_CANCEL,
        ADD_TIMESTAMP_TO_FIELD;

    }; 
    
	public RulesForm( SeleniumWebDriver selenium, JSONRules ruleCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.ruleCfg = ruleCfg;
		
	}	
	
	public RulesForm open() throws FormException {
		
		super.open( OfferOptimisationSection.RULES );
		
		return this;
		
	}
	
	public RulesForm addRules() throws FormException, JSONException {
		
		JSONArray rules = ruleCfg.getList();
		try {
		for( int ruleIndex = 0; ruleIndex < rules.length(); ruleIndex++ ) {
			
			ruleCfg.setRuleById( ruleIndex );
			
			if( ruleCfg.getEnabled() ) {
			
				clickLink( "Add" ).
				configureRule().
				saveRule().
				manageErrorAction( ruleCfg.getErrorActions().getString( "ELEMENT_ALREADY_EXISTS" ) );
				
			}
					
		}
		} catch( Exception e ) { System.out.println( e.getMessage() );}
		return this;
		
	}
	
	public RulesForm configureRule() throws FormException, JSONException {
				
		sendKeysByName( "name", ruleCfg.getName() ).
		sendKeysByXPath( "//textarea[@ng-model='ruleset.description']", ruleCfg.getDescription() ).
		selectByNameAndVisibleText( "tokenType", ruleCfg.getTokenType() ).
		multiselectByXPathAndVisibleText( "//select[@multiple]", ruleCfg.getRuleChannelsAsArray() ).
		configureRuleChannels().
		selectByNameAndVisibleText( "algorithm", ruleCfg.getOptimizationAlgorithm() ).
		sendKeysByName( "usage", ruleCfg.getMaximumNumberOfOffers() );
				
		if( ruleCfg.getKeepOffersConsistentAcrossMultipleRedraws() ) { clickId( "keepOffersConsistent-yes" ); }
		else { clickId( "keepOffersConsistent-no" ); }
		
		if( ruleCfg.getIncludePreviouslyAcceptedOffers() ) { clickId( "previousOffersDrawnIncluded-yes" ); }
		else { clickId( "previousOffersDrawnIncluded-no" ); }
					
		return this;
		
	}
	
	public RulesForm configureRuleChannels() throws JSONException, FormException {

		Map<String, JSONRuleChannel> ruleChannels = ruleCfg.getRuleChannels();
		
		for( String ruleChannelName : ruleChannels.keySet() ) {
						
			JSONRuleChannel ruleChannel = ruleCfg.getRuleChannelByName( ruleChannelName );
			
			if ( ruleChannel.getMandatory() ) {
				clickXPath("//div[@ng-repeat='channel in ruleset.ruleSetChannel']//div[contains(text(),'" + ruleChannel.getName() + "')]//ancestor::div[1]//input[@type='checkbox']");
			}
			
			if ( !ruleChannel.getUnlimited() ) {
				sendKeysByXPath("//div[@ng-repeat='channel in ruleset.ruleSetChannel']//div[contains(text(),'" + ruleChannel.getName() + "')]//ancestor::div[1]//input[@type='number']", ruleChannel.getMaxOffer() );
			}
			
		}
		
		return this;
		
	}
	
	public RulesForm manageErrorAction( String errorAction ) throws FormException {
		
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
				case ABORT_CANCEL: {  
										
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
	
	public RulesForm saveRule() throws FormException {
		
		super.clickXPath( "//a[@name='btn-add']" );
		
		return this;
		
	}
	
	public RulesForm cancelRule() throws FormException {
		
		super.clickXPath( "//a[@name='btn-cancel']" );
		
		return this;
		
	}
	
	@Override
	public RulesForm clickName( String name ) throws FormException {
		
		super.clickName( name );
		
		return this;
		
	}
	
	@Override
	public RulesForm clickXPath( String xpath ) throws FormException {
		
		super.clickXPath( xpath );
		
		return this;
		
	}
	
	@Override
	public RulesForm clickLink( String link ) throws FormException {
		
		super.clickLink( link );
		
		return this;
		
	}
	
	@Override
	public RulesForm sendKeysByName( String name, String text ) throws FormException {
		
		super.sendKeysByName( name, text );
		
		return this;
	
	}
	
	@Override
	public RulesForm sendKeysByXPath( String xpath, String text ) throws FormException {
		
		super.sendKeysByXPath( xpath, text );
		
		return this;
	
	}
	
	@Override
	public RulesForm sendKeysByLink( String link, String text ) throws FormException {
		
		super.sendKeysByLink( link, text );
		
		return this;
	
	}
	
	@Override
	public RulesForm selectByName( String name, String label ) throws FormException {
		
		super.selectByName( name, label );
		
		return this;
		
	}
	
	@Override
	public RulesForm clearByName( String xpath ) throws FormException {
		
		super.clearByName( xpath );
		
		return this;
		
	}
	
	@Override
	public RulesForm typeByName( String name, String text ) throws FormException {
		
		super.typeByName( name, text );
		
		return this;
	
	}
	
	@Override
	public RulesForm multiselectByXPathAndVisibleText( String xpath, JSONArray list ) throws FormException {
		
		super.multiselectByXPathAndVisibleText( xpath, list );	
		
		return this;
		
	}
	
	@Override
	public RulesForm selectByNameAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByNameAndVisibleText( name, text );	
		
		return this;
		
	}
	
	@Override
	public RulesForm openAngularFrame() throws FormException {
		
		super.openAngularFrame();	
		
		return this;
		
	}
	
	@Override
	public RulesForm closeAngularFrame() throws FormException {
		
		super.closeAngularFrame();	
		
		return this;
		
	}
	
}
