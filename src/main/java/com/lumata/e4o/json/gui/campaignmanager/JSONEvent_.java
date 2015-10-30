package com.lumata.e4o.json.gui.campaignmanager;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONEvent_ extends JsonConfig {

	public JSONEvent_( JSONObject eventCfg ) {
		
		super( eventCfg );
			
	}

	public String getEventType() {
		return getStringFromPath( "eventType");
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
	
	public Map<String, JSONAction> getActions() throws JSONException { 		
    	
		Map<String, JSONAction> actions = new LinkedHashMap<String, JSONAction>();
		
		JSONArray jsonActions = getJSONArrayFromPath( "actions" );
		
		for( int j = 0; j < jsonActions.length(); j++ ) {
			
			String actionName = "action" + j;
			
			actions.put( actionName, new JSONAction( jsonActions.getJSONObject( j ) ) );
			
		}
	
		return actions;  		
	
	}

	public JSONAction getActionByIndex( Integer actionIndex ) throws JSONException {
		
		return new JSONAction( getJSONArrayFromPath( "actions" ).getJSONObject( actionIndex ) );
		
	}
	
	public JSONAction getActionByName( String actionName ) throws JSONException {
		
		return getActions().get( actionName );
		
	}
	
	public Boolean getBeneficiary() {
		return getBooleanFromPath( "beneficiary");
	}

	public Map<String, JSONNotification> getNotifications() throws JSONException { 		
    	
		Map<String, JSONNotification> notifications = new LinkedHashMap<String, JSONNotification>();
		
		JSONArray jsonNotifications = getJSONArrayFromPath( "notifications" );
		
		for( int j = 0; j < jsonNotifications.length(); j++ ) {
			
			String notificationName = "notification" + j;
			
			notifications.put( notificationName, new JSONNotification( jsonNotifications.getJSONObject( j ) ) );
			
		}
	
		return notifications;  		
	
	}
	
	public JSONNotification getNotificationByIndex( Integer notificationIndex ) throws JSONException {
		
		return new JSONNotification( getJSONArrayFromPath( "notifications" ).getJSONObject( notificationIndex ) );
		
	}
	
	public JSONNotification getNotificationByName( String notificationName ) throws JSONException {
		
		return getNotifications().get( notificationName );
		
	}
	
	public Boolean hasNotification() throws JSONException {
		
		Object obj = getJSONArrayFromPath("notifications");
		
		return ( null != obj ? true : false );
		
	}
	
	public Boolean getDelete() {
		return getBooleanFromPath( "delete");
	}
	
	public Boolean getEdit() {
		return getBooleanFromPath( "edit");
	}

}
