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
public class JSONCriteria extends JsonConfig {

	private static final  Logger logger = LoggerFactory.getLogger( JSONAction.class );
	
	public JSONCriteria( JSONObject criteriaCfg ) {
		
		super( criteriaCfg );
		
	}

    public String getType() { 		
    	return getStringFromPath("type"); 		
	}

	public String getOperator() { 		
    	return getStringFromPath("operator"); 		
 	}
	
	public String getValue() { 		
    	return getStringFromPath("value"); 		
 	}
	
	public void setType( String value ) {
		setObjectFromPath( "type", value );
	}
	
	public void setValue( String value ) {
		setObjectFromPath( "value", value );
	}
	
	public void setOperator( String value ) {
		setObjectFromPath( "operator", value );
	}

}
