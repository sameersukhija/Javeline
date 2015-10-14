package com.lumata.e4o.regressions.gui;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Reporter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.validating.Format;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.network.SFTPClient;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.OffersForm;
import com.lumata.e4o.gui.catalogmanager.ProductTypesForm;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.VoucherType;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONOfferContentElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONPricesElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.OfferContentType;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;

import java.awt.AWTException;

import javax.mail.MessagingException;

import org.json.JSONException;

import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
	)
@TCSeleniumWebDriver

	public class TestOfferForm extends ParentTestCase{
	
	public JSONOffers setupOffer=null;
	public JSONProductTypes setupProductTypes = null;
	public String product_type_name=null;
	public String product_Name=null;
	public String offerName=null;
	public String Content_OFFERNAME=null;
	public String edit_offer=null;
	public String Voucher="One Time Use";
	public String expiryDate=null;
	private OffersForm setDescription;
	private OffersForm setUnlimitedVoucher;
	private OffersForm oneTimeVoucher;
	private OffersForm setOneTimeVoucher; 
	
	@BeforeMethod
	public void initOfferForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	@Parameters({"jsonFilePath1_Offer","jsonFileName1_Offer"})
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void testUc28_01addProduct_content_OfferForm(@Optional("/input/catalogmanager/Offers") String jsonFilePath1_Offer,
			@Optional("newOffers") String jsonFileName1_Offer) throws FormException, JSONException, JSONSException {
	String resourcePath2 = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath1_Offer;
	String resourceFile2 = jsonFileName1_Offer;
	setupOffer = new JSONOffers(resourcePath2,resourceFile2);

	Boolean offer_status=false;

	seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	Reporter.log("Creation of \"Offers Form\".", LOG_TO_STD_OUT);

	
	int numberOfOffer=setupOffer.getList().size();
	for (int index = 1; index < numberOfOffer-2; index++) {
		
	JsonCurrentElement current =setupOffer.getCurrentElementById(index);
	if(current.getEnabled()==true)
	{
		
		final String OFFER_NAME = Format.addTimestamp( setupOffer.getName() + "_" );
		Content_OFFERNAME = OFFER_NAME;
		OffersForm offerForm = new OffersForm( seleniumWebDriver,setupOffer, TIMEOUT, ATTEMPT_TIMEOUT );
	    
			offerForm.
			openForm().
			
			clickAddOffer().
			setName( OFFER_NAME ).
		    setDescription(setupOffer.getDescription()).
			setTerms(setupOffer.getTermsAndConditions()).
			clickOfferContentTab().addCharacteristicButton();
			offerForm.setProductName("Products").setProductQuantity("20").saveCharacteristic();
			
			offerForm.clickPriceTab();
		
		List<JSONPricesElement> prices = setupOffer.getOffersPrices();
		
		if ( prices != null && prices.size() != 0 ) {

			for (JSONPricesElement price : prices) {
				
				

				for (String channel : price.getChannels()) {
					offerForm.clickAddPriceButton();
					offerForm.setPriceChannel(channel);
				}
			}
		}
		
		
			offerForm.clickNotificationTab().addNotification().
		
			clickAvailabilityTab().setAvailableOffers( setupOffer.getStock() ).				
			
			clickActivationTab().saveBtn();
			
			offer_status=offerForm.isOfferInList(OFFER_NAME);
			
			if(offer_status==true)
			{
				Assert.assertTrue(offer_status);
				Reporter.log("Offer Created Succesfully!");
				
			}
			else
			{
				
				AssertJUnit.fail("The Offer creation Failed!");
				Reporter.log("Creation of Offer Failed!");
			}
}

	}
	}
	
	
	@Parameters({"jsonFilePath1_Offer","jsonFileName1_Offer","jsonFilePath_ProductType","jsonFileName_ProductType"})
	@Test( enabled=TEST_ENABLED, priority = 2 )
	public void testUc28_02_addproducttype_OfferForm(@Optional("/input/catalogmanager/Offers") String jsonFilePath1_Offer,
		@Optional("newOffers") String jsonFileName1_Offer,@Optional ("/input/catalogmanager/productTypes") String jsonFilePath_ProductType,
		@Optional ("newOffer_ProductType") String jsonFileName_ProductType) throws FormException, JSONException, JSONSException {
	
		Boolean status=false;
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Product Types Form\".", LOG_TO_STD_OUT);

		String resourcePath1 = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_ProductType;
		String resourceFile1 = jsonFileName_ProductType;

		setupProductTypes = new JSONProductTypes(resourcePath1,resourceFile1);
	    

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
					  Assert.assertTrue(status,"The creation of Product Failed!");
					AssertJUnit.fail("The Product Types creation Failed!");
					Reporter.log("Creation of Product Types Failed!");
				}
			}
		}
	
	
	String resourcePath2 = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath1_Offer;
	String resourceFile2 = jsonFileName1_Offer;
	setupOffer = new JSONOffers(resourcePath2,resourceFile2);
	
	
	
	Boolean offer_status=false;

	seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	Reporter.log("Creation of \"Offers Form\".", LOG_TO_STD_OUT);

	
	int numberOfOffer=setupOffer.getList().size();
	for (int index = 0; index < numberOfOffer-3; index++) {
		
	JsonCurrentElement current =setupOffer.getCurrentElementById(index);
	if(current.getEnabled()==true)
	{
		
		final String OFFER_NAME = Format.addTimestamp( setupOffer.getName() + "_" );
		edit_offer=OFFER_NAME;
		OffersForm offerForm = new OffersForm( seleniumWebDriver,setupOffer, TIMEOUT, ATTEMPT_TIMEOUT );
	    
			offerForm.
			openForm().
			
			clickAddOffer().
			setName( OFFER_NAME ).
		    setDescription(setupOffer.getDescription()).
			setTerms(setupOffer.getTermsAndConditions()).
			clickOfferContentTab().addCharacteristicButton().
			setProductType(product_type_name).saveCharacteristic().
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
			offerForm.clickNotificationTab().addNotification().
		
			clickAvailabilityTab().setAvailableOffers( setupOffer.getStock() ).				
			
			clickActivationTab().saveBtn();
			
			offer_status=offerForm.isOfferInList(OFFER_NAME);
			
			if(offer_status==true)
			{
				Assert.assertTrue(offer_status);
				Reporter.log("Offer Created Succesfully!");
				
			}
			else
			{
				
				AssertJUnit.fail("The Offer creation Failed!");
				Reporter.log("Creation of Offer Failed!");
			}
}
	}
	}
	
	@Parameters({"jsonFilePath1_Offer","jsonFileName1_Offer"})
	@Test( enabled=TEST_ENABLED, priority = 3)
	public void testUc28_3_addOffer_content_OfferForm(@Optional("/input/catalogmanager/Offers") String jsonFilePath1_Offer,
			@Optional("newOffers") String jsonFileName1_Offer) throws FormException, JSONException, JSONSException {
				
	String resourcePath2 = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath1_Offer;
	String resourceFile2 = jsonFileName1_Offer;
	setupOffer = new JSONOffers(resourcePath2,resourceFile2);

	Boolean offer_status=false;

	seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	Reporter.log("Creation of \"Offers Form\".", LOG_TO_STD_OUT);

	
	int numberOfOffer=setupOffer.getList().size();
	for (int index = 2; index < numberOfOffer-1; index++) {
		
	JsonCurrentElement current =setupOffer.getCurrentElementById(index);
	if(current.getEnabled()==true)
	{
		
		final String OFFER_NAME = Format.addTimestamp( setupOffer.getName() + "_" );
		OffersForm offerForm = new OffersForm( seleniumWebDriver,setupOffer, TIMEOUT, ATTEMPT_TIMEOUT );
	    
			offerForm.
			openForm().
			
			clickAddOffer().
			setName( OFFER_NAME ).
		    setDescription(setupOffer.getDescription()).
			setTerms(setupOffer.getTermsAndConditions()).
			clickOfferContentTab().addCharacteristicButton();
			
			offerForm.setOfferName(Content_OFFERNAME).saveCharacteristic();
			
			offerForm.clickPriceTab();
		
		List<JSONPricesElement> prices = setupOffer.getOffersPrices();
		
		if ( prices != null && prices.size() != 0 ) {

			for (JSONPricesElement price : prices) {
			
				offerForm.clickAddPriceButton();

				for (String channel : price.getChannels()) {
					offerForm.setPriceChannel(channel);
				}
			}
		}
			offerForm.clickNotificationTab().addNotification().
		
			clickAvailabilityTab().setAvailableOffers( setupOffer.getStock() ).				
			
			clickActivationTab().saveBtn();
			
			offer_status=offerForm.isOfferInList(OFFER_NAME);
			
			if(offer_status==true)
			{
				Assert.assertTrue(offer_status);
				Reporter.log("Offer Created Succesfully!");
				
			}
			else
			{
				
				AssertJUnit.fail("The Offer creation Failed!");
				Reporter.log("Creation of Offer Failed!");
			}
}

	}
}
        	
	
        
	
			@Parameters({"jsonFilePath_ProductType","jsonFileName_ProductType"})
	
			@Test( enabled=TEST_ENABLED, priority = 4 )
			public void testUc28_04_editOfferForm(@Optional("/input/catalogmanager/productTypes") String jsonFilePath_ProductType,
					@Optional ("newOffer_ProductType") String jsonFileName_ProductType) throws FormException, JSONException, JSONSException {
				
				Boolean status=false;
				String resourcePath1 = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_ProductType;
				String resourceFile1 = jsonFileName_ProductType;
			
		        setupProductTypes = new JSONProductTypes(resourcePath1,resourceFile1);
				ProductTypesForm productTypesForm = new ProductTypesForm(seleniumWebDriver,setupProductTypes,TIMEOUT, ATTEMPT_TIMEOUT);
				productTypesForm.openForm();	
  				int numbProdType = setupProductTypes.getList().size();
  				Reporter.log("numProductType -> " + numbProdType, LOG_TO_STD_OUT);
  			
  		        for (int index1 = 0; index1 < numbProdType; index1++) {
  				JsonCurrentElement current1 = setupProductTypes.getCurrentElementById(index1);
  				if ( current1.getEnabled() ){
  				
  				productTypesForm.openForm();
  				product_type_name=Format.addTimestamp("TestProductType" + "_");
  				productTypesForm.configureProductType(product_type_name,"TestProductType");
  				
  				for (JsonCharacteristicElement chElem : setupProductTypes.getCharacteristicsList()) {
  			
  					if ( chElem.getEnabled() ) {
  				
  						productTypesForm.addCharacteristicButton();
  						productTypesForm.fillCharacteristicElement(Format.addTimestamp(chElem.getName()),chElem);
  						productTypesForm.saveCharacteristic();
  					
  				
  						productTypesForm.saveProductType();
  						status=productTypesForm.isProductTypeInList(product_type_name);
  						if(status==true)
  						{
  							Assert.assertTrue(status);
  							Reporter.log("Product Types Created Succesfully!");
  						}
  						else
  						{
  							Assert.assertTrue(status,"The creation of Product Failed!");
  							AssertJUnit.fail("The Product Types creation Failed!");
  							Reporter.log("Creation of Product Types Failed!");
  				}
  			}
  
  				}	
  	
  				OffersForm offerForm = new OffersForm( seleniumWebDriver,setupOffer, TIMEOUT, ATTEMPT_TIMEOUT );
  				
  			boolean offer_status;
  			
  			offerForm.
  			openForm().
  
  			clickEditSavedOffer(edit_offer);
  			offerForm.setName("XYZ");
  			offerForm.clickOfferContentTab().addCharacteristicButton();
  			offerForm.setProductType(product_type_name).saveCharacteristic();
  			offerForm.clickPriceTab();
  			offerForm.clickEditPriceButton();
  			offerForm.setPriceChannel("Ch B");
  			offerForm.clickAvailabilityTab().
  			setAvailableOffers( "12" );				
  			offerForm.clickActivationTab().saveBtn();
  			offer_status=offerForm.isOfferInList("XYZ");
  			Reporter.log("Editing of \"Offers Form\"."+"XYZ", LOG_TO_STD_OUT);
  			
  			if(offer_status==true)
  			{
  				AssertJUnit.assertTrue(offer_status);
  				Reporter.log("Offer Edited Succesfully!");
  	
  			}
  			else
  			{
  				AssertJUnit.fail("The Offer editing Failed!");
  				Reporter.log("Editing of Offer Failed!");
  			}
  			
  			}
  		        }
  			}
  			
  			@Parameters({"jsonFilePath1_Offer","jsonFileName1_Offer"})
  			@Test( enabled=TEST_ENABLED, priority = 5 )
  			public void testUc28_05_activate_saved_OfferForm(@Optional("/input/catalogmanager/Offers") String jsonFilePath1_Offer,
  					@Optional("newOffers") String jsonFileName1_Offer) throws FormException, JSONException, JSONSException {
  			
  			  //Activate an already saved offer
  				OffersForm offerForm = new OffersForm( seleniumWebDriver,setupOffer, TIMEOUT, ATTEMPT_TIMEOUT );

  			boolean offer_status;
  			
  			offerForm.clickEditSavedOffer("XYZ");
  			
  			offerForm.clickActivationTab().ActivationBtn();
  			
  			offer_status=offerForm.isOfferInList("XYZ");
				
			seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
				
  			//offer_status=true;
  			if(offer_status==true)
  			{
  				AssertJUnit.assertTrue(offer_status);
  				Reporter.log("Offer Activated Succesfully!");
  				
  			}
  			else
  			{
  				
  				AssertJUnit.fail("The Offer activation Failed!");
  				Reporter.log("Activation of Offer Failed!");
  			}
  
  	}
  
  
  		@Parameters({"jsonFilePath1_Offer","jsonFileName1_Offer"})
  		@Test( enabled=TEST_ENABLED, priority = 6 )
  		public void testUc28_06_addOffer_UnlimitedVoucher_OfferForm(@Optional("/input/catalogmanager/Offers") String jsonFilePath1_Offer,
  				@Optional("newOffers") String jsonFileName1_Offer) throws FormException, JSONException, JSONSException, IOException {
  		
  		String resourcePath2 = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath1_Offer;
  		String resourceFile2 = jsonFileName1_Offer;
  		setupOffer = new JSONOffers(resourcePath2,resourceFile2);
  
  		Boolean offer_status=false;
  
  		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  		Reporter.log("Creation of \"Offers Form\".", LOG_TO_STD_OUT);
  
  
  		int numberOfOffer=setupOffer.getList().size();
  		for (int index = 3; index < numberOfOffer; index++) {
  			
  		JsonCurrentElement current =setupOffer.getCurrentElementById(index);
  		if(current.getEnabled()==true)
  		{
  			
  			final String OFFER_NAME = Format.addTimestamp( setupOffer.getName() + "_" );
  			OffersForm offerForm = new OffersForm( seleniumWebDriver,setupOffer, TIMEOUT, ATTEMPT_TIMEOUT );

  				
  				offerForm.
  				openForm().
  				
  				clickAddOffer();
  				offerForm.setName( OFFER_NAME );
  				setDescription = offerForm.setDescription(setupOffer.getDescription());
  				offerForm.setTerms(setupOffer.getTermsAndConditions());
  				
  				setupOffer.getVoucher();
  				
  				offerForm.setUnlimitedVoucher(VoucherType.Unlimited.toString());
  				
  				offerForm.clickVoucherDefinitionTab();
  				
  				offerForm.setUnlimitedVoucherCode("Armjy");
  				
  				offerForm.setExternalSupplier("Mobistar");
  				
  				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  				     //get current date time with Date()
  				   Date date = new Date(ATTEMPT_TIMEOUT);
  				   System.out.println(dateFormat.format(date));
  				   Calendar cal = Calendar.getInstance();
  				   System.out.println(dateFormat.format(cal.getTime()));
  				   offerForm.setVoucherExpiryDate(dateFormat.format(cal.getTime()));
  				
  				offerForm.clickPriceTab();
  			
  			List<JSONPricesElement> prices = setupOffer.getOffersPrices();
  			
  			if ( prices != null && prices.size() != 0 ) {
  
  				for (JSONPricesElement price : prices) {
  					
  					
  
  					for (String channel : price.getChannels()) {
  						offerForm.clickAddPriceButton();
  						offerForm.setPriceChannel(channel);
  					}
  				}
  			}
  			
  			
  				offerForm.clickNotificationTab().addNotification().
  			
  				clickAvailabilityTab().setAvailableOffers( setupOffer.getStock() );				
  				
  				offerForm.setOfferstartdate(dateFormat.format(cal.getTime()));
  				
  				offerForm.setOfferenddate(dateFormat.format(cal.getTime()));
  				
  				offerForm.clickActivationTab().saveBtn().closeAlertAndGetItsText();
  				
  				offerForm.handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
  				
  				offer_status=offerForm.isOfferInList(OFFER_NAME);
  				
  				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
  				
  				//offerForm.UnlimitedVoucherAlertHandling();
  				
  				if(offer_status==true)
  				{
  					offerForm.handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
  						
  					Assert.assertTrue(offer_status);
  					Reporter.log("Offer Created Succesfully!");
  					
  				}
  				else
  				{
  					
  					AssertJUnit.fail("The Offer creation Failed!");
  					Reporter.log("Creation of Offer Failed!");
  				}
  		}
  
  		}
  
  			}
  		
		@Parameters({"jsonFilePath1_Offer","jsonFileName1_Offer","networkEnvironmentParams","seleniumWebDriverParams"})
		@Test( enabled=TEST_ENABLED, priority = 1 )
		public void testUc28_07_addOffer_OneTimeVoucher_OfferForm(@Optional("/input/catalogmanager/Offers") String jsonFilePath1_Offer,
				@Optional("newOffers") String jsonFileName1_Offer,
				String networkEnvironmentParams,
				String seleniumWebDriverParams) throws FormException, JSONException, JSONSException, IOException, InterruptedException, AWTException, MessagingException {
			String resourcePath2 = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath1_Offer;
			String resourceFile2 = jsonFileName1_Offer;
			setupOffer = new JSONOffers(resourcePath2,resourceFile2);

			Boolean offer_status=false;

	seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	Reporter.log("Creation of \"Offers Form\".", LOG_TO_STD_OUT);

	int numberOfOffer=setupOffer.getList().size();
	for (int index = 3; index < numberOfOffer; index++) {
		
	JsonCurrentElement current =setupOffer.getCurrentElementById(index);
	if(current.getEnabled()==true)
	{
		
		final String OFFER_NAME = Format.addTimestamp( setupOffer.getName() + "_" );
		OffersForm offerForm = new OffersForm( seleniumWebDriver,setupOffer, TIMEOUT, ATTEMPT_TIMEOUT );
	    
			
			offerForm.
			openForm().
			
			clickAddOffer();
			offerForm.setName( OFFER_NAME );
			
			setDescription = offerForm.setDescription(setupOffer.getDescription());
			
			offerForm.setTerms(setupOffer.getTermsAndConditions());
			
			offerForm.setOneTimeVoucher(Voucher);		
			
			offerForm.clickVoucherDefinitionTab();
			
			offerForm.setOneTimeBrowseFile("/tmp/VoucherCodes.csv");
			
			offerForm.setExternalSupplier("Mobistar");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			    //get current date time with Date()
			   Date date = new Date(ATTEMPT_TIMEOUT);
			   System.out.println(dateFormat.format(date));
			   Calendar cal = Calendar.getInstance();
			   System.out.println(dateFormat.format(cal.getTime()));
			   
			   offerForm.setVoucherExpiryDate(dateFormat.format(cal.getTime()));
			   offerForm.clickImportVoucherCodes();
			   offerForm.handleJavascriptAlertAcceptDismiss(true);
			   offerForm.clickXPath("//div[contains(@class,'dialogMiddleCenterInner dialogContent')]//button[@name='btn-ok']");
			   offerForm.clickPriceTab();
				
			   
		List<JSONPricesElement> prices = setupOffer.getOffersPrices();
		
		if ( prices != null && prices.size() != 0 ) {

			for (JSONPricesElement price : prices) {
				
				

				for (String channel : price.getChannels()) {
					offerForm.clickAddPriceButton();
					offerForm.setPriceChannel(channel);
				}
			}
		}
		
		
			offerForm.clickNotificationTab().addNotification();
		
			offerForm.clickAvailabilityTab();
			
			offerForm.setOfferstartdate(dateFormat.format(cal.getTime()));
			
			offerForm.setOfferenddate(dateFormat.format(cal.getTime()));
			
			offerForm.clickActivationTab().saveBtn().closeAlertAndGetItsText();
			
			offerForm.handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
			
			offer_status=offerForm.isOfferInList(OFFER_NAME);
			
			seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
			
			offerForm.handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
			
			if(offer_status==true)
			{
				Assert.assertTrue(offer_status);
				Reporter.log("Offer Created Succesfully!");
				
			}
			else
			{
				
				AssertJUnit.fail("The Offer creation Failed!");
				Reporter.log("Creation of Offer Failed!");
			}
	}

	}
		
		}
		
				}
