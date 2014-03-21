package com.lumata.expression.operators.json.common;

import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;

public abstract class JsonConfig {

	protected JSONObject root;
	
	public JsonConfig(String path, String jsonFile) throws JSONSException, IOFileException {
		root = JSONUtils.loadJSONResource(path, jsonFile + Format.JSON_EXTENSION);
	}
	
	public String getStringFromPath(String path) {
		String[] subpathList = path.split("/");
		
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
		
		return "";
	}
}
