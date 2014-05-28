package com.lumata.e4o.json.gui.administrationmanager;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile;

public class JSONNotifications extends JsonConfigurationFile {

	/**
	 * This enum describes the Notification type
	 */
	public enum NotificationType {
		
		SMS, MAIL;
	}
	
	/**
	 * This enum describes the Notification language
	 */
	public enum NotificationLanguage {
		
		English, Spanish, French;
	}
	
	public JSONNotifications(String path, String jsonFile) throws JSONSException {
		super(path, jsonFile);
	}
	
	public NotificationType getNotificationType() {
		
		String raw = getCurrentElement().getStringFromPath("chooseChannel");
		
		return NotificationType.valueOf(raw);
	}
	
	public NotificationLanguage getNotificationLanguage() {

		String raw = getCurrentElement().getStringFromPath("chooseLanguage");
		
		return NotificationLanguage.valueOf(raw);
	}
	
	public String getMailObject() {
		
		return getCurrentElement().getStringFromPath("mailObject");
	}
	
	public String getTextMessage() {
		
		return getCurrentElement().getStringFromPath("textMessage");
	}	
	
	public String getTemplateName() {
		
		return getCurrentElement().getStringFromPath("templateName");
	}
	
	public String getContentFile() {
		
		return getCurrentElement().getStringFromPath("contentFile");
	}
	
	@Override
	public String getElementsSectionLabel() {

		return "notifications";
	}

}