package com.lumata.e4o.regressions.gui;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.SuppliersForm;
import com.lumata.e4o.json.gui.catalogmanager.JSONSuppliers;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(
	@TCOwner( name="Parvinder Bhogra", email="parvinder.bhogra@lumatagroup.com" )
)
@TCSeleniumWebDriver
public class TestSupplierForm extends ParentTestCase {
	
	private JSONSuppliers setupSupplier=null;
	
	@Parameters({"sup_jsonFilePath","sup_jsonFileName"})
	@Test( enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT, priority = 1 )
	public void testSupplierCreation( @Optional("input/catalogmanager/suppliers") String sup_jsonFilePath, @Optional("supplierList") String sup_jsonFileName) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Supplier Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + sup_jsonFilePath;
		String resourceFile = sup_jsonFileName;

		Reporter.log("\"Supplier Form\" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		setupSupplier=new JSONSuppliers(resourcePath, resourceFile);
		SuppliersForm suppliersForm = new SuppliersForm( seleniumWebDriver,setupSupplier, TIMEOUT, ATTEMPT_TIMEOUT );
		
		suppliersForm.openForm();
		JSONArray suppliers = setupSupplier.getList();
		
		for( int supplierIndex = 0; supplierIndex < suppliers.length(); supplierIndex++ ) {
			
			setupSupplier.setSupplierById( supplierIndex );
			if( setupSupplier.getEnabled() ) {
				String name=Format.addTimestamp(setupSupplier.getName() + "_");
				suppliersForm.configureSupplier(name,setupSupplier.getEmail(),setupSupplier.getPhone(),setupSupplier.getWebsite());
				suppliersForm.saveSupplier();
				status=suppliersForm.isSupplierInList(name);
				if(status==true)
				{
					Assert.assertTrue(status);
					Reporter.log("Supplier Created Succesfully!",LOG_TO_STD_OUT);
				}
				else{
					Assert.fail("The Supplier creation Failed!");
					Reporter.log("Creation of Supplier Failed!",LOG_TO_STD_OUT);
				}
			}
		}
		
	}
}
