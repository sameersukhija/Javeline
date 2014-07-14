package com.lumata.common.testing.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;

/**
 * This class provides a series of facilities to handle Json configuration elements. 
 */
public abstract class JsonConfig {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger( JsonConfig.class );

	/**
	 * The path separator is dot (.) and this is the internal rule adopted for splitting.
	 */
	private static final String SPLIT_RULE_ = "\\.";
	
	/**
	 * This is the default separator for JSON element addressing
	 */
	public static final String NAVIGATION_SEPARATOR = ".";
	
	/**
	 * Internal instance of <b>JSONObject</b> to handle
	 */
	protected JSONObject root;
	
	/**
	 * This is the internal parser for fast
	 */
	private static JSONParser parser = new JSONParser();

	/**
	 * This element describes the mapping during JSON parsing :<br>
	 * <li> <b>JSONArray</b> will be mapped with <b>ArrayList</b>
	 * <li> <b>JSONObject</b> will be mapped with <b>HashMap</b>
	 */
	private static ContainerFactory containerFactory = new ContainerFactory() {

		public List<Object> creatArrayContainer() {
			return new ArrayList<Object>();
		}

		public Map<String,Object> createObjectContainer() {
			return new HashMap<String,Object>();
		}
	};

	/**
	 * Construct object with path and name of configuration file
	 * 
	 * @param path
	 * @param jsonFile
	 * @throws JSONSException
	 */
	public JsonConfig(String path, String jsonFile) throws JSONSException {
		
		logger.debug("Init " + JsonConfig.class.getSimpleName() + " from file.");
		logger.debug("Path file -> " + path);
		logger.debug("File name -> " + jsonFile);
		
		root = JSONUtils.loadJSONResource(path, jsonFile + Format.JSON_EXTENSION);
	}
	
	/**
	 * Construct object with JSONObject instance
	 * 
	 * @param newObject
	 */
	public JsonConfig(Map<String, Object> newObject) {

		logger.debug("Init " + JsonConfig.class.getSimpleName() + " from map.");
		logger.debug("Passed object -> " + newObject);
		
		this.root = new JSONObject(newObject);
	}
	
	/**
	 *	This method return the json size :<br>
	 *	
	 * @return Integer json size
	 */
	protected Integer length() {
		
		return this.root.length();
	
	}
	
	/**
	 *	This method performs two actions :<br>
	 *	<li> it navigates with passed string the configuration file
	 *	<li> it tries to convert found object to <b>Integer</b>
	 *
	 * @param jsonNavigationPath
	 *
	 * @return Integer conversion of found object
	 */
	protected Integer getIntegerFromPath(String jsonNavigationPath) {
		
		Integer resp = null;
		
		Object value = getObjectFromPath( jsonNavigationPath );
		
		if ( value != null )
			resp = Integer.parseInt(value.toString());
		
		return resp;
	}
	
	/**
	 *	This method performs two actions :<br>
	 *	<li> it navigates with passed string the configuration file
	 *	<li> it tries to convert found object to <b>String</b>
	 *
	 * @param jsonNavigationPath
	 *
	 * @return String conversion of found object
	 */
	protected String getStringFromPath(String jsonNavigationPath) {
	
		String resp = null;
		
		Object value = getObjectFromPath( jsonNavigationPath );

		if ( value != null )
			resp = (String)value;
		
		return resp;
	}
	
	/**
	 *	This method performs two actions :<br>
	 *	<li> it navigates with passed string the configuration file
	 *	<li> it tries to convert found object to <b>Boolean</b>
	 *
	 * @param jsonNavigationPath
	 *
	 * @return Boolean conversion of found object
	 */
	protected Boolean getBooleanFromPath(String jsonNavigationPath) {

		Boolean resp = null;
		
		Object value = getObjectFromPath( jsonNavigationPath );
		
		if ( value == null || value == "" )
			resp = null;
		else if (	"true".equalsIgnoreCase(value.toString()) || 
					"false".equalsIgnoreCase(value.toString())
				) 
			resp = Boolean.parseBoolean(value.toString());
		else {
			  // something is present but it is NOT castable to Boolean
			throw new ClassCastException("The value is NOT a Boolean!");
		}
		
		return resp;
	}
	
