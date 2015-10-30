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
import com.lumata.e4o.gui.common.IForm;
import com.lumata.e4o.gui.common.IFormEvent;
import com.lumata.e4o.gui.common.INotificationForm;
import com.lumata.e4o.gui.common.NotificationForm;
import com.lumata.e4o.gui.common.NotificationForm.NotificationChannel;
import com.lumata.e4o.gui.common.NotificationForm.NotificationTongue;
import com.lumata.e4o.json.gui.campaignmanager.JSONAction;
//import com.lumata.e4o.json.gui.campaignmanager.JSONActionTime;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.campaignmanager.JSONCriteria;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.campaignmanager.JSONNotification;

/**
 * 
 * @author adipasquale
 *
 */
public class CampaignModelForm extends CampaignManagerForm implements IForm, IFormEvent, INotificationForm {

	private static final Logger logger = LoggerFactory.getLogger(CampaignModelForm.class);

	private JSONCampaignModel campaignModelCfg;
	
	private final String CAMPAIGN_MODEL_FORM_XPATH = "//*[@id='gwt-debug-FormCampaignModelCreation']";	
	
	private final String CAMPAIGN_MODEL_EDIT_FORM_XPATH = "//div[text()='Campaign Model Edition']//ancestor::table[2]/tbody/tr[2]";
	
	private final String CAMPAIGN_MODEL_COPY_FORM_XPATH = "//div[text()='Campaign Model Creation']//ancestor::table[2]/tbody/tr[2]";
	
	private final String CAMPAIGN_MODEL_EDIT_FIELD_XPATH = CAMPAIGN_MODEL_EDIT_FORM_XPATH + "//td[contains(text(),{field})]/parent::tr//input";
	
	private final String CAMPAIGN_MODEL_EDIT_BTN_XPATH = CAMPAIGN_MODEL_EDIT_FORM_XPATH + "//button[@title='{btnText}']";
	
	private final String CAMPAIGN_MODEL_COPY_BTN_XPATH = CAMPAIGN_MODEL_COPY_FORM_XPATH + "//button[@title='{btnText}']";
	
	private final String EVENT_ROW_XPATH = "//table[@id='gwt-debug-FormCampaignModelCreationRules']//tr[{rowIndex}]";
	
	private final String EVENT_ROW_EDIT_XPATH = "//div[text()='Event Type']/../../../tr[{rowIndex}]";
	
	private final String EVENT_ROW_ADD_CRITERIA_XPATH = "//div[@class='criterionContainer' and contains(@style, 'border')]/table/tbody/tr/td[3]";
	
	private final String EVENT_CRITERIA_ROW_XPATH = "//div[@class='criterionContainer' {style}]/table/tbody/tr/td/table/tbody/tr[{rowIndex}]";
	
	private final String EVENT_ACTION_ROW_XPATH = "//table[@class='commodityContainer']//tr[{rowIndex}]";
	
	private final String EVENT_ACTION_TIME_XPATH = "//div[@class='gwt-DialogBox']//div[@class='Caption' and text()='Time Configuration']/ancestor::tbody";
	
	private final String EVENT_ACTION_TIME_CONFIGURATION_XPATH = "//div[@class='gwt-DialogBox']//div[@class='Caption' and text()='Time Configuration']//ancestor::tbody";
	
	private final String EVENT_ACTION_TIME_CONFIGURATION_OPTION_XPATH = EVENT_ACTION_TIME_CONFIGURATION_XPATH + "//td[contains(text(),'{actionTimeOption}')]//parent::tr";
	
	private final String EVENT_ACTION_TIME_CONFIGURATION_STARTTIME_XPATH = EVENT_ACTION_TIME_CONFIGURATION_OPTION_XPATH.replace( "{actionTimeOption}" , ActionTimeOption.StartTime.value() );
 	
	private final String EVENT_ACTION_TIME_CONFIGURATION_DURATION_XPATH = EVENT_ACTION_TIME_CONFIGURATION_OPTION_XPATH.replace( "{actionTimeOption}" , ActionTimeOption.Duration.value() ); 
		
	private String currEventRowXPath;
	
	private String currEventCriteriaRowXPath;	
	
	private Integer currEventActionRowIndex;	
	private String currEventActionRowXPath;
	
	private NotificationForm notificationForm;
	
	private boolean editing = false;
	
	
	public enum ActionTimeOption {
		
		StartTime("Start time"),
		Duration("Duration");
		
		String actionTimeOption;
		
		ActionTimeOption( String actionTimeOption ) {
			this.actionTimeOption = actionTimeOption;
		}
		
		public String value() {
			return this.actionTimeOption;
		}
		
	}
	
	public enum ActionTimeDurationType {
		Relative, Absolute;
	}
	
    public enum ElementErrorActionType {

        RETURN_ERROR,
        ABORT_CANCEL,
        ADD_TIMESTAMP_TO_FIELD;

    }; 

	public CampaignModelForm( SeleniumWebDriver selenium, JSONCampaignModel campaignModelCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);

