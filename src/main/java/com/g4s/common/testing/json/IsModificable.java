package com.g4s.common.testing.json;

import com.g4s.common.testing.exceptions.JSONSException;

/**
 * This interface will be used to mark object that handle JSON element and modify it.
 */
public interface IsModificable {

	/**
	 *	This method performs two actions :<br>
	 *	<li> it navigates with passed string the configuration file
	 *	<li> it tries to convert found object to <b>String</b>
	 *
	 * @param jsonNavigationPath
	 *
	 * @return String conversion of found object
	 */
	public String getStringFromPath(String jsonNavigationPath);
	
	/**
	 * This method provides the facility to <b>modify</b> an existing string value into current JSON Object
	 * 
	 * If requested path to modify does NOT exist the method throws an <b>JSONSException</b>
	 * 
	 * @param jsonNavigationPath is the path where write value
	 * @param object2Insert is the data object to write
	 * 
	 * @throws JSONSException
	 */
	public void modifyStringFromPath(String jsonNavigationPath, Object object2Insert) throws JSONSException;
}
