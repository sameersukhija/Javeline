package com.lumata.e4o.json.gui.catalogmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONRuleChannel extends JsonConfig {
	
	public JSONRuleChannel( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONRuleChannel( JSONObject currentRuleChannel ) {
		
		super( currentRuleChannel );
		
	}
	
	public JSONArray getList() throws JSONException {		
		return (JSONArray)getJSONArrayFromPath("channels");				
	}
	
	public Boolean getEnabled() throws JSONException {
		return getBooleanFromPath( "enabled" );
	}
	
	public String getName() {
		return getStringFromPath( "name" );	
	}
	
	public String getDescription() {
		return getStringFromPath( "description" );
	}

	public Boolean getMandatory() {
		return getBooleanFromPath( "mandatory" );		
	}

	public Boolean getUnlimited() {
		return getBooleanFromPath( "unlimited" );		
	}
	
	public String getMaxOffer() {
		return String.valueOf( getIntegerFromPath( "maxOffer" ) );		
	}

	public JSONObject getErrorActions() throws JSONException {
		return getJSONObjectFromPath( "errorActions" );
	}
	
	public void setName( String name ) {
		setObjectFromPath( "name", name );
	}
	
}
