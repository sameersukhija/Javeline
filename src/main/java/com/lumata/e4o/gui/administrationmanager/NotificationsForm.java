package com.lumata.e4o.gui.administrationmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.administrationmanager.JSONNotifications;
import com.lumata.e4o.json.gui.administrationmanager.JSONNotifications.NotificationLanguage;
import com.lumata.e4o.json.gui.administrationmanager.JSONNotifications.NotificationType;

public class NotificationsForm extends AdministrationForm {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(NotificationsForm.class);
	
	/**
	 * 
	 */
	private JSONNotifications notificationsCfg = null;
	
	/**
	 * 
	 * @param selenium
	 * @param timeout
	 * @param interval
	 */
	public NotificationsForm(SeleniumWebDriver selenium, JSONNotifications inputCfg, long timeout, long interval) {
		
		super(selenium, timeout, interval);
		
		this.notificationsCfg = inputCfg;
	}

	/**
	 * 
	 */
	public NotificationsForm open() throws FormException {

		super.open().clickId( "gwt-debug-actrule-notification-notifications" );
		
		return this;
	}
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public NotificationsForm addNotificationsList() throws FormException, JSONSException {
		
		int numbNotifications = notificationsCfg.getList().size();
		
		for( int index = 0; index < numbNotifications; index++ ) {
			
			notificationsCfg.setCurrentElementById(index);
			
			/**
			 * Only "enabled" notifications will be configured
			 */
			if ( notificationsCfg.getCurrentElement().getEnabled() ) {
				clickXPath( "//button[@title='Add a notification']" );
				
				createNotification().
				saveNotification();
			}
					
		}		
		
		return this;
	}
		
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public NotificationsForm createNotification() throws FormException, JSONSException {
		
		NotificationType currentType = notificationsCfg.getNotificationType();
		NotificationLanguage currentLang = notificationsCfg.getNotificationLanguage();
		
		selectByXPathAndVisibleText( "//td[contains(text(),'Choose channel')]/ancestor::tr[1]//select", currentType.toString() );
		selectByXPathAndVisibleText( "//td[contains(text(),'Choose language')]/ancestor::tr[1]//select", currentLang.toString() );
		clickXPath("//div[text()='Choose channel']/ancestor::div[2]//button[@title='OK']");
		
		if ( currentType.equals(NotificationType.SMS) ) {
			
			sendKeysByXPath("//textarea[contains(@id,'TextCampaignModelCreationENEValue')]", notificationsCfg.getTextMessage());
			
			clickXPath("//button[@id='gwt-debug-BtnCampaignModelCreationENESaveTemplate']");
			
			sendKeysByXPath("//td[contains(text(),'Template Name')]/ancestor::tr[1]//input", notificationsCfg.getTemplateName());
		}
		else if ( currentType.equals(NotificationType.MAIL) ) {

			sendKeysByXPath("//textarea[contains(@id,'TextCampaignModelCreationENEValue')]", notificationsCfg.getTextMessage());

			throw new FormException(getClass().getSimpleName() + " \"Mail Notification\" not implemented!");
		}
		
		return this;
	}
	
