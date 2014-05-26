package com.lumata.e4o.gui.administrationmanager;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.json.gui.administrationmanager.JSONComplements;

public class ConfigureComplements extends ParentUITestCase {

	/**
	 * Commodities configuration file
	 */
	private JSONComplements setupComplements = null;
	
	/**
	 * Commodities Form
	 */
	private ComplementsForm complementsForm = null;
	

	@Parameters({"complementsFile"})
	@Test(groups = { "configureComplements" })
	public void configureComplementsTest(@Optional("complements_template") String complementsFile) throws JSONSException, FormException {

		Reporter.log("Creation of \"Complements Form\".", PRINT2STDOUT__);

		String resourcePath = currentResourceStartPath + "/administrationmanager/complements";
		String resourceFile = complementsFile;

		Reporter.log("\"Complements Form\" is filled with reosurce file : ",
				PRINT2STDOUT__);
		Reporter.log("Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log("Resource file -> " + resourceFile, PRINT2STDOUT__);

		setupComplements = new JSONComplements(resourcePath, resourceFile);

		complementsForm = new ComplementsForm(seleniumWebDriver,
				setupComplements, TIMEOUT, ATTEMPT_TIMEOUT);

		Reporter.log("Open \"Complements Form\" UI.", PRINT2STDOUT__);

		complementsForm.open();

		Reporter.log("Apply setup on UI.", PRINT2STDOUT__);

		complementsForm.addComplementsList();

		Reporter.log("Check general status of form", PRINT2STDOUT__);

		Assert.assertTrue(complementsForm.navigate(),
				"Status error during configuration!");
	}	
}
