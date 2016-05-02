package com.g4s.common.testing.json;

import java.util.Map;

/**
 * The json configuration element that implements this interface are optional
 * inside the configuration element that contains them.
 * 
 * The optional sub-object to be detected could be marked with "null" value or the
 * associated "key" cannot be present and test execution must continue without error
 * related to missing configuration.
 * 
 * The major feature of optional element is to return "default" value that can be
 * decided for class (e.g. when optional element "sub-element" is not defined as 
 * value of "key1" returns "null" and as value of "key2" returns "100" )
 */
public interface IsOptional {

	/**
	 * This method returns a map that contains the default values map to use @ initialization
	 * 
	 * @return a map with key associated to default value
	 */
	public Map<String,Object> getDefaultValueMap();
	
	/**
	 * This method describes if current object behavior is because provided by
	 * json information or not ( so default value ).
	 * 
	 * @return TRUE if provided by json file, FALSE otherwise.
	 */
	public Boolean isProvided();
}
