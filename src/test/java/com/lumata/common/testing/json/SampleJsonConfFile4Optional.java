package com.lumata.common.testing.json;

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
	public class SampleJsonConfElemOptional extends JsonOptionalElement {

		/**
		 * 
		 * @param newObject
		 */
		public SampleJsonConfElemOptional(Map<String, Object> newObject) {
			
			super(newObject);
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

			Map<String,Object> h = new HashMap<String, Object>();
			
		    h.put("key1","defaultKey1");
		    h.put("key2",new Integer(999));
		    h.put("key3",Boolean.FALSE);
		    
			return h;
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
