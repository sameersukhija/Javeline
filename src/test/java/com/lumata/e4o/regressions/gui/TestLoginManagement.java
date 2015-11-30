package com.lumata.e4o.regressions.gui;

import org.testng.AssertJUnit;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.internal.matchers.Each;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;
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
import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.AdministrationForm;
import com.lumata.e4o.gui.administrationmanager.AdministrationManagementConfigureForm;
import com.lumata.e4o.gui.administrationmanager.LoginManagementAgencyForm;
import com.lumata.e4o.gui.administrationmanager.LoginManagementGroupForm;
import com.lumata.e4o.gui.administrationmanager.LoginManagementUserForm;
import com.lumata.e4o.gui.customercare.CustomerCareForm.Tab;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.json.gui.administrationmanager.JSONLMAgency;
import com.lumata.e4o.json.gui.administrationmanager.JSONLMGroup;
import com.lumata.e4o.json.gui.administrationmanager.JSONLMUser;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONPricesElement;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;
@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
	)
@TCSeleniumWebDriver
public class TestLoginManagement extends ParentTestCase{
	private JSONLMUser setupLM=null;
	private JSONLMAgency setupLMA=null;
	private String groupName;
	private JSONLMGroup setupLMG;
	private Object ctabs;
	private LoginManagementGroupForm setAddCampaignsTab;
	private JSONArray jsonObject;
	private String TabName;
	@BeforeMethod
	public void initLMForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	    
	
	@Parameters({"jsonFilePath_LMA","jsonFileName_LMA"})
	@Test(enabled = TEST_ENABLED, priority = 9 )
	public void testUc03_01addAgency_LoginManagementForm(@Optional("input/administrationmanager/loginmanagement") String jsonFilePath_LMA,
			@Optional("Agency") String jsonFileName_LMA) throws JSONSException, FormException, JSONException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_LMA;
		String resourceFile = jsonFileName_LMA;
			
