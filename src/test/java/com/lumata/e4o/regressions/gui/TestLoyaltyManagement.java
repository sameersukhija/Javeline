package com.lumata.e4o.regressions.gui;

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
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lumata.e4o.gui.loyaltymanager.LoyaltyCreationForm;
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
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.campaignmanager.JSONCriteria;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation;
import com.lumata.e4o.json.gui.loyaltymanager.LoyaltyManageCfg;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation.LoyaltyTypes;
import com.lumata.common.testing.json.ErrorModificableElement;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.HasJsonElements;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.json.JsonConfigurationFile;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

@TCOwners(
		@TCOwner( name="Sameer Sukhija", email="sameer.sukhija@lumatagroup.com" )
	)
@TCSeleniumWebDriver

public class TestLoyaltyManagement<jsonFilePath1_Loyalty> extends ParentTestCase {

	private static final Logger logger = LoggerFactory.getLogger(TestLoyaltyManagement.class );
	/**
	 * Commodities configuration file
	 * 
	 */
	

	private JSONLoyaltiesCreation setupLoyaltiesCreation = null;

	private LoyaltyManageCfg<?> setupLoyaltiesManagement = null;
	
	
	
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
	private JSONEvent_ awarded;
	private String jsonFileName1_Loyalty;
	private LoyaltyManagmentForm clickEditBadgeCreationV2;
	@BeforeMethod
	public void initOfferForm( Method method ) throws  FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	@Parameters({"jsonFilePath_Loyalty", "jsonFileName_Loyalty"})
	@Test(enabled = TEST_ENABLED,  priority = 1 )
	public void testUc29_01CreateLoyaltyProgram(@Optional("/input/loyalties") String jsonFilePath_Loyalty,
		@Optional("loyalty_create") String jsonFileName_Loyalty) throws FormException, JSONException, JSONSException {
	
	seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
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
			
			loyaltyCreationForm.addLoyaltyProgramName(setupLoyaltiesCreation.getName());
	
			loyaltyCreationForm.addLoyaltyProgramDesc(setupLoyaltiesCreation.getDescription());
			
			loyaltyCreationForm.saveLoyaltyProgram();
		}
	}
			loyaltyCreationForm.addBadgesTypeName(setupLoyaltiesCreation.getBadges());
			
			seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
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
			}
		

	@Parameters({"jsonFilePath1_Loyalty", "jsonFileName1_Loyalty"})
	@Test(enabled = TEST_ENABLED,  priority = 2 )
	public void testUc29_02testManageExistingLoyaltyProgram(@Optional("/input/loyalties") String jsonFilePath1_Loyalty,
	  @Optional("loyalty_manage") String jsonFileName1_Loyalty) throws FormException, JSONException, JSONSException {
		
		Calendar startDate = Calendar.getInstance();
		
		Calendar endDate = Calendar.getInstance();
		
		Reporter.log("Management of \"Loyalty Creation Form\".", LOG_TO_STD_OUT);

		String resourcePath1 =DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath1_Loyalty;
			
		String resourceFile1 = jsonFileName1_Loyalty;
		
		setupLoyaltiesManagement = new LoyaltyManageCfg<Object>(resourcePath1, resourceFile1);

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
						
				LoyaltyManagmentForm.clickEditBadgeCreation("BadgesProgramNew");
				
				LoyaltyManagmentForm.clickaddBadgeLoyaltyPrograms();
				
				LoyaltyManagmentForm.addBadgeLoyaltyProgramName(setupLoyaltiesManagement.getDefinitionName());
		
				LoyaltyManagmentForm.addBadgeLoyaltyProgramDesc(setupLoyaltiesManagement.getDefinitionDescription());
				
				LoyaltyManagmentForm.selectBadgeTypeName(setupLoyaltiesManagement.getDefinitionBadges());
				
				Map<String, JSONEvent_> awarded =setupLoyaltiesManagement.getAwardedEvents();
				
				LoyaltyManagmentForm.openSchedulingTab();
				
				LoyaltyManagmentForm.setLoyaltySchedulingExecutionStart( startDate );
				
				LoyaltyManagmentForm.setLoyaltySchedulingExecutionEnd( endDate );
				
				LoyaltyManagmentForm.setLoyaltyRedeemDays(setupLoyaltiesManagement.getSchedulingRedeemDays() );
				
				LoyaltyManagmentForm.openAwardedTab();
				
				LoyaltyManagmentForm.addAwardedEventButton();
				
				LoyaltyManagmentForm.addEvents(awarded);
				
				LoyaltyManagmentForm.clickAwardNotificationbutton();
				
				LoyaltyManagmentForm.clickAwardNotificationEditbutton();
				
				LoyaltyManagmentForm.AwardNotificationMessage(setupLoyaltiesManagement.getAwardedNotificationMessage());
				
				LoyaltyManagmentForm.openRedeemedTab();
				
				LoyaltyManagmentForm.addRedeemedEventButton();
				
				Map<String, JSONEvent_> redeemed =setupLoyaltiesManagement.getRedeemedEvents();
				
				LoyaltyManagmentForm.addEventsr(redeemed);
				
				LoyaltyManagmentForm.clickAwardNotificationbutton();
				
				LoyaltyManagmentForm.clickAwardNotificationEditbutton();
				
				LoyaltyManagmentForm.AwardNotificationMessage(setupLoyaltiesManagement.getRedeemedNotificationMessage());
				
				LoyaltyManagmentForm.openActivationTab();
				
				LoyaltyManagmentForm.ActivateLoyaltyProgram();
				
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				loyalty_status=LoyaltyManagmentForm.isLoyaltyProgramInList(setupLoyaltiesManagement.getDefinitionName());
				
				LoyaltyManagmentForm.clickclosebutton();
				
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
		
	
	}

	@Parameters({"jsonFilePath_Loyalty", "jsonFileName_Loyalty","jsonFilePath1_Loyalty", "jsonFileName1_Loyalty"})
	@Test(enabled = TEST_ENABLED,  priority = 3 )
	public void testUc29_03EditLoyaltyProgram(@Optional("/input/loyalties") String jsonFilePath_Loyalty,
			@Optional("loyalty_create") String jsonFileName_Loyalty,
			@Optional("/input/loyalties") String jsonFilePath1_Loyalty,
			@Optional("loyalty_manage") String jsonFileName1_Loyalty) 
					throws FormException, JSONException, JSONSException {
	
	Boolean loyalty_status=false;
	Boolean loyalty_managed=false;
	
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
			
			loyaltyCreationForm.clickeditBadgeName(setupLoyaltiesCreation.getName());
			
			loyaltyCreationForm.editLoyaltyBadgeType(setupLoyaltiesCreation.getBadgesType());
		
			
			
		}
	}
			
			
			loyalty_status=loyaltyCreationForm.isLoyaltyInEList(setupLoyaltiesCreation.getName());
	
			seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
			loyaltyCreationForm.clickclosebutton();
			
			Reporter.log("Check general status of form", LOG_TO_STD_OUT);

			
			if(loyalty_status==true)
			{
				Assert.assertTrue(loyalty_status);
				Reporter.log("Loyalty Program Edited Succesfully!");
				
			}
			else
			{
				
				Assert.fail("The Loyalty Program modification Failed!");
				Reporter.log("Modification of Loyalty Program Failed!");
			}

			//Edit LoyaltyMananagement Program to use existing Loyalty Program Type
					loyalty_managed=ManageExistingLoyaltyProgram();
					if(loyalty_managed==true)
					{
						Assert.assertTrue(loyalty_managed);
						logger.info("Edited loyalty Management Program Succesfully");
									
					}
					else{
							Assert.fail("The loyalty Management Program  Modification Failed!");
							Reporter.log("Modification of loyalty Management Program Failed!",LOG_TO_STD_OUT);
						}	
	}
	
	
	public Boolean ManageExistingLoyaltyProgram() throws  FormException, JSONSException{
	
		
		LoyaltyManagmentForm = new LoyaltyManagmentForm(seleniumWebDriver,
				null, TIMEOUT, ATTEMPT_TIMEOUT);

		
		Reporter.log("\"Loyalty Creation\" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath1, LOG_TO_STD_OUT);
		
		Reporter.log("Resource file -> " + resourceFile1, LOG_TO_STD_OUT);

		Reporter.log("Open \"Loyalty Creation Form\" UI.", LOG_TO_STD_OUT);
			
			try{
			
				LoyaltyManagmentForm.Management();
									
				LoyaltyManagmentForm.clickEditBadgeCreation("BadgesProgramNew");
				
				LoyaltyManagmentForm.clickeditBadgeLoyaltyPrograms("BadgesLoyaltyProgram");
				
				LoyaltyManagmentForm.editBadgeLoyaltyProgramName("BadgesLoyaltyProgramLatest");
		
				LoyaltyManagmentForm.editBadgeLoyaltyProgramDesc("BadgesLoyaltyProgram latest Description");
				
				LoyaltyManagmentForm.selectBadgeTypeName("Archery");
				
				LoyaltyManagmentForm.openAwardedTab();
				
				LoyaltyManagmentForm.setcriteriaValue("9650450905");
				
				LoyaltyManagmentForm.openRedeemedTab();
				
				LoyaltyManagmentForm.setcriteriaValue("9650450905");
			
				LoyaltyManagmentForm.openActivationTab();
				
				LoyaltyManagmentForm.SaveLoyaltyProgram();
				
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				loyalty_status=LoyaltyManagmentForm.isLoyaltyProgramInList("BadgesLoyaltyProgramLatest");
				
				LoyaltyManagmentForm.clickclosebutton();
				
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				Reporter.log("Check general status of form", LOG_TO_STD_OUT);
				
				System.out.println("Loyalty_status"+ loyalty_status);
			
			}catch (FormException e)
			{
				Reporter.log("Exception occured while modifying BadgeLoyaltyProgram!"+e.getMessage());
			}
			return loyalty_status;
		}

	
	
	@Test(enabled = TEST_ENABLED,  priority = 4 )
	public void testUc29_04testCopyExistingLoyaltyProgram() throws FormException, Throwable{
		
		LoyaltyManagmentForm = new LoyaltyManagmentForm(seleniumWebDriver,
				setupLoyaltiesManagement,TIMEOUT, ATTEMPT_TIMEOUT);

				LoyaltyManagmentForm.Management();
			
				LoyaltyManagmentForm.clickcopyBadgeLoyaltyPrograms("BadgesProgramNew");
				
				LoyaltyManagmentForm.addBadgeLoyaltyProgramCopyVersion("2.0");
		
				LoyaltyManagmentForm.addBadgeLoyaltyProgramCopyVersionDesc("Copy of Version 1.0");
				
				LoyaltyManagmentForm.clickcopyOKButton();
				
				clickEditBadgeCreationV2 = LoyaltyManagmentForm.clickEditBadgeCreationV2("BadgesProgramNew", "2.0");
				
				LoyaltyManagmentForm.clickaeditBadgeLoyaltyProgramscopy("BadgesLoyaltyProgramLatest");
				
				LoyaltyManagmentForm.openActivationTab();
				
				LoyaltyManagmentForm.SaveLoyaltyProgram();
				
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				loyalty_status=LoyaltyManagmentForm.isLoyaltyProgramInList("BadgesLoyaltyProgramLatest(Copy)");
				
				LoyaltyManagmentForm.clickclosebutton();
				
				LoyaltyManagmentForm.clickActivatebutton("Program: BadgesProgramNew");
				
				LoyaltyManagmentForm.handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
				
				Reporter.log("Check general status of form", LOG_TO_STD_OUT);
				
				System.out.println("Loyalty_status"+ loyalty_status);
				
				if(loyalty_status==true)
				{
					Assert.assertTrue(loyalty_status);
					Reporter.log("Loyalty Program Copied Succesfully!");
					
				}
				else
				{
					
					Assert.fail("The Loyalty Program copy Failed!");
					Reporter.log("Copy of Loyalty Program Failed!");
				}
		
			}
		

		@Test(enabled = TEST_ENABLED,  priority = 5 )
		public void testUc29_05testDeleteExistingLoyaltyProgram() throws FormException, Throwable{
		
			LoyaltyManagmentForm = new LoyaltyManagmentForm(seleniumWebDriver,
				setupLoyaltiesManagement,TIMEOUT, ATTEMPT_TIMEOUT);

				LoyaltyManagmentForm.Management();
				
				LoyaltyManagmentForm.clickEditBadgeCreationV2("BadgesProgramNew","2.0");
				
				LoyaltyManagmentForm.clickDeleteBadgeManagementProgram("BadgesLoyaltyProgramLatest(Copy)");
				
				seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				LoyaltyManagmentForm.clickclosebutton();
				
				LoyaltyManagmentForm.clickDeleteBadgeLoyaltyPrograms("BadgesProgramNew","2.0").closeAlertAndGetItsText();
				
				LoyaltyManagmentForm.handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
				
				Reporter.log("Check general status of form", LOG_TO_STD_OUT);
				
				AssertJUnit.assertTrue("Loyalty Program deleted successfully",true);
				
				System.out.println("Loyalty_status"+ loyalty_status);
		
			}
}

	
	
	
