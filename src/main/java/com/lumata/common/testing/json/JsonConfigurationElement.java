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
	 * This object describes "Error Actions" on configuration element
	 */
	public final class JsonErrorActions extends JsonConfig {

		/**
		 * Constructor
		 * 
		 * @param newObject
		 */
		public JsonErrorActions(Map<String, Object> newObject) {
			super(newObject);
		}

		/**
		 * This method looks into error actions object for a condition and returns associated action type
		 * 
		 * @param condition is a ElementErrorConditionType element
		 * 
		 * @return ElementErrorActionType
		 * 
		 * @throws JSONSException
		 */
		public ElementErrorActionType getAction( ElementErrorConditionType condition) throws JSONSException {
			
			ElementErrorActionType resp = null;
			
			String raw = this.getStringFromPath(condition.toString());	
			
			if ( raw != null )
				resp = ElementErrorActionType.valueOf(raw);
			else
				throw new JSONSException(getClass().getSimpleName() + " requests condition \""+condition+"\" but it is missing!");
			
			return resp;
		}		
	}
	
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
