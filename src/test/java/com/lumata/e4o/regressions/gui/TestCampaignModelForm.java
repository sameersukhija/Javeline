package com.lumata.e4o.regressions.gui;

import org.testng.Assert;
import org.testng.AssertJUnit;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.AssertJUnit;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.gui.catalogmanager.SuppliersForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.campaignmanager.JSONCriteria;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.catalogmanager.JSONSuppliers;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@Test
@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
	)
@TCSeleniumWebDriver

	public class TestCampaignModelForm extends ParentTestCase{
	private static final JSONCriteria JSONCriteria = null;
	private JSONCampaignModel campaignModel=null;
	private String campModelName=null;
	private String campModelCopyName=null;
	@BeforeMethod
	public void initCampaignModelForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	@Parameters({"jsonFilePath","jsonFileName"})
	
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void testUc33_01CampaignModelCreation( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath, @Optional("newCampaignModel") String jsonFileName) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath;
		String resourceFile = jsonFileName;

		Reporter.log("\"Campaign Model \" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		campaignModel=new JSONCampaignModel(resourcePath, resourceFile);
		CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
		
		campaignModelForm.open();
		JSONArray campaignModels = campaignModel.getList();
		
		for( int camapignModelIndex = 0; camapignModelIndex < campaignModels.length(); camapignModelIndex++ ) {
			
			campaignModel.setCampaignModelById( camapignModelIndex );
			
			if( campaignModel.getEnabled() ) {
			
				campaignModelForm.campaignModelAddButton();
				campModelName="CSM_14";
				
				campaignModelForm.configureCampaignModel(campModelName, campaignModel.getDescription(), campaignModel.getcampaignType(), campaignModel.getuseHierarchy());
				Map<String, JSONEvent_> events = campaignModel.getEvents();
				campaignModelForm.addEvents(events);
				campaignModelForm.saveCampaignModel();
				campaignModelForm.confirmCampaignModelAlert(status);
				status=campaignModelForm.isCampaignModelNameInList(campModelName);
				if(status==true)
				{

					AssertJUnit.assertTrue("Campaign Model created successfully", true);
					Reporter.log("Campaign Model created successfully",LOG_TO_STD_OUT);
				}
				else
				{
					Assert.fail("campaign model didn't create successfully");
					Reporter.log("Campaign Model didn't create successfully", LOG_TO_STD_OUT);
				}
				
			}
		}
	}
	
	
	@Parameters({"jsonFilePath","jsonFileName"})
	
	@Test( enabled=TEST_ENABLED, priority = 2 )
	public void testUc33_02CampaignModelMultipleEvents_Criteria_Action( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath, @Optional("newCampaignModel") String jsonFileName) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath;
		String resourceFile = jsonFileName;

		Reporter.log("\"Campaign Model \" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		campaignModel=new JSONCampaignModel(resourcePath, resourceFile);
		CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
		
		campaignModelForm.open();
		JSONArray campaignModels = campaignModel.getList();
		
		for( int camapignModelIndex = 0; camapignModelIndex < campaignModels.length(); camapignModelIndex++ ) {
			
			campaignModel.setCampaignModelById( camapignModelIndex );
			
			if( campaignModel.getEnabled() ) {
			
				campaignModelForm.campaignModelAddButton();
				campModelName="CMS_09";
				campaignModelForm.configureCampaignModel(campModelName, campaignModel.getDescription(), campaignModel.getcampaignType(), campaignModel.getuseHierarchy());
				Map<String, JSONEvent_> events = campaignModel.getEvents();
				
				campaignModelForm.addEvents(events);
				campaignModelForm.addEvents2(events);
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				campaignModelForm.saveCampaignModel();
				campaignModelForm.confirmCampaignModelAlert(status);
				status=campaignModelForm.isCampaignModelNameInList(campModelName);
				if(status==true)
				{

					AssertJUnit.assertTrue("Campaign Model created successfully", true);
					Reporter.log("Campaign Model created successfully",LOG_TO_STD_OUT);
				    
				}
				else
				{
					Assert.fail("campaign model didn't create successfully");
					Reporter.log("Campaign Model didn't create successfully", LOG_TO_STD_OUT);
				}
				
			}
		}
	}


	@Parameters({"jsonFilePath","jsonFileName"})
	
	@Test( enabled=TEST_ENABLED, priority = 3 )
	public void testUc33_03EditCampaignModel( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath, @Optional("newCampaignModel") String jsonFileName) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath;
		String resourceFile = jsonFileName;

		Reporter.log("\"Campaign Model \" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		campaignModel=new JSONCampaignModel(resourcePath, resourceFile);
		CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
		
		campaignModelForm.open();
		JSONArray campaignModels = campaignModel.getList();
		
		for( int camapignModelIndex = 0; camapignModelIndex < campaignModels.length(); camapignModelIndex++ ) {
			
			campaignModel.setCampaignModelById( camapignModelIndex );
			
			if( campaignModel.getEnabled() ) {
			
				campaignModelForm.campaignModelEditButton();
				
				Map<String, JSONEvent_> events = campaignModel.getEvents();
				
				campaignModelForm.EditEvents(events);
				
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				campaignModelForm.saveEditedCampaignModel();
				campaignModelForm.confirmCampaignModelAlert(status);
				status=campaignModelForm.isCampaignModelNameInList(campModelName);
				if(status==true)
				{
					AssertJUnit.assertTrue("Campaign Model updated successfully", true);
					Reporter.log("Campaign Model updated successfully",LOG_TO_STD_OUT);
				    
				}
				else
				{
					Assert.fail("campaign model didn't update successfully");
					Reporter.log("Campaign Model didn't update successfully", LOG_TO_STD_OUT);
				}
				
			}
		}
	}
	
	@Parameters({"jsonFilePath","jsonFileName"})
	
	@Test( enabled=TEST_ENABLED, priority = 4 )
	public void testUc33_04CampaignModelDelete( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath, @Optional("newCampaignModel") String jsonFileName) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath;
		String resourceFile = jsonFileName;

		Reporter.log("\"Campaign Model \" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		campaignModel=new JSONCampaignModel(resourcePath, resourceFile);
		CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
		
		campaignModelForm.open();
		JSONArray campaignModels = campaignModel.getList();
		
		for( int camapignModelIndex = 0; camapignModelIndex < campaignModels.length(); camapignModelIndex++ ) {
			
			campaignModel.setCampaignModelById( camapignModelIndex );
			
			if( campaignModel.getEnabled() ) {
			
				campaignModelForm.campaignModelDeleteButton().closeAlertAndGetItsText();
				
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				campaignModelForm.confirmCampaignModelAlert(status);
				
				status=campaignModelForm.isCampaignModelNameInList("CSM_14");
				
				if(status!=true)
				{

					AssertJUnit.assertTrue("Campaign Model deleted successfully", true);
					Reporter.log("Campaign Model deleted successfully",LOG_TO_STD_OUT);
				    
				}
				else
				{
					Assert.fail("campaign model didn't delete successfully");
					Reporter.log("Campaign Model didn't delete successfully", LOG_TO_STD_OUT);
				}
				
			}
		}
	}
	
	@Parameters({"jsonFilePath","jsonFileName"})
	@Test( enabled=TEST_ENABLED, priority = 5 )
	public void testUc33_05CopyCampaignModel( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath, @Optional("newCampaignModel") String jsonFileName) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath;
		String resourceFile = jsonFileName;

		Reporter.log("\"Campaign Model \" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		campaignModel=new JSONCampaignModel(resourcePath, resourceFile);
		CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
		
		campaignModelForm.open();
		JSONArray campaignModels = campaignModel.getList();
		
		for( int camapignModelIndex = 0; camapignModelIndex < campaignModels.length(); camapignModelIndex++ ) {
			
			campaignModel.setCampaignModelById( camapignModelIndex );
			
			if( campaignModel.getEnabled() ) {
			
				campaignModelForm.campaignModelCopyButton();
				
				campaignModelForm.CopyconfigureCriteria();
				
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				campaignModelForm.saveEditedCampaignModel();
				campaignModelForm.confirmCampaignModelAlert(status);
				status=campaignModelForm.isCampaignModelNameInList("CMS_09 (Copy Model)");
				
				if(status==true)
				{
					AssertJUnit.assertTrue("Campaign Model copied successfully", true);
					Reporter.log("Campaign Model copied successfully",LOG_TO_STD_OUT);
				    
				}
				else
				{
					Assert.fail("campaign model didn't copy successfully");
					Reporter.log("Campaign Model didn't copy successfully", LOG_TO_STD_OUT);
				}
				
			}
		}
	}
}
		

	

