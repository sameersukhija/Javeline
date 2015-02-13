package com.lumata.unit.json.campaignmanager;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaigns;
import com.lumata.e4o.json.gui.common.JSONEvents;

public class TestJSONCampaigns {
	 
	@Test
	public void testJSONCampaigns() throws JSONSException {
		 
		 JSONCampaigns campaigns = new JSONCampaigns( "input/campaignmanager/campaigns", "testCampaignCMTemplate" );
		 
		 Assert.assertTrue( campaigns.size() > 0 );
		 
		 Assert.assertTrue( campaigns.get( 0 ).isEnabled() );
		 		 
		 /** DEFINITION */
		 Assert.assertEquals( campaigns.get( 0 ).name(), "campaignCMTemplate" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).description(), "campaignCMTemplate" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).executionMode(), "Model" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).campaignType(), "No type" ); 
		 
		 Assert.assertTrue( campaigns.get( 0 ).byPassMediaType() );
		 
		 /** SCHEDULING */
		 Assert.assertTrue( campaigns.get( 0 ).hasSchedulingSettings() );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingTypeOfRecurrence(), "Multiple" );
		 
		 /** SCHEDULING SINGLE */
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleExecutionStart(), "2015-05-01" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleExecutionEndType(), "Absolute" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleExecutionEndValue(), "2015-05-31" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleProvisioningStart(), "2015-05-01" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleProvisioningEnd(), "2015-05-27" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleDaysBetweenProvisioningAndStartDates(), "2" );
		 
		 /** SCHEDULING MULTIPLE */
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleRecurrencePattern(), "Monthly" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleRecurrencePatternWeeklyRecurEveryWeek(), "2" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleRecurrencePatternWeeklyRecurEveryDay(), Arrays.asList( "M", "Th" ) );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleProvisioningDuration(), "2" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleDaysBetweenProvisioningAndExecutionStartDate(), "2" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleExecutionDuration(), "5" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleStartDate(), "2014-05-04" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleRangeOfRecurrenceType(), "EndDate" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleRangeOfRecurrenceValue(), "2014-05-05" );
		 
		 /** DIALOGUE */
		 Assert.assertTrue( campaigns.get( 0 ).hasDialogueSettings() );
		 
		 Assert.assertEquals( campaigns.get( 0 ).dialogueChannelShortCode(), "111" );
		
		 Assert.assertEquals( campaigns.get( 0 ).dialogueEmailAddress(), "info@lumatagroup.com" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).dialogueNotificationDaysOfNotificationBeforeExecution(), "5" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).dialogueNotificationMessages(), null );
		 
		 Assert.assertTrue( campaigns.get( 0 ).dialogueNotificationApplyCampaignToNotifiedOnly() );
		 
		 Assert.assertEquals( campaigns.get( 0 ).dialogueNotificationTime(), "10:00" );
	
		 /** TARGET */
		 Assert.assertTrue( campaigns.get( 0 ).hasTargetSettings() );
		 
		 JSONEvents events = campaigns.get( 0 ).campaignTargetOpenedSubscriberRestrictionsEvents();
		 
		 System.out.println( events.get( 0 ).eventType() );
		 
		 //System.out.println( events );
		 	 
		 /** LIMIT */
		 Assert.assertFalse( campaigns.get( 0 ).hasLimitSettings() );
		 		 
		 /** ACTIVATION */
		 Assert.assertTrue( campaigns.get( 0 ).hasActivationSettings() );
		 
	 }
 
}

