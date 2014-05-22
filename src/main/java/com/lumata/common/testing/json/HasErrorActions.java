package com.lumata.common.testing.json;

import com.lumata.common.testing.exceptions.JSONSException;

/**
 * This interface describes the property to carry on "Error Actions" configuration.
 * It will provide facilities to track and handle error actions to be adopted during 
 * configuration procedure based on <b>JsonConfig</b> object.
 */
public interface HasErrorActions {

	/**
	 * This label contains name of error action object to handle error during elements configuration
	 */
	public static final String ERROR_ACTIONS_LABEL_ = "errorActions";
	
	/**
	 *	This enumeration describes error condition that can occur during  
	 *	elements configuration as described into JSON file.<br><br>
	 *	Typical error condition can be listed in two types :<br>
	 *	<li> element to be configured already exists
	 *	<li> an unexpected error on DUT during configuration
	 */
	public enum ElementErrorConditionType {
		
		/**
		 * A key parameter for configuration is already present in DUT
		 */
		ELEMENT_AREADY_EXISTS,
		
		/**
		 * Generic error on DUT during configuration
		 */
		GENERAL_ERROR;
	}
		
	/**
	 *	This enumeration describes "recover" action to be taken in case of error 
	 *	during configuration of an element described into JSON file.<br><br>
	 *	Typical error condition can be listed in two types :<br>
	 *	<li> element to be configured already exists
	 *	<li> an unexpected error on DUT during configuration
	 */
	public enum ElementErrorActionType { 
		
		/**
		 * This element cover eventuality that a "key" attribute of element already exists.
		 * To "recover" configuration step will be generated a new "key" attribute appending
		 * the time-stamp before use it into configuration.
		 */
		ADD_TIMESTAMP_TO_FIELD,
		
		/**
		 * The element cannot be configured as described into JSON file. <b>In this condition code
		 * returns an exception and calling method handles it (e.g. continue configuration, stop execution...).</b>
		 */
		RETURN_ERROR,
		
		/**
		 * The element cannot be configured as described into JSON file. <b>Application simulates
		 * pressing "Cancel" button on UI.</b>
		 */
		ABORT_CANCEL;
	};
	
	/**
	 * This methods returns the Json element related to "Error Actions" configuration.
	 * If current object does not contains an element labeled with <b>ERROR_ACTIONS_LABEL_</b>,
	 * this method throws a <b>JSONSException</b>.
	 * 
	 * @return a <b>JsonConfig</b> object or throws a <b>JSONSException</b> instead.
	 * 
	 * @throws JSONSException
	 */
	public JsonConfig getErrorActions() throws JSONSException;
}
