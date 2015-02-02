package com.lumata.e4o.json.gui.campaignmanager;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONCampaignModel extends JsonConfig {

	private JsonConfig currentCampaignModel;
	
	public JSONCampaignModel( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONArray getList() throws JSONException {
		
		return (JSONArray)getJSONArrayFromPath("campaignModels");
				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentCampaignModel.getBooleanFromPath( "enabled" );
	}
	
	public String getName() throws JSONException {
		return currentCampaignModel.getStringFromPath( "name" );
	}

	public String getDescription() throws JSONException {
		return currentCampaignModel.getStringFromPath( "description" );
	}
	
	public Map<String, JSONEvent_> getEvents() throws JSONException { 		
    	
		Map<String, JSONEvent_> events = new LinkedHashMap<String, JSONEvent_>();
		
		JSONArray jsonEvents = currentCampaignModel.getJSONArrayFromPath( "events" );
		
		for( int j = 0; j < jsonEvents.length(); j++ ) {
			
			String eventName = "event" + j;
			
			events.put( eventName, new JSONEvent_( jsonEvents.getJSONObject( j ) ) );
			
		}
		
		return events;  		
	
	}

	public JSONEvent_ getEventByIndex( Integer eventIndex ) throws JSONException {
		
		return new JSONEvent_( currentCampaignModel.getJSONArrayFromPath( "events" ).getJSONObject( eventIndex ) );
		
	}
	
	public JSONEvent_ getEventByName( String eventName ) throws JSONException {
		
		return getEvents().get( eventName );
		
	}
	
	public JSONObject getErrorActions() throws JSONException {
		return currentCampaignModel.getJSONObjectFromPath( "errorActions" );
	}
	
	public void setCampaignModelById( Integer currentCampaignModelId ) throws JSONException {
		
		this.currentCampaignModel = new JsonConfig( getList().getJSONObject( currentCampaignModelId ) );
				
	}
	
	public void setName( String name ) {
		setObjectFromPath( "name" , name );
	}
	
}
