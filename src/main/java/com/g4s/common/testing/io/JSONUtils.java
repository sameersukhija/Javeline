package com.g4s.common.testing.io;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.grid.common.JSONConfigurationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.g4s.common.testing.exceptions.IOFileException;
import com.g4s.common.testing.exceptions.JSONSException;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONUtils extends JSONConfigurationUtils {
	
	private static final  Logger logger = LoggerFactory.getLogger( JSONUtils.class );
	
	public static JSONObject loadJSONResource( String resource ) throws JSONSException {
		
		JSONObject jsonResource = null;
		
		try {
			
			jsonResource = new JSONObject(IOFileUtils.loadResourceAsString( resource ).replaceAll( "\n", ""));			
			
			logger.debug("The Json Object has been loaded ( " + jsonResource.toString() + " )");
			
		} catch (JSONException | IOFileException e) {
			
			logger.error( e.getMessage(), e );
			
			throw new JSONSException(e.getMessage(), e);
		
		} 
    		
		return jsonResource;
		
	}
	
	public static JSONObject loadJSONResource( String folder, String resource ) throws JSONSException {
		
		JSONObject jsonResource = null;
		
		try {
			
			jsonResource = new JSONObject(IOFileUtils.loadResourceAsString( folder, resource ).replaceAll( "\n", ""));			
			
			logger.debug("The Json Object has been loaded ( " + jsonResource.toString() + " )");
			
		} catch (JSONException | IOFileException e) {
			
			logger.error( e.getMessage(), e );
		
			throw new JSONSException(e.getMessage(), e);
		
		} 
		
		return jsonResource;
		
	}
	
	public static JSONObject loadJSONResourceFromXML( String resource ) throws JSONException, JSONSException {
		
		JSONObject jsonResource = null;
		
		try {
			
			jsonResource = XML.toJSONObject( IOFileUtils.loadResourceAsString( resource ) );			
				
			logger.debug("The Json Object has been loaded ( " + jsonResource.toString() + " )");
		
		} catch ( JSONException | IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new JSONSException(e.getMessage(), e);
		
		} 
		
		return jsonResource;
		
	}
	
	public static JSONObject loadJSONResourceFromXML( String folder, String resource ) throws JSONSException {
		
		JSONObject jsonResource = null;
		
		try {
		
			jsonResource = XML.toJSONObject( IOFileUtils.loadResourceAsString( folder, resource ) );				
		
			logger.debug("The Json Object has been loaded ( " + jsonResource.toString() + " )");
   		
		} catch ( JSONException | IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new JSONSException(e.getMessage(), e);
		
		} 	
			
		return jsonResource;
		
	}
	
	public static JSONObject loadJSONFile( String file ) throws JSONSException {
		
		JSONObject jsonResource = null;
		
		try {
			
			jsonResource = new JSONObject(IOFileUtils.loadFileAsString(file).replaceAll( "\n", ""));			
			
			logger.debug("The Json Object has been loaded ( " + jsonResource.toString() + " )");
																	
		} catch (JSONException | IOFileException e) {
			
			logger.error( e.getMessage(), e );
			
			throw new JSONSException(e.getMessage(), e);
		
		} 
		
		return jsonResource;
		
	}
	
	public static JSONObject loadJSONFile( String folder, String file ) throws JSONSException {
		
		JSONObject jsonResource = null;
		
		try {
			
			jsonResource = new JSONObject(IOFileUtils.loadFileAsString(folder, file).replaceAll( "\n", ""));			
			
			logger.debug("The Json Object has been loaded ( " + jsonResource.toString() + " )");
																	
		} catch (JSONException | IOFileException e) {
			
			logger.error( e.getMessage(), e );
			
			throw new JSONSException(e.getMessage(), e);
		
		}
    		
		return jsonResource;
		
	}
	
	public static void saveJSONResource( JSONObject jsonResource, String jsonFile ) throws JSONSException {
		
		try {
			 
			IOFileUtils.saveResource(jsonResource.toString(), jsonFile);
			
			logger.debug("The Json Object has been created ( " + jsonResource.toString() + " )");
			
		} catch (IOFileException e) {
			
			logger.error( e.getMessage(), e );
			
			throw new JSONSException(e.getMessage(), e);
		
		}
		
	}
	
	public static void saveJSONResource( JSONObject jsonResource, String folder, String jsonFile ) throws JSONSException {
		
		try {
			 
			IOFileUtils.saveResource(jsonResource.toString(), folder, jsonFile);
			
			logger.debug("The Json Object has been created ( " + jsonResource.toString() + " )");
			
		} catch (IOFileException e) {
			
			logger.error( e.getMessage(), e );
			
			throw new JSONSException(e.getMessage(), e);
		
		}
		
	}

	public static void saveJSONFile( JSONObject jsonResource, String jsonFile ) throws JSONSException {
		
		try {
			 
			IOFileUtils.saveFile(jsonResource.toString(), jsonFile);
			
			logger.debug("The Json Object has been created ( " + jsonResource.toString() + " )");
	 
		} catch (IOFileException e) {
			
			logger.error( e.getMessage(), e );
			
			throw new JSONSException(e.getMessage(), e);
		
		}
		
	}
	
	public static void saveJSONFile( JSONObject jsonResource, String folder, String jsonFile ) throws JSONSException {
	
		try {
			 
			IOFileUtils.saveFile(jsonResource.toString(), folder, jsonFile);
			
			logger.debug("The Json Object has been created ( " + jsonResource.toString() + " )");
	 
		} catch ( IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new JSONSException(e.getMessage(), e);
		
		}
		
	}	
	
}
