package com.lumata.expression.operators.gui.catalogue;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.json.catalogue.TokenTypeCfg;

public class TokenTypeForm {

	private static final Logger logger = LoggerFactory.getLogger(CatalogueForm.class);
			
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
		
		return true;
		
	}
	
}
