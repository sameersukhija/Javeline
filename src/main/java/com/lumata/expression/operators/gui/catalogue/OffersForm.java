package com.lumata.expression.operators.gui.catalogue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm.CMErrorAction;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm.CMErrorActionType;
import com.lumata.expression.operators.gui.common.ButtonImpl;
import com.lumata.expression.operators.gui.common.Buttons;
import com.lumata.expression.operators.gui.common.MenuBar;
import com.lumata.expression.operators.gui.common.SectionImpl;
import com.lumata.expression.operators.json.campaigns.CampaignCfg;
import com.lumata.expression.operators.json.campaigns.CampaignModelCfg;

public class OffersForm extends CatalogueForm {

	private static final Logger logger = LoggerFactory.getLogger(OffersForm.class);

	public enum OfferCreationSection {
		
		DEFINITION("Definition"),
		OFFER_CONTENT("Offer Content"),
		PRICES("Prices"),
		NOTIFICATION("Notification"),
		ELIGIBILITY_CRITERIA("Eligibility Criteria"),
		AVAILABILITY("Availability"),
		ACTIVATION("Activation");
		
		private String value;
		
		public String getJsonID() {
			
			return this.value.replaceAll( " ", "_" ).toLowerCase();
			
		}
		
		OfferCreationSection( String value ) {
			
			this.value = value;
			
		}
		
	} 
	
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		if( !CatalogueForm.open(selenium, timeout, interval) ) { return false; }
		
		if( !MenuBar.select( selenium, new SectionImpl<MenuBar.CatalogSections, String, String>(MenuBar.CatalogSections.OFFERS, MenuBar.CatalogSections.OFFERS.section_id_prefix, MenuBar.CatalogSections.OFFERS.section_type), timeout, interval ) ) { return false; }
				
		return true;
		
	}

	public static boolean add( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		return Buttons.click( selenium, new ButtonImpl<Buttons.Types>(Buttons.Types.BTN_ADD), "page-OfferPageView", timeout, interval );
		
	}	
	
	public static boolean create( SeleniumWebDriver selenium, long timeout, long interval ) {
	
		if( !OffersForm.add( selenium, timeout, interval ) ) { return false; }
		
		/*
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Add Campaign") );
		
		WebElement campaignAdd = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Add Campaign", timeout, interval);
		if( campaignAdd == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to add a new Campaign") );
		campaignAdd.click();
		*/
		//OffersForm.setDefinition( selenium, campaignCfg, timeout, interval );
		
		//OffersForm.setActivation( selenium, campaignCfg, timeout, interval );
		
		return true; //OffersForm.manageErrorAction( selenium, campaignCfg, timeout, interval );
				
	}	
	
	/*
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
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for gwt-debug-Campaign Model Select in Campaign" ) );
		
		WebElement campaignModel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Campaign Model Select in Campaign", timeout, interval);
		if( campaignModel == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot set Campaign Model" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to set Campaign Model") );
		selenium.select( "id=gwt-debug-Campaign Model Select in Campaign" ,  campaignCfg.getCampaignModel() );
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for gwt-debug-Campaign Name" ) );
		
		WebElement campaignName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Campaign Name", timeout, interval);
		if( campaignName == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot set Campaign Name" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to set Campaign Name") );
		selenium.type( "id=gwt-debug-Campaign Name" , campaignCfg.getCampaignName() );
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for gwt-debug-Campaign Description" ) );
		
		WebElement campaignDescription = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Campaign Description", timeout, interval);
		if( campaignDescription == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot set Campaign Description" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to set Campaign Model") );
		selenium.type( "id=gwt-debug-Campaign Description" ,  campaignCfg.getCampaignDescription() );		
		
		return true;
		
	}
		
	public static boolean setTarget( SeleniumWebDriver selenium, CampaignCfg campaignCfg, long timeout, long interval ) {
		
		
		
		return true;
		
	}
	
	public static boolean setActivation( SeleniumWebDriver selenium, CampaignCfg campaignCfg, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for link=Activation") );
		
		WebElement definitionSection = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.LINK, "Activation", timeout, interval);
		if( definitionSection == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot activate the new Campaign" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to activate a new Campaign") );
		definitionSection.click();
			
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Campaign Edition Activate" ) );
		
		WebElement activateBtn = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Campaign Edition Activate", timeout, interval);
		if( activateBtn == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot activate Campaign Model" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to activate Campaign Model") );
		activateBtn.click();
				
		return true;
		
	}
	
	public static boolean manageErrorAction( SeleniumWebDriver selenium, CampaignCfg campaignCfg, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for error message") );
		
		WebElement messageError = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "/html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/div/div/table/tbody/tr[7]/td/table/tbody/tr/td/div", timeout, interval);
		
		if( messageError != null ) { 
			
			JSONObject error_actions = campaignCfg.getErrorActions();
			
			if( error_actions == null ) {
				
				logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign ( Wrong json configuration )" ) );
				
				return false;
								
			} else {
				
				try {
					
					if( messageError.getText().equals( "Campaign name already exist" ) && !error_actions.isNull( CampaignErrorAction.CAMPAIGN_ALREADY_EXISTS.name() ) ) {
						
						switch( CampaignErrorActionType.valueOf( error_actions.getString( CampaignErrorAction.CAMPAIGN_ALREADY_EXISTS.name() ) ) ) {
						
							case RETURN_ERROR:{
								
								logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign ( Campaign name already exist )" ) );
								
								return false;
								
							}
							case ADD_TIMESTAMP_TO_CAMPAIGN_NAME:{
								
								String name_with_timestamp = campaignCfg.getCampaignName() + "_" + String.valueOf( TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) );
								
								campaignCfg.setCampaignName( name_with_timestamp );
								
								campaignCfg.setCampaignDescription( name_with_timestamp );
								
								OffersForm.setDefinition( selenium, campaignCfg, timeout, interval );
								
								OffersForm.setActivation( selenium, campaignCfg, timeout, interval );
								
								return true;
								
							}
							case ABORT:{
								
								logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-Campaign Edition Cancel") );
								
								WebElement campaignCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Campaign Edition Cancel", timeout, interval);
								if( campaignCancel == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign" ) ); return false; }	
								
								campaignCancel.click();
								
								return true;
																
							}
							
						}
						
					}  
					
				} catch( Exception e ) {}
				
			}
			
			logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new Campaign ( " + messageError.getText() + " )" ) ); 
			
			return false; 
			
		
		}	
			
		return true;
		
	}
	
	
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
