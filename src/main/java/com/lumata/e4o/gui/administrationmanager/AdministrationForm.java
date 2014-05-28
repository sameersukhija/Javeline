package com.lumata.e4o.gui.administrationmanager;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

public class AdministrationForm extends Form {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(AdministrationForm.class);
	
	public AdministrationForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	protected Form open() throws FormException {
		
		return clickId( "gwt-debug-BarCaptionHomeAdministration" );
		
	}
	
	/**
	 * This method returns the presence of web component in error condition.
	 * The error condition is at application level for the actual form.
	 * (e.g. duplication of value, missing field) 
	 * 
	 * @return true if at least one visible element is in error.
	 * 
	 * @throws FormException
	 */
	protected Boolean containsErrorElement() throws FormException {
		
		Integer numbers = null;
		Boolean resp = Boolean.TRUE;

		// error condition
		//*[contains(@class,'errorBackground')]
		
		List<WebElement> elements = getErrorElement();

		if ( elements == null )
			numbers = 0;
		else
			numbers = elements.size();
		
		if ( numbers != 0 )
			resp = true;
		else
			resp = false;
		
		return resp;
	}	
	
	/**
	 * This method returns a list with <b>WebElement</b> in error condition.
	 * The error condition is at application level for the actual form.
	 * (e.g. duplication of value, missing field) 
	 * 
	 * @param errorXPathRule is the XPath rule used to looking error condition
	 * 
	 * @return a <b>List</b> of element in error condition, empty if no visible elements are in error.
	 * 
	 * @throws FormException
	 */
	protected List<WebElement> getErrorElement(String errorXPathRule) throws FormException {
		
		List<WebElement> resp = null;

		// error condition
		//*[contains(@class,'errorBackground')]
		//div[text()='An error occured']
		
		try {

			logger.debug("Looking for more visible form.");
			Integer maxZed = 0;
			
			List<WebElement> visibleDiv = selenium.getWrappedDriver().findElements(By.xpath("//div[not(contains(@style,'none'))]/parent::div[contains(@style,'z-index')]"));
			
			for( WebElement ele:visibleDiv ) {
				
				String zed = ele.getCssValue("z-index");	
				
				Integer currZed = null;
				
				try {
					currZed = Integer.parseInt(zed);
				} catch ( NumberFormatException e ) {
					currZed = 0;
				}
				
				if ( currZed > maxZed ) {
					maxZed = currZed;
					lastWebElement = ele;
				}
			}
			
			logger.debug("Max \"z-index\" is -> " + maxZed);
			
			searchByXPath("//*[contains(@style,'"+maxZed+"')]");
			
			resp = SeleniumUtils.findListForComponentDisplayed(	selenium, 
																SearchBy.XPATH, 
																lastWebElement, 
																errorXPathRule);
			
		} catch ( StaleElementReferenceException | NoSuchElementException e ) {
			
			resp = new ArrayList<WebElement>();
		}		

		return resp;
	}
	
	/**
	 * This method returns a list with <b>WebElement</b> in error condition.
	 * The error condition is at application level for the actual form.
	 * (e.g. duplication of value, missing field) 
	 * 
	 * This method is for generic error on the data input value.
	 * 
	 * @return a <b>List</b> of element in error condition, empty if no visible elements are in error.
	 * 
	 * @throws FormException
	 */
	protected List<WebElement> getErrorElement() throws FormException {
		
		return getErrorElement("//*[contains(@class,'errorBackground')]");
	}
	
	/**
	 * This method handles the popup warning displayed through Javascript.
	 * 
	 * The popup is the ones with Accept/Dismiss possible choice.
	 * 
	 * If input Boolean is TRUE will be pressed "Accept" condition otherwise "Dismiss". 
	 * 
	 * The returns Boolean describe if popup was displayed and pressed.
	 */
	protected Boolean handleJavascriptAlertAcceptDismiss(Boolean accept) {
		
		Alert popupAlert = null;
		Boolean pressed = null;
		
		try {
			
			popupAlert = selenium.selectAlert();
		    	
			if ( popupAlert != null ) { 
				
				if ( accept )
					popupAlert.accept();
				else 
					popupAlert.dismiss();
				
				pressed = Boolean.TRUE; 
			}
			
		} catch (NoAlertPresentException e) {
			
			// nothing to do
			pressed = Boolean.FALSE;
		}
		
		return pressed;
	}	
}
