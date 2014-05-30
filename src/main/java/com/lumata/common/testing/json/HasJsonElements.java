package com.lumata.common.testing.json;

import java.util.List;

import com.lumata.common.testing.exceptions.JSONSException;

/**
 * This interface provides the facilities at Json configuration file. These facilities
 * are focused to handle more elements to be configured. 
 */
public interface HasJsonElements {

	/**
	 * This method returns the main section label into Json array that contains elements.
	 * 
	 * @return a String with label
	 */
	public String getElementsSectionLabel();
	
	/**
	 * This method returns the list of configuration elements contained into file.
	 * If current object does not contains sub-object, "getList" returns "null".
	 * 
	 * @return List<? extends JsonConfigurationElement> or null
	 * 
	 * @throws JSONSException
	 */
	public List<? extends JsonConfigurationElement> getList() throws JSONSException;

	/**
	 * This method sets the current element to be configured.
	 * 
	 * @param index is the index in main list of elements
	 * 
	 * @throws JSONSException
	 */
	public void setCurrentElementById(Integer index) throws JSONSException;

	/**
	 * This method returns the current element to be configured.
	 * 
	 * @return the current element or null if not selected
	 */
	public JsonConfigurationElement getCurrentElement();
}
