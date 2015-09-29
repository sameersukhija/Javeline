package com.lumata.e4o.testplan.functional.e2e;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.e4o.gui.OperationManagementForm.OperationManagementForm;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.gui.customercare.CustomerCareLoyaltyForm;
import com.lumata.e4o.gui.loyaltymanager.LoyaltyCreationForm;
import com.lumata.e4o.gui.loyaltymanager.LoyaltyManagerForm;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.loyaltymanager.LoyaltyManagmentForm;
import com.lumata.e4o.gui.operationmanager.TrafficGenerator;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;
import com.lumata.e4o.testplan.functional.e2e.OfferRankValidationScenario;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation;
import com.lumata.e4o.json.gui.loyaltymanager.LoyaltyManageCfg;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation.LoyaltyTypes;
import com.lumata.e4o.json.gui.operationmanager.TrafficGeneratorEvent;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.ErrorModificableElement;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.HasJsonElements;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.json.JsonConfigurationFile;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
	)
@TCSeleniumWebDriver

public class TestLoyaltyManagementE2E<jsonFilePath1_Loyalty> extends ParentTestCase {

	private static final Logger logger = LoggerFactory.getLogger(TestLoyaltyManagementE2E.class );
	/**
	 * Commodities configuration file
	 * 
	 */
	

	private JSONLoyaltiesCreation setupLoyaltiesCreation = null;

	private LoyaltyManageCfg setupLoyaltiesManagement = null;
	
	private TrafficGeneratorEvent TrafficEvent = null;
	
	
	private LoyaltyCreationForm loyaltyCreationForm = null;

	private List<String> element2fill;

	public String Loyalty_NAME;

	Boolean loyalty_status;

	private Map<String, JSONEvent_> events;

	private JSONLoyaltiesCreation LoyaltiesCreation;

	private String jsonFilePath1_Loyalty;
	private String resourcePath1;
	private String resourceFile1;
	private LoyaltyManagmentForm LoyaltyManagmentForm;
	private JsonConfigurationFile loyalties;
	private Map<String, JSONEvent_> awarded;
	private TrafficGeneratorEvent TrafficGeneratorForm;
	private OperationManagementForm OperationManagementForm;
	@BeforeMethod
	public void initOfferForm( Method method ) throws  FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	@Parameters({"jsonFilePath_Loyalty", "jsonFileName_Loyalty","jsonFilePath1_Loyalty", "jsonFileName1_Loyalty",
		"jsonFilePath_Traffic", "jsonFileName_Traffic"})
	@Test( enabled=true, priority = 1 ,timeOut=1500000)
	public void testUc29_EndtoEndScenario_LoyaltyProgramManagement(@Optional("/input/loyalties") 
	String jsonFilePath_Loyalty,@Optional("loyaltyCreationTemplate") 
	String jsonFileName_Loyalty, @Optional("/input/loyalties") String jsonFilePath1_Loyalty,
	@Optional("loyalty_manage") String jsonFileName1_Loyalty,@Optional("/input/traffic_generator") String jsonFilePath_Traffic,
	@Optional("recharge_event")	String jsonFileName_Traffic)  throws FormException, JSONException, JSONSException,ParseException {
		
			
		Boolean loyalty_created=false;
		
		Boolean loyalty_managed=false;
		
		Boolean event_created=false;
		
		Boolean redeem_status=false;
		
		//create Loyaltycreation Badges Program for Loyalty Management
		
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		loyalty_created = CreateLoyaltyProgram(jsonFilePath_Loyalty, jsonFileName_Loyalty);
		
		if(loyalty_created==true)
		{
			Assert.assertTrue(loyalty_created);
			logger.info("Created Badges loyalty Program Succesfully");
						
		}
		else{
				Assert.fail("The loyalty Management Program  Failed!");
				Reporter.log("Creation of loyalty Management Program Failed!",LOG_TO_STD_OUT);
			}	
	
	
		
		
		
		//create LoyaltyMananagement Program to use existing Loyalty Program Type
				loyalty_managed=ManageExistingLoyaltyProgram(jsonFilePath1_Loyalty, jsonFileName1_Loyalty);
				if(loyalty_managed==true)
				{
					Assert.assertTrue(loyalty_managed);
					logger.info("Created loyalty Management Program Succesfully");
								
				}
				else{
						Assert.fail("The loyalty Management Program  Failed!");
						Reporter.log("Creation of loyalty Management Program Failed!",LOG_TO_STD_OUT);
					}	
	



	//Generate revenue recharge event usin Traffic Generator
	event_created=OperationManagement(jsonFilePath_Traffic, jsonFileName_Traffic);
	if(event_created==true)
	{
		Assert.assertTrue(event_created);
		logger.info("Generated recharge event Succesfully");
					
	}
	else{
			Assert.fail("The Generated recharge event Failed!");
			Reporter.log("Generated recharge event Failed!",LOG_TO_STD_OUT);
		}	

	
	//verify redeemed status in Customer Care
	redeem_status=CustomerCare();
	if(redeem_status==true)
	{
		Assert.assertTrue(redeem_status);
		logger.info("Loyalty Award Redeemed Succesfully");
					
	}
	else{
			Assert.fail("The Loyalty Award Redemption  Failed!");
			Reporter.log("Redemption of Loyalty Award Failed!",LOG_TO_STD_OUT);
		}	
}
	

public Boolean CreateLoyaltyProgram(String jsonFilePath_Loyalty,String jsonFileName_Loyalty) throws JSONSException, FormException{
	
	Reporter.log("Creation of \"Loyalty Creation Form\".", LOG_TO_STD_OUT);

	String resourcePath =DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_Loyalty;
		
	String resourceFile = jsonFileName_Loyalty;
	
	setupLoyaltiesCreation = new JSONLoyaltiesCreation(resourcePath, resourceFile);

	Reporter.log("\"Loyalty Creation\" is filled with resource file : ",
			LOG_TO_STD_OUT);
	Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
	
	Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);