	/**
	 *	This method performs two actions :<br>
	 *	<li> it navigates with passed string the configuration file
	 *	<li> it tries to convert found object to <b>Map<String, Object></b>
	 *  <li> it returns "null" if element is not provided and it is optional
	 *
	 * @param jsonNavigationPath is the search path
	 * @param isOptional describe if looked element can be null into json
	 *
	 * @return Map<String, Object> of found object or null
	 *
	 * @throws JSONSException
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getJsonMapFromPath( String jsonNavigationPath, Boolean isOptional ) throws JSONSException {

		Map<String, Object> resp = null;
		
		logger.debug("Looking for " + jsonNavigationPath);
		logger.debug("This element is " + (isOptional ? "optional" : "mandatory"));
		
		Object findObject = getObjectFromPath( jsonNavigationPath );

		try {
			
			// element not found
			if ( findObject == null ) {

				logger.debug("Element is not present into json.");
				
				if ( !isOptional ) {
					
					logger.error("The missing of mandatory element is an error!");
					throw new NullPointerException();
				}
				else
					logger.warn("Optional element not provided, go forward \"null\".");
			}
			// element found
			else {
				
				// strange condition, but happened that sometimes org.json returns an HashMap
				// maybe for very simple json object (?)
				if ( findObject instanceof Map ) 
					resp = (Map<String, Object>)findObject;
				else
					resp = (Map<String, Object>)parser.parse(findObject.toString(), containerFactory);

				if ( resp.size() == 0 )
					resp = null;
			}
						
		} catch (ParseException | NullPointerException e) {
			
			String message = "Error during looking for \"Map of object\" @ \""+jsonNavigationPath+"\"!";
			logger.error(message);
			e.printStackTrace();
			
			throw new JSONSException(message); 
		}
		
		return resp;
	}
	
	/**
	 *	This method performs two actions :<br>
	 *	<li> it navigates with passed string the configuration file
	 *	<li> it tries to convert found object to <b>Map<String, Object></b>
	 *  
	 * @param jsonNavigationPath is the search path
	 * 
	 * @return Map<String, Object> of found object 
	 * 
	 * @throws JSONSException
	 */
	protected Map<String, Object> getJsonMapFromPath( String jsonNavigationPath) throws JSONSException {
		
		return this.getJsonMapFromPath(jsonNavigationPath, Boolean.FALSE);
	}
	
	/**
	 *	This method performs two actions :<br>
	 *	<li> it navigates with passed string the configuration file
	 *	<li> it tries to convert found object to <b>List<Object></b>
	 *	<li> it returns "null" if element is not provided and it is optional
	 *
	 * @param jsonNavigationPath
	 *
	 * @return List<Object> of found object
	 * @param isOptional describe if looked element can be null into json 
	 *
	 * @throws JSONSException
	 */
	@SuppressWarnings("unchecked")
	protected List<Object> getJsonListFromPath( String jsonNavigationPath, Boolean isOptional) throws JSONSException {

		List<Object> resp = null;
		
		logger.debug("Looking for " + jsonNavigationPath);
		logger.debug("This element is " + (isOptional ? "optional" : "mandatory"));
		
		Object findObject = getObjectFromPath( jsonNavigationPath );

		try {
			
			// element not found
			if ( findObject == null ) {

				logger.debug("Element is not present into json.");
				
				if ( !isOptional ) {
					
					logger.error("The missing of mandatory element is an error!");
					throw new NullPointerException();
				}
				else
					logger.warn("Optional element not provided, go forward \"null\".");
			}
			// element found
			else {
				
				// strange condition, but happened that sometimes org.json returns an ArrayList
				// maybe for very simple json object (?)
				if ( findObject instanceof List ) 
					resp = (List<Object>)findObject;
				else
					resp = (List<Object>)parser.parse(findObject.toString(), containerFactory);
				
				if ( resp.size() == 0 )
					resp = null;
			}			

		} catch (ParseException | NullPointerException e) {
			
			String message = "Error during looking for \"List of object\" @ \""+jsonNavigationPath+"\"!";
			logger.error(message);
			e.printStackTrace();
			
			throw new JSONSException(message); 
		}
		
		return resp;
	}

