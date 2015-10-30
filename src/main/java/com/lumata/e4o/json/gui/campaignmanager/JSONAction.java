package com.lumata.e4o.json.gui.campaignmanager;

import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONAction extends JsonConfig {

	public JSONAction( JSONObject actionCfg ) {
		
		super( actionCfg );
		
	}

    public String getName() { 		
    	return getStringFromPath("name"); 		
	}

    public String getValue() { 		
    	return getStringFromPath("value"); 		
 	}
    
    public String getUnit() { 		
    	return getStringFromPath("unit"); 		
 	}
	
    public String getOption() { 		
    	return getStringFromPath("option"); 		
 	}
	
    public JSONActionTime getActionTime() throws JSONException { 		
		return new JSONActionTime( getJSONObjectFromPath("time") ); 	
	}
	
	public void setName( String value ) {
		setObjectFromPath( "name", value );
	}
	
	public void setValue( String value ) {
		setObjectFromPath( "value", value );
	}
	
	public void setUnit( String unit ) {
		setObjectFromPath( "unit", unit );
	}
	
	public void setOption( String value ) {
		setObjectFromPath( "option", value );
	}

	public Boolean getDelete() {
		return getBooleanFromPath( "delete");
	}
	
	public Boolean getEdit() {
		return getBooleanFromPath( "edit");
	}
	
	public Boolean hasActionTime() throws JSONException {
		
		Object obj = getJSONObjectFromPath("time");
		
		return ( null != obj ? true : false );
		
	}
	
}
