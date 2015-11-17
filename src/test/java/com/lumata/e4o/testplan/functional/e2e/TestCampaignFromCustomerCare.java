package com.lumata.e4o.testplan.functional.e2e;

import static com.lumata.e4o.gui.campaignmanager.CampaignsForm.TargetingMode.Restricted;
import static com.lumata.e4o.gui.common.NotificationForm.NotificationChannel.SMS;
import static com.lumata.e4o.gui.common.NotificationForm.NotificationTongue.English;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bsh.ParseException;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.campaignmanager.CampaignModelFormOld;
import com.lumata.e4o.gui.campaignmanager.CampaignsForm;
import com.lumata.e4o.gui.customercare.CustomerCareCampaignsForm;
import com.lumata.e4o.gui.customercare.CustomerCareCreateSubscriberForm;
import com.lumata.e4o.gui.customercare.CustomerCareHistoryForm;
import com.lumata.e4o.gui.customercare.CustomerCareProfileForm;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.schema.global.NotifLogs;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(@TCOwner(name = "Isha Vyas", email = "isha.vyas@lumatagroup.com"))
@TCSeleniumWebDriver
@TCMysqlMaster
/**
 * This Automation covers End to End flow of CustomerCareCampaign Form.
 *
 */
public class TestCampaignFromCustomerCare extends ParentTestCase {
	private static final Logger logger = LoggerFactory
			.getLogger(TestCampaignFromCustomerCare.class);
	private CustomerCareCreateSubscriberForm customerCareCreateSubscriberForm;
	private CampaignsForm campaignsForm;
	private CampaignModelFormOld campaignModelForm;
	private Subscribers subscriber = null;
	private JSONCampaignModel campaignModelJson = null;
	private String campaignModelName = null;
	private String campaign_name = null;
	private final Long MSISDN = 44871004956L;
	private final String STATUS = "active (prepaid)";
	private final String RATE_PLAN_NAME = "FUN";
	private final String INTAG = "QAIN";
	private final Long IMSI = 990888L;
	private final Long IMEI = 4567L;
	private final String TONGUE = "ENG";
	private final Date date = Calendar.getInstance().getTime();
	private NotifLogs notiflogs = null;
	Boolean status = false;
	int TIMEOUT = 60000;
	int ATTEMPT_TIMEOUT = 200;

	@BeforeClass
	@Parameters({ "campaignModel_jsonFilePath", "campaignModel_jsonFileName" })
	public void initEnv(
			@Optional("input/campaignmanager/campaignModels") String campaignModel_jsonFilePath,
			@Optional("endToEndCampaignModelForCustomerCare") String campaignModel_jsonFileName)
			throws FormException, JSONException, ParseException, JSONSException {
		// Create Subscriber
		createSubscriber();
		// Create CampaignModel
		createModel(campaignModel_jsonFilePath, campaignModel_jsonFileName);
		// Create and Activate Campaign for subscriber created
		createAndActivateCampaign();

	}

	/**
	 * UC18-01 Create a campaign for existing subscriber and verified activated
	 * campaign under campaign tab of customer care
	 */
	@Test(enabled = TEST_ENABLED, priority = 1)
	public void testUc1801_verifyCampaignStatus() throws FormException {
		CustomerCareCampaignsForm customerCareCampaignsForm = new CustomerCareCampaignsForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		customerCareCampaignsForm.open();
		customerCareCampaignsForm.clickClearButton();
		customerCareCampaignsForm.setSubscriberMsisdn(String.valueOf(MSISDN));
		customerCareCampaignsForm.clickSearchButton();
		customerCareCampaignsForm.openCampaignTab();
		status = customerCareCampaignsForm
				.isActivatedStatePresent(getCampaignName());
		if (status == true) {
			Assert.assertTrue(true, "Subscriber has activated campaign");
			Reporter.log("Subscriber has activated campaign");
		} else {
			AssertJUnit.fail("Subscriber has not activated campaign");
			Reporter.log("Subscriber has not activated campaign");
		}

	}

