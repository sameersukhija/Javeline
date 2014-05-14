package com.lumata.e4o.json.gui.catalogmanager;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONProductTypes extends JsonConfig {

	private static final Logger logger = LoggerFactory.getLogger(JSONProductTypes.class);

	private JsonConfig currentProductType;
	
	public JSONProductTypes( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONArray getList() throws JSONException {		
		return (JSONArray)getJSONArrayFromPath("suppliers");				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentProductType.getBooleanFromPath( "enabled" );
	}
	
	public String getName() {
		return currentProductType.getStringFromPath( "name" );
	}
	
	public String getDescription() {
		return currentProductType.getStringFromPath( "description" );
	}

	public Map<String, JSONEvent> getCharacteristics() throws JSONException { 		
    	
		Map<String, JSONEvent> characteristics = new LinkedHashMap<String, JSONEvent>();
		
		JSONArray jsonCharacteristics = currentProductType.getJSONArrayFromPath( "characteristics" );
		
		for( int j = 0; j < jsonCharacteristics.length(); j++ ) {
			
			String eventName = "characteristic" + j;
			
			characteristics.put( eventName, new JSONEvent( jsonCharacteristics.getJSONObject( j ) ) );
			
		}
		
		return characteristics;  		
	
	}

	public JSONEvent getCharacteristicByIndex( Integer eventIndex ) throws JSONException {
		
		return new JSONEvent( currentProductType.getJSONArrayFromPath( "events" ).getJSONObject( eventIndex ) );
		
	}
	
	public JSONEvent getCharacteristicByName( String eventName ) throws JSONException {
		
		return getCharacteristics().get( eventName );
		
	}
	
	public JSONObject getErrorActions() throws JSONException {
		return currentProductType.getJSONObjectFromPath( "errorActions" );
	}
	
	public void setName( String name ) {
		setObjectFromPath( "name" , name );
	}
	
	public void setProductTypeById( Integer currentProductType ) throws JSONException {
		
		this.currentProductType = new JsonConfig( getList().getJSONObject( currentProductType ) );
				
	}

}
