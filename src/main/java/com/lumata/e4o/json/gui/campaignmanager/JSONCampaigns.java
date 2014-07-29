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
	public String getSchedulingMultipleRecurrencePattern() {
		return this.getString( "scheduling.multipleRecurrence.recurrencePattern" );
	}
	
	public String getSchedulingMultipleRecurrencePatternWeeklyRecurEveryWeek() {
		return this.getString( "scheduling.multipleRecurrence.recurrencePatternWeekly.recurEveryWeek" );
	}

	public List<String> getSchedulingMultipleRecurrencePatternWeeklyRecurEveryDay() {
		return this.getListOfString( "scheduling.multipleRecurrence.recurrencePatternWeekly.recurEveryDay" );
	}
	
	public String getSchedulingMultipleProvisioningDuration() {
		return this.getString( "scheduling.multipleRecurrence.provisionigDuration" );
	}
	
	public String getSchedulingMultipleDaysBetweenProvisioningAndExecutionStartDate() {
		return this.getString( "scheduling.multipleRecurrence.daysBetweenProvisioningAndExecutionStartDate" );
	}
	
	public String getSchedulingMultipleExecutionDuration() {
		return this.getString( "scheduling.multipleRecurrence.executionDuration" );
	}
	
	public String getSchedulingMultipleStartDate() {
		return this.getString( "scheduling.multipleRecurrence.startDate" );
	}

	public String getSchedulingMultipleRangeOfRecurrenceType() {
		return this.getString( "scheduling.multipleRecurrence.rangeOfRecurrenceType" );
	}

	public String getSchedulingMultipleRangeOfRecurrenceValue() {
		return this.getString( "scheduling.multipleRecurrence.rangeOfRecurrenceValue" );
	}

}
