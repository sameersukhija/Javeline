package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners({
	@TCOwner( name="Arcangelo Di Pasquale", email="arcangelo.dipasquale@lumatagroup.com" ),
	@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
})
@TCSeleniumWebDriver
public class TestCampaignModelForm extends ParentTestCase {
	
	private final boolean TEST_ENABLED = false;
	
	private JSONCampaignModel campaignModel=null;
	
	@BeforeMethod
	public void initCampaignsForm( Method method ) throws NetworkEnvironmentException, FormException {		
		
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	private void initTest() {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log( Log.CREATING.createMessage( "Creation of \"Campaign Model Form\"." ), LOG_TO_STD_OUT);
	}
	
	private JSONCampaignModel getCampaignModel( String jsonFilePath, String jsonFileName, Integer campaignModelIndex ) throws JSONSException {
		
		initTest();
		
		if( null != jsonFilePath && null != jsonFileName ) {
			
			String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath;
			String resourceFile = jsonFileName;
			
			Reporter.log( Log.LOADING.createMessage( "\"Campaign Model \" is filled with resource file : " ), LOG_TO_STD_OUT);
			Reporter.log( Log.LOADING.createMessage( "Resource path -> " + resourcePath ), LOG_TO_STD_OUT);
			Reporter.log( Log.LOADING.createMessage( "Resource file -> " + resourceFile ), LOG_TO_STD_OUT);
			
			campaignModel = new JSONCampaignModel(resourcePath, resourceFile);
			campaignModel.setCampaignModelById( campaignModelIndex );
			
			Reporter.log( Log.LOADING.createMessage( "Loading Campaign Model ( " + campaignModel.getName() + " )" ), LOG_TO_STD_OUT);
			
			return campaignModel;
		}
		
		return null;
		
	}
	
	private void checkResult(Boolean status) {
		String message = "Campaign Model created successfully";
		if( !status ) { message = "Campaign Model didn't create successfully"; }
		Reporter.log( Log.CHECKING.createMessage( message ),LOG_TO_STD_OUT);
		Assert.assertTrue(status, message);
	}
	
	@Parameters({"jsonFilePath","jsonFileName"})
	@Test( enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT, priority = 1 )
	public void testUc33_01CampaignModelCreation( @Optional("input/campaignmanager/campaignModels") String jsonFilePath, @Optional("newCampaignModel") String jsonFileName) throws FormException, JSONSException {
		
		Integer campaignModelIndex = 0;
		
		campaignModel = getCampaignModel( jsonFilePath, jsonFileName, campaignModelIndex );
		
		if( null != campaignModel && campaignModel.getEnabled() && !campaignModel.getEdit() ) {
						
			CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
			
			campaignModelForm.
				openForm().
				addBtn().
				addGeneralInfo(campaignModel).
				addEvents(campaignModel.getEvents()).
				saveBtn().
				abortDialog();
			
			checkResult(campaignModelForm.isCampaignModelInList(campaignModel.getName()));
			
		}
	
	}
	
	@Parameters({"jsonFilePath","jsonFileName"})	
	@Test( enabled=TEST_ENABLED, priority = 2 )
	public void testUc33_02CampaignModelMultipleEvents_Criteria_Action( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath, @Optional("newCampaignModel") String jsonFileName) throws FormException, JSONSException {
		
		Integer campaignModelIndex = 1;
		
		campaignModel = getCampaignModel( jsonFilePath, jsonFileName, campaignModelIndex );
		
		if( null != campaignModel && campaignModel.getEnabled() ) {
						
			CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
			
			campaignModelForm.
				openForm().
				addBtn().
				addGeneralInfo(campaignModel).
				addEvents(campaignModel.getEvents()).
				saveBtn().
				abortDialog();
			
			checkResult(campaignModelForm.isCampaignModelInList(campaignModel.getName()));
			
		}

	}

	@Parameters({"jsonFilePath","jsonFileName"})	
	@Test( enabled=TEST_ENABLED, priority = 3 )
	public void testUc33_03EditCampaignModel( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath, @Optional("newCampaignModel") String jsonFileName ) throws FormException, JSONSException {
		
		Integer campaignModelIndex = 2;
		
		campaignModel = getCampaignModel( jsonFilePath, jsonFileName, campaignModelIndex );
		
		if( null != campaignModel && campaignModel.getEnabled() && campaignModel.getEdit() ) {
						
			CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
			
			if( campaignModelForm.openForm().isCampaignModelInList(campaignModel.getName()) ) {
				
				campaignModelForm.
					editBtn(campaignModel.getName()).
					addGeneralInfo(campaignModel).
					addEvents(campaignModel.getEvents()).
					saveEditBtn().
					abortDialog();
					
				checkResult(campaignModelForm.isCampaignModelInList(campaignModel.getName()));
			
			} else {
				
				checkResult(false);
			
			}
			
		}

	}
	
	@Parameters({"jsonFilePath","jsonFileName"})
	@Test( enabled=TEST_ENABLED, priority = 4 )
	public void testUc33_04CampaignModelDelete( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath, @Optional("newCampaignModel") String jsonFileName ) throws FormException, JSONSException {
		
		Integer campaignModelIndex = 3;
		
		campaignModel = getCampaignModel( jsonFilePath, jsonFileName, campaignModelIndex );
		
		if( null != campaignModel && campaignModel.getEnabled() && campaignModel.getDelete() ) {
			
			CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
			
			if( campaignModelForm.openForm().isCampaignModelInList(campaignModel.getName()) ) {
				
				campaignModelForm.
					deleteBtn(campaignModel.getName()).
					confirmDialog();
					
				checkResult(!campaignModelForm.isCampaignModelInList(campaignModel.getName()));
			
			} else {
				
				checkResult(false);
			
			}
			
		}
		
	}
		
	@Parameters({"jsonFilePath","jsonFileName"})
	@Test( enabled=TEST_ENABLED, priority = 5 )
	public void testUc33_05CopyCampaignModel( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath, @Optional("newCampaignModel") String jsonFileName ) throws FormException, JSONSException {
		
		Integer campaignModelIndex = 4;
		
		campaignModel = getCampaignModel( jsonFilePath, jsonFileName, campaignModelIndex );
		
		if( null != campaignModel && campaignModel.getEnabled() && campaignModel.getCopy() ) {
			
			CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
			
			if( campaignModelForm.openForm().isCampaignModelInList(campaignModel.getName()) ) {
				
				campaignModelForm.
					copyBtn(campaignModel.getName()).
					addEvents(campaignModel.getEvents()).
					saveCopyBtn().
					confirmDialog();
					
				checkResult(campaignModelForm.isCampaignModelInList(campaignModel.getName()+ " (Copy Model)"));
			
			} else {
				
				checkResult(false);
			
			}
			
		}
		
	}
	
}