	Reporter.log("Open \"Loyalty Creation Form\" UI.", LOG_TO_STD_OUT);

	int numbProdType = setupLoyaltiesCreation.getList().size();
	
	for (int index = 0; index < numbProdType; index++) {
		
		JsonCurrentElement current = setupLoyaltiesCreation.getCurrentElementById(index);
		
		if (current.getEnabled()==true)  {
		
			loyaltyCreationForm = new LoyaltyCreationForm(seleniumWebDriver,
					setupLoyaltiesCreation, TIMEOUT, ATTEMPT_TIMEOUT);

			
			setupLoyaltiesCreation.getType();
			
			loyaltyCreationForm.open();
			
			element2fill =setupLoyaltiesCreation.getBadges();
			
			loyaltyCreationForm.clickaddnewBadge(setupLoyaltiesCreation.getType());
			
			loyaltyCreationForm.clickaddLoyaltyPrograms();
			
			loyaltyCreationForm.addLoyaltyProgramName(setupLoyaltiesCreation.getName());
	
			loyaltyCreationForm.addLoyaltyProgramDesc(setupLoyaltiesCreation.getDescription());
			
			loyaltyCreationForm.saveLoyaltyProgram();
		}
	}
			loyaltyCreationForm.addBadgesTypeName(setupLoyaltiesCreation.getBadges());
			
			loyaltyCreationForm.clickclosebutton();
			
			Reporter.log("Check general status of form", LOG_TO_STD_OUT);

			loyalty_status=loyaltyCreationForm.isLoyaltyInList(setupLoyaltiesCreation.getName());
			
			if(loyalty_status==true)
			{
				Assert.assertTrue(loyalty_status);
				Reporter.log("Loyalty Program Created Succesfully!");
				
			}
			else
			{
				
				Assert.fail("The Loyalty Program creation Failed!");
				Reporter.log("Creation of Loyalty Program Failed!");
			}
			
