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
