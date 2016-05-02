package com.g4s.common.testing.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.g4s.common.testing.exceptions.JSONSException;

/*
 *	This is the Json configuration file that contains an array of Json element to be
 *	configured into DUT.
 */
public abstract class JsonConfigurationFile extends JsonConfig implements HasJsonElements {

	/*
	 * This object describes "Current Element" on configuration element.
	 * It is a final class to wrap functionality to be exported to current element
	 */
	public final class JsonCurrentElement extends ErrorModificableElement {

		public JsonCurrentElement(Map<String, Object> newObject) {
			super(newObject);
		}
		
		public Boolean getBooleanFromPath(String jsonNavigationPath) {
			return super.getBooleanFromPath(jsonNavigationPath);
		}

		public Integer getIntegerFromPath(String jsonNavigationPath) {
			return super.getIntegerFromPath(jsonNavigationPath);
		}
				
		public List<Object> getJsonListFromPath( String jsonNavigationPath ) throws JSONSException {
			return super.getJsonListFromPath(jsonNavigationPath);
		}
		
		public Map<String, Object> getJsonMapFromPath( String jsonNavigationPath ) throws JSONSException {
			return super.getJsonMapFromPath(jsonNavigationPath);
		}
	}
	
	/*
	 * 
	 */
	protected static final Logger logger = LoggerFactory.getLogger(JsonConfigurationFile.class);

	/*
	 * This is the current instance to be configured
	 */
	private JsonCurrentElement currentInstance2Configure = null;
	
	/*
	 * JsonConfigurationElement listed into file
	 */
	protected List<JsonCurrentElement> listedElements = null;
	
	@SuppressWarnings("unchecked")
	public JsonConfigurationFile(String path, String jsonFile)  throws JSONSException {
		super(path, jsonFile);
		
		// init list of elements
		listedElements = new ArrayList<JsonCurrentElement>();
		
		List<Object> tmp = getJsonListFromPath(getElementsSectionLabel());
		
		if ( tmp != null )
			for (Object object : tmp) 
				listedElements.add(new JsonCurrentElement((Map<String, Object>) object));
	}
	
	@Override
	public abstract String getElementsSectionLabel();

	@Override
	public List<? extends JsonConfigurationElement> getList() throws JSONSException {

		return listedElements;
	}

	@Override
	public JsonCurrentElement getCurrentElementById(Integer index) throws JSONSException {

		setCurrentElementById(index);
		
		return currentInstance2Configure;
		
	}
	
	@Override
	public void setCurrentElementById(Integer index) throws JSONSException {

		currentInstance2Configure = (JsonCurrentElement) getList().get(index);
	}

	@Override
	public JsonCurrentElement getCurrentElement() {
		
		JsonCurrentElement resp = null;
		
		if ( currentInstance2Configure != null )
			resp = currentInstance2Configure;
		else
			logger.error("First element not yet selected!");
		
		return resp;
	}

}
