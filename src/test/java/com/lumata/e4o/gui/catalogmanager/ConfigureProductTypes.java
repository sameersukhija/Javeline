package com.lumata.e4o.gui.catalogmanager;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;

public class ConfigureProductTypes extends ParentUITestCase {
	
	/**
	 * Commodities configuration file
	 */
	private JSONProductTypes setupProductTypes = null;
	
	/**
	 * Product Types Form
	 */
	private ProductTypesForm productTypesForm = null;
	
	@Parameters({"productTypesFile"})
	@Test(groups = { "configureProductTypes" })
	public void configureProductTypesTest(@Optional("productTypesTemplate") String productTypesFile) throws JSONSException, FormException {

		Reporter.log("Creation of \"Product Types Form\".", PRINT2STDOUT__);

		String resourcePath = currentResourceStartPath + "/catalogmanager/productTypes";
		String resourceFile = productTypesFile;

		Reporter.log("\"Product Types\" is filled with reosurce file : ",
				PRINT2STDOUT__);
		Reporter.log("Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log("Resource file -> " + resourceFile, PRINT2STDOUT__);

		setupProductTypes = new JSONProductTypes(resourcePath, resourceFile);

		productTypesForm = new ProductTypesForm(seleniumWebDriver,
				setupProductTypes, TIMEOUT, ATTEMPT_TIMEOUT);

		Reporter.log("Open \"Product Types Form\" UI.", PRINT2STDOUT__);

		productTypesForm.open();

		Reporter.log("Apply setup on UI.", PRINT2STDOUT__);

		productTypesForm.addProductTypes();

		Reporter.log("Check general status of form", PRINT2STDOUT__);

		Assert.assertTrue(productTypesForm.navigate(),
				"Status error during configuration!");
	}
	
	@Parameters({"cleanup"})
	@Test(groups = { "configureProductTypes" }, dependsOnMethods = {"configureProductTypesTest"})
	public void cleanupProductTypesForm(@Optional("TRUE") Boolean cleanup) throws FormException, JSONSException {
		
		try {
			Reporter.log( "Cleanup \"Product Types\" created.", PRINT2STDOUT__);
			
			if ( productTypesForm != null && cleanup ) {

				Reporter.log( "Cleanup \"Product Types\" form.", PRINT2STDOUT__);
			
				int numbPt = setupProductTypes.getList().size();
				
				List<String> names = new ArrayList<>();
				
				for (int index = 0; index < numbPt; index++) {
					
					setupProductTypes.setCurrentElementById(index);
					
					JsonCurrentElement current = setupProductTypes.getCurrentElement();
					
					if ( current.getDelete() )
						names.add(current.getStringFromPath("name"));
				}
				
				String[] productTypes2BeDelete = names.toArray(new String[0]);
				
				Assert.assertTrue( 	productTypesForm.open().deleteProductTypes(productTypes2BeDelete),
						"Error during \"Product Types\" cleanup!");
			}
			else
				Reporter.log( "Leave configured \"Product Types\" form.", PRINT2STDOUT__);	
		}
		catch ( FormException e ) {
			
			Assert.assertTrue(false, "Error on forms cleanup : " + e.getMessage());
		}
	}	
}
