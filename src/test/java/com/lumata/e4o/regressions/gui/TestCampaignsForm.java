package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.gui.campaignmanager.CampaignsForm;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaign_;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaigns;
import com.lumata.e4o.json.gui.campaignmanager.JSONCriteria;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;

import static com.lumata.e4o.gui.common.NotificationForm.NotificationChannel.*;
import static com.lumata.e4o.gui.common.NotificationForm.NotificationTongue.*;
import static com.lumata.e4o.gui.campaignmanager.CampaignsForm.SchedulingMultipleMonthlyGeneralDayOfWeek.*;
import static com.lumata.e4o.gui.campaignmanager.CampaignsForm.SchedulingMultipleMonthlyGeneralDayOrdinalDayOfWeek.*;
import static com.lumata.e4o.gui.campaignmanager.CampaignsForm.SchedulingMultipleRecurrencePatternWeekly.*;
import static com.lumata.e4o.gui.campaignmanager.CampaignsForm.TargetingMode.*;

import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(
	@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
)
@TCSeleniumWebDriver
public class TestCampaignsForm<CampaignForm> extends ParentTestCase {

	private CampaignsForm campaignsForm;
	
	
	@BeforeClass
	public void initCampaignsForm() throws NetworkEnvironmentException, FormException {		
	
		/** Campaigns Form **/
		campaignsForm = new CampaignsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
	}
	
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void testUc34_01CreateCampaign_ExistingModel() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Calendar startDate = Calendar.getInstance();
		
		Calendar endDate = Calendar.getInstance();
		
		Calendar provEndDate = (Calendar)endDate.clone();
		
		
		/**
		 * Campaign Notification
		 * - simple scheduling
		 * - absolute end date
		 * - restricted no sample target
		 */
			campaignsForm.
			openForm().
			addBtn().		
			/** configure definition tab **/
			openDefinitionTab().
			setCampaignModel("CMS_09").
			setCampaignName( "CAMPAIGN_09" ).
			setCampaignDescription( "CAMPAIGN_09" + " description" ).
			setByPassMediaType( false );
			/** configure single scheduling tab **/
			campaignsForm.openSchedulingTab();
			campaignsForm.setCampaignSingleSchedulingType();		
			campaignsForm.setCampaignSingleSchedulingExecutionStart( startDate );
			campaignsForm.setCampaignSingleSchedulingExecutionEndRelative( 101 );
			
			campaignsForm.setCampaignSingleSchedulingProvisioningStartDate( startDate );
			
			campaignsForm.setCampaignSingleSchedulingProvisioningEndDate( provEndDate );
			/** configure dialog tab **/
			campaignsForm.openDialogTab().
			setCampaignDialogueEmailAddress( "" ).
			openDialogueNotification().
			editDialogueNotification( English, SMS ).
			setDialogueNotificationMessage( "campaign notification message ( ###campaign_name### )" ).
			saveDialogueNotificationEditing().
			saveDialogueNotification();
			/** configure activation tab **/
			campaignsForm.openActivationTab().
			activateBtn().
			confirmCampaignActivation();	
			
			/** Verify activated Campaign exists or not **/
			WebDriverWait wait=new WebDriverWait(seleniumWebDriver.getWrappedDriver(), 30);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Campaign List']//ancestor::table[@class='tableList']")));
			Boolean campaign_status = campaignsForm.isCampaignNameInList("CAMPAIGN_09");
			Reporter.log("Creation of \"Campaign Form\".", LOG_TO_STD_OUT);
			
