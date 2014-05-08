package com.lumata.e4o.json.gui.campaignmanager;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.e4o.json.common.JsonConfig;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONAction extends JsonConfig {

	private static final  Logger logger = LoggerFactory.getLogger( JSONAction.class );
	
	private JSONActionTime jsonActionTime;
	
	public JSONAction( JSONObject actionCfg ) {
		
		super( actionCfg );
		
	}

    public String getName() { 		
    	return getStringFromPath("name"); 		
	}

	public String getValue() { 		
    	return getStringFromPath("value"); 		
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
	
	public void setOption( String value ) {
		setObjectFromPath( "option", value );
	}

	public Boolean hasActionTime() throws JSONException {
		
		Object obj = getJSONObjectFromPath("time");
		
		return ( null != obj ? true : false );
		
	}
	
}
