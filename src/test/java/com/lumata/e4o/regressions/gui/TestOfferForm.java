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
import com.lumata.e4o.gui.catalogmanager.OffersForm;
import com.lumata.e4o.gui.catalogmanager.ProductTypesForm;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONPricesElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;

import org.json.JSONException;

import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCSeleniumWebDriver
public class TestOfferForm extends ParentTestCase{
	private JSONOffers setupOffer=null;
	private JSONProductTypes setupProductTypes = null;
	private String product_type_name=null;
	
	
	@BeforeMethod
	public void initOfferForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	@Parameters({"jsonFilePath_ProductType","jsonFileName_ProductType","jsonFilePath_Offer","jsonFileName_Offer"})
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void testOfferForm(@Optional ("/input/catalogmanager/productTypes") String jsonFilePath_ProductType,
			@Optional ("newProductType") String jsonFileName_ProductType,
			@Optional("/input/catalogmanager/Offers") String jsonFilePath_Offer,
			@Optional("newOffers") String jsonFileName_Offer) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		Boolean offer_status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Offers Form\".", LOG_TO_STD_OUT);

		String resourcePath1 = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_ProductType;
		String resourceFile1 = jsonFileName_ProductType;
		String resourcePath2 = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_Offer;
		String resourceFile2 = jsonFileName_Offer;
		
        setupProductTypes = new JSONProductTypes(resourcePath1,resourceFile1);
        setupOffer = new JSONOffers(resourcePath2,resourceFile2);
		OffersForm offerForm = new OffersForm( seleniumWebDriver,setupOffer, TIMEOUT, ATTEMPT_TIMEOUT );
	    Reporter.log("Creation of \"Offers Form\".", LOG_TO_STD_OUT);
		

		ProductTypesForm productTypesForm = new ProductTypesForm(seleniumWebDriver,setupProductTypes,TIMEOUT, ATTEMPT_TIMEOUT);
		productTypesForm.openForm();	
		int numbProdType = setupProductTypes.getList().size();
		Reporter.log("numProductType -> " + numbProdType, LOG_TO_STD_OUT);

		for (int index = 0; index < numbProdType; index++) {
			JsonCurrentElement current = setupProductTypes.getCurrentElementById(index);
			if ( current.getEnabled() ){
				product_type_name=Format.addTimestamp(setupProductTypes.getName() + "_");
				productTypesForm.configureProductType(product_type_name,setupProductTypes.getDescription());
				for (JsonCharacteristicElement chElem : setupProductTypes.getCharacteristicsList()) {
			
					if ( chElem.getEnabled() ) {
				
						productTypesForm.addCharacteristicButton();
						productTypesForm.fillCharacteristicElement(Format.addTimestamp(chElem.getName()),chElem);
						productTypesForm.saveCharacteristic();
					}
				}
				productTypesForm.saveProductType();
				status=productTypesForm.isProductTypeInList(product_type_name);
				if(status==true)
				{
					Assert.assertTrue(status);
					Reporter.log("Product Types Created Succesfully!");
				}
				else
				{
					//Assert.assertTrue(status,"The creation of Product Failed!");
					Assert.fail("The Product Types creation Failed!");
					Reporter.log("Creation of Product Types Failed!");
				}
			}
	
		}
		int numberOfOffer=setupOffer.getList().size();
		for (int index = 0; index < numberOfOffer; index++) {
			
		JsonCurrentElement current =setupOffer.getCurrentElementById(index);
		if(current.getEnabled()==true)
		{
			
			final String OFFER_NAME = Format.addTimestamp( setupOffer.getName() + "_" );
		
				offerForm.
				openForm().

				clickAddOffer().
				setName( OFFER_NAME ).
			    setDescription(setupOffer.getDescription()).
				setTerms(setupOffer.getTermsAndConditions()).
				clickOfferContentTab().
				setProductType("product_type_name"). 
				clickPriceTab();
			
			List<JSONPricesElement> prices = setupOffer.getOffersPrices();
			
			if ( prices != null && prices.size() != 0 ) {

				for (JSONPricesElement price : prices) {
				
					offerForm.clickAddPriceButton();

					for (String channel : price.getChannels()) {
						offerForm.setPriceChannel(channel);
					}
				}
			}
				offerForm.clickNotificationTab().addNotitification().
				//EligibilityCriteria(setupProductTypes, OFFER_NAME).
				clickAvailabilityTab().setStockAvailability( setupOffer.getStock() ).				
				clickActivationTab().ActivationBtn();
				offer_status=offerForm.isOfferInList(OFFER_NAME);
				if(offer_status==true)
				{
					Assert.assertTrue(offer_status);
					Reporter.log("Offer Created Succesfully!");
				}
				else
				{
					//Assert.assertTrue(status,"The creation of Product Failed!");
					Assert.fail("The Offer creation Failed!");
					Reporter.log("Creation of Offer Failed!");
				}
		
	}
	}
}	
	
}