			if(campaign_status==true)
			{
				Assert.assertTrue(campaign_status);
				Reporter.log("Campaign created and activated Succesfully!");
	
			}
			else
			{
				Assert.fail("The Campaign creation Failed!");
				Reporter.log("Creation of Campaign Failed!");
			}
			
			}
        
					
	@Test( enabled=TEST_ENABLED, priority = 2 )
	public void testUc34_02CreateCampaign_MulSch_ExistingModel() throws FormException{
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Calendar startDate = Calendar.getInstance();
		
		Calendar endDate = Calendar.getInstance();
		
		Calendar provEndDate = (Calendar)endDate.clone();
		
		/**
		 * Campaign Notification
		 * - simple scheduling
		 * - absolute end date
		 * - restricted no sample target
		 */
			campaignsForm.
			openForm().
			addBtn().		
			/** configure definition tab **/
			openDefinitionTab().
			setCampaignModel("CMS_09").
			setCampaignName( "CAMPAIGN_14" ).
			setCampaignDescription( "CAMPAIGN_14" + " description" ).
			setByPassMediaType( false );
			/** configure single scheduling tab **/
			/** configure multiple scheduling tab **/
			campaignsForm.
			openSchedulingTab().
			setCampaignMultipleSchedulingType().
			/** configure multiple scheduling tab - weekly **/
			setCampaignSchedulingMultipleRecurrencePatternWeekly().
			setCampaignSchedulingMultipleRecurrencePatternOccurrence( 1 ).
			setCampaignSchedulingMultipleRecurrencePatternDaysOfWeek( Th, F ).
			/** configure multiple scheduling tab - monthly **/
			setCampaignSchedulingMultipleRecurrencePatternMonthly().
			/** configure multiple scheduling tab - monthly - simple day**/
			setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDay().
			setCampaignSchedulingMultipleMonthlyRecurrencePatternSimpleDayEverMonth( 4 ).
			/** configure multiple scheduling tab - monthly - general day**/
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDay().
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOrdinalDayOfWeek( Last ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayOfWeek( Sunday ).
			setCampaignSchedulingMultipleMonthlyRecurrencePatternGeneralDayEverMonth( 3 ).
			/** configure multiple scheduling tab - common parameters**/
			setCampaignSchedulingMultipleProvisioningDuration( 1 ).
			setCampaignSchedulingMultipleDaysBetweenProvisioningStartDateAndExecutionStartDate( 1 ).
			setCampaignSchedulingMultipleExecutionPeriodType("Absolute").
			setCampaignSchedulingMultipleExecutionPeriod( 1 ).
			
			setCampaignSchedulingMultipleRecurrencePatternMonthly().
			setCampaignSchedulingMultipleStartDate( startDate ).
			setCampaignSchedulingMultipleRangeOfRecurrenceNoEndDate().
			setCampaignSchedulingMultipleRangeOfRecurrenceValueEndAfterNOccurences( 1 ).
			setCampaignSchedulingMultipleRangeOfRecurrenceValueEndDate( endDate ).
			/** configure dialog tab **/
			openDialogTab().
			setCampaignDialogueEmailAddress( "" ).
			setDialogueNotificationDaysOfNotificationBeforeExecution( 1 ).
			openDialogueNotification().
			editDialogueNotification( English, SMS ).
			setDialogueNotificationMessage( "Test Campaign notification message" ).
			saveDialogueNotificationEditing().
			saveDialogueNotification().
			setCampaignDialogueApplyCampaignToNotifiedOnly().
			setCampaignDialogueNotificationTime( "00:00" );
			/** configure activation tab **/
			campaignsForm.openActivationTab().
			activateBtn().
			confirmCampaignActivation();	
			
			/** Verify activated Campaign exists or not **/
			
			Boolean campaign_status = campaignsForm.isCampaignNameInList("CAMPAIGN_14");
			Reporter.log("Creation of \"Campaign Form\".", LOG_TO_STD_OUT);
			
			if(campaign_status==true)
			{
				Assert.assertTrue(campaign_status);
				Reporter.log("Campaign created and activated Succesfully!");
	
			}
			else
			{
				Assert.fail("The Campaign creation Failed!");
				Reporter.log("Creation of Campaign Failed!");
			}
			
			}
        

	
	@Test( enabled=TEST_ENABLED, priority = 3 )
	public void testUc34_03CampaignNotificationSimpleScheduling() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Calendar startDate = Calendar.getInstance();
		
		Calendar endDate = Calendar.getInstance();
		
		Calendar provEndDate = (Calendar)endDate.clone();
			
		
		/**
		 * Campaign Notification
		 * - simple scheduling
		 * - absolute end date
		 * - restricted no sample target
		 */
		
		campaignsForm.
			openForm().
			addBtn().		
			/** configure definition tab **/
			openDefinitionTab().
			setCampaignExecutionModeNotification().
			
			setCampaignName( "CAMPAIGN_15" ).
			
			setCampaignDescription( "CAMPAIGN_15" + " description" ).
			setByPassMediaType( false ).
			/** configure single scheduling tab **/
			openSchedulingTab().
			setCampaignSingleSchedulingType().			
			setCampaignSingleSchedulingExecutionStart( startDate ).
			setCampaignSingleSchedulingExecutionEndAbsolute( endDate ).
			setCampaignSingleSchedulingProvisioningStartDate( startDate ).
			setCampaignSingleSchedulingProvisioningEndDate( provEndDate ).
			/** configure dialog tab **/
			openDialogTab().
			setCampaignDialogueEmailAddress( "" ).
			openDialogueNotification().
			editDialogueNotification( English, SMS ).
			setDialogueNotificationMessage( "campaign notification message ( ###campaign_name### )" ).
			saveDialogueNotificationEditing().
			saveDialogueNotification().
			/** configure target tab **/
			openTargetTab().
			setCampaignTargetTargetingMode( Restricted ).
			setCampaignTargetTargetingRestrictedModeCriteria();
			campaignsForm.setCriteriaCampaign();
			campaignsForm.configureCriteria("Subscriber.Msisdn","9650450905");
	
			campaignsForm.setCampaignTargetTargetingRestrictedConfigureASampleNoSample();
			/** configure activation tab **/
			campaignsForm.openActivationTab().
			activateBtn().
			confirmCampaignActivation();	
		/** Verify activated Campaign exists or not **/
		
		Boolean campaign_status = campaignsForm.isCampaignNameInList("CAMPAIGN_15 (Notification)");
		Reporter.log("Creation of \"Campaign Form\".", LOG_TO_STD_OUT);
		
		if(campaign_status==true)
		{
			Assert.assertTrue(campaign_status);
			Reporter.log("Campaign created and activated Succesfully!");

		}
		else
		{
			Assert.fail("The Campaign creation Failed!");
			Reporter.log("Creation of Campaign Failed!");
		}
		
		}
	
    
	
	
	@Test( enabled=TEST_ENABLED, priority = 4 )
	public void testUc34_04_EditcampaignForm() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Boolean status=false;
		campaignsForm.
		openForm()
		.campaignEditButton("CAMPAIGN_14").
			
		/** configure definition tab **/
		openDefinitionTab().
		setCampaignExecutionMode( CampaignsForm.ExecutionMode.Notification ).
		
		/** configure target tab **/
		openTargetTab().
		setCampaignTargetTargetingMode( Restricted ).
		setCampaignTargetTargetingRestrictedModeCriteria();
		campaignsForm.setCriteriaCampaign();
		campaignsForm.configureCriteria("Subscriber.Msisdn","393886933857");

		campaignsForm.setCampaignTargetTargetingRestrictedConfigureASampleNoSample().
		openActivationTab();
		campaignsForm.saveBtn().
		confirmCampaignAlert(status);		
		status = campaignsForm.isCampaignNameInList("CAMPAIGN_14 (Notification)");
		Reporter.log("Creation of \"Campaign Form\".", LOG_TO_STD_OUT);
		
		if(status==true)
		{
			Assert.assertTrue(status);
			Reporter.log("Campaign updated Succesfully!");

		}
		else
		{
			Assert.fail("The Campaign updation Failed!");
			Reporter.log("Updation of Campaign Failed!");
		}
		
		}
	

	@Test( enabled=TEST_ENABLED, priority = 5 )
	public void testUc34_05_CopycampaignForm() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Calendar startDate = Calendar.getInstance();
		
		Calendar endDate = Calendar.getInstance();
		
		Calendar provEndDate = (Calendar)endDate.clone();
		
		Boolean status=false;
		campaignsForm.
		openForm().
		campaignCopyButton("CAMPAIGN_14 (Notification)").
		
		/** configure definition tab **/
		setCampaignExecutionModeModel().
		setCampaignModel("CMS_09").
		
		setCampaignName( "CAMPAIGN_18" ).
		
		setCampaignDescription( "CAMPAIGN_18" + " description" ).
		setByPassMediaType( false );
		/** configure single scheduling tab **/
		campaignsForm.openSchedulingTab();
		campaignsForm.setCampaignSingleSchedulingType();		
		campaignsForm.setCampaignSingleSchedulingExecutionStart( startDate );
		campaignsForm.setCampaignSingleSchedulingExecutionEndRelative( 101 );
		
		campaignsForm.setCampaignSingleSchedulingProvisioningStartDate( startDate );
		
		campaignsForm.setCampaignSingleSchedulingProvisioningEndDate( provEndDate );
		/** configure dialog tab **/
		campaignsForm.openDialogTab().
		setCampaignDialogueEmailAddress( "" ).
		openDialogueNotification().
		editDialogueNotification( English, SMS ).
		setDialogueNotificationMessage( "campaign notification message ( ###campaign_name### )" ).
		saveDialogueNotificationEditing().
		saveDialogueNotification();
		/** configure activation tab **/
		campaignsForm.openActivationTab().
		activateBtn().
		confirmCampaignActivation();	
		
		/** Verify activated Campaign exists or not **/
		
		Boolean campaign_status = campaignsForm.isCampaignNameInList("CAMPAIGN_18");
		Reporter.log("Copy of \"Campaign Form\".", LOG_TO_STD_OUT);
		
		if(campaign_status==true)
		{
			Assert.assertTrue(campaign_status);
			Reporter.log("Campaign copied and activated Succesfully!");

		}
		else
		{
			Assert.fail("Campaign copy Failed!");
			Reporter.log("Copy of Campaign Failed!");
		}
		
		}

	@Test( enabled=TEST_ENABLED, priority = 6 )
	public void testUc34_06_DeletecampaignForm() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Boolean status=false;
		campaignsForm.
		openForm()
		/** Stopping an activated Campaign **/
		
		.campaignStopButton("CAMPAIGN_18").
		closeAlertAndGetItsText();
		campaignsForm.confirmCampaignAlert(status);
		
		/** Deleting a Suspended Campaign **/
		
		campaignsForm.
		campaignDeleteButton("CAMPAIGN_18").	
		
		closeAlertAndGetItsText();
		
		campaignsForm.confirmCampaignAlert(status);
		
		status = campaignsForm.isCampaignNameInList("CAMPAIGN_18");
		
		Reporter.log("Deletion of \"Campaign Form\".", LOG_TO_STD_OUT);
		
		if(status!=true)
		{
			AssertJUnit.assertTrue("Campaign deleted successfully", true);
			Reporter.log("Campaign deleted successfully",LOG_TO_STD_OUT);
		   
		}
		else
		{
			Assert.fail("The Campaign deletion Failed!");
			Reporter.log("Deletion of Campaign Failed!");
		}
		
		}
	

}



	
