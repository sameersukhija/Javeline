package com.lumata.e4o.gui.administrationmanager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.ErrorModificableElement;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.utils.TempFileHandling;
import com.lumata.common.testing.utils.TempFileHandling.TempFileExtension;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler;
import com.lumata.e4o.gui.common.NotificationForm;
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

public NotificationsForm(SeleniumWebDriver selenium, long timeout, long interval) {
		
		super(selenium, timeout, interval);
		
	}
	/**
	 * 
	 */
	public NotificationsForm open() throws FormException {

		super.open().clickId( "gwt-debug-actrule-notification-notifications" );
		
		return this;
	}
	public NotificationsForm addNotification() throws FormException{
		this.clickXPath( "//button[@title='Add a notification']" );
		return this;
	}
	public NotificationsForm setChannelType(JSONNotifications.NotificationType channel) throws FormException{
		selectByXPathAndVisibleText( "//td[contains(text(),'Choose channel')]/ancestor::tr[1]//select", channel.name());
		return this;
	}
	public String getChannelType() throws FormException{
		return this.getValueByXPath("//td[contains(text(),'Choose channel')]/ancestor::tr[1]//select");
	}
	public NotificationsForm setLanguage(JSONNotifications.NotificationLanguage language) throws FormException{
		selectByXPathAndVisibleText( "//td[contains(text(),'Choose language')]/ancestor::tr[1]//select", language.name());
		return this;
	}
	public String getLanguage() throws FormException{
		return this.getValueByXPath("//td[contains(text(),'Choose language')]/ancestor::tr[1]//select");
	}
	public NotificationsForm channelOKButton() throws FormException{
		this.clickXPath("//div[text()='Choose channel']/ancestor::div[2]//button[@title='OK']");
		return this;
	}
	public NotificationsForm setSMSMessage(String sms) throws FormException{
		this.sendKeysByXPath("//textarea[contains(@id,'TextCampaignModelCreationENEValue')]", sms);
		return this;
	}
	public NotificationsForm saveTemplate() throws FormException{
		this.clickXPath("//button[@id='gwt-debug-BtnCampaignModelCreationENESaveTemplate']");
		return this;
	}
	public NotificationsForm setTemplateName(String name) throws FormException{
		this.sendKeysByXPath("//td[contains(text(),'Template Name')]//ancestor::tr[1]//input", name);
		return this;
	}
	public NotificationsForm templateOKButton() throws FormException{
		this.clickXPath("//div[text()='Template Name']/ancestor::div[2]//button[@title='OK']");
		return this;
	}
	public NotificationsForm setMailObject(String mail_object) throws FormException{
		this.sendKeysByXPath("//td[contains(text(),'Object')]//ancestor::tr[1]//input", mail_object);
		return this;
	}
	public String getMailObject() throws FormException{
		return this.getValueByXPath("//td[contains(text(),'Object')]//ancestor::tr[1]//input");
	}
	public NotificationsForm uploadContentFile(String tmp_path) throws FormException{
		selenium.getWrappedDriver().findElement(By.xpath("//form[contains(@action,'dialogImport')]//input[@name='uploadFormElement']")).sendKeys(tmp_path);
		return this;
	}
	public NotificationsForm clickImportButton() throws FormException{
		this.clickXPath("//table[@class='tableList Form']//table[@class='importPanel']//button[@name='btn-importer']");
		return this;
	}
