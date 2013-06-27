package com.lumata.common.testing.io;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.grid.common.JSONConfigurationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONUtils extends JSONConfigurationUtils {
	
	private static final  Logger logger = LoggerFactory.getLogger( JSONUtils.class );
	
	public static JSONObject loadJSONResource( String resource ) throws JSONSException, IOFileException {
		
		JSONObject jsonResource = null;
		
		try {
			
			jsonResource = new JSONObject(IOFileUtils.loadResourceAsString( resource ));			
			
			logger.debug("The Json Object has been loaded ( " + jsonResource.toString() + " )");
			
		} catch (JSONException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		} catch (IOFileException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		}
    		
		return jsonResource;
		
	}
	
	public static JSONObject loadJSONResource( String folder, String resource ) throws JSONSException, IOFileException {
		
		JSONObject jsonResource = null;
		
		try {
			
			jsonResource = new JSONObject(IOFileUtils.loadResourceAsString( folder, resource ));			
			
			logger.debug("The Json Object has been loaded ( " + jsonResource.toString() + " )");
			
		} catch (JSONException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		} catch (IOFileException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		}
    		
		return jsonResource;
		
	}
	
	public static JSONObject loadJSONFile( String file ) throws JSONSException, IOFileException {
		
		JSONObject jsonResource = null;
		
		try {
			
			jsonResource = new JSONObject(IOFileUtils.loadFileAsString(file));			
			
			logger.debug("The Json Object has been loaded ( " + jsonResource.toString() + " )");
																	
		} catch (JSONException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		} catch (IOFileException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		}
    		
		return jsonResource;
		
	}
	
	public static JSONObject loadJSONFile( String folder, String file ) throws JSONSException, IOFileException {
		
		JSONObject jsonResource = null;
		
		try {
			
			jsonResource = new JSONObject(IOFileUtils.loadFileAsString(folder, file));			
			
			logger.debug("The Json Object has been loaded ( " + jsonResource.toString() + " )");
																	
		} catch (JSONException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		} catch (IOFileException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		}
    		
		return jsonResource;
		
	}
	
	public static void saveJSONResource( JSONObject jsonResource, String jsonFile ) throws JSONSException, IOFileException {
		
		try {
			 
			IOFileUtils.saveResource(jsonResource.toString(), jsonFile);
			
			logger.debug("The Json Object has been created ( " + jsonResource.toString() + " )");
			
		} catch (IOFileException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		}
		
	}
	
	public static void saveJSONResource( JSONObject jsonResource, String folder, String jsonFile ) throws JSONSException, IOFileException {
		
		try {
			 
			IOFileUtils.saveResource(jsonResource.toString(), folder, jsonFile);
			
			logger.debug("The Json Object has been created ( " + jsonResource.toString() + " )");
			
		} catch (IOFileException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		}
		
	}

	public static void saveJSONFile( JSONObject jsonResource, String jsonFile ) throws JSONSException, IOFileException {
		
		try {
			 
			IOFileUtils.saveFile(jsonResource.toString(), jsonFile);
			
			logger.debug("The Json Object has been created ( " + jsonResource.toString() + " )");
	 
		} catch (IOFileException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		}
		
	}
	
	public static void saveJSONFile( JSONObject jsonResource, String folder, String jsonFile ) throws JSONSException, IOFileException {
	
		try {
			 
			IOFileUtils.saveFile(jsonResource.toString(), folder, jsonFile);
			
			logger.debug("The Json Object has been created ( " + jsonResource.toString() + " )");
	 
		} catch (IOFileException e) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		
		}
		
	}	
	
}