	/**
	 *	This method performs two actions :<br>
	 *	<li> it navigates with passed string the configuration file
	 *	<li> it tries to convert found object to <b>List<Object></b>
	 *  
	 * @param jsonNavigationPath is the search path
	 * 
	 * @return List<Object> of found object 
	 * 
	 * @throws JSONSException
	 */	
	protected List<Object> getJsonListFromPath( String jsonNavigationPath) throws JSONSException {
		
		return this.getJsonListFromPath(jsonNavigationPath, Boolean.FALSE);
	}	
	
	/**
	 *	This method navigates the JSON file and returns the addressed element.<br>
	 *	This method can returns both wrapper classes (e.g. Integer, String, Boolean) or custom object that maps sub-tree of JSON file (e.g. JSONObject, JSONArray).<br><br>
	 *
	 *	The navigation path adopts the "dotted" notation (e.g. firstObject.secondObject.finalObject).<br>
	 *	This method does not address a specific index-element from array but can return entire JSONArray.
	 *
	 * @param jsonNavigationPath
	 *
	 * @return an <b>Object</b>
	 */
	protected Object getObjectFromPath(String jsonNavigationPath) {

		Object resp = null;

		if (null != jsonNavigationPath && null != root) {

			// the "dot" operator return "root"
			if (jsonNavigationPath.equals(NAVIGATION_SEPARATOR))
				return root;

			String[] subpathList = jsonNavigationPath.split(SPLIT_RULE_);

			JSONObject current = root;

			int index = 0;

			for (String subpath : subpathList) {

				index++;

				if (index == subpathList.length) { // last segment simple query

					try {

						resp = current.get(subpath);

					} catch (JSONException e) {

						logger.error("The \"value\" associated to \"key\" \""
								+ subpath + "\" does NOT exists!");
					}

				} else { // normal object query

					try {

						current = current.getJSONObject(subpath);

					} catch (JSONException e) {

						logger.error("The \"object\" associated to \"key\" \""
								+ subpath + "\" does NOT exists!");

						resp = null;
						
						// exit from loop
						break;
					}

				} // end else

			} // end loop on sub-paths

			// latest step fills "resp"
		}

		return resp;
	}
	
	/**
	 * This method provides the facility to <b>modify</b> a value associated to an existing key into current object
	 * 
	 * If requested path to modify does NOT exist the method throws an <b>JSONSException</b>
	 * 
	 * @param jsonNavigationPath is the path where write value
	 * @param object2Insert is the data object to write
	 * 
	 * @throws JSONSException
	 */
	protected void modifyStringFromPath( String jsonNavigationPath, Object object2Insert ) throws JSONSException {
		
		try {
			
			if( this.root.has( jsonNavigationPath ) ) 
				this.root.put( jsonNavigationPath , object2Insert );
			else
				throw new JSONSException("Cannot modify the \"key\" \"" + jsonNavigationPath + "\", it does NOT exists!");
		
		} catch( JSONException e ) {
			
			logger.error("The \"value\" associated to \"key\" \""
					+ jsonNavigationPath + "\" provides error during modify!");
			
			e.printStackTrace();
		}
	}

	/**
	 * This method performs two actions :<br>
	 * <li> looking for an array contained into current JSON element with requested key
	 * <li> returns a list of contained element as "String" into found array
	 * 
	 * @param jsonArrayName
	 * 
	 * @return a list of String
	 * 
	 * @throws JSONSException
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getStringList(String jsonArrayName) throws JSONSException {
		
		List<String> list = null;
		String errorMessage = null;
		
		try {
			Object raw = root.get(jsonArrayName);
		
			// I am waiting a List...
			if ( raw instanceof List )
				list = (List<String>) raw;
			// ... or a JSONArray object
			else if ( raw instanceof JSONArray ) {
				JSONArray jsonArray = (JSONArray) raw;
				list = new ArrayList<String>();
				
				for (int i=0; i<jsonArray.length(); i++) 
					list.add( jsonArray.getString(i) );
			}
			else {
				errorMessage = "Error during looking of \"Array of value\" with path \""+jsonArrayName+"\" : it's a " + raw.getClass().getSimpleName() + "!";
				
				throw new JSONException(errorMessage);
			}
				
			
		} catch (JSONException | NullPointerException e) {
			
			errorMessage = errorMessage != null ? errorMessage : "Error during looking of \"Array of value\" for \""+jsonArrayName+"\"!";
			logger.error(errorMessage);
			e.printStackTrace();
			
			throw new JSONSException(errorMessage); 
		}

		return list;
	}
	
}
