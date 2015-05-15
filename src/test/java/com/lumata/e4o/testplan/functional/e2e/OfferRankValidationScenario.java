package com.lumata.e4o.testplan.functional.e2e;

import static com.lumata.e4o.gui.campaignmanager.CampaignsForm.TargetingMode.Restricted;
import static com.lumata.e4o.gui.common.NotificationForm.NotificationChannel.SMS;
import static com.lumata.e4o.gui.common.NotificationForm.NotificationTongue.English;

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
import com.lumata.e4o.gui.customercare.CustomerCareTokensForm;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
import com.lumata.e4o.json.gui.catalogmanager.JSONSuppliers;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;
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
public class OfferRankValidationScenario extends ParentTestCase {
	
	private static final Logger logger = LoggerFactory.getLogger( OfferRankValidationScenario.class );
	
	private JSONSuppliers setupSupplier=null;
	private String supplierName=null;
	private String productTypeName=null;
	private JSONProductTypes setupProductTypes=null;
	private JSONTokenType jsonTokenType=null;
	private String tokenName=null;
	private String RuleName=null;
	private JSONRules jsonRules=null;
	private String campaignModelName=null;
	private JSONCampaignModel campaignModelJson=null;
	private CampaignsForm campaignsForm;
	private String campaign_name=null;
	private JSONOffers jsonOffer=null;
	@Parameters({"supplier_jsonFilePath",
		"supplier_jsonFileName",
		"productType_jsonFilePath",
		"productType_jsonFileName",
		"tokenType_jsonPath",
		"tokenType_jsonFileName",
		"rule_jsonPath",
		"rule_jsonFileName",
		"campaignModel_jsonPath",
		"campaignModel_jsonFileName",
		"offer_jsonPath",
		"offer_jsonFileName"
		})
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void testEndtoEndScenario( @Optional("input/catalogmanager/suppliers") String supplier_jsonFilePath, 
			@Optional("endToEndSupplier") String supplier_jsonFileName,
			@Optional("input/catalogmanager/productTypes") String productType_jsonFilePath, 
			@Optional("endToEndProductType") String productType_jsonFileName,
			@Optional("input/catalogmanager/tokenTypes") String tokenType_jsonPath, 
			@Optional("NewTokenTypeList") String tokenType_jsonFileName,
			@Optional("input/catalogmanager/rules") String rule_jsonPath,
			@Optional("NewRuleList") String rule_jsonFileName,
			@Optional("input/campaignmanager/campaignModels") String campaignModel_jsonPath,
			@Optional("endToEndCampaignModel") String campaignModel_jsonFileName,
			@Optional("input/catalogmanager/Offers") String offer_jsonPath,
			@Optional("newOffers") String offer_jsonFileName
			) throws FormException, JSONException, JSONSException,ParseException {
		
		
		Boolean supplier_created=false;
		Boolean pdtype_created=false;
		Boolean subscriber_created=false;
		Boolean token_created=false;
		Boolean rule_created=false;
		Boolean campaignModel_created=false;
		Boolean offer_created=false;
		Boolean campaign_created=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//create Subscriber
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
		
		//create Supplier
		supplier_created=configureSupplier(supplier_jsonFilePath,supplier_jsonFileName);
		if(supplier_created==false)
		{
			Assert.fail("The Supplier creation Failed!");
			Reporter.log("Creation of Supplier Failed!",LOG_TO_STD_OUT);
			
			
		}
		else{
			Assert.assertTrue(supplier_created);
			logger.info("Created Supplier Succesfully:" + getSupplierName());
		}
		
		//create a product Type
		pdtype_created=configureProductType(productType_jsonFilePath, productType_jsonFileName);
		if(pdtype_created==true)
		{
			Assert.assertTrue(pdtype_created);
			logger.info("Created Product Type Succesfully:" + getProductTypeName());
						
		}
		else{
				Assert.fail("The Product Type creation Failed!");
				Reporter.log("Creation of Product Type Failed!",LOG_TO_STD_OUT);
			}
		
		//create offers to use the product type
		offer_created=configureOffers(offer_jsonPath, offer_jsonFileName);
		if(offer_created==true)
		{
			Assert.assertTrue(offer_created);
			logger.info("Created Offer Succesfully");
						
		}
		else{
				Assert.fail("The Offer creation Failed!");
				Reporter.log("Creation of Offer Failed!",LOG_TO_STD_OUT);
			}
		//create Token
		token_created=configureToken(tokenType_jsonPath, tokenType_jsonFileName);
		if(token_created==true)
		{
			Assert.assertTrue(token_created);
			logger.info("Created Token Type Succesfully:" + getTokenName());
						
		}
		else{
				Assert.fail("The Token Type creation Failed!");
				Reporter.log("Creation of Token Type Failed!",LOG_TO_STD_OUT);
			}
		//create a Rule to use the token created above
		rule_created=configureRule(rule_jsonPath, rule_jsonFileName);
		if(rule_created==true)
		{
			Assert.assertTrue(rule_created);
			logger.info("Created Rule Succesfully:" + getRuleName());
						
		}
		else{
				Assert.fail("The Token Type creation Failed!");
				Reporter.log("Creation of Token Type Failed!",LOG_TO_STD_OUT);
			}
		//create a campaign Model using the Rule created above
		campaignModel_created=createCampaignModel(campaignModel_jsonPath, campaignModel_jsonFileName);
		if(campaignModel_created==true)
		{
			Assert.assertTrue(campaignModel_created);
			logger.info("Created Campaign Model Succesfully:" + getCampaignModelName());
						
		}
		else{
				Assert.fail("The Campaign Model creation Failed!");
				Reporter.log("Creation of Campaign Model Failed!",LOG_TO_STD_OUT);
			}
		//create and activate the campaign using the above created sampaign Model
		createAndActivateCampaign();
		
		//validate the ranks of allocated offer in Customer Care
		CustomerCareTokensForm ccTokenForm=new CustomerCareTokensForm(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		Reporter.log("Open CustomerCare Tab.", LOG_TO_STD_OUT);
		ccTokenForm.open();
		Reporter.log("Start search of subscriber", LOG_TO_STD_OUT);
		ccTokenForm.searchMsisdnByPhoneNumber(null, "9890234567");
		Reporter.log("Open Token Tab.", LOG_TO_STD_OUT);
		ccTokenForm.openTokenTab();
		Reporter.log("Load offers allocated for the token", LOG_TO_STD_OUT);
		ccTokenForm.loadOffersForCampaignName(getCampaignName());
		if(ccTokenForm.verifyRanksOfOffers())
		{
			Assert.assertTrue(true, "Ranks are sorted!");
			Reporter.log("Ranks are in sorted order!", LOG_TO_STD_OUT);
		}
		else{
			Assert.fail("Ranks are not appearing in sorted order!");
			Reporter.log("Ranks are not in sorted order!", LOG_TO_STD_OUT);
		}
		ccTokenForm.close();
	}
	public Boolean createSubscriber(String number)
	{
		Boolean status=false;
		CustomerCareCreateSubscriberForm customerCareCreateSubscriberForm = new CustomerCareCreateSubscriberForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		
			
		try{
		
		customerCareCreateSubscriberForm.open();
		// Enter msisdn
		
		customerCareCreateSubscriberForm.inputSubscriberId(number);
		// Click AddButton
		customerCareCreateSubscriberForm.clickCustomerCareAddButton();
		// Enter Language
		customerCareCreateSubscriberForm.enterLanguage("ENG");
		// Click add button
		customerCareCreateSubscriberForm.clickCustomerCareCreateAdd();
		customerCareCreateSubscriberForm.clickClearButton();
		status=customerCareCreateSubscriberForm.subscriberPhoneNumberExists(null, "9890234567");
		}catch (FormException e)
		{
			Reporter.log("Exception occured while creating Subscriber!"+e.getMessage());
		}
		return status;
	}
	public Boolean configureSupplier(String supplierJsonPath,String supplierJsonFileName) throws JSONSException
	{
		Boolean status=false;
		Reporter.log("Creation of \"Supplier Form\".", LOG_TO_STD_OUT);
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + supplierJsonPath;
		String resourceFile = supplierJsonFileName;

		Reporter.log("\"Supplier\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		try{
			
			setSupplierJson(new JSONSuppliers(resourcePath, resourceFile));
			SuppliersForm suppliersForm = new SuppliersForm( seleniumWebDriver,getSupplierJson(), TIMEOUT, ATTEMPT_TIMEOUT );
			
			suppliersForm.openForm();
			JSONArray suppliers = getSupplierJson().getList();
			for( int supplierIndex = 0; supplierIndex < suppliers.length(); supplierIndex++ ) {
				
				getSupplierJson().setSupplierById( supplierIndex );
				if( getSupplierJson().getEnabled() ) {
					setSupplierName(Format.addTimestamp(getSupplierJson().getName() + "_"));
					logger.info("Creating Supplier Name:" + getSupplierName());
					suppliersForm.configureSupplier(getSupplierName(),getSupplierJson().getEmail(),getSupplierJson().getPhone(),getSupplierJson().getWebsite());
					suppliersForm.saveSupplier();
					status=suppliersForm.isSupplierInList(getSupplierName());
					
				}
			}
		}catch (FormException e){
			
			Reporter.log("Exception occured while creating Supplier!"+e.getMessage());
		}
		return status;
	}
	public Boolean configureProductType(String JsonPath,String JsonFileName) throws JSONSException
	{
		Boolean status=false;
		Reporter.log("Creation of \"Product Types Form\".", LOG_TO_STD_OUT);
		
		String Pdt_resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + JsonPath;
		String Pdt_resourceFile = JsonFileName;

		Reporter.log("\"Product Types\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + Pdt_resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + Pdt_resourceFile, LOG_TO_STD_OUT);
		try{
		setProductTypeJson(new JSONProductTypes(Pdt_resourcePath, Pdt_resourceFile));

		ProductTypesForm productTypesForm= new ProductTypesForm(seleniumWebDriver,getProductTypeJson(),TIMEOUT, ATTEMPT_TIMEOUT);
		
		productTypesForm.clickId( "gwt-debug-actrule-catalog-productTypes" );
		int numbProdType = getProductTypeJson().getList().size();
		
		for (int index = 0; index < numbProdType; index++) {
			
			JsonCurrentElement current = getProductTypeJson().getCurrentElementById(index);
			
			if ( current.getEnabled() ){
				setProductTypeName(Format.addTimestamp(setupProductTypes.getName() + "_"));
				productTypesForm.configureProductType(getProductTypeName(),getProductTypeJson().getDescription());
				for (JsonCharacteristicElement chElem : getProductTypeJson().getCharacteristicsList()) {
			
					if ( chElem.getEnabled() ) {
				
						productTypesForm.addCharacteristicButton();
						productTypesForm.fillCharacteristicElement(Format.addTimestamp(chElem.getName()+"_"),chElem);
						productTypesForm.saveCharacteristic();
					}
				}
				productTypesForm.saveProductType();
				status=productTypesForm.isProductTypeInList(getProductTypeName());
			}
		}
		}catch(FormException e)
		{
			Reporter.log("Exception occured while creating Product Type!"+e.getMessage());
		}
		return status;
	}
	public Boolean configureToken(String JsonPath,String JsonFileName) throws JSONSException,ParseException
	{
		Boolean status=false;
		Reporter.log("Creation of  Token", LOG_TO_STD_OUT);
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + JsonPath;
		String resourceFile = JsonFileName;
		Reporter.log("\"Token Types\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		try{
		setTokenTypeJson(new JSONTokenType(resourcePath, resourceFile));
		TokenTypeForm tokenTypeForm = new TokenTypeForm(seleniumWebDriver, getTokenTypeJson(),
				TIMEOUT, ATTEMPT_TIMEOUT);
		setTokenName(Format.addTimestamp("TType_"));
		JSONArray tokenTypes = getTokenTypeJson().getList();
		for (int tokenTypeIndex = 0; tokenTypeIndex < tokenTypes.length(); tokenTypeIndex++) {

			getTokenTypeJson().setTokenTypeById(tokenTypeIndex);
			tokenTypeForm.openForm();
			tokenTypeForm.waitVisibleElement(By.linkText("Add"));
			tokenTypeForm.addBtn();
			tokenTypeForm
					.setName(getTokenName())
					.setDescription(getTokenName() + "description")
					.setImgUrl(getTokenTypeJson().getImageUrl())
					.setFormat(TokenTypeForm.TokenFormat.br.value())
					.setValidityType(
							TokenTypeForm.TokenValidityType.Relative.value());
//				Calendar date = Calendar.getInstance();
//			
//				
//				if( PlaceHolderDate.getInstance( getTokenTypeJson().getValidityValue() ).isPlaceHolderDate() ) {
//				
//					date = PlaceHolderDate.getInstance( getTokenTypeJson().getValidityValue() ).parse();
//										
//				} else {
//									
//					date.setTime( new SimpleDateFormat("yyyy-MM-dd").parse( getTokenTypeJson().getValidityValue() ) );
//				
//				}
//				
//				tokenTypeForm.setAbsoluteDate("validity.value", date);
//					
				tokenTypeForm.setValidityValue(jsonTokenType.getValidityValue()).
				setUnlimitedRedraw(jsonTokenType.getUsageUnlimited()).
					// setNumberOfRedraw(jsonTokenType.getUsage()).
					saveBtn();
				
			
			status=tokenTypeForm.isTokenTypeInList(getTokenName());
			tokenTypeForm.close();
		}
		}catch(FormException e)
		{
			Reporter.log("Exception occured while creating Product Type!"+e.getMessage());
		}
		return status;
	}
	
	public Boolean configureRule(String JsonPath,String JsonFileName) throws JSONSException
	{
		Boolean status=false;
		Reporter.log("Creation of  Rule", LOG_TO_STD_OUT);
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + JsonPath;
		String resourceFile = JsonFileName;
		Reporter.log("\"Rule Form\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		try{
		setRuleJson(new JSONRules(resourcePath, resourceFile));
		RulesForm rulesForm = new RulesForm(seleniumWebDriver, getRuleJson(), TIMEOUT,
				ATTEMPT_TIMEOUT);
		//setRuleName(Format.addTimestamp("Rule_"));
		setRuleName("RuleEndToEnd78");
		JSONArray ruleTypes = getRuleJson().getList();
		for (int ruleTypeIndex = 0; ruleTypeIndex < ruleTypes.length(); ruleTypeIndex++) {
			getRuleJson().setRuleById(ruleTypeIndex);

			rulesForm.openForm();
			rulesForm.clickAddBtn();
			rulesForm.setName(getRuleName());
			rulesForm.setDescription(getRuleName() + " Description");
			// Use token which created in this class
			rulesForm.setTokenType(getTokenName());
			rulesForm.setChannel(getRuleJson().getRuleChannelsAsArray());
			rulesForm.configureRuleChannels();
			rulesForm
					.setAlgorithm(RulesForm.optimizationAlgorithm.RandomAssigment
							.value());
			rulesForm.clickKeepOfferConsistentNo();
			rulesForm.clickPrevioslyAcceptedOfferNo();
			rulesForm
					.setMaxNumberOfOffers(jsonRules.getMaximumNumberOfOffers());
			rulesForm
					.setExpiredOfferBehaviour(RulesForm.expiredOfferBehaviour.Pickupnewoffer
							.value());
			Assert.assertTrue(rulesForm.formIsValid());
			rulesForm.saveRule();
			status=rulesForm.isRuleNameInList("RuleEndToEnd78");
			rulesForm.close();
		}

		}catch(FormException e)
		{
			Reporter.log("Exception occured while creating Product Type!"+e.getMessage());
		}
		return status;
	}
	
	public Boolean createCampaignModel(String JsonPath,String JsonFileName)throws JSONSException{
	Boolean status=false;
	Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);

	String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + JsonPath;
	String resourceFile = JsonFileName;

	Reporter.log("\"Campaign Model \" is filled with reosurce file : ",
			LOG_TO_STD_OUT);
	Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
	Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
	try{
	setCampaignModelJson(new JSONCampaignModel(resourcePath, resourceFile));
	CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver,getCampaignModelJson(), TIMEOUT, ATTEMPT_TIMEOUT );
	
	campaignModelForm.open();
	JSONArray campaignModels = getCampaignModelJson().getList();
	
	for( int camapignModelIndex = 0; camapignModelIndex < campaignModels.length(); camapignModelIndex++ ) {
		
		getCampaignModelJson().setCampaignModelById( camapignModelIndex );
		
		if( getCampaignModelJson().getEnabled() ) {
		
			campaignModelForm.campaignModelAddButton();
			setCampaignModelName(Format.addTimestamp(getCampaignModelJson().getName() + "_"));
			campaignModelForm.configureCampaignModel(getCampaignModelName(), getCampaignModelJson().getDescription(), getCampaignModelJson().getcampaignType(), getCampaignModelJson().getuseHierarchy());
			Map<String, JSONEvent_> events = getCampaignModelJson().getEvents();
			campaignModelForm.addEvents(events);
			campaignModelForm.saveCampaignModel();
			//need a method to validate campaignmodel creation
			status=true;
			
		}
	}
	}catch(FormException e)
	{
		Reporter.log("Exception occured while creating Product Type!"+e.getMessage());
	}
	return status;
	}
	
