package com.lumata.expression.operators.gui.campaigns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.json.campaigns.CampaignCfg;
import com.lumata.expression.operators.json.campaigns.CampaignModelCfg;

public class CampaignCreationForm extends CampaignsForm {

	private static final Logger logger = LoggerFactory.getLogger(CampaignCreationForm.class);
	
	
	public enum CampaignExecutionMode { 
		
		ModelBased("Model"), 
		Notification("Notification"), 
		RuleEngineBased("Rule Engine");
		
		private String value;
		
		public String getID() {
			
			StringBuilder id = new StringBuilder();
			
			id.append( "gwt-debug-Campaign " )
				.append( this.value )
				.append( " Exec Mode-input" );
				
			return id.toString();
			
		}
		
		CampaignExecutionMode( String value ) {
			
			this.value = value;
			
		}
		
	};
	
	/*
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
	*/
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		if( !CampaignCreationForm.select(selenium, timeout, interval) ) { return false; }
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-InputCMCampaignCreation") );
		
		WebElement campaignButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputCMCampaignCreation", timeout, interval);
		if( campaignButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Campaign DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for open the Campaign DashBoard") );
		campaignButton.click();
		
		return true;
		
	}
	
	public static boolean create( SeleniumWebDriver selenium, CampaignCfg campaignCfg, long timeout, long interval ) {
	
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Add Campaign") );
		
		WebElement campaignAdd = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Add Campaign", timeout, interval);
		if( campaignAdd == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to add a new Campaign") );
		campaignAdd.click();
		
		CampaignCreationForm.setDefinition( selenium, campaignCfg, timeout, interval );
		
		return true;
		
	}	
	
	public static boolean setDefinition( SeleniumWebDriver selenium, CampaignCfg campaignCfg, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for css=div.gwt-Hyperlink.selectableSC") );
		
		WebElement definitionSection = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.CSS, "div.gwt-Hyperlink.selectableSC", timeout, interval);
		if( definitionSection == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot define the new Campaign" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to define a new Campaign") );
		definitionSection.click();
				
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for " + CampaignExecutionMode.valueOf( campaignCfg.getExecutionMode() ).getID()) );
		
		WebElement executionMode = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, CampaignExecutionMode.valueOf( campaignCfg.getExecutionMode() ).getID(), timeout, interval);
		if( executionMode == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot set Campaign Execution Mode" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to set Campaign Execution Mode") );
		executionMode.click();
		
		
		
		return true;
		
	}
		
	// gwt-debug-Campaign Model Exec Mode-input
	// gwt-debug-Campaign Model Select in Campaign
	// gwt-debug-Campaign Name
	
	
	// gwt-debug-Campaign Configure Sample
	// gwt-debug-Campaign Eligibility Refresh
	// gwt-debug-Campaign Edition Save
	// gwt-debug-Campaign Edition Activate
	
	
	
	/*
	public static boolean create( SeleniumWebDriver selenium, CampaignModelCfg cm, long timeout, long interval ) {
		
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
	
	public static boolean isModel( SeleniumWebDriver selenium, ArrayList<CampaignModelCfg> cmList, CampaignModelCfg cm ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "if Campaign Model exists ( " + cm.getName() + " )") );
				
		for( int i = 0; i < cmList.size(); i++ ) {
			
			CampaignModelCfg cmElement = cmList.get( i );
			
			if( cmElement.getName().equals( cm.getName() ) ) { return true; }			
			
		}
		
		return false;
		
	}
	
	public static WebElement getCampaignModelTable( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-ListCampaignModel") );
		
		WebElement campaignModelTable = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-ListCampaignModel", timeout, interval);
		if( campaignModelTable == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Campaign Model Table not found" ) ); return null; }	
		
		return campaignModelTable;		
		
	}
	
	public static List<WebElement> getCampaignModelTableContent( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for elements contained in id=gwt-debug-ListCampaignModel") );
				
		List<WebElement> availableCampaignModels = SeleniumUtils.findListForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, SeleniumUtils.SearchBy.CLASS_NAME, "gwt-debug-ListCampaignModel", "contentRow", timeout, interval); 
		
		return availableCampaignModels;		
		
	}
	
	public static ArrayList<Map<String, Object>> getCampaignModelList( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		ArrayList<Map<String, Object>> cmList = new ArrayList<Map<String, Object>>();
				
		List<WebElement> availableCampaignModels = CampaignsDashboardForm.getCampaignModelTableContent( selenium, timeout, interval );
		
		logger.info( Log.PUTTING.createMessage( selenium.getTestName(), "all discovered elements contained in id=gwt-debug-ListCampaignModel") );
		
		for( int i = 0; i < availableCampaignModels.size(); i++ ) {
			
			Map<String, Object> cmModel = new HashMap<String, Object>();
						
			List<WebElement> availableCampaignModelName = SeleniumUtils.findListForComponentDisplayed( selenium, SeleniumUtils.SearchBy.CLASS_NAME, availableCampaignModels.get( i ), "column_description", timeout, interval );
			
			// Assume only the first element found is valid
			String name = availableCampaignModelName.get( 0 ).getText();
			cmModel.put( "name" , ( name != null ? name : "" ) );
			
			List<WebElement> availableCampaignModelDescription = SeleniumUtils.findListForComponentDisplayed( selenium, SeleniumUtils.SearchBy.CLASS_NAME, availableCampaignModels.get( i ), "column_longText", timeout, interval );
			
			// Assume only the first element found is valid
			String description = availableCampaignModelDescription.get( 0 ).getText();
			cmModel.put( "description" , ( description != null ? description : "" ) );
			
			List<WebElement> availableCampaignModelButtons = SeleniumUtils.findListForComponentDisplayed( selenium, SeleniumUtils.SearchBy.TAG_NAME, availableCampaignModels.get( i ), "button", timeout, interval );
			
			for( int j = 0; j < availableCampaignModelButtons.size(); j++ ) {
				
				cmModel.put( availableCampaignModelButtons.get( j ).getAttribute( "title" ).toLowerCase() , availableCampaignModelButtons.get( j ) );
				
			}
			
			cmList.add( cmModel );
				
		}
		
		return cmList;
		
	}	
	
	public static Map<String, Object> searchCampaignModel( SeleniumWebDriver selenium, ArrayList<Map<String, Object>> cmList, String cmModelName, long timeout, long interval ) {
				
		for( int i = 0; i < cmList.size(); i++ ) {
			
			Map<String, Object> cmModel = cmList.get( i );
			
			if( cmModel.get( "name" ).equals( cmModelName ) ) { return cmModel; }
			
		}
		
		return null;
		
	}

	public static boolean editCampaignModel( SeleniumWebDriver selenium, Map<String, Object> cmModel, long timeout, long interval ) {
		
		if( cmModel != null ) { ((WebElement)cmModel.get( "edit" )).click(); }
		else { return false; }
		
		return true;
		
	}
	
	public static boolean copyCampaignModel( SeleniumWebDriver selenium, Map<String, Object> cmModel, long timeout, long interval ) {
		
		if( cmModel != null ) { ((WebElement)cmModel.get( "copy" )).click(); }
		else { return false; }
		
		return true;
		
	}
	
	public static boolean deleteCampaignModel( SeleniumWebDriver selenium, Map<String, Object> cmModel, long timeout, long interval ) {
		
		if( cmModel != null ) { ((WebElement)cmModel.get( "delete" )).click(); }
		else { return false; }
		
		return true;
		
	}
	*/
}
