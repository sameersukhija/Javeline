package com.g4s.common.testing.json;

import java.util.Collections;
import java.util.Map;

public abstract class JsonOptionalElement extends JsonConfigurationElement implements IsOptional {

	public JsonOptionalElement(Map<String, Object> newObject) {
		
		super(newObject);
		
		// init the dictionary as immutable map
		defaultValueMap = Collections.unmodifiableMap(getDefaultValueMap()); 
	}

	/**
	 * This object maps the default value mapped
	 */
	private Map <String, Object> defaultValueMap = null;	
	
	@Override
	public abstract Map<String, Object> getDefaultValueMap();
	
	@Override
	public Boolean isProvided() {
		
		return length().equals(0) ? Boolean.FALSE : Boolean.TRUE;
	}
	
	/**
	 * This method is invoked when an optional element is not provided and returns
	 * the default object for requested key.
	 * 
	 * @param key is the key requested
	 * 
	 * @return default value for key
	 */
	private Object proxyOptional(String key) {
		
		return defaultValueMap.get(key);
	}
	
	@Override
	protected Integer getIntegerFromPath(String jsonNavigationPath) {
		
		Integer resp = null;
		
		if ( isProvided() )
			resp = super.getIntegerFromPath(jsonNavigationPath);
		else
			resp = (Integer) proxyOptional(jsonNavigationPath);
		
		return resp;
	}
	
	@Override
	protected Boolean getBooleanFromPath(String jsonNavigationPath) {
		
		Boolean resp = null;
		
		if ( isProvided() )
			resp = super.getBooleanFromPath(jsonNavigationPath);
		else
			resp = (Boolean) proxyOptional(jsonNavigationPath);
		
		return resp;
	}	
	
	@Override
	protected String getStringFromPath(String jsonNavigationPath) {
		
		String resp = null;
		
		if ( isProvided() )
			resp = super.getStringFromPath(jsonNavigationPath);
		else
			resp = (String) proxyOptional(jsonNavigationPath);
		
		return resp;
	}	
}
