package com.lumata.e4o.regressions.gui;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Reporter;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.validating.Format;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.CommoditiesForm;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.gui.catalogmanager.OffersForm;
import com.lumata.e4o.gui.catalogmanager.ProductTypesForm;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONPricesElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.administrationmanager.JSONCommodities;

import org.json.JSONException;

import com.lumata.e4o.testing.common.TCSeleniumWebDriver;
@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
)

@TCSeleniumWebDriver
public class TestPaymentMeanForm extends ParentTestCase{
	/**
	 * Commodities configuration file
	 */
	private JSONCommodities setupCommodities = null;
	
	/**
	 * Commodities Form
	 */
	private CommoditiesForm commoditiesForm = null;
	
	@BeforeMethod
	public void initCommoditiesForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	@Parameters({"commoditiesFile"})
	
	@Test( enabled=TEST_ENABLED, priority = 1 )
	
	public void testUc07_01CreateNewCommodityPaymentMean(@Optional("commoditiesPaymentMean") String commoditiesFile) throws FormException, JSONException, JSONSException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Commodities Form\".", LOG_TO_STD_OUT);

		//String currentResourceStartPath = null;
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT+"input/administrationmanager/commodities/";
		
		String resourceFile ="commoditiesPaymentMean";

		Reporter.log("\"Commodities Form\" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);

		setupCommodities = new JSONCommodities(resourcePath, resourceFile);

		commoditiesForm = new CommoditiesForm(seleniumWebDriver,
				setupCommodities, TIMEOUT, ATTEMPT_TIMEOUT);

		Reporter.log("Open \"Commodities Form\" UI.",LOG_TO_STD_OUT);

		commoditiesForm.open();

		Reporter.log("Apply setup on UI.", LOG_TO_STD_OUT);
		
		commoditiesForm.clickPaymentMeanTab();
		int numbCommodities = setupCommodities.getList().size();
		
		for( int commodityIndex = 0; commodityIndex < numbCommodities; commodityIndex++ ) {
			
			setupCommodities.setCurrentElementById(commodityIndex);
			
			/**
			 * Only "enabled" commodities will be configured
			 */
			if ( setupCommodities.getCurrentElement().getEnabled() ) 
			{
				commoditiesForm.clickAddPaymentMeanButton();
				commoditiesForm.setCommodityPaymentType(setupCommodities.getType());
		
				commoditiesForm.setCommodityPaymentAccount(setupCommodities.getAccount());
		
				commoditiesForm.setCommodityPaymentMean(setupCommodities.getName());
		
				commoditiesForm.saveCommodityPaymentMean();
		
				Reporter.log("Check general status of form", LOG_TO_STD_OUT);

				Assert.assertTrue(commoditiesForm.navigate(),
				"Status error during configuration!");
			}
		}
	
	}

		
	
		@Parameters({"commoditiesFile"})
		
		@Test( enabled=TEST_ENABLED, priority = 2 )
		
		public void testUc07_02EditCommodityPaymentMean(@Optional("commoditiesPaymentMean") String commoditiesFile) throws FormException, JSONException, JSONSException {
			seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Reporter.log("Creation of \"Commodities Form\".", LOG_TO_STD_OUT);

			//String currentResourceStartPath = null;
			String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT+"input/administrationmanager/commodities/";
			
			String resourceFile ="commoditiesPaymentMean";

			Reporter.log("\"Commodities Form\" is filled with resource file : ",
					LOG_TO_STD_OUT);
			Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
			Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);

			setupCommodities = new JSONCommodities(resourcePath, resourceFile);

			commoditiesForm = new CommoditiesForm(seleniumWebDriver,
					setupCommodities, TIMEOUT, ATTEMPT_TIMEOUT);

			Reporter.log("Open \"Commodities Form\" UI.",LOG_TO_STD_OUT);
			int numbCommodities = setupCommodities.getList().size();
			commoditiesForm.open();
			commoditiesForm.clickPaymentMeanTab();
			for( int commodityIndex = 0; commodityIndex < numbCommodities; commodityIndex++ ) {
				
				setupCommodities.setCurrentElementById(commodityIndex);
				
				/**
				 * Only "enabled" commodities will be configured
				 */
				if ( setupCommodities.getCurrentElement().getEnabled() ) 
				{
			
					commoditiesForm.clickEditCommoditiesPaymentMeanbutton(setupCommodities.getName());
						
					commoditiesForm.setEditCommodityPaymentMean(setupCommodities.getNewCurrency());
			
					Reporter.log("Apply setup on UI.", LOG_TO_STD_OUT);
			
					Assert.assertEquals(commoditiesForm.saveEditCommodityPaymentMean(),
				"Status error during edit!");

					Reporter.log("Check general status of form", LOG_TO_STD_OUT);
				}
			}
		}
			
	
	
	@Parameters({"commoditiesFile"})
	@Test( enabled=TEST_ENABLED, priority = 3 )
	public void testUc07_03DeleteCommodityPaymentMean(@Optional("commoditiesPaymentMean") String commoditiesFile) throws FormException, JSONException, JSONSException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Commodities Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT+"input/administrationmanager/commodities/";
		
		String resourceFile ="commoditiesPaymentMean";

		Reporter.log("\"Commodities Form\" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);

		setupCommodities = new JSONCommodities(resourcePath, resourceFile);

		commoditiesForm = new CommoditiesForm(seleniumWebDriver,
				setupCommodities, TIMEOUT, ATTEMPT_TIMEOUT);

		Reporter.log("Open \"Commodities Form\" UI.",LOG_TO_STD_OUT);

		commoditiesForm.open();
		commoditiesForm.clickPaymentMeanTab();
		Reporter.log("Apply setup on UI.", LOG_TO_STD_OUT);
		int numbCommodities = setupCommodities.getList().size();
		for( int commodityIndex = 0; commodityIndex < numbCommodities; commodityIndex++ ) {
			
			setupCommodities.setCurrentElementById(commodityIndex);
			
			/**
			 * Only "enabled" commodities will be configured
			 */
			if ( setupCommodities.getCurrentElement().getDelete() ) 
			{
				Assert.assertTrue(commoditiesForm.deleteCommoditiesPaymentMean(setupCommodities.getName()),"Status error during cleanup!");
			}
		}

		Reporter.log("Check general status of form", LOG_TO_STD_OUT);

	}
	}	


