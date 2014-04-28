package com.lumata.e4o.gui.dialog;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;
import com.lumata.e4o.gui.json.campaign.JSONNotification;

public class NotificationForm extends Form {

	private static final Logger logger = LoggerFactory.getLogger(NotificationForm.class);

	private Map<String, JSONNotification> jsonNotificationList;
	private final String notificationDialogFormHeaderXPath = "//div[@class='gwt-DialogBox']//table[@class='Caption']//div[text()='Notification']";
	private final String notificationDialogFormXPath = notificationDialogFormHeaderXPath + "/ancestor::tbody";
	private final String notificationEditDialogFormHeaderXPath = "//div[@class='gwt-DialogBox']//div[@class='Caption'  and text()='Edit']";
	private final String notificationEditDialogFormXPath = notificationEditDialogFormHeaderXPath + "/ancestor::tbody";
	
	public enum NotificationType { 
		SMS, MAIL		
	}
	
	public enum NotificationTongueType { 
		
		English("ENG"),
		French("FRA"),
		Spanish("SPA");
		
		private String value;
		
		NotificationTongueType( String value ) {
			this.value = value;
		}
		
		public String value() { return this.value; }
		
	}
	
	public NotificationForm( SeleniumWebDriver selenium, Map<String, JSONNotification> jsonNotificationList, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.jsonNotificationList = jsonNotificationList;
		
	}
	
	public Map<String, JSONNotification> getJSONConfiguration() {
		
		return this.jsonNotificationList;
		
	}

	public void setJSONConfiguration( Map<String, JSONNotification> jsonNotificationList ) {
		
		this.jsonNotificationList = jsonNotificationList;
		
	}

	public NotificationForm configureNotifications() throws FormException {
		
		for( String notificationName : jsonNotificationList.keySet() ) {
			
			configureNotification( jsonNotificationList.get( notificationName ) ).
			saveNotifications();
			
		}
			
		return this;
		
	}

	public NotificationForm configureNotification( JSONNotification jsonNotification ) throws FormException {
		
		/** example: English SMS row xpath */  
		String notificationTypeRowXPath = notificationDialogFormXPath + "//table[@class='tableList']//div[@id='gwt-debug-TextCampaignModelCreationEN" + NotificationTongueType.valueOf( jsonNotification.getTongue() ).value() + "Channel' and text() = '" + NotificationType.valueOf( jsonNotification.getType().toUpperCase() ) + "']//ancestor::tr[contains(@class, 'contentRow')]";
				
		if( jsonNotification.getText().length() > 0 ) {
			
			NotificationTongueType.valueOf( jsonNotification.getTongue() );
			
			String notificationTypeRowEditButtonXPath = notificationTypeRowXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationEN" +  NotificationTongueType.valueOf( jsonNotification.getTongue() ).value() + "Edit']";
			
			clickXPath( notificationTypeRowEditButtonXPath );
			
			switch( NotificationType.valueOf( jsonNotification.getType().toUpperCase() ) ) {
			
				case SMS: { 
					editSMSNotification( jsonNotification );
					break; 
				}
				case MAIL: { 
					
					break;					
				}
				
			}
		
			saveNotificationEditing();
			
		}
				
		return this;
		
	}
	
	public NotificationForm saveNotifications() throws FormException {
		
		String notificationSaveXPath = notificationDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENOk']";
		
		clickXPath( notificationSaveXPath );			
		
		return this;
		
	}

	public NotificationForm cancelNotifications() throws FormException {
		
		String notificationCancelXPath = notificationEditDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENCancel']";
		
		clickXPath( notificationCancelXPath );			
		
		return this;
		
	}
	
	public NotificationForm editSMSNotification( JSONNotification jsonNotification ) throws FormException {
		
		String notificationTextXPath = notificationEditDialogFormXPath + "//table[@class='tableList Form']//textarea[@id='gwt-debug-TextCampaignModelCreationENEValue']";
		
		typeByXPath( notificationTextXPath, jsonNotification.getText() );
		
		return this;
		
	}
	
	public NotificationForm editMAILNotification( JSONNotification jsonNotification ) {
		
		
		
		return this;
		
	}
	
	public NotificationForm saveNotificationEditing() throws FormException {
		
		String notificationSaveEditingXPath = notificationEditDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENESave']";
		
		clickXPath( notificationSaveEditingXPath );
		
		return this;
		
	}
	
	public NotificationForm saveTemplateNotificationEditing() throws FormException {
		
		String notificationSavetemplateEditingXPath = notificationEditDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENESaveTemplate']";
		
		clickXPath( notificationSavetemplateEditingXPath );
		
		return this;
		
	}
	
	public NotificationForm cancelNotificationEditing() throws FormException {
		
		String notificationCancelEditingXPath = notificationEditDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENECancel']";
		
		clickXPath( notificationCancelEditingXPath );
		
		return this;
		
	}
	
	public NotificationForm configureNotifications( Integer eventRow ) throws FormException {
		
		// //div[@class='gwt-DialogBox']//table[@class='Caption']//div[text()='Notification']/ancestor::tbody//div[@class='dialogMiddleCenterInner dialogContent']
		// gwt-debug-BtnCampaignModelCreationENCancel
		// gwt-debug-BtnCampaignModelCreationENOk
				
		// gwt-debug-BtnCampaignModelCreationENENGEdit
		// gwt-debug-BtnCampaignModelCreationENENGDelete
		// gwt-debug-BtnCampaignModelCreationENENGImport

		return this;
		
	}

	@Override
	public NotificationForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
		
	}
	
	@Override
	public NotificationForm clickXPath( String xpath ) throws FormException {
		
		super.clickXPath( xpath );
		
		return this;
		
	}
	
	@Override
	public NotificationForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
		
	}
	
	@Override
	public NotificationForm selectByXPathAndVisibleText( String xpath, String text ) throws FormException {
		
		super.selectByXPathAndVisibleText( xpath, text );
		
		return this;
		
	}
	
	@Override
	public NotificationForm typeByXPath( String xpath, String text ) throws FormException {
		
		super.typeByXPath( xpath, text );
		
		return this;
		
	}
	
	@Override
	public NotificationForm selectDropDownListItem( String itemPath ) throws FormException {
		
		super.selectDropDownListItem( itemPath );
		
		return this;		
		
	}
	
	@Override
	public Boolean isCheckedByXPath( String xpath ) throws FormException {
		
		return super.isCheckedByXPath( xpath );
		
	}

}
