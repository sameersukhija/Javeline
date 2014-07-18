package com.lumata.e4o.gui.catalogmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules.JSONChannel;

public class RulesForm extends OfferOptimisationForm {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(RulesForm.class);
	
	/**
	 * 
	 */
	private JSONRules ruleCfg;
	
    /**
     * 
     * @param selenium
     * @param ruleCfg
     * @param timeout
     * @param interval
     */
	public RulesForm( SeleniumWebDriver selenium, JSONRules ruleCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.ruleCfg = ruleCfg;
	}	
	
	/**
	 * 
	 */
	public RulesForm open() throws FormException {
		
		super.open( OfferOptimisationSection.RULES );
		
		return this;
	}
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public RulesForm addRules() throws FormException, JSONSException {
		
		Integer numRules = ruleCfg.getList().size();
		
		for( int ruleIndex = 0; ruleIndex < numRules; ruleIndex++ ) {
			
			ruleCfg.setCurrentElementById( ruleIndex );
			
			if( ruleCfg.getCurrentElement().getEnabled() ) {
			
				clickLink( "Add" ).
				configureRule().
				saveRule(); //.
				manageErrorAction( ruleCfg.getErrorActions().getAction(ElementErrorConditionType.ELEMENT_AREADY_EXISTS) );	
			}	
		}
		
		return this;
	}
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public RulesForm configureRule() throws FormException, JSONSException {
				
		List<JSONChannel> channels = ruleCfg.getChannels();
		List<String> chNames = new ArrayList<String>();
		
		for (JSONChannel ch : channels)
			chNames.add(ch.getName());
		
		sendKeysByName( "name", ruleCfg.getName() ).
		sendKeysByXPath( "//textarea[@ng-model='ruleset.description']", ruleCfg.getDescription() ).
		selectByNameAndVisibleText( "tokenType", ruleCfg.getTokenType() ).
		multiselectByXPathAndVisibleText( "//select[@multiple]", chNames );
		
		/**
		 * custom channels configuration
		 */
		for (JSONChannel ch : channels) {
			
			//div[@ng-repeat='channel in ruleset.ruleSetChannel']//div[contains(text(),'Ch A')]//ancestor::div[1]//input[@type='checkbox']
			if ( ch.isMandatory() )
				clickXPath("//div[@ng-repeat='channel in ruleset.ruleSetChannel']//div[contains(text(),'"+ch.getName()+"')]//ancestor::div[1]//input[@type='checkbox']");

			if ( !ch.isUnlimited() ) {
				
				Integer max = ch.getMaxOffers();
				
				//div[@ng-repeat='channel in ruleset.ruleSetChannel']//div[contains(text(),'Ch A')]//ancestor::div[1]//input[@type='number']
				sendKeysByXPath("//div[@ng-repeat='channel in ruleset.ruleSetChannel']//div[contains(text(),'"+ch.getName()+"')]//ancestor::div[1]//input[@type='number']", max.toString());				
			}
		}
		
		selectByNameAndVisibleText( "algorithm", ruleCfg.getOptimizationAlgorithm() );
		
		if( ruleCfg.getKeepOffersConsistentAcrossMultipleRedraws() ) 
			clickForAlternativeId( "keepOffersConsistent-1", "keepOffersConsistent-yes");
		else 
			clickForAlternativeId( "keepOffersConsistent-0", "keepOffersConsistent-no");

		if( ruleCfg.getKeepOffersConsistentAcrossMultipleRedraws() ) 
			clickForAlternativeId( "previousOffersDrawnIncluded-1", "previousOffersDrawnIncluded-yes");
		else 
			clickForAlternativeId( "previousOffersDrawnIncluded-0", "previousOffersDrawnIncluded-no");		
		
		WebElement maxOfferElem = selenium.getWrappedDriver().findElement(By.xpath("//input[@ng-model='ruleset.numOfOffersToDraw']"));
		maxOfferElem.clear();
		maxOfferElem.sendKeys(ruleCfg.getMaximumNumberOfOffers().toString());
		
		return this;		
	}
	
	/**
	 * This method describes the different render situation for some id into application logic.
	 * 
	 * It tries to match the first id that found into provided sequence.
	 * 
	 * @param idStrings
	 * @throws FormException
	 */
	private void clickForAlternativeId(String... idStrings ) throws FormException {
		
		for (String singleIdString : idStrings) {
			
				if ( selenium.getWrappedDriver().findElements(By.id(singleIdString)).size() > 0 ) {
				
				logger.debug("Find the \""+singleIdString+"\"");
				
				clickId( singleIdString );
				
				break;
			}
		}
	}
	
	/**
	 * 
	 * @param errorAction
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public RulesForm manageErrorAction( ElementErrorActionType errorAction ) throws FormException, JSONSException {
		
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		closeAngularFrame();
		
		try {
		
			searchByXPath( "//div[@class='gwt-DialogBox errorDialog']", 2000, 50 );
		
		} catch( FormException fe ) {
			
			// no error to manage
			
		}
			
		if( status ) {
		
			clickXPath( "//div[@class='gwt-DialogBox errorDialog']//button" ).
			openAngularFrame();
			
			switch( errorAction ) {
			
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
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 */
	public RulesForm saveRule() throws FormException {
		
		super.clickName( "btn-add" );
		
		return this;
	}
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 */
	public RulesForm cancelRule() throws FormException {
		
		super.clickXPath( "//a[@label='actrule-button-cancel']" );
		
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

	/**
	 * 
	 * @param xpath
	 * @param list
	 * @return
	 * @throws FormException
	 */
	public RulesForm multiselectByXPathAndVisibleText( String xpath, List<String> list ) throws FormException {
		
		lastWebElement = search( SearchBy.XPATH, xpath );
		
		Select available = new Select( lastWebElement );
		
		for (String string : list)
			available.selectByVisibleText( string );
		
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
