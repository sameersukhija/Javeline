package com.lumata.e4o.gui.common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.exceptions.FormException;

/**
 * This class provides facilities to handle error condition that occurs into UI Form during server side validation on "saving" event.<br>
 * 
 * This object will be extended for every "save" interaction to provide form specific behavior. The behavior provided are :<br>
 * <li> <b>containsErrorElement</b>, it returns true/false according web application status
 * <li> <b>getSaveWebElement</b>, it returns the WebElement to be clicked on "Save"
 * <li> <b>defineErrorCondition</b>, it returns the error condition for application accordin input configuration
 * <li> <b>cancelAction</b>, it performs the Abort/Cancel action
 * <li> <b>addTimestampAction()</b>, it perform the "Add Timestamp" to field and re-perform "Save" event
 *  
 * With the objects that extend this one will be encapsulated the specific behavior
 * to handle form configuration handling.
 */
public abstract class FormSaveConfigurationHandler {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(FormSaveConfigurationHandler.class);
	
	/**
	 * The Selenium driver that carry the status of under test application
	 */
	private EventFiringWebDriver eventFiringDriver = null;
	
	/**
	 * The <b>JsonCurrentElement</b> related to "Save" event.
	 * Thought it is possible to fetch <b>JsonErrorActions</b> to handle "error" condition.
	 */
	private JsonCurrentElement currentElement = null;
	
	/**
	 * This object described if in latest monitored "Save" event is showed a "Confirmation" Popup and accpet it.
	 */
	private Boolean acceptedConfirmationPopup = Boolean.FALSE;
	
	/**
	 * If "Save" event produce exception is handled externally.
	 */
	private Exception resultingException = null;
	
	/**
	 * Constructor
	 * 
	 * @param inDriver
	 * @param inErrorActions
	 */
	protected FormSaveConfigurationHandler( WebDriver inDriver, JsonCurrentElement inCurrentElement ) {
		
		logger.debug("Init " + getClass().getSimpleName());
		
		currentElement = inCurrentElement;
		
		eventFiringDriver = new EventFiringWebDriver(inDriver);
	}
	
	/**
	 * It returns the WebDriver instance to be used
	 * 
	 * @return a <b>WebDriver</b> instance
	 */
	protected WebDriver getWebDriver() {
		
		return eventFiringDriver;
	}
	
	/**
	 * The <b>JsonCurrentElement</b> used to handle "Save" event
	 * 
	 * @return a <b>JsonCurrentElement</b> object
	 */
	protected JsonCurrentElement getCurrentElement() {
		
		return currentElement;
	}
	
	/**
	 * This is a WebDriverEventListener to intercept under test application status in "Save" event time slot
	 */
	private class SaveTimeSlotHandler extends AbstractWebDriverEventListener {

		@Override
		public void afterClickOn(WebElement arg0, WebDriver arg1) {
			
			if ( arg0.equals(getSaveWebElement()) ) {
				
				// check "Confirmation" popup after "Save" event
				acceptedConfirmationPopup = handleJavascriptAlertAcceptDismiss(true); 
				
				if ( acceptedConfirmationPopup ) 
					logger.debug( getClass().getSimpleName() + " enconters \"confirmation\" popup.");
				else
					logger.debug( getClass().getSimpleName() + " does NOT enconter \"confirmation\" popup.");

				logger.info("The \"Save Click\" occurs : afterClickOn with "+getClass().getSimpleName()+".");
				
				// in case no confirmation was executed, check element in error
				
				// if "Confirmation" popup is displayed and accepted, no error check is necessary
				// this block handles the investigation on error action to take
				if ( !acceptedConfirmationPopup && containsErrorElement() ) {
					
					logger.warn(getClass().getSimpleName() + "  after \"save\" event the panel seems in error!");
					
					/**
					 * Error condition detection
					 */
					ElementErrorConditionType condition = defineErrorCondition();
					
					try {
						ElementErrorActionType action = currentElement.getErrorActions().getAction(condition);
						
						// abort insertion
						if ( action.equals(ElementErrorActionType.ABORT_CANCEL) ) 
							cancelAction();
						
						// stop execution and return error
						else if ( action.equals(ElementErrorActionType.RETURN_ERROR) )
							throw new FormException(getClass().getSimpleName() + " cannot configure \""+currentElement.getStringFromPath("name")+"\"!");
						
						// add time stamp to configured field(s)
						else if ( action.equals(ElementErrorActionType.ADD_TIMESTAMP_TO_FIELD) )
							addTimestampAction();
						
					} catch (JSONSException | FormException e) {

						e.printStackTrace();
						
						resultingException = e;
					}

				}				

			}
			else
				logger.debug("The \"Save Click\" does NOT occur.");
		}

		@Override
		public void beforeClickOn(WebElement arg0, WebDriver arg1) {
			
			if ( arg0.equals(getSaveWebElement()) ) {
				
				// reset this status before "Save" event
				acceptedConfirmationPopup = Boolean.FALSE;
				
				// reset exception buffer
				resultingException = null;
				
				logger.info("The \"Save Click\" occurs : beforeClickOn with "+getClass().getSimpleName()+".");
				
				if ( containsErrorElement() )
					logger.error(getClass().getSimpleName() + " without performs \"save\" event the panel seems in error!");
			}
			else
				logger.debug("The \"Save Click\" does NOT occur.");
		}
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
		
			popupAlert = eventFiringDriver.switchTo().alert();
		    	
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
	
	/**
	 * This method performs the "Save" event on event armed driver.<br>
	 * 
	 * The armed driver follows the error events handling according application status and
	 * configuration passed during init phase.
	 * 
	 * @throws FormException 
	 */
	public void saveAction() throws FormException {
	
		SaveTimeSlotHandler oneTimeUse = new SaveTimeSlotHandler();
		
		eventFiringDriver.register(oneTimeUse);
		
		getSaveWebElement().click();
		
		eventFiringDriver.unregister(oneTimeUse);
		
		if ( resultingException != null ) {
			logger.warn(getClass().getSimpleName() + " encounters an " + resultingException.getClass().getSimpleName() + "!");
			
			throw new FormException(resultingException.getMessage());
		}
	}

	/**
	 * It returns if last "Save" event shows a "Confirmation" Popup and accept it.
	 * 
	 * @return a Boolean
	 */
	protected Boolean acceptedConfirmationPopup() {
		
		return acceptedConfirmationPopup;
	}
	
	/**
	 * This method returns the presence of web component in error condition.
	 * The error condition is at application level for the actual form.
	 * (e.g. duplication of value, missing field) 
	 * 
	 * @return true if at least one visible element is in error.
	 */
	protected abstract Boolean containsErrorElement();

	/**
	 * This method returns the <b>WebElement</b> to be clicked for "Save" event.
	 * 
	 * @return a <b>WebElement</b> instance
	 */
	protected abstract WebElement getSaveWebElement();
	
	/**
	 * This method returns the <b>ElementErrorConditionType</b> according actual application status.
	 * 
	 * @return <b>ElementErrorConditionType</b> element
	 */
	protected abstract ElementErrorConditionType defineErrorCondition();
	
	/**
	 * This method proceeds to Abort/Cancel last insertion form.
	 * 
	 * @return it returns the exit status of "Cancel/Abort" operation
	 */
	protected abstract Boolean cancelAction();
	
	/**
	 * This method proceeds to modify the value that block "Save" event into form and re-launch "Save" event.
	 * 
	 * @return it returns the exit status of "Add Timestamp" operation
	 */
	protected abstract Boolean addTimestampAction();
}
