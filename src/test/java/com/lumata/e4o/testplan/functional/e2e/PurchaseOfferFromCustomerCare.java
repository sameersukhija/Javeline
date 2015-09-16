package com.lumata.e4o.testplan.functional.e2e;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.common.PlaceHolderDate;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOTokenType;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.gui.campaignmanager.CampaignsForm;
import com.lumata.e4o.gui.campaignmanager.ConfigureCampaignModel;
import com.lumata.e4o.gui.catalogmanager.OffersForm;
import com.lumata.e4o.gui.catalogmanager.ProductTypesForm;
import com.lumata.e4o.gui.catalogmanager.ProductsForm;
import com.lumata.e4o.gui.catalogmanager.RulesForm;
import com.lumata.e4o.gui.catalogmanager.SuppliersForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.customercare.CustomerCareCreateSubscriberForm;
import com.lumata.e4o.gui.customercare.CustomerCareForm;
import com.lumata.e4o.gui.customercare.CustomerCarePurchasesForm;
import com.lumata.e4o.gui.customercare.CustomerCareTokensForm;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONReservationElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.OfferContentType;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
import com.lumata.e4o.json.gui.catalogmanager.JSONSuppliers;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONOfferContentElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONPricesElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;
import com.lumata.e4o.schema.tenant.TokenType;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(
	@TCOwner( name="Parvinder Bhogra", email="parvinder.bhogra@lumatagroup.com" )
)
@TCSeleniumWebDriver
@TCMysqlMaster
public class PurchaseOfferFromCustomerCare extends ParentTestCase {

	private static final Logger logger = LoggerFactory.getLogger( PurchaseOfferFromCustomerCare.class );
	public String OFFER_NAME=null;
	
	JSONOffers setupOffer=null;
	@Parameters({"jsonFilePath_Offer","jsonFileName_Offer"})
	@Test(enabled=TEST_ENABLED,priority = 1 ,timeOut=1500000)
	public void testUC20_01purchaseOfferFromCustomerCare(String jsonFilePath_Offer,String jsonFileName_Offer) throws JSONSException,FormException{
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String resourcePath2 = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_Offer;
		String resourceFile2 = jsonFileName_Offer;
		setupOffer = new JSONOffers(resourcePath2,resourceFile2);

		Boolean offer_status=false;

		Reporter.log("Creation of \"Offers Form\".", LOG_TO_STD_OUT);

		int numberOfOffer=setupOffer.getList().size();
		for (int index = 0; index < numberOfOffer; index++) {
			
		JsonCurrentElement current =setupOffer.getCurrentElementById(index);
		if(current.getEnabled()==true)
		{
			
			OFFER_NAME= Format.addTimestamp( setupOffer.getName() + "_" );
			OffersForm offerForm = new OffersForm( seleniumWebDriver,setupOffer, TIMEOUT, ATTEMPT_TIMEOUT );
		    
				offerForm.
				openForm().
				
				clickAddOffer();
				offerForm.setName(OFFER_NAME).
			    setDescription(setupOffer.getDescription()).
				setTerms(setupOffer.getTermsAndConditions()).
				clickOfferContentTab();
				
				List<JSONOfferContentElement> list_offer_content=setupOffer.getOfferContents();
				if(list_offer_content!=null && list_offer_content.size()!=0)
				{
					for(JSONOfferContentElement offercon:list_offer_content)
					{
					//JsonCurrentElement cur=list_offer_content.get(j);
						System.out.println("the type of offercontent is "+ offercon.getOfferContentType());
					if(offercon.getOfferContentType().equals(OfferContentType.Products))
					{
						offerForm.addCharacteristicButton();
						offerForm.setProductName(offercon.getProduct()).setProductQuantity(Integer.toString(offercon.getQuantity())).saveCharacteristic();
					}
				}
				}
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
			
			
				offerForm.clickNotificationTab().addNotitification().
			
				clickAvailabilityTab().setAvailableOffers( setupOffer.getStock() );	
				List<JSONReservationElement> reserv=setupOffer.getReservations();
				if(reserv!=null && reserv.size()!=0)
				{
					for(JSONReservationElement crres : reserv)
					offerForm.setStockReservation(crres.getChannel(), Integer.toString(crres.getQuantity()));
				}
				offerForm.clickActivationTab().ActivationBtn();
				
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
		Boolean subscriber_created=false;
		Boolean subscriber_exists = DAOSubscribers.getInstance( mysqlMaster ).isSubscriber(9890234567L);
		if(!subscriber_exists)
		{
			subscriber_created=createSubscriber("9890234567");
		
			if(subscriber_created==false)
			{
				Assert.fail("The Subscriber creation Failed!");
				Reporter.log("Creation of Subscriber Failed!",LOG_TO_STD_OUT);
			
			
			}
			else{
				Assert.assertTrue(subscriber_created);
				logger.info("Created Subscriber Succesfully");
			}
		}
		//Purchase offer from customercare
		Boolean purchased=purchaseOffer();
		if(purchased==false)
		{
			Assert.fail("The Purchase of Offer Failed!");
			Reporter.log("The Purchase of Offer Failed!",LOG_TO_STD_OUT);
		
		
		}
		else{
			Assert.assertTrue(purchased);
			logger.info("Purchased offer Succesfully");
		}
}
	public Boolean createSubscriber(String number)
	{
		Boolean status=false;
		CustomerCareCreateSubscriberForm customerCareCreateSubscriberForm = new CustomerCareCreateSubscriberForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
			
		try{
		
			customerCareCreateSubscriberForm.open();
		// Enter msisdn
		
			customerCareCreateSubscriberForm.setSubscriberMsisdn(number);
		// Click AddButton
			customerCareCreateSubscriberForm.clickCustomerCareAddButton();
		// Enter Language
		customerCareCreateSubscriberForm.enterLanguage("ENG");
		// Click add button
		customerCareCreateSubscriberForm.clickCustomerCareCreateAdd();
		customerCareCreateSubscriberForm.clickClearButton();
		status=customerCareCreateSubscriberForm.subscriberPhoneNumberExists(null, "9890234567");
		customerCareCreateSubscriberForm.clickClearButton();
		//status=customerCareCreateSubscriberForm.searchById("gwt-debug-BtnCCInfoEdit").isDisplayed();
		}catch (FormException e)
		{
			Reporter.log("Exception occured while creating Subscriber!"+e.getMessage());
		}
		return status;
	}
	public Boolean purchaseOffer()
	{
		Boolean status=false;
		CustomerCarePurchasesForm purchase = new CustomerCarePurchasesForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		try{
			
			purchase.open();
			purchase.searchMsisdnByPhoneNumber(null,"9890234567");
			purchase.openPurchasesTab().purchaseOffer(OFFER_NAME);
			purchase.setPurchaseQuantity("1");
			purchase.setChannel("Campaign manager").clickOKButton();
			purchase.handleJavascriptAlertAcceptDismiss(true);
			purchase.confirmDialog();
			status=purchase.isPurchaseSuccessfull(OFFER_NAME);
		}catch (FormException e)
		{
			Reporter.log("Exception occured while purchasing offer!"+e.getMessage());
		}
		
		return status;
	}
}