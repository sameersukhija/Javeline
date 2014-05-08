package com.lumata.e4o.json.gui.catalogmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONTokenType extends JsonConfig {

	private static final  Logger logger = LoggerFactory.getLogger( JSONTokenType.class );
	
	public enum TokenTypeValidity { seconds, minutes, hours, days }
	
	private JsonConfig currentTokenType;
	
	public JSONTokenType( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONArray getList() throws JSONException {		
		return (JSONArray)getJSONArrayFromPath("tokenTypes");				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentTokenType.getBooleanFromPath( "enabled" );
	}
	
	public String getName() {
		return currentTokenType.getStringFromPath( "name" );
	}
	
	public String getDescription() {
		return currentTokenType.getStringFromPath( "description" );
	}
	
	public String getImageUrl() {
		return currentTokenType.getStringFromPath( "imageUrl" );
	}
	
	public String getFormat() {
		return currentTokenType.getStringFromPath( "format" );
	}

	public String getValidityUnit() {
		return currentTokenType.getStringFromPath( "validityUnit" );
	}

	public String getValidity() {
		return currentTokenType.getStringFromPath( "validity" );
	}
	
	public boolean getUsageUnlimited() {
		return currentTokenType.getBooleanFromPath( "usageUnlimited" );
	}
	
	public String getUsage() {
		return currentTokenType.getStringFromPath( "usage" );
	}

	public JSONObject getErrorActions() throws JSONException {
		return currentTokenType.getJSONObjectFromPath( "errorActions" );
	}
	
	public void setName( String name ) {
		setObjectFromPath( "name" , name );
	}
	
	public void setTokenTypeById( Integer currentTokenType ) throws JSONException {
		
		this.currentTokenType = new JsonConfig( getList().getJSONObject( currentTokenType ) );
				
	}
	
}
