package com.lumata.e4o.regressions.gui;

import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.customercare.CustomerCareCreateSubscriberForm;
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
	@Test
	public void createSubscriber() throws FormException {

		int TIMEOUT = 60000;
		int ATTEMPT_TIMEOUT = 200;
		CustomerCareCreateSubscriberForm customerCareCreateSubscriberForm = new CustomerCareCreateSubscriberForm(
				seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);

		customerCareCreateSubscriberForm.open();
		// Enter msisdn
		customerCareCreateSubscriberForm.inputSubscriberId("9999000088");
		// Click AddButton
		customerCareCreateSubscriberForm.clickCustomerCareAddButton();
		// Enter Language
		customerCareCreateSubscriberForm.enterLanguage("ENG");
		// Click add button
		customerCareCreateSubscriberForm.clickCustomerCareCreateAdd();

	}
}
