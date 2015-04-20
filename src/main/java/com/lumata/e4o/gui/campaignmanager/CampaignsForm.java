package com.lumata.e4o.gui.campaignmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.common.PlaceHolderDate;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.GWTCalendarForm;
import com.lumata.e4o.gui.common.IForm;
import com.lumata.e4o.gui.common.IFormCreation;
import com.lumata.e4o.gui.common.INotificationForm;
import com.lumata.e4o.gui.common.NotificationForm;
import com.lumata.e4o.gui.common.NotificationForm.NotificationChannel;
import com.lumata.e4o.gui.common.NotificationForm.NotificationTongue;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaigns;

public class CampaignsForm extends CampaignManagerForm implements IForm, IFormCreation, INotificationForm {

	private JSONCampaigns campaignCfg;
	private NotificationForm notificationForm;
	
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
	
	private enum TargetingMode {
		Restricted, Opened
	}
		
	private enum TargetingRestrictedMode {
		
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
	
	private enum TargetingSampleType {
		
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

		Previous, Cancel, Save, Activate;

	};
	
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
		
		super.open().clickId( "gwt-debug-InputCMCampaignCreation" );
		
		return this;
		
	}
	
	public CampaignsForm closeForm() throws FormException {
		
		return this;
		
	}
	
	public CampaignsForm addBtn() throws FormException {
		
		clickId( "gwt-debug-Add Campaign" );
		
		return this;
		
	}

	public CampaignsForm saveBtn() throws FormException {
		
		return this;
		
	}

	public CampaignsForm cancelBtn() throws FormException {
		
		return this;
		
	}

