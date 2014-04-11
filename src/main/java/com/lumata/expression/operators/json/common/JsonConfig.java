package com.lumata.expression.operators.json.common;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;

public abstract class JsonConfig {

	private static final Logger logger = LoggerFactory.getLogger( JsonConfig.class );
	
	protected JSONObject root;
	
	public JsonConfig(String path, String jsonFile) throws JSONSException, IOFileException {
		root = JSONUtils.loadJSONResource(path, jsonFile + Format.JSON_EXTENSION);
	}
	
	public String getStringFromPath(String path) {
		
		/*String[] subpathList = path.split("/");
		
		JSONObject current = root;
		int index = 0;
		
		for (String subpath : subpathList) {
			index++;
			
			if (index == subpathList.length) {
				try {
					return current.getString(subpath);
				} catch (JSONException e) {
					return "";
				}
				
			} else {
				try {
					current = current.getJSONObject(subpath);
				} catch (JSONException e) {
					return "";
				}
			}
		}
		*/
		return (String)getObjectFromPath( path );
	}
	
	public Object getObjectFromPath( String path ) {
		
		String[] subpathList = path.split("/");
		
		JSONObject current = root;
		int index = 0;
		
		for( String subpath : subpathList) {
			
			index++;
			
			if( index == subpathList.length ) {
				try {
					return current.get(subpath);
				} catch (JSONException e) {
					return null;
				}
				
			} else {
				try {
					current = current.getJSONObject(subpath);
				} catch (JSONException e) {
					return null;
				}
			}
		}
		
		return null;
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
	
	public List<String> getStringList(String key) throws JSONException {
		List<String> list = new ArrayList<String>();
		
		JSONArray jsonArray = root.getJSONArray(key);
		
		for (int i=0; i<jsonArray.length(); i++) {
			list.add( jsonArray.getString(i) );
		}
		
		return list;
	}
	
}
