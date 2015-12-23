package com.lumata.e4o.gui.campaignmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.common.PlaceHolderDate;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.GWTCalendarForm;
import com.lumata.e4o.gui.common.IForm;
import com.lumata.e4o.gui.common.IFormWizard;
import com.lumata.e4o.gui.common.INotificationForm;
import com.lumata.e4o.gui.common.NotificationForm;
import com.lumata.e4o.gui.common.NotificationForm.NotificationChannel;
import com.lumata.e4o.gui.common.NotificationForm.NotificationTongue;
import com.lumata.e4o.json.gui.campaignmanager.CampaignCfg;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaign_;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaigns;
import com.lumata.e4o.json.gui.campaignmanager.JSONCriteria;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;


public class CampaignsForm extends CampaignManagerForm implements IForm, IFormWizard, INotificationForm {

	private JSONCampaigns campaignCfg;
	private NotificationForm notificationForm;
	public JSONCampaigns campaign;
	
	private enum WizardTab {
		Definition, Scheduling, Dialogue, Target, Limits, Activation
	}
	
	public enum ExecutionMode {
		Model, Notification, Rule
	}

	public enum SchedulingType {
		Single, Multiple
	}
	
	public enum SchedulingExecutionEndType {
		Relative, Absolute
	}
	
	private enum SchedulingMultipleRecurrencePattern {
		Weekly, Monthly
	}
	
	public enum SchedulingMultipleRecurrencePatternWeekly {
		M, T, W, Th, F, Sa, Su
	}
	
	private enum SchedulingMultipleRangeOfRecurrence {
		
		NoEndDate("No end date"), 
		EndAfterNOccurences("End after n occurences"), 
		EndDate("End date");
		
		String value;
		
		SchedulingMultipleRangeOfRecurrence( String value ) {
			this.value = value;
		}
		
		public String value() {
			return value;
		}
		
	}
	
	private enum SchedulingMultipleMonthlyDaySetting {
		SimpleDay, GeneralDay
	}
	
	public enum SchedulingMultipleMonthlyGeneralDayOrdinalDayOfWeek {
		First, Second, Third, Fourth, Last
	}
	
	public enum SchedulingMultipleMonthlyGeneralDayOfWeek {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}
	
	public enum TargetingMode {
		Restricted, Opened
	}
		
	public enum TargetingRestrictedMode {
		
		Criteria("Criteria"), 
		ImportSubscribers("Import subscribers"), 
		CriteriaIntersectionImport("Criteria Intersection Import"),
		CriteriaUnionImport("Criteria Union Import"),
		ManualTargeting("Manual Targeting");
		
		String value;
		
		TargetingRestrictedMode( String value ) {
			this.value = value;
		}
		
		public String value() {
			return value;
		}

	}
	
	public enum TargetingSampleType {
		
		NoSample("No sample"), 
		ControlSample("Control sample"), 
		TestSample("Test sample");
		
		String value;
		
		TargetingSampleType( String value ) {
			this.value = value;
		}
		
		public String value() {
			return value;
		}
		
	}
	
	public enum ActivationAction {

		Previous, Next, Cancel, Save, Activate;

	};
	
	public enum CampaignErrorMessage {
		
		CampaignNameEmpty("Campaign name field cannot be empty.");
		
		String message;
		
		CampaignErrorMessage( String message ) {
			this.message = message;			
		}
		
		public String message() {
			return message;
		}
		
	}
	
	public enum CMErrorAction {

		MODEL_ALREADY_EXISTS;

	};

	public enum CMErrorActionType {

		RETURN_ERROR, ABORT, ADD_TIMESTAMP_TO_MODEL_NAME;

	};

