package com.lumata.e4o.regressions.gui;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.CampaignSubstatesForm;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

/**
 * This automation covers creation of campaignsubstates under Administration
 * section, edition and deletion of existing campaign substates
  *
 */
@TCOwners(@TCOwner(name = "Isha Vyas", email = "isha.vyas@lumatagroup.com"))
@TCSeleniumWebDriver
public class TestCampaignSubstatesForm extends ParentTestCase {
	private static final Logger logger = LoggerFactory
			.getLogger(TestCampaignSubstatesForm.class);
	private CampaignSubstatesForm campaignSubstatesForm;

	/**
	 * UC09-01 Create Campaign substate under Adminstration section.Campaign
	 * substate created successfully and verified on UI.
	 * 
	 */
	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 1)
	public void testUc09_01CreateCampaignSubstates() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Campaign Substates Form\".", LOG_TO_STD_OUT);
		campaignSubstatesForm = new CampaignSubstatesForm(seleniumWebDriver,
				TIMEOUT, ATTEMPT_TIMEOUT);
		campaignSubstatesForm.open();
		campaignSubstatesForm.addCampaignSubstates();
		campaignSubstatesForm.setName("test_campaignsubstate");
		campaignSubstatesForm
				.setDescription("test_campaignsubstatedescription");
		campaignSubstatesForm.clickSaveButton();
		if (campaignSubstatesForm.isSubstateinList("test_campaignsubstate")) {
			Assert.assertTrue(true, "CampaignSubstate created successfully");
			Reporter.log("CampaignSubstate created successfully",
					LOG_TO_STD_OUT);
		} else {
			Assert.fail("CampaignSubstate doesn't created successfully");
			Reporter.log("CampaignSubstate creation failed", LOG_TO_STD_OUT);
		}

	}

	/**
	 * 
	 * UC09-02 Edit the already created campaign substate under Administration
	 * section.Campaign substate edited successfully and verified on UI.
	 */
	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 2)
	public void testUc09_02EditCampaignSubstates() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);
		campaignSubstatesForm = new CampaignSubstatesForm(seleniumWebDriver,
				TIMEOUT, ATTEMPT_TIMEOUT);
		campaignSubstatesForm.open();
		campaignSubstatesForm.clickeditButton("test_campaignsubstate");
		campaignSubstatesForm
				.setName("test_campaignsubstate_updated");
		campaignSubstatesForm.clickSaveButton();
		if (campaignSubstatesForm
				.isSubstateinList("test_campaignsubstate_updated")) {
			Assert.assertTrue(true, "CampaignSubstate updated successfully");
			Reporter.log("CampaignSubstate edited successfully", LOG_TO_STD_OUT);
		} else {
			Assert.fail("CampaignSubstate doesn't edited successfully");
			Reporter.log("CampaignSubstate updation failed", LOG_TO_STD_OUT);
		}
	}

	/**
	 * 
	 * UC09-03 Delete the already created campaign substate under Administration
	 * section.Campaign substate deleted successfully and verified on UI.
	 */

	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 3)
	public void testUc09_03DeleteCampaignSubstates() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);
		campaignSubstatesForm = new CampaignSubstatesForm(seleniumWebDriver,
				TIMEOUT, ATTEMPT_TIMEOUT);
		campaignSubstatesForm.open();
		Boolean bStatus;
		bStatus = campaignSubstatesForm
				.clickDeleteButton("test_campaignsubstate_updated");
		if (bStatus == true) {
			Assert.assertTrue(true, "Campaign Substate deleted successfully");
			Reporter.log("Campaign Substate deleted successfully",
					LOG_TO_STD_OUT);
		} else {
			Assert.fail("Failed to delete Campaign Substate");
			Reporter.log("Failed to delete Campaign Substate", LOG_TO_STD_OUT);
		}

	}

}
