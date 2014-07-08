package com.lumata.common.testing.json;

import java.util.Map;

import com.lumata.common.testing.exceptions.JSONSException;

/**
 * Only for test
 */
public class SampleJsonConfFile4Optional extends JsonConfigurationFile {

	/**
	 * 
	 * @param path
	 * @param jsonFile
	 * @throws JSONSException
	 */
	public SampleJsonConfFile4Optional(String path, String jsonFile) throws JSONSException {
		
		super(path, jsonFile);
	}

	/**
	 * Only for test
	 */
	public class SampleJsonConfElemOptional extends OptionalElement {

		public SampleJsonConfElemOptional(Map<String, Object> newObject) {
			
			super(newObject);
		}

		@Override
		public Map<String, Object> getDefaultValueMap() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	@Override
	public String getElementsSectionLabel() {
		// TODO Auto-generated method stub
		return null;
	}
}