	public CampaignsForm refreshBtn() throws FormException {
		
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
/*	
	public CampaignForm saveCampaignModel() throws FormException {
		
		final String saveCampaignModelButtonXPath = campaignModelFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationSave']";
		
		clickXPath( saveCampaignModelButtonXPath );
		
		return this;
		
	}
	
	public CampaignForm cancelCampaignModel() throws FormException {
		
		final String cancelCampaignModelButtonXPath = campaignModelFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationCancel']";
		
		clickXPath( cancelCampaignModelButtonXPath );
			
		return this;
		
	}
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

	public CampaignsForm setCampaignModel( String campaignModel ) throws FormException {
	
		String campaignModeValueXPath = "//select[@id='gwt-debug-Campaign Model Select in Campaign']";
		
		switch( ExecutionMode.valueOf( campaignCfg.executionMode() ) ) {
		
			case Model : {
				selectByXPathAndVisibleText( campaignModeValueXPath, campaignModel );
				break;
			}
			case Notification : {
				break;
			}
			case Rule : {
				break;
			}
			default: break;
			
		}
		
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
	
	/** Notification Dialog **/
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
	
	
	// ---------------------------------------------------------------
	
	
	
	public CampaignsForm configureCampaignTarget() throws FormException, JSONException {
		
		String campaignTargetTargetingModeXPath = "//td[@class='headers' and contains(text(), 'Targeting Mode' )]/parent::tr//select";
		
		String campaignTargetRestrictedModeXPath = "//select[@id='gwt-debug-Campaign Eligibility Select Mode']";
		String campaignTargetConfigureASimpleXPath = "//select[@id='gwt-debug-Campaign Configure Sample']";		
		
		String campaignTargetOpenedModeRuleTableXPath = "//table[contains(@class,'tableList rulesTable')]";
		String campaignTargetOpenedModeRuleTableEventTypeXPath = campaignTargetOpenedModeRuleTableXPath + "//td[@class='column_eventType']";
		String campaignTargetOpenedModeRuleTableEventTypeAddXPath = campaignTargetOpenedModeRuleTableEventTypeXPath + "//button[@title='Add']";
					
		clickXPath( getWizardTabXPath( WizardTab.Target ) );
		
		selectByXPathAndVisibleText( campaignTargetTargetingModeXPath, TargetingMode.valueOf( campaignCfg.campaignTargetTargetingMode() ).name() );
		
		switch( TargetingMode.valueOf( campaignCfg.campaignTargetTargetingMode() ) ) {
		
			case Restricted: {
				
				selectByXPathAndVisibleText( campaignTargetRestrictedModeXPath, TargetingRestrictedMode.valueOf( campaignCfg.campaignTargetRestrictedMode() ).value() );
				
				selectByXPathAndVisibleText( campaignTargetConfigureASimpleXPath, TargetingSampleType.valueOf( campaignCfg.campaignTargetConfigureASimple() ).value() );
				
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
				
				System.out.println(  "opened" );
				
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

	public CampaignsForm configureCampaignLimits() throws FormException, JSONException {
		
		clickXPath( getWizardTabXPath( WizardTab.Limits ) );
		
		return this;
		
	}

	public CampaignsForm configureCampaignActivation() throws FormException, JSONException {
		
		String campaignActivationBtnXPath = "//button[@id='gwt-debug-Campaign Edition {action}']";

		
		clickXPath( getWizardTabXPath( WizardTab.Activation ) );
		
		clickXPath( campaignActivationBtnXPath.replace( "{action}" , ActivationAction.valueOf( campaignCfg.campaignActivationAction() ).name()) );
			
		try {
			
			Alert confirmLogout = selenium.selectAlert();
			
			while( null != confirmLogout ) {
			
				confirmLogout.accept(); 
				
				confirmLogout = selenium.selectAlert();
				
			}
		
		} catch (NoAlertPresentException e) {}
		
				
		return this;
		
	}
	
	public CampaignsForm configureGWTCalendarByXPath( String xpath, Calendar date ) throws FormException, JSONException {
		
		GWTCalendarForm.
			create( selenium, timeout, interval ).
			openByXPath( xpath ).
			setDate( date );
		
		return this;
		
	}
	
	private String getWizardTabXPath( WizardTab wizardTab ) {
		
		String wizardDefinitionTabXPath = "//div[contains(@class, 'gwt-Hyperlink') and contains(@class, 'selectableSC')]/a[text()='" + wizardTab.name() + "']";
		
		return wizardDefinitionTabXPath;
		
	}
	
	
	
	/*
	public CampaignForm addEvents() throws JSONException, FormException {
		
		Map<String, JSONEvent> events = campaignModelCfg.getEvents();
		
		int eventRow = 1;
		
		for( String eventName : events.keySet() ) {
			
			eventRow++;
			
			addEvent().
			configureEvent( events.get( eventName ), eventRow );
						
		}
		
		return this;
		
	}
	
	public CampaignForm addEvent() throws JSONException, FormException {
		
		clickId( "gwt-debug-BtnCampaignModelCreationEventAdd" );
			
		return this;
		
	}
	
	public CampaignForm configureEvent( JSONEvent event, Integer eventRow ) throws JSONException, FormException {
		
		String eventXPath ="//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[" + eventRow + "]//*[@id='gwt-debug-ListCampaignModelCreationETType']";
		
		clickXPath( eventXPath ).
		selectDropDownListItem( event.getEventType() ).
		addActions( event, eventRow ).
		selectBeneficiary( event.getBeneficiary(), eventRow ).
		addNotifications( event, eventRow );
		
		return this;
		
	}
	
	public CampaignForm configureCriteria( JSONEvent event ) throws JSONException, FormException {

		event.getCriteria();
		
		return this;
		
	}
	
	public CampaignForm addActions( JSONEvent event, Integer eventRow ) throws JSONException, FormException {

		Map<String, JSONAction> actions = event.getActions();
		
		int actionRow = 0;
		
		for( String actionName : actions.keySet() ) {
					
			addAction( eventRow );
			
			actionRow++;
						
			configureAction( actions.get( actionName ), eventRow, actionRow );
												
		}
		
		return this;
		
	}
	
	public CampaignForm addAction( Integer eventRow ) throws FormException {
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']";
		
		clickXPath( actionXPathRowAAdd );
		
		return this;
		
	}
	
	public CampaignForm deleteAction( Integer eventRow ) throws FormException {
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRowADelete = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationEADelete']";
		
		clickXPath( actionXPathRowADelete );
		
		return this;
		
	}

	public CampaignForm configureAction( JSONAction action, Integer eventRow, Integer actionRow ) throws JSONException, FormException {
				
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRow = eventXPathRow + "//table[@class='commodityContainer']/tbody/tr[" + actionRow + "]";
		
		String actionXPathRowAValue = actionXPathRow + "//*[@id='gwt-debug-TextCampaignModelCreationEAValue']";			
		String actionXPathRowAType = actionXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationEAType']";
		String actionXPathRowAUnit = actionXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationEAUnit']";			
				
		/** configure action time */
/*		if( action.hasActionTime() ) {
			
			addActionTime( eventRow, actionRow ).
			configureActionTime( action.getActionTime() ).
			saveActionTime();				
							
		}
		
		/** configure action */
/*		clickXPath( actionXPathRowAType ).
		selectDropDownListItem( action.getName() ).
		typeByXPath( actionXPathRowAValue, action.getValue() ).
		selectByXPathAndVisibleText( actionXPathRowAUnit, action.getOption() );
		
		return this;
		
	}
	
	public CampaignForm addActionTime( Integer eventRow, Integer actionRow ) throws FormException {
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRow = eventXPathRow + "//table[@class='commodityContainer']/tbody/tr[" + actionRow + "]";		
		String actionTimeXPathRowAAdd = actionXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationEATime']";
		
		clickXPath( actionTimeXPathRowAAdd );		
		
		return this;
		
	}

	public CampaignForm saveActionTime() throws FormException {
		
		String actionTimeXPathRowSave = "//div[@class='gwt-DialogBox']//div[@class='Caption' and text()='Time Configuration']/ancestor::tbody//button[@title='OK']";
		
		clickXPath( actionTimeXPathRowSave );
		
		return this;
		
	}
	
	public CampaignForm abortActionTime() throws FormException {
		
		String actionTimeXPathRowCancel = "//div[@class='gwt-DialogBox']//div[@class='Caption' and text()='Time Configuration']/ancestor::tbody//button[@title='Cancel']";
		
		clickXPath( actionTimeXPathRowCancel );
		
		return this;
		
	}

	public CampaignForm configureActionTime( JSONActionTime actionTime ) throws FormException {
		
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
	
	public CampaignForm enableBeneficiary( Integer eventRow ) throws FormException {
		
		Boolean isBeneficiarySelected = isBeneficiarySelected( eventRow );
		
		if( !isBeneficiarySelected ) { this.lastWebElement.click(); }
		
		return this;
		
	}

	public CampaignForm disableBeneficiary( Integer eventRow ) throws FormException {
		
		Boolean isBeneficiarySelected = isBeneficiarySelected( eventRow );
		
		if( isBeneficiarySelected ) { this.lastWebElement.click(); }
				
		return this;
		
	}
		
	public CampaignForm selectBeneficiary( Boolean selectBeneficiary, Integer eventRow ) throws FormException {
				
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
	
	public CampaignForm addNotifications( JSONEvent event, Integer eventRow ) throws FormException, JSONException {
				
		if( event.hasNotification() ) {
		
			String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]"; 
			String notificationXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationENAdd']";
			
			clickXPath( notificationXPathRowAAdd ).
			configureNotifications( event.getNotifications() );
			
		}
		
		return this;
		
	}
	
	public CampaignForm configureNotifications( Map<String, JSONNotification> notifications ) throws FormException {
		
		NotificationForm notificationDialog = new NotificationForm( selenium, notifications, timeout, interval );
		
		notificationDialog.configureNotifications();

		return this;
		
	}
*/	
	


	
	
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
