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
	private String supplier_name=null;
	private JSONSuppliers setupSupplier=null;
	
	@Parameters({"sup_jsonFilePath","sup_jsonFileName"})
	@Test( enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT, priority = 1 )
	public void testUc25_01CreateNewSupplier( @Optional("input/catalogmanager/suppliers") String sup_jsonFilePath, @Optional("supplierList") String sup_jsonFileName) throws FormException, JSONException, JSONSException {
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
				//String name=Format.addTimestamp(setupSupplier.getName() + "_");
				suppliersForm.configureSupplier(setupSupplier.getName(),setupSupplier.getEmail(),setupSupplier.getPhone(),setupSupplier.getWebsite());
				suppliersForm.saveSupplier();
				status=suppliersForm.isSupplierInList(setupSupplier.getName());
				if(status==true)
				{
					Assert.assertTrue(status);
					Reporter.log("Supplier Created Succesfully!",LOG_TO_STD_OUT);
					if(supplierIndex == (suppliers.length()-1))
					{
						setSupplierName(setupSupplier.getName());
					}
				}
				else{
					Assert.fail("The Supplier creation Failed!");
					Reporter.log("Creation of Supplier Failed!",LOG_TO_STD_OUT);
				}
			}
		}
		
	}
	@Test( enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT, priority = 2 )
	public void testUc25_02EditSupplier() throws FormException, JSONException, JSONSException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Edit of \"Supplier\".", LOG_TO_STD_OUT);
		SuppliersForm suppliersForm = new SuppliersForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		try{
			suppliersForm.openForm();
			suppliersForm.editSupplier(getSupplierName());
			String newName=getSupplierName()+"New";
			setSupplierName(newName);
			suppliersForm.setSupplierName(getSupplierName());
			suppliersForm.setSupplierEmail("newEmail@o2.com");
			suppliersForm.setSupplierWebSite("newWebsite.co.in");
			suppliersForm.saveSupplier();
		}catch(FormException e)
		{
			Reporter.log("Exception occured while editing the supplier:"+e.getMessage(), LOG_TO_STD_OUT);
			Assert.fail("Edit Supplier Failed");
		}
	}
	@Test( enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT, priority = 3 )
	public void testUc25_03DeleteSupplier() throws FormException, JSONException, JSONSException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Delete Supplier", LOG_TO_STD_OUT);
		SuppliersForm suppliersForm = new SuppliersForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		try{
			suppliersForm.openForm();
			suppliersForm.deleteSupplier(getSupplierName());
			suppliersForm.handleJavascriptAlertAcceptDismiss(true);
		}catch(FormException e)
		{
			Reporter.log("Exception occured while deleting the supplier:"+e.getMessage(), LOG_TO_STD_OUT);
			Assert.fail("Delete Supplier Failed");
		}
	}
	public void setSupplierName(String name) throws FormException{
		this.supplier_name=name;
	}
	public String getSupplierName() throws FormException{
		return this.supplier_name;
	}
}
