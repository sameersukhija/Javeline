package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import com.lumata.e4o.gui.customercare.CustomerCareLoyaltyForm;
import com.lumata.e4o.gui.loyaltymanager.LoyaltyCreationForm;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.loyaltymanager.LoyaltyCreationForm;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation.LoyaltyTypes;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.ErrorModificableElement;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
	)
@TCSeleniumWebDriver

public class TestLoyaltyManagement extends ParentTestCase {

	/**
	 * Commodities configuration file
	 * 
	 */
	

	private JSONLoyaltiesCreation setupLoyaltiesCreation = null;

	private LoyaltyCreationForm loyaltyCreationForm = null;

	private List<String> element2fill;

	public String Loyalty_NAME;

	Boolean loyalty_status;

	private Map<String, JSONEvent_> events;

	private JSONLoyaltiesCreation LoyaltiesCreation;
	
	@BeforeMethod
	public void initOfferForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	@Parameters({"jsonFilePath_Loyalty", "jsonFileName_Loyalty"})
	@Test(enabled = TEST_ENABLED,  priority = 1 )
	public void testUc29_01CreateLoyaltyProgram(@Optional("/input/loyalties") String jsonFilePath_Loyalty,@Optional("loyaltyCreationTemplate") String jsonFileName_Loyalty) throws FormException, JSONException, JSONSException {
		
		Reporter.log("Creation of \"Loyalty Creation Form\".", LOG_TO_STD_OUT);

		String resourcePath =DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_Loyalty;
			
		String resourceFile = "loyaltyCreationTemplate";
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
				
				loyaltyCreationForm.clickaddnewBadge();
				
				loyaltyCreationForm.clickaddLoyaltyPrograms();
				
				loyaltyCreationForm.addLoyaltyProgramName(setupLoyaltiesCreation.getName());
		
				loyaltyCreationForm.addLoyaltyProgramDesc(setupLoyaltiesCreation.getDescription());
				
				loyaltyCreationForm.saveLoyaltyProgram();
				
				loyaltyCreationForm.addBadgesTypeName(setupLoyaltiesCreation.getBadges());
				
				loyaltyCreationForm.clickclosebutton();
				
				Reporter.log("Check general status of form", LOG_TO_STD_OUT);

				loyalty_status=loyaltyCreationForm.isLoyaltyInList("BadgesProgram");
				
				System.out.println("Loyalty_status"+ loyalty_status);
				if(loyalty_status==true)
				{
					Assert.assertTrue(loyalty_status);
					Reporter.log("Loyalty Program Created Succesfully!");
					
				}
				else
				{
					
					AssertJUnit.fail("The Loyalty Program creation Failed!");
					Reporter.log("Creation of Loyalty Program Failed!");
				}
	}
	}
	}

	@Parameters({"jsonFilePath1_Loyalty", "jsonFileName1_Loyalty"})
	@Test(enabled = TEST_ENABLED,  priority = 2 )
	public void testUc29_02ManageExistingLoyaltyProgram(@Optional("/input/loyalties") String jsonFilePath1_Loyalty,@Optional("loyalty_manage") String jsonFileName_Loyalty) throws FormException, JSONException, JSONSException {
		
		Calendar startDate = Calendar.getInstance();
		
		Calendar endDate = Calendar.getInstance();
		
		Reporter.log("Management of \"Loyalty Creation Form\".", LOG_TO_STD_OUT);

		String resourcePath =DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath1_Loyalty;
			
		String resourceFile = "loyalty_manage";
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

				loyaltyCreationForm.Management();
				
				loyaltyCreationForm.clickEditBadgeCreation();
				
				loyaltyCreationForm.clickaddBadgeLoyaltyPrograms();
				
				loyaltyCreationForm.addBadgeLoyaltyProgramName(setupLoyaltiesCreation.getName());
		
				loyaltyCreationForm.addBadgeLoyaltyProgramDesc(setupLoyaltiesCreation.getDescription());
				
				loyaltyCreationForm.selectBadgeTypeName("Astronomy");
				
				loyaltyCreationForm.openSchedulingTab();
				
				loyaltyCreationForm.setLoyaltySchedulingExecutionStart( startDate );
				
				loyaltyCreationForm.setLoyaltySchedulingExecutionEnd( endDate );
				
				loyaltyCreationForm.setLoyaltyRedeemDays( "1" );
				
				loyaltyCreationForm.openAwardedTab();
				
				loyaltyCreationForm.addAwardedEventButton();
				
				loyaltyCreationForm.seteventType(setupLoyaltiesCreation.geteventType());
				
				loyaltyCreationForm.addAwardedCriteriaButton();
				
				loyaltyCreationForm.setcriteriaType(setupLoyaltiesCreation.getcriteriaType());
				
				loyaltyCreationForm.setcriteriaValue(setupLoyaltiesCreation.getcriteriaValue());
				
				loyaltyCreationForm.addAwardedActionButton();
				
				loyaltyCreationForm.setActionType(setupLoyaltiesCreation.getactionType());
				
				loyaltyCreationForm.setactionValue(setupLoyaltiesCreation.getactionValue());
				
				loyaltyCreationForm.clickAwardNotificationbutton();
				
				loyaltyCreationForm.clickAwardNotificationEditbutton();
				
				loyaltyCreationForm.AwardNotificationMessage("Loyalty Award Notification");
				
				loyaltyCreationForm.openRedeemedTab();
				
				loyaltyCreationForm.addRedeemedEventButton();
				
				loyaltyCreationForm.seteventType(setupLoyaltiesCreation.geteventType());
				
				loyaltyCreationForm.addAwardedCriteriaButton();
				
				loyaltyCreationForm.setcriteriaType(setupLoyaltiesCreation.getcriteriaType());
				
				loyaltyCreationForm.setcriteriaValue(setupLoyaltiesCreation.getcriteriaValue());
				
				loyaltyCreationForm.addAwardedActionButton();
				
				loyaltyCreationForm.setActionType(setupLoyaltiesCreation.getactionType());
				
				loyaltyCreationForm.setactionValue(setupLoyaltiesCreation.getactionValue());
				
				loyaltyCreationForm.clickAwardNotificationbutton();
				
				loyaltyCreationForm.clickAwardNotificationEditbutton();
				
				loyaltyCreationForm.AwardNotificationMessage("Loyalty Redemption Notification");
				
				loyaltyCreationForm.openActivationTab();
				
				loyaltyCreationForm.ActivateLoyaltyProgram();
				
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				loyalty_status=loyaltyCreationForm.isLoyaltyProgramInList("BadgesLoyaltyProgram");
				
				loyaltyCreationForm.clickclosebutton();
				
				loyaltyCreationForm.clickActivatebutton();
				
				loyaltyCreationForm.handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
				
				Reporter.log("Check general status of form", LOG_TO_STD_OUT);
				
				System.out.println("Loyalty_status"+ loyalty_status);
				
				if(loyalty_status==true)
				{
					Assert.assertTrue(loyalty_status);
					Reporter.log("Loyalty Program Created Succesfully!");
					
				}
				else
				{
					
					AssertJUnit.fail("The Loyalty Program creation Failed!");
					Reporter.log("Creation of Loyalty Program Failed!");
				}
			}
		}
	}
	
	
		@Test(enabled = TEST_ENABLED,  priority = 3)
		public void testUc29_03OperationManagement() throws FormException {
			
			OperationManagementForm operationsForm = new OperationManagementForm(seleniumWebDriver,
					setupLoyaltiesCreation, TIMEOUT, ATTEMPT_TIMEOUT);
			operationsForm.open();

			operationsForm.clickTrafficGenerator();
			
			operationsForm.SetSubscriberId("9717207081");
			
			operationsForm.SetSource("REVENUE_RECHARGE_CDR");
			
			operationsForm.SetEvent("revenue");
			
			operationsForm.clickAddEventParameterButton();
			
			operationsForm.SetEventParameterName("recharge");
			
			operationsForm.SetEventParameterValue("1");
			
			operationsForm.clickApplyButton();
			
			operationsForm.clickEventExecuteButton();
			
			operationsForm.handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
			
			Reporter.log("Open \"Traffic Generator Form\" UI.", LOG_TO_STD_OUT);

			Assert.assertTrue(true, "Event Generated");
					
		}
		
		@Test(enabled = TEST_ENABLED, priority = 4 )
		public void testUc29_04_CustomerCare() throws FormException{
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
					AssertJUnit.fail("Subscriber's loyalty is not redeemed");
				}
				customerCareLoyaltyForm.closeAngularFrame();
			}
		}

			
	

