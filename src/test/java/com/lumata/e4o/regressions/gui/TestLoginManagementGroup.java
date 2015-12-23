package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.LoginManagementGroupForm;
import com.lumata.e4o.json.gui.administrationmanager.JSONLMGroup;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
	)
@TCSeleniumWebDriver
public class TestLoginManagementGroup extends ParentTestCase{
	private String groupName;
	private JSONLMGroup setupLMG;
	private Object ctabs;
	private LoginManagementGroupForm setAddCampaignsTab;
	private JSONArray jsonObject;
	private String TabName;
	private int index=0;
	
	@BeforeMethod
	public void initLMForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	@Parameters({"jsonFilePath_LMG","jsonFileName_LMG"})
	@Test(enabled = TEST_ENABLED, priority = 1 )
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
				 for (int i=0; i<ctab; i++ ){
					 if(setupLMG.getEdit())
						 setTabIndex(i);
					  loginManagementGroupForm.ClickTabsButton();
					  loginManagementGroupForm.setTabName(setupLMG.getTabs().get(i),i+1);
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
		

	@Parameters({"jsonFilePath_LMG","jsonFileName_LMG"})		
	@Test(enabled = TEST_ENABLED, priority = 2 )
	public void testUc01_02editgroups_LoginManagementForm(@Optional("input/administrationmanager/loginmanagement") String jsonFilePath_LMG,
			@Optional("groups") String jsonFileName_LMG) throws JSONSException, FormException, JSONException {
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_LMG;
				String resourceFile = jsonFileName_LMG;
				JSONLMGroup setupLMG=null;
				
				JSONLMGroup jsonLMGroup = new JSONLMGroup( resourcePath,resourceFile );
				setupLMG=new JSONLMGroup(resourcePath,resourceFile);
				
				LoginManagementGroupForm loginManagementGroupForm = new LoginManagementGroupForm( seleniumWebDriver, setupLMG, TIMEOUT, ATTEMPT_TIMEOUT  );
				JSONArray LoginFormG = jsonLMGroup.getList();
				
				for( int LoginFormIndex = 0; LoginFormIndex < LoginFormG.length(); LoginFormIndex++ ) {
					
					setupLMG.setGroupById(LoginFormIndex);
					
					if( setupLMG.getEdit() ) {
						loginManagementGroupForm.open();
						loginManagementGroupForm.editGroup(setupLMG.getName());
						loginManagementGroupForm.setGroupName("superadmin");
						loginManagementGroupForm.setGroupIsRemovable(false);
						loginManagementGroupForm.setGroupUserAddition(true);
						loginManagementGroupForm.ClickTabsButton();
						loginManagementGroupForm.setTabName("CustomerCare", getTabIndex()+2);
						loginManagementGroupForm.ClickTabsButton();
						loginManagementGroupForm.setTabName("CustomerCare_Token", getTabIndex()+3);
						loginManagementGroupForm.ClickSaveGroup().closeAlertAndGetItsText();
						loginManagementGroupForm.clickRefreshButton();
					}
				}
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

		
	@Test(enabled = TEST_ENABLED, priority = 3 )
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

		
	
	

	public int getTabIndex()
	{
		return this.index;
	}
	public void setTabIndex(int index)
	{
		this.index=index;
	}
}
