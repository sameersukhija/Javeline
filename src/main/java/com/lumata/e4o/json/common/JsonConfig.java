package com.lumata.e4o.json.common;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;

public class JsonConfig {

	protected JSONObject root;

	public JsonConfig(String path, String jsonFile) throws JSONSException {
		root = JSONUtils.loadJSONResource(path, jsonFile + Format.JSON_EXTENSION);
	}
	
	public JsonConfig(JSONObject root) {
		this.root = root;
	}
	
	public Integer getIntegerFromPath(String path) {
		
		Object value = getObjectFromPath( path );
		
		return ( !value.equals( null ) ? (Integer)value : null );
	
	}
	
	public String getStringFromPath(String path) {
	
		Object value = getObjectFromPath( path );
		
		return ( !value.equals( null ) ? (String)value : null );
	
	}
	
	public Boolean getBooleanFromPath(String path) {
		
		Object value = getObjectFromPath( path );
		
		return ( !value.equals( null ) ? (Boolean)value : null );
	
	}
	
	public JSONObject getJSONObjectFromPath( String path ) throws JSONException {
		
		Object obj = getObjectFromPath( path );
		
		return ( !obj.equals( null ) ? new JSONObject( obj.toString() ) : null );
	
	}
	
	public JSONArray getJSONArrayFromPath( String path ) throws JSONException {
		
		Object obj = getObjectFromPath( path );
		
		return ( !obj.equals( null ) ? new JSONArray( obj.toString() ) : null );
	
	}
	
	public Object getObjectFromPath( String path ) {
		
		if( null != path && null != root ) {
			
			String[] subpathList = path.split("\\.");
			
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
			
		}
		
		return null;
	}
	
	public void setObjectFromPath( String path, Object value ) {
		
		try {
			
			if( this.root.has( path ) ) { this.root.put( path , value ); }
		
		} catch( JSONException e ) {}
	
	}
	
	public List<String> getStringList(String key) throws JSONException {
		List<String> list = new ArrayList<String>();
		
		JSONArray jsonArray = root.getJSONArray(key);
		
		for (int i=0; i<jsonArray.length(); i++) {
			list.add( jsonArray.getString(i) );
		}
		
		return list;
	}
	
}
