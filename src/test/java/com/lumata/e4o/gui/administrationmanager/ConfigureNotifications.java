package com.lumata.e4o.gui.administrationmanager;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.json.gui.administrationmanager.JSONNotifications;

public class ConfigureNotifications extends ParentUITestCase {

	/**
	 * Notifications configuration file
	 */
	private JSONNotifications setupNotifications = null;
	
	/**
	 * Notifications Form
	 */
	private NotificationsForm notificationsForm = null;
	

	@Parameters({"notificationsFile"})
	@Test(groups = { "configureNotifications" })
	public void configureNotificationsTest(@Optional("notificationsTemplate") String notificationsFile) throws JSONSException, FormException {

		Reporter.log("Creation of \"Notifications Form\".", PRINT2STDOUT__);

		String resourcePath = currentResourceStartPath + "/administrationmanager/notifications";
		String resourceFile = notificationsFile;

		Reporter.log("\"Notifications Form\" is filled with reosurce file : ", PRINT2STDOUT__);
		Reporter.log("Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log("Resource file -> " + resourceFile, PRINT2STDOUT__);

		setupNotifications = new JSONNotifications( resourcePath, resourceFile);

		notificationsForm = new NotificationsForm( seleniumWebDriver, setupNotifications, TIMEOUT, ATTEMPT_TIMEOUT);

		Reporter.log("Open \"Notifications Form\" UI.", PRINT2STDOUT__);

		notificationsForm.open();

		Reporter.log("Apply setup on UI.", PRINT2STDOUT__);

		notificationsForm.addNotificationsList();

		Reporter.log("Check general status of form", PRINT2STDOUT__);

		Assert.assertTrue( notificationsForm.navigate(), "Status error during configuration!");
	}	
	
	@Parameters({"cleanup"})
	@Test(groups = { "configureNotifications" }, dependsOnMethods = {"configureNotificationsTest"})
	public void cleanupNotificationsForm(@Optional("TRUE") Boolean cleanup) throws FormException, JSONSException {
		
		try {
			Reporter.log( "Cleanup \"Notifications\" created.", PRINT2STDOUT__);
			
			if ( notificationsForm != null && cleanup ) {

				Reporter.log( "Cleanup \"Notifications\" form.", PRINT2STDOUT__);
			
				int numbComm = setupNotifications.getList().size();
				
				List<String> names = new ArrayList<>();
				
				for (int index = 0; index < numbComm; index++) {
					
					setupNotifications.setCurrentElementById(index);
					
					JsonCurrentElement current = setupNotifications.getCurrentElement();
					
					if ( current.getDelete() )
						names.add(current.getStringFromPath("templateName"));
				}
				
				String[] commodities2BeDelete = names.toArray(new String[0]);
				
				Assert.assertTrue( 	notificationsForm.open().deleteNotifications(commodities2BeDelete),
						"Error during \"Notifications\" cleanup!");
			}
			else
				Reporter.log( "Leave configured \"Notifications\" form.", PRINT2STDOUT__);	
		}
		catch ( FormException e ) {
			
			Assert.assertTrue(false, "Error on forms cleanup : " + e.getMessage());
		}
	}		
}
