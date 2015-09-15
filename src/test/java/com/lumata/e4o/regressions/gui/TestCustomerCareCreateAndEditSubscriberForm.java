package com.lumata.e4o.regressions.gui;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.customercare.CustomerCareCreateSubscriberForm;
import com.lumata.e4o.gui.customercare.CustomerCareForm;
import com.lumata.e4o.gui.customercare.CustomerCareProfileForm;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(@TCOwner(name = "Isha Vyas", email = "isha.vyas@lumatagroup.com"))
@TCSeleniumWebDriver
@TCMysqlMaster
/*
 * This automation covers create subscriber and edit subscriber under
 * CustomerCare Section
 */
public class TestCustomerCareCreateAndEditSubscriberForm extends ParentTestCase {
	private static final Logger logger = LoggerFactory
			.getLogger(TestCustomerCareCreateAndEditSubscriberForm.class);
	private CustomerCareCreateSubscriberForm customerCareCreateSubscriberForm;
	private CustomerCareForm customerCareForm;
	private Subscribers subscriber = null;
	private final Long MSISDN = 22368919522L;
	private final String STATUS = "active (prepaid)";
	private final String RATE_PLAN_NAME = "FUN";
	private final String INTAG = "QAIN";
	private final Long IMSI = 990888L;
	private final Long IMEI = 4567L;
	private final String TONGUE = "ENG";
	private final Date date = Calendar.getInstance().getTime();

	/**
	 * UC13-01 testcase which covers creation of subscriber in customercare
	 * section and verifying it on GUI and database.
	 */
	@Test(enabled = TEST_ENABLED, priority = 1)
	public void testUc1301_CreateSubscriber() throws FormException, Exception {

		int TIMEOUT = 60000;
		int ATTEMPT_TIMEOUT = 200;
		customerCareCreateSubscriberForm = new CustomerCareCreateSubscriberForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		customerCareForm = new CustomerCareForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		Reporter.log("Open CustomerCare Tab.", LOG_TO_STD_OUT);
		customerCareForm.open();
		// Enter msisdn
		Reporter.log("Enter subscriber id", LOG_TO_STD_OUT);
		customerCareForm.setSubscriberMsisdn(String.valueOf(MSISDN));
		// Click AddButton
		customerCareForm.clickCustomerCareAddButton();
		customerCareCreateSubscriberForm.selectRatePlan(RATE_PLAN_NAME);
		customerCareCreateSubscriberForm.selectStatus(STATUS);
		customerCareCreateSubscriberForm.selectInTag(INTAG);
		customerCareCreateSubscriberForm.enterImsi(String.valueOf(IMSI));
		customerCareCreateSubscriberForm.enterImei(String.valueOf(IMEI));
		// Enter Language
		customerCareCreateSubscriberForm.enterLanguage(TONGUE);
		// Click add button
		customerCareCreateSubscriberForm.clickCustomerCareCreateAdd();
		customerCareForm.clickClearButton();
		if (customerCareForm.subscriberPhoneNumberExists(null,
				String.valueOf(MSISDN))) {
			Assert.assertTrue(true,
					"Subscriber created successfully and exists");
			Reporter.log("Subscriber created successfully and exists",
					LOG_TO_STD_OUT);
		} else {
			Assert.assertFalse(false, "Subsciber doesn't exists");
			Reporter.log("Subsciber doesn't exists", LOG_TO_STD_OUT);
		}
		// Verification in database
		subscriber = DAOSubscribers.getInstance(mysqlMaster).getSubscriber(
				MSISDN);
		if (subscriber != null) {
			Assert.assertEquals(subscriber.getMsisdn(), MSISDN);
			Assert.assertEquals(subscriber.getTongue(), TONGUE);
			Assert.assertEquals(subscriber.getProfileId(), Byte.valueOf("2"));
			Assert.assertEquals(subscriber.getRatePlanId(), Byte.valueOf("1"));
			Assert.assertEquals(subscriber.getImei(), IMEI);
			Assert.assertEquals(subscriber.getImsi(), IMSI);
			System.out.println("database date"
					+ subscriber.getSubscriptionDate());
			System.out.println(Format.getMysqlDate(date));
			Assert.assertEquals(
					Format.getMysqlDate(subscriber.getSubscriptionDate()),
					Format.getMysqlDate(date));

			Reporter.log("Subscriber exists in database", LOG_TO_STD_OUT);
		} else {
			Reporter.log("Subscriber doesn't exists in database",
					LOG_TO_STD_OUT);
		}

	}

	/**
	 * UC14-01 testcase covers edit RatePlan under CustomerCare Section and
	 * verifying it in database
	 */
	@Test(enabled = TEST_ENABLED, priority = 2)
	public void testUC14_01EditRatePlan() throws FormException {
		customerCareCreateSubscriberForm = new CustomerCareCreateSubscriberForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		customerCareForm = new CustomerCareForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);

		Reporter.log("Open CustomerCare Tab.", LOG_TO_STD_OUT);
		customerCareForm.clickClearButton();
		// Enter msisdn
		Reporter.log("Enter subscriber id which need to edit", LOG_TO_STD_OUT);
		customerCareForm
				.searchMsisdnByPhoneNumber(null, String.valueOf(MSISDN));
		customerCareCreateSubscriberForm.editRatePlan("NEO");
		subscriber = DAOSubscribers.getInstance(mysqlMaster).getSubscriber(
				MSISDN);
		Assert.assertEquals(subscriber.getRatePlanId(), Byte.valueOf("2"));
		Reporter.log("Rate Plan edited successfully", LOG_TO_STD_OUT);

	}

	/**
	 * UC14-02 testcase covers adding of Channel in existing subscriber under
	 * CustomerCareSection and verifying it in database.
	 */
	@Test(enabled = TEST_ENABLED, priority = 3)
	public void testUC14_01EditChannel() throws FormException {
		customerCareForm = new CustomerCareForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		CustomerCareProfileForm customerCareProfileForm = new CustomerCareProfileForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		customerCareProfileForm.open();
		customerCareProfileForm.clickChannel();
		customerCareProfileForm.clickAddChannelButton();
		customerCareProfileForm.addChannel("SMS", String.valueOf(MSISDN),
				"Active");
		subscriber = DAOSubscribers.getInstance(mysqlMaster).getSubscriber(
				MSISDN);
		Assert.assertEquals(subscriber.getChannelIdList(), "1");
		Reporter.log("Channel Added successfully", LOG_TO_STD_OUT);

	}

}