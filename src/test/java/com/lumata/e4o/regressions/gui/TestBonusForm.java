package com.lumata.e4o.regressions.gui;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TestBonusForm extends ParentTestCase {
	private static final Logger logger = LoggerFactory
			.getLogger(TestBonusForm.class);
	private BonusForm bonusForm;
	private JSONCommodities jsonCommodity;
	private String BONUS_NAME= Format.addTimestamp("Bonus_");

	@Test
	@Parameters({ "jsonFilePath_Bonus", "jsonFileName_Bonus" })
	public void testUc06_01CreateNewBonus(@Optional String jsonFilePath_Bonus,
			@Optional String jsonFileName_Bonus) throws FormException,
			JSONSException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Rule form integrated with Token Form\".",
				LOG_TO_STD_OUT);
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
			if(jsonCommodity.getType().equals("Internal"))
			{
			bonusForm.selectType(jsonCommodity.getType());
			bonusForm.enterName(BONUS_NAME);
			bonusForm.selectDefaultValidityType(jsonCommodity
					.getDefaultValidityType());
			bonusForm.selectPeriodType(jsonCommodity.getDefaultPeriodType());
			bonusForm.enterDefaultQuantityPeriod(jsonCommodity
					.getDefaultQuantityPeriod());
			bonusForm.enterUnitaryCost(jsonCommodity.getUnitaryCost());
			bonusForm.enterListPrice(jsonCommodity.getListPrice());
			bonusForm.saveCommodity();
			}
			else
			{
				bonusForm.selectType(jsonCommodity.getType());
				bonusForm.enterName(BONUS_NAME);
				bonusForm.selectAccountType(jsonCommodity.getAccountType());
				bonusForm.selectDefaultValidityType(jsonCommodity
						.getDefaultValidityType());
				if(jsonCommodity
						.getDefaultValidityType().equals("Fixed"))
				{
					bonusForm.selectDefaultPeriodStart(jsonCommodity.getDefaultPeriodStart());
					
				}
					bonusForm.selectPeriodType(jsonCommodity.getDefaultPeriodType());
					bonusForm.enterDefaultQuantityPeriod(jsonCommodity.getDefaultQuantityPeriod());
					bonusForm.enterUnitaryCost(jsonCommodity.getUnitaryCost());
					bonusForm.enterListPrice(jsonCommodity.getListPrice());
					bonusForm.saveCommodity();
				
			}
			
		}

	}
	
	/*@Test
	public void testUc06_01EditBonus() throws FormException,JSONSException
	{
		
	}
*/
}
