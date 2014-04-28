package com.lumata.e4o.gui.customercare;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Security;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

public class CustomerCareLightForm extends Form {

	private static final Logger logger = LoggerFactory.getLogger(CustomerCareLightForm.class);

	private final String link = "angular/customerCare/index.html?lang=ENG&login=${login}&pwd=${password}#/tokens/${msisdn}";
	private String url;	
	
	/*
	private Map<String, JSONNotification> jsonNotificationList;
	private final String notificationDialogFormHeaderXPath = "//div[@class='gwt-DialogBox']//table[@class='Caption']//div[text()='Notification']";
	private final String notificationDialogFormXPath = notificationDialogFormHeaderXPath + "/ancestor::tbody";
	private final String notificationEditDialogFormHeaderXPath = "//div[@class='gwt-DialogBox']//div[@class='Caption'  and text()='Edit']";
	private final String notificationEditDialogFormXPath = notificationEditDialogFormHeaderXPath + "/ancestor::tbody";
	*/
		
	public CustomerCareLightForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
			
	}

	public CustomerCareLightForm open() {
		
		selenium.open( url );
		
		waitForPageLoading();
		
		return this;
		
	}
	
	public CustomerCareLightForm buildUrl( User user, String msisdn ) {
		
		url = link.				
				replace( "${login}", ( null != user.getUsername() ? user.getUsername() : "" ) ).
				replace( "${password}", ( null != user.getPassword() ? Security.decrypt( user.getPassword() ) : "" ) ).
				replace( "${msisdn}", ( null != msisdn ? msisdn : "" ) );			
		
		return this;
		
	}
	
	public Boolean isAuthenticated() throws FormException {
		
		String authenticationSuccessXPath = "//p[@class='ng-hide' and @ng-show='notAuthenticated' and contains(text(), 'Authentication error, please try again with different credentials' )]";
		
		searchByXPath( authenticationSuccessXPath );
			
		return true;
						
	}
	
	public Boolean isNotAuthenticated() throws FormException {
		
		String authenticationFailureXPath = "//p[@class='' and @ng-show='notAuthenticated' and contains(text(), 'Authentication error, please try again with different credentials' )]";
		
		searchByXPath( authenticationFailureXPath );
			
		return true;
						
	}
	
	public Boolean isResultNotFound() throws FormException {
		
		String resultNotFoundXPath = "//span[@key='actrule-customerCare-tokens-noResultsFound' and contains(text(), 'No results found')]/ancestor::div[@class='e4ol-section' and @ng-show='tokens == undefined']";
		
		searchByXPath( resultNotFoundXPath );
			
		return true;
						
	}
	
	public Boolean isResultFound() throws FormException {
		
		String resultFoundXPath = "//div[@class='e4ol-section' and @ng-show='tokens != undefined']";
		
		searchByXPath( resultFoundXPath );
			
		return true;
						
	}
	
	public CustomerCareLightForm list() throws FormException {
		
		String tokensListXPath = "//div[@ng-show='tokens != undefined']";
		String tokensListContentXPath = "//div[@ng-class=\"{'e4ol-list__row--highlight': token.id == offerToken.id}\"]";
		
		List<WebElement> we = getListByXPath( tokensListXPath, tokensListContentXPath );
		
		for( int w = 0; w < we.size(); w++ ) {
			
			List<WebElement> weCell = we.get( w ).findElements( By.xpath( "//div[@class='e4ol-list__cell e4ol-list__cell--text ng-binding']" ) );
			
			for( int wc = 0; wc < weCell.size(); wc++ ) {
				
				System.out.print( weCell.get( wc ).getText() );
				System.out.print( " | " );
							
			}
			
			System.out.println( " " );	
			
		}
		
		return this;
		
	}
	
	/*
	public Map<String, JSONNotification> getJSONConfiguration() {
		
		return this.jsonNotificationList;
		
	}

	public void setJSONConfiguration( Map<String, JSONNotification> jsonNotificationList ) {
		
		this.jsonNotificationList = jsonNotificationList;
		
	}

	public CustomerCareLight configureNotifications() throws FormException {
		
		for( String notificationName : jsonNotificationList.keySet() ) {
			
			configureNotification( jsonNotificationList.get( notificationName ) ).
			saveNotifications();
			
		}
			
		return this;
		
	}

	public CustomerCareLight configureNotification( JSONNotification jsonNotification ) throws FormException {
		
		/** example: English SMS row xpath */  
