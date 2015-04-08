package com.lumata.e4o.gui.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.campaignmanager.JSONNotification;

public class NotificationForm extends Form {

	private static final Logger logger = LoggerFactory.getLogger(NotificationForm.class);

	private Map<String, JSONNotification> jsonNotificationList;
	private final String notificationDialogFormHeaderXPath = "//div[@class='gwt-DialogBox']//table[@class='Caption']//div[text()='Notification']";
	private final String notificationDialogFormXPath = notificationDialogFormHeaderXPath + "/ancestor::tbody";
	private final String notificationEditDialogFormHeaderXPath = "//div[@class='gwt-DialogBox']//div[@class='Caption'  and text()='Edit']";
	private final String notificationEditDialogFormXPath = notificationEditDialogFormHeaderXPath + "/ancestor::tbody";
	
	public enum NotificationChannel { 
		SMS, MAIL		
	}
	
	public enum NotificationTongue { 
		
		English("ENG"),
		French("FRA"),
		Spanish("SPA");
		
		private String value;
		
		NotificationTongue( String value ) {
			this.value = value;
		}
		
		public String value() { return this.value; }
		
	}
	
	public NotificationForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
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

//	public NotificationForm configureNotifications() throws FormException {
//		
//		for( String notificationName : jsonNotificationList.keySet() ) {
//			
//			configureNotification( jsonNotificationList.get( notificationName ) ).
//			saveBtn();
//			
//		}
//			
//		return this;
//		
//	}

//	public NotificationForm configureNotification( JSONNotification jsonNotification ) throws FormException {
//		
//		/** example: English SMS row xpath */  
//		String notificationTypeRowXPath = notificationDialogFormXPath + "//table[@class='tableList']//div[@id='gwt-debug-TextCampaignModelCreationEN" + NotificationTongue.valueOf( jsonNotification.getTongue() ).value() + "Channel' and text() = '" + NotificationChannel.valueOf( jsonNotification.getType().toUpperCase() ) + "']//ancestor::tr[contains(@class, 'contentRow')]";
//				
//		if( jsonNotification.getText().length() > 0 ) {
//			
//			NotificationTongue.valueOf( jsonNotification.getTongue() );
//			
//			String notificationTypeRowEditButtonXPath = notificationTypeRowXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationEN" +  NotificationTongue.valueOf( jsonNotification.getTongue() ).value() + "Edit']";
//			
//			clickXPath( notificationTypeRowEditButtonXPath );
//			
//			switch( NotificationChannel.valueOf( jsonNotification.getType().toUpperCase() ) ) {
//			
//				case SMS: { 
//					editSMSNotification( jsonNotification );
//					break; 
//				}
//				case MAIL: { 
//					
//					break;					
//				}
//				
//			}
//		
//			saveNotificationEditing();
//			
//		}
//				
//		return this;
//		
//	}
	
	public NotificationForm saveBtn() throws FormException {
		
		String saveBtnXPath = notificationDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENOk']";
		
		clickXPath( saveBtnXPath );			
		
		return this;
		
	}

	public NotificationForm cancelBtn() throws FormException {
		
		String cancelBtnXPath = notificationDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENCancel']";
		
		clickXPath( cancelBtnXPath );			
		
		return this;
		
	}
	
	public NotificationForm editBtn( NotificationTongue tongue, NotificationChannel channel ) throws FormException {
		
		return editBtn( tongue.value(), channel.name() );
		
	}
	
	public NotificationForm editBtn( String tongue, String channel ) throws FormException {
		
		String editBtnXPath = "//div[@id='gwt-debug-TextCampaignModelCreationEN" + tongue + "Channel' and text()='" + channel + "']/parent::td/parent::tr//button[@id='gwt-debug-BtnCampaignModelCreationEN" + tongue + "Edit']";
		
		clickXPath( editBtnXPath );			
		
		return this;
		
	}

	public NotificationForm deleteBtn( NotificationTongue tongue, NotificationChannel channel ) throws FormException {
		
		return deleteBtn( tongue.value(), channel.name() );
		
	}
	
	public NotificationForm deleteBtn( String tongue, String channel ) throws FormException {
		
		String deleteBtnXPath = "//div[@id='gwt-debug-TextCampaignModelCreationEN" + tongue + "Channel' and text()='" + channel + "']/parent::td/parent::tr//button[@id='gwt-debug-BtnCampaignModelCreationEN" + tongue + "Delete']";
		
		clickXPath( deleteBtnXPath );			
		
		return this;
		
	}
	
	public NotificationForm importBtn( NotificationTongue tongue, NotificationChannel channel ) throws FormException {
		
		return importBtn( tongue.value(), channel.name() );
		
	}
	
	public NotificationForm importBtn( String tongue, String channel ) throws FormException {
		
		String importBtnXPath = "//div[@id='gwt-debug-TextCampaignModelCreationEN" + tongue + "Channel' and text()='" + channel + "']/parent::td/parent::tr//button[@id='gwt-debug-BtnCampaignModelCreationEN" + tongue + "Import']";
		
		clickXPath( importBtnXPath );			
		
		return this;
		
	}

	public NotificationForm saveEdititingBtn() throws FormException {
		
		String saveEditingBtnId = "gwt-debug-BtnCampaignModelCreationENESave";
		
		clickId( saveEditingBtnId );			
		
		return this;
		
	}

