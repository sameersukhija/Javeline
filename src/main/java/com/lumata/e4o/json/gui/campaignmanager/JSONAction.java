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
	
	public JSONAction( JSONObject actionCfg ) {
		
		super( actionCfg );
		
	}

    public String getName() { 		
    	return getStringFromPath("name"); 		
	}

    public String getName1() { 		
    	return getStringFromPath("name1"); 		
	}

    public String getName2() { 		
    	return getStringFromPath("name2"); 		
	}

    public String getValue() { 		
    	return getStringFromPath("value"); 		
 	}
	
    public String getValue1() { 		
    	return getStringFromPath("value1"); 		
 	}
	
    public String getOption() { 		
    	return getStringFromPath("option"); 		
 	}
	
    public String getOption1() { 		
    	return getStringFromPath("option1"); 		
 	}
	
    public String getOption2() { 		
    	return getStringFromPath("option2"); 		
 	}
    
    public JSONActionTime getActionTime() throws JSONException { 		
		return new JSONActionTime( getJSONObjectFromPath("time") ); 	
	}
	
	public void setName( String value ) {
		setObjectFromPath( "name", value );
	}
	
	public void setName1( String value1 ) {
		setObjectFromPath( "name1", value1 );
	}
	
	public void setName2( String value2 ) {
		setObjectFromPath( "name2", value2 );
	}
	
	public void setValue( String value ) {
		setObjectFromPath( "value", value );
	}
	
	public void setValue1( String value1 ) {
		setObjectFromPath( "value1", value1 );
	}
	
	public void setValue2( String value2 ) {
		setObjectFromPath( "value2", value2 );
	}
	
	public void setOption( String value ) {
		setObjectFromPath( "option", value );
	}

	public void setOption1( String value1 ) {
		setObjectFromPath( "option1", value1 );
	}

	public void setOption2( String value2 ) {
		setObjectFromPath( "option2", value2 );
	}

	public Boolean hasActionTime() throws JSONException {
		
		Object obj = getJSONObjectFromPath("time");
		
		return ( null != obj ? true : false );
		
	}
	
}