	public void createAndActivateCampaign() throws FormException
	{
		campaignsForm = new CampaignsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		Calendar endDate = Calendar.getInstance();
		
		Calendar provEndDate = (Calendar)endDate.clone();
		setCampaignName(Format.addTimestamp( "Campaign_" ));
		campaignsForm.
		openForm().
		addBtn().
		openDefinitionTab().
		setCampaignExecutionModeModel().
		setCampaignModel(getCampaignModelName()).
		setCampaignName(getCampaignName()).
		setCampaignDescription( getCampaignName() + " description" ).
		setByPassMediaType( false ).
		/** configure single scheduling tab **/
		openSchedulingTab().
		setCampaignSingleSchedulingType().			
		//setCampaignSingleSchedulingExecutionStart( startDate ).
		setCampaignSingleSchedulingExecutionEndRelative( 101 ).
		//setCampaignSingleSchedulingProvisioningEndDate( provEndDate ).
		//setCampaignSingleSchedulingProvisioningStartDate( startDate ).
		//setCampaignSchedulingSingleDaysBetweenProvisioningAndExecutionStartDates( 3 ).
		/** configure dialog tab **/
		openDialogTab().
		//setCampaignDialogueShortCode( "333" ). // suppose to have the value 333 in the channel_destination table
		//setCampaignDialogueEmailAddress( "" ).
		//setDialogueNotificationDaysOfNotificationBeforeExecution( 2 ).
		openDialogueNotification().
		editDialogueNotification( English, SMS ).
		setDialogueNotificationMessage( "campaign notification message" ).
		saveDialogueNotificationEditing().
		saveDialogueNotification().
		//setCampaignDialogueApplyCampaignToNotifiedOnly().
		//setCampaignDialogueNotificationTime( "00:00" ).
		/** configure target tab **/
		openTargetTab().
		setCampaignTargetTargetingMode( Restricted ).
		setCampaignTargetTargetingRestrictedModeCriteria().
		setCampaignTargetTargetingRestrictedConfigureASampleNoSample().
		/** configure activation tab **/
		openActivationTab().
		activateBtn().
		confirmCampaignActivation();
		//campaignsForm.waitForPageLoad();
		//WebDriverWait wait=new WebDriverWait(seleniumWebDriver.getWrappedDriver(), 40);
		//wait.until(ExpectedConditions.alertIsPresent());
		//campaignsForm.confirmCampaignActivation();
	}
	public Boolean configureOffers(String jsonPath,String jsonFileName) throws JSONSException
	{
		Boolean status=false;
		Reporter.log("Creation of \"Offer Form\".", LOG_TO_STD_OUT);
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonPath;
		String resourceFile = jsonFileName;

		Reporter.log("\"Offer\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		try{
			
			setOfferJson(new JSONOffers(resourcePath, resourceFile));
			OffersForm offerForm = new OffersForm( seleniumWebDriver,getOfferJson(), TIMEOUT, ATTEMPT_TIMEOUT );
			
			int numberOfOffer=getOfferJson().getList().size();
			for (int index = 0; index < numberOfOffer; index++) {
				
			JsonCurrentElement current =getOfferJson().getCurrentElementById(index);
			if(current.getEnabled()==true)
			{
				
				final String OFFER_NAME = Format.addTimestamp( getOfferJson().getName() + "_" );
			
					offerForm.
					openForm().

					clickAddOffer().
					setName( OFFER_NAME ).
				    setDescription(getOfferJson().getDescription()).
					setTerms(getOfferJson().getTermsAndConditions()).
					clickOfferContentTab().
					setProductType(getProductTypeName()). 
					clickPriceTab();
				
				List<JSONPricesElement> prices = getOfferJson().getOffersPrices();
				
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
					clickAvailabilityTab().setStockAvailability( getOfferJson().getStock() ).				
					clickActivationTab().ActivationBtn();
					status=offerForm.isOfferInList(OFFER_NAME);
		}
		}
	}catch (FormException e){
			
			Reporter.log("Exception occured while creating offers!"+e.getMessage());
		}
		return status;
	}
	public void setSupplierName(String supplierName)
	{
		this.supplierName=supplierName;
	}
	public String getSupplierName()
	{
		return this.supplierName;
	}
	public void setProductTypeName(String productType_Name)
	{
		this.productTypeName=productType_Name;
	}
	public String getProductTypeName()
	{
		return this.productTypeName;
	}
	public void setSupplierJson(JSONSuppliers jsonSupplier)
	{
		this.setupSupplier=jsonSupplier;
	}
	public JSONSuppliers getSupplierJson()
	{
		return this.setupSupplier;
	}
	public void setProductTypeJson(JSONProductTypes jsonProductType)
	{
		this.setupProductTypes=jsonProductType;
	}
	public JSONProductTypes getProductTypeJson()
	{
		return this.setupProductTypes;
	}
	public void setTokenTypeJson(JSONTokenType jsonTokenType)
	{
		this.jsonTokenType=jsonTokenType;
	}
	public JSONTokenType getTokenTypeJson()
	{
		return this.jsonTokenType;
	}
	public void setRuleJson(JSONRules jsonRule)
	{
		this.jsonRules=jsonRule;
	}
	public JSONRules getRuleJson()
	{
		return this.jsonRules;
	}
	public void setTokenName(String tokenName)
	{
		this.tokenName=tokenName;
	}
	public String getTokenName()
	{
		return this.tokenName;
	}
	public void setRuleName(String ruleName)
	{
		this.RuleName=ruleName;
	}
	public String getRuleName()
	{
		return this.RuleName;
	}
	public void setCampaignModelName(String campaignModelName)
	{
		this.campaignModelName=campaignModelName;
	}
	public String getCampaignModelName()
	{
		return this.campaignModelName;
	}
	public void setCampaignModelJson(JSONCampaignModel campaignModelJson)
	{
		this.campaignModelJson=campaignModelJson;
	}
	public JSONCampaignModel getCampaignModelJson()
	{
		return this.campaignModelJson;
	}
	public void setOfferJson(JSONOffers offerJson)
	{
		this.jsonOffer=offerJson;
	}
	public JSONOffers getOfferJson()
	{
		return this.jsonOffer;
	}
	public void setCampaignName(String campName)
	{
		this.campaign_name=campName;
	}
	public String getCampaignName()
	{
		return this.campaign_name;
	}
}
