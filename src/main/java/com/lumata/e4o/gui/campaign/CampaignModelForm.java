package com.lumata.e4o.gui.campaign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.dialog.NotificationForm;
import com.lumata.e4o.gui.json.campaign.JSONAction;
import com.lumata.e4o.gui.json.campaign.JSONActionTime;
import com.lumata.e4o.gui.json.campaign.JSONCampaignModel;
import com.lumata.e4o.gui.json.campaign.JSONEvent;
import com.lumata.e4o.gui.json.campaign.JSONNotification;

public class CampaignModelForm extends CampaignManagerForm {

	private static final Logger logger = LoggerFactory.getLogger(CampaignModelForm.class);

	private JSONCampaignModel campaignModelCfg;
	
	private final String campaignModelFormXPath = "//*[@id='gwt-debug-FormCampaignModelCreation']";	
	
	public enum CMErrorAction {

		MODEL_ALREADY_EXISTS;

	};

	public enum CMErrorActionType {

		RETURN_ERROR, ABORT, ADD_TIMESTAMP_TO_MODEL_NAME;

	};

	public CampaignModelForm( SeleniumWebDriver selenium, JSONCampaignModel campaignModelCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);

		logger.info("Init CampaignModelForm");
		
		this.campaignModelCfg = campaignModelCfg;
		
	}
	
	public CampaignModelForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-InputCMCampaignModel" );
		
		return this;
		
	}

	public CampaignModelForm addCampaignModels() throws FormException, JSONException {
		
		JSONArray campaignModels = campaignModelCfg.getList();
		
		for( int camapignModelIndex = 0; camapignModelIndex < campaignModels.length(); camapignModelIndex++ ) {
			
			campaignModelCfg.setCampaignModelById( camapignModelIndex );
			
			if( isActive() ) {
			
				clickId( "gwt-debug-BtnCampaignModelAdd" ).
				configureCampaignModel().
				saveCampaignModel();
				
			}
					
		}
		
		return this;
		
	}
	
	public CampaignModelForm saveCampaignModel() throws FormException {
		
		final String saveCampaignModelButtonXPath = campaignModelFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationSave']";
		
		clickXPath( saveCampaignModelButtonXPath );
		
		return this;
		
	}
	
	public CampaignModelForm cancelCampaignModel() throws FormException {
		
		final String cancelCampaignModelButtonXPath = campaignModelFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationCancel']";
		
		clickXPath( cancelCampaignModelButtonXPath );
			
		return this;
		
	}
	
	public CampaignModelForm configureCampaignModel() throws FormException, JSONException {
		
		sendKeysById( "gwt-debug-InputCampaignModelCreationName", campaignModelCfg.getName() ).
		sendKeysById( "gwt-debug-InputCampaignModelCreationDescription", campaignModelCfg.getDescription() ).
		addEvents();
		
				
		/*
	
		JSONArray eventsList = cm.getEventsList();

		if (eventsList.length() > 0) {

			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationEventAdd"));

			WebElement campaignModelAddEvents = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationEventAdd", timeout, interval);
			if (campaignModelAddEvents == null) {
				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
				return false;
			}

			for (int i = 0; i < eventsList.length(); i++) {
				campaignModelAddEvents.click();

				logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-ListCampaignModelCreationETType"));
				String eventPath ="//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr["+(i+2)+"]";
				String pathAddEvent =eventPath+"//*[@id='gwt-debug-ListCampaignModelCreationETType']";
				WebElement campaignModelAddEventType = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, pathAddEvent, timeout,
						interval);	
				if (campaignModelAddEventType == null) {
					logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
					return false;
				}

				campaignModelAddEventType.click();

				String eventType = cm.getEventType(i);

				if (eventType != null) {					
					selectGenericPath(selenium, eventType, campaignModelAddEventType);
					
					JSONArray actionList = cm.getActionList(i);
					if (null != actionList) {
						for (int j = 0; j < actionList.length(); ++j) {
							logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationEAAdd"));
							String addActionPTH = eventPath+"//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']";
							WebElement campaignModelAddActionBtn = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, addActionPTH, timeout,
									interval);
							if (campaignModelAddActionBtn == null) {
								logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
								return false;
							}

							campaignModelAddActionBtn.click();
							
							logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-ListCampaignModelCreationEAType"));
							String actionBasePath = eventPath + "//*[@class='commodityContainer']/tbody/tr[" + (j + 1) + "]";
							String actionPath =actionBasePath+ "//td[@id='gwt-debug-ListCampaignModelCreationEAType']";							
							WebElement campaignModelActionTypeList = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, actionPath, 500, interval);
							if (campaignModelActionTypeList == null) {
								logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
								return false;
							}
							campaignModelActionTypeList.click();
							String actionName = cm.getActionName(i, j);
							String actionValue = cm.getActionValue(i, j);
							String actionOption = cm.getActionOption(i, j);
							logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for xpath=//*[@class='gwt-MenuBarPopup act-ListBoxHolderPopup"));
							WebElement container = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "//*[@class='gwt-MenuBarPopup act-ListBoxHolderPopup']",
									400, interval);
							selectActionFromPath(selenium, actionName, container,actionValue,actionOption,actionBasePath,timeout, interval);
						}
					}
				}
			}

			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationSave"));

			WebElement campaignModelSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationSave", timeout, interval);
			if (campaignModelSave == null) {
				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
				return false;
			}

			campaignModelSave.click();

			return CampaignModelForm.manageErrorAction(selenium, cm, 1000, interval);

		} else {

			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationCancel"));

			WebElement campaignModelCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationCancel", timeout, interval);
			if (campaignModelCancel == null) {
				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
				return false;
			}

			campaignModelCancel.click();

		}
*/
		return this;

	}

	public CampaignModelForm addEvents() throws JSONException, FormException {
		
		Map<String, JSONEvent> events = campaignModelCfg.getEvents();
		
		int eventRow = 1;
		
		for( String eventName : events.keySet() ) {
			
			eventRow++;
			
			addEvent().
			configureEvent( events.get( eventName ), eventRow );
						
		}
		
		return this;
		
	}
	
	public CampaignModelForm addEvent() throws JSONException, FormException {
		
		clickId( "gwt-debug-BtnCampaignModelCreationEventAdd" );
			
		return this;
		
	}
	
	public CampaignModelForm configureEvent( JSONEvent event, Integer eventRow ) throws JSONException, FormException {
		
		String eventXPath ="//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[" + eventRow + "]//*[@id='gwt-debug-ListCampaignModelCreationETType']";
		
		clickXPath( eventXPath ).
		selectDropDownListItem( event.getEventType() ).
		addActions( event, eventRow ).
		selectBeneficiary( event.getBeneficiary(), eventRow ).
		addNotifications( event, eventRow );
		
		return this;
		
	}
	
	public CampaignModelForm configureCriteria( JSONEvent event ) throws JSONException, FormException {

		event.getCriteria();
		
		return this;
		
	}
	
	public CampaignModelForm addActions( JSONEvent event, Integer eventRow ) throws JSONException, FormException {

		Map<String, JSONAction> actions = event.getActions();
		
		int actionRow = 0;
		
		for( String actionName : actions.keySet() ) {
					
			addAction( eventRow );
			
			actionRow++;
						
			configureAction( actions.get( actionName ), eventRow, actionRow );
												
		}
		
		return this;
		
	}
	
	public CampaignModelForm addAction( Integer eventRow ) throws FormException {
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']";
		
		clickXPath( actionXPathRowAAdd );
		
		return this;
		
	}
	
	public CampaignModelForm deleteAction( Integer eventRow ) throws FormException {
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRowADelete = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationEADelete']";
		
		clickXPath( actionXPathRowADelete );
		
		return this;
		
	}

	public CampaignModelForm configureAction( JSONAction action, Integer eventRow, Integer actionRow ) throws JSONException, FormException {
				
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRow = eventXPathRow + "//table[@class='commodityContainer']/tbody/tr[" + actionRow + "]";
		
		String actionXPathRowAValue = actionXPathRow + "//*[@id='gwt-debug-TextCampaignModelCreationEAValue']";			
		String actionXPathRowAType = actionXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationEAType']";
		String actionXPathRowAUnit = actionXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationEAUnit']";			
				
		/** configure action time */
		if( action.hasActionTime() ) {
			
			addActionTime( eventRow, actionRow ).
			configureActionTime( action.getActionTime() ).
			saveActionTime();				
							
		}
		
		/** configure action */
		clickXPath( actionXPathRowAType ).
		selectDropDownListItem( action.getName() ).
		typeByXPath( actionXPathRowAValue, action.getValue() ).
		selectByXPathAndVisibleText( actionXPathRowAUnit, action.getOption() );
		
		return this;
		
	}
	
	public CampaignModelForm addActionTime( Integer eventRow, Integer actionRow ) throws FormException {
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRow = eventXPathRow + "//table[@class='commodityContainer']/tbody/tr[" + actionRow + "]";		
		String actionTimeXPathRowAAdd = actionXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationEATime']";
		
		clickXPath( actionTimeXPathRowAAdd );		
		
		return this;
		
	}

	public CampaignModelForm saveActionTime() throws FormException {
		
		String actionTimeXPathRowSave = "//div[@class='gwt-DialogBox']//div[@class='Caption' and text()='Time Configuration']/ancestor::tbody//button[@title='OK']";
		
		clickXPath( actionTimeXPathRowSave );
		
		return this;
		
	}
	
	public CampaignModelForm abortActionTime() throws FormException {
		
		String actionTimeXPathRowCancel = "//div[@class='gwt-DialogBox']//div[@class='Caption' and text()='Time Configuration']/ancestor::tbody//button[@title='Cancel']";
		
		clickXPath( actionTimeXPathRowCancel );
		
		return this;
		
	}

	public CampaignModelForm configureActionTime( JSONActionTime actionTime ) throws FormException {
		
		String actionTimeXPathRowStartTime = "//div[@class='gwt-DialogBox']//td[contains(text(),'Start time')]//parent::tr/td/select";
		String actionTimeXPathRowDurationType = "//div[@class='gwt-DialogBox']//td[contains(text(),'Duration')]//parent::tr[@class='cycle2']//tbody/tr/td[1]/select";
		String actionTimeXPathRowDurationValue = "//div[@class='gwt-DialogBox']//td[contains(text(),'Duration')]//parent::tr[@class='cycle2']//tbody/tr/td[2]/input";
		String actionTimeXPathRowDurationTimeType = "//div[@class='gwt-DialogBox']//td[contains(text(),'Duration')]//parent::tr[@class='cycle2']//tbody/tr/td[3]/select";
		
		selectByXPathAndVisibleText( actionTimeXPathRowStartTime, actionTime.getStarTime() ).
		selectByXPathAndVisibleText( actionTimeXPathRowDurationType, actionTime.getDurationType() ).
		typeByXPath( actionTimeXPathRowDurationValue, actionTime.getDurationValue() ).
		selectByXPathAndVisibleText( actionTimeXPathRowDurationTimeType, actionTime.getDurationTimeType() );
		
		return this;
		
	}
	
	public CampaignModelForm enableBeneficiary( Integer eventRow ) throws FormException {
		
		Boolean isBeneficiarySelected = isBeneficiarySelected( eventRow );
		
		if( !isBeneficiarySelected ) { this.lastWebElement.click(); }
		
		return this;
		
	}

	public CampaignModelForm disableBeneficiary( Integer eventRow ) throws FormException {
		
		Boolean isBeneficiarySelected = isBeneficiarySelected( eventRow );
		
		if( isBeneficiarySelected ) { this.lastWebElement.click(); }
				
		return this;
		
	}
		
	public CampaignModelForm selectBeneficiary( Boolean selectBeneficiary, Integer eventRow ) throws FormException {
				
		if( selectBeneficiary == true ) { 
			enableBeneficiary( eventRow ); 
		} else {
			disableBeneficiary( eventRow );
		}
				
		return this;
		
	}

	private Boolean isBeneficiarySelected( Integer eventRow ) throws FormException {
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]";
		String beneficiaryXPathCheckBox = eventXPathRow + "//*[@id='gwt-debug-CheckCampaignModelCreationEBInput-input']";
		
		return isCheckedByXPath( beneficiaryXPathCheckBox );
		
	}
	
	public CampaignModelForm addNotifications( JSONEvent event, Integer eventRow ) throws FormException, JSONException {
				
		if( event.hasNotification() ) {
		
			String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]"; 
			String notificationXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationENAdd']";
			
			clickXPath( notificationXPathRowAAdd ).
			configureNotifications( event.getNotifications() );
			
		}
		
		return this;
		
	}
	
	public CampaignModelForm configureNotifications( Map<String, JSONNotification> notifications ) throws FormException {
		
		NotificationForm notificationDialog = new NotificationForm( selenium, notifications, timeout, interval );
		
		notificationDialog.configureNotifications();

		return this;
		
	}
	
	private Boolean isActive() throws JSONException {
		
		return this.campaignModelCfg.getEnabled();
		
	}
	
	@Override
	public CampaignModelForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
		
	}
	
	@Override
	public CampaignModelForm clickXPath( String xpath ) throws FormException {
		
		super.clickXPath( xpath );
		
		return this;
		
	}
	
	@Override
	public CampaignModelForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
		
	}
	
	@Override
	public CampaignModelForm selectByXPathAndVisibleText( String xpath, String text ) throws FormException {
		
		super.selectByXPathAndVisibleText( xpath, text );
		
		return this;
		
	}
	
	@Override
	public CampaignModelForm typeByXPath( String xpath, String text ) throws FormException {
		
		super.typeByXPath( xpath, text );
		
		return this;
		
	}
	
	@Override
	public CampaignModelForm selectDropDownListItem( String itemPath ) throws FormException {
		
		super.selectDropDownListItem( itemPath );
		
		return this;		
		
	}
	
	@Override
	public Boolean isCheckedByXPath( String xpath ) throws FormException {
		
		return super.isCheckedByXPath( xpath );
		
	}

	/**
	 * This method delete a list of "Campaign Model" into running system.
	 * If input var-args is empty or null, this method deletes each campaign model
	 * 
	 * @param CampaignModelNames
	 * 
	 * @return true if wanted campaign model are corrected removed
	 * 
	 * @throws FormException 
	 */
	public Boolean deleteCampaignModel(String... CampaignModelNames) throws FormException {

		List<String> cmLabel2Delete = null;
		Boolean resp = Boolean.FALSE;
		
		if ( CampaignModelNames != null && CampaignModelNames.length != 0 )
			cmLabel2Delete = Arrays.asList(CampaignModelNames);
		else { // fetch every campaign model present on UI
			
			cmLabel2Delete = new ArrayList<String>();
			
			String rootPath = "//table[@id=\"gwt-debug-ListCampaignModel\"]";
			String subPath = "//tr[contains(@class,\"contentRow cycle\")]//td[@class=\"column_description\"]";
		
			List<WebElement> cmLabels = getListByXPath(rootPath, rootPath + subPath);
			
			for (WebElement webElement : cmLabels)
				cmLabel2Delete.add(webElement.getText());
		}

		logger.debug("Campaign labels to be deleted : " + cmLabel2Delete);
		
		try {
			
			for (String cnName : cmLabel2Delete) {
				
				String singleRule = "//div[text()='"+cnName+"']//ancestor::tr//*[@name='btn-delete']";
				
				logger.debug("Try to delete \"Campaign Moldel \" + \""+cnName+"\".");
				
				clickXPath(singleRule);
			}
			
			resp = Boolean.TRUE;
			
		} catch ( FormException e ) {

			logger.error("Error during delete \"Campaign Moldel \" : " + e.getMessage());
			
			resp = Boolean.FALSE;
		}

		return resp;
	}	

	/*
	public static boolean create(SeleniumWebDriver selenium, CampaignModelCfg cm, long timeout, long interval) {

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelAdd"));

		WebElement campaignModelAdd = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelAdd", timeout, interval);
		if (campaignModelAdd == null) {
			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
			return false;
		}

		logger.info(Log.SELECTING.createMessage(selenium.getTestName(), "to add a new Campaign Model"));
		campaignModelAdd.click();

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-InputCampaignModelCreationName"));

		WebElement campaignModelAddName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputCampaignModelCreationName", timeout, interval);
		if (campaignModelAddName == null) {
			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
			return false;
		}

		logger.info(Log.PUTTING.createMessage(selenium.getTestName(), "Campaign Model Name"));

		campaignModelAddName.sendKeys(cm.getName());

		logger.info(Log.GETTING.createMessage(selenium.getTestName(), "for events parameter"));
		JSONArray eventsList = cm.getEventsList();

		if (eventsList.length() > 0) {

			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationEventAdd"));

			WebElement campaignModelAddEvents = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationEventAdd", timeout, interval);
			if (campaignModelAddEvents == null) {
				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
				return false;
			}

			for (int i = 0; i < eventsList.length(); i++) {
				campaignModelAddEvents.click();

				logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-ListCampaignModelCreationETType"));
				String eventPath ="//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr["+(i+2)+"]";
				String pathAddEvent =eventPath+"//*[@id='gwt-debug-ListCampaignModelCreationETType']";
				WebElement campaignModelAddEventType = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, pathAddEvent, timeout,
						interval);	
				if (campaignModelAddEventType == null) {
					logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
					return false;
				}

				campaignModelAddEventType.click();

				String eventType = cm.getEventType(i);

				if (eventType != null) {					
					selectGenericPath(selenium, eventType, campaignModelAddEventType);
					
					JSONArray actionList = cm.getActionList(i);
					if (null != actionList) {
						for (int j = 0; j < actionList.length(); ++j) {
							logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationEAAdd"));
							String addActionPTH = eventPath+"//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']";
							WebElement campaignModelAddActionBtn = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, addActionPTH, timeout,
									interval);
							if (campaignModelAddActionBtn == null) {
								logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
								return false;
							}

							campaignModelAddActionBtn.click();
							
							logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-ListCampaignModelCreationEAType"));
							String actionBasePath = eventPath + "//*[@class='commodityContainer']/tbody/tr[" + (j + 1) + "]";
							String actionPath =actionBasePath+ "//td[@id='gwt-debug-ListCampaignModelCreationEAType']";							
							WebElement campaignModelActionTypeList = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, actionPath, 500, interval);
							if (campaignModelActionTypeList == null) {
								logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
								return false;
							}
							campaignModelActionTypeList.click();
							String actionName = cm.getActionName(i, j);
							String actionValue = cm.getActionValue(i, j);
							String actionOption = cm.getActionOption(i, j);
							logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for xpath=//*[@class='gwt-MenuBarPopup act-ListBoxHolderPopup"));
							WebElement container = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "//*[@class='gwt-MenuBarPopup act-ListBoxHolderPopup']",
									400, interval);
							selectActionFromPath(selenium, actionName, container,actionValue,actionOption,actionBasePath,timeout, interval);
						}
					}
				}
			}

			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationSave"));

			WebElement campaignModelSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationSave", timeout, interval);
			if (campaignModelSave == null) {
				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
				return false;
			}

			campaignModelSave.click();

			return CampaignModelForm.manageErrorAction(selenium, cm, 1000, interval);

		} else {

			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationCancel"));

			WebElement campaignModelCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationCancel", timeout, interval);
			if (campaignModelCancel == null) {
				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
				return false;
			}

			campaignModelCancel.click();

		}

		return true;

	}

	private static void selectActionFromPath(SeleniumWebDriver selenium, String actionNamePath, WebElement container, String actionValue, String actionOption,String basePath, long timeout, long interval) {
		selectGenericPath(selenium, actionNamePath, container);
		if (null!= actionValue){
			String actionValuePath = basePath+"//input[@id='gwt-debug-TextCampaignModelCreationEAValue']";
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for "+actionValuePath));
			WebElement actionValueElement = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, actionValuePath, timeout, interval);
			actionValueElement.sendKeys(actionValue);
		}
		if (null!= actionOption){
			String actionValuePath = basePath+"//select[@id='gwt-debug-ListCampaignModelCreationEAUnit']";
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for "+actionValuePath));
			WebElement actionActionElement = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, actionValuePath, timeout, interval);
			Select s = new Select(actionActionElement);
			s.selectByVisibleText(actionOption);
		}
	}

	private static void selectGenericPath(SeleniumWebDriver selenium, String actionNamePath, WebElement container) {
		int k = 0;
		String delimiter = "\\.";
		String[] actionSequence = actionNamePath.split(delimiter);
		boolean found = false;
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "trying to select "+actionNamePath));
		while (true) {			
			List<WebElement> elementList = container.findElements(By.xpath("//td[@class='gwt-MenuItem']"));
			for (WebElement webElement : elementList) {
				String path = actionSequence[k];
				if (webElement.getText().equalsIgnoreCase(path)) {
					String elementId = webElement.getAttribute("id");
					selenium.mouseOver("id=" + elementId);
					if (k == actionSequence.length - 1) {
						selenium.click("id=" + elementId);
						found = true;
						break;
					}
					k++;
				}
			}
			if (found) {
				break;
			}
		}
	}

	public static boolean manageErrorAction(SeleniumWebDriver selenium, CampaignModelCfg cm, long timeout, long interval) {

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for error message"));

		WebElement messageError = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "/html/body/div[6]/div/div", timeout, interval);

		if (messageError != null) {

			JSONObject error_actions = cm.getErrorActions();

			if (error_actions == null) {

				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model ( Wrong json configuration )"));

				return false;

			} else {

				try {

					if (messageError.getText().equals("Model name already exist") && !error_actions.isNull(CMErrorAction.MODEL_ALREADY_EXISTS.name())) {

						switch (CMErrorActionType.valueOf(error_actions.getString(CMErrorAction.MODEL_ALREADY_EXISTS.name()))) {

						case RETURN_ERROR: {

							logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model ( Model name already exist )"));

							return false;

						}
						case ADD_TIMESTAMP_TO_MODEL_NAME: {

							cm.setName(cm.getName() + "_" + String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())));

							logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-InputCampaignModelCreationName"));

							WebElement campaignModelAddName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-InputCampaignModelCreationName",
									timeout, interval);
							if (campaignModelAddName == null) {
								logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
								return false;
							}

							logger.info(Log.PUTTING.createMessage(selenium.getTestName(), "Campaign Model Name"));

							campaignModelAddName.sendKeys(cm.getName());

							logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationSave"));

							WebElement campaignModelSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationSave", timeout,
									interval);
							if (campaignModelSave == null) {
								logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
								return false;
							}

							campaignModelSave.click();

							logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for new error message"));

							WebElement newMessageError = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "/html/body/div[6]/div/div", timeout, interval);

							if (newMessageError != null) {

								logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model ( " + newMessageError.getText() + " )"));

								return false;

							} else {

								return true;

							}

						}
						case ABORT: {

							logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-BtnCampaignModelCreationCancel"));

							WebElement campaignModelCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BtnCampaignModelCreationCancel",
									timeout, interval);
							if (campaignModelCancel == null) {
								logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model"));
								return false;
							}

							campaignModelCancel.click();

							return true;

						}

						}

					}

				} catch (Exception e) {
				}

			}

			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Campaign Model ( " + messageError.getText() + " )"));

			return false;

		}

		return true;

	}

	public static boolean isModel(SeleniumWebDriver selenium, ArrayList<CampaignModelCfg> cmList, CampaignModelCfg cm) {

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "if Campaign Model exists ( " + cm.getName() + " )"));

		for (int i = 0; i < cmList.size(); i++) {

			CampaignModelCfg cmElement = cmList.get(i);

			if (cmElement.getName().equals(cm.getName())) {
				return true;
			}

		}

		return false;

	}

	public static WebElement getCampaignModelTable(SeleniumWebDriver selenium, long timeout, long interval) {

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=gwt-debug-ListCampaignModel"));

		WebElement campaignModelTable = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-ListCampaignModel", timeout, interval);
		if (campaignModelTable == null) {
			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Campaign Model Table not found"));
			return null;
		}

		return campaignModelTable;

	}

	public static List<WebElement> getCampaignModelTableContent(SeleniumWebDriver selenium, long timeout, long interval) {

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for elements contained in id=gwt-debug-ListCampaignModel"));

		List<WebElement> availableCampaignModels = SeleniumUtils.findListForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, SeleniumUtils.SearchBy.CLASS_NAME,
				"gwt-debug-ListCampaignModel", "contentRow", timeout, interval);

		return availableCampaignModels;

	}

	public static ArrayList<Map<String, Object>> getCampaignModelList(SeleniumWebDriver selenium, long timeout, long interval) {

		ArrayList<Map<String, Object>> cmList = new ArrayList<Map<String, Object>>();

		List<WebElement> availableCampaignModels = CampaignModelForm.getCampaignModelTableContent(selenium, timeout, interval);

		logger.info(Log.PUTTING.createMessage(selenium.getTestName(), "all discovered elements contained in id=gwt-debug-ListCampaignModel"));

		for (int i = 0; i < availableCampaignModels.size(); i++) {

			Map<String, Object> cmModel = new HashMap<String, Object>();

			List<WebElement> availableCampaignModelName = SeleniumUtils.findListForComponentDisplayed(selenium, SeleniumUtils.SearchBy.CLASS_NAME, availableCampaignModels.get(i),
					"column_description", timeout, interval);

			// Assume only the first element found is valid
			String name = availableCampaignModelName.get(0).getText();
			cmModel.put("name", (name != null ? name : ""));

			List<WebElement> availableCampaignModelDescription = SeleniumUtils.findListForComponentDisplayed(selenium, SeleniumUtils.SearchBy.CLASS_NAME, availableCampaignModels.get(i),
					"column_longText", timeout, interval);

			// Assume only the first element found is valid
			String description = availableCampaignModelDescription.get(0).getText();
			cmModel.put("description", (description != null ? description : ""));

			List<WebElement> availableCampaignModelButtons = SeleniumUtils.findListForComponentDisplayed(selenium, SeleniumUtils.SearchBy.TAG_NAME, availableCampaignModels.get(i), "button",
					timeout, interval);

			for (int j = 0; j < availableCampaignModelButtons.size(); j++) {

				cmModel.put(availableCampaignModelButtons.get(j).getAttribute("title").toLowerCase(), availableCampaignModelButtons.get(j));

			}

			cmList.add(cmModel);

		}

		return cmList;

	}

	public static Map<String, Object> searchCampaignModel(SeleniumWebDriver selenium, ArrayList<Map<String, Object>> cmList, String cmModelName, long timeout, long interval) {

		for (int i = 0; i < cmList.size(); i++) {

			Map<String, Object> cmModel = cmList.get(i);

			if (cmModel.get("name").equals(cmModelName)) {
				return cmModel;
			}

		}

		return null;

	}

	public static boolean editCampaignModel(SeleniumWebDriver selenium, Map<String, Object> cmModel, long timeout, long interval) {

		if (cmModel != null) {
			((WebElement) cmModel.get("edit")).click();
		} else {
			return false;
		}

		return true;

	}

	public static boolean copyCampaignModel(SeleniumWebDriver selenium, Map<String, Object> cmModel, long timeout, long interval) {

		if (cmModel != null) {
			((WebElement) cmModel.get("copy")).click();
		} else {
			return false;
		}

		return true;

	}

	public static boolean deleteCampaignModel(SeleniumWebDriver selenium, Map<String, Object> cmModel, long timeout, long interval) {

		if (cmModel != null) {
			((WebElement) cmModel.get("delete")).click();
		} else {
			return false;
		}

		return true;

	}
*/
}