	/**
	 * UC22-03 Verify Notification history under history tab of customer care
	 * for particular time window
	 */
	@Test(enabled = TEST_ENABLED, priority = 2)
	public void testUc2203_verifyNotificationHistoryTab() throws FormException {
		CustomerCareHistoryForm customerCareHistoryForm = new CustomerCareHistoryForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		customerCareHistoryForm.clickSearchButton();
		customerCareHistoryForm.openHistoryTab();
		customerCareHistoryForm.clickNotificationHistoryRefreshButton();
		String status = customerCareHistoryForm
				.getNotificationHistoryStatus(getCampaignName());
		String strChannelName = customerCareHistoryForm
				.getChannel(getCampaignName());
		Assert.assertEquals(status, "NOT_SENT");
		Assert.assertEquals(strChannelName, "SMS");
		Reporter.log("Status verified with Channel", LOG_TO_STD_OUT);
	}

	/*
	 * UC22-01 Verify bonus detail under history tab of customer care for
	 * particular time window
	 */
	@Test(enabled = TEST_ENABLED, priority = 3)
	public void testUc2201_verifyBonusDetailHistoryTab() throws FormException {
		CustomerCareHistoryForm customerCareHistoryForm = new CustomerCareHistoryForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		customerCareHistoryForm.openHistoryTab();
		customerCareHistoryForm.clickBonusDetailsRefreshButton();
		String strRewardName = customerCareHistoryForm
				.getRewardName(getCampaignName());
		String strRewardQuantity = customerCareHistoryForm
				.getRewardQuantity(getCampaignName());
		String strOperationType = customerCareHistoryForm
				.getOperationType(getCampaignName());
		Assert.assertEquals(strRewardName, "Points");
		Assert.assertEquals(strRewardQuantity, "10");
		Assert.assertEquals(strOperationType, "credit");
		Reporter.log(
				"Bonus as points verified in Bonus Details section of Historytab of CustomerCare",
				LOG_TO_STD_OUT);
	}

	private void createSubscriber() throws FormException {
		customerCareCreateSubscriberForm = new CustomerCareCreateSubscriberForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		CustomerCareProfileForm customerCareProfileForm = new CustomerCareProfileForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);

