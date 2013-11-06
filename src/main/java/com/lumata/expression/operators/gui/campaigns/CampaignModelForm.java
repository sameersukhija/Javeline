package com.lumata.expression.operators.gui.campaigns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.json.campaigns.CampaignModelCfg;

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
		private static String actionValueID = "gwt-debug-TextCampaignModelCreationEAValue";
		private static String actionTypeID = "gwt-debug-ListCampaignModelCreationEAType";
		private static String actionUnitID = "gwt-debug-ListCampaignModelCreationEAUnit";
					
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
			
			return actionTypeID + "-item" + pos;
			
		}
		
		public String getActionValueID() {
			
			return actionValueID;
			
		}
		
		public String getActionTypeID() {
			
			return actionTypeID;
			
		}
		
		public String getActionUnitID() {
			
			return actionUnitID;
			
		}
		
	};
	
	public enum CMActionCommodities { 
		
		POINTS;
						
		public String getID() {
									
			return CMAction.COMMODITIES.getID() + "-item" + ordinal();
			
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
	
	public enum CMErrorAction { 
		
		MODEL_ALREADY_EXISTS;
				
	};
	
	public enum CMErrorActionType { 
		
		RETURN_ERROR,
		ABORT,
		ADD_TIMESTAMP_TO_MODEL_NAME;
				
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
					
					String actionName = cm.getActionName( i );
					
					String delimiter = "\\.";
					
					String[] actionLevel = actionName.split( delimiter );
					
					logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-ListCampaignModelCreationEAType") );
					
					campaignModelActionTypeList = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-ListCampaignModelCreationEAType", timeout, interval);
					if( campaignModelActionTypeList == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
					
					campaignModelActionTypeList.click();
					
					switch( CMAction.valueOf( actionLevel[ 0 ] ) ) {
					
						case COMMODITIES: { 
							selenium.mouseOver( CMAction.COMMODITIES.getID() );
							selenium.click( CMActionCommodities.valueOf( actionLevel[ 1 ] ).getID() );
							selenium.type( CMAction.COMMODITIES.getActionValueID(), cm.getActionValue( i ) );
							selenium.select( CMAction.COMMODITIES.getActionUnitID(), cm.getActionOption( i ) );
							break; 
						}
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
			
			return CampaignModelForm.manageErrorAction( selenium, cm, timeout, interval );
			
						
		} else {
			
			logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationCancel") );
			
			WebElement campaignModelCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationCancel", timeout, interval);
			if( campaignModelCancel == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
			
			campaignModelCancel.click();			
						
		}
				
		return true;
		
	}
	
	public static boolean manageErrorAction( SeleniumWebDriver selenium, CampaignModelCfg cm, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for error message") );
		
		WebElement messageError = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "/html/body/div[6]/div/div", timeout, interval);
		
		if( messageError != null ) { 
			
			JSONObject error_actions = cm.getErrorActions();
			
			if( error_actions == null ) {
				
				logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model ( Wrong json configuration )" ) );
				
				return false;
								
			} else {
				
				try {
					
					if( messageError.getText().equals( "Model name already exist" ) && !error_actions.isNull( CMErrorAction.MODEL_ALREADY_EXISTS.name() ) ) {
						
						switch( CMErrorActionType.valueOf( error_actions.getString( CMErrorAction.MODEL_ALREADY_EXISTS.name() ) ) ) {
						
							case RETURN_ERROR:{
								
								logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model ( Model name already exist )" ) );
								
								return false;
								
							}
							case ADD_TIMESTAMP_TO_MODEL_NAME:{
								
								cm.setName( cm.getName() + "_" + String.valueOf( TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) ) );
								
								logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-InputCampaignModelCreationName") );
								
								WebElement campaignModelAddName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputCampaignModelCreationName", timeout, interval);
								if( campaignModelAddName == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
								
								logger.info( Log.PUTTING.createMessage( selenium.getTestName(), "Campaign Model Name") );
								
								campaignModelAddName.sendKeys( cm.getName() );
								
								logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationSave") );
								
								WebElement campaignModelSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationSave", timeout, interval);
								if( campaignModelSave == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
								
								campaignModelSave.click();
								
								logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for new error message") );
								
								WebElement newMessageError = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "/html/body/div[6]/div/div", timeout, interval);
								
								if( newMessageError != null ) {
									
									logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model ( " + newMessageError.getText() + " )" ) );
									
									return false;
									
								} else {
									
									return true;
									
								}
								
							}
							case ABORT:{
								
								logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationCancel") );
								
								WebElement campaignModelCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationCancel", timeout, interval);
								if( campaignModelCancel == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model" ) ); return false; }	
								
								campaignModelCancel.click();
								
								return true;
																
							}
							
						}
						
					}  
					
				} catch( Exception e ) {}
				
			}
			
			logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign Model ( " + messageError.getText() + " )" ) ); 
			
			return false; 
			
		
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
				
		List<WebElement> availableCampaignModels = CampaignModelForm.getCampaignModelTableContent( selenium, timeout, interval );
		
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
	
}
