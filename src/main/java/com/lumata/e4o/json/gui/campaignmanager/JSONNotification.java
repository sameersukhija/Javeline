package com.lumata.e4o.json.gui.campaignmanager;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.expression.operators.json.common.JsonConfig;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONNotification extends JsonConfig {

	private static final  Logger logger = LoggerFactory.getLogger( JSONNotification.class );

	public JSONNotification( JSONObject actionCfg ) {
		
		super( actionCfg );

	}

    public String getTongue() { 		
    	return getStringFromPath("tongue"); 		
	}

	public String getType() { 		
    	return getStringFromPath("type"); 		
 	}
	
	public String getText() { 		
    	return getStringFromPath("text"); 		
 	}
	
	public void setTongue( String value ) {
		setObjectFromPath( "tongue", value );
	}
	
	public void setType( String value ) {
		setObjectFromPath( "type", value );
	}
	
	public void setText( String value ) {
		setObjectFromPath( "text", value );
	}
	
}
