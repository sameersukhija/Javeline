package com.lumata.e4o.json.gui.campaignmanager;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONCampaign_ extends JsonConfig {

	private JsonConfig currentCampaign;
	
	public JSONCampaign_( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONArray getList() throws JSONException {
		
		return (JSONArray)getJSONArrayFromPath( "campaigns" );
				
	}
	
	
	
	public Boolean getEnabled() throws JSONException {
		return currentCampaign.getBooleanFromPath( "enabled" );
	}
	
	/** Definition */
	public String getName() throws JSONException {
		return currentCampaign.getStringFromPath( "name" );
	}

	public String getDescription() throws JSONException {
		return currentCampaign.getStringFromPath( "description" );
	}
	
	public String getExecutionMode() throws JSONException {
		return currentCampaign.getStringFromPath( "definition.executionMode" );
	}
	
	public String getCampaignType() throws JSONException {
		return currentCampaign.getStringFromPath( "definition.campaignType" );
	}
	
	public Boolean getBypassMediaType() throws JSONException {
		return currentCampaign.getBooleanFromPath( "definition.byPassMediaType" );
	}

	/** Scheduling */
	public String getSchedulingTypeOfRecurrence() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.typeOfRecurrence" );
	}

	/** Scheduling Single */
	public String getSchedulingSingleExecutionStart() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.singleRecurrence.executionStart" );
	}
	
	public String getSchedulingSingleExecutionEndType() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.singleRecurrence.executionEndType" );
	}
	
	public String getSchedulingSingleExecutionEndValue() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.singleRecurrence.executionEndValue" );
	}
	
	public String getSchedulingSingleProvisioningStart() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.singleRecurrence.provisioningStart" );
	}
	
	public String getSchedulingSingleProvisioningEnd() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.singleRecurrence.provisioningEnd" );
	}
	
	public String getSchedulingSingleDaysBetweenProvisioningAndStartDates() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.singleRecurrence.daysBetweenProvisioningAndExecutionStartDates" );
	}
	
	/** Scheduling Multiple */
	public String getSchedulingMultipleRecurrencePattern() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.multipleRecurrence.recurrencePattern" );
	}
	
	public String getSchedulingMultipleRecurrencePatternWeeklyRecurEveryWeek() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.multipleRecurrence.recurrencePatternWeekly.recurEveryWeek" );
	}

	public JSONArray getSchedulingMultipleRecurrencePatternWeeklyRecurEveryDay() throws JSONException {
		return currentCampaign.getJSONArrayFromPath( "scheduling.multipleRecurrence.recurrencePatternWeekly.recurEveryDay" );
	}
	
	public String getSchedulingMultipleProvisioningDuration() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.multipleRecurrence.provisionigDuration" );
	}
	
	public String getSchedulingMultipleDaysBetweenProvisioningAndExecutionStartDate() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.multipleRecurrence.daysBetweenProvisioningAndExecutionStartDate" );
	}
	
	public String getSchedulingMultipleExecutionDuration() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.multipleRecurrence.executionDuration" );
	}
	
	public String getSchedulingMultipleStartDate() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.multipleRecurrence.startDate" );
	}

	public String getSchedulingMultipleRangeOfRecurrenceType() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.multipleRecurrence.rangeOfRecurrenceType" );
	}

	public String getSchedulingMultipleRangeOfRecurrenceValue() throws JSONException {
		return currentCampaign.getStringFromPath( "scheduling.multipleRecurrence.rangeOfRecurrenceValue" );
	}
	
	public void setCampaignById( Integer currentCampaignId ) throws JSONException {
		
		this.currentCampaign = new JsonConfig( getList().getJSONObject( currentCampaignId ) );
				
	}
	
	
	public Map<String, JSONCriteria> getCriteria() throws JSONException{
		Map<String, JSONCriteria> criteria = new LinkedHashMap<String, JSONCriteria>();
		
		JSONArray jsonCriteria = getJSONArrayFromPath( "criteria" );
		
		for( int j = 0; j < jsonCriteria.length(); j++ ) {
			
			String criteriaName = "criteria" + j;
			
			criteria.put( criteriaName, new JSONCriteria( jsonCriteria.getJSONObject( j ) ) );
			
		}
	
		return criteria;  		
	
	}

	public Boolean getuseHierarchy() throws JSONException {
		return currentCampaign.getBooleanFromPath( "useHierarchy" );
	}

	public Map<String, JSONEvent_> getEvents() throws JSONException { 		
    	
		Map<String, JSONEvent_> events = new LinkedHashMap<String, JSONEvent_>();
		
		JSONArray jsonEvents = currentCampaign.getJSONArrayFromPath( "events" );
		
		for( int j = 0; j < jsonEvents.length(); j++ ) {
			
			String eventName = "event" + j;
			
			events.put( eventName, new JSONEvent_( jsonEvents.getJSONObject( j ) ) );
			
		}
		
		return events;  		
	
	}

	
}