		Reporter.log("Open CustomerCare Tab.", LOG_TO_STD_OUT);
		customerCareCreateSubscriberForm.open();
		subscriber = DAOSubscribers.getInstance(mysqlMaster).getSubscriber(
				MSISDN);
		if (null == subscriber) {
			// Enter msisdn
			Reporter.log("Enter subscriber id", LOG_TO_STD_OUT);
			customerCareCreateSubscriberForm.setSubscriberMsisdn(String
					.valueOf(MSISDN));
			// Click AddButton
			customerCareCreateSubscriberForm.clickCustomerCareAddButton();
			customerCareCreateSubscriberForm.selectRatePlan(RATE_PLAN_NAME);
			customerCareCreateSubscriberForm.selectStatus(STATUS);
			customerCareCreateSubscriberForm.selectInTag(INTAG);
			customerCareCreateSubscriberForm.enterImsi(String.valueOf(IMSI));
			customerCareCreateSubscriberForm.enterImei(String.valueOf(IMEI));
			// Enter Language
			customerCareCreateSubscriberForm.enterLanguage(TONGUE);
			// Click add button
			customerCareCreateSubscriberForm.clickCustomerCareCreateAdd();

			customerCareProfileForm.open();
			customerCareProfileForm.clickChannel();
			customerCareProfileForm.clickAddChannelButton();
			customerCareProfileForm.addChannel("SMS", String.valueOf(MSISDN),
					"Active");

			Reporter.log("Subscriber created successfully");
		}
	}

	private void createModel(String campaignModel_jsonFilePath,
			String campaignModel_jsonFileName) throws FormException,
			JSONException, ParseException, JSONSException {

		Boolean status = false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Model Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT
				+ campaignModel_jsonFilePath;
		String resourceFile = campaignModel_jsonFileName;

		Reporter.log("\"Campaign Model \" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		campaignModelJson = new JSONCampaignModel(resourcePath, resourceFile);
		campaignModelForm = new CampaignModelFormOld(seleniumWebDriver,
				campaignModelJson, TIMEOUT, ATTEMPT_TIMEOUT);

		campaignModelForm.openForm();
		JSONArray campaignModels = campaignModelJson.getList();

		for (int camapignModelIndex = 0; camapignModelIndex < campaignModels
				.length(); camapignModelIndex++) {

			campaignModelJson.setCampaignModelById(camapignModelIndex);

			if (campaignModelJson.getEnabled()) {

				campaignModelForm.addBtn();
				setCampaignModelName(Format.addTimestamp(getCampaignModelJson()
						.getName() + "_"));

				campaignModelForm.configureCampaignModel(
						getCampaignModelName(),
						campaignModelJson.getDescription(),
						campaignModelJson.getcampaignType(),
						campaignModelJson.getuseHierarchy());
				Map<String, JSONEvent_> events = campaignModelJson.getEvents();
				campaignModelForm.addEvents(events);
				campaignModelForm.saveCampaignModel();

				campaignModelForm.confirmCampaignModelAlert(status);
				status = campaignModelForm
						.isCampaignModelNameInList(getCampaignModelName());
				if (status == true) {
					AssertJUnit.assertTrue(
							"Campaign Model created successfully", true);
					Reporter.log("Campaign Model created successfully",
							LOG_TO_STD_OUT);
				} else {
					Assert.fail("campaign model didn't create successfully");
					Reporter.log("Campaign Model didn't create successfully",
							LOG_TO_STD_OUT);
				}
			}
		}
	}

	private void createAndActivateCampaign() throws FormException {

		campaignsForm = new CampaignsForm(seleniumWebDriver, TIMEOUT,
				ATTEMPT_TIMEOUT);
		Calendar endDate = Calendar.getInstance();

		Calendar provEndDate = (Calendar) endDate.clone();
		setCampaignName(Format.addTimestamp("Campaign_"));
		campaignsForm
				.openForm()
				.addBtn()
				.openDefinitionTab()
				.setCampaignExecutionModeModel()
				.setCampaignModel(getCampaignModelName())
				.setCampaignName(getCampaignName())
				.setCampaignDescription(getCampaignName() + " description")
				.setByPassMediaType(false)
				.
				/** configure single scheduling tab **/
				openSchedulingTab()
				.setCampaignSingleSchedulingType()
				.setCampaignSingleSchedulingExecutionEndRelative(101)
				.

				/** configure dialog tab **/
				openDialogTab()
				.openDialogueNotification()
				.editDialogueNotification(English, SMS)
				.setDialogueNotificationMessage("campaign notification message")
				.saveDialogueNotificationEditing().saveDialogueNotification().confirmDialog();
				/** configure target tab **/
		campaignsForm.openTargetTab().setCampaignTargetTargetingMode(Restricted)
				.setCampaignTargetTargetingRestrictedModeCriteria()
				.setCampaignTargetTargetingRestrictedConfigureASampleNoSample()
				.
				/** configure activation tab **/
				openActivationTab().activateBtn().confirmCampaignActivation();
		Boolean campaign_status = campaignsForm
				.isCampaignNameInList(getCampaignName());
		Reporter.log("Creation of \"Campaign Form\".", LOG_TO_STD_OUT);

		if (campaign_status == true) {
			Assert.assertTrue(campaign_status);
			Reporter.log("Campaign created and activated Succesfully!");

		} else {
			Assert.fail("The Campaign creation Failed!");
			Reporter.log("Creation of Campaign Failed!");
		}

	}

	public JSONCampaignModel getCampaignModelJson() {
		return this.campaignModelJson;
	}

	public void setCampaignModelName(String campaignModelName) {
		this.campaignModelName = campaignModelName;
	}

	public String getCampaignModelName() {
		return this.campaignModelName;
	}

	public String getCampaignName() {
		return this.campaign_name;
	}

	public void setCampaignName(String campName) {
		this.campaign_name = campName;
	}

}
