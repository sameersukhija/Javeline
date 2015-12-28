package com.lumata.e4o.regressions.gui;

import org.testng.AssertJUnit;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.LoginManagementUserForm;
import com.lumata.e4o.json.gui.administrationmanager.JSONLMUser;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
	)
@TCSeleniumWebDriver
public class TestLoginManagementUser extends ParentTestCase{
	private JSONLMUser setupLM=null;
	@BeforeMethod
	public void initLMForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	@Parameters({"jsonFilePath_LM","jsonFileName_LM"})
	@Test(enabled = TEST_ENABLED, priority = 1 )
	public void testUc02_01adduser_LoginManagementForm(@Optional("input/administrationmanager/loginmanagement") String jsonFilePath_LM,
			@Optional("User") String jsonFileName_LM) throws JSONSException, FormException, JSONException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_LM;
		String resourceFile = jsonFileName_LM;
			
		JSONLMUser jsonLMUser = new JSONLMUser( resourcePath,resourceFile );
		setupLM=new JSONLMUser(resourcePath,resourceFile);
		LoginManagementUserForm loginManagementUserForm = new LoginManagementUserForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT  );
		loginManagementUserForm.open();
		JSONArray LoginForm = jsonLMUser.getList();
		
		for( int LoginFormIndex = 0; LoginFormIndex < LoginForm.length(); LoginFormIndex++ ) {
			
			setupLM.setUserById(LoginFormIndex);
			
			if( setupLM.getEnabled() ) {
				loginManagementUserForm.clickAddButton();
				loginManagementUserForm.setUserName(setupLM.getName());
				loginManagementUserForm.setUseremail(setupLM.getemail());
				loginManagementUserForm.setUserpassword(setupLM.getpassword());
				loginManagementUserForm.setUserconfirmpassword(setupLM.getconfirmpassword());
				loginManagementUserForm.clickTabsButton();
				loginManagementUserForm.setAddLoginManagementGroup(setupLM.getgroupname());
				loginManagementUserForm.setAgencyName(setupLM.getAgencyName());
				
				
				loginManagementUserForm.setAddUserPermission(setupLM.getpermission());
				loginManagementUserForm.clickSaveUser().closeAlertAndGetItsText();
				loginManagementUserForm.clickRefreshButton();
				Boolean status = loginManagementUserForm.isUserinList(setupLM.getName());
				System.out.println(status);
				if(status==true)
				{
					AssertJUnit.assertTrue(status);
					Reporter.log("User Created Succesfully!");
					
				}
				else
				{
					
					AssertJUnit.fail("The User creation Failed!");
					Reporter.log("Creation of User Failed!");
				}
	}

		}
	}
	
	
	
	@Test(enabled = TEST_ENABLED, priority = 2 )
	public void testUc02_02Edituser_LoginManagementForm() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		LoginManagementUserForm loginManagementUserForm = new LoginManagementUserForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT  );
		loginManagementUserForm.open();
		loginManagementUserForm.clickEditButton("Rajesh");
		
		loginManagementUserForm.setUseremail("Rajesh.ksingh@yahoo.com");
		loginManagementUserForm.setUserpassword("raj");
		loginManagementUserForm.setUserconfirmpassword("raj");
		loginManagementUserForm.setAddLoginManagementGroup("loginmanagement");
		loginManagementUserForm.setAddUserPermission( "Manager");
		loginManagementUserForm.clickSaveUser().closeAlertAndGetItsText();
		loginManagementUserForm.open();
		loginManagementUserForm.clickEditButton("Rajesh");
		Boolean status = loginManagementUserForm.isGroupPermissionModified("Manager");
		loginManagementUserForm.clickCancel();
		System.out.println(status);
		if(status==true)
				{
					AssertJUnit.assertTrue(status);
					Reporter.log("User Permission Modified Succesfully!");
					
				}
		else
				{
					
					AssertJUnit.fail("The User Permission Modification Failed!");
					Reporter.log("Modification of User Permission Failed!");
				}
	}


	@Test(enabled = TEST_ENABLED, priority = 3 )
	public void testUc02_03Copyuser_LoginManagementForm() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		LoginManagementUserForm loginManagementUserForm = new LoginManagementUserForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT  );
		loginManagementUserForm.open();
		loginManagementUserForm.clickCopyButton("Rajesh");
		loginManagementUserForm.setUseremail("Rajesh.ksingh@yahoo.com");
		loginManagementUserForm.setUserpassword("rajesh");
		loginManagementUserForm.setUserconfirmpassword("rajesh");
		loginManagementUserForm.setAddLoginManagementGroup("loginmanagement");
		loginManagementUserForm.setAddUserPermission( "Manager");
		loginManagementUserForm.clickSaveUser().closeAlertAndGetItsText();
		loginManagementUserForm.clickRefreshButton();
		Boolean status = loginManagementUserForm.isUserinList("Rajesh (Copy)");
		System.out.println(status);
		if(status==true)
				{
					AssertJUnit.assertTrue(status);
					Reporter.log("Copy of User created Succesfully!");
					
				}
		else
				{
					
					AssertJUnit.fail("The User Copy Failed!");
					Reporter.log("Copy of User Failed!");
				}
	}


	@Test(enabled = TEST_ENABLED, priority = 4 )
	public void testUc02_04DeleteUser_LoginManagementForm() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		LoginManagementUserForm loginManagementUserForm = new LoginManagementUserForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT  );
		loginManagementUserForm.open();
		loginManagementUserForm.clickDeleteButton("Rajesh (Copy)");
		loginManagementUserForm.closeAlertAndGetItsText();
		loginManagementUserForm.clickRefreshButton();
		Boolean status = loginManagementUserForm.isUserinList("Rajesh (Copy)");
		System.out.println(status);
		if(status==false)
				{
					AssertJUnit.assertFalse(status);
					Reporter.log("User deleted Succesfully!");
					
				}
		else
				{
					
					AssertJUnit.fail("The User deletion Failed!");
					Reporter.log("User deletion Failed!");
				}
	}
}
