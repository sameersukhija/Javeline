package com.lumata.e4o.regressions.gui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.ProductTypesForm;
import com.lumata.e4o.gui.catalogmanager.ProductsForm;
import com.lumata.e4o.gui.catalogmanager.SuppliersForm;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONSuppliers;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;
@TCOwners(
		@TCOwner( name="Parvinder Bhogra", email="parvinder.bhogra@lumatagroup.com" )
	)
@TCSeleniumWebDriver
public class TestProductsForm extends ParentTestCase{
	private static final Logger logger = LoggerFactory.getLogger( TestProductsForm.class );
	private JSONSuppliers setupSupplier=null;
	private String supplierName=null;
	private String productTypeName=null;
	private JSONProductTypes setupProductTypes=null;
	private String productName=null;
	private Boolean supplier_created=false;
	private Boolean pdtype_created=false;
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Parameters({"supplier_jsonFilePath","supplier_jsonFileName","productType_jsonFilePath","productType_jsonFileName"})
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void testEndtoEndProductCreation( @Optional("/catalogmanager/suppliers") String supplier_jsonFilePath, @Optional("supplierList") String supplier_jsonFileName,@Optional("/catalogmanager/productTypes") String productType_jsonFilePath, @Optional("newProductType") String productType_jsonFileName) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Supplier Form\".", LOG_TO_STD_OUT);
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + supplier_jsonFilePath;
		String resourceFile = supplier_jsonFileName;

		Reporter.log("\"Supplier\" is filled with reosurce file : ",
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
				supplierName=Format.addTimestamp(setupSupplier.getName() + "_");
				logger.info("Creating Supplier Name:" + supplierName);
				suppliersForm.configureSupplier(supplierName,setupSupplier.getEmail(),setupSupplier.getPhone(),setupSupplier.getWebsite());
				suppliersForm.saveSupplier();
				status=suppliersForm.isSupplierInList(supplierName);
				if(status==true)
				{
					Assert.assertTrue(status);
					logger.info("Created Supplier Succesfully:" + supplierName);
					supplier_created=true;
					
				}
				else{
					Assert.fail("The Supplier creation Failed!");
					Reporter.log("Creation of Supplier Failed!",LOG_TO_STD_OUT);
				}
			}
		}
		if(supplier_created==true)
		{
		Boolean pd_type_status=false;
		Reporter.log("Creation of \"Product Types Form\".", LOG_TO_STD_OUT);

		String Pdt_resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + productType_jsonFilePath;
		String Pdt_resourceFile = productType_jsonFileName;

		Reporter.log("\"Product Types\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + Pdt_resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + Pdt_resourceFile, LOG_TO_STD_OUT);
		
		setupProductTypes = new JSONProductTypes(Pdt_resourcePath, Pdt_resourceFile);

		ProductTypesForm productTypesForm= new ProductTypesForm(seleniumWebDriver,setupProductTypes,TIMEOUT, ATTEMPT_TIMEOUT);
		
		productTypesForm.clickId( "gwt-debug-actrule-catalog-productTypes" );
		int numbProdType = setupProductTypes.getList().size();
		
		for (int index = 0; index < numbProdType; index++) {
			
			JsonCurrentElement current = setupProductTypes.getCurrentElementById(index);
			
			if ( current.getEnabled() ){
				productTypeName=Format.addTimestamp(setupProductTypes.getName() + "_");
				productTypesForm.configureProductType(productTypeName,setupProductTypes.getDescription());
				for (JsonCharacteristicElement chElem : setupProductTypes.getCharacteristicsList()) {
			
					if ( chElem.getEnabled() ) {
				
						productTypesForm.addCharacteristicButton();
						productTypesForm.fillCharacteristicElement(Format.addTimestamp(chElem.getName()+"_"),chElem);
						productTypesForm.saveCharacteristic();
					}
				}
				productTypesForm.saveProductType();
				pd_type_status=productTypesForm.isProductTypeInList(productTypeName);
				if(pd_type_status==true)
				{
					Assert.assertTrue(pd_type_status);
					logger.info("Created Product Type Succesfully:" + productTypeName);
					pdtype_created=true;
				}
				else{
					Assert.fail("The Product Type creation Failed!");
					Reporter.log("Creation of Product Failed!",LOG_TO_STD_OUT);
				}
			}
	}
}
		if(pdtype_created==true)
		{
			ProductsForm pdForm=new ProductsForm(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
			pdForm.clickId( "gwt-debug-actrule-catalog-products" );
			productName=Format.addTimestamp("new_Product_");
			List<String> pdType_list=new ArrayList<String>();
			pdType_list.add(productTypeName);
			pdForm.addExternalProduct(supplierName, productName, "new Product", null, null, pdType_list,"20","15","100","@current+1month","@current+2month");
			Boolean stat=pdForm.isProductInList(productName);
			if(stat==true)
			{
				Assert.assertTrue(stat);
				logger.info("Created Product Succesfully:" + productName);
			}
			else{
				Assert.fail("The Product creation Failed!");
				Reporter.log("Creation of Product Failed!",LOG_TO_STD_OUT);
			}
		}
		
	}
}
