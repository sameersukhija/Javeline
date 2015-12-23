package com.lumata.e4o.testplan.functional.e2e;

import static com.lumata.e4o.gui.common.NotificationForm.NotificationChannel.SMS;
import static com.lumata.e4o.gui.common.NotificationForm.NotificationTongue.English;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.SalesChannelsForm;
import com.lumata.e4o.gui.campaignmanager.CampaignModelFormOld;
import com.lumata.e4o.gui.campaignmanager.CampaignsForm;
import com.lumata.e4o.gui.catalogmanager.RulesForm;
import com.lumata.e4o.gui.catalogmanager.RulesForm.ExpiredOfferBehaviour;
import com.lumata.e4o.gui.catalogmanager.RulesForm.OptimizationAlgorithm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm.TokenFormat;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm.TokenValidityType;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm.TokenValidityUnit;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;


@TCSeleniumWebDriver
public class ConfigureGUIToGiveTokens extends ParentTestCase {
	
	private final boolean TEST_ENABLED = true;
		
	private SalesChannelsForm salesChannelsForm;
	private TokenTypeForm tokenTypesForm;
	private RulesForm rulesForm;
	private CampaignModelFormOld campaignModelsForm;
	private CampaignsForm campaignsForm;
	
	private final String CHANNEL_NAME_PREFIX = "Ch ";
	private final Integer NUMBER_OF_SALES_CHANNELS = 3;
	private final String TOKEN_TYPE_NAME_PREFIX = "TokenType";
	private final Integer NUMBER_OF_TOKEN_TYPES = 3;
	private final String RULE_NAME_PREFIX = "Rule";
	private final Integer NUMBER_OF_RULES = 3;
	private final String CAMPAIGN_MODEL_NAME_PREFIX = "CampaignModel";
	private final Integer NUMBER_OF_CAMPAIGN_MODELS = 3;
	
