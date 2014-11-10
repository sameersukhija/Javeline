package com.lumata.e4o.json.gui.administrationmanager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

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
	
	private List<String> getMailTemplate() {
		
		List<String> resp = new ArrayList<String>();
		
		String raw = getCurrentElement().getStringFromPath("contentFile");
		
		for (String singleLine : raw.split("\n")) 
			resp.add(singleLine);
		
		return resp;
	}	
	
	/**
	 * This method generates a temporary file that contains html mail template.
	 * 
	 * @return File object
	 * @throws JSONSException 
	 */
	public File getMailNotificationFile() throws JSONSException {

		File resp = null;
		
		try {
			Path temp = Files.createTempFile("tempMailTemplateFile", ".html");
			
		    Files.write(temp, getMailTemplate(), Charset.defaultCharset(), StandardOpenOption.WRITE);		
			
			resp = temp.toFile();	
		}
		catch ( IOException e ) {
			
			throw new JSONSException("Error during mail template file creation : " + e.getMessage());
		}
		
		return resp;
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