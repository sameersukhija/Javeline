package com.lumata.e4o.json.gui.operationmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.TrafficGeneratorEventException;
import com.lumata.e4o.gui.operationmanager.*;
import com.lumata.e4o.json.common.JsonConfig;

public class TrafficGeneratorEvent extends JsonConfig {
	private JsonConfig currentTrfevent;

	public TrafficGeneratorEvent(String folder, String file) throws JSONSException {
		
		super(folder, file);
	}
	

	public String getSubscriberID() {
		
		return currentTrfevent.getStringFromPath("subscriber_id");
		
				
	}
	
	public String getSource() {
		
		return currentTrfevent.getStringFromPath("source"); 
		
	}
	
	public String getInterpretor() {
		
		return currentTrfevent.getStringFromPath("interpretor"); 
		
	}
	
	public String getEventClass() {
	
		return currentTrfevent.getStringFromPath("event_class"); 
		
		
	}
	
	public String getParametername() {
			
		return currentTrfevent.getStringFromPath("event_name"); 
		
	}

	public String getParametervalue() {
		
		return currentTrfevent.getStringFromPath("event_value"); 
		
	}
	public JSONArray getList() throws JSONException {
		
		return getJSONArrayFromPath("EventGeneration");
				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentTrfevent.getBooleanFromPath( "enabled" );
	}

	
	public void setTrafficById( Integer currentTrafficId ) throws JSONException {
		
		this.currentTrfevent = new JsonConfig( getList().getJSONObject( currentTrafficId ) );
				
	}
	
	
	
}
