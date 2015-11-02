package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.BonusForm;
import com.lumata.e4o.json.gui.administrationmanager.JSONCommodities;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners({
	@TCOwner(name = "Isha Vyas", email = "isha.vyas@lumatagroup.com"),
	@TCOwner(name = "Arcangelo Di Pasquale", email = "arcangelo.dipasquale@lumatagroup.com")
})
@TCSeleniumWebDriver
/**
 * 
 * @author isha.vyas
 * This automation covers creation of Bonus both internal and external, edit the bonus, add balance limit and then delete the bonus.
 *
 */
public class TestBonusForm extends ParentTestCase {
	
	private BonusForm bonusForm;
	private JSONCommodities jsonCommodity;
	private String BONUS_NAME_INTERNAL;
	private String BONUS_NAME_EXTERNAL;
	
	@BeforeMethod
	public void initBonusForm( Method method ) throws NetworkEnvironmentException, FormException {		
		
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	
	private void checkResult(Boolean status, String successMessage, String errorMessage ) {
		String message = ( status ? successMessage: errorMessage );
		Reporter.log( Log.CHECKING.createMessage( message ), LOG_TO_STD_OUT);
		Assert.assertTrue(status, message);
	}
	
	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 1)
	@Parameters({ "jsonFilePath_Bonus", "jsonFileName_Bonus" })
	/**
	 * 
	 * UC06-01 UC06 create a new Bonus  Bonus is created 
	 */
	public void testUc06_01CreateNewBonus(@Optional("input/administration/commodities") String jsonFilePath_Bonus, @Optional("BonusList") String jsonFileName_Bonus) throws FormException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Reporter.log("Creation of \"Bonus Form\".", LOG_TO_STD_OUT);
		
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_Bonus;
		String resourceFile = jsonFileName_Bonus;
		
		jsonCommodity = new JSONCommodities(resourcePath, resourceFile);
		
		Reporter.log("\"Bonus\" is filled with resource file : ", LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);

		bonusForm = new BonusForm(seleniumWebDriver, jsonCommodity, TIMEOUT, ATTEMPT_TIMEOUT);

		int size = jsonCommodity.getList().size();
		
		for (int index = 0; index < size; index++) {
					
			jsonCommodity.setCurrentElementById(index);
			
			bonusForm.
				open().
				addBtn();
			
			if (jsonCommodity.getType().equals("Internal")) {
				
				BONUS_NAME_INTERNAL = Format.addTimestamp("Bonus_");
				
				bonusForm.
					selectType(jsonCommodity.getType()).
					setName(BONUS_NAME_INTERNAL).
					selectDefaultValidityType(jsonCommodity.getDefaultValidityType());
				
				if (jsonCommodity.getDefaultValidityType().equals("Fixed")) {
					
					bonusForm.selectDefaultPeriodStart(jsonCommodity.getDefaultPeriodStart());

				}

				bonusForm.
					selectPeriodType(jsonCommodity.getDefaultPeriodType()).
					setDefaultQuantityPeriod(jsonCommodity.getDefaultQuantityPeriod()).
					setUnitaryCost(jsonCommodity.getUnitaryCost()).
					setListPrice(jsonCommodity.getListPrice()).
					saveBtn();
				
				checkResult(bonusForm.isBonusNameInList(BONUS_NAME_INTERNAL), "Internal Bonus Created successfully", "Internal Bonus creation failed" );
			
			} else {
				
				BONUS_NAME_EXTERNAL = Format.addTimestamp("Bonus_");
				
				bonusForm.
					selectType(jsonCommodity.getType()).
					setName(BONUS_NAME_EXTERNAL).
					selectAccount(jsonCommodity.getAccount()).
					selectAccountType(jsonCommodity.getAccountType()).
					selectDefaultValidityType(jsonCommodity.getDefaultValidityType());
				
				if (jsonCommodity.getDefaultValidityType().equals("Fixed")) {
					
					bonusForm.selectDefaultPeriodStart(jsonCommodity.getDefaultPeriodStart());

				}
				
				bonusForm.
					selectPeriodType(jsonCommodity.getDefaultPeriodType()).
					setDefaultQuantityPeriod(jsonCommodity.getDefaultQuantityPeriod()).
					setUnitaryCost(jsonCommodity.getUnitaryCost()).
					setListPrice(jsonCommodity.getListPrice()).
					saveBtn();
				
				checkResult(bonusForm.isBonusNameInList(BONUS_NAME_EXTERNAL), "External Bonus Created successfully", "External Bonus creation failed" );

			}

		}

	}
	
	/**
	 * 
	 * UC06-02 UC06 edit an already existing bonus  bonus should be editable 
	 */

	//@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 2)
	@Test(enabled = true, timeOut = TESTNG_TIMEOUT, priority = 2)
	public void testUc06_02EditBonus() throws FormException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		bonusForm = new BonusForm(seleniumWebDriver, jsonCommodity, TIMEOUT, ATTEMPT_TIMEOUT);
		
		bonusForm.open();
		
		if (bonusForm.isBonusNameInList(BONUS_NAME_INTERNAL)) {
			
			bonusForm.
				editBtn(BONUS_NAME_INTERNAL).
				selectDefaultValidityType("Fixed").
				selectDefaultPeriodStart("BeginningOfNextPeriod").
				saveBtn();
			
			checkResult(bonusForm.isBonusNameInList(BONUS_NAME_INTERNAL), "Internal Bonus edited successfully", "Internal Bonus not edited successfully" );

		}

		if (bonusForm.isBonusNameInList(BONUS_NAME_EXTERNAL)) {
			
			bonusForm.
				editBtn(BONUS_NAME_EXTERNAL).
				selectDefaultValidityType("Variable").
				saveBtn();
			
			checkResult(bonusForm.isBonusNameInList(BONUS_NAME_EXTERNAL), "External Bonus edited successfully", "External Bonus no edited successfully" );

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
				bonusForm.
					balanceLimitBtn(BONUS_NAME_INTERNAL).
					setBalanceLimit(BONUS_NAME_INTERNAL, "10").
					saveBtn().
					balanceLimitBtn(BONUS_NAME_INTERNAL);
				
				checkResult(bonusForm.getBalanceLimit(BONUS_NAME_INTERNAL).equals("10"), "Balance Limit added successfully", "Balance Limit no added successfully" );				
				
				bonusForm.cancelBtn();

			}
			
		}

		if (bonusForm.isBonusNameInList(BONUS_NAME_EXTERNAL)) {
			
			checkResult(bonusForm.isBalanceLimitBtnDisable(BONUS_NAME_EXTERNAL), "Limit button is disabled for External bonus", "Limit button is not disabled for External bonus" );
		
		}
		
	}
	
	/**
	 * UC06-04 UC06 delete an already existing bonus  bonus should be deleted 
	 * 
	 */
	@Test(enabled = TEST_ENABLED, timeOut = TESTNG_TIMEOUT, priority = 4)
	public void testUc06_04DeleteBonus() throws FormException {

		checkResult(bonusForm.deleteBtn(BONUS_NAME_INTERNAL), "Internal Bonus deleted successfully", "Failed to delete internal bonus" );

		checkResult(bonusForm.deleteBtn(BONUS_NAME_EXTERNAL), "External Bonus deleted successfully", "Failed to delete bonus" );

	}
	
}
