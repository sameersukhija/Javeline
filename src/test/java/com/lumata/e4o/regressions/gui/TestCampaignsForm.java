package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.json.JSONException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.campaignmanager.CampaignsForm;

import static com.lumata.e4o.gui.common.NotificationForm.NotificationChannel.*;
import static com.lumata.e4o.gui.common.NotificationForm.NotificationTongue.*;
import static com.lumata.e4o.gui.campaignmanager.CampaignsForm.SchedulingMultipleMonthlyGeneralDayOfWeek.*;
import static com.lumata.e4o.gui.campaignmanager.CampaignsForm.SchedulingMultipleMonthlyGeneralDayOrdinalDayOfWeek.*;
import static com.lumata.e4o.gui.campaignmanager.CampaignsForm.SchedulingMultipleRecurrencePatternWeekly.*;

import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(
	@TCOwner( name="Arcangelo Di Pasquale", email="arcangelo.dipasquale@lumatagroup.com" )
)
@TCSeleniumWebDriver
public class TestCampaignsForm extends ParentTestCase {

	private CampaignsForm campaignsForm;
	
	@BeforeMethod
	public void initCampaignsForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		/** Token Type Form **/
		campaignsForm = new CampaignsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		seleniumWebDriver.setTestName( method.getName() );
		
	}
			
	@Test( enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT, priority = 1 )
	public void checkMandatoryFields1() throws FormException, JSONException, JSONSException {
		
		Calendar startDate = Calendar.getInstance();
		startDate.add( Calendar.DATE, 10 );
		
		Calendar endDate = Calendar.getInstance();
		endDate.add( Calendar.DATE, 20 );
		
		Calendar provEndDate = (Calendar)endDate.clone();
		provEndDate.add( Calendar.DATE, -3 );
				
		Integer daysDiff = Days.daysBetween( new DateTime( startDate.getTime() ), new DateTime( endDate.getTime() ) ).getDays(); 
				
		final String CAMPAIGN_NAME = Format.addTimestamp( "Campaign_" );
				
		campaignsForm.
			openForm().
			addBtn().		
			/** configure definition tab **/
			openDefinitionTab().
			setCampaignExecutionMode( CampaignsForm.ExecutionMode.Notification ).
			setCampaignName( CAMPAIGN_NAME ).
			setCampaignDescription( CAMPAIGN_NAME + " description" ).
			//setCampaignModel( campaignCfg.campaignModel() ).	
			//setCampaignType( campaignCfg.campaignType() ).		
			setByPassMediaType( false ).
			/** configure single scheduling tab **/
			openSchedulingTab().
			setCampaignSingleSchedulingType().			
			setCampaignSingleSchedulingExecutionStart( startDate ).
			setCampaignSingleSchedulingExecutionEndRelative( daysDiff ).
			setCampaignSingleSchedulingExecutionEndAbsolute( endDate ).
			setCampaignSingleSchedulingProvisioningEndDate( provEndDate ).
			setCampaignSingleSchedulingProvisioningStartDate( startDate ).
			setCampaignSchedulingSingleDaysBetweenProvisioningAndExecutionStartDates( 3 ).
			/** configure multiple scheduling tab **/
			openSchedulingTab().
			setCampaignMultipleSchedulingType().
			/** configure multiple scheduling tab - weekly **/
			setCampaignSchedulingMultipleRecurrencePatternWeekly().
			setCampaignSchedulingMultipleRecurrencePatternOccurrence( 1 ).
			setCampaignSchedulingMultipleRecurrencePatternDaysOfWeek( M, F, Sa ).
			setCampaignSchedulingMultipleRecurrencePatternDaysOfWeek( Th, F ).
			/** configure multiple scheduling tab - monthly **/
			setCampaignSchedulingMultipleRecurrencePatternMonthly().
			/** configure multiple scheduling tab - monthly - simple day**/
			setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDay().
			setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverDay( 3 ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverMonth( 4 ).
			/** configure multiple scheduling tab - monthly - general day**/
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDay().
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek( First ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek( Second ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek( Third ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek( Fourth ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek( Last ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( Monday ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( Tuesday ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( Wednesday ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( Thursday ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( Friday ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( Saturday ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( Sunday ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayEverMonth( 3 ).
			/** configure multiple scheduling tab - common parameters**/
			setCampaignSchedulingMultipleProvisioningDuration( 1 ).
			setCampaignSchedulingMultipleDaysBetweenProvisioningStartDateAndExecutionStartDate( 1 ).
			setCampaignSchedulingMultipleExecutionPeriod( 1 ).
			setCampaignSchedulingMultipleRecurrencePatternMonthly().
			setCampaignSchedulingMultipleStartDate( startDate ).
			setCampaignSchedulingMultipleRangeOfRecurrenceNoEndDate().
			setCampaignSchedulingMultipleRangeOfRecurrenceValueEndAfterNOccurences( 1 ).
			setCampaignSchedulingMultipleRangeOfRecurrenceValueEndDate( endDate ).
			/** configure dialog tab **/
			openDialogTab().
			setCampaignDialogueShortCode( "333" ). // suppose to have the value 333 in the channel_destination table
			setCampaignDialogueEmailAddress( "" ).
			setDialogueNotificationDaysOfNotificationBeforeExecution( 2 ).
			openDialogueNotification().
			cancelDialogueNotification().
			openDialogueNotification().
			editDialogueNotification( English, SMS ).
			setDialogueNotificationMessage( "notification message" ).
			cancelDialogueNotificationEditing().
			editDialogueNotification( English, SMS ).
			setDialogueNotificationMessage( "notification message" ).
			saveDialogueNotificationEditing().
			editDialogueNotification( English, SMS ).
			setDialogueNotificationMessage( "modify notification message" ).
			saveDialogueNotificationEditing().
			saveDialogueNotification().
			setCampaignDialogueApplyCampaignToNotifiedOnly().
			setCampaignDialogueNotificationTime( "00:00" );		

	}
		
}