			return loyalty_status;
	}

	public Boolean ManageExistingLoyaltyProgram(String jsonFilePath1_Loyalty,String jsonFileName1_Loyalty) throws JSONSException, FormException{
	
		
		Calendar startDate = Calendar.getInstance();
		
		Calendar endDate = Calendar.getInstance();
		
		Reporter.log("Management of \"Loyalty Creation Form\".", LOG_TO_STD_OUT);

		String resourcePath1 =DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath1_Loyalty;
			
		String resourceFile1 = jsonFileName1_Loyalty;
		
		setupLoyaltiesManagement = new LoyaltyManageCfg(resourcePath1, resourceFile1);

		LoyaltyManagmentForm = new LoyaltyManagmentForm(seleniumWebDriver,
				setupLoyaltiesManagement,TIMEOUT, ATTEMPT_TIMEOUT);

		
		Reporter.log("\"Loyalty Creation\" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath1, LOG_TO_STD_OUT);
		
		Reporter.log("Resource file -> " + resourceFile1, LOG_TO_STD_OUT);

		Reporter.log("Open \"Loyalty Creation Form\" UI.", LOG_TO_STD_OUT);

		
		JSONArray LoyaltyM =  setupLoyaltiesManagement.getList();
		
		
		for (int index = 0; index < LoyaltyM.length(); index++) {
			
			setupLoyaltiesManagement.setLoyaltyById( index );
			
			if( setupLoyaltiesManagement.getEnabled() ) {
			
				LoyaltyManagmentForm.Management();
						
				LoyaltyManagmentForm.clickEditBadgeCreation();
				
				LoyaltyManagmentForm.clickaddBadgeLoyaltyPrograms();
				
				LoyaltyManagmentForm.addBadgeLoyaltyProgramName(setupLoyaltiesManagement.getDefinitionName());
		
				LoyaltyManagmentForm.addBadgeLoyaltyProgramDesc(setupLoyaltiesManagement.getDefinitionDescription());
				
				LoyaltyManagmentForm.selectBadgeTypeName(setupLoyaltiesManagement.getDefinitionBadges());
				
				LoyaltyManagmentForm.openSchedulingTab();
				
				LoyaltyManagmentForm.setLoyaltySchedulingExecutionStart( startDate );
				
				LoyaltyManagmentForm.setLoyaltySchedulingExecutionEnd( endDate );
				
				LoyaltyManagmentForm.setLoyaltyRedeemDays(setupLoyaltiesManagement.getSchedulingRedeemDays() );
				
				LoyaltyManagmentForm.openAwardedTab();
				
				LoyaltyManagmentForm.addAwardedEventButton();
				
				LoyaltyManagmentForm.seteventType(setupLoyaltiesManagement.getAwardedEventType());
				
				LoyaltyManagmentForm.addAwardedCriteriaButton();
				
				LoyaltyManagmentForm.setcriteriaType(setupLoyaltiesManagement.getAwardedCriteriaType());
				
				LoyaltyManagmentForm.setcriteriaValue(setupLoyaltiesManagement.getAwardedCriteriaValue());
				
				LoyaltyManagmentForm.addAwardedActionButton();
				
				LoyaltyManagmentForm.setActionType(setupLoyaltiesManagement.getAwardedActionType());
				
				LoyaltyManagmentForm.setactionValue(setupLoyaltiesManagement.getAwardedOption());
				
				LoyaltyManagmentForm.clickAwardNotificationbutton();
				
				LoyaltyManagmentForm.clickAwardNotificationEditbutton();
				
				LoyaltyManagmentForm.AwardNotificationMessage(setupLoyaltiesManagement.getAwardedNotificationMessage());
				
				LoyaltyManagmentForm.openRedeemedTab();
				
				LoyaltyManagmentForm.addRedeemedEventButton();
				
				LoyaltyManagmentForm.seteventType(setupLoyaltiesManagement.getRedeemedEventType());
				
				LoyaltyManagmentForm.addAwardedCriteriaButton();
				
				LoyaltyManagmentForm.setcriteriaType(setupLoyaltiesManagement.getRedeemedCriteriaType());
				
				LoyaltyManagmentForm.setcriteriaValue(setupLoyaltiesManagement.getRedeemedCriteriaValue());
				
				LoyaltyManagmentForm.addAwardedActionButton();
				
				LoyaltyManagmentForm.setActionType(setupLoyaltiesManagement.getRedeemedActionType());
				
				LoyaltyManagmentForm.setactionValue(setupLoyaltiesManagement.getRedeemedOption());
				
				LoyaltyManagmentForm.clickAwardNotificationbutton();
				
				LoyaltyManagmentForm.clickAwardNotificationEditbutton();
				
				LoyaltyManagmentForm.AwardNotificationMessage(setupLoyaltiesManagement.getRedeemedNotificationMessage());
				
				LoyaltyManagmentForm.openActivationTab();
				
				LoyaltyManagmentForm.ActivateLoyaltyProgram();
				
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				loyalty_status=LoyaltyManagmentForm.isLoyaltyProgramInList(setupLoyaltiesManagement.getDefinitionName());
				
				LoyaltyManagmentForm.clickclosebutton();
				
				LoyaltyManagmentForm.clickActivatebutton("Program: BadgesProgram");
				
				LoyaltyManagmentForm.handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
				
				Reporter.log("Check general status of form", LOG_TO_STD_OUT);
				
				System.out.println("Loyalty_status"+ loyalty_status);
				
				if(loyalty_status==true)
				{
					Assert.assertTrue(loyalty_status);
					Reporter.log("Loyalty Program Created Succesfully!");
					
				}
				else
				{
					
					Assert.fail("The Loyalty Program creation Failed!");
					Reporter.log("Creation of Loyalty Program Failed!");
				}
		
			}
		}
	
		return loyalty_status;
		}
	
	
	public Boolean OperationManagement(String jsonFilePath_Traffic,String jsonFileName_Traffic) throws JSONSException, FormException{
		
				
			String resourcePath =DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_Traffic;
			
			String resourceFile = jsonFileName_Traffic;
			
			TrafficEvent = new TrafficGeneratorEvent(resourcePath, resourceFile);
			
			OperationManagementForm<TrafficGeneratorEvent> operationsForm = new OperationManagementForm<TrafficGeneratorEvent>(seleniumWebDriver,
					TrafficEvent, TIMEOUT, ATTEMPT_TIMEOUT);
		
			JSONArray TM =  TrafficEvent.getList();
			
			
			for (int index = 0; index < TM.length(); index++) {
				
				TrafficEvent.setTrafficById( index );
				
				if( TrafficEvent.getEnabled() ) {
		
			operationsForm.open();

			operationsForm.clickTrafficGenerator();
			
			operationsForm.SetSubscriberId(TrafficEvent.getSubscriberID());
			
			operationsForm.SetSource(TrafficEvent.getSource());
			
			operationsForm.SetEvent(TrafficEvent.getEventClass());
			
			operationsForm.clickAddEventParameterButton();
			
			operationsForm.SetEventParameterName(TrafficEvent.getParametername());
			
			operationsForm.SetEventParameterValue(TrafficEvent.getParametervalue());
			
			operationsForm.clickApplyButton();
			
			operationsForm.clickEventExecuteButton();
			
			operationsForm.handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
			
			Reporter.log("Open \"Traffic Generator Form\" UI.", LOG_TO_STD_OUT);

			Assert.assertTrue(true, "Event Generated");
						
		}
			}
		return LOG_TO_STD_OUT;
		}
		
	public Boolean CustomerCare() throws FormException{
			seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			CustomerCareLoyaltyForm customerCareLoyaltyForm = new CustomerCareLoyaltyForm(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
				customerCareLoyaltyForm.open();
				customerCareLoyaltyForm.setSubscriberMsisdn("9717207081");
				customerCareLoyaltyForm.clickSearchButton();
				customerCareLoyaltyForm.openLoyaltyTab();
				boolean status =customerCareLoyaltyForm.isRedeemStatusPresent("BadgesLoyaltyProgram");
				if(status==true)
				{
					Assert.assertTrue(true, "Subscriber has loyalty with Redeemed status");
				}
				else
				{
					Assert.fail("Subscriber's loyalty is not redeemed");
				}
				customerCareLoyaltyForm.closeAngularFrame();
				return status;
			}
		
		}
	
	

	

