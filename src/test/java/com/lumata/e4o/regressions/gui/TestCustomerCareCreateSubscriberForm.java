package com.lumata.e4o.regressions.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.customercare.CustomerCareCreateSubscriberForm;
import com.lumata.e4o.gui.customercare.CustomerCareForm;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(@TCOwner(name = "Isha Vyas", email = "isha.vyas@lumatagroup.com"))
@TCSeleniumWebDriver
/*
 * CustomerCareCreateSubscriberFormTest
 */
public class TestCustomerCareCreateSubscriberForm extends ParentTestCase {
	private static final Logger logger = LoggerFactory
			.getLogger(TestCustomerCareCreateSubscriberForm.class);

	@Test(enabled = TEST_ENABLED)
	public void createSubscriber() throws FormException {

		int TIMEOUT = 60000;
		int ATTEMPT_TIMEOUT = 200;
		CustomerCareCreateSubscriberForm customerCareCreateSubscriberForm = new CustomerCareCreateSubscriberForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		CustomerCareForm customerCareForm = new CustomerCareForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		Reporter.log("Open CustomerCare Tab.", LOG_TO_STD_OUT);
		customerCareCreateSubscriberForm.open();
		// Enter msisdn
		Reporter.log("Enter subscriber id", LOG_TO_STD_OUT);
		customerCareCreateSubscriberForm.inputSubscriberId("9991100199");
		// Click AddButton
		customerCareCreateSubscriberForm.clickCustomerCareAddButton();
		customerCareCreateSubscriberForm.selectRatePlan("FUN");
		customerCareCreateSubscriberForm.selectStatus("active (prepaid)");
		customerCareCreateSubscriberForm.selectInTag("QAIN");
		customerCareCreateSubscriberForm.enterImsi(Integer.toString(12234));
		customerCareCreateSubscriberForm.enterImei(Integer.toString(4567));
		// Enter Language
		customerCareCreateSubscriberForm.enterLanguage("ENG");
		// Click add button
		customerCareCreateSubscriberForm.clickCustomerCareCreateAdd();
		customerCareForm.clickClearButton();
		if (customerCareForm.subscriberPhoneNumberExists(null, "9991100199")) {
			Assert.assertTrue(true,
					"Subscriber created successfully and exists");
			Reporter.log("Subscriber created successfully and exists",
					LOG_TO_STD_OUT);
		} else {
			Assert.assertFalse(false, "Subsciber doesn't exists");
			Reporter.log("Subsciber doesn't exists", LOG_TO_STD_OUT);
		}

	}

}
