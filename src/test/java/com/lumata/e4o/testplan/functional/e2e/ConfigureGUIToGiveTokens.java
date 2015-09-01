package com.lumata.e4o.testplan.functional.e2e;

import static com.lumata.e4o.gui.common.NotificationForm.NotificationChannel.SMS;
import static com.lumata.e4o.gui.common.NotificationForm.NotificationTongue.English;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.SalesChannelsForm;
import com.lumata.e4o.gui.campaignmanager.CampaignsForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm.TokenFormat;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm.TokenValidityType;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm.TokenValidityUnit;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;


@TCSeleniumWebDriver
public class ConfigureGUIToGiveTokens extends ParentTestCase {
	
	private SalesChannelsForm salesChannelsForm;
	private TokenTypeForm tokenTypeForm;
	private CampaignsForm campaignsForm;
	
	private final String CHANNEL_NAME_PREFIX = "Ch ";
	private final Integer NUMBER_OF_SALES_CHANNELS = 3;
	private final String TOKEN_TYPE_NAME_PREFIX = "TokenType";
	private final Integer NUMBER_OF_TOKEN_TYPES = 3;
	
	@BeforeClass
	public void initCampaignsForm() throws NetworkEnvironmentException, FormException {		
		
		/** SalesChannels Form **/
		salesChannelsForm = new SalesChannelsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		/** SalesChannels Form **/
		tokenTypeForm = new TokenTypeForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		/** Campaigns Form **/
		campaignsForm = new CampaignsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
	}
	
	//@Test( enabled=TEST_ENABLED, priority = 1 )
	@Test( enabled=false, priority = 1 )
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

	//@Test( enabled=TEST_ENABLED, priority = 2 )
	@Test( enabled=false, priority = 2 )
	public void configurTokenTypes() throws FormException {
		
		tokenTypeForm.openForm();
		
		for( int n = 1; n <= NUMBER_OF_TOKEN_TYPES; n++ ) {

			String tokenTypeName = TOKEN_TYPE_NAME_PREFIX + Character.toString ((char) ( 64 + n ) );
			
			if( !tokenTypeForm.isTokenTypeInList( tokenTypeName ) ) {
			
				tokenTypeForm.
					addBtn().
					setName( tokenTypeName ).
					setDescription( tokenTypeName ).
					setFormat( TokenFormat.imm5 ).
					setValidityType( TokenValidityType.Relative ).
					setValidityValue( 100 ).
					setValidityUnit( TokenValidityUnit.days ).
					setUnlimitedRedraw( true ).
					saveBtn();
				
			}
			
		}
		
		tokenTypeForm.goToHome();
		
	}

	@Test( enabled=false, priority = 2 )
	public void configurRules() throws FormException {
		
		tokenTypeForm.openForm();
		
		for( int n = 1; n <= NUMBER_OF_TOKEN_TYPES; n++ ) {

			String tokenTypeName = TOKEN_TYPE_NAME_PREFIX + Character.toString ((char) ( 64 + n ) );
			
			if( !tokenTypeForm.isTokenTypeInList( tokenTypeName ) ) {
			
				tokenTypeForm.
					addBtn().
					setName( tokenTypeName ).
					setDescription( tokenTypeName ).
					setFormat( TokenFormat.imm5 ).
					setValidityType( TokenValidityType.Relative ).
					setValidityValue( 100 ).
					setValidityUnit( TokenValidityUnit.days ).
					setUnlimitedRedraw( true ).
					saveBtn();
				
			}
			
		}
		
		tokenTypeForm.goToHome();
		
	}
	
	
	
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