		this.campaignModelCfg = campaignModelCfg;
		
	}

	public CampaignModelForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super(selenium, timeout, interval);

	}
	
	public CampaignModelForm openForm() throws FormException {
		
		super.openForm().clickId( "gwt-debug-InputCMCampaignModel" );
				
		return this;
		
	}
	
	public CampaignModelForm closeForm() throws FormException {
		
		return this;
		
	}
	
	public CampaignModelForm addBtn() throws FormException{
		
		clickId("gwt-debug-BtnCampaignModelAdd");
		
		return this;
		
	}
		
	public CampaignModelForm refreshAllBtn() throws FormException{
		
		clickId("gwt-debug-BtnCampaignModelRefresh");
		
		return this;
		
	}
	
	public CampaignModelForm editBtn( String campaignModelName ) throws FormException{
		
		super.clickXPath("//div[text()='" + campaignModelName + "']//ancestor::tr[1]//td[3]/table/tbody/tr/td[2]//button[@name='btn-edit']");
		
		return this;
	
	}	

	public CampaignModelForm copyBtn( String campaignModelName ) throws FormException{
	
		super.clickXPath("//div[text()='" + campaignModelName + "']//ancestor::tr[1]/td[3]/table/tbody/tr/td[1]/button[@name='btn-copy']");
		
		return this;
		
	}
	
	public CampaignModelForm deleteBtn( String campaignModelName ) throws FormException{
		
		super.clickXPath("//div[text()='" + campaignModelName + "']//ancestor::tr[1]/td/table/tbody/tr/td//button[@name='btn-delete']");
	
		return this;
		
	}
	
	public CampaignModelForm saveBtn() throws FormException {
		
		final String saveCampaignModelButtonXPath = CAMPAIGN_MODEL_FORM_XPATH + "//button[@id='gwt-debug-BtnCampaignModelCreationSave']";
		
		clickXPath( saveCampaignModelButtonXPath );
		
		return this;
		
	}
	
	public CampaignModelForm cancelBtn() throws FormException {
		
		final String cancelCampaignModelButtonXPath = CAMPAIGN_MODEL_FORM_XPATH + "//button[@id='gwt-debug-BtnCampaignModelCreationCancel']";
		
		clickXPath( cancelCampaignModelButtonXPath );
		
		return this;
		
	}

	public CampaignModelForm saveEditBtn() throws FormException {
		
		final String saveCampaignModelEditButtonXPath = CAMPAIGN_MODEL_EDIT_BTN_XPATH.replace("{btnText}", "Save");
		
		clickXPath( saveCampaignModelEditButtonXPath );
		
		return this;
		
	}
	
	public CampaignModelForm saveCopyBtn() throws FormException {
		
		final String saveCampaignModelCopyButtonXPath = CAMPAIGN_MODEL_COPY_BTN_XPATH.replace("{btnText}", "Save");
		
		clickXPath( saveCampaignModelCopyButtonXPath );
		
		return this;
		
	}
		
	public CampaignModelForm cancelEditBtn() throws FormException {
		
		final String cancelCampaignModelEditButtonXPath = CAMPAIGN_MODEL_EDIT_BTN_XPATH.replace("{btnText}", "Cancel");
		
		clickXPath( cancelCampaignModelEditButtonXPath );
		
		return this;
		
	}
	
	public CampaignModelForm editing( Boolean editing ) {
		
		this.editing = editing;
		
		return this;
		
	}
 	
	/**
	 * 
	 * GENERAL
	 * 
	 * @param name
	 * @return
	 * @throws FormException
	 */
	public CampaignModelForm setName(String name) throws FormException{
		
		sendKeysById( "gwt-debug-InputCampaignModelCreationName", name);
		
		return this;
		
	}
	
	public CampaignModelForm editName(String name) throws FormException{
		
		sendKeysByXPath( CAMPAIGN_MODEL_EDIT_FIELD_XPATH.replace( "{field}", "'Campaign Model Name'" ), name);
		
		return this;
		
	}
	
	public String getName() throws FormException{
		
		return getValueById("gwt-debug-InputCampaignModelCreationName");
		
	}
	
	public List<WebElement> getList() throws FormException {

		List<WebElement> campaignModelList = super.getListByXPath(
				"//table[@id='gwt-debug-FormCampaignModel']",
				"//div[contains(@class , 'gwt-Label showPopupLink')]");

		return campaignModelList;

	}
	
	public Boolean isCampaignModelInList(String campaignModelName) throws FormException {

		List<WebElement> campaignModelList = getList();

		for (WebElement campaignModelEl : campaignModelList) {

			if (campaignModelEl.getText().trim().equals(campaignModelName)) {

				return true;

			}

		}

		return false;

	}
	
	public CampaignModelForm setDescription(String description) throws FormException{
		
		sendKeysById( "gwt-debug-InputCampaignModelCreationDescription", description);
		
		return this;
		
	}
	
	public CampaignModelForm editDescription(String description) throws FormException{
		
		sendKeysByXPath( CAMPAIGN_MODEL_EDIT_FIELD_XPATH.replace( "{field}", "'Campaign Model Description'" ), description);
		
		return this;
		
	}
	
	public String getDescription() throws FormException {
		
		return getValueById("gwt-debug-InputCampaignModelCreationDescription");
		
	}
	
	public CampaignModelForm setType( String type ) throws FormException {
		
		if( null != type ) {
			selectByIdAndVisibleText( "gwt-debug-ListCampaignModelCreationType", type );
		}
		
		return this;
		
	}
	
	public CampaignModelForm editType( String type ) throws FormException {
		
		if( null != type ) {
			selectByXPathAndVisibleText( CAMPAIGN_MODEL_EDIT_FIELD_XPATH.replace( "{field}", "'Campaign Type'" ).replace("input", "select"), type );
		}
		
		return this;
		
	}
	
	public String getType() throws FormException{
		
		return getValueById("gwt-debug-ListCampaignModelCreationType");
		
	}
	
	public CampaignModelForm setUseHierarchy( Boolean enabled ) throws FormException {
		
		Boolean checked = isCheckedById( "gwt-debug-CheckCampaignModelCreationUseHierarchy-input" );
		
		if( checked != enabled ) {
			
			clickId("gwt-debug-CheckCampaignModelCreationUseHierarchy-input");
			
		}
				
		return this;
	
	}
	
	public CampaignModelForm editUseHierarchy( Boolean enabled ) throws FormException {
		
		String useHierarchyXPath = CAMPAIGN_MODEL_EDIT_FIELD_XPATH.replace( "{field}", "'Use hierarchy'" );
		
		Boolean checked = isCheckedByXPath( useHierarchyXPath );
		
		if( checked != enabled ) {
			
			clickXPath(useHierarchyXPath);
			
		}
				
		return this;
	
	}
		
	public CampaignModelForm setUseHierarchyEnabled() throws FormException{
		
		setUseHierarchy( true );
		
		return this;
	
	}

	public CampaignModelForm setUseHierarchyDisabled() throws FormException{
		
		setUseHierarchy( false );
		
		return this;
	
	}

	public CampaignModelForm setUseHierarchy() throws FormException{
		
		clickId("gwt-debug-CheckCampaignModelCreationUseHierarchy-input");
		
		return this;
	
	}
	
	/**
	 * 
	 * EVENT
	 * 
	 * @param eventType
	 * @return
	 * @throws FormException
	 */
	public CampaignModelForm addEventBtn() throws FormException {
		
		clickId( "gwt-debug-BtnCampaignModelCreationEventAdd" );
		
		return this;
		
	}
	
	public CampaignModelForm addEventsBtn( Integer numberOfEvents ) throws FormException {
		
		for( int ne = 1; ne <= numberOfEvents; ne++ ) {
			
			addEventBtn();
			
		}
		
		return this;
		
	}
	
	public CampaignModelForm deleteEventBtn() throws FormException {
		
		String deleteEventRowXPath = currEventRowXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationEDel2']";
		
		clickXPath( deleteEventRowXPath );
		
		return this;
		
	}
	
	public CampaignModelForm deleteEventBtn( Integer eventRowIndex ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
		
		deleteEventBtn();
		
		return this;
		
	}

	public CampaignModelForm selectEvent( Integer eventRowIndex ) throws FormException {
		
		if( null == eventRowIndex || eventRowIndex <= 0 ) {
			
			throw new FormException( "No valid event row index. Only values > 0 are accepted " );
			
		}
		
		Integer currEventRowIndex = eventRowIndex + 1;

		if( editing ) {
			
			currEventRowXPath = EVENT_ROW_EDIT_XPATH.replace( "{rowIndex}", String.valueOf( currEventRowIndex ) );
					
		} else {
		
			currEventRowXPath = EVENT_ROW_XPATH.replace( "{rowIndex}", String.valueOf( currEventRowIndex ) );
			
		}
		
		return this;
		
	}
	
	public CampaignModelForm selectEventType( String eventType ) throws FormException {
		
		selectEventType( null, eventType );
		
		return this;
		
	}
	
	public CampaignModelForm selectEventType( Integer eventRowIndex, String eventType ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
		
		String currEventTypeXPath = currEventRowXPath + "//td[@id='gwt-debug-ListCampaignModelCreationETType']";
				
		clickXPath( currEventTypeXPath ).
		selectDropDownListItem( eventType );
		
		return this;
		
	}
	
	/**
	 * 
	 * CRITERIA
	 * 
	 * @throws FormException
	 */
	public CampaignModelForm addCriteriaBtn() throws FormException {
		
		addCriteriaBtn( null );
		
		return this;
		
	}	
	
	public CampaignModelForm addCriteriaBtn( Integer eventRowIndex ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
		
		String currEventActionBtnXPath = currEventRowXPath + EVENT_ROW_ADD_CRITERIA_XPATH + "//button[@id='gwt-debug-BtnCampaignModelCreationECAdd']";
		
		clickXPath( currEventActionBtnXPath );
			
		return this;
		
	}
	
	public CampaignModelForm addCriteriasBtn( Integer numberOfCriteria ) throws FormException {
		
		addCriteriasBtn( null, numberOfCriteria );
				
		return this;
		
	}
	
	public CampaignModelForm addCriteriasBtn( Integer eventRowIndex,  Integer numberOfCriteria ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
				
		for( int nc = 1; nc <= numberOfCriteria; nc++ ) {
			
			addCriteriaBtn();
			
		}
		
		return this;
		
	}
		
	public CampaignModelForm addSubCriteriaBtn() throws FormException {
		
		addSubCriteriaBtn( null, null );
		
		return this;
		
	}	
	
	public CampaignModelForm addSubCriteriaBtn( Integer eventRowIndex, Integer eventCriteriaRowIndex ) throws FormException {
		
		if( null != eventCriteriaRowIndex ) { selectCriteria( eventRowIndex, eventCriteriaRowIndex ); }
		
		String currEventActionBtnXPath = currEventCriteriaRowXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationECAdd']";
		
		clickXPath( currEventActionBtnXPath );
			
		return this;
		
	}
	
	public CampaignModelForm addSubCriteriasBtn( Integer numberOfCriteria ) throws FormException {
		
		addSubCriteriasBtn( null, null, numberOfCriteria );
				
		return this;
		
	}

	public CampaignModelForm addSubCriteriasBtn( Integer eventCriteriaRowIndex, Integer numberOfCriteria ) throws FormException {
		
		addSubCriteriasBtn( null, eventCriteriaRowIndex, numberOfCriteria );
				
		return this;
		
	}

	public CampaignModelForm addSubCriteriasBtn( Integer eventRowIndex, Integer eventCriteriaRowIndex,  Integer numberOfCriteria ) throws FormException {
		
		if( null != eventCriteriaRowIndex ) { selectCriteria( eventRowIndex, eventCriteriaRowIndex ); }
				
		for( int nc = 1; nc <= numberOfCriteria; nc++ ) {
			
			addSubCriteriaBtn();
			
		}
		
		return this;
		
	}
	
	public CampaignModelForm addSubCriteriaContainerBtn() throws FormException {
		
		addSubCriteriaContainerBtn( null, null );
		
		return this;
		
	}	
	
	public CampaignModelForm addSubCriteriaContainerBtn( Integer eventCriteriaRowIndex ) throws FormException {
		
		addSubCriteriaContainerBtn( null, eventCriteriaRowIndex );
		
		return this;
		
	}
	
	public CampaignModelForm addSubCriteriaContainerBtn( Integer eventRowIndex, Integer eventCriteriaRowIndex ) throws FormException {
		
		if( null != eventCriteriaRowIndex ) { selectCriteria( eventRowIndex, eventCriteriaRowIndex ); }
		
		String currEventActionBtnXPath = currEventCriteriaRowXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationECParenthesis']";
		
		clickXPath( currEventActionBtnXPath );
			
		return this;
		
	}
	
	public CampaignModelForm deleteCriteriaBtn() throws FormException {
		
		deleteCriteriaBtn( null, (Integer[])null );
		
		return this;
		
	}	
	
	public CampaignModelForm deleteCriteriaBtn( Integer... eventCriteriaRowIndex ) throws FormException {
		
		deleteCriteriaBtn( null, eventCriteriaRowIndex );
		
		return this;
		
	}
	
	public CampaignModelForm deleteCriteriaBtn( Integer eventRowIndex, Integer... eventCriteriaRowIndex ) throws FormException {
		
		if( null != eventCriteriaRowIndex ) { selectCriteria( eventRowIndex, eventCriteriaRowIndex ); }
		
		String currEventActionBtnXPath = currEventCriteriaRowXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationECDelete']";
		
		clickXPath( currEventActionBtnXPath );
			
		return this;
		
	}
	
	public CampaignModelForm selectCriteria( Integer eventRowIndex, Integer... eventCriteriaRowIndices ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
		
		if( null == eventCriteriaRowIndices ) {
			
			throw new FormException( "No valid criteria row index. Only values > 0 are accepted " );
			
		}
		
		currEventCriteriaRowXPath = currEventRowXPath;
		
		Integer deep = 0;
		
		for( Integer eventCriteriaRowIndex : eventCriteriaRowIndices ) {
			
			deep++;
			
			currEventCriteriaRowXPath = currEventCriteriaRowXPath + 
											EVENT_CRITERIA_ROW_XPATH.
											replace( "{style}", ( deep == 1 ? "and contains(@style, 'border')" : "" ) ).
											replace( "{rowIndex}", String.valueOf( eventCriteriaRowIndex ) );
						
		}
		
		return this;
		
	}

	public CampaignModelForm setCriteriaType( String criteriaType ) throws FormException {
				
		setCriteriaType( null, criteriaType );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaType( String criteriaType, Integer... eventCriteriaRowIndex ) throws FormException {
		
		setCriteriaType( null, criteriaType, eventCriteriaRowIndex );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaType( Integer eventRowIndex, String criteriaType, Integer... eventCriteriaRowIndex ) throws FormException {
		
		if( null != eventCriteriaRowIndex ) { selectCriteria( eventRowIndex, eventCriteriaRowIndex ); }
		
		String criteriaTypeRowXPath = currEventCriteriaRowXPath + "//td[@id='gwt-debug-ListCampaignModelCreationECType']";
		
		clickXPath( criteriaTypeRowXPath ).
		selectDropDownListItem( criteriaType );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaOperator( String criteriaOperator ) throws FormException {
		
		setCriteriaOperator( null, criteriaOperator );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaOperator( String criteriaOperator, Integer eventCriteriaRowIndex ) throws FormException {
		
		
		setCriteriaOperator( null, criteriaOperator, eventCriteriaRowIndex );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaOperator( Integer eventRowIndex, String criteriaOperator, Integer... eventCriteriaRowIndex ) throws FormException {
		
		if( null != eventCriteriaRowIndex ) { selectCriteria( eventRowIndex, eventCriteriaRowIndex ); }
		
		String criteriaOperatorRowXPath = currEventCriteriaRowXPath + "//select[@id='gwt-debug-ListCampaignModelCreationECOperator']";
		
		selectByXPathAndVisibleText( criteriaOperatorRowXPath, criteriaOperator );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaLogicalOperator( String criteriaOperator ) throws FormException {
		
		setCriteriaLogicalOperator( null, criteriaOperator );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaLogicalOperator( String criteriaOperator, Integer eventCriteriaRowIndex ) throws FormException {
		
		setCriteriaLogicalOperator( null, criteriaOperator, eventCriteriaRowIndex );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaLogicalOperator( Integer eventRowIndex, String criteriaOperator, Integer... eventCriteriaRowIndex ) throws FormException {
		
		if( null != eventCriteriaRowIndex ) { selectCriteria( eventRowIndex, eventCriteriaRowIndex ); }
		
		String criteriaLogicalOperatorRowXPath = currEventCriteriaRowXPath + "//select";
		
		selectByXPathAndVisibleText( criteriaLogicalOperatorRowXPath, criteriaOperator );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaValue( String criteriaValue ) throws FormException {
		
		setCriteriaValue( null, criteriaValue );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaValue( String criteriaValue, Integer... eventCriteriaRowIndex ) throws FormException {
		
		setCriteriaValue( null, criteriaValue, eventCriteriaRowIndex );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaValue( Integer eventRowIndex, String criteriaValue, Integer... eventCriteriaRowIndex ) throws FormException {
		
		if( null != eventCriteriaRowIndex ) { selectCriteria( eventRowIndex, eventCriteriaRowIndex ); }
		
		String criteriaValueRowXPath = currEventCriteriaRowXPath + "//input[@id='gwt-debug-TextCampaignModelCreationECValue']";
		
		typeByXPath( criteriaValueRowXPath, criteriaValue );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaOptionValue( String criteriaOptionValue ) {
		
		
		return this;
		
	}

	public CampaignModelForm setCriteriaUnit( String criteriaUnit ) throws FormException {
		
		setCriteriaUnit( null, criteriaUnit );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaUnit( String criteriaUnit, Integer... eventCriteriaRowIndex ) throws FormException {
		
		setCriteriaUnit( null, criteriaUnit, eventCriteriaRowIndex );
		
		return this;
		
	}
	
	public CampaignModelForm setCriteriaUnit( Integer eventRowIndex, String criteriaUnit, Integer... eventCriteriaRowIndex ) throws FormException {
		
		if( null != eventCriteriaRowIndex ) { selectCriteria( eventRowIndex, eventCriteriaRowIndex ); }
		
		String criteriaUnitRowXPath = currEventCriteriaRowXPath + "td[@id='gwt-debug-ListCampaignModelCreationECUnit']";
		
		clickXPath( criteriaUnitRowXPath ).
		selectDropDownListItem( criteriaUnit );
		
		return this;
		
	}
	
	/**
	 * TODO
	 */
//	.//table[@id='gwt-debug-ListCampaignModelCreationECValue']
//	gwt-debug-ListCampaignModelCreationECValue-0 -> boolean
//	gwt-debug-ListCampaignModelCreationECValue-1 -> boolean
	
//	.//*[@id='gwt-debug-BtnCampaignModelCreationECParenthesis']
//	.//*[@id='gwt-debug-BtnCampaignModelCreationECDelete']
//

	
	
	
	/**
	 * 
	 * ACTION
	 * 
	 * @throws FormException
	 */
	public CampaignModelForm addActionBtn() throws FormException {
		
		addActionBtn( null );
		
		return this;
		
	}
	
	public CampaignModelForm addActionBtn( Integer eventRowIndex ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
		
		String currEventActionBtnXPath = currEventRowXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationEAAdd']";
		
		clickXPath( currEventActionBtnXPath );
			
		return this;
		
	}
	
	public CampaignModelForm addActionsBtn( Integer numberOfActions ) throws FormException {
		
		addActionsBtn( null, numberOfActions );
				
		return this;
		
	}
	
	public CampaignModelForm addActionsBtn( Integer eventRowIndex,  Integer numberOfActions ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
				
		for( int na = 1; na <= numberOfActions; na++ ) {
			
			addActionBtn();
			
		}
		
		return this;
		
	}
	
	public CampaignModelForm selectEventAction( Integer eventActionRowIndex ) throws FormException {
		
		if( null == eventActionRowIndex || eventActionRowIndex <= 0 ) {
			
			throw new FormException( "No valid action row index. Only values > 0 are accepted " );
			
		}
		
		currEventActionRowIndex = eventActionRowIndex;
						
		currEventActionRowXPath = currEventRowXPath + EVENT_ACTION_ROW_XPATH.replace( "{rowIndex}", String.valueOf( currEventActionRowIndex ) );
		
		return this;
		
	}
	
	public CampaignModelForm setEventActionValue( String actionValue ) throws FormException {
		
		String currEventActionValueXPath = currEventActionRowXPath + "//*[@id='gwt-debug-TextCampaignModelCreationEAValue']";
		
		typeByXPath( currEventActionValueXPath, actionValue );
	
		return this;
		
	}
	
	public CampaignModelForm setEventActionType( String actionType ) throws FormException {
		
		String currEventActionTypeXPath = currEventActionRowXPath + "//*[@id='gwt-debug-ListCampaignModelCreationEAType']";
		
		clickXPath( currEventActionTypeXPath ).
		selectDropDownListItem( actionType );
	
		return this;
		
	}
	
	public CampaignModelForm setEventActionUnit( String actionUnit ) throws FormException {
		
		String currEventActionUnitXPath = currEventActionRowXPath + "//*[@id='gwt-debug-ListCampaignModelCreationEAUnit']";
		
		selectByXPathAndVisibleText( currEventActionUnitXPath, actionUnit );
	
		return this;
		
	}
	
	public CampaignModelForm setEventActionOption( String actionOption ) throws FormException {
		
		String currEventActionOptionXPath = currEventActionRowXPath + "//*[contains(text(), '::AUTO_ALLOCATE::') ]/parent::select";
		
		selectByXPathAndVisibleText( currEventActionOptionXPath.replace( "::AUTO_ALLOCATE::" , actionOption ), actionOption );
	
		return this;
		
	}
	
	public CampaignModelForm setEventAction( String actionType, String actionValue, String actionUnit, String actionOption ) throws FormException {
		
		setEventAction( null, null, actionType, actionValue, actionUnit );		
		
		return this;
		
	}

	public CampaignModelForm setEventAction( Integer eventActionRowIndex, String actionType, String actionValue, String actionUnit, String actionOption ) throws FormException {
		
		setEventAction( null, eventActionRowIndex, actionType, actionValue, actionUnit, actionOption );		
		
		return this;
		
	}
	
	public CampaignModelForm setEventAction( Integer eventRowIndex, Integer eventActionRowIndex, String actionType, String actionValue, String actionUnit, String actionOption ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
		
		if( null != eventActionRowIndex ) { selectEventAction( eventActionRowIndex ); }
		
		if( null != actionType ) { setEventActionType( actionType ); }
		
		if( null != actionValue ) { setEventActionValue( actionValue ); }
		
		if( null != actionUnit ) { setEventActionUnit( actionUnit ); }		
		
		if( null != actionOption ) { setEventActionOption( actionOption ); }	
		
		return this;
		
	}
	
	public CampaignModelForm deleteEventAction() throws FormException {
		
		deleteEventAction( null, null );
	
		return this;
		
	}

	public CampaignModelForm deleteEventAction( Integer eventActionRowIndex ) throws FormException {
		
		deleteEventAction( null, eventActionRowIndex );
	
		return this;
		
	}
	
	public CampaignModelForm deleteEventAction( Integer eventRowIndex, Integer eventActionRowIndex ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
		
		if( null != eventActionRowIndex ) { selectEventAction( eventActionRowIndex ); }
		
		String currEventActionDeleteXPath = currEventActionRowXPath + "//*[@id='gwt-debug-BtnCampaignModelCreationEADelete']";
		
		clickXPath( currEventActionDeleteXPath );
	
		return this;
		
	}

	/**
	 * 
	 * ACTION TIME
	 * 
	 * @throws FormException
	 */
	public CampaignModelForm addActionTimeBtn() throws FormException {
		
		addActionTimeBtn( null, null );		
		
		return this;
		
	}

	public CampaignModelForm addActionTimeBtn( Integer eventActionRowIndex ) throws FormException {
		
		addActionTimeBtn( null, eventActionRowIndex );		
		
		return this;
		
	}

	public CampaignModelForm addActionTimeBtn( Integer eventRowIndex, Integer eventActionRowIndex ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
		
		if( null != eventActionRowIndex ) { selectEventAction( eventActionRowIndex ); }
		
		String currEventActionTimeXPath = currEventActionRowXPath + "//*[@id='gwt-debug-BtnCampaignModelCreationEATime']";
				
		clickXPath( currEventActionTimeXPath );		
		
		return this;
		
	}

	public CampaignModelForm saveActionTimeBtn() throws FormException {
				
		String eventActionTimeSaveXPath = EVENT_ACTION_TIME_XPATH + "//button[@title='OK']";
		
		clickXPath( eventActionTimeSaveXPath );
		
		return this;
		
	}
	
	public CampaignModelForm cancelActionTimeBtn() throws FormException {
		
		String eventActionTimeCancelXPath = EVENT_ACTION_TIME_XPATH + "//button[@title='Cancel']";
		
		clickXPath( eventActionTimeCancelXPath );
		
		return this;
		
	}
	
	public CampaignModelForm setActionTimeStartTime( String startTimeValue ) throws FormException {
		
		String eventActionTimeStartTimeValueXPath = EVENT_ACTION_TIME_CONFIGURATION_STARTTIME_XPATH + "//select";	
		
		selectByXPathAndVisibleText( eventActionTimeStartTimeValueXPath, startTimeValue );
		
		return this;
		
	}
	
	public CampaignModelForm setActionTimeDurationType( ActionTimeDurationType durationType ) throws FormException {
		
		setActionTimeDurationType( durationType.name() );
		
		return this;
		
	}
	
	public CampaignModelForm setActionTimeDurationType( String durationType ) throws FormException {
		
		String eventActionTimeDurationTypeXPath = EVENT_ACTION_TIME_CONFIGURATION_DURATION_XPATH + "//option[text()='Relative']//parent::select";	
		
		selectByXPathAndVisibleText( eventActionTimeDurationTypeXPath, durationType );
		
		return this;
		
	}

	public CampaignModelForm setActionTimeDurationTypeRelative() throws FormException {
		
		setActionTimeDurationType( ActionTimeDurationType.Relative );
		
		return this;
		
	}
	
	public CampaignModelForm setActionTimeDurationTypeAbsolute() throws FormException {
		
		setActionTimeDurationType( ActionTimeDurationType.Absolute );
		
		return this;
		
	}

	public CampaignModelForm setActionTimeDurationValue( String durationValue ) throws FormException {
		
		String eventActionTimeDurationValueXPath = EVENT_ACTION_TIME_CONFIGURATION_DURATION_XPATH + "//input";	
		
		typeByXPath( eventActionTimeDurationValueXPath, durationValue );
		
		return this;
		
	}
	
	public CampaignModelForm setActionTimeDurationTypeRelativeUnit( String durationRelativeUnit ) throws FormException {
		
		String eventActionTimeDurationRelativeUnitXPath = EVENT_ACTION_TIME_CONFIGURATION_DURATION_XPATH + "//option[text()='Hours']//parent::select";	
		
		selectByXPathAndVisibleText( eventActionTimeDurationRelativeUnitXPath, durationRelativeUnit );
		
		return this;
		
	}
	
	public CampaignModelForm setActionTimeDurationTypeAbsoluteUnit( String durationAbsoluteUnit ) throws FormException {
		
		String eventActionTimeDurationAbsoluteUnitXPath = EVENT_ACTION_TIME_CONFIGURATION_DURATION_XPATH + "//option[text()='00:00:00']//parent::select";	
		
		selectByXPathAndVisibleText( eventActionTimeDurationAbsoluteUnitXPath, durationAbsoluteUnit );
		
		return this;
		
	}

	public CampaignModelForm setActionTimeDurationRelative( String durationValue, String durationRelativeUnit ) throws FormException {
	
		setActionTimeDurationTypeRelative();
		
		setActionTimeDurationValue( durationValue );
		
		setActionTimeDurationTypeRelativeUnit( durationRelativeUnit );
		
		return this;
		
	}

	public CampaignModelForm setActionTimeDurationAbsolute( String durationValue, String durationAbsoluteUnit ) throws FormException {
		
		setActionTimeDurationTypeAbsolute();
		
		setActionTimeDurationValue( durationValue );
		
		setActionTimeDurationTypeAbsoluteUnit( durationAbsoluteUnit );
		
		return this;
		
	}

	/**
	 * 
	 * BENEFICIARY
	 * 
	 * @throws FormException
	 */	
	public Boolean isBeneficiaryEnabled() throws FormException {
						
		return isBeneficiaryEnabled( null );
		
	}
	
	public Boolean isBeneficiaryEnabled( Integer eventRowIndex ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
		
		String beneficiaryXPath = currEventRowXPath + "//input[@id='gwt-debug-CheckCampaignModelCreationEBInput-input']";
		
		return isCheckedByXPath( beneficiaryXPath );
		
	}
	
	public CampaignModelForm setBeneficiary( Integer eventRowIndex, Boolean beneficiaryEnabled ) throws FormException {
		
		Boolean isBeneficiaryEnabled = isBeneficiaryEnabled( eventRowIndex );
		
		String beneficiaryXPath = currEventRowXPath + "//input[@id='gwt-debug-CheckCampaignModelCreationEBInput-input']";
		
		if( isBeneficiaryEnabled != beneficiaryEnabled ) { clickXPath( beneficiaryXPath ); }
		
		return this;
		
	}
	
	public CampaignModelForm setBeneficiaryEnabled() throws FormException {
		
		setBeneficiary( null, true );
		
		return this;
		
	}
	
	public CampaignModelForm setBeneficiaryEnabled( Integer eventRowIndex ) throws FormException {
		
		setBeneficiary( eventRowIndex, true );
		
		return this;
		
	}

	public CampaignModelForm setBeneficiaryDisabled() throws FormException {
		
		setBeneficiary( null, false );
		
		return this;
		
	}
	
	public CampaignModelForm setBeneficiaryDisabled( Integer eventRowIndex ) throws FormException {
		
		setBeneficiary( eventRowIndex, false );
		
		return this;
		
	}
		
	/**
	 *  
	 * NOTIFICATION DIALOG
	 * 
	 **/
	public CampaignModelForm openDialogueNotification() throws FormException {
				
		this.notificationForm = new NotificationForm( this.selenium, this.timeout, this.interval ); 
		
		String campaignModelDialogueNotificationXPath = currEventRowXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENAdd']";
		
		clickXPath( campaignModelDialogueNotificationXPath );
		
		return this;
		
	}
	
	public CampaignModelForm openDialogueNotification( Integer eventRowIndex ) throws FormException {
		
		if( null != eventRowIndex ) { selectEvent( eventRowIndex ); }
		
		openDialogueNotification();
		
		return this;
		
	}
	
	public CampaignModelForm saveDialogueNotification() throws FormException {
		
		this.notificationForm.saveBtn();
	
		return this;
		
	}
	
	public CampaignModelForm cancelDialogueNotification() throws FormException {
		
		this.notificationForm.cancelBtn();
	
		return this;
		
	}
	
	public CampaignModelForm editDialogueNotification( NotificationTongue tongue, NotificationChannel channel ) throws FormException {
		
		this.notificationForm.editBtn( tongue.value(), channel.name() );
		
		return this;
		
	}
	
	public CampaignModelForm editDialogueNotification( String tongue, String channel ) throws FormException {
		
		this.notificationForm.editBtn( tongue, channel );	
		
		return this;
		
	}
	
	public CampaignModelForm deleteDialogueNotification( NotificationTongue tongue, NotificationChannel channel ) throws FormException {
		
		this.notificationForm.deleteBtn( tongue.value(), channel.name() );
		
		return this;
		
	}
	
	public CampaignModelForm deleteDialogueNotification( String tongue, String channel ) throws FormException {
		
		this.notificationForm.deleteBtn( tongue, channel );	
		
		return this;
		
	}
	
	public CampaignModelForm importDialogueNotification( NotificationTongue tongue, NotificationChannel channel ) throws FormException {
		
		this.notificationForm.importBtn( tongue.value(), channel.name() );
		
		return this;
		
	}
	
	public CampaignModelForm importDialogueNotification( String tongue, String channel ) throws FormException {
		
		this.notificationForm.importBtn( tongue, channel );	
		
		return this;
		
	}
	
	public CampaignModelForm saveDialogueNotificationEditing() throws FormException {
		
		this.notificationForm.saveEdititingBtn();
	
		return this;
		
	}

	public CampaignModelForm saveTemplateDialogueNotificationEditing() throws FormException {
		
		this.notificationForm.saveTemplateEditingBtn();
	
		return this;
		
	}

	public CampaignModelForm cancelDialogueNotificationEditing() throws FormException {
		
		this.notificationForm.cancelEditingBtn();
	
		return this;
		
	}
	
	public CampaignModelForm setDialogueNotificationMessage( String message ) throws FormException {
		
		this.notificationForm.setMessage( message );
		
		return this;
		
	}
	
	/**
	* Click on campaign model save and accept if alert popup is displayed
	*
	* @throws FormException
	*/
	public CampaignModelForm confirmSaving() throws FormException {
		
		try {
			
			Alert confirmLogout = selenium.selectAlert();
			
			if ( confirmLogout != null ) { confirmLogout.accept(); }
		
		} catch (NoAlertPresentException e) {
			
			status = true;
		
		}
		
		return this;
	
	}
	
	public CampaignModelForm addGeneralInfo( JSONCampaignModel campaignModel ) throws FormException, JSONException {
		
		return addGeneralInfo( campaignModel, campaignModel.getName() );
		
	}
	
	public CampaignModelForm addGeneralInfo( JSONCampaignModel campaignModel, String name ) throws FormException, JSONException {
		
		if( !campaignModel.getEdit() ) { 
			setName( name ); 
			setDescription( campaignModel.getDescription() ).
			setType( campaignModel.getType() ).
			setUseHierarchy( campaignModel.getuseHierarchy() );
		} else {
			//editName( name ).
			editDescription( campaignModel.getDescription() ).
			editType( campaignModel.getType() ).
			editUseHierarchy( campaignModel.getuseHierarchy() );
		}
		
		return this;

	}

	public CampaignModelForm addEvents(Map<String, JSONEvent_> events) throws JSONException, FormException {
		
		int eventRow = 0;
		
		for( String eventName : events.keySet() ) {
			
			eventRow++;
			
			if( !events.get( eventName ).getEdit() ) { 
				
				addEventBtn(); 
				
			}
		
			configureEvent( events.get( eventName ), eventRow );
			
		}
				
		return this;
		
	}
	
	public CampaignModelForm configureEvent( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {
		
		editing( event.getEdit() ).
		selectEvent( eventRow ).
		selectEventType( event.getEventType() ).	
		configureCriterias( event, eventRow ).		
		configureActions( event, eventRow ).
		setBeneficiary( eventRow, event.getBeneficiary() );
		
		//addNotifications( event, eventRow );
		
		return this;
		
	}
	
	public CampaignModelForm configureCriterias( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {

		Map<String, JSONCriteria> criteria = event.getCriteria();
		
		int criteriaRow = -1;
		
		for( String criteriaName : criteria.keySet() ) {
			
			if( event.getEdit() || !criteria.get( criteriaName ).getEdit() ) { 
				
				addCriteriaBtn( eventRow ); 
				
			}
			
			criteriaRow+=2;
			
			configureCriteria( criteria.get( criteriaName ), eventRow, criteriaRow );
												
		}
		
		return this;
		
	}
	
	public CampaignModelForm configureCriteria( JSONCriteria criteria, Integer eventRow, Integer criteriaRow ) throws JSONException, FormException {

		setCriteriaType( eventRow, criteria.getType(), criteriaRow ).
		setCriteriaOperator( eventRow, criteria.getOperator(), criteriaRow ).
		setCriteriaValue( eventRow, criteria.getValue(), criteriaRow );
		
		return this;
		
	}

	public CampaignModelForm configureActions( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {

		Map<String, JSONAction> actions = event.getActions();
		
		int actionRow = 0;
		
		for( String actionName : actions.keySet() ) {
					
			if( event.getEdit() || !actions.get( actionName ).getEdit() ) { 
				
				addActionBtn( eventRow ); 
						
			}
			
			actionRow++;
			
			configureAction( actions.get( actionName ), eventRow, actionRow );
			
		}
		
		return this;
		
	}	

	public CampaignModelForm configureAction( JSONAction action, Integer eventRow, Integer actionRow ) throws JSONException, FormException {
	
		setEventAction( eventRow, actionRow, action.getName(), action.getValue(), action.getUnit(), action.getOption() );
		
		/** TODO **/
//		/** configure action time */
//		if( action.hasActionTime() ) {
//		
//		addActionTimeBtn( eventRow, actionRow ).
//		configureActionTime( action.getActionTime() ).
//		saveActionTimeBtn();				
//						
//		}
//		
//		/** configure action */
//		clickXPath( actionXPathRowAType ).
//		selectDropDownListItem( action.getName() );
//		
//		if( null != action.getValue() ) { typeByXPath( actionXPathRowAValue, action.getValue() ); }
//		
//		if( null != action.getOption() ) { 
//		
//		if( action.getName().matches( ruleActionPattern ) ) {
//			
//			selectByXPathAndVisibleText( actionXPathRowAAutoAllocation.replace( "::AUTO_ALLOCATE::" , action.getOption() ), action.getOption() );
//			
//		} else { selectByXPathAndVisibleText( actionXPathRowAUnit, action.getOption() ); }
//		
//		}
			
		return this;

	}

	/** TODO **/
//	public CampaignModelForm configureActionTime( JSONActionTime actionTime ) throws FormException {
//		
//		String actionTimeXPathRowStartTime = "//div[@class='gwt-DialogBox']//td[contains(text(),'Start time')]//parent::tr/td/select";
//		String actionTimeXPathRowDurationType = "//div[@class='gwt-DialogBox']//td[contains(text(),'Duration')]//parent::tr[@class='cycle2']//tbody/tr/td[1]/select";
//		String actionTimeXPathRowDurationValue = "//div[@class='gwt-DialogBox']//td[contains(text(),'Duration')]//parent::tr[@class='cycle2']//tbody/tr/td[2]/input";
//		String actionTimeXPathRowDurationTimeType = "//div[@class='gwt-DialogBox']//td[contains(text(),'Duration')]//parent::tr[@class='cycle2']//tbody/tr/td[3]/select";
//		
//		selectByXPathAndVisibleText( actionTimeXPathRowStartTime, actionTime.getStarTime() ).
//		selectByXPathAndVisibleText( actionTimeXPathRowDurationType, actionTime.getDurationType() ).
//		typeByXPath( actionTimeXPathRowDurationValue, actionTime.getDurationValue() ).
//		selectByXPathAndVisibleText( actionTimeXPathRowDurationTimeType, actionTime.getDurationTimeType() );
//		
//		return this;
//		
//	}

	public CampaignModelForm addNotifications( JSONEvent_ event, Integer eventRow ) throws FormException, JSONException {
				
		if( event.hasNotification() ) {
		
			String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]"; 
			String notificationXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationENAdd']";
			
			clickXPath( notificationXPathRowAAdd );
			//configureNotifications( event.getNotifications() );
			
		}
		
		return this;
		
	}
	
	/**
	 * 
	 * TODO
	 * 
	 * @param notifications
	 * @return
	 * @throws FormException
	 */
	public CampaignModelForm configureNotifications( Map<String, JSONNotification> notifications ) throws FormException {
		
		@SuppressWarnings("unused")
		NotificationForm notificationDialog = new NotificationForm( selenium, notifications, timeout, interval );
		
		//notificationDialog.configureNotifications();

		return this;
		
	}
	
	public CampaignModelForm closeErrorDialog() {
		
		
		return this;
		
	}
	
	public CampaignModelForm manageErrorAction( String errorAction ) throws FormException, JSONException {
		
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
					sendKeysByName( "name", campaignModelCfg.getName() );
					//saveCampaignModel();					
					
					break; 				
				}
			
			}
		
		} 
		
		status = true;
		
		return this;
		
	}
	
	@SuppressWarnings("unused")
	private Boolean isActive() throws JSONException {
		
		return this.campaignModelCfg.getEnabled();
		
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

}
