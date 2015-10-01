package com.lumata.e4o.gui.loyaltymanager;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation;

public class ConfigureLoyalty extends ParentUITestCase {

	/**
	 * Commodities configuration file
	 */
	private JSONLoyaltiesCreation setupLoyaltiesCreation = null;
	
	/**
	 * Product Types Form
	 */
	private LoyaltyCreationForm loyaltyCreationForm = null;
	
	@Parameters({"loyaltyCreationFile"})
	@Test(groups = { "configureLoyaltyCreation" })
	public void configureLoyaltyCreation(@Optional("loyaltyCreationTemplate") String loyaltyCreationFile) throws JSONSException, FormException {
		
		Reporter.log("Creation of \"Loyalty Creation Form\".", PRINT2STDOUT__);

		String resourcePath = currentResourceStartPath + "/loyaltymanager";
		String resourceFile = loyaltyCreationFile;

		Reporter.log("\"Loyalty Creation\" is filled with reosurce file : ",
				PRINT2STDOUT__);
		Reporter.log("Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log("Resource file -> " + resourceFile, PRINT2STDOUT__);

		setupLoyaltiesCreation = new JSONLoyaltiesCreation(resourcePath, resourceFile);

		loyaltyCreationForm = new LoyaltyCreationForm(seleniumWebDriver,
				setupLoyaltiesCreation, TIMEOUT, ATTEMPT_TIMEOUT);

		Reporter.log("Open \"Loyalty Creation Form\" UI.", PRINT2STDOUT__);

		loyaltyCreationForm.open();

		Reporter.log("Apply setup on UI.", PRINT2STDOUT__);

		//loyaltyCreationForm.addLoyaltyPrograms();

		Reporter.log("Check general status of form", PRINT2STDOUT__);

		Assert.assertTrue(loyaltyCreationForm.navigate(),
				"Status error during configuration!");
	}
}
