package com.lumata.e4o.regressions.gui;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.BonusForm;
import com.lumata.e4o.json.gui.administrationmanager.JSONCommodities;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(@TCOwner(name = "Isha Vyas", email = "isha.vyas@lumatagroup.com"))
@TCSeleniumWebDriver
/**
 * 
 * @author isha.vyas
 * This automation covers creation of Bonus both internal and external, edit the bonus, add balance limit and then delete the bonus.
 *
 */
public class TestBonusForm extends ParentTestCase {
	private static final Logger logger = LoggerFactory
			.getLogger(TestBonusForm.class);
	private BonusForm bonusForm;
	private JSONCommodities jsonCommodity;
	private String BONUS_NAME_INTERNAL;
	private String BONUS_NAME_EXTERNAL;

	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 1)
	@Parameters({ "jsonFilePath_Bonus", "jsonFileName_Bonus"})
	/**
	 * 
	 * UC06-01 UC06 create a new Bonus  Bonus is created 
	 */
	public void testUc06_01CreateNewBonus(@Optional String jsonFilePath_Bonus,
			@Optional String jsonFileName_Bonus) throws FormException,
			JSONSException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Bonus Form\".", LOG_TO_STD_OUT);
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_Bonus;
		String resourceFile = jsonFileName_Bonus;
		jsonCommodity = new JSONCommodities(resourcePath, resourceFile);
		Reporter.log("\"Bonus\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);

		bonusForm = new BonusForm(seleniumWebDriver, jsonCommodity, TIMEOUT,
				ATTEMPT_TIMEOUT);

		int size = jsonCommodity.getList().size();
		for (int index = 0; index < size; index++) {

			jsonCommodity.setCurrentElementById(index);
			bonusForm.open();
			bonusForm.clickAddBonus();
			if (jsonCommodity.getType().equals("Internal")) {
				BONUS_NAME_INTERNAL = Format.addTimestamp("Bonus_");
				bonusForm.selectType(jsonCommodity.getType());
				bonusForm.enterName(BONUS_NAME_INTERNAL);
				bonusForm.selectDefaultValidityType(jsonCommodity
						.getDefaultValidityType());
				if (jsonCommodity.getDefaultValidityType().equals("Fixed")) {
					bonusForm.selectDefaultPeriodStart(jsonCommodity
							.getDefaultPeriodStart());

				}

				bonusForm
						.selectPeriodType(jsonCommodity.getDefaultPeriodType());
				bonusForm.enterDefaultQuantityPeriod(jsonCommodity
						.getDefaultQuantityPeriod());
				bonusForm.enterUnitaryCost(jsonCommodity.getUnitaryCost());
				bonusForm.enterListPrice(jsonCommodity.getListPrice());
				bonusForm.saveBtn();
				if (bonusForm.isBonusNameInList(BONUS_NAME_INTERNAL)) {
					Assert.assertTrue(true,
							"Internal Bonus Created successfully");
					Reporter.log("Internal Bonus created successfully",
							LOG_TO_STD_OUT);
				} else {
					Assert.fail("Internal Bonus doesn't create successfully");
					Reporter.log("Internal Bonus creation failed",
							LOG_TO_STD_OUT);
				}
			} else {
				BONUS_NAME_EXTERNAL = Format.addTimestamp("Bonus_");
				bonusForm.selectType(jsonCommodity.getType());
				bonusForm.enterName(BONUS_NAME_EXTERNAL);
				bonusForm.selectAccount(jsonCommodity.getAccount());
				bonusForm.selectAccountType(jsonCommodity.getAccountType());
				bonusForm.selectDefaultValidityType(jsonCommodity
						.getDefaultValidityType());
				if (jsonCommodity.getDefaultValidityType().equals("Fixed")) {
					bonusForm.selectDefaultPeriodStart(jsonCommodity
							.getDefaultPeriodStart());

				}
				bonusForm
						.selectPeriodType(jsonCommodity.getDefaultPeriodType());
				bonusForm.enterDefaultQuantityPeriod(jsonCommodity
						.getDefaultQuantityPeriod());
				bonusForm.enterUnitaryCost(jsonCommodity.getUnitaryCost());
				bonusForm.enterListPrice(jsonCommodity.getListPrice());
				bonusForm.saveBtn();
				if (bonusForm.isBonusNameInList(BONUS_NAME_EXTERNAL)) {
					Assert.assertTrue(true,
							"External Bonus Created successfully");
					Reporter.log("External Bonus created successfully",
							LOG_TO_STD_OUT);
				} else {
					Assert.fail("External Bonus doesn't create successfully");
					Reporter.log("External Bonus creation failed",
							LOG_TO_STD_OUT);
				}

			}

		}

	}
	
	/**
	 * 
	 * UC06-02 UC06 edit an already existing bonus  bonus should be editable 
	 */

	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 2)
	public void testUc06_02EditBonus() throws FormException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);
		bonusForm = new BonusForm(seleniumWebDriver, jsonCommodity, TIMEOUT,
				ATTEMPT_TIMEOUT);
		bonusForm.open();
		if (bonusForm.isBonusNameInList(BONUS_NAME_INTERNAL)) {
			bonusForm.clickEdit(BONUS_NAME_INTERNAL);
			bonusForm.selectDefaultValidityType("Fixed");
			bonusForm.selectDefaultPeriodStart("BeginningOfNextPeriod");
			bonusForm.saveBtn();
			if (bonusForm.isDefaultValidityInList(BONUS_NAME_INTERNAL, "Fixed")) {
				Assert.assertTrue(true, "Internal Bonus edited successfully");
				Reporter.log("Internal Bonus edited successfully", LOG_TO_STD_OUT);
			} else {
				Assert.fail("Internal Bonus not edited successfully");
				Reporter.log("Internal Bonus not edited successfully");
			}
		}

		if (bonusForm.isBonusNameInList(BONUS_NAME_EXTERNAL)) {
			bonusForm.clickEdit(BONUS_NAME_EXTERNAL);
			bonusForm.selectDefaultValidityType("Variable");
			bonusForm.saveBtn();
			if (bonusForm.isDefaultValidityInList(BONUS_NAME_EXTERNAL,
					"Variable")) {
				Assert.assertTrue(true, "External Bonus edited successfully");
				Reporter.log("External Bonus edited successfully", LOG_TO_STD_OUT);
			} else {
				Assert.fail("External Bonus not edited successfully");
				Reporter.log("External Bonus not edited successfully");
			}
		}
	}
	
	/**
	 * UC06-03 UC06 limit an already created bonus  we should be able to set the limit for the internal bonus.
	 * In case of external bonus verify that bonus limit button is disabled.
	 */

	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 3)
	public void testUc06_03EditLimit() throws FormException, JSONSException {

		if (bonusForm.isBonusNameInList(BONUS_NAME_INTERNAL)) {

			{
				bonusForm.clickButtonLimit(BONUS_NAME_INTERNAL);
				bonusForm.enterBalanclimitValue("10");
				bonusForm.saveBtn();
				bonusForm.clickButtonLimit(BONUS_NAME_INTERNAL);
				if (bonusForm.getBalancelimitValue().equals("10")) {
					Assert.assertTrue(true, "limit added successfully");
					Reporter.log("Balance limit added successfully",
							LOG_TO_STD_OUT);
					
				} else {
					Assert.fail("Balance Limit not added successfully");
					Reporter.log("Balance limit not added successfully",
							LOG_TO_STD_OUT);
				}
				bonusForm.clickCancel();
			}
		}

		if (bonusForm.isBonusNameInList(BONUS_NAME_EXTERNAL)) {
			if (bonusForm.isBalanceLimitBtnDisable(BONUS_NAME_EXTERNAL)) {
				Assert.assertTrue(true,
						"Limit button is disabled for External bonus");
				Reporter.log("Limit button is disabled for External bonus",
						LOG_TO_STD_OUT);
			} else {
				Assert.fail("Limit button is not disabled for External bonus");
				Reporter.log("Limit button is not disabled for External bonus",
						LOG_TO_STD_OUT);
			}
		}
	}
	
	/**
	 * UC06-04 UC06 delete an already existing bonus  bonus should be deleted 
	 * 
	 */

	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 4)
	public void testUc06_04DeleteBonus() throws FormException {
	Boolean bStatus;
			bStatus=bonusForm.clickDeleteButton(BONUS_NAME_INTERNAL);
			if (bStatus==true) {
				Assert.assertTrue(true,
							"Internal Bonus deleted successfully");
					Reporter.log("Internal Bonus deleted successfully",
							LOG_TO_STD_OUT);
				}
				else
				{
					Assert.fail("Failed to delete internal bonus");
					Reporter.log("Failed to delete internal bonus",LOG_TO_STD_OUT);
				}

				bStatus=bonusForm.clickDeleteButton(BONUS_NAME_EXTERNAL);
				if (bStatus==true) {
					Assert.assertTrue(true,
							"External Bonus deleted successfully");
					Reporter.log("External Bonus deleted successfully",
							LOG_TO_STD_OUT);
				}
				else
				{
					Assert.fail("Failed to delete bonus");
					Reporter.log("Failed to delete bonus",LOG_TO_STD_OUT);
				}
	}
}
