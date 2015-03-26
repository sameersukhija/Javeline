package com.lumata.e4o.gui.catalogmanager;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers;

public class ConfigureOffers extends ParentUITestCase {

	/**
	 * Offers configuration file
	 */
	private JSONOffers setupOffers = null;
	
	/**
	 * Offer Form
	 */
	private OffersForm offersForm = null;	
	
	@Parameters({"offersFile"})
	@Test(groups = { "configureOffers" })
	public void configureOffers( @Optional("offersTemplate") String offersFile ) throws FormException, JSONSException {
		
		Reporter.log("Creation of \"Offers Form\".", PRINT2STDOUT__);

		String resourcePath = currentResourceStartPath + "/catalogmanager/offers";
		String resourceFile = offersFile;

		Reporter.log("\"Offers\" is filled with reosurce file : ",
				PRINT2STDOUT__);
		Reporter.log("Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log("Resource file -> " + resourceFile, PRINT2STDOUT__);

		setupOffers = new JSONOffers(resourcePath, resourceFile);

		offersForm = new OffersForm(seleniumWebDriver,
				setupOffers, TIMEOUT, ATTEMPT_TIMEOUT);

		Reporter.log("Open \"Offer Form\" UI.", PRINT2STDOUT__);

		offersForm.openForm();

		Reporter.log("Apply setup on UI.", PRINT2STDOUT__);

		offersForm.addOffers();

		Reporter.log("Check general status of form", PRINT2STDOUT__);

		Assert.assertTrue(offersForm.navigate(),
				"Status error during configuration!");		
	}
	
}
