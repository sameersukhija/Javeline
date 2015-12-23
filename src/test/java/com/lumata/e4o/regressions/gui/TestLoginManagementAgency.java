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
import com.lumata.e4o.gui.administrationmanager.LoginManagementAgencyForm;
import com.lumata.e4o.json.gui.administrationmanager.JSONLMAgency;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;
@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
	)
@TCSeleniumWebDriver
public class TestLoginManagementAgency extends ParentTestCase{
	private JSONLMAgency setupLMA=null;
	
	@BeforeMethod
	public void initLMForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	    
	
	@Parameters({"jsonFilePath_LMA","jsonFileName_LMA"})
	@Test(enabled = TEST_ENABLED, priority = 1 )
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
	
	
}
	
	
	
