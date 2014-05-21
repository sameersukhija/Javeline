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
	 *	<li> it tries to convert found object to <b>JSONObject</b>
	 *
	 * @param jsonNavigationPath
	 *
	 * @return Map<String, Object> of found object
	 *
	 * @throws JSONSException
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getJsonMapFromPath( String jsonNavigationPath ) throws JSONSException {

		Map<String, Object> resp = null;
		
		Object findObject = getObjectFromPath( jsonNavigationPath );

		try {
			
			// strange condition, but happened that sometimes org.json returns an HashMap
			// maybe for very simple json object (?)
			if ( findObject instanceof Map ) 
				resp = (Map<String, Object>)findObject;
			else
				resp = (Map<String, Object>)parser.parse(findObject.toString(), containerFactory);

			if ( resp.size() == 0 )
				resp = null;
			
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
	 *	<li> it tries to convert found object to <b>JSONArray</b>
	 *
	 * @param jsonNavigationPath
	 *
	 * @return List<Object> of found object
	 *
	 * @throws JSONSException
	 */
	@SuppressWarnings("unchecked")
	protected List<Object> getJsonListFromPath( String jsonNavigationPath ) throws JSONSException {
		
		List<Object> resp = null;
		
		Object findObject = getObjectFromPath( jsonNavigationPath );

		try {
			
			// strange condition, but happened that sometimes org.json returns an ArrayList
			// maybe for very simple json object (?)
			if ( findObject instanceof List ) 
				resp = (List<Object>)findObject;
			else
				resp = (List<Object>)parser.parse(findObject.toString(), containerFactory);
			
			if ( resp.size() == 0 )
				resp = null;
			
		} catch (ParseException | NullPointerException e) {
			
			String message = "Error during looking for \"List of object\" @ \""+jsonNavigationPath+"\"!";
			logger.error(message);
			e.printStackTrace();
			
			throw new JSONSException(message); 
		}
		
		return resp;
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
	 * 
	 * @param jsonNavigationPath
	 * @param object2Insert
	 * @throws JSONException 
	 */
	protected void setObjectFromPath( String jsonNavigationPath, Object object2Insert ) {
		
		try {
			
			if( this.root.has( jsonNavigationPath ) ) 
				this.root.put( jsonNavigationPath , object2Insert ); 
		
		} catch( JSONException e ) {
			
			// TO BE DEFINED!!!!
		}
	
	}
	
	/** TODO 
	public Object getElement( String path ) {
						
		return parseJson( this.root, path.split("/"), 0 );
						
	}
	
	public Object parseJson( Object json, String[] path, int pathIndex ) {
					
		System.out.println( pathIndex );
		
		if( pathIndex < path.length ) {
		
			if( json instanceof JSONObject ) {
								
				try {
					System.out.println( "S1: " + path[ pathIndex ] );
					System.out.println( json );
					json = ((JSONObject)json).get( path[ pathIndex ] );
					System.out.println( json );
					parseJson( json, path, pathIndex + 1 );
									
				} catch( JSONException e ) {
					logger.error( e.getMessage(), e );
					return null;
				}
				
			} else {
				
				if( json instanceof JSONArray ) {
					System.out.println( "PIPPO: " + json );
					JSONArray currentArray = (JSONArray)json;
					for( int j = 0; j < currentArray.length(); j++ ) {
									
						try {
							System.out.println( "S2: " + path[ pathIndex ] );
							System.out.println( json );
							json = ((JSONArray)json).get( j );
							System.out.println( json );
							parseJson( json, path, pathIndex );
							
						} catch( JSONException e ) {
							logger.error( e.getMessage(), e );
							return null;
						}
					
					}
				
				}
				
			}
			
		}
		
		System.out.println( "FINISH: " + json );
		
		return json;
					
	}
	*/
	
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
	protected List<String> getStringList(String jsonArrayName) throws JSONSException {
		
		List<String> list = new ArrayList<String>();
		
		JSONArray jsonArray = null;
		
		try {
			jsonArray = root.getJSONArray(jsonArrayName);
			
			for (int i=0; i<jsonArray.length(); i++) 
				list.add( jsonArray.getString(i) );
			
		} catch (JSONException | NullPointerException e) {
			
			String message = "Error during looking of \"Array of value\" for \""+jsonArrayName+"\"!";
			logger.error(message);
			e.printStackTrace();
			
			throw new JSONSException(message); 
		}

		return list;
	}
	
}