	public NotificationForm saveTemplateEditingBtn() throws FormException {
		
		String saveTemplateEditingBtnId = "gwt-debug-BtnCampaignModelCreationENESaveTemplate";
		
		clickId( saveTemplateEditingBtnId );			
		
		return this;
		
	}
	
	public NotificationForm cancelEditingBtn() throws FormException {
		
		String cancelEditNotificationId = "gwt-debug-BtnCampaignModelCreationENECancel";
		
		clickId( cancelEditNotificationId );			
		
		return this;
		
	}
	
	public NotificationForm setMessage( String message ) throws FormException {
		
		String textXPath = "//textarea[@id='gwt-debug-TextCampaignModelCreationENEValue']";
		
		typeByXPath( textXPath, message );
		
		return this;
		
	}
	
	
	
	//  //div[@class='gwt-DialogBox']//td[contains(text(), 'Template Name')]/parent::tr//input
	// //div[@id='gwt-debug-TextCampaignModelCreationENELenght']
	// //textarea[@id='gwt-debug-TextCampaignModelCreationENEValue']
	
	/*
	 * 	//div[@id='gwt-debug-TextCampaignModelCreationENENGChannel' and text()='SMS']/parent::td/parent::tr//div[@id='gwt-debug-TextCampaignModelCreationENENGPreview']
		//div[@id='gwt-debug-TextCampaignModelCreationENENGChannel' and text()='SMS']/parent::td/parent::tr//button[@id='gwt-debug-BtnCampaignModelCreationENENGDelete']
		//div[@id='gwt-debug-TextCampaignModelCreationENENGChannel' and text()='SMS']/parent::td/parent::tr//button[@id='gwt-debug-BtnCampaignModelCreationENENGImport']
		//div[@id='gwt-debug-TextCampaignModelCreationENENGChannel' and text()='SMS']/parent::td/parent::tr//button[@id='gwt-debug-BtnCampaignModelCreationENENGEdit']	
	 */
	
	
	
	
	
//	public NotificationForm editSMSNotification( JSONNotification jsonNotification ) throws FormException {
//		
//		String notificationTextXPath = notificationEditDialogFormXPath + "//table[@class='tableList Form']//textarea[@id='gwt-debug-TextCampaignModelCreationENEValue']";
//		
//		typeByXPath( notificationTextXPath, jsonNotification.getText() );
//		
//		return this;
//		
//	}
//	
//	public NotificationForm editMAILNotification( JSONNotification jsonNotification ) {
//		
//		
//		
//		return this;
//		
//	}
//	
//	public NotificationForm saveNotificationEditing() throws FormException {
//		
//		String notificationSaveEditingXPath = notificationEditDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENESave']";
//		
//		clickXPath( notificationSaveEditingXPath );
//		
//		return this;
//		
//	}
//	
//	public NotificationForm saveTemplateNotificationEditing() throws FormException {
//		
//		String notificationSavetemplateEditingXPath = notificationEditDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENESaveTemplate']";
//		
//		clickXPath( notificationSavetemplateEditingXPath );
//		
//		return this;
//		
//	}
//	
//	public NotificationForm cancelNotificationEditing() throws FormException {
//		
//		String notificationCancelEditingXPath = notificationEditDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENECancel']";
//		
//		clickXPath( notificationCancelEditingXPath );
//		
//		return this;
//		
//	}
//	
//	public NotificationForm configureNotifications( Integer eventRow ) throws FormException {
//		
//		// //div[@class='gwt-DialogBox']//table[@class='Caption']//div[text()='Notification']/ancestor::tbody//div[@class='dialogMiddleCenterInner dialogContent']
//		// gwt-debug-BtnCampaignModelCreationENCancel
//		// gwt-debug-BtnCampaignModelCreationENOk
//				
//		// gwt-debug-BtnCampaignModelCreationENENGEdit
//		// gwt-debug-BtnCampaignModelCreationENENGDelete
//		// gwt-debug-BtnCampaignModelCreationENENGImport
//
//		return this;
//		
//	}
//
//	@Override
//	public NotificationForm clickId( String id ) throws FormException {
//		
//		super.clickId( id );
//		
//		return this;
//		
//	}
//	
//	@Override
//	public NotificationForm clickXPath( String xpath ) throws FormException {
//		
//		super.clickXPath( xpath );
//		
//		return this;
//		
//	}
//	
//	@Override
//	public NotificationForm sendKeysById( String id, String text ) throws FormException {
//		
//		super.sendKeysById( id, text );
//		
//		return this;
//		
//	}
//	
//	@Override
//	public NotificationForm selectByXPathAndVisibleText( String xpath, String text ) throws FormException {
//		
//		super.selectByXPathAndVisibleText( xpath, text );
//		
//		return this;
//		
//	}
//	
//	@Override
//	public NotificationForm typeByXPath( String xpath, String text ) throws FormException {
//		
//		super.typeByXPath( xpath, text );
//		
//		return this;
//		
//	}
//	
//	@Override
//	public NotificationForm selectDropDownListItem( String itemPath ) throws FormException {
//		
//		super.selectDropDownListItem( itemPath );
//		
//		return this;		
//		
//	}
//	
//	@Override
//	public Boolean isCheckedByXPath( String xpath ) throws FormException {
//		
//		return super.isCheckedByXPath( xpath );
//		
//	}

}
