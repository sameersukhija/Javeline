package com.lumata.e4o.regressions.gui;
import org.testng.annotations.AfterClass;
import org.testng.AssertJUnit;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Reporter;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.validating.Format;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.json.JsonConfigurationFile;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.dao.tenant.DAOCatalogOffers;
import com.lumata.e4o.dao.tenant.DAOProductType;
import com.lumata.e4o.dao.tenant.DAOTokenType;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.OffersForm;
import com.lumata.e4o.gui.catalogmanager.ProductTypesForm;


import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.common.testing.*;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.CatalogProductTypes;
import com.lumata.e4o.schema.tenant.TokenType;

import org.json.JSONException;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.validating.Format;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCSeleniumWebDriver
public class TestOfferForm extends ParentTestCase{
	private JSONProductTypes OfferForm=null;
	private String OFFER_NAME=null;
	private JSONProductTypes setupProductTypes = null;
	
	
	@BeforeMethod
	public void initOfferForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	@Parameters({"jsonFilePath","jsonFileName","networkEnvironmentParams","seleniumWebDriverParams"})
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void testOfferForm( @Optional("/input/catalog/product_types") String jsonFilePath, @Optional("newProductType") String jsonFileName,@Optional String networkEnvironmentParams, @Optional String seleniumWebDriverParams ) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Offers Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath;
		String resourceFile = jsonFileName;
		String productTypeName = null;
		String productTypes=null;
		String offer_description = null;
		String TC = null;
		String PriceChannel = null;
        String stock=null;
        String criteria=null;
        
		Reporter.log("\"Campaign Model \" is filled with resource file : ",LOG_TO_STD_OUT);
		OffersForm offerForm = new OffersForm( seleniumWebDriver,OfferForm, TIMEOUT, ATTEMPT_TIMEOUT );
	    Reporter.log("Creation of \"Offers Form\".", LOG_TO_STD_OUT);
		JSONProductTypes setupProductTypes = new JSONProductTypes(resourcePath, resourceFile);

		ProductTypesForm productTypesForm = new ProductTypesForm(seleniumWebDriver,setupProductTypes,TIMEOUT, ATTEMPT_TIMEOUT);
				
		int numbProdType = setupProductTypes.getList().size();
		Reporter.log("numProductType -> " + numbProdType, LOG_TO_STD_OUT);

		for (int index = 0; index < numbProdType; index++) {
			
		//JSONProductTypes setupProductTypes;
		JsonCurrentElement current = setupProductTypes.getCurrentElementById(index);
			
			if ( current.getEnabled() ){
			 	productTypes = Format.addTimestamp(setupProductTypes.getName() + "_");
				productTypeName = productTypes;
				offer_description=setupProductTypes.getoffer_description();
				TC=setupProductTypes.getTC();
				PriceChannel=setupProductTypes.getPriceChannel();
				stock=	setupProductTypes.getstock();
				//criteria=setupProductTypes.getcriteria();
				Reporter.log("productTypes -> " + productTypes, LOG_TO_STD_OUT);
			}
			else
			{
				Reporter.log("Creation of Product Failed!");
			}
		
				Reporter.log("Offer_Name -> " + OFFER_NAME, LOG_TO_STD_OUT);
		}
		
		final String OFFER_NAME = Format.addTimestamp( "OFName_" );
		
		AssertJUnit.assertTrue( offerForm.
				openForm().

				addOffer().
				setName( OFFER_NAME ).
			    setDescription(offer_description).
				setTerms(TC).
				addProductTypes(productTypes).
				setProductType(productTypeName). 
				setPriceChannel(PriceChannel).
				addNotitification().
				//EligibilityCriteria(setupProductTypes, OFFER_NAME).
				setStockAvailability( stock ).				
				ActivationBtn().
				openForm().
				isOfferInList(OFFER_NAME)
		);
	}	
	
		/** save form with all fields empty **/
	/*	AssertJUnit.assertFalse( OfferForm.
							openForm().
							addOffer().
							saveBtn().
							formIsValid() 
		);
				
		
		*/
		/** go to home form **/
	//	OfferForm.cancelBtn().goToHome();
	
	//}
		
}




