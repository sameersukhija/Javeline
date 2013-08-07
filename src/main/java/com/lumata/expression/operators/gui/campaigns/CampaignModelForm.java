package com.lumata.expression.operators.gui.campaigns;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.model.json.CampaignModel;

public class CampaignModelForm extends CampaignsForm {

	private static final Logger logger = LoggerFactory.getLogger(CampaignModelForm.class);
	
	public enum CMEventType { 
		
		USSD, 
		CALL, 
		CAMPAIGN_END, 
		CAMPAIGN_FOLLOWING, 
		CAMPAIGN_START, 
		DATA, 
		LIFECYCLE, 
		MESSAGE, 
		OTHER_USAGE, 
		REVENUE;
		
		private static String eventID = "gwt-debug-ListCampaignModelCreationETType";
				
		public String getID() {
			
			return eventID + "-item" + ordinal();
			
		}
		
	};
	
	public enum CMAction { 
		
		COMMODITIES( true ), 
		TOKENS( true );
		
		private boolean value;
		private static String actionID = "gwt-debug-ListCampaignModelCreationEAType";
			
		CMAction( boolean value ) {
			
			this.value = value;
			
		}
		
		public boolean getValue() {
			
			return this.value;
			
		}
		
		public void setValue( boolean value ) {
			
			this.value = value;
			
		}
		
		public String getID() {
			
			int pos = ordinal();
			
			for( CMAction action : CMAction.values()) {
				  
				if( action.name().equals( this.name() ) ) { break; }
				else {
					
					if( !action.getValue() ) { pos--; }
					
				}
			
			}
			
			return actionID + "-item" + pos;
			
		}
		
	};
	
	public enum CMActionToken { 
		
		BRONZE, 
		GOLD,
		SILVER;
						
		public String getID() {
									
			return CMAction.TOKENS.getID() + "-item" + ordinal();
			
		}
		
	};
	
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		if( !CampaignModelForm.select(selenium, timeout, interval) ) { return false; }
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-InputCMCampaignModel") );
		
		WebElement campaignModelButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputCMCampaignModel", timeout, interval);
		if( campaignModelButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Campaign Model DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for open the Campaign Model DashBoard") );
		selenium.click( "id=gwt-debug-InputCMCampaignModel" );
		
		return true;
		
	}
	
	public static boolean create( SeleniumWebDriver selenium, CampaignModel cm, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelAdd") );
		
		WebElement campaignModelAdd = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelAdd", timeout, interval);
		if( campaignModelAdd == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to add a new Campaign Model") );
		campaignModelAdd.click();
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-InputCampaignModelCreationName") );
		
		WebElement campaignModelAddName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputCampaignModelCreationName", timeout, interval);
		if( campaignModelAddName == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
		
		logger.info( Log.PUTTING.createMessage( selenium.getTestName(), "Campaign Model Name") );
		
		campaignModelAddName.sendKeys( cm.getName() );
		
		logger.info( Log.GETTING.createMessage( selenium.getTestName(), "for events parameter") );
		JSONArray eventsList = cm.getEventsList();
		
		if( eventsList.length() > 0 ) {
			 
			logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationEventAdd") );
			
			WebElement campaignModelAddEvents = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationEventAdd", timeout, interval);
			if( campaignModelAddEvents == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
													   
			for( int i = 0; i < eventsList.length(); i++ ) {
				
				campaignModelAddEvents.click();
				
				logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-ListCampaignModelCreationETType") );
				
				WebElement campaignModelAddEventType = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-ListCampaignModelCreationETType", timeout, interval);
				if( campaignModelAddEventType == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
				
				campaignModelAddEventType.click();
				
				String eventType = cm.getEventType( i );
				
				if( eventType != null ) {
										
					selenium.click("id=" + CMEventType.valueOf( eventType ).getID() );
					
					String criteria = cm.getCriteria( i );
					
					
					
					logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationEAAdd") );
					
					WebElement campaignModelAddActionBtn = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationEAAdd", timeout, interval);
					if( campaignModelAddActionBtn == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
					
					campaignModelAddActionBtn.click();
										
					logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-ListCampaignModelCreationEAType") );
					
					WebElement campaignModelActionTypeList = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-ListCampaignModelCreationEAType", timeout, interval);
					if( campaignModelActionTypeList == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
					
					campaignModelActionTypeList.click();
					
					// Strange behavior. Need select the first option before to have all other correct ids after
					selenium.mouseOver( "id=gwt-debug-ListCampaignModelCreationEAType-item0" );					  
					selenium.click( "id=gwt-debug-ListCampaignModelCreationEAType-item0-item0" );
					
					String action = cm.getAction( i );
					
					String delimiter = "\\.";
					
					String[] actionLevel = action.split( delimiter );
					
					logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-ListCampaignModelCreationEAType") );
					
					campaignModelActionTypeList = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-ListCampaignModelCreationEAType", timeout, interval);
					if( campaignModelActionTypeList == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
					
					campaignModelActionTypeList.click();
					
					switch( CMAction.valueOf( actionLevel[ 0 ] ) ) {
					
						case COMMODITIES: { break; }
						case TOKENS: {
							selenium.mouseOver( CMAction.TOKENS.getID() );
							selenium.click( CMActionToken.valueOf( actionLevel[ 1 ] ).getID() );
							break;
						}
						
					}				
					
				}
				
			}
			
			logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationSave") );
			
			WebElement campaignModelSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationSave", timeout, interval);
			if( campaignModelSave == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
			
			campaignModelSave.click();			
			
		} else {
			
			logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationCancel") );
			
			WebElement campaignModelCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationCancel", timeout, interval);
			if( campaignModelCancel == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
			
			campaignModelCancel.click();			
						
		}
				
		return true;
		
	}
	
	public static ArrayList<CampaignModel> getCampaignModelList( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		ArrayList<CampaignModel> cmList = new ArrayList<CampaignModel>();
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-ListCampaignModel") );
		
		WebElement campaignModelList = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-ListCampaignModel", timeout, interval);
		if( campaignModelList == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Campaign Model List not found" ) ); return null; }	
		
		List<WebElement> availableCampaignModels = campaignModelList.findElements( By.className("contentRow") );
		
		logger.info( "################# >> " + availableCampaignModels.size() );
		
		for( int i = 0; i < availableCampaignModels.size(); i++ ) {
			
			List<WebElement> availableCampaignModelName = availableCampaignModels.get( i ).findElements( By.className("column_description") );
			logger.info( availableCampaignModelName.get( 0 ).getText() );
			
		}
		
		
		return cmList;
		
	}
	
}