	public NotificationsForm saveNotification() throws FormException, JSONSException {
		
		if ( containsErrorElement() )
			logger.error("Without click \"save\" panel is in error!");
		
		Boolean completed = Boolean.FALSE;
		
		do {
			
			/**
			 * Core save procedure
			 */
			
			clickXPath("//td[contains(text(),'Template Name')]/ancestor::div[1]//button[@title='OK']");
			
			/**
			 * End - Core save procedure
			 */
			
			// in case no confirmation was executed, check element in error
			if ( templateNameErrorElement() ) {

				JsonCurrentElement current = notificationsCfg.getCurrentElement();
				
				logger.warn("After click \"Save\" panel is in error!");
				
				ElementErrorConditionType condition = null;
				
				searchByXPath("//div[@class='gwt-DialogBox']");
				
				// error condition
				//div[text()='Bonus name already used']
				List<WebElement> element = SeleniumUtils.findListForComponentDisplayed(	selenium, 
																						SearchBy.XPATH, 
																						lastWebElement, 
																						"//div[text()='An error occured']"
																					);
				
				// close error popup
				//div[text()='An error occured']/ancestor::div[2]//button[@title='OK']
				
				clickXPath( "//div[text()='An error occured']/ancestor::div[2]//button[@title='OK']" );
				
				if ( element.size() != 0 )
					condition = ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
				else
					condition = ElementErrorConditionType.GENERAL_ERROR;
				
				ElementErrorActionType action = current.getErrorActions().getAction(condition);
				
				// abort insertion
				if ( action.equals(ElementErrorActionType.ABORT_CANCEL) ) {
					clickId( "gwt-debug-BtnCampaignModelCreationENECancel" );
					
					completed = Boolean.TRUE;
				}
				// stop execution and return error
				else if ( action.equals(ElementErrorActionType.RETURN_ERROR) )
					throw new FormException(getClass().getSimpleName() + " cannot configure \""+current.getStringFromPath("name")+"\" commodities!");
				// add timestamp to name
				else if ( action.equals(ElementErrorActionType.ADD_TIMESTAMP_TO_FIELD) ) {
					
					// one click su input
					clickXPath( "//input[@id='gwt-debug-Bonus Name']");
					
					// delete current text
					lastWebElement.clear();
					
					/**
					 * Technical Debit - This point contains an error
					 */
					
					String actualName = current.getStringFromPath("name");
					current.setObjectFromPath("name", actualName + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
					
					// send old text + timestamp
					typeById( "gwt-debug-Bonus Name", notificationsCfg.getTemplateName() );
					
					completed = Boolean.FALSE;
				}
			}
			else // all ok!
				completed = Boolean.TRUE;			
		}
		while ( !completed );
		
		return this;
	}
	
	public JSONNotifications getNotificationsFormCfg() {
		return this.notificationsCfg;
	}
	
	public void setNotificationsFormCfg( JSONNotifications inputCfg ) {
		this.notificationsCfg = inputCfg;
	}
	
	/**
	 * This method delete a list of "Notifications" into running system.
	 * If input var-args is empty or null, this method deletes each notifications
	 * 
	 * @param NotificationsNames
	 * 
	 * @return true if wanted notifications are corrected removed
	 * 
	 * @throws FormException 
	 */	
	public Boolean deleteNotifications(String... NotificationsNames) throws FormException {

		//div[text()='dgsdgdfg']/ancestor::tr[1]//button[@title='Delete']
		
		List<String> notificationsLabel = null;
		Boolean resp = Boolean.FALSE;
		
		if ( NotificationsNames != null && NotificationsNames.length != 0 )
			notificationsLabel = Arrays.asList(NotificationsNames);
		else { // fetch every commodities present on UI
			
			notificationsLabel = new ArrayList<String>();
			
			String rootPath = "//table[contains(@class, 'page-NotificationsPageView')]";
			String subPath = "//table[contains(@class, 'page-NotificationsPageView')]//tr[contains(@class, 'contentRow')]";
		
			List<WebElement> cmLabels = getListByXPath(rootPath, rootPath + subPath);
			
			for (WebElement webElement : cmLabels)
				notificationsLabel.add(webElement.getText());
		}

		logger.debug("Commodities element to be deleted : " + notificationsLabel);
		
		try {
			
			for (String cnName : notificationsLabel) {
				
				String singleRule = "//div[text()='"+cnName+"']//ancestor::tr[1]//*[@name='btn-delete']";

				logger.debug("Try to delete \"Notification\" with name + \""+cnName+"\".");
				
				clickXPath(singleRule);
				
				handleJavascriptAlertAcceptDismiss(true);
			}
			
			resp = Boolean.TRUE;
			
		} catch ( FormException e ) {

			logger.error("Error during delete \"Notifications \" : " + e.getMessage());
			
			resp = Boolean.FALSE;
		}

		return resp;
	}
	
	/**
	 * This method returns the presence of web component that inform "Template name" error.
	 * The error condition is at application level for the actual form.
	 * (e.g. duplication of value, missing field) 
	 * 
	 * @return true if "Template Name" error is displayed.
	 * 
	 * @throws FormException
	 */
	private Boolean templateNameErrorElement() throws FormException {
		
		Integer numbers = null;
		Boolean resp = Boolean.TRUE;

		// error condition
		//div[text()='An error occured']
		//div[text()='An error occured']/ancestor::div[2]//button[@title='OK']
		
		List<WebElement> elements = getErrorElement("//div[text()='An error occured']");

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
}