	public CampaignsForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
				
	}
	
	public CampaignsForm( SeleniumWebDriver selenium, JSONCampaigns campaignCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.campaignCfg = campaignCfg;
		
	}
	
	public CampaignsForm openForm() throws FormException {
		
		super.openForm();
		/*fix done on 15th Dec 2015 to make the element visible*/
		super.scrollTo(selenium.getWrappedDriver().findElement(By.id("gwt-debug-InputCMCampaignCreation")));
		super.clickId( "gwt-debug-InputCMCampaignCreation" );
		
		return this;
		
	}
	
	public CampaignsForm closeForm() throws FormException {
		
		return this;
		
	}
	
	public CampaignsForm addBtn() throws FormException {
		
		clickId( "gwt-debug-Add Campaign" );
		
		return this;
		
	}

	public CampaignsForm refreshAllBtn() throws FormException {
		
		String refreshAllBtnXPath = "//table[contains(@class, 'CampaignActivationTab')]//button[@name='btn-refresh']//span[text()='Refresh']//parent::button";
		
		clickXPath( refreshAllBtnXPath );
		
		return this;
		
	}

	/**
	 * TODO
	 * @return
	 * @throws FormException
	 */
	public CampaignsForm refreshBtn() throws FormException {
		
		// //table[contains(@class, 'CampaignActivationTab')]//button[@name='btn-refresh']
				
//		String refreshBtnXPath = "//table[contains(@class, 'CampaignActivationTab')]//button[@name='btn-refresh']//span[text()='Refresh']//parent::button";
//		
//		clickXPath( refreshBtnXPath );
		//div[text()='CMExtPrice1']
//		
		return this;
		
	}
	
	public CampaignsForm previousBtn() throws FormException {
		
		actionBtn( ActivationAction.Previous );
		
		return this;
		
	}
	
	public CampaignsForm nextBtn() throws FormException {
		
		actionBtn( ActivationAction.Next );
		
		return this;
		
	}

	public CampaignsForm cancelBtn() throws FormException {
		
		actionBtn( ActivationAction.Cancel );
		
		return this;
		
	}

	public CampaignsForm saveBtn() throws FormException {
		
		actionBtn( ActivationAction.Save );
		
		return this;
		
	}

	public CampaignsForm activateBtn() throws FormException {
		
		actionBtn( ActivationAction.Activate );
		
		return this;
		
	}
    
	public CampaignsForm addCampaigns() throws FormException, JSONException {
		
		Integer campaignsQty = campaignCfg.size();
		
		for( int campaignIndex = 0; campaignIndex < campaignsQty; campaignIndex++ ) {
			
			campaignCfg.cursor( campaignIndex );
			
			if( campaignCfg.isEnabled() ) {
			
				addBtn().
				configureCampaign();
				
			}					
			
		}
		
		return this;
		
	}

	/**
	 * 
	 * CAMPAIGN CONFIGURATION
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONException
	 */
	public CampaignsForm configureCampaign() throws FormException, JSONException {
		
		configureCampaignDefinition().
		configureCampaignScheduling().
		configureCampaignDialogue().
		configureCampaignTarget();
		configureCampaignLimits().
		configureCampaignActivation();

		return this;

	}

	/**
	 * 
	 * DEFINITION
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONException
	 */
	public CampaignsForm configureCampaignDefinition() throws FormException, JSONException {
							
		openDefinitionTab().
		setCampaignExecutionMode( ExecutionMode.valueOf( campaignCfg.executionMode() ) ).
		setCampaignName( campaignCfg.name() ).
		setCampaignDescription( campaignCfg.description() ).
		setCampaignModel( campaignCfg.campaignModel() ).	
		setCampaignType( campaignCfg.campaignType() ).		
		setByPassMediaType( campaignCfg.byPassMediaType() );
						
		return this;
		
	}
	
	public CampaignsForm openDefinitionTab() throws FormException {
		
		clickXPath( getWizardTabXPath( WizardTab.Definition ) );
		
		return this;
		
	}
	
	public CampaignsForm setCampaignExecutionMode( ExecutionMode campaignExecutionMode ) throws FormException {
		
		String executionModeXPath = "//span[contains(@id, 'gwt-debug-Campaign " + campaignExecutionMode + "' )]/input";
		
		clickXPath( executionModeXPath );
			
		return this;
		
	}

	
	public CampaignsForm configureCampaigns(String name, String description,String type, Boolean boolean1) throws FormException, JSONException {
		
		searchByName(name);
		if(null!=description)
			setCampaignDescription(description);
		if(null!=type)
			setCampaignType(type);
				return this;

	}

	
	public CampaignsForm setCampaignExecutionModeModel() throws FormException {
		
		setCampaignExecutionMode( ExecutionMode.Model );
			
		return this;
		
	}

	public CampaignsForm setCampaignExecutionModeNotification() throws FormException {
		
		setCampaignExecutionMode( ExecutionMode.Notification );
			
		return this;
		
	}

	public CampaignsForm setCampaignExecutionModeRule() throws FormException {
		
		setCampaignExecutionMode( ExecutionMode.Rule );
			
		return this;
		
	}

	
	public CampaignsForm setCampaignModel( String campaignModel ) throws FormException {
	
		//String campaignModeValueXPath = "//select[@id='gwt-debug-Campaign Model Select in Campaign']";
		
		selectByIdAndVisibleText("gwt-debug-Campaign Model Select in Campaign", campaignModel );
		
		return this;
		
	}
	
	public CampaignsForm setCampaignName( String campaignName ) throws FormException {
	
		String campaignNameXPath = "//input[@id='gwt-debug-Campaign Name']";
		
		typeByXPath( campaignNameXPath, campaignName );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignDescription( String campaignDescription ) throws FormException {
		
		String campaignDescriptionXPath = "//textarea[@id='gwt-debug-Campaign Description']";
		
		typeByXPath( campaignDescriptionXPath, campaignDescription );
		
		return this;
		
	}

	public CampaignsForm setCampaignType( String campaignType ) throws FormException {
		
		String campaignTypeXPath = "//td[@class='headers' and text()='Campaign Type']/parent::tr//select";
		
		if( null != campaignTypeXPath ) { selectByXPathAndVisibleText( campaignTypeXPath, campaignTypeXPath ); }
				
		return this;
		
	}
	
	public CampaignsForm setByPassMediaType( Boolean campaignByPassMediaType ) throws FormException {
		
		String byPassMediaTypeXPath = "//td[@class='headers' and text()='Bypass Meta Type']/parent::tr//input";
		
		if( campaignByPassMediaType ) { clickXPath( byPassMediaTypeXPath ); }
		
		return this;
		
	}
	
	/**
	 * 
	 * SCHEDULING
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONException
	 */
	public CampaignsForm configureCampaignScheduling() throws FormException, JSONException {
				
		openSchedulingTab().
		setCampaignSchedulingTypeOfRecurrenceXPath( SchedulingType.valueOf( campaignCfg.schedulingTypeOfRecurrence() ) );
		
		switch( SchedulingType.valueOf( campaignCfg.schedulingTypeOfRecurrence() ) ) {
		
			case Single: {
				configureCampaignSingleScheduling();				
				break;
			}
			case Multiple: {
				configureCampaignMultipleScheduling();				
				break;
			}
		}
		
		return this;
		
	}
	
	/**
	 * From datestring contained into JSON identify if :
	 * <li> plain date string
	 * <li> placeholder
	 * 
	 * and convert it into final written data
	 * 
	 * @param StringDateField
	 * @return
	 * @throws FormException 
	 */
	private Calendar resolveDateField(String StringDateField) throws FormException {
		
		Calendar date = Calendar.getInstance();
		if( PlaceHolderDate.getInstance( StringDateField ).isPlaceHolderDate() )
			date = PlaceHolderDate.getInstance( StringDateField ).parse();						
		else 		
			try {
				date.setTime( new SimpleDateFormat("yyyy-MM-dd").parse( StringDateField ) );
			} catch (ParseException e) {
				throw new FormException("Error during SingleExecutionStart parsing " + StringDateField);
			}
				
		return date;
	}
	
	public CampaignsForm configureCampaignSingleScheduling() throws FormException, JSONException {
		
		setCampaignSingleSchedulingExecutionStart( campaignCfg.schedulingSingleExecutionStart() ).
		setCampaignSingleSchedulingExecutionEndType( SchedulingExecutionEndType.valueOf( campaignCfg.schedulingSingleExecutionEndType() ), campaignCfg.schedulingSingleExecutionEndValue() ).		
		setCampaignSingleSchedulingProvisioningEndDate( campaignCfg.schedulingSingleProvisioningEnd() ).
		setCampaignSingleSchedulingProvisioningStartDate( campaignCfg.schedulingSingleProvisioningStart() ).
		setCampaignSchedulingSingleDaysBetweenProvisioningAndExecutionStartDates( campaignCfg.schedulingSingleDaysBetweenProvisioningAndStartDates() );		
		
		return this;
		
	}
	
	public CampaignsForm openSchedulingTab() throws FormException {
		
		clickXPath( getWizardTabXPath( WizardTab.Scheduling ) );
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingTypeOfRecurrenceXPath( SchedulingType schedulingType ) throws FormException {
		
		String campaignSchedulingTypeOfRecurrenceXPath = "//td[@class='headers' and contains( text(), 'Type of Recurrence' )]/parent::tr//select";
		
		selectByXPathAndVisibleText( campaignSchedulingTypeOfRecurrenceXPath, schedulingType.name() );
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSingleSchedulingType() throws FormException {
		
		setCampaignSchedulingTypeOfRecurrenceXPath( SchedulingType.Single );
		  		 
		return this;
		
	}

	public CampaignsForm setCampaignMultipleSchedulingType() throws FormException {
		
		setCampaignSchedulingTypeOfRecurrenceXPath( SchedulingType.Multiple );
		  		 
		return this;
		
	}
	
	public CampaignsForm setCampaignSingleSchedulingExecutionStart( Calendar executionStartDate ) throws FormException {
		
		try {
			
			setCampaignSingleSchedulingExecutionStart( Format.getMysqlDateTime( executionStartDate ) );
		
		} catch (ParseException e) {
			
			throw new FormException( e.getMessage(), e );
			
		}
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSingleSchedulingExecutionStart( String executionStartDate ) throws FormException {
		
		String campaignSchedulingSingleExecutionStartXPath = "//td[@class='headers' and text()='Execution Start']/parent::tr//input";
		
		configureGWTCalendarByXPath( campaignSchedulingSingleExecutionStartXPath, resolveDateField( executionStartDate ) );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSingleSchedulingExecutionEndType( SchedulingExecutionEndType executionEndType, String executionEndDate ) throws FormException {
		
		switch( executionEndType ) {
		
			case Relative: {
				setCampaignSingleSchedulingExecutionEndRelative( executionEndDate );				
				break;
			}
			case Absolute: {
				setCampaignSingleSchedulingExecutionEndAbsolute( executionEndDate );
				break;
			}
		}
		
		return this;
		
	}

	public CampaignsForm setCampaignSingleSchedulingExecutionEndRelative( Integer executionEndDate ) throws FormException {
		
		setCampaignSingleSchedulingExecutionEndRelative( String.valueOf( executionEndDate ) );				
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSingleSchedulingExecutionEndRelative( String executionEndDate ) throws FormException {
		
		String campaignSchedulingSingleExecutionEndTypeXPath = "//td[@class='headers' and text()='Execution End']/parent::tr//select";
		String campaignSchedulingSingleExecutionEndValueXPath = "//td[@class='headers' and text()='Execution End']/parent::tr//input";
		
		selectByXPathAndVisibleText( campaignSchedulingSingleExecutionEndTypeXPath, CampaignsForm.SchedulingExecutionEndType.Relative.name() );
				
		typeByXPath( campaignSchedulingSingleExecutionEndValueXPath, executionEndDate );				
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSingleSchedulingExecutionEndAbsolute( Calendar executionEndDate ) throws FormException {
		
		try {
			
			setCampaignSingleSchedulingExecutionEndAbsolute( Format.getMysqlDateTime( executionEndDate ) );
		
		} catch (ParseException e) {
			
			throw new FormException( e.getMessage(), e );
			
		}
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSingleSchedulingExecutionEndAbsolute( String executionEndDate ) throws FormException {
		
		String campaignSchedulingSingleExecutionEndTypeXPath = "//td[@class='headers' and text()='Execution End']/parent::tr//select";
		String campaignSchedulingSingleExecutionEndValueXPath = "//td[@class='headers' and text()='Execution End']/parent::tr//input";
		
		selectByXPathAndVisibleText( campaignSchedulingSingleExecutionEndTypeXPath, CampaignsForm.SchedulingExecutionEndType.Absolute.name() );
				
		configureGWTCalendarByXPath( campaignSchedulingSingleExecutionEndValueXPath, resolveDateField( executionEndDate ) );				
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSingleSchedulingProvisioningStartDate( Calendar provisioningStartDate ) throws FormException {
		
		try {
			
			setCampaignSingleSchedulingProvisioningStartDate( Format.getMysqlDateTime( provisioningStartDate ) );
		
		} catch (ParseException e) {
			
			throw new FormException( e.getMessage(), e );
			
		}
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSingleSchedulingProvisioningStartDate( String provisioningStartDate ) throws FormException {
		
		String campaignSchedulingSingleProvisioningStartXPath = "//td[@class='headers' and text()='Provisioning Start']/parent::tr//input";
		
		configureGWTCalendarByXPath( campaignSchedulingSingleProvisioningStartXPath, resolveDateField( provisioningStartDate ) );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSingleSchedulingProvisioningEndDate( Calendar provisioningEndDate ) throws FormException {
		
		try {
			
			setCampaignSingleSchedulingProvisioningEndDate( Format.getMysqlDateTime( provisioningEndDate ) );
		
		} catch (ParseException e) {
			
			throw new FormException( e.getMessage(), e );
			
		}
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSingleSchedulingProvisioningEndDate( String provisioningEndDate ) throws FormException {
		
		String campaignSchedulingSingleProvisioningEndXPath = "//td[@class='headers' and text()='Provisioning End']/parent::tr//input";
		
		configureGWTCalendarByXPath( campaignSchedulingSingleProvisioningEndXPath, resolveDateField( provisioningEndDate ) );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingSingleDaysBetweenProvisioningAndExecutionStartDates( Integer daysBetweenProvisioningAndExecutionStartDates ) throws FormException {
		
		setCampaignSchedulingSingleDaysBetweenProvisioningAndExecutionStartDates( String.valueOf( daysBetweenProvisioningAndExecutionStartDates ) );
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingSingleDaysBetweenProvisioningAndExecutionStartDates( String daysBetweenProvisioningAndExecutionStartDates ) throws FormException {
		
		String campaignSchedulingSingleDaysBetweenProvisioningAndExecutionStartDates = "//td[@class='headers' and text()='Days between provisioning and execution start dates']/parent::tr//input";
		
		typeByXPath( campaignSchedulingSingleDaysBetweenProvisioningAndExecutionStartDates, daysBetweenProvisioningAndExecutionStartDates );
				
		return this;
		
	}
	
	public CampaignsForm configureCampaignMultipleScheduling() throws FormException, JSONException {
				
		setCampaignSchedulingMultipleRecurrencePattern( campaignCfg.schedulingMultipleRecurrencePattern() ).		
		setCampaignSchedulingMultipleDaysBetweenProvisioningStartDateAndExecutionStartDate( campaignCfg.schedulingMultipleDaysBetweenProvisioningAndExecutionStartDate() );
		
		/** to check relative and absolute **/
		setCampaignSchedulingMultipleExecutionPeriod( campaignCfg.schedulingMultipleExecutionPeriod() );
		
		switch( SchedulingMultipleRecurrencePattern.valueOf( campaignCfg.schedulingMultipleRecurrencePattern() ) ) {
		
			case Weekly: {
				
				setCampaignSchedulingMultipleRecurrencePatternOccurrence( campaignCfg.schedulingMultipleRecurrencePatternWeeklyRecurEveryWeek() ).
				setCampaignSchedulingMultipleProvisioningDuration( campaignCfg.schedulingMultipleProvisioningDuration() ).
				setCampaignSchedulingMultipleRecurrencePatternDaysOfWeek( campaignCfg.schedulingMultipleRecurrencePatternWeeklyRecurEveryDay()  );
				
				break;
			}
			case Monthly: {
				
				setCampaignSchedulingMultipleRecurrencePatternMonthly();
				
				switch( SchedulingMultipleMonthlyDaySetting.valueOf( campaignCfg.schedulingMultipleRecurrencePatternMonthlyRecurDayType() ) ) {
				
					case SimpleDay: {
					
						setCampaignSchedulingMultipleMonthlyRecurrencePatternDayType( SchedulingMultipleMonthlyDaySetting.SimpleDay.ordinal() ).						
						setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverDay( campaignCfg.schedulingMultipleRecurrencePatternMonthlyRecurSimpleDayEveryDay() ).
						setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverMonth( campaignCfg.schedulingMultipleRecurrencePatternMonthlyRecurSimpleDayEveryMonth() );
												
						break;
					}
					case GeneralDay: {
					
						setCampaignSchedulingMultipleMonthlyRecurrencePatternDayType( SchedulingMultipleMonthlyDaySetting.GeneralDay.ordinal() );
						setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek( campaignCfg.schedulingMultipleRecurrencePatternMonthlyRecurGeneralDayEveryOrdinalDay() ).
						setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( campaignCfg.schedulingMultipleRecurrencePatternMonthlyRecurGeneralDayEveryDay() ).
						setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayEverMonth( campaignCfg.schedulingMultipleRecurrencePatternMonthlyRecurGeneralDayEveryMonth() );
						
						break;
					}
				
				}
				
				break;
			}
		
		}
		
		setCampaignSchedulingMultipleStartDate( campaignCfg.schedulingMultipleStartDate() ).
		setCampaignSchedulingMultipleRangeOfRecurrenceType( SchedulingMultipleRangeOfRecurrence.valueOf( campaignCfg.schedulingMultipleRangeOfRecurrenceType() ).value() );
		
		switch( SchedulingMultipleRangeOfRecurrence.valueOf( campaignCfg.schedulingMultipleRangeOfRecurrenceType() ) ) {
		
			case NoEndDate: { 
				
				break; 
			
			}
			case EndAfterNOccurences: {
				
				setCampaignSchedulingMultipleRangeOfRecurrenceValueEndAfterNOccurences( campaignCfg.schedulingMultipleRangeOfRecurrenceValue() );		
								
				break;
			}
			case EndDate: {
				
				setCampaignSchedulingMultipleRangeOfRecurrenceValueEndDate( campaignCfg.schedulingMultipleRangeOfRecurrenceValue() );
								
				break;
			}
			
		}
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRecurrencePattern( String recurrencePattern ) throws FormException {
		
		String campaignSchedulingMultipleRecurrencePatternXPath = "//td[@class='headers' and contains( text(), 'Recurrence pattern' )]/parent::tr//select";
		
		selectByXPathAndVisibleText( campaignSchedulingMultipleRecurrencePatternXPath, SchedulingMultipleRecurrencePattern.valueOf( recurrencePattern ).name() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRecurrencePatternWeekly() throws FormException {
		
		setCampaignSchedulingMultipleRecurrencePattern( SchedulingMultipleRecurrencePattern.Weekly.name() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRecurrencePatternMonthly() throws FormException {
		
		setCampaignSchedulingMultipleRecurrencePattern( SchedulingMultipleRecurrencePattern.Monthly.name() );
				
		return this;
		
	}

	public CampaignsForm setCampaignSchedulingMultipleRecurrencePatternOccurrence( Integer recurrencePatternOccurrence ) throws FormException {
		
		setCampaignSchedulingMultipleRecurrencePatternOccurrence( String.valueOf( recurrencePatternOccurrence ) );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRecurrencePatternOccurrence( String recurrencePatternOccurrence ) throws FormException {
		
		String campaignSchedulingMultipleRecurrencePatternOccurrenceXPath = "//td[@class='headers' and contains( text(), 'Recurrence pattern' )]/parent::tr/parent::tbody//table[@class='recurrenceWidget']//div[text()='Recur every']/parent::td/parent::tr//input";
		
		typeByXPath( campaignSchedulingMultipleRecurrencePatternOccurrenceXPath, recurrencePatternOccurrence );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRecurrencePatternDaysOfWeek( SchedulingMultipleRecurrencePatternWeekly... daysOfWeek ) throws FormException {
		
		cleanAllCampaignSchedulingMultipleRecurrencePatternDayOfWeek();
		
		for( SchedulingMultipleRecurrencePatternWeekly dayOfWeek : daysOfWeek ) {
			
			setCampaignSchedulingMultipleRecurrencePatternDayOfWeek( dayOfWeek.name() );
			
		}
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRecurrencePatternDaysOfWeek( List<String> daysOfWeek ) throws FormException {
		
		cleanAllCampaignSchedulingMultipleRecurrencePatternDayOfWeek();
		
		for( String dayOfWeek : daysOfWeek ) {
			
			setCampaignSchedulingMultipleRecurrencePatternDayOfWeek( dayOfWeek );
			
		}
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRecurrencePatternDayOfWeek( SchedulingMultipleRecurrencePatternWeekly dayOfWeek ) throws FormException {
		
		setCampaignSchedulingMultipleRecurrencePatternDayOfWeek( dayOfWeek.name() );
		
		return this;
		
	}
	
	
	public CampaignsForm setCampaignSchedulingMultipleRecurrencePatternDayOfWeek( String dayOfWeek ) throws FormException {
		
		String campaignSchedulingMultipleRecurrencePatternDaysOfWeekXPath = "//td[@class='headers' and contains( text(), 'Recurrence pattern' )]/parent::tr/parent::tbody//table[@class='recurrenceWidget']//label[starts-with(text(), '" + dayOfWeek + "')]/parent::span/input";
	
		if( !isCheckedByXPath( campaignSchedulingMultipleRecurrencePatternDaysOfWeekXPath ) ) {
			
			clickXPath( campaignSchedulingMultipleRecurrencePatternDaysOfWeekXPath ); 
			
		}
		
		return this;
		
	}
	
	
	
	public CampaignsForm cleanAllCampaignSchedulingMultipleRecurrencePatternDayOfWeek() throws FormException {
		
		for( SchedulingMultipleRecurrencePatternWeekly dayOfWeek : SchedulingMultipleRecurrencePatternWeekly.values() ) {
			
			cleanCampaignSchedulingMultipleRecurrencePatternDayOfWeek( dayOfWeek.name() );
			
		}
				
		return this;
		
	}
	
	public CampaignsForm cleanCampaignSchedulingMultipleRecurrencePatternDayOfWeek( SchedulingMultipleRecurrencePatternWeekly dayOfWeek ) throws FormException {
		
		cleanCampaignSchedulingMultipleRecurrencePatternDayOfWeek( dayOfWeek.name() );
		
		return this;
		
	}
	
	public CampaignsForm cleanCampaignSchedulingMultipleRecurrencePatternDayOfWeek( String dayOfWeek ) throws FormException {
		
		String campaignSchedulingMultipleRecurrencePatternDaysOfWeekXPath = "//td[@class='headers' and contains( text(), 'Recurrence pattern' )]/parent::tr/parent::tbody//table[@class='recurrenceWidget']//label[starts-with(text(), '" + dayOfWeek + "')]/parent::span/input";
	
		if( isCheckedByXPath( campaignSchedulingMultipleRecurrencePatternDaysOfWeekXPath ) ) {
			
			clickXPath( campaignSchedulingMultipleRecurrencePatternDaysOfWeekXPath ); 
			
		}
		
		return this;
		
	}
		
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternDayType( Integer option ) throws FormException {
		
		String campaignSchedulingMultipleMonthlyRecurrencePatternDayName = "myRadioGroupMonth";	
		
		selectRadioGroupByName( campaignSchedulingMultipleMonthlyRecurrencePatternDayName, option );
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDay() throws FormException {
		
		return setCampaignSchedulingMultipleMonthlyRecurrencePatternDayType( SchedulingMultipleMonthlyDaySetting.SimpleDay.ordinal() );
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverDay( Integer value ) throws FormException {
		
		setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverDay( String.valueOf( value ) );
				
		return this;
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverDay( String value ) throws FormException {
		
		String campaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayValueXPath = ".//table[@class='recurrenceWidget']/tbody/tr[1]//tr/td[3]//input";
		
		typeByXPath( campaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayValueXPath, value );
				
		return this;
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverMonth( Integer value ) throws FormException {
		
		setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverMonth( String.valueOf( value ) );
				
		return this;
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverMonth( String value ) throws FormException {
		
		String campaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverMonthXPath = ".//table[@class='recurrenceWidget']/tbody/tr[1]//tr/td[5]//input";
		
		typeByXPath( campaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverMonthXPath, value );
				
		return this;
		
	}	
		
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDay() throws FormException {
		
		return setCampaignSchedulingMultipleMonthlyRecurrencePatternDayType( SchedulingMultipleMonthlyDaySetting.GeneralDay.ordinal() );
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek( SchedulingMultipleMonthlyGeneralDayOrdinalDayOfWeek ordinalDayOfWeek ) throws FormException {
		
		setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek( ordinalDayOfWeek.name() );
				
		return this;
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek( String value ) throws FormException {
		
		String campaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek = ".//table[@class='recurrenceWidget']/tbody/tr[2]//tr/td[3]//select";
		
		selectByXPathAndVisibleText( campaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek, value );
						
		return this;
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( SchedulingMultipleMonthlyGeneralDayOfWeek dayOfWeek ) throws FormException {
		
		setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( dayOfWeek.name() );
				
		return this;
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( String value ) throws FormException {
		
		String campaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek = ".//table[@class='recurrenceWidget']/tbody/tr[2]//tr/td[4]//select";
		
		selectByXPathAndVisibleText( campaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek, value );
						
		return this;
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayEverMonth( Integer value ) throws FormException {
		
		setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayEverMonth( String.valueOf( value ) );
				
		return this;
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayEverMonth( String value ) throws FormException {
		
		String campaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverMonthXPath = ".//table[@class='recurrenceWidget']/tbody/tr[2]//tr/td[6]//input";
		
		typeByXPath( campaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverMonthXPath, value );
				
		return this;
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleProvisioningDuration( Integer provisioningDuration ) throws FormException {
		
		setCampaignSchedulingMultipleProvisioningDuration( String.valueOf( provisioningDuration ) );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleProvisioningDuration( String provisioningDuration ) throws FormException {
		
		String campaignSchedulingMultipleProvisioningDurationXPath = "//td[@class='headers' and contains( text(), 'Provisioning Duration' )]/parent::tr//input";
		
		typeByXPath( campaignSchedulingMultipleProvisioningDurationXPath, provisioningDuration );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleDaysBetweenProvisioningStartDateAndExecutionStartDate( Integer daysBetweenProvisioningStartDateAndExecutionStartDate ) throws FormException {
		
		setCampaignSchedulingMultipleDaysBetweenProvisioningStartDateAndExecutionStartDate( String.valueOf( daysBetweenProvisioningStartDateAndExecutionStartDate ) );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleDaysBetweenProvisioningStartDateAndExecutionStartDate( String daysBetweenProvisioningStartDateAndExecutionStartDate ) throws FormException {
		
		String campaignSchedulingMultipleDaysBetweenProvisioningStartDateAndExecutionStartDateXPath = "//td[@class='headers' and contains( text(), 'Days between provisioning start date and execution start date' )]/parent::tr//input";
		
		typeByXPath( campaignSchedulingMultipleDaysBetweenProvisioningStartDateAndExecutionStartDateXPath, daysBetweenProvisioningStartDateAndExecutionStartDate );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleExecutionPeriod( Integer executionPeriod ) throws FormException {
		
		setCampaignSchedulingMultipleExecutionPeriod( String.valueOf( executionPeriod ) );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleExecutionPeriodType(String value) throws FormException {
		
		String CampaignSchedulingMultipleExecutionPeriodType="//table[@class='tableList Form marginTop20px']/tbody//tr[5]/td[2]/table/tbody/tr/td/select";
		selectByXPathAndVisibleText(CampaignSchedulingMultipleExecutionPeriodType, value);		
	
		return this;
		
	}
	
	public String getsetCampaignSchedulingMultipleExecutionPeriodType() throws FormException
	{
		return super.getValueByXPath("//table[@class='tableList Form marginTop20px']/tbody//tr[5]/td[2]/table/tbody/tr/td/select");
	}

	
	public CampaignsForm setCampaignSchedulingMultipleExecutionPeriod( String executionPeriod ) throws FormException {
		
		String campaignSchedulingMultipleExecutionPeriodXPath = "//td[@class='headers' and contains( text(), 'Execution Period' )]/parent::tr//input";
		
		typeByXPath( campaignSchedulingMultipleExecutionPeriodXPath, executionPeriod );
				
		return this;
		
	}
	
	
	
	public CampaignsForm setCampaignSchedulingMultipleStartDate( Calendar startDate ) throws FormException {
		
		try {
			
			setCampaignSchedulingMultipleStartDate( Format.getMysqlDateTime( startDate ) );
		
		} catch (ParseException e) {
			
			throw new FormException( e.getMessage(), e );
			
		}
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleStartDate( String startDate ) throws FormException {
		
		String campaignSchedulingMultipleStartDateXPath = "//td[@class='headers' and contains( text(), 'Start Date' )]/parent::tr//input";
		
		configureGWTCalendarByXPath( campaignSchedulingMultipleStartDateXPath, resolveDateField( startDate ) );
				
		return this;
		
	}
		
	public CampaignsForm setCampaignSchedulingMultipleRangeOfRecurrenceType( SchedulingMultipleRangeOfRecurrence rangeOfRecurrenceType ) throws FormException {
		
		setCampaignSchedulingMultipleRangeOfRecurrenceType( rangeOfRecurrenceType.value() );		
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRangeOfRecurrenceType( String rangeOfRecurrenceType ) throws FormException {
		
		String campaignSchedulingMultipleRangeOfRecurrenceTypeXPath = "//td[@class='headers' and contains( text(), 'Range of recurrence' )]/parent::tr//select";
		
		selectByXPathAndVisibleText( campaignSchedulingMultipleRangeOfRecurrenceTypeXPath, rangeOfRecurrenceType );		
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRangeOfRecurrenceNoEndDate() throws FormException {
		
		setCampaignSchedulingMultipleRangeOfRecurrenceType( SchedulingMultipleRangeOfRecurrence.NoEndDate.value() );
		
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRangeOfRecurrenceValueEndAfterNOccurences( Integer rangeOfRecurrenceValue ) throws FormException {
		
		setCampaignSchedulingMultipleRangeOfRecurrenceValueEndAfterNOccurences( String.valueOf( rangeOfRecurrenceValue ) );		
				
		return this;
		
	}	
	
	public CampaignsForm setCampaignSchedulingMultipleRangeOfRecurrenceValueEndAfterNOccurences( String rangeOfRecurrenceValue ) throws FormException {
				
		String campaignSchedulingMultipleRangeOfRecurrenceValueXPath = "//td[@class='headers' and contains( text(), 'Range of recurrence' )]/parent::tr//parent::tbody/tr[3]//input";
		
		setCampaignSchedulingMultipleRangeOfRecurrenceType( SchedulingMultipleRangeOfRecurrence.EndAfterNOccurences.value() );
		
		typeByXPath( campaignSchedulingMultipleRangeOfRecurrenceValueXPath, rangeOfRecurrenceValue );		
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRangeOfRecurrenceValueEndDate( Calendar rangeOfRecurrenceValue ) throws FormException {
		
		try {
			
			setCampaignSchedulingMultipleRangeOfRecurrenceValueEndDate( Format.getMysqlDateTime( rangeOfRecurrenceValue ) );
		
		} catch (ParseException e) {
			
			throw new FormException( e.getMessage(), e );
			
		}
				
		return this;
		
	}
	
	public CampaignsForm setCampaignSchedulingMultipleRangeOfRecurrenceValueEndDate( String rangeOfRecurrenceValue ) throws FormException {
		
		String campaignSchedulingMultipleStartDateXPath = "//td[@class='headers' and contains( text(), 'Start Date' )]/parent::tr/parent::tbody/tr[3]//input";
		
		setCampaignSchedulingMultipleRangeOfRecurrenceType( SchedulingMultipleRangeOfRecurrence.EndDate.value() );
		
		configureGWTCalendarByXPath( campaignSchedulingMultipleStartDateXPath, resolveDateField( rangeOfRecurrenceValue ) );
				
		return this;
		
	}
	
	/**
	 * 
	 * DIALOGUE
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONException
	 */
	public CampaignsForm configureCampaignDialogue() throws FormException, JSONException {
				
		openDialogTab();
		
		if( null != campaignCfg.dialogueChannelShortCode() ) { setCampaignDialogueShortCode( campaignCfg.dialogueChannelShortCode() ); }
		if( null != campaignCfg.dialogueEmailAddress() ) { setCampaignDialogueEmailAddress( campaignCfg.dialogueEmailAddress() ); }
		if( null != campaignCfg.dialogueNotificationDaysOfNotificationBeforeExecution() ) { setDialogueNotificationDaysOfNotificationBeforeExecution( campaignCfg.dialogueNotificationDaysOfNotificationBeforeExecution() ); }
		if( null != campaignCfg.dialogueNotificationMessages() ) {}
		if( campaignCfg.dialogueNotificationApplyCampaignToNotifiedOnly() ) { setCampaignDialogueApplyCampaignToNotifiedOnly(); }
		if( null != campaignCfg.dialogueNotificationTime() ) { setCampaignDialogueNotificationTime( campaignCfg.dialogueNotificationTime() ); }
		
		
		return this;
		
	}
	
	public CampaignsForm openDialogTab() throws FormException {
		
		clickXPath( getWizardTabXPath( WizardTab.Dialogue ) );
		
		return this;
		
	}
	
	public CampaignsForm setCampaignDialogueShortCode( String shortCode ) throws FormException {
		
		String campaignDialogueShortCodeXPath = "//select[@id='gwt-debug-Campaign Dialogue Shortcode']";
		
		selectByXPathAndVisibleText( campaignDialogueShortCodeXPath, shortCode );
		
		return this;
		
	}
	
	public CampaignsForm setCampaignDialogueEmailAddress( String emailAddress ) throws FormException {
		
		String campaignDialogueEmailAddressXPath = "//select[@id='gwt-debug-Campaign Dialogue Email Address']";
		
		selectByXPathAndVisibleText( campaignDialogueEmailAddressXPath, emailAddress );
		
		return this;
		
	}
	
	public CampaignsForm setDialogueNotificationDaysOfNotificationBeforeExecution( Integer daysOfNotificationBeforeExecution ) throws FormException {
				
		return setDialogueNotificationDaysOfNotificationBeforeExecution( String.valueOf( daysOfNotificationBeforeExecution ) );
		
	}
	
	public CampaignsForm setDialogueNotificationDaysOfNotificationBeforeExecution( String daysOfNotificationBeforeExecution ) throws FormException {
		
		String campaignDialogueDayOfNotificationXPath = "//input[@id='gwt-debug-Campaign Dialogue Days of Notification']";
		
		typeByXPath( campaignDialogueDayOfNotificationXPath, daysOfNotificationBeforeExecution );
		
		return this;
		
	}
	
	/**
	 *  
	 * NOTIFICATION DIALOG
	 * 
	 **/
	public CampaignsForm openDialogueNotification() throws FormException {
		
		this.notificationForm = new NotificationForm( this.selenium, this.timeout, this.interval ); 
		
		String campaignDialogueNotificationXPath = "//button[@id='gwt-debug-Campaign Dialogue Notification pop up']";
		
		clickXPath( campaignDialogueNotificationXPath );
		
		return this;
		
	}
	
	public CampaignsForm saveDialogueNotification() throws FormException {
		
		this.notificationForm.saveBtn();
	
		return this;
		
	}
	
	public CampaignsForm cancelDialogueNotification() throws FormException {
		
		this.notificationForm.cancelBtn();
	
		return this;
		
	}
	
	public CampaignsForm editDialogueNotification( NotificationTongue tongue, NotificationChannel channel ) throws FormException {
		
		this.notificationForm.editBtn( tongue.value(), channel.name() );
		
		return this;
		
	}
	
	public CampaignsForm editDialogueNotification( String tongue, String channel ) throws FormException {
		
		this.notificationForm.editBtn( tongue, channel );	
		
		return this;
		
	}
	
	public CampaignsForm deleteDialogueNotification( NotificationTongue tongue, NotificationChannel channel ) throws FormException {
		
		this.notificationForm.deleteBtn( tongue.value(), channel.name() );
		
		return this;
		
	}
	
	public CampaignsForm deleteDialogueNotification( String tongue, String channel ) throws FormException {
		
		this.notificationForm.deleteBtn( tongue, channel );	
		
		return this;
		
	}
	
	public CampaignsForm importDialogueNotification( NotificationTongue tongue, NotificationChannel channel ) throws FormException {
		
		this.notificationForm.importBtn( tongue.value(), channel.name() );
		
		return this;
		
	}
	
	public CampaignsForm importDialogueNotification( String tongue, String channel ) throws FormException {
		
		this.notificationForm.importBtn( tongue, channel );	
		
		return this;
		
	}
	
	public CampaignsForm saveDialogueNotificationEditing() throws FormException {
		
		this.notificationForm.saveEdititingBtn();
	
		return this;
		
	}

	public CampaignsForm saveTemplateDialogueNotificationEditing() throws FormException {
		
		this.notificationForm.saveTemplateEditingBtn();
	
		return this;
		
	}

	public CampaignsForm cancelDialogueNotificationEditing() throws FormException {
		
		this.notificationForm.cancelEditingBtn();
	
		return this;
		
	}
	
	public CampaignsForm setDialogueNotificationMessage( String message ) throws FormException {
		
		this.notificationForm.setMessage( message );
		
		return this;
		
	}
		
	public CampaignsForm setCampaignDialogueApplyCampaignToNotifiedOnly() throws FormException {
		
		String campaignDialogueApplyCampaignToNotifiedOnlyXPath = "//input[@id='gwt-debug-Campaign Dialogue Notified Only-input']";
		
		if( !isCheckedByXPath( campaignDialogueApplyCampaignToNotifiedOnlyXPath ) ) {
			
			clickXPath( campaignDialogueApplyCampaignToNotifiedOnlyXPath ); 
			
		}
		
		return this;	
		
	}
	
	public CampaignsForm cleanCampaignDialogueApplyCampaignToNotifiedOnly() throws FormException {
		
		String campaignDialogueApplyCampaignToNotifiedOnlyXPath = "//input[@id='gwt-debug-Campaign Dialogue Notified Only-input']";
		
		if( isCheckedByXPath( campaignDialogueApplyCampaignToNotifiedOnlyXPath ) ) {
			
			clickXPath( campaignDialogueApplyCampaignToNotifiedOnlyXPath ); 
			
		}
		
		return this;	
		
	}
		
	public CampaignsForm setCampaignDialogueNotificationTime( String time ) throws FormException {
		
		String campaignDialogueNotificationTimeXPath = "//select[@id='gwt-debug-Campaign Dialogue Notif Time']";
		
		selectByXPathAndVisibleText( campaignDialogueNotificationTimeXPath, time );
		
		return this;
		
	}
		
	/**
	 * 
	 * TARGET
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONException
	 * 
	 * TODO
	 */	
	public CampaignsForm configureCampaignTarget() throws FormException, JSONException {
				
		String campaignTargetOpenedModeRuleTableXPath = "//table[contains(@class,'tableList rulesTable')]";
		String campaignTargetOpenedModeRuleTableEventTypeXPath = campaignTargetOpenedModeRuleTableXPath + "//td[@class='column_eventType']";
		String campaignTargetOpenedModeRuleTableEventTypeAddXPath = campaignTargetOpenedModeRuleTableEventTypeXPath + "//button[@title='Add']";
					
		openTargetTab();
		
		setCampaignTargetTargetingMode( TargetingMode.valueOf( campaignCfg.campaignTargetTargetingMode() ).name() );
			
		switch( TargetingMode.valueOf( campaignCfg.campaignTargetTargetingMode() ) ) {
		
			case Restricted: {
				
				setCampaignTargetTargetingRestrictedMode( TargetingRestrictedMode.valueOf( campaignCfg.campaignTargetRestrictedMode() ).value() );
						
				setCampaignTargetTargetingRestrictedConfigureASample( TargetingSampleType.valueOf( campaignCfg.campaignTargetConfigureASimple() ).value() );
				
				switch( TargetingSampleType.valueOf( campaignCfg.campaignTargetConfigureASimple() ) ) {
				
					case NoSample: {												
						break;
					}
					case ControlSample: {						
						break;
					} 
					case TestSample: {						
						break;
					}
				}
								
				break;
			}
			case Opened: {
				
				System.out.println( "opened" );
				
				//selectByXPathAndVisibleText( campaignTargetRestrictedModeXPath, TargetingRestrictedMode.valueOf( campaignCfg.campaignTargetRestrictedMode() ).value() );
				
				//table[contains(@class,'tableList rulesTable')]
				
				//table[contains(@class,'tableList rulesTable')]//td[@class='column_eventType']
				
				//table[contains(@class,'tableList rulesTable')]//td[@class='column_eventType']//button[@title='Add']
								
				//-----
				//table[contains(@class,'tableList rulesTable')]//tr[contains(@class,'contentRow')][1]//td[@class='column_eventType']
				
				//table[contains(@class,'tableList rulesTable')]//tr[contains(@class,'contentRow')][1]//td[@class='column_criteria']
				
				//table[contains(@class,'tableList rulesTable')]//tr[contains(@class,'contentRow')][1]//td[@class='column_criteria']//div[@class='criterionContainer']
				
				//table[contains(@class,'tableList rulesTable')]//tr[contains(@class,'contentRow')][1]//td[@class='column_criteria']//div[@class='criterionContainer']//button[@id='gwt-debug-BtnCampaignModelCreationECAdd']
							
				//-----
							
				// //td[@class='column_eventType']//input
				//td[@class='column_criteria']//button[@id='gwt-debug-BtnCampaignModelCreationECAdd']
								
				//*[@id='gwt-debug-ListCampaignModelCreationECType']
				//gwt-debug-ListCampaignModelCreationECOperator
				
				// gwt-debug-TextCampaignModelCreationECValue
				
				// gwt-debug-BtnCampaignModelCreationECParenthesis
				
				//*[@id='gwt-debug-BtnCampaignModelCreationECDelete']
				
				// //*[@id='isc_2L']
				
				// /*[@id='gwt-debug-ListCampaignModelCreationECUnit']
								
				break;
			}

		}
 				
		return this;
		
	}
	
	public Boolean confirmCampaignAlert(Boolean accept) throws FormException {
		
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
	
	
	public CampaignsForm openTargetTab() throws FormException {
		
		clickXPath( getWizardTabXPath( WizardTab.Target ) );
		
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingMode( TargetingMode targetingMode ) throws FormException {
		
		setCampaignTargetTargetingMode( targetingMode.name() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingMode( String targetingMode ) throws FormException {
		
		String campaignTargetTargetingModeXPath = "//td[@class='headers' and contains(text(), 'Targeting Mode' )]/parent::tr//select";
				
		selectByXPathAndVisibleText( campaignTargetTargetingModeXPath, targetingMode );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedMode( TargetingRestrictedMode targetingRestrictedMode ) throws FormException {
		
		setCampaignTargetTargetingRestrictedMode( targetingRestrictedMode.name() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedMode( String targetingRestrictedMode ) throws FormException {
		
		String campaignTargetRestrictedModeXPath = "//select[@id='gwt-debug-Campaign Eligibility Select Mode']";
				
		selectByXPathAndVisibleText( campaignTargetRestrictedModeXPath, targetingRestrictedMode );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedModeCriteria() throws FormException {
		
		setCampaignTargetTargetingRestrictedMode( TargetingRestrictedMode.Criteria.value() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedModeImportSubscribers() throws FormException {
		
		setCampaignTargetTargetingRestrictedMode( TargetingRestrictedMode.ImportSubscribers.value() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedModeCriteriaIntersectionImport() throws FormException {
		
		setCampaignTargetTargetingRestrictedMode( TargetingRestrictedMode.CriteriaIntersectionImport.value() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedModeCriteriaUnionImport() throws FormException {
		
		setCampaignTargetTargetingRestrictedMode( TargetingRestrictedMode.CriteriaUnionImport.value() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedModeManualTargeting() throws FormException {
		
		setCampaignTargetTargetingRestrictedMode( TargetingRestrictedMode.ManualTargeting.value() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedConfigureASample( TargetingSampleType targetingSampleType ) throws FormException {
		
		setCampaignTargetTargetingRestrictedConfigureASample( targetingSampleType.name() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedConfigureASample( String targetingSampleType ) throws FormException {
		
		String campaignTargetConfigureASimpleXPath = "//select[@id='gwt-debug-Campaign Configure Sample']";		
				
		selectByXPathAndVisibleText( campaignTargetConfigureASimpleXPath, targetingSampleType );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedConfigureASampleNoSample() throws FormException {
		
		setCampaignTargetTargetingRestrictedConfigureASample( TargetingSampleType.NoSample.value() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedConfigureASampleControlSample() throws FormException {
		
		setCampaignTargetTargetingRestrictedConfigureASample( TargetingSampleType.ControlSample.value() );
				
		return this;
		
	}
	
	public CampaignsForm setCampaignTargetTargetingRestrictedConfigureASampleTestSample() throws FormException {
		
		setCampaignTargetTargetingRestrictedConfigureASample( TargetingSampleType.TestSample.value() );
				
		return this;
		
	}
		
	/**
	 * 
	 * LIMIT
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONException
	 * 
	 * TODO
	 * 
	 */
	public CampaignsForm configureCampaignLimits() throws FormException, JSONException {
		
		openLimitsTab();
		
		return this;
		
	}

	public CampaignsForm openLimitsTab() throws FormException {
		
		clickXPath( getWizardTabXPath( WizardTab.Limits ) );
		
		return this;
		
	}
	
	/**
	 * 
	 * ACTIVATION
	 * 	
	 * @return
	 * @throws FormException
	 * @throws JSONException
	 * 
	 * TODO
	 * 
	 */
	public CampaignsForm configureCampaignActivation() throws FormException, JSONException {
			
		openActivationTab();
		
		actionBtn( ActivationAction.valueOf( campaignCfg.campaignActivationAction() ) );
					
		confirmCampaignActivation();
				
		return this;
		
	}
	
	public CampaignsForm openActivationTab() throws FormException {
		
		clickXPath( getWizardTabXPath( WizardTab.Activation ) );
		
		return this;
		
	}
	
	public CampaignsForm actionBtn( ActivationAction action ) throws FormException {
		
		String campaignActivationBtnXPath = "//button[@id='gwt-debug-Campaign Edition {action}']";
		
		clickXPath( campaignActivationBtnXPath.replace( "{action}" , action.name() ) );
				
		return this;
		
	}
	
	public CampaignsForm confirmCampaignActivation() {
		
		try {

			Alert confirmLogout = selenium.selectAlert();
			
			while( null != confirmLogout ) {
			
				confirmLogout.accept(); 
				confirmLogout = selenium.selectAlert();
				
			}
		
		} catch (NoAlertPresentException e) {}
		
		return this;
		
	}
	
	
	public Boolean isCampaignInActivatedList( String campaignName ) throws FormException {
		
		List<WebElement> campaignList = getCampaignActivationList(campaignName);

		for( WebElement campaignListE1 : campaignList ) {

			if( campaignListE1.getText().trim().equals( campaignName ) ) {
		
				return true;

			}	
		}

		return false;	
		}

	
	public Boolean isCampaignNameInList(String strCampaignName)
			throws FormException {

		List<WebElement> campaignList = getCampaignList();

		for (WebElement campaignEl : campaignList) {
			/*fix done on 15th Dec 2015 to make the element visible*/
			super.scrollTo(campaignEl);
			if (campaignEl.getText().trim().equals(strCampaignName)) {

				return true;

			}

		}

		return false;

	}
	
	public List<WebElement> getCampaignActivationList(String CAMPAIGN_NAME)  throws FormException {
		
		String rootPath = "//div[text()='"+CAMPAIGN_NAME+"']//ancestor::tr[1]";
		String subPath = "/td[3]//div[@class='gwt-Label showPopupLink']";
		List<WebElement> campaignList = getListByXPath(rootPath, rootPath + subPath);
		System.out.println(campaignList);
		return campaignList;
	}

	public List<WebElement> getCampaignList()  throws FormException {
		
		List<WebElement> campaignList = super.getListByXPath(
				"//table[contains(@class,'CampaignActivationTab')]//table[@class='tableList']",
				"//tr[contains(@class,'contentRow cycle')]//td[@class='column_campaignName']/div");

		return campaignList;
	}

	public CampaignsForm configureGWTCalendarByXPath( String xpath, Calendar date ) throws FormException, JSONException {
		
		GWTCalendarForm.
			create( selenium, timeout, interval ).
			openByXPath( xpath ).
			setDate( date );
		
		return this;
		
	}
	
	public CampaignsForm setCriteriaCampaign() throws FormException {
		
	super.clickXPath("//table[@class='tableList rulesTable marginTop10px']/tbody/tr[@class='contentRow cycle1 ruleNotApplied']//div[@class='criterionContainer']/table/tbody/tr/td[3]//button[@id='gwt-debug-BtnCampaignModelCreationECAdd']");
		
	return this;
		
	}
	
	public String getCriteriaCampaign() throws FormException {
		
		String addcriteria = "//table[@class='tableList rulesTable marginTop10px']/tbody/tr[@class='contentRow cycle1 ruleNotApplied']//div[@class='criterionContainer']/table/tbody/tr/td[3]//button[@id='gwt-debug-BtnCampaignModelCreationECAdd']";
		
		return getValueByXPath( addcriteria );
		
	}
	
	public CampaignsForm campaignEditButton(String campName) throws FormException{
		
		String xpathEditButton="//td[@class='column_campaignName']//div[text()='"+campName+"']//ancestor::tr[1]//button[@name='btn-edit']";
		WebElement el=selenium.getWrappedDriver().findElement(By.xpath(xpathEditButton));
		super.scrollTo(el);
		super.clickXPath(xpathEditButton);
		
		return this;
	}	

	
	public CampaignsForm campaignCopyButton(String campName) throws FormException{
		
		String xpathCopyButton="//td[@class='column_campaignName']//div[text()='"+campName+"']//ancestor::tr[1]//button[@name='btn-copy']";
		WebElement el=selenium.getWrappedDriver().findElement(By.xpath(xpathCopyButton));
		super.scrollTo(el);
		super.clickXPath(xpathCopyButton);
		return this;
	}
	
	
	public CampaignsForm campaignStopButton(String campName) throws FormException{
		
		String xpathStopButton="//td[@class='column_campaignName']//div[text()='"+campName+"']//ancestor::tr[1]//button[@name='btn-stop']";
		WebElement el=selenium.getWrappedDriver().findElement(By.xpath(xpathStopButton));
		super.scrollTo(el);
		super.clickXPath(xpathStopButton);
		
		return this;
	}	

	public CampaignsForm campaignDeleteButton(String campName) throws FormException{
		String xpathDelButton="//td[@class='column_campaignName']//div[text()='"+campName+"']//ancestor::tr[1]//button[@name='btn-delete']";
		WebElement el=selenium.getWrappedDriver().findElement(By.xpath(xpathDelButton));
		super.scrollTo(el);
		super.clickXPath(xpathDelButton);
		
		return this;
	}	

	public CampaignsForm configureCriteria(String subscriber, String SubNO) throws JSONException, FormException {
		
		String criteriaXPathRow = "//table[@class='tableList rulesTable marginTop10px']//div[@class='criterionContainer']"; 
		String criteriaXPathRow1 = criteriaXPathRow + "//table/tbody/tr[1]";
		String criteriaXPathRowAValue = criteriaXPathRow1 + "//*[@id='gwt-debug-TextCampaignModelCreationECValue']";
		String criteriaXPathRowAType = criteriaXPathRow1 + "//*[@id='gwt-debug-ListCampaignModelCreationECType']";
		
		/** configure criteria */
		
		clickXPath( criteriaXPathRowAType );
		selectDropDownListItem(subscriber);
				
		typeByXPath( criteriaXPathRowAValue, SubNO); 
				
		return this;
		
	}
	
	public String getCampaignActivationError() throws FormException {
		
		String activationErrorXPath = "//table[@class='serverResponseLabelError']//div";
		
		return getTextByXPath( activationErrorXPath );
		
	}
	
	private String getWizardTabXPath( WizardTab wizardTab ) {
		
		String wizardDefinitionTabXPath = "//div[contains(@class, 'gwt-Hyperlink') and contains(@class, 'selectableSC')]/a[text()='" + wizardTab.name() + "']";
		
		return wizardDefinitionTabXPath;
		
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
	
}
