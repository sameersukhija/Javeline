package com.lumata.expression.operators.gui.catalogue;

import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.catalogue.OffersForm.OfferErrorAction;
import com.lumata.expression.operators.gui.catalogue.OffersForm.OfferErrorActionType;
import com.lumata.expression.operators.json.catalogue.OfferCfg;
import com.lumata.expression.operators.json.catalogue.TokenTypeCfg;

public class TokenTypeForm {

	private static final Logger logger = LoggerFactory.getLogger(CatalogueForm.class);
		
	public enum TokenTypeErrorAction { 
		
		TOKEN_TYPE_ALREADY_EXISTS;
				
	};
	
	public enum TokenTypeErrorActionType { 
		
		RETURN_ERROR,
		ABORT,
		ADD_TIMESTAMP_TO_OFFER_NAME;
				
	};
	
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		return OfferOptimisationForm.open(selenium, OfferOptimisationForm.OfferOptimisationSection.TOKEN_TYPE, timeout, interval);
		
	}
	
	public static boolean addTokenType( SeleniumWebDriver selenium, TokenTypeCfg tokenType, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for link=Add") );
		
		WebElement addTokenTypeBtn = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.LINK, "Add", timeout, interval);
		if( addTokenTypeBtn == null ) { return false; }
			
		addTokenTypeBtn.click();
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = name") );
		
		WebElement tokenTypeName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "name", timeout, interval);
		if( tokenTypeName == null ) { return false; }
			
		tokenTypeName.sendKeys(tokenType.getName());
				
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = format") );
		
		WebElement tokenTypeFormat = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "format", timeout, interval);
		if( tokenTypeFormat == null ) { return false; }
			
		selenium.select( "name=format", "label=" + tokenType.getFormat() );
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = validityUnit") );
		
		WebElement tokenTypeValidityUnit = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "validityUnit", timeout, interval);
		if( tokenTypeValidityUnit == null ) { return false; }
			
		selenium.select( "name=validityUnit", "label=" + TokenTypeCfg.TokenTypeValidity.valueOf( tokenType.getValidityUnit() ) );

		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = validity") );
		
		WebElement tokenTypeValidity = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "validity", timeout, interval);
		if( tokenTypeValidity == null ) { return false; }
			
		tokenTypeValidity.sendKeys(tokenType.getValidity());

		if( tokenType.getUsageUnlimited() == true ) {
			
			logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id = usageUnlimited-1") );
			
			WebElement tokenTypeUsageUnlimited1 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "usageUnlimited-1", timeout, interval);
			if( tokenTypeUsageUnlimited1 == null ) { return false; }
				
			tokenTypeUsageUnlimited1.click();
		
		} else {
			
			logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id = usageUnlimited-0") );
			
			WebElement tokenTypeUsageUnlimited0 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "usageUnlimited-0", timeout, interval);
			if( tokenTypeUsageUnlimited0 == null ) { return false; }
				
			tokenTypeUsageUnlimited0.click();
	
			logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = usage") );
			
			WebElement tokenTypeUsage = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "usage", timeout, interval);
			if( tokenTypeUsage == null ) { return false; }
			
			tokenTypeUsage.clear();
			
			tokenTypeUsage.sendKeys( tokenType.getUsage() );
		
		}
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = btn-add") );
		
		WebElement tokenTypeSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "btn-add", timeout, interval);
		if( tokenTypeSave == null ) { return false; }
		tokenTypeSave.click();		
		
		return TokenTypeForm.manageErrorAction( selenium, tokenType, timeout, interval );
		
	}
	
	public static boolean manageErrorAction( SeleniumWebDriver selenium, TokenTypeCfg tokenType, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for error message") );
		
		selenium.selectFrame("relative=top");
		
		WebElement btnMessageError = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "//div[@class='gwt-DialogBox errorDialog']//button", 2000, 100);
		
		if( btnMessageError != null ) { 
			
			btnMessageError.click();
			
			WebElement angularFrame = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.CLASS_NAME, "gwt-Frame", timeout, interval);
			if( angularFrame == null ) { return false; }
			
			selenium.getWrappedDriver().switchTo().frame(angularFrame);
			
			JSONObject error_actions = tokenType.getErrorActions();
			
			if( error_actions == null ) {
				
				logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new token type ( Wrong json configuration )" ) );
				
				return false;
								
			} else {
				
				try {
					
					switch( TokenTypeErrorActionType.valueOf( error_actions.getString( TokenTypeErrorAction.TOKEN_TYPE_ALREADY_EXISTS.name() ) ) ) {
						
						case RETURN_ERROR:{
							
							logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new token type ( token type name already exist )" ) );
							
							return false;
							
						}
						case ADD_TIMESTAMP_TO_OFFER_NAME:{
							
							/*
							String name_with_timestamp = offerCfg.getOfferName() + "_" + String.valueOf( TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) );
							
							offerCfg.setOfferName( name_with_timestamp );
							
							OffersForm.setDefinition( selenium, offerCfg, timeout, interval );
							
							OffersForm.setActivation( selenium, offerCfg, timeout, interval );
							*/
							return true;
							
						}
						case ABORT:{
							
							logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name=btn-cancel") );
							
							WebElement tokenTypeCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "html/body/div[1]/div[2]/div/div/div[2]/a[1]", timeout, interval);
							if( tokenTypeCancel == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Abort token type creation" ) ); return false; }	
							tokenTypeCancel.click();
							
							return true;
															
						}
						
					}
				
				} catch( Exception e ) {}
				
			}
							
		}
	
		WebElement angularFrame = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.CLASS_NAME, "gwt-Frame", timeout, interval);
		if( angularFrame == null ) { return false; }
		
		selenium.getWrappedDriver().switchTo().frame(angularFrame);
		
		return true;
		
	}
	
}