public Boolean isTemplateInList( String templateName ) throws FormException {
		
		List<WebElement> templateList = getTemplateList();
				
		for( WebElement templateEl : templateList ) {
			
			if( templateEl.getText().trim().equals(templateName)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	} 
public List<WebElement> getTemplateList() throws FormException {
	
	String rootPath = "//table[contains(@class, 'page-NotificationsPageView')]//table[@class='tableList']";
	String subPath = "//tr[contains(@class, 'contentRow cycle')]//td[@class='column_name'][1]";

	List<WebElement> templateList = getListByXPath(rootPath, rootPath + subPath);
	return templateList;
		
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
		}
		else if ( currentType.equals(NotificationType.MAIL) ) {

			// oggetto mail
			//td[contains(text(),'Object')]//ancestor::tr[1]//input			
			sendKeysByXPath("//td[contains(text(),'Object')]//ancestor::tr[1]//input", notificationsCfg.getMailObject());
			
			WebElement uploadElement = selenium.getWrappedDriver().findElement(By.xpath("//form[contains(@action,'dialogImport')]//input[@name='uploadFormElement']"));
//			uploadElement.clear();
			
//			File templateFile = notificationsCfg.getMailNotificationFile();
			Path dir_path=Paths.get(System.getProperty( "user.dir" ) + "/src/test/resources/input/administrationmanager/notifications/");
			List<String> lis=new ArrayList<String>();
			lis.add(notificationsCfg.getContentFile());
			try {
				TempFileHandling.uploadFile( 	uploadElement,
												notificationsCfg.getMailNotificationFile(dir_path,lis, notificationsCfg.getTemplateName(),TempFileExtension.HTML).toPath());
			} catch (IOException e1) {
				e1.printStackTrace();
				throw new FormException(e1.getMessage());
			}
			
//			WebElement fileUpload = selenium.getWrappedDriver().findElement(By.xpath("//form[contains(@action,'dialogImport')]//input[@name='uploadFormElement']"));
//			fileUpload.sendKeys(templateFile.getAbsolutePath());

			// manage errors
			MailNotificationImporterHandler handler = new MailNotificationImporterHandler( 	selenium.getWrappedDriver(), 
																							notificationsCfg.getCurrentElement());
			
			handler.saveAction();		
			
			try {
				Thread.sleep(2_000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			
			// Save template
			clickXPath("//div[contains(@class,'dialogMiddleCenterInner')]//button[@title='Save Template']");
		}
		
		return this;
	}
	
	public NotificationsForm saveNotification() throws FormException, JSONSException {

		// Template name
		//td[contains(text(),'Template Name')]//ancestor::tr[1]//input
		
		
		// manage errors
		SaveNotifTemplateHandler handler = new SaveNotifTemplateHandler(	selenium.getWrappedDriver(), 
																				notificationsCfg.getCurrentElement());				
		handler.saveAction();		

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
		
			List<WebElement> cmLabels = searchListByXPath(rootPath, rootPath + subPath);
			
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
			throw e;
			
		}

		return resp;
	}

	/**
	 * 
	 * 
	 * Save Handler
	 * 
	 * 
	 * 
	 */
	
	private class SaveNotifTemplateHandler extends FormSaveConfigurationHandler {

		protected SaveNotifTemplateHandler(WebDriver inDriver, ErrorModificableElement inCurrentElement) {
			
			super(inDriver, inCurrentElement);
		}

		@Override
		protected Boolean containsErrorElement() {
			
			Integer numbers = null;
			Boolean resp = Boolean.TRUE;

			// error condition
			//div[text()='An error occured']
			//div[text()='An error occured']/ancestor::div[2]//button[@title='OK']
			
			List<WebElement> elements = getWebDriver().findElements(By.xpath("//div[text()='An error occured']")); 
					
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
		 * 
		 */
		private WebElement saveElement = null;
		
		@Override
		protected WebElement getSaveWebElement() {

			if ( saveElement == null )
				saveElement = getWebDriver().findElement(By.xpath("//div[contains(text(),'Template Name')]//ancestor::tbody//button[@title='OK']")); 
			
			return saveElement;			
		}

		@Override
		protected ElementErrorConditionType defineErrorCondition() {
			
			ElementErrorConditionType condition = null;
			
			WebElement dialogBox = getWebDriver().findElement(By.xpath("//div[@class='gwt-DialogBox']"));
			
			// error condition
			//div[text()='Bonus name already used']			
			List<WebElement> element = dialogBox.findElements(By.xpath("//div[text()='Name is not valid or is already used']"));
								
			if ( element.size() != 0 )
				condition = ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
			else
				condition = ElementErrorConditionType.GENERAL_ERROR;

			return condition;
		}

		@Override
		protected Boolean cancelAction() {
			
			Boolean resp = Boolean.FALSE;

			try {

				getWebDriver().findElement(By.xpath("//div[contains(text(),'An error occured')]//ancestor::tbody//button")).click();
				
				Thread.sleep(1_000);
				
				NotificationType currentType = notificationsCfg.getNotificationType();
				WebElement cancelButton = null;
				
				if ( currentType.equals(NotificationType.SMS) )
					cancelButton = getWebDriver().findElement(By.id("gwt-debug-BtnCampaignModelCreationENECancel"));
				else if ( currentType.equals(NotificationType.MAIL) ) 
					cancelButton = getWebDriver().findElement(By.xpath("//div[contains(@class,'dialogMiddleCenterInner')]//button[@title='Cancel']"));
				else
					throw new NoSuchElementException("Missing notification type during cancel procedure!");
				
				cancelButton.click();
				
				resp = Boolean.TRUE;
			}
			catch ( NoSuchElementException | InterruptedException e ) {
			
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}

		@Override
		protected Boolean addTimestampAction() {
			
			Boolean resp = Boolean.FALSE;

			Long timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
			NotificationType currentType = notificationsCfg.getNotificationType(); 
			
			try {
			
				String oldTemplateName = notificationsCfg.getTemplateName();
				
				getCurrentElement().modifyStringFromPath("templateName", oldTemplateName + timestamp);
				
				// clean text
				// one click su input
				clickXPath( "//textarea[contains(@id,'TextCampaignModelCreationENEValue')]" );
				
				// delete current text
				lastWebElement.clear();	
				
				if ( currentType.equals(NotificationType.SMS) ) { // SMS

					// clean text
					// one click su input
					//clickXPath( "//textarea[contains(@id,'TextCampaignModelCreationENEValue')]" );
					
					// delete current text
					//lastWebElement.clear();					
					
					sendKeysByXPath("//textarea[contains(@id,'TextCampaignModelCreationENEValue')]", notificationsCfg.getTextMessage());
					
					clickXPath("//button[@id='gwt-debug-BtnCampaignModelCreationENESaveTemplate']");
					
					sendKeysByXPath("//td[contains(text(),'Template Name')]/ancestor::tr[1]//input", notificationsCfg.getTemplateName());
				}
				else if ( currentType.equals(NotificationType.MAIL) ) { // MAIL
					
					sendKeysByXPath("//td[contains(text(),'Template Name')]/ancestor::tr[1]//input", notificationsCfg.getTemplateName());
				}

				saveAction();
				
				resp = Boolean.TRUE;
				
			} catch ( NoSuchElementException | FormException | JSONSException e ) {
				
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}
		
	}
	
	
	private class MailNotificationImporterHandler extends FormSaveConfigurationHandler {

		protected MailNotificationImporterHandler( WebDriver inDriver, ErrorModificableElement inCurrentElement) {
			
			super(inDriver, inCurrentElement);
		}

		@Override
		protected Boolean containsErrorElement() {
			// TODO Auto-generated method stub
			return Boolean.FALSE;
		}

		/**
		 * 
		 */
		private WebElement saveElement = null;
		
		@Override
		protected WebElement getSaveWebElement() {

			if ( saveElement == null )
				saveElement = getWebDriver().findElement(By.xpath("//form[contains(@action,'dialogImport')]//ancestor::tr[1]//button")); 
			
			return saveElement;			
		}

		@Override
		protected ElementErrorConditionType defineErrorCondition() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Boolean cancelAction() {

			throw new RuntimeException("Not supported yet!");
		}

		@Override
		protected Boolean addTimestampAction() {
			
			throw new RuntimeException("Not supported yet!");
		}

	}	
}
