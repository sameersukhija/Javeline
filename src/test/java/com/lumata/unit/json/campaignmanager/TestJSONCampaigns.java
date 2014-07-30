package com.lumata.unit.json.campaignmanager;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaigns;

public class TestJSONCampaigns {
	 
	@Test
	public void testJSONCampaigns() throws JSONSException {
		 
		 JSONCampaigns campaigns = new JSONCampaigns( "input/campaignmanager/campaigns", "campaignCMTemplate" );
		 
		 Assert.assertTrue( campaigns.size() > 0 );
		 
		 /** DEFINITION */
		 Assert.assertEquals( campaigns.get( 0 ).name(), "campaignCMTemplate" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).description(), "campaignCMTemplate" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).executionMode(), "Model" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).campaignType(), "No type" ); 
		 
		 Assert.assertTrue( campaigns.get( 0 ).byPassMediaType() );
		 
		 /** SCHEDULING */
		 Assert.assertEquals( campaigns.get( 0 ).schedulingTypeOfRecurrence(), "Multiple" );
		 
		 /** SCHEDULING SINGLE */
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleExecutionStart(), "2014-05-01" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleExecutionEndType(), "Relative" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleExecutionEndValue(), "1" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleProvisioningStart(), "2014-05-01" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleProvisioningEnd(), "2014-05-01" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingSingleDaysBetweenProvisioningAndStartDates(), "2" );
		 
		 /** SCHEDULING MULTIPLE */
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleRecurrencePattern(), "Weekly" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleRecurrencePatternWeeklyRecurEveryWeek(), "2" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleRecurrencePatternWeeklyRecurEveryDay(), Arrays.asList( "M", "Th" ) );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleProvisioningDuration(), "2" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleDaysBetweenProvisioningAndExecutionStartDate(), "2" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleExecutionDuration(), "5" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleStartDate(), "2014-05-04" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleRangeOfRecurrenceType(), "No end Date" );
		 
		 Assert.assertEquals( campaigns.get( 0 ).schedulingMultipleRangeOfRecurrenceValue(), "" );
		 
	 }
 
}

