package com.lumata.common.testing.json;

import java.util.Map;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.json.JsonConfigurationFile;

/**
 * Only for test
 */
public class SampleFinalJsonConfigurationFile extends
		JsonConfigurationFile {

	
	/**
	 * Only for test
	 */
	public class SampleFinalJsonConfigurationElement extends JsonConfigurationElement {

		/**
		 * Only for test constructor
		 *  
		 * @param newObject
		 */
		public SampleFinalJsonConfigurationElement(Map<String, Object> newObject) {
			super(newObject);
		}
	}
	
	/**
	 * Only for test constructor
	 * 
	 * @param path
	 * @param jsonFile
	 * @throws JSONSException
	 */
	public SampleFinalJsonConfigurationFile( String path,String jsonFile) throws JSONSException {
		super(path, jsonFile);
	}

	@Override
	public String getElementsSectionLabel() {
		
		return "commodities";
	}
}
