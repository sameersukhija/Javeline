package com.lumata.common.testing.json;

import java.util.Map;

import com.lumata.common.testing.exceptions.JSONSException;

/**
 *	This is the configuration element that will be configured into DUT.
 *	It is a basic Json map with the addition of "Error Actions" object to describe
 *	how to "recover" error condition during configuration
 */
public abstract class JsonConfigurationElement extends JsonConfig implements HasErrorActions {

	/**
	 * Constructor 
	 * 
	 * @param newObject
	 */
	public JsonConfigurationElement(Map<String, Object> newObject) {
		super(newObject);
	}
	
	/**
	 * This method returns "enabled" status for json element.
	 * 
	 * This property is useful to handle configuration into json file source without
	 * take in count during test execution.
	 * 
	 * This property is optional and when it is not present is equals to "true".
	 */
	public final Boolean getEnabled() {
		
		Boolean resp = null;
		
		resp = this.getBooleanFromPath("enabled");
		
		// omission is comparable to "true"
		if ( resp == null )
			resp = Boolean.TRUE;
		
		return resp;
	}
	
	/**
	 * This method returns "delete" status for json element.
	 * 
	 * This property is useful to handle post-test steps during execution.
	 * 
	 * This property is optional and when it is not present is equals to "false".
	 */
	public final Boolean getDelete() {
		
		Boolean resp = null;
		
		resp = this.getBooleanFromPath("delete");
		
		// omission is comparable to "false"
		if ( resp == null )
			resp = Boolean.FALSE;
		
		return resp;
	}		
	
	@Override
	public JsonErrorActions getErrorActions() throws JSONSException {
		
		JsonErrorActions resp = null;
		
		Map<String, Object> errAct = null;
		
		try {
			errAct = getJsonMapFromPath(ERROR_ACTIONS_LABEL_);
		}
		catch ( JSONSException e ) {
			
			// do nothing here
		}
		
		if ( errAct != null )
			resp = new JsonErrorActions(errAct);
		else
			throw new JSONSException(getClass().getSimpleName() + " requests an \""+ERROR_ACTIONS_LABEL_+"\" but it is missing!");
		
		return resp;
	}

}
