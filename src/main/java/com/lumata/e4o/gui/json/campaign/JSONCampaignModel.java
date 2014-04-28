package com.lumata.e4o.gui.json.campaign;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.expression.operators.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONCampaignModel extends JsonConfig {

	private JsonConfig currentCampaignModel;
	
	public JSONCampaignModel( String folder, String file ) throws JSONSException, IOFileException {
		
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
	
	public Map<String, JSONEvent> getEvents() throws JSONException { 		
    	
		Map<String, JSONEvent> events = new LinkedHashMap<String, JSONEvent>();
		
		JSONArray jsonEvents = currentCampaignModel.getJSONArrayFromPath( "events" );
		
		for( int j = 0; j < jsonEvents.length(); j++ ) {
			
			String eventName = "event" + j;
			
			events.put( eventName, new JSONEvent( jsonEvents.getJSONObject( j ) ) );
			
		}
		
		return events;  		
	
	}

	public JSONEvent getEventByIndex( Integer eventIndex ) throws JSONException {
		
		return new JSONEvent( currentCampaignModel.getJSONArrayFromPath( "events" ).getJSONObject( eventIndex ) );
		
	}
	
	public JSONEvent getEventByName( String eventName ) throws JSONException {
		
		return getEvents().get( eventName );
		
	}
	
	public void setCampaignModelById( Integer currentCampaignModelId ) throws JSONException {
		
		this.currentCampaignModel = new JsonConfig( getList().getJSONObject( currentCampaignModelId ) );
				
	}
	
}
