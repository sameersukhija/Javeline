package com.lumata.e4o.regressions.gui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.utils.TempFileHandling.TempFileExtension;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.NotificationsForm;
import com.lumata.e4o.gui.catalogmanager.ProductTypesForm;
import com.lumata.e4o.json.gui.administrationmanager.JSONNotifications;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.common.JSONNotification;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(
		@TCOwner( name="Parvinder Bhogra", email="parvinder.bhogra@lumatagroup.com" )
	)
	@TCSeleniumWebDriver
public class TestNotificationTemplate extends ParentTestCase{
	@Test(enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT,priority=1)
	@Parameters({"jsonFilePath_notif","jsonFileName_notif"})
	public void testUc10_01CreateNewSMSTemplate(@Optional("input/administrationmanager/notifications/") String jsonFilePath_notif, @Optional("newNotification") String jsonFileName_notif) throws JSONSException,FormException
	{
		JSONNotifications setupNotification=null;
		NotificationsForm notificationForm=null;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Reporter.log("Creation of \"New SMS Template\".", LOG_TO_STD_OUT);
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_notif;
		String resourceFile = jsonFileName_notif;

		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		
		setupNotification = new JSONNotifications(resourcePath, resourceFile);

		notificationForm= new NotificationsForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
		for(int i=0 ; i<setupNotification.getList().size()-1; i++)
		{
			JsonCurrentElement current=setupNotification.getCurrentElementById(i);
			if(current.getEnabled() == true)
			{
				Boolean status=false;
				notificationForm.open().addNotification();
				notificationForm.setChannelType(setupNotification.getNotificationType());
				notificationForm.setLanguage(setupNotification.getNotificationLanguage()).channelOKButton();
				notificationForm.setSMSMessage(setupNotification.getTextMessage());
				notificationForm.saveTemplate();
				notificationForm.setTemplateName(setupNotification.getTemplateName()).templateOKButton();
				
				status=notificationForm.isTemplateInList(setupNotification.getTemplateName());
				if(status==true)
				{
					Assert.assertTrue(status);
					Reporter.log("Template Created Succesfully!");
				}
				else
				{
					//Assert.assertTrue(status,"The creation of Product Failed!");
					Assert.fail("The Template creation Failed!");
					Reporter.log("Creation of Template Failed!");
				}
			}
			
		}
		
		}
	@Test(enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT,priority=2)
	@Parameters({"jsonFilePath_notif","jsonFileName_notif"})
	public void testUc10_03CreateNewMailTemplate(@Optional("input/administrationmanager/notifications/") String jsonFilePath_notif, @Optional("newNotification") String jsonFileName_notif) throws JSONSException,FormException
	{
		JSONNotifications setupNotification=null;
		NotificationsForm notificationForm=null;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Reporter.log("Creation of \"New SMS Template\".", LOG_TO_STD_OUT);
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_notif;
		String resourceFile = jsonFileName_notif;

		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		
		setupNotification = new JSONNotifications(resourcePath, resourceFile);

		notificationForm= new NotificationsForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
			JsonCurrentElement current=setupNotification.getCurrentElementById(2);
			if(current.getEnabled() == true)
			{
				Boolean status=false;
				notificationForm.open().addNotification();
				notificationForm.setChannelType(setupNotification.getNotificationType());
				notificationForm.setLanguage(setupNotification.getNotificationLanguage()).channelOKButton();
				notificationForm.setMailObject(setupNotification.getMailObject());
				Path dir_path=Paths.get(DEFAULT_RESOURCE_FOLDER_ROOT+"input/administrationmanager/notifications/");
				List<String> lis=new ArrayList<String>();
				lis.add(setupNotification.getContentFile());
					setupNotification.getMailNotificationFile(dir_path,lis, setupNotification.getTemplateName(),TempFileExtension.HTML);
				notificationForm.uploadContentFile("/tmp/sampleMAIL.html").clickImportButton();
				notificationForm.confirmDialog();
				notificationForm.saveTemplate();
				notificationForm.setTemplateName(setupNotification.getTemplateName()).templateOKButton();
				status=notificationForm.isTemplateInList(setupNotification.getTemplateName());
				if(status==true)
				{
					Assert.assertTrue(status);
					Reporter.log("Mail Template Created Succesfully!");
				}
				else
				{
					Assert.fail("Mail Template creation Failed!");
					Reporter.log("Creation of Mail Template Failed!");
				}
			}
			
		}
	
	@Test(enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT,priority=3)
	@Parameters({"jsonFilePath_notif","jsonFileName_notif"})
	public void testUc10_04DeleteExistingTemplate(@Optional("input/administrationmanager/notifications/") String jsonFilePath_notif, @Optional("newNotification") String jsonFileName_notif) throws JSONSException
	{
		JSONNotifications setupNotification=null;
		NotificationsForm notificationForm=null;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Reporter.log("Creation of \"New SMS Template\".", LOG_TO_STD_OUT);
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_notif;
		String resourceFile = jsonFileName_notif;

		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		
		setupNotification = new JSONNotifications(resourcePath, resourceFile);

		notificationForm= new NotificationsForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
		for(int i=0 ; i<setupNotification.getList().size(); i++)
		{
			JsonCurrentElement current=setupNotification.getCurrentElementById(i);
			if(current.getDelete() == true)
			{
				Boolean status=true;
				
				try{
					notificationForm.open();
					notificationForm.deleteNotifications(setupNotification.getTemplateName());
				}catch(FormException e)
				{
					Assert.fail("Mail Template deletion Failed!");
					Reporter.log("Deletion of Mail Template Failed!");
					status=false;
				}
				if(status==true)
				{
					Assert.assertTrue(status);
					Reporter.log("Mail Template Deleted Succesfully!");
				}
				else
				{
					
				}
			}
		}
			
		}
		
}
