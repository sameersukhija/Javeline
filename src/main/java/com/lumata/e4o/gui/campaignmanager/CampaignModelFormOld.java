package com.lumata.e4o.gui.campaignmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;
import com.lumata.e4o.gui.common.NotificationForm;
import com.lumata.e4o.json.gui.campaignmanager.JSONAction;
import com.lumata.e4o.json.gui.campaignmanager.JSONActionTime;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.campaignmanager.JSONCriteria;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.campaignmanager.JSONNotification;

public class CampaignModelFormOld extends CampaignManagerForm {

	private static final Logger logger = LoggerFactory.getLogger(CampaignModelFormOld.class);

	private JSONCampaignModel campaignModelCfg;
	
	private final String campaignModelFormXPath = "//*[@id='gwt-debug-FormCampaignModelCreation']";	
	
    public enum ElementErrorActionType {

        RETURN_ERROR,
        ABORT_CANCEL,
        ADD_TIMESTAMP_TO_FIELD;

    }; 

	public CampaignModelFormOld( SeleniumWebDriver selenium, JSONCampaignModel campaignModelCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);

		this.campaignModelCfg = campaignModelCfg;
		
	}

	public CampaignModelFormOld( SeleniumWebDriver selenium,long timeout, long interval ) {
		
		super(selenium, timeout, interval);

	}
	
	public CampaignModelFormOld openForm() throws FormException {
		
		super.openForm();
		waitForPageLoad();
//		WebDriverWait wait=new WebDriverWait(selenium.getWrappedDriver(), 30);
//		wait.until(ExpectedConditions.elementToBeClickable(By.id("gwt-debug-InputCMCampaignModel")));
		this.clickId( "gwt-debug-InputCMCampaignModel" );
		
		return this;
		
	}
	
	public CampaignModelFormOld addBtn() throws FormException{
		super.clickId("gwt-debug-BtnCampaignModelAdd");
		return this;
	}
	
	public String CampaignModelNameInList(String strCampaignModelName)
			throws FormException {

		List<WebElement> campaignModelList = getCampaignModelList();

		for (WebElement campaignModelEl : campaignModelList) {

			if (campaignModelEl.getText().trim().equals(strCampaignModelName)) {

				return strCampaignModelName;

			}

		}

		return null;

	}
	
	public static int searchCampaignModel(SeleniumWebDriver selenium, ArrayList<Map<String, Object>> cmList, String cmModelName, long timeout, long interval) {

		for (int i = 0; i < cmList.size(); i++) {

			Map<String, Object> strCampaignModelName = cmList.get(i);

			if (strCampaignModelName.get("name").equals(cmModelName)) {
				System.out.println(i);
				return i;
			}

		}

		return 0;
	
	}
	
	public CampaignModelFormOld addCampaignModels() throws FormException, JSONException {
		
		//configureCampaignModel().
		//saveCampaignModel().manageErrorAction( campaignModelCfg.getErrorActions().getString( "ELEMENT_ALREADY_EXISTS" ) );
			
		return this;
	
	}
	
	public CampaignModelFormOld campaignModelEditButton(String campModelName) throws FormException{
		
		
		super.clickXPath("//div[text()='"+campModelName+"']//ancestor::tr[1]//td[3]/table/tbody/tr/td[2]//button[@name='btn-edit']");
		
		return this;
	}	

	public CampaignModelFormOld campaignModelCopyButton(String campModelName) throws FormException{
		super.clickXPath("//div[text()='"+campModelName+"']//ancestor::tr[1]/td[3]/table/tbody/tr/td[1]/button[@name='btn-copy']");
		return this;
		
	}
	
	public CampaignModelFormOld campaignModelDeleteButton(String CMName) throws FormException{
		super.clickXPath("//div[text()='"+CMName+"']//ancestor::tr[1]/td/table/tbody/tr/td//button[@name='btn-delete']");
		return this;
		
	}

	public CampaignModelFormOld setModelName(String name) throws FormException{
		super.sendKeysById( "gwt-debug-InputCampaignModelCreationName", name);
		return this;
		
	}
	
	public String getModelName() throws FormException{
		return super.getValueById("gwt-debug-InputCampaignModelCreationName");
		
	}
	
	public CampaignModelFormOld setModelDescription(String description) throws FormException{
		super.sendKeysById( "gwt-debug-InputCampaignModelCreationDescription", description);
		return this;
		
	}
	
	public String getModelDescription() throws FormException{
		return super.getValueById("gwt-debug-InputCampaignModelCreationDescription");
		
	}
	
	public CampaignModelFormOld setModelType(String type) throws FormException{
		super.selectByIdAndVisibleText("gwt-debug-ListCampaignModelCreationType", type);
		return this;
		
	}
	
	public String getModelType() throws FormException{
		return super.getValueById("gwt-debug-ListCampaignModelCreationType");
		
	}
	
	public CampaignModelFormOld setUseHierarchy() throws FormException{
		super.clickId("gwt-debug-CheckCampaignModelCreationUseHierarchy-input");
		return this;
	}
	
	public CampaignModelFormOld configureEventType(String eventType) throws FormException{
		super.selectByIdAndVisibleText("gwt-debug-ListCampaignModelCreationETType", eventType);
		return this;
	}
	
	public CampaignModelFormOld addActionButton() throws FormException{
		super.clickId("gwt-debug-BtnCampaignModelCreationEAAdd");
		return this;
	}
	
	public CampaignModelFormOld saveCampaignModel() throws FormException {
		
		clickXPath( "//button[@id='gwt-debug-BtnCampaignModelCreationSave']" );
		
		return this;
		
	}
	
	public CampaignModelFormOld saveEditedCampaignModel() throws FormException {
		
		clickXPath( "//button[@name='btn-save']" );
	
		return this;
		
	}
	
	/**
	* Click on campaign model save and accept if alert popup is displayed
	*
	* @throws FormException
	*/

	public Boolean confirmCampaignModelAlert(Boolean accept) throws FormException {
		
		Alert popupAlert = null;
		Boolean pressed = null;
		
		try {
		
			popupAlert = selenium.selectAlert();
			String popupalertText = popupAlert.getText();
			System.out.println(popupalertText);
			if ( popupAlert != null ) { 
				
				if ( accept )
					popupAlert.accept();
				else 
					popupAlert.dismiss();
				
				pressed = Boolean.TRUE; 
			}
			
		} catch (NoAlertPresentException e) {
			
			// nothing to do
			pressed = Boolean.TRUE;
		}
		
		return pressed;
	}
	
	
	public String closeAlertAndGetItsText() {
		
		boolean acceptNextAlert = true;
		try {
	      
			Alert alert = selenium.selectAlert();
	      
			String alertText = alert.getText();

			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
	      
			return alertText;
	    
		} finally {
	      acceptNextAlert = true;
	    }
	}
		
	public CampaignModelFormOld cancelBtn() throws FormException {
		
		final String cancelCampaignModelButtonXPath = campaignModelFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationCancel']";
		
		clickXPath( cancelCampaignModelButtonXPath );
			
		return this;
		
	}
	
	public CampaignModelFormOld configureCampaignModel(String name, String description,String type,Boolean check) throws FormException, JSONException {
		
		setModelName(name);
		if(null!=description)
			setModelDescription(description);
		if(null!=type)
			setModelType(type);
		if(check==true)
			setUseHierarchy();
		//addEvents(events);
		//configureEventType(event);
		//addActionButton();
				
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

	public CampaignModelFormOld addEvents(Map<String, JSONEvent_> events) throws JSONException, FormException {
		
	
		int eventRow = 1;
		
		for( String eventName : events.keySet() ) {
			
			eventRow++;
			
			
			addEventButton().
			
			configureEvent( events.get( eventName ), eventRow );
						
		}
		
		return this;
		
	}
	
	
//	public CampaignModelFormOld addEvents2(Map<String, JSONEvent_> events) throws JSONException, FormException {
//		
//		
//		int eventRow = 2;
//		
//		for( String eventName : events.keySet() ) {
//			
//			eventRow++;
//			
//			
//			addEventButton().
//			
//			configureEvent1( events.get( eventName ), eventRow );
//						
//		}
//		
//		return this;
//		
//	}
//	
//
//	public CampaignModelFormOld EditEvents(Map<String, JSONEvent_> events) throws JSONException, FormException {
//		
//		int eventRow = 1;
//		
//		for( String eventName : events.keySet() ) {
//			
//			eventRow++;
//			
//			EditconfigureEvent( events.get( eventName ), eventRow );
//						
//		}
//		
//		return this;
//		
//	}
	
	public CampaignModelFormOld addEventButton() throws JSONException, FormException {
		
		clickId( "gwt-debug-BtnCampaignModelCreationEventAdd" );
			
		return this;
		
	}
	
	public CampaignModelFormOld configureEvent( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {
		
		String eventXPath ="//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[" + eventRow + "]//*[@id='gwt-debug-ListCampaignModelCreationETType']";
		clickXPath( eventXPath ).
		selectDropDownListItem( event.getEventType() ).
		addCriterae(event,eventRow);
		addActions( event, eventRow );
		
		return this;
		
	}
	
//	public CampaignModelFormOld configureEvent1( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {
//		
//		String eventXPath ="//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[" + eventRow + "]//*[@id='gwt-debug-ListCampaignModelCreationETType']";
//		clickXPath( eventXPath ).
//		selectDropDownListItem( event.getEventType() );
//		addCriterae1(event,eventRow);
//		addActions1( event, eventRow );
//		
//		return this;
//		
//	}
//	
//	
//	public CampaignModelFormOld EditconfigureEvent( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {
//		
//		String criteriaXPath ="//tr[(@class='contentRow cycle1')]//*[@id='gwt-debug-BtnCampaignModelCreationECAdd']";
//
//		clickXPath( criteriaXPath );
//		
//		EditCriterae(event,eventRow);
//		
//		String actionXPath="//tr[(@class='contentRow cycle1')]//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']";
//		
//		clickXPath( actionXPath );
//		
//		EditActions1( event, eventRow );
//		
//		return this;
//		
//	}
	
	public CampaignModelFormOld configureCriteria( JSONEvent_ event ) throws JSONException, FormException {

		event.getCriteria();
		
		return this;
		
	}
	
	public CampaignModelFormOld addActions( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {

		Map<String, JSONAction> actions = event.getActions();
		
		int actionRow = 0;
		
		for( String actionName : actions.keySet() ) {
					
			addAction( eventRow );
			
			actionRow++;
						
			configureAction( actions.get( actionName ), eventRow, actionRow );
												
		}
		
		return this;
		
	}

//	public CampaignModelFormOld addActions1( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {
//
//		Map<String, JSONAction> actions = event.getActions();
//		
//		int actionRow = 0;
//		
//		for( String actionName : actions.keySet() ) {
//					
//			addAction1( eventRow );
//			
//			actionRow++;
//						
//			configureAction1( actions.get( actionName ), eventRow, actionRow );
//												
//		}
//		
//		return this;
//		
//	}
//
//	public CampaignModelFormOld EditActions1( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {
//
//		Map<String, JSONAction> actions = event.getActions();
//		
//		int actionRow = 1;
//		
//		for( String actionName : actions.keySet() ) {
//					
//			actionRow++;
//						
//			EditconfigureAction1( actions.get( actionName ), eventRow, actionRow );
//												
//		}
//		
//		return this;
//		
//	}
	
	public CampaignModelFormOld addCriterae( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {

		Map<String, JSONCriteria> criteria = event.getCriteria();
		
		int criteriaRow = 0;
		
		for( String criteriaName : criteria.keySet() ) {
					
			addCriteria( eventRow );
			
			criteriaRow++;
						
			configureCriteria( criteria.get( criteriaName ), eventRow, criteriaRow );
												
		}
		
		return this;
		
	}
	
//	public CampaignModelFormOld addCriterae1( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {
//
//		Map<String, JSONCriteria> criteria = event.getCriteria();
//		
//		int criteriaRow = 0;
//		
//		for( String criteriaName : criteria.keySet() ) {
//					
//			addCriteria1( eventRow );
//			
//			criteriaRow++;
//						
//			configureCriteria1( criteria.get( criteriaName ), eventRow, criteriaRow );
//												
//		}
//		
//		return this;
//	}
	
	public CampaignModelFormOld addCriteria( Integer eventRow ) throws FormException {
		
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow cycle2' ) and position() = " + eventRow + " ]//td[@class='column_criteria']"; 
		String criteriaXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationECAdd']";
		
		clickXPath( criteriaXPathRowAAdd );
		
		return this;
		
	}
	
	public CampaignModelFormOld addCriteria1( Integer eventRow ) throws FormException {
		
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow cycle1' ) and position() = " + eventRow + " ]//td[@class='column_criteria']"; 
		String criteriaXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationECAdd']";
		
		clickXPath( criteriaXPathRowAAdd );
		
		return this;
		
	}
	
//	public CampaignModelFormOld EditCriterae( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {
//
//		Map<String, JSONCriteria> criteria = event.getCriteria();
//		
//		int criteriaRow = 1;
//		
//		for( String criteriaName : criteria.keySet() ) {
//					
//			criteriaRow++;
//						
//			EditconfigureCriteria1( criteria.get( criteriaName ), eventRow, criteriaRow );
//												
//		}
//		
//		return this;
//		
//	}

	
	public CampaignModelFormOld addAction( Integer eventRow ) throws FormException {
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']";
		
		clickXPath( actionXPathRowAAdd );
		
		return this;
		
		
	}

	public CampaignModelFormOld addAction1( Integer eventRow ) throws FormException {
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']";
		
		clickXPath( actionXPathRowAAdd );
		
		return this;
		
		
	}

	
	public CampaignModelFormOld deleteAction( Integer eventRow ) throws FormException {
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRowADelete = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationEADelete']";
		
		clickXPath( actionXPathRowADelete );
		
		return this;
		
	}

	public CampaignModelFormOld configureAction( JSONAction action, Integer eventRow, Integer actionRow ) throws JSONException, FormException {
				
		String ruleActionPattern = "^Rulesets[.].*";		
		
				
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
	
		String actionXPathRow = eventXPathRow + "//table[@class='commodityContainer']/tbody/tr[" + actionRow + "]";
		String actionValuePath = actionXPathRow+"//input[@id='gwt-debug-TextCampaignModelCreationEAValue']";
		String actionXPathRowAType = actionXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationEAType']";
		String actionXPathRowAUnit = actionXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationEAUnit']";			
		String actionXPathRowAAutoAllocation = actionXPathRow + "//*[contains(text(), '::AUTO_ALLOCATE::') ]/parent::select";
		
		
		/** configure action */
		clickXPath( actionXPathRowAType ).
		selectDropDownListItem( action.getName());
		if (null!= action.getValue()){
		sendKeysByXPath(actionValuePath, action.getValue());
		}
		
		if( null != action.getOption() ) { 
			
			if( action.getName().matches( ruleActionPattern ) ) {
				
				selectByXPathAndVisibleText( actionXPathRowAAutoAllocation.replace( "::AUTO_ALLOCATE::" , action.getOption() ), action.getOption() );
				
			} else { selectByXPathAndVisibleText( actionXPathRowAUnit, action.getOption() ); }
		
		}
		
				
		return this;
		
	}
	
	
//	public CampaignModelFormOld configureAction1( JSONAction action, Integer eventRow, Integer actionRow ) throws JSONException, FormException {
//		
//		String ruleActionPattern = "^Rulesets[.].*";		
//		
//		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
//		String actionXPathRow = eventXPathRow + "//table[@class='commodityContainer']/tbody/tr[" + actionRow + "]";
//		String actionXPathRowAType = actionXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationEAType']";
//		String actionXPathRowAUnit = actionXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationEAUnit']";			
//		String actionXPathRowAAutoAllocation = actionXPathRow + "//*[contains(text(), '::AUTO_ALLOCATE::') ]/parent::select";
//		/** configure action */
//		clickXPath( actionXPathRowAType ).
//		selectDropDownListItem( action.getName1() );
//		
//		if( null != action.getOption1() ) { 
//			
//			if( action.getName1().matches( ruleActionPattern ) ) {
//				
//				selectByXPathAndVisibleText( actionXPathRowAAutoAllocation.replace( "::AUTO_ALLOCATE::" , action.getOption1() ), action.getOption1() );
//				
//			} else { selectByXPathAndVisibleText( actionXPathRowAUnit, action.getOption1() ); }
//		
//		}
//				
//		return this;
//		
//	}
//	
//	
//	public CampaignModelFormOld EditconfigureAction1( JSONAction action, Integer eventRow, Integer actionRow ) throws JSONException, FormException {
//		String ruleActionPattern = "^Rulesets[.].*";		
//		
//		String actionXPathRowAType = "//tr[2]/td[3]/div/table/tbody/tr//*[@id='gwt-debug-ListCampaignModelCreationEAType']";
//		String actionXPathRowAUnit =  "//tr[2]/td[6]/select";
//		/** configure action */
//		clickXPath( actionXPathRowAType ).
//		selectDropDownListItem( action.getName2() );
//		
//		if( null != action.getOption2() ) { 
//			
//			if( action.getName2().matches( ruleActionPattern ) ) {
//				
//				selectByXPathAndVisibleText( actionXPathRowAUnit.replace( "::AUTO_ALLOCATE::" , action.getOption2() ), action.getOption2() );
//				
//			} else { selectByXPathAndVisibleText( actionXPathRowAUnit, action.getOption2() ); }
//		
//		}
//				
//		return this;
//		
//	}
	
	public CampaignModelFormOld configureCriteria( JSONCriteria criteria, Integer eventRow, Integer criteriaRow ) throws JSONException, FormException {
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow cycle2' ) and position() = " + eventRow + " ]//td[@class='column_criteria']"; 
		String criteriaXPathRow = eventXPathRow + "//div[@class='criterionContainer']//table/tbody/tr["+ criteriaRow + "]";
		String criteriaXPathRowAValue = criteriaXPathRow + "//*[@id='gwt-debug-TextCampaignModelCreationECValue']";
		String criteriaXPathRowAType = criteriaXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationECType']";
		
	
		/** configure action */
		
		clickXPath( criteriaXPathRowAType ).
		selectDropDownListItem( criteria.getType() );
		
		if( null != criteria.getValue() ) { typeByXPath( criteriaXPathRowAValue, criteria.getValue() ); }
				
		return this;
		
	}
		
//	public CampaignModelFormOld configureCriteria1( JSONCriteria criteria, Integer eventRow, Integer criteriaRow ) throws JSONException, FormException {
//			
//			String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow cycle1' ) and position() = " + eventRow + " ]//td[@class='column_criteria']";
//			String criteriaXPathRow = eventXPathRow + "//div[@class='criterionContainer']//table/tbody/tr["+ criteriaRow + "]";
//			String criteriaXPathRowAValue = criteriaXPathRow + "//*[@id='gwt-debug-TextCampaignModelCreationECValue']";
//			String criteriaXPathRowAType = criteriaXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationECType']";
//		
//		
//		/** configure action */
//		
//		clickXPath( criteriaXPathRowAType ).
//		selectDropDownListItem( criteria.getType1() );
//		
//		if( null != criteria.getValue1() ) { typeByXPath( criteriaXPathRowAValue, criteria.getValue1() ); }
//		
//		return this;
//		
//	}
//	
//		
//		public CampaignModelFormOld EditconfigureCriteria1( JSONCriteria criteria, Integer eventRow, Integer criteriaRow ) throws JSONException, FormException {
//			
//			String criteriaXPathRowAValue = "//tr[3]/td/table/tbody/tr/td[4]//*[@id='gwt-debug-TextCampaignModelCreationECValue']";
//			String criteriaXPathRowAType =  "//tr[3]/td/table/tbody/tr/td[2]/div/table/tbody/tr//*[@id='gwt-debug-ListCampaignModelCreationECType']";
//			
//			/** configure action */
//		
//			clickXPath( criteriaXPathRowAType ).
//			selectDropDownListItem( criteria.getType2() );
//			if( null != criteria.getValue2() ) { typeByXPath( criteriaXPathRowAValue, criteria.getValue2() ); }
//		
//				
//			return this;
//		
//	}	
		
	
		public CampaignModelFormOld CopyconfigureCriteria(String Value) throws JSONException, FormException {
			
			String criteriaXPathRowAValue = "//tr[3]/td/table/tbody/tr/td[4]//*[@id='gwt-debug-TextCampaignModelCreationECValue']";
			typeByXPath( criteriaXPathRowAValue, Value);
			return this;
		
	}	
		
		public CampaignModelFormOld addActionTime( Integer eventRow, Integer actionRow ) throws FormException {
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRow = eventXPathRow + "//table[@class='commodityContainer']/tbody/tr[" + actionRow + "]";		
		String actionTimeXPathRowAAdd = actionXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationEATime']";
		
		clickXPath( actionTimeXPathRowAAdd );		
		
		return this;
		
	}

	public CampaignModelFormOld saveActionTime() throws FormException {
		
		String actionTimeXPathRowSave = "//div[@class='gwt-DialogBox']//div[@class='Caption' and text()='Time Configuration']/ancestor::tbody//button[@title='OK']";
		
		clickXPath( actionTimeXPathRowSave );
		
		return this;
		
	}
	
	public CampaignModelFormOld abortActionTime() throws FormException {
		
		String actionTimeXPathRowCancel = "//div[@class='gwt-DialogBox']//div[@class='Caption' and text()='Time Configuration']/ancestor::tbody//button[@title='Cancel']";
		
		clickXPath( actionTimeXPathRowCancel );
		
		return this;
		
	}

	public CampaignModelFormOld configureActionTime( JSONActionTime actionTime ) throws FormException {
		
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
	
	public CampaignModelFormOld enableBeneficiary( Integer eventRow ) throws FormException {
		
		Boolean isBeneficiarySelected = isBeneficiarySelected( eventRow );
		
		if( !isBeneficiarySelected ) { this.lastWebElement.click(); }
		
		return this;
		
	}

	public CampaignModelFormOld disableBeneficiary( Integer eventRow ) throws FormException {
		
		Boolean isBeneficiarySelected = isBeneficiarySelected( eventRow );
		
		if( isBeneficiarySelected ) { this.lastWebElement.click(); }
				
		return this;
		
	}
		
	public CampaignModelFormOld selectBeneficiary( Boolean selectBeneficiary, Integer eventRow ) throws FormException {
				
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
	
	public CampaignModelFormOld addNotifications( JSONEvent_ event, Integer eventRow ) throws FormException, JSONException {
				
		if( event.hasNotification() ) {
		
			String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]"; 
			String notificationXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationENAdd']";
			
			clickXPath( notificationXPathRowAAdd ).
			configureNotifications( event.getNotifications() );
			
		}
		
		return this;
		
	}
	
	public CampaignModelFormOld configureNotifications( Map<String, JSONNotification> notifications ) throws FormException {
		
		NotificationForm notificationDialog = new NotificationForm( selenium, notifications, timeout, interval );
		
		//notificationDialog.configureNotifications();

		return this;
		
	}
	
	public CampaignModelFormOld closeErrorDialog() {
		
		
		return this;
		
	}
	
	public CampaignModelFormOld manageErrorAction( String errorAction ) throws FormException, JSONException {
		
		try {
		
			searchByXPath( "//div[contains(text(),'Model name already exist')]", 2000, 50 );
		
		} catch( FormException fe ) {
			
			// no error to manage
			
		}
		
		if( status ) {
			
			switch( ElementErrorActionType.valueOf( errorAction ) ) {
			
				case RETURN_ERROR: {  
					
					throw new FormException( "Error in the form navigation" );
									
				}
				case ABORT_CANCEL: {  
										
					cancelBtn();				
					
					break; 				
				}
				case ADD_TIMESTAMP_TO_FIELD: {  
					
					String name_with_timestamp = campaignModelCfg.getName() + "_" + String.valueOf( TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) );
					
					campaignModelCfg.setName( name_with_timestamp );					
					
					clearByName( "name" ).
					sendKeysByName( "name", campaignModelCfg.getName() ).
					saveCampaignModel();					
					
					break; 				
				}
			
			}
		
		} 
		
		status = true;
		
		return this;
		
	}
	
	private Boolean isActive() throws JSONException {
		
		return this.campaignModelCfg.getEnabled();
		
	}
	
	@Override
	public CampaignModelFormOld clearByName( String name ) throws FormException {
		
		super.clearByName( name );
		
		return this;
		
	}
	
	@Override
	public CampaignModelFormOld clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
		
	}
	
	@Override
	public CampaignModelFormOld clickName( String name ) throws FormException {
		
		super.clickXPath( name );
		
		return this;
		
	}
	
	@Override
	public CampaignModelFormOld clickXPath( String xpath ) throws FormException {
		
		super.clickXPath( xpath );
		
		return this;
		
	}
	
	@Override
	public CampaignModelFormOld sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
		
	}
	
	@Override
	public CampaignModelFormOld sendKeysByName( String name, String text ) throws FormException {
		
		super.sendKeysByName( name, text );
		
		return this;
		
	}
	
	@Override
	public CampaignModelFormOld selectByXPathAndVisibleText( String xpath, String text ) throws FormException {
		
		super.selectByXPathAndVisibleText( xpath, text );
		
		return this;
		
	}
	
	@Override		
	public Form selectByXPathAndValue( String xpath, String value ) throws FormException {
		
		super.selectByXPathAndValue( xpath, value );	
		
		return this;
		
	}
	
	@Override
	public CampaignModelFormOld typeByXPath( String xpath, String text ) throws FormException {
		
		super.typeByXPath( xpath, text );
		
		return this;
		
	}
	
	@Override
	public CampaignModelFormOld selectDropDownListItem( String itemPath ) throws FormException {
		
		super.selectDropDownListItem( itemPath );
		
		return this;		
		
	}
	
	@Override
	public Boolean isCheckedByXPath( String xpath ) throws FormException {
		
		return super.isCheckedByXPath( xpath );
		
	}
	public CampaignModelFormOld selectDropDownListItem( String itemPath, String text ) throws FormException {
		
		super.selectDropDownListItem( itemPath );
		
		return this;		
		
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
		
			List<WebElement> cmLabels = searchListByXPath(rootPath, rootPath + subPath);
			
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
	
	public List<WebElement> getCampaignModelList() throws FormException {

		List<WebElement> campaignModelList = super.getListByXPath(
				"//table[@id='gwt-debug-FormCampaignModel']",
				"//div[contains(@class , 'gwt-Label showPopupLink')]");

		return campaignModelList;

	}

	public Boolean isCampaignModelNameInList(String strCampaignModelName)
			throws FormException {

		List<WebElement> campaignModelList = getCampaignModelList();

		for (WebElement campaignModelEl : campaignModelList) {

			if (campaignModelEl.getText().trim().equals(strCampaignModelName)) {

				return true;

			}

		}

		return false;

	}

	
	

	

	public  boolean editCampaignModel(SeleniumWebDriver selenium, Map<String, Object> campModelName, long timeout, long interval) {
        
		if (campModelName != null) {
			//@SuppressWarnings("unchecked")
			///Map<String, Object> map = (Map<String,Object>) name;
			((WebElement) campModelName.get("edit")).click();
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
