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
import com.lumata.e4o.gui.campaignmanager.CampaignModelFormOld;
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

@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
	)
@TCSeleniumWebDriver
public class TestCampaignModelFormOld extends ParentTestCase{
	
	private static final JSONCriteria JSONCriteria = null;
	private JSONCampaignModel campaignModel=null;
	private String campModelName=null;
	private String campModelCopyName=null;
	@BeforeMethod
	public void initCampaignModelForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	@Parameters({"jsonFilePath_cm","jsonFileName_cm"})
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void testUc33_01CampaignModelCreation( @Optional("input/campaignmanager/campaignModels") String jsonFilePath_cm, @Optional("newCampaignModel") String jsonFileName_cm) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_cm;
		String resourceFile = jsonFileName_cm;

		Reporter.log("\"Campaign Model \" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		campaignModel=new JSONCampaignModel(resourcePath, resourceFile);
		CampaignModelFormOld campaignModelForm = new CampaignModelFormOld( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
		
		campaignModelForm.openForm();
		JSONArray campaignModels = campaignModel.getList();
		
		for( int camapignModelIndex = 0; camapignModelIndex < campaignModels.length(); camapignModelIndex++ ) {
			
			campaignModel.setCampaignModelById( camapignModelIndex );
			
			if( campaignModel.getEnabled() ) {
			
				campaignModelForm.addBtn();
				campModelName="CSM_14";
				
				campaignModelForm.configureCampaignModel(campModelName, campaignModel.getDescription(), campaignModel.getType(), campaignModel.getuseHierarchy());
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
	
	
	@Parameters({"jsonFilePath_cm","jsonFileName_cm"})
	@Test( enabled=TEST_ENABLED, priority = 2 )
	public void testUc33_02CampaignModelMultipleEvents_Criteria_Action( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath_cm, @Optional("newCampaignModel") String jsonFileName_cm) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_cm;
		String resourceFile = jsonFileName_cm;

		Reporter.log("\"Campaign Model \" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		campaignModel=new JSONCampaignModel(resourcePath, resourceFile);
		CampaignModelFormOld campaignModelForm = new CampaignModelFormOld( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
		
		campaignModelForm.openForm();
		JSONArray campaignModels = campaignModel.getList();
		
		for( int camapignModelIndex = 0; camapignModelIndex < campaignModels.length(); camapignModelIndex++ ) {
			
			campaignModel.setCampaignModelById( camapignModelIndex );
			
			if( campaignModel.getEnabled() ) {
			
				campaignModelForm.addBtn();
				campModelName="CMS_09";
				campaignModelForm.configureCampaignModel(campModelName, campaignModel.getDescription(), campaignModel.getType(), campaignModel.getuseHierarchy());
				Map<String, JSONEvent_> events = campaignModel.getEvents();
				
				campaignModelForm.addEvents(events);
				campaignModelForm.addEvents2(events);
				//seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
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


	@Parameters({"jsonFilePath_cm","jsonFileName_cm"})
	@Test( enabled=TEST_ENABLED, priority = 3 )
	public void testUc33_03EditCampaignModel( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath_cm, @Optional("newCampaignModel") String jsonFileName_cm) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_cm;
		String resourceFile = jsonFileName_cm;

		Reporter.log("\"Campaign Model \" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		campaignModel=new JSONCampaignModel(resourcePath, resourceFile);
		CampaignModelFormOld campaignModelForm = new CampaignModelFormOld( seleniumWebDriver,campaignModel, TIMEOUT, ATTEMPT_TIMEOUT );
		
		campaignModelForm.openForm();
		JSONArray campaignModels = campaignModel.getList();
		
		for( int camapignModelIndex = 0; camapignModelIndex < campaignModels.length(); camapignModelIndex++ ) {
			
			campaignModel.setCampaignModelById( camapignModelIndex );
			
			if( campaignModel.getEnabled() ) {
				
				campModelName="CMS_09";
				
				campaignModelForm.campaignModelEditButton(campModelName);
				
				Map<String, JSONEvent_> events = campaignModel.getEvents();
				
				campaignModelForm.EditEvents(events);
				
				//seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
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
	
	
	
	@Test( enabled=TEST_ENABLED, priority = 4 )
	public void testUc33_04CampaignModelDelete() throws FormException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);
		CampaignModelFormOld campaignModelForm = new CampaignModelFormOld( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		campaignModelForm.openForm();
	
				campaignModelForm.campaignModelDeleteButton("CSM_14").closeAlertAndGetItsText();
				
				//seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
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
	
	
	@Test( enabled=TEST_ENABLED, priority = 5 )
	public void testUc33_05CopyCampaignModel() throws FormException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);
	
		CampaignModelFormOld campaignModelForm = new CampaignModelFormOld( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		campaignModelForm.openForm();
			
			
				campaignModelForm.campaignModelCopyButton(campModelName);
				
				campaignModelForm.CopyconfigureCriteria("25678");
				
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

		

	

