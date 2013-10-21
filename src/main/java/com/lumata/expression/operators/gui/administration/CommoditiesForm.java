package com.lumata.expression.operators.gui.administration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.json.administration.CommoditiesCfg;

public class CommoditiesForm extends AdministrationForm {

	private static final Logger logger = LoggerFactory.getLogger(CommoditiesForm.class);
	
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
				
		if( !CommoditiesForm.select(selenium, timeout, interval) ) { return false; }
				
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Enter Commodities") );
		
		WebElement commoditiesButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Enter Commodities", timeout, interval);
		if( commoditiesButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Commodities DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to open the Commodities DashBoard") );
		commoditiesButton.click();		
		
		return true;
		
	}
	
	public static boolean addCommodities( SeleniumWebDriver selenium, JSONObject commodities, long timeout, long interval ) {
		
		try {
			
			JSONArray commoditiesList = commodities.getJSONArray( "commodities" );
			
			for( int i = 0; i < commoditiesList.length(); i++ ) {
			
				logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Add Bonus") );
				
				WebElement addCommoditiesButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Add Bonus", timeout, interval);
				if( addCommoditiesButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Add Commodities Dialog" ) ); return false; }	
				
				logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to open the Add Commodities Dialog") );
				addCommoditiesButton.click();			
				
				CommoditiesCfg commodity = new CommoditiesCfg( commoditiesList.getJSONObject( i ) );
				
				if( CommoditiesForm.setCommodity( selenium, commodity, timeout, interval ) == false ) { return false; };
				
				if( CommoditiesForm.saveCommodities( selenium, timeout, interval ) == false ) { return false; };
				
			}
		
		} catch( JSONException e ) {
			
			logger.info( Log.FAILED.createMessage( selenium.getTestName(), "loading commodity list") );
			
		}	
		
		return true;
		
	}
		
	public static boolean setCommodity( SeleniumWebDriver selenium, CommoditiesCfg commodity, long timeout, long interval ) {
					
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus Type") );
		
		WebElement typeField = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus Type", timeout, interval);
		if( typeField == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Type field not found" ) ); return false; }	
				
		selenium.select( "id=gwt-debug-Bonus Type", "label=" + commodity.getType() );

		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus Name") );
		
		WebElement nameField = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus Name", timeout, interval);
		if( nameField == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Name field not found" ) ); return false; }	
				
		selenium.type( "id=gwt-debug-Bonus Name", commodity.getName() );
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus Account") );
		
		WebElement accountField = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus Account", timeout, interval);
		if( accountField == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Account field not found" ) ); return false; }	
		
		selenium.select( "id=gwt-debug-Bonus Account", "label=" + commodity.getAccount() );
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus Account Type") );
		
		WebElement accountTypeField = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus Account Type", timeout, interval);
		if( accountTypeField == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Account Type field not found" ) ); return false; }	
		
		selenium.select( "id=gwt-debug-Bonus Account Type", "label=" + commodity.getAccountType() );
				
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus Unit") );
		
		WebElement unitField = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus Unit", timeout, interval);
		if( unitField == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Unit field not found" ) ); return false; }	
		
		selenium.select( "id=gwt-debug-Bonus Unit", "label=" + commodity.getUnit() );
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus Default Validity Type") );
		
		WebElement defaultValidityTypeField = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus Default Validity Type", timeout, interval);
		if( defaultValidityTypeField == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Default Validity Type field not found" ) ); return false; }	
		
		selenium.select( "id=gwt-debug-Bonus Default Validity Type", "label=" + commodity.getDefaultValidityType() );
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus Default Period Type") );
		
		WebElement defaultPeriodTypeField = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus Default Period Type", timeout, interval);
		if( defaultPeriodTypeField == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Default Period Type field not found" ) ); return false; }	
		
		selenium.select( "id=gwt-debug-Bonus Default Period Type", "label=" + commodity.getDefaultPeriodType() );

		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus Default Quantity Period") );
		
		WebElement defaultQuantityPeriodField = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus Default Quantity Period", timeout, interval);
		if( defaultQuantityPeriodField == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Default Quantity Period field not found" ) ); return false; }	
		
		selenium.type( "id=gwt-debug-Bonus Default Quantity Period", commodity.getDefaultQuantityPeriod() );
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus Unitary Cost") );
		
		WebElement defaultUnitaryCostField = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus Unitary Cost", timeout, interval);
		if( defaultUnitaryCostField == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Unitary Cost field not found" ) ); return false; }	
		
		selenium.type( "id=gwt-debug-Bonus Unitary Cost", commodity.getUnitaryCost() );

		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus List Price") );
		
		WebElement defaultListPriceField = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus List Price", timeout, interval);
		if( defaultListPriceField == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "List Price field not found" ) ); return false; }	
		
		selenium.type( "id=gwt-debug-Bonus Default Quantity Period", commodity.getDefaultQuantityPeriod() );

		
		return true;
		
	}
	
	//id="gwt-debug-Bonus Save"
	public static boolean saveCommodities( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus Save") );
		
		WebElement saveBtn = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus Save", timeout, interval);
		if( saveBtn == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Save button not found" ) ); return false; }	
		
		saveBtn.click();
		
		try {
		
			if( saveBtn.isDisplayed() ) { 
				
				System.out.println( "button still visible" );
				
				logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Bonus Cancel") );
			
				WebElement cancelBtn = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Bonus Cancel", timeout, interval);
				if( cancelBtn == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cancel button not found" ) ); return false; }
			
				cancelBtn.click();
				
			}
		
		} catch( StaleElementReferenceException e ) {
			
			System.out.println( "button not visible" );
			
		}	
		
		return true;
		
	}
	
}
