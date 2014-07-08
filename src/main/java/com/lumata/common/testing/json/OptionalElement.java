package com.lumata.common.testing.json;

import java.util.Map;

public abstract class OptionalElement extends JsonConfigurationElement implements IsOptional {

	/**
	 * Default value dictionary
	 */
	private Map<String,Object> defaultValue = null;
	
	public OptionalElement(Map<String, Object> newObject) {
		
		super(newObject);
	}

	@Override
	public abstract Map<String, Object> getDefaultValueMap();
	
	@Override
	public Boolean isProvided() {
		
		return this.root == null ? Boolean.FALSE : Boolean.TRUE;
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
		
		return defaultValue.get(key);
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
