package com.lumata.common.testing.json;

import java.util.Map;

import com.lumata.common.testing.exceptions.JSONSException;

public abstract class ErrorModificableElement extends JsonConfigurationElement implements IsModificable {

	public ErrorModificableElement(Map<String, Object> newObject) {
		
		super(newObject);
	}

	@Override
	public String getStringFromPath(String jsonNavigationPath) {
		return super.getStringFromPath(jsonNavigationPath);
	}
	
	@Override
	public void modifyStringFromPath( String jsonNavigationPath, Object object2Insert ) throws JSONSException {
		super.modifyStringFromPath(jsonNavigationPath, object2Insert);
	}
}