	@BeforeClass
	public void initCampaignsForm() throws NetworkEnvironmentException, FormException {		
		
		/** SalesChannels Form **/
		salesChannelsForm = new SalesChannelsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		/** Token Type Form **/
		tokenTypesForm = new TokenTypeForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		/** Rules Form **/
		rulesForm = new RulesForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		/** Campaign Model Form **/
		campaignModelsForm = new CampaignModelFormOld( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		/** Campaigns Form **/
		campaignsForm = new CampaignsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
	}
	
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void configureSalesChannels() throws FormException {
		
		salesChannelsForm.open();
		
		for( int n = 1; n <= NUMBER_OF_SALES_CHANNELS; n++ ) {
			
			String salesChannelName = CHANNEL_NAME_PREFIX + Character.toString ((char) ( 64 + n) );
			
			if( !salesChannelsForm.isSalesChannelExisting( salesChannelName ) ) {
				
				salesChannelsForm.
					clickAddButton().
					setSalesChannelName( salesChannelName ).
					saveSalesChannel().
					activateSalesChannel( salesChannelName );
				
			}
			
		}
		
	}

	@Test( enabled=TEST_ENABLED, priority = 2 )
	public void configureTokenTypes() throws FormException {
		
		tokenTypesForm.openForm();
		
		for( int n = 1; n <= NUMBER_OF_TOKEN_TYPES; n++ ) {

			String tokenTypeName = TOKEN_TYPE_NAME_PREFIX + Character.toString ((char) ( 64 + n ) );
			
			if( !tokenTypesForm.isTokenTypeInList( tokenTypeName ) ) {
			
				tokenTypesForm.
					addBtn().
					setName( tokenTypeName ).
					setDescription( tokenTypeName ).
					setFormat( TokenFormat.imm5 ).
					setValidityType( TokenValidityType.Relative ).
					setValidityValue( 100 ).
					setValidityUnit( TokenValidityUnit.days ).
					setUnlimitedRedraw( true ).
					saveBtn();
									
			} else { 			
				
				tokenTypesForm.
					editByName( tokenTypeName ).
					setDescription( tokenTypeName ).
					setFormat( TokenFormat.imm5 ).
					setValidityType( TokenValidityType.Relative ).
					setValidityValue( 100 ).
					setValidityUnit( TokenValidityUnit.days ).
					setUnlimitedRedraw( true ).
					editSaveBtn();
				
			}
						
		}
		
		tokenTypesForm.goToHome();
		
	}

	@Test( enabled=TEST_ENABLED, priority = 3 )
	public void configureRules() throws FormException {

		ArrayList<String> salesChannels = new ArrayList<String>();
		
		for( int n = 1; n <= NUMBER_OF_SALES_CHANNELS; n++ ) {
		
			String salesChannelName = CHANNEL_NAME_PREFIX + Character.toString ((char) ( 64 + n) );
			
			salesChannels.add( salesChannelName );
			
		}
		
		ArrayList<String> tokenTypes = new ArrayList<String>();
		
		for( int n = 1; n <= NUMBER_OF_TOKEN_TYPES; n++ ) {
		
			String tokenTypeName = TOKEN_TYPE_NAME_PREFIX + Character.toString ((char) ( 64 + n ) );
			
			tokenTypes.add( tokenTypeName );
			
		}
		
		rulesForm.openForm();
		
		for( int n = 1; n <= NUMBER_OF_RULES; n++ ) {
						
			String ruleName = RULE_NAME_PREFIX + Character.toString ((char) ( 64 + n ) );
			
			if( !rulesForm.isRuleNameInList( ruleName ) ) {
								
				rulesForm.
					addBtn().
					setName( ruleName ).
					setDescription( ruleName ).
					setTokenType( tokenTypes.get( ( n - 1 ) % tokenTypes.size() ) ).
					addChannel( salesChannels.get( ( n - 1 ) % salesChannels.size() ) ).
					setAlgorithm( OptimizationAlgorithm.RandomAssigment ).
					setKeepOfferConsistentYes().setPrevioslyAcceptedOfferYes().
					setMaxNumberOfOffers( 10 ).
					setExpiredOfferBehaviour( ExpiredOfferBehaviour.Pickupnewoffer ).
					saveBtn();
				
			} else {
				
				rulesForm.
					editByName( ruleName ).
					setDescription( ruleName ).
					setTokenType( tokenTypes.get( ( n - 1 ) % tokenTypes.size() ) ).
					addChannel( salesChannels.get( ( n - 1 ) % salesChannels.size() ) ).
					setAlgorithm( OptimizationAlgorithm.RandomAssigment ).
					setKeepOfferConsistentYes().setPrevioslyAcceptedOfferYes().
					setMaxNumberOfOffers( 10 ).
					setExpiredOfferBehaviour( ExpiredOfferBehaviour.Pickupnewoffer ).
					saveBtn();
				
			}
			
		}
		
		rulesForm.goToHome();
		
	}
	
	//@Test( enabled=TEST_ENABLED, priority = 2 )
	@Test( enabled=false, priority = 4 )
	public void configureCampaignModels() throws FormException {
		
		campaignModelsForm.openForm();
		
		for( int n = 1; n <= NUMBER_OF_CAMPAIGN_MODELS; n++ ) {
		
			String campaignModelName = CAMPAIGN_MODEL_NAME_PREFIX + Character.toString ((char) ( 64 + n ) );
			
			campaignModelsForm.
				addBtn().
				setModelName( campaignModelName ).
				setModelDescription( campaignModelName ).
				cancelBtn();
			
		}
		
//		for( int n = 1; n <= NUMBER_OF_TOKEN_TYPES; n++ ) {
//
//			String tokenTypeName = TOKEN_TYPE_NAME_PREFIX + Character.toString ((char) ( 64 + n ) );
//			
//			if( !tokenTypesForm.isTokenTypeInList( tokenTypeName ) ) {
//			
//				tokenTypesForm.
//					addBtn().
//					setName( tokenTypeName ).
//					setDescription( tokenTypeName ).
//					setFormat( TokenFormat.imm5 ).
//					setValidityType( TokenValidityType.Relative ).
//					setValidityValue( 100 ).
//					setValidityUnit( TokenValidityUnit.days ).
//					setUnlimitedRedraw( true ).
//					saveBtn();
//				
//			}
//			
//		}
//		
//		tokenTypesForm.goToHome();
		
	}
	
	
	
//	final String RULE_TYPE_NAME = Format.addTimestamp( "Rule_" );
//	ruleTypeForm.openForm();
//	//ruleTypeForm.waitForVisibilityOfElement();
//	ruleTypeForm.clickAddBtn();
//	ruleTypeForm.setName(RULE_TYPE_NAME);
//	ruleTypeForm.setDescription(RULE_TYPE_NAME + " Description" );	
//	ruleTypeForm.setTokenType("TType_1427787040383");
//	ruleTypeForm.setChannel("Campaign manager");
//	
//	ruleTypeForm.setAlgorithm(RulesForm.optimizationAlgorithm.RandomAssigment.value());
//	ruleTypeForm.clickKeepOfferConsistentNo();
//	ruleTypeForm.clickPrevioslyAcceptedOfferNo();
//	ruleTypeForm.setMaxNumberOfOffers("1");
//	ruleTypeForm.setExpiredOfferBehaviour(RulesForm.expiredOfferBehaviour.Pickupnewoffer.value());
//	Assert.assertTrue(ruleTypeForm.formIsValid());
//	ruleTypeForm.saveRule();
	
	
	
//	@Test( enabled=TEST_ENABLED, priority = 1 )
//	public void testUc34_01CreateCampaign_ExistingModel() throws FormException {
//		
//		//seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		
//		Calendar startDate = Calendar.getInstance();
//		
//		Calendar endDate = Calendar.getInstance();
//		
//		Calendar provEndDate = (Calendar)endDate.clone();
//		
//		
//		/**
//		 * Campaign Notification
//		 * - simple scheduling
//		 * - absolute end date
//		 * - restricted no sample target
//		 */
//		campaignsForm.
//		openForm().
//		addBtn().		
//		/** configure definition tab **/
//		openDefinitionTab().
//		setCampaignModel("CMS_09").
//		setCampaignName( "CAMPAIGN_0910" ).
//		setCampaignDescription( "CAMPAIGN_09" + " description" ).
//		setByPassMediaType( false );
//		/** configure single scheduling tab **/
//		campaignsForm.openSchedulingTab();
//		campaignsForm.setCampaignSingleSchedulingType();		
//		//campaignsForm.setCampaignSingleSchedulingExecutionStart( startDate );
//		campaignsForm.setCampaignSingleSchedulingExecutionEndRelative( 101 );
//		
//		//campaignsForm.setCampaignSingleSchedulingProvisioningStartDate( startDate );
//		
//		//campaignsForm.setCampaignSingleSchedulingProvisioningEndDate( provEndDate );
//		/** configure dialog tab **/
//		campaignsForm.openDialogTab().
//		setCampaignDialogueEmailAddress( "" ).
//		openDialogueNotification().
//		editDialogueNotification( English, SMS ).
//		setDialogueNotificationMessage( "campaign notification message ( ###campaign_name### )" ).
//		saveDialogueNotificationEditing().
//		saveDialogueNotification();
//		/** configure activation tab **/
//		campaignsForm.openActivationTab().
//		activateBtn();
//		//confirmCampaignActivation();
//		for (int i=0; i<=3;i++)
//		{
//			WebDriverWait wait=new WebDriverWait(seleniumWebDriver.getWrappedDriver(), 40);
//			wait.until(ExpectedConditions.alertIsPresent());
//			campaignsForm.handleJavascriptAlertAcceptDismiss(true);
//		}
//		/** Verify activated Campaign exists or not **/
//		WebDriverWait wait=new WebDriverWait(seleniumWebDriver.getWrappedDriver(), 30);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Campaign List']//ancestor::table[@class='tableList']")));
//		Boolean campaign_status = campaignsForm.isCampaignNameInList("CAMPAIGN_0910");
//		Reporter.log("Creation of \"Campaign Form\".", LOG_TO_STD_OUT);
//		
//		if(campaign_status==true) {
//			Assert.assertTrue(campaign_status);
//			Reporter.log("Campaign created and activated Succesfully!");
//		
//		}
//		else {
//			Assert.fail("The Campaign creation Failed!");
//			Reporter.log("Creation of Campaign Failed!");
//		}
//			
//	}
	
//	@Parameters({"salesChannelsList"})
//	@Test( enabled=true, priority = 1 )
//	public void configureSalesChannels( @Optional("salesChannelsList") String salesChannelsList ) throws FormException, JSONException, JSONSException {
//		
//		SalesChannelsForm salesChannelsForm = new SalesChannelsForm( seleniumWebDriver, new JSONSalesChannels( "input/administrationmanager/salesChannels", salesChannelsList ), TIMEOUT, ATTEMPT_TIMEOUT );
//		
//		Assert.assertTrue( salesChannelsForm.open().addSalesChannels().navigate() );
//				
//	}
	
//	@Parameters({"tokenTypeList"})
//	@Test( enabled=testEnabled, priority = 2 )
//	public void configureTokeType( @Optional("tokenTypeList") String tokenTypeList ) throws FormException, JSONException, JSONSException {
//		
//		TokenTypeForm tokenTypeForm = new TokenTypeForm( seleniumWebDriver, new JSONTokenType( "input/catalogmanager/tokenTypes", tokenTypeList ), TIMEOUT, ATTEMPT_TIMEOUT );
//		
//		Assert.assertTrue( tokenTypeForm.openForm().addTokenTypes().close().navigate() );
//		
//	}
//	
//	@Parameters({"ruleList"})
//	@Test( enabled=testEnabled, priority = 3 )
//	public void configureRules( @Optional("ruleList") String ruleList ) throws FormException, JSONException, JSONSException {
//		
//		RulesForm rulesForm = new RulesForm( seleniumWebDriver, new JSONRules( "input/catalogmanager/rules", ruleList ), TIMEOUT, ATTEMPT_TIMEOUT );
//		
//		Assert.assertTrue( rulesForm.openForm().addRules().close().navigate() );
//		
//	}
//	
//	@Parameters({"campaignModelList"})
//	@Test( enabled = true, priority = 4 )
//	public void configureCampaignModel( @Optional("campaignModelList") String campaignModelList ) throws JSONSException, FormException, JSONException {
//
//		CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver, new JSONCampaignModel( "input/campaignmanager/campaignModels", campaignModelList ), TIMEOUT, ATTEMPT_TIMEOUT );
//						
//		Assert.assertTrue( campaignModelForm.open().addCampaignModels().navigate() );		
//				
//    }
//		
}
