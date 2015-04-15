package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.ConfigureSuppliers;
import com.lumata.e4o.gui.catalogmanager.SuppliersForm;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.json.gui.catalogmanager.JSONSuppliers;

public class testSupplierForm extends ParentUITestCase{
private static final Logger logger = LoggerFactory.getLogger( ConfigureSuppliers.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 200;
	
	private final boolean testEnabled = true;
	
	private JSONSuppliers setupSupplier=null;
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Parameters({"jsonFilePath","jsonFileName"})
	@Test( enabled=testEnabled, priority = 1 )
	public void testSupplierCreation( @Optional("/catalogmanager/suppliers") String jsonFilePath, @Optional("supplierList") String jsonFileName ) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Supplier Form\".", PRINT2STDOUT__);

		String resourcePath = currentResourceStartPath + jsonFilePath;
		String resourceFile = jsonFileName;

		Reporter.log("\"Product Types\" is filled with reosurce file : ",
				PRINT2STDOUT__);
		Reporter.log("Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log("Resource file -> " + resourceFile, PRINT2STDOUT__);
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
					Reporter.log("Supplier Created Succesfully!",PRINT2STDOUT__);
				}
				else{
					Assert.fail("The Supplier creation Failed!");
					Reporter.log("Creation of Product Failed!",PRINT2STDOUT__);
				}
			}
		}
		
	}
}
