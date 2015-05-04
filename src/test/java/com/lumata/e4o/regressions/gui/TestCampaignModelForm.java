package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;
import java.util.Map;
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
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.gui.catalogmanager.SuppliersForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.catalogmanager.JSONSuppliers;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCSeleniumWebDriver
public class TestCampaignModelForm extends ParentTestCase{
	private JSONCampaignModel campaignModel=null;
	private String campModelName=null;
	@BeforeMethod
	public void initCampaignModelForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	@Parameters({"jsonFilePath","jsonFileName","networkEnvironmentParams","seleniumWebDriverParams"})
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void testCampaignModelCreation( @Optional("/input/campaignmanager/campaignModels") String jsonFilePath, @Optional("newCampaignModel") String jsonFileName,@Optional String networkEnvironmentParams, @Optional String seleniumWebDriverParams) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath;
		String resourceFile = jsonFileName;

		Reporter.log("\"Campaign Model \" is filled with reosurce file : ",
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
				campModelName=Format.addTimestamp(campaignModel.getName() + "_");
				campaignModelForm.configureCampaignModel(campModelName, campaignModel.getDescription(), campaignModel.getcampaignType(), campaignModel.getuseHierarchy());
				Map<String, JSONEvent_> events = campaignModel.getEvents();
				campaignModelForm.addEvents(events);
				campaignModelForm.saveCampaignModel();
				
			}
		}
}
	}
