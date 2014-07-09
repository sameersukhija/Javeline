package com.lumata.common.testing.json;

import java.util.Collections;
import java.util.HashMap;
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

		/**
		 * 
		 */
		private Map <String, Object> defaultValueMap = null;
		
		/**
		 * 
		 * @param newObject
		 */
		public SampleJsonConfElemOptional(Map<String, Object> newObject) {
			
			super(newObject);
			
			@SuppressWarnings("serial")
			Map<String,Object> h = new HashMap<String, Object>(){{
		        put("key1","defaultKey1");
		        put("key2",new Integer(999));
		        put("key3",Boolean.FALSE);
		    }};
		    
		    defaultValueMap = Collections.unmodifiableMap(h); 
		}

		/**
		 * 
		 * @return
		 */
		public String getKey1() {
			
			return getStringFromPath("key1");
		}
		
		/**
		 * 
		 * @return
		 */
		public Integer getKey2() {
			
			return getIntegerFromPath("key2");
		}
		
		/**
		 * 
		 * @return
		 */
		public Boolean getKey3() {
			
			return getBooleanFromPath("key3");
		}		
		
		@Override
		public Map<String, Object> getDefaultValueMap() {

			return defaultValueMap;
		}
		
	}

	/**
	 * It returns the optional object from current element
	 * 
	 * @return
	 * @throws JSONSException 
	 */
	public SampleJsonConfElemOptional getOptionalSubElement() throws JSONSException {
		
		Map<String, Object> raw = getCurrentElement().getJsonMapFromPath("optionalSubElement", Boolean.TRUE);
		
		return new SampleJsonConfElemOptional(raw);
	}
	
	@Override
	public String getElementsSectionLabel() {

		return "testElementWithOptional";
	}
}