		JSONLMAgency jsonLMAgency = new JSONLMAgency( resourcePath,resourceFile );
		JSONLMAgency setupLMA = new JSONLMAgency(resourcePath,resourceFile);
		LoginManagementAgencyForm loginManagementAgencyForm = new LoginManagementAgencyForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT  );
		loginManagementAgencyForm.open();
		JSONArray LoginFormA = jsonLMAgency.getList();
		
		for( int LoginFormIndex = 0; LoginFormIndex < LoginFormA.length(); LoginFormIndex++ ) {
			
			setupLMA.setAgencyById(LoginFormIndex);
			
			if( setupLMA.getEnabled() ) {
				loginManagementAgencyForm.clickAddButton();
				loginManagementAgencyForm.setAgencyName(setupLMA.getName());
				loginManagementAgencyForm.setAgencyAddress(setupLMA.getaddress());
				loginManagementAgencyForm.setAgencyPhone(setupLMA.getphone());
				loginManagementAgencyForm.ClickSaveAgency();
				loginManagementAgencyForm.clickRefreshButton();
				
				Boolean status = loginManagementAgencyForm.isAgencyinList(setupLMA.getName());
				System.out.println(status);
				if(status==true)
				{
					AssertJUnit.assertTrue(status);
					Reporter.log("Agency Created Succesfully!");
					
				}
				else
				{
					
					AssertJUnit.fail("The Agency creation Failed!");
					Reporter.log("Creation of Agency Failed!");
				}
	}

		}
	}
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Parameters({"jsonFilePath_LMG","jsonFileName_LMG"})
	@Test(enabled = TEST_ENABLED, priority = 2 )
	public void testUc01_01addgroups_LoginManagementForm(@Optional("input/administrationmanager/loginmanagement") String jsonFilePath_LMG,
			@Optional("groups") String jsonFileName_LMG ) throws JSONSException, FormException, JSONException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_LMG;
		String resourceFile = jsonFileName_LMG;
		JSONLMGroup setupLMG=null;
		
		JSONLMGroup jsonLMGroup = new JSONLMGroup( resourcePath,resourceFile );
		setupLMG=new JSONLMGroup(resourcePath,resourceFile);
		LoginManagementGroupForm loginManagementGroupForm = new LoginManagementGroupForm(seleniumWebDriver, setupLMG, TIMEOUT, ATTEMPT_TIMEOUT  );
		loginManagementGroupForm.open();
		JSONArray LoginFormG = jsonLMGroup.getList();
		
		for( int LoginFormIndex = 0; LoginFormIndex < LoginFormG.length(); LoginFormIndex++ ) {
			
			setupLMG.setGroupById(LoginFormIndex);
			
			if( setupLMG.getEnabled() ) {
				loginManagementGroupForm.clickAddButton();
				loginManagementGroupForm.setGroupName(setupLMG.getName());
				loginManagementGroupForm.setGroupIsRemovable(setupLMG.getIsRemovable());
				System.out.println("ISREMOVABLE"+setupLMG.getIsRemovable());
				loginManagementGroupForm.setGroupHasAgencies(setupLMG.getHasAgencies());
				
				loginManagementGroupForm.setGroupUserAddition(setupLMG.getCanUsersBeAdded());
				loginManagementGroupForm.setGroupUserselectedinList(setupLMG.getCanBeSelectedInGroupList());
				 int ctab=setupLMG.getTabs().size();
				 for (int i=1; i<ctab; i++ ){
					  loginManagementGroupForm.ClickTabsButton();
				  for ( String ctabs : setupLMG.getTabs()) {
					  	 loginManagementGroupForm.ClickTabsButton();
					  		  
						  loginManagementGroupForm.setAddCampaignsTab(ctabs);
						  break;
			      }
			
					}
				loginManagementGroupForm.ClickSaveGroup().closeAlertAndGetItsText();
				loginManagementGroupForm.clickRefreshButton();
				Boolean status = loginManagementGroupForm.isGroupinList(setupLMG.getName());
				System.out.println(status);
				if(status==true)
				{
					AssertJUnit.assertTrue(status);
					Reporter.log("Group Created Succesfully!");
					
				}
				else
				{
					
					AssertJUnit.fail("The Group creation Failed!");
					Reporter.log("Creation of Group Failed!");
				}
	}
		
		}
	}
		

			
	@Test(enabled = TEST_ENABLED, priority = 3 )
	public void testUc01_02editgroups_LoginManagementForm() throws JSONSException, FormException, JSONException {
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				LoginManagementGroupForm loginManagementGroupForm = new LoginManagementGroupForm( seleniumWebDriver, setupLMG, TIMEOUT, ATTEMPT_TIMEOUT  );
				loginManagementGroupForm.open();
				loginManagementGroupForm.editGroup("administration");
				loginManagementGroupForm.setGroupName("superadmin");
				loginManagementGroupForm.setGroupIsRemovable(false);
				loginManagementGroupForm.setGroupUserAddition(true);
				
				loginManagementGroupForm.ClickSaveGroup().closeAlertAndGetItsText();
				loginManagementGroupForm.clickRefreshButton();
				Boolean status = loginManagementGroupForm.isGroupinList("superadmin");
				
				System.out.println(status);
				if(status==true)
				{
					AssertJUnit.assertTrue(status);
					Reporter.log("Group Updated Succesfully!");
					
				}
				else
				{
					
					AssertJUnit.fail("The Group updation Failed!");
					Reporter.log("Updation of Group Failed!");
				}
	}

		
	@Test(enabled = TEST_ENABLED, priority = 4 )
	public void testUc01_03Deletegroup_LoginManagementForm() throws JSONSException, FormException, JSONException {
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				LoginManagementGroupForm loginManagementGroupForm = new LoginManagementGroupForm( seleniumWebDriver, setupLMG, TIMEOUT, ATTEMPT_TIMEOUT  );
				loginManagementGroupForm.open();
				loginManagementGroupForm.deleteGroup("superadmin").closeAlertAndGetItsText();;
				loginManagementGroupForm.clickRefreshButton();
				Boolean status = loginManagementGroupForm.isGroupinList("superadmin");
				System.out.println(status);
				if(status==false)
				{
					Assert.assertFalse(status);
					Reporter.log("Group Deleted Succesfully!");
					
				}
				else
				{
					
					AssertJUnit.fail("The Group Deletion Failed!");
					Reporter.log("Deletion of Group Failed!");
				}
	}

		
	
	
	@Parameters({"jsonFilePath_LM","jsonFileName_LM"})
	@Test(enabled = TEST_ENABLED, priority = 5 )
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
				loginManagementUserForm.ClickTabsButton();
				loginManagementUserForm.setAddLoginManagementGroup(setupLM.getgroupname());
				loginManagementUserForm.setAgencyName(setupLM.getAgencyName());
				
				
				loginManagementUserForm.setAddUserPermission(setupLM.getpermission());
				loginManagementUserForm.ClickSaveUser().closeAlertAndGetItsText();
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
	
	
	
	@Test(enabled = TEST_ENABLED, priority = 6 )
	public void testUc02_02Edituser_LoginManagementForm() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		LoginManagementUserForm loginManagementUserForm = new LoginManagementUserForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT  );
		loginManagementUserForm.open();
		loginManagementUserForm.clickEditButton("Rajesh");
		
		loginManagementUserForm.setUseremail("Rajesh.ksingh@yahoo.com");
		loginManagementUserForm.setUserpassword("raj");
		loginManagementUserForm.setUserconfirmpassword("raj");
		loginManagementUserForm.setAddLoginManagementGroup("loginmanagement");
		loginManagementUserForm.setAddUserPermission( "Manager");
		loginManagementUserForm.ClickSaveUser().closeAlertAndGetItsText();
		loginManagementUserForm.clickRefreshButton();
		Boolean status = loginManagementUserForm.isUserinList("Rajesh");
		System.out.println(status);
		if(status==true)
				{
					AssertJUnit.assertTrue(status);
					Reporter.log("User Modified Succesfully!");
					
				}
		else
				{
					
					AssertJUnit.fail("The User ModificationFailed!");
					Reporter.log("Modification of User Failed!");
				}
	}


	@Test(enabled = TEST_ENABLED, priority = 7 )
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
		loginManagementUserForm.ClickSaveUser().closeAlertAndGetItsText();
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


	@Test(enabled = TEST_ENABLED, priority = 8 )
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
