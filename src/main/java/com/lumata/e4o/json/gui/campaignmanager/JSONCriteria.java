package com.lumata.e4o.json.gui.campaignmanager;

import org.json.JSONObject;

import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONCriteria extends JsonConfig {

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

	public Boolean getDelete() {
		return getBooleanFromPath( "delete");
	}
	
	public Boolean getEdit() {
		return getBooleanFromPath( "edit");
	}
	
}
