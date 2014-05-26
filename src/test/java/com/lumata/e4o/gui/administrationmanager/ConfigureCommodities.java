package com.lumata.e4o.gui.administrationmanager;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.json.gui.administrationmanager.JSONCommodities;

public class ConfigureCommodities extends ParentUITestCase {

	/**
	 * Commodities configuration file
	 */
	private JSONCommodities setupCommodities = null;
	
	/**
	 * Commodities Form
	 */
	private CommoditiesForm commoditiesForm = null;

	@Parameters({"commoditiesFile"})
	@Test(groups = { "configureCommodities" })
	public void configureCommoditiesTest(@Optional("commodities_template") String commoditiesFile) throws JSONSException, FormException {

		Reporter.log("Creation of \"Commodities Form\".", PRINT2STDOUT__);

		String resourcePath = currentResourceStartPath + "/administrationmanager/commodities";
		String resourceFile = commoditiesFile;

		Reporter.log("\"Commodities Form\" is filled with reosurce file : ",
				PRINT2STDOUT__);
		Reporter.log("Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log("Resource file -> " + resourceFile, PRINT2STDOUT__);

		setupCommodities = new JSONCommodities(resourcePath, resourceFile);

		commoditiesForm = new CommoditiesForm(seleniumWebDriver,
				setupCommodities, TIMEOUT, ATTEMPT_TIMEOUT);

		Reporter.log("Open \"Commodities Form\" UI.", PRINT2STDOUT__);

		commoditiesForm.open();

		Reporter.log("Apply setup on UI.", PRINT2STDOUT__);

		commoditiesForm.addCommoditiesList();

		Reporter.log("Check general status of form", PRINT2STDOUT__);

		Assert.assertTrue(commoditiesForm.navigate(),
				"Status error during configuration!");
	}
	
	@Parameters({"cleanup"})
	@Test(dependsOnGroups={"configureCommodities"})
	public void cleanupCommoditiesForm(@Optional("TRUE") Boolean cleanup) throws FormException, JSONSException {
		
		try {
			Reporter.log( "Cleanup \"Commodities\" created.", PRINT2STDOUT__);
			
			if ( commoditiesForm != null && cleanup ) {

				Reporter.log( "Cleanup \"Commodities\" form.", PRINT2STDOUT__);
			
				int numbComm = setupCommodities.getList().size();
				
				List<String> names = new ArrayList<>();
				
				for (int index = 0; index < numbComm; index++) {
					
					setupCommodities.setCurrentElementById(index);
					
					JsonCurrentElement current = setupCommodities.getCurrentElement();
					
					if ( current.getDelete() )
						names.add(current.getStringFromPath("name"));
				}
				
				String[] commodities2BeDelete = names.toArray(new String[0]);
				
				Assert.assertTrue( 	commoditiesForm.open().deleteCommodities(commodities2BeDelete),
						"Error during \"Commodities\" cleanup!");
			}
			else
				Reporter.log( "Leave configured \"Commodities\" form.", PRINT2STDOUT__);	
		}
		catch ( FormException e ) {
			
			Assert.assertTrue(false, "Error on forms cleanup : " + e.getMessage());
		}
	}	

}
