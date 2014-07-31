package com.lumata.e4o.json.gui.campaignmanager;

import java.util.List;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.element.JsonElement;

public class JSONCampaigns extends JsonElement {

	private final String JSON_ROOT_ = "campaigns";
	
	public JSONCampaigns( String jsonFolder, String jsonFile ) throws JSONSException {
		
		super( jsonFolder, jsonFile );
		
		setJsonRoot( JSON_ROOT_ );
		
	}

	public JSONCampaigns get( Integer index ) {
		
		super.cursor( index );
		
		return this;
		
	}

	/** DEFINITION section */
	public String name() {
		return this.getString( "name" );
	}
	
	public String description() {
		return this.getString( "description" );
	}
	
	public String executionMode() {
		return this.getString( "definition.executionMode" );
	}
	
	public String campaignType() {
		return this.getString( "definition.campaignType" );
	}
	
	public Boolean byPassMediaType() {
		return this.getBoolean( "definition.byPassMediaType" );
	}
		
	/** SCHEDULING section */
	public Boolean hasSchedulingSettings() {
		return isNotNull( "scheduling" );
	}
	
	public String schedulingTypeOfRecurrence() {
		return this.getString( "scheduling.typeOfRecurrence" );
	}

	/** SCHEDULING SINGLE section */
	public String schedulingSingleExecutionStart() {
		return this.getString( "scheduling.singleRecurrence.executionStart" );
	}
	
	public String schedulingSingleExecutionEndType() {
		return this.getString( "scheduling.singleRecurrence.executionEndType" );
	}
	
	public String schedulingSingleExecutionEndValue() {
		return this.getString( "scheduling.singleRecurrence.executionEndValue" );
	}
	
	public String schedulingSingleProvisioningStart() {
		return this.getString( "scheduling.singleRecurrence.provisioningStart" );
	}
	
	public String schedulingSingleProvisioningEnd() {
		return this.getString( "scheduling.singleRecurrence.provisioningEnd" );
	}
	
	public String schedulingSingleDaysBetweenProvisioningAndStartDates() {
		return this.getString( "scheduling.singleRecurrence.daysBetweenProvisioningAndExecutionStartDates" );
	}
	
	/** SCHEDULING MULTIPLE section */
	public String schedulingMultipleRecurrencePattern() {
		return this.getString( "scheduling.multipleRecurrence.recurrencePattern" );
	}
	
	public String schedulingMultipleRecurrencePatternWeeklyRecurEveryWeek() {
		return this.getString( "scheduling.multipleRecurrence.recurrencePatternWeekly.recurEveryWeek" );
	}

	public List<String> schedulingMultipleRecurrencePatternWeeklyRecurEveryDay() {
		return this.getListOfString( "scheduling.multipleRecurrence.recurrencePatternWeekly.recurEveryDay" );
	}
	
	public String schedulingMultipleProvisioningDuration() {
		return this.getString( "scheduling.multipleRecurrence.provisionigDuration" );
	}
	
	public String schedulingMultipleDaysBetweenProvisioningAndExecutionStartDate() {
		return this.getString( "scheduling.multipleRecurrence.daysBetweenProvisioningAndExecutionStartDate" );
	}
	
	public String schedulingMultipleExecutionDuration() {
		return this.getString( "scheduling.multipleRecurrence.executionDuration" );
	}
	
	public String schedulingMultipleStartDate() {
		return this.getString( "scheduling.multipleRecurrence.startDate" );
	}

	public String schedulingMultipleRangeOfRecurrenceType() {
		return this.getString( "scheduling.multipleRecurrence.rangeOfRecurrenceType" );
	}

	public String schedulingMultipleRangeOfRecurrenceValue() {
		return this.getString( "scheduling.multipleRecurrence.rangeOfRecurrenceValue" );
	}

	/** DIALOGUE section */
	public Boolean hasDialogueSettings() {
		return ( null != this.getBoolean( "dialogue" ) ? true : false  );
	}
	
	public String channelShortCode() {
		return this.getString( "dialogue.channel.shortCode" );
	}
		
	public String channelEmailAddress() {
		return this.getString( "dialogue.channel.emailAddress" );
	}
	
	public String notificationDaysOfNotificationBeforeExecution() {
		return this.getString( "dialogue.notification.daysOfNotificationBeforeExecution" );
	}
	
	public String notificationMessages() {
		return this.getString( "dialogue.notification.messages" );
	}
	
	public Boolean notificationApplyCampaignToNotifiedOnly() {
		return this.getBoolean( "dialogue.notification.applyCampaignToNotifiedOnly" );
	}
	
	public String notificationTime() {
		return this.getString( "dialogue.notification.time" );
	}
	
	/** TARGET section */
	public Boolean hasTargetSettings() {
		return isNotNull( "target" );
	}
	
	/** LIMIT section */
	public Boolean hasLimitSettings() {
		return isNotNull( "limit" );
	}
	
	/** ACTIVATION section */
	public Boolean hasActivationSettings() {
		return isNotNull( "activation" );
	}
	
}
