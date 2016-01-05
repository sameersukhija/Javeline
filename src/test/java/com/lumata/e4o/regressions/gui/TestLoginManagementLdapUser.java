package com.lumata.e4o.regressions.gui;

import org.testng.AssertJUnit;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.AdministrationManagementConfigureForm;
import com.lumata.e4o.gui.administrationmanager.LoginManagementUserForm;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;
@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
	)
@TCSeleniumWebDriver
public class TestLoginManagementLdapUser extends ParentTestCase{
	@BeforeMethod
	public void initLMForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	    
		
	
	@Test(enabled = TEST_ENABLED, priority = 1 )
	public void testUc40_01ConfigureLdap_LoginManagementForm() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);

		Authorization authorizationForm = new Authorization( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT  );
		
		AdministrationManagementConfigureForm administrationManagementConfigureForm = new AdministrationManagementConfigureForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT  );
		administrationManagementConfigureForm.open();
		administrationManagementConfigureForm.clickLdapSectionbutton("Section : ldap");
		administrationManagementConfigureForm.clickeditLdapconfigbutton("LDAP_use");
		administrationManagementConfigureForm.clickEditpropertyButton();
		administrationManagementConfigureForm.setLdapValue("true");
		administrationManagementConfigureForm.ClickSaveLdapProperty().closeAlertAndGetItsText();
		administrationManagementConfigureForm.closeAlertAndGetItsText();
		administrationManagementConfigureForm.clickeditLdapconfigbutton("LDAP_authorize_pwd_use");
		administrationManagementConfigureForm.clickEditpropertyButton();
		administrationManagementConfigureForm.setLdapValue("true");
		administrationManagementConfigureForm.ClickSaveLdapProperty().closeAlertAndGetItsText();
		administrationManagementConfigureForm.closeAlertAndGetItsText();
		
		LoginManagementUserForm loginManagementUserForm = new LoginManagementUserForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT  );
		loginManagementUserForm.open();
		loginManagementUserForm.clickEditButton("Rajesh");
		
		loginManagementUserForm.setUseremail("Rajesh.ksingh@yahoo.com");
		loginManagementUserForm.setUserpassword("super2010Man");
		loginManagementUserForm.setUserconfirmpassword("super2010Man");
		loginManagementUserForm.setAddLoginManagementGroup("loginmanagement");
		loginManagementUserForm.setAddUserPermission( "Manager");
		loginManagementUserForm.clickSaveUser().closeAlertAndGetItsText();
		loginManagementUserForm.clickRefreshButton();
		Boolean status = loginManagementUserForm.isUserinList("Rajesh");
		System.out.println(status);
		if(status==true)
				{
					AssertJUnit.assertTrue(status);
					Reporter.log("User Modified Succesfully after applying Ldap configuration!");
					
				}
		else
				{
					
					AssertJUnit.fail("The User Modification Failed! after applying Ldap configuration");
					Reporter.log("Modification of User Failed after applying Ldap configuration!");
				}
		authorizationForm.quit();
	}

	
	
	@Test(enabled = TEST_ENABLED, priority = 2 )
	
	public void testUc40_02LoginLdapUser_agency_LoginManagementForm() throws FormException {
	
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Authorization authorizationForm = new Authorization( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT  );
		authorizationForm.logout();
		authorizationForm.loginwithAgency("Rajesh", "super2010Man", "PBS_SAMEER");
		LoginManagementUserForm loginManagementUserForm = new LoginManagementUserForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT  );
		
		Boolean status = loginManagementUserForm.isUserloggedin("Rajesh");
		if(status==true)
		{
			AssertJUnit.assertTrue(status);
			Reporter.log("Ldap User belonging to Agency succesfully logged in!");
			
		}
		else
		{
			
			AssertJUnit.fail("Ldap User belonging to Agency failed to log in");
			Reporter.log("Ldap User belonging to Agency failed to log in!");
		}
	
		
		loginManagementUserForm.quit();
	}
}
	
	
	
