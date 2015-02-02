package com.lumata.common.testing.json.element;

import java.util.List;

import org.json.JSONObject;

import com.jayway.restassured.path.json.JsonPath;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public abstract class JsonElement {
	
	private final String JSON_DEFAULT_ROOT_ = "$";
	
	private JsonPath jsonPath;
		
	protected String jsonRoot;
	
	private Integer currentIndex;
	
		
	protected JsonElement( String jsonFolder, String jsonFile ) throws JSONSException {
		
		this( JSONUtils.loadJSONResource( jsonFolder, jsonFile + Format.JSON_EXTENSION ) );
		
	}	
	
	protected JsonElement( JSONObject json ) throws JSONSException {
		
		this.jsonPath = new JsonPath( json.toString() );
		
		this.jsonPath.setRoot( JSON_DEFAULT_ROOT_ );
			
	}	

	protected void setJsonRoot( String jsonRoot ) {
		
		this.jsonRoot = jsonRoot;
		
		jsonPath.setRoot( this.jsonRoot );
		
	}

	public Integer size() {
		
		Integer size = jsonPath.get( "size" );
		
		return ( null != size ? size : 0 );
	}
	
	public Boolean isEnabled() {
		return jsonPath.getBoolean( "enabled" );
	}
	
	public Boolean isToDelete() {
		return jsonPath.getBoolean( "delete" );
	}
	
	public void cursor( Integer currentIndex ) {
		
		this.currentIndex = currentIndex;
		
		jsonPath.setRoot( this.jsonRoot + "[" + this.currentIndex + "]" );
		
	} 

	protected Boolean isNull( String path ) {
		return ( null == jsonPath.get( path ) );
	}
	
	protected Boolean isNotNull( String path ) {
		return !isNull( path );
	}
	
	protected Boolean getBoolean( String path ) {				
		return jsonPath.getBoolean( path );
	}
	
	protected String getString( String path ) {				
		return jsonPath.getString( path );
	}

	protected JSONObject getJSONObject( String path ) {				
		return new JSONObject( jsonPath.get( path ) );
	}
	
	protected List<JsonElement> getJsonElements( String path ) {				
		return jsonPath.getList( path );		
	}
	
	protected List<String> getListOfString( String path ) {				
		return jsonPath.getList( path );
	}
	
}
