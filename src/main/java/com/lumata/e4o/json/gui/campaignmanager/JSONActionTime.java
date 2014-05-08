package com.lumata.e4o.json.gui.campaignmanager;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONActionTime extends JsonConfig {

	private static final  Logger logger = LoggerFactory.getLogger( JSONActionTime.class );
	
	public JSONActionTime( JSONObject actionTimeCfg ) {
		
		super( actionTimeCfg );
			
	}

    public String getStarTime() { 		
    	return getStringFromPath("starTime"); 		
	}

	public String getDurationType() { 		
    	return getStringFromPath("durationType"); 		
 	}
	
	public String getDurationValue() { 		
    	return getStringFromPath("durationValue"); 		
 	}
	
	public String getDurationTimeType() { 		
    	return getStringFromPath("durationTimeType"); 		
 	}

	public void setStarTime( String value ) {
		setObjectFromPath( "starTime", value );
	}
	
	public void setDurationType( String value ) {
		setObjectFromPath( "durationType", value );
	}
	
	public void setDurationValue( String value ) {
		setObjectFromPath( "durationValue", value );
	}
	
	public void setDurationTimeType( String value ) {
		setObjectFromPath( "durationTimeType", value );
	}

}