/*		String notificationTypeRowXPath = notificationDialogFormXPath + "//table[@class='tableList']//div[@id='gwt-debug-TextCampaignModelCreationEN" + NotificationTongueType.valueOf( jsonNotification.getTongue() ).value() + "Channel' and text() = '" + NotificationType.valueOf( jsonNotification.getType().toUpperCase() ) + "']//ancestor::tr[contains(@class, 'contentRow')]";
				
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
	
	public CustomerCareLight saveNotifications() throws FormException {
		
		String notificationSaveXPath = notificationDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENOk']";
		
		clickXPath( notificationSaveXPath );			
		
		return this;
		
	}

	public CustomerCareLight cancelNotifications() throws FormException {
		
		String notificationCancelXPath = notificationEditDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENCancel']";
		
		clickXPath( notificationCancelXPath );			
		
		return this;
		
	}
	
	public CustomerCareLight editSMSNotification( JSONNotification jsonNotification ) throws FormException {
		
		String notificationTextXPath = notificationEditDialogFormXPath + "//table[@class='tableList Form']//textarea[@id='gwt-debug-TextCampaignModelCreationENEValue']";
		
		typeByXPath( notificationTextXPath, jsonNotification.getText() );
		
		return this;
		
	}
	
	public CustomerCareLight editMAILNotification( JSONNotification jsonNotification ) {
		
		
		
		return this;
		
	}
	
	public CustomerCareLight saveNotificationEditing() throws FormException {
		
		String notificationSaveEditingXPath = notificationEditDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENESave']";
		
		clickXPath( notificationSaveEditingXPath );
		
		return this;
		
	}
	
	public CustomerCareLight saveTemplateNotificationEditing() throws FormException {
		
		String notificationSavetemplateEditingXPath = notificationEditDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENESaveTemplate']";
		
		clickXPath( notificationSavetemplateEditingXPath );
		
		return this;
		
	}
	
	public CustomerCareLight cancelNotificationEditing() throws FormException {
		
		String notificationCancelEditingXPath = notificationEditDialogFormXPath + "//button[@id='gwt-debug-BtnCampaignModelCreationENECancel']";
		
		clickXPath( notificationCancelEditingXPath );
		
		return this;
		
	}
	
	public CustomerCareLight configureNotifications( Integer eventRow ) throws FormException {
		
		// //div[@class='gwt-DialogBox']//table[@class='Caption']//div[text()='Notification']/ancestor::tbody//div[@class='dialogMiddleCenterInner dialogContent']
		// gwt-debug-BtnCampaignModelCreationENCancel
		// gwt-debug-BtnCampaignModelCreationENOk
				
		// gwt-debug-BtnCampaignModelCreationENENGEdit
		// gwt-debug-BtnCampaignModelCreationENENGDelete
		// gwt-debug-BtnCampaignModelCreationENENGImport

		return this;
		
	}

	@Override
	public CustomerCareLight clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
		
	}
	
	@Override
	public CustomerCareLight clickXPath( String xpath ) throws FormException {
		
		super.clickXPath( xpath );
		
		return this;
		
	}
	
	@Override
	public CustomerCareLight sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
		
	}
	
	@Override
	public CustomerCareLight selectByXPathAndVisibleText( String xpath, String text ) throws FormException {
		
		super.selectByXPathAndVisibleText( xpath, text );
		
		return this;
		
	}
	
	@Override
	public CustomerCareLight typeByXPath( String xpath, String text ) throws FormException {
		
		super.typeByXPath( xpath, text );
		
		return this;
		
	}
	
	@Override
	public CustomerCareLight selectDropDownListItem( String itemPath ) throws FormException {
		
		super.selectDropDownListItem( itemPath );
		
		return this;		
		
	}
	
	@Override
	public Boolean isCheckedByXPath( String xpath ) throws FormException {
		
		return super.isCheckedByXPath( xpath );
		
	}
*/
}
