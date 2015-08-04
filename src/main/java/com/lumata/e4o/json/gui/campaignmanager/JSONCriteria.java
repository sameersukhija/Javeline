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

    public String getType1() { 		
    	return getStringFromPath("type1"); 		
	}

    public String getType2() { 		
    	return getStringFromPath("type2"); 		
	}
    
    public String getOperator() { 		
    	return getStringFromPath("operator"); 		
 	}
	
    public String getOperator1() { 		
    	return getStringFromPath("operator1"); 		
 	}
	
    public String getOperator2() { 		
    	return getStringFromPath("operator2"); 		
 	}
    
    public String getValue() { 		
    	return getStringFromPath("value"); 		
 	}
	
    public String getValue1() { 		
    	return getStringFromPath("value1"); 		
 	}
	
    public String getValue2() { 		
    	return getStringFromPath("value2"); 		
 	}
    
    public void setType( String value ) {
		setObjectFromPath( "type", value );
	}
	
    public void setType1( String value1 ) {
		setObjectFromPath( "type1", value1 );
	}
	
    public void setType2( String value2 ) {
		setObjectFromPath( "type2", value2 );
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
	
    public void setOperator( String value ) {
		setObjectFromPath( "operator", value );
	}

    public void setOperator1( String value1 ) {
		setObjectFromPath( "operator1", value1 );
	}

    public void setOperator2( String value2 ) {
    	setObjectFromPath( "operator2", value2 );
    }

}
