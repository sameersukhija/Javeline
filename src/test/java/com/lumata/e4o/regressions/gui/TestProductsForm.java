package com.lumata.e4o.regressions.gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
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
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONProducts;
import com.lumata.e4o.json.gui.catalogmanager.JSONProducts.CharacteristicType;
import com.lumata.e4o.json.gui.catalogmanager.JSONProducts.JsonProdCharacteristicElement;
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
public class TestProductsForm extends ParentTestCase {
	
	private static final Logger logger = LoggerFactory.getLogger( TestProductsForm.class );
	
	private JSONSuppliers setupSupplier=null;
	private String supplierName=null;
	private String productTypeName=null;
	private JSONProductTypes setupProductTypes=null;
	private String productName=null;
	private Boolean supplier_created=false;
	private Boolean pdtype_created=false;
	
	@Parameters({"product_jsonFilePath","product_jsonFileName"})
	@Test( enabled=TEST_ENABLED, timeOut=1000000L, priority = 1 )
	public void testUc27_01createNewInternalProduct( 
			@Optional("input/catalogmanager/products") String product_jsonFilePath,
			@Optional("productsTemplate") String product_jsonFileName) throws FormException, JSONException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String prod_resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + product_jsonFilePath;
		String prod_resourceFile = product_jsonFileName;

		Reporter.log("\"Product\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + prod_resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + prod_resourceFile, LOG_TO_STD_OUT);
		JSONProducts setupProduct=new JSONProducts(prod_resourcePath, prod_resourceFile);
		ProductsForm pdForm=new ProductsForm(seleniumWebDriver, setupProduct,TIMEOUT, ATTEMPT_TIMEOUT);
		pdForm.openForm();
		
		int numbProds = setupProduct.getList().size();
		
		for (int index = 0; index < numbProds; index++) {
			
			JsonCurrentElement current = setupProduct.getCurrentElementById(index);
			
			if ( current.getEnabled() ){
		
				if(setupProduct.getType().equals("internal"))
				{
					pdForm.configureInternalDefinition(setupProduct.getSupplier(), setupProduct.getName(),setupProduct.getTermsAndCondition(), setupProduct.getImageUrl());
					pdForm.configureCostPrice(setupProduct.getCost(),setupProduct.getPrice());
					pdForm.configureAvailability(setupProduct.getStock(),setupProduct.getStartDate(),setupProduct.getEndDate());
					pdForm.saveProductButton();
					pdForm.clickRefreshButton();
					Boolean stat=pdForm.isProductInList(setupProduct.getName());
					if(stat==true)
					{
						Assert.assertTrue(stat);
						logger.info("Created Product Succesfully:" + setupProduct.getName());
					}
					else{
						Assert.fail("The Product creation Failed!");
						Reporter.log("Creation of Product Failed!",LOG_TO_STD_OUT);
					}
				}
				
			}
		}		
	}
	
	@Parameters({"productType_jsonFilePath","productType_jsonFileName","product_jsonFilePath","product_jsonFileName"})
	@Test( enabled=TEST_ENABLED, timeOut=800000L, priority = 2 )
	public void testUc27_03createNewExternalProductWithPDType(
			@Optional("input/catalogmanager/productTypes") String productType_jsonFilePath, 
			@Optional("newProductType") String productType_jsonFileName,
			@Optional("input/catalogmanager/products") String product_jsonFilePath,
			@Optional("productsTemplate") String product_jsonFileName) throws FormException, JSONException, JSONSException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
			Boolean pd_type_status=false;
			Reporter.log("Creation of \"Product Types Form\".", LOG_TO_STD_OUT);
	
			String Pdt_resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + productType_jsonFilePath;
			String Pdt_resourceFile = productType_jsonFileName;
	
			Reporter.log("\"Product Types\" is filled with reosurce file : ",
					LOG_TO_STD_OUT);
			Reporter.log("Resource path -> " + Pdt_resourcePath, LOG_TO_STD_OUT);
			Reporter.log("Resource file -> " + Pdt_resourceFile, LOG_TO_STD_OUT);
			
			setupProductTypes = new JSONProductTypes(Pdt_resourcePath, Pdt_resourceFile);
	
			ProductTypesForm productTypesForm = new ProductTypesForm(seleniumWebDriver,setupProductTypes,TIMEOUT, ATTEMPT_TIMEOUT);
			productTypesForm.openForm();
			//productTypesForm.clickId( "gwt-debug-actrule-catalog-productTypes" );
			int numbProdType = setupProductTypes.getList().size();
			
			for (int index = 0; index < numbProdType; index++) {
				
				JsonCurrentElement current = setupProductTypes.getCurrentElementById(index);
				
				if ( current.getEnabled() ){
					productTypeName=setupProductTypes.getName();
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
						Reporter.log("Creation of Product Type Failed!",LOG_TO_STD_OUT);
					}
				}
			}
		
		if(pdtype_created==true) {
			
			String prod_resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + product_jsonFilePath;
			String prod_resourceFile = product_jsonFileName;

			Reporter.log("\"Product\" is filled with reosurce file : ",
					LOG_TO_STD_OUT);
			Reporter.log("Resource path -> " + prod_resourcePath, LOG_TO_STD_OUT);
			Reporter.log("Resource file -> " + prod_resourceFile, LOG_TO_STD_OUT);
			JSONProducts setupProduct=new JSONProducts(prod_resourcePath, prod_resourceFile);
			ProductsForm pdForm=new ProductsForm(seleniumWebDriver, setupProduct,TIMEOUT, ATTEMPT_TIMEOUT);
			pdForm.openForm();
			
			int numbProds = setupProduct.getList().size();
			
			for (int index = 1; index < numbProds-3; index++) {
				
				JsonCurrentElement current = setupProduct.getCurrentElementById(index);
				
				if ( current.getEnabled() ){
			
					if(setupProduct.getType().equals("external"))
					{
						pdForm.configureExternalDefinition(setupProduct.getSupplier(), setupProduct.getName(), setupProduct.getDescription(), setupProduct.getTermsAndCondition(), setupProduct.getImageUrl());
						pdForm.openCharacteristicsTab();
						for(JsonProdCharacteristicElement chel : setupProduct.getCharacteristicsList())
						{
							if(chel.getEnabled())
							pdForm.configureProductCharacteristic(chel);
						}
					}
					pdForm.configureCostPrice(setupProduct.getCost(),setupProduct.getPrice());
					pdForm.configureAvailability(setupProduct.getStock(),setupProduct.getStartDate(),setupProduct.getEndDate());
					pdForm.saveProductButton();
					pdForm.clickRefreshButton();
					Boolean stat=pdForm.isProductInList(setupProduct.getName());
					if(stat==true)
					{
						Assert.assertTrue(stat);
						logger.info("Created Product Succesfully:" + setupProduct.getName());
					}
					else{
						Assert.fail("The Product creation Failed!");
						Reporter.log("Creation of Product Failed!",LOG_TO_STD_OUT);
					}
				}
			}		
		}
	}
	@Parameters({"product_jsonFilePath","product_jsonFileName"})
	@Test( enabled=TEST_ENABLED, timeOut=800000L, priority = 3 )
	public void testUc27_02createNewExternalProductWithAllChars(
			@Optional("input/catalogmanager/products") String product_jsonFilePath,
			@Optional("productsTemplate") String product_jsonFileName) throws FormException, JSONException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String prod_resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + product_jsonFilePath;
			String prod_resourceFile = product_jsonFileName;

			Reporter.log("\"Product\" is filled with reosurce file : ",
					LOG_TO_STD_OUT);
			Reporter.log("Resource path -> " + prod_resourcePath, LOG_TO_STD_OUT);
			Reporter.log("Resource file -> " + prod_resourceFile, LOG_TO_STD_OUT);
			JSONProducts setupProduct=new JSONProducts(prod_resourcePath, prod_resourceFile);
			ProductsForm pdForm=new ProductsForm(seleniumWebDriver, setupProduct,TIMEOUT, ATTEMPT_TIMEOUT);
			pdForm.openForm();
			
			
				JsonCurrentElement current = setupProduct.getCurrentElementById(2);
				
				if ( current.getEnabled() ){
			
					if(setupProduct.getType().equals("external"))
					{
						pdForm.configureExternalDefinition(setupProduct.getSupplier(), setupProduct.getName(), setupProduct.getDescription(), setupProduct.getTermsAndCondition(), setupProduct.getImageUrl());
						pdForm.openCharacteristicsTab();
						for(JsonProdCharacteristicElement chel : setupProduct.getCharacteristicsList())
						{
							if(chel.getEnabled())
							pdForm.configureProductCharacteristic(chel);
						}
					}
					pdForm.configureCostPrice(setupProduct.getCost(),setupProduct.getPrice());
					pdForm.configureAvailability(setupProduct.getStock(),setupProduct.getStartDate(),setupProduct.getEndDate());
					pdForm.saveProductButton();
					pdForm.clickRefreshButton();
					Boolean stat=pdForm.isProductInList(setupProduct.getName());
					if(stat==true)
					{
						Assert.assertTrue(stat);
						logger.info("Created Product Succesfully:" + setupProduct.getName());
					}
					else{
						Assert.fail("The Product creation Failed!");
						Reporter.log("Creation of Product Failed!",LOG_TO_STD_OUT);
					}
				}
			}	
	
	@Parameters({"product_jsonFilePath","product_jsonFileName"})
	@Test( enabled=TEST_ENABLED, timeOut=800000L, priority = 4 )
	public void testUc27_04createNewExternalProductWithRelatedProduct(
			@Optional("input/catalogmanager/products") String product_jsonFilePath,
			@Optional("productsTemplate") String product_jsonFileName) throws FormException, JSONException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String prod_resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + product_jsonFilePath;
			String prod_resourceFile = product_jsonFileName;

			Reporter.log("\"Product\" is filled with reosurce file : ",
					LOG_TO_STD_OUT);
			Reporter.log("Resource path -> " + prod_resourcePath, LOG_TO_STD_OUT);
			Reporter.log("Resource file -> " + prod_resourceFile, LOG_TO_STD_OUT);
			JSONProducts setupProduct=new JSONProducts(prod_resourcePath, prod_resourceFile);
			ProductsForm pdForm=new ProductsForm(seleniumWebDriver, setupProduct,TIMEOUT, ATTEMPT_TIMEOUT);
			pdForm.openForm();
			
			
				JsonCurrentElement current = setupProduct.getCurrentElementById(3);
				
				if ( current.getEnabled() ){
			
					if(setupProduct.getType().equals("external"))
					{
						pdForm.configureExternalDefinition(setupProduct.getSupplier(), setupProduct.getName(), setupProduct.getDescription(), setupProduct.getTermsAndCondition(), setupProduct.getImageUrl());
						pdForm.openCharacteristicsTab();
						for(JsonProdCharacteristicElement chel : setupProduct.getCharacteristicsList())
						{
							if(chel.getEnabled())
							pdForm.configureProductCharacteristic(chel);
						}
					}
					pdForm.configureCostPrice(setupProduct.getCost(),setupProduct.getPrice());
					pdForm.configureAvailability(setupProduct.getStock(),setupProduct.getStartDate(),setupProduct.getEndDate());
					pdForm.saveProductButton();
					pdForm.clickRefreshButton();
					Boolean stat=pdForm.isProductInList(setupProduct.getName());
					if(stat==true)
					{
						Assert.assertTrue(stat);
						logger.info("Created Product Succesfully:" + setupProduct.getName());
					}
					else{
						Assert.fail("The Product creation Failed!");
						Reporter.log("Creation of Product Failed!",LOG_TO_STD_OUT);
					}
				}
			}
	
	@Parameters({"product_jsonFilePath","product_jsonFileName"})
	@Test( enabled=TEST_ENABLED, timeOut=800000L, priority = 5 )
	public void testUc27_05createNewExternalProductWithSpecChars(
			@Optional("input/catalogmanager/products") String product_jsonFilePath,
			@Optional("productsTemplate") String product_jsonFileName) throws FormException, JSONException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String prod_resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + product_jsonFilePath;
			String prod_resourceFile = product_jsonFileName;

			Reporter.log("\"Product\" is filled with reosurce file : ",
					LOG_TO_STD_OUT);
			Reporter.log("Resource path -> " + prod_resourcePath, LOG_TO_STD_OUT);
			Reporter.log("Resource file -> " + prod_resourceFile, LOG_TO_STD_OUT);
			JSONProducts setupProduct=new JSONProducts(prod_resourcePath, prod_resourceFile);
			ProductsForm pdForm=new ProductsForm(seleniumWebDriver, setupProduct,TIMEOUT, ATTEMPT_TIMEOUT);
			pdForm.openForm();
			
			
				JsonCurrentElement current = setupProduct.getCurrentElementById(4);
				
				if ( current.getEnabled() ){
			
					if(setupProduct.getType().equals("external"))
					{
						pdForm.configureExternalDefinition(setupProduct.getSupplier(), setupProduct.getName(), setupProduct.getDescription(), setupProduct.getTermsAndCondition(), setupProduct.getImageUrl());
						pdForm.openCharacteristicsTab();
						for(JsonProdCharacteristicElement chel : setupProduct.getCharacteristicsList())
						{
							if(chel.getEnabled())
							pdForm.configureProductCharacteristic(chel);
						}
					}
					pdForm.configureCostPrice(setupProduct.getCost(),setupProduct.getPrice());
					pdForm.configureAvailability(setupProduct.getStock(),setupProduct.getStartDate(),setupProduct.getEndDate());
					pdForm.saveProductButton();
					pdForm.clickRefreshButton();
					Boolean stat=pdForm.isProductInList(setupProduct.getName());
					if(stat==true)
					{
						Assert.assertTrue(stat);
						logger.info("Created Product Succesfully:" + setupProduct.getName());
					}
					else{
						Assert.fail("The Product creation Failed!");
						Reporter.log("Creation of Product Failed!",LOG_TO_STD_OUT);
					}
				}
			}
	@Test( enabled=TEST_ENABLED, timeOut=800000L, priority = 6 )
	public void testUc27_06editInternalProduct() throws FormException, JSONException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			ProductsForm pdForm=new ProductsForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
			pdForm.openForm();
			pdForm.editProductByName("Points");
			pdForm.openCostAndPriceTab();
			pdForm.setUnitaryCost("50");
			pdForm.setListPrice("40");
			pdForm.openAvailabilityTab();
			pdForm.setAvailabilityStock("30");
			pdForm.saveProductButton();
			
				
			
	}
	@Test( enabled=TEST_ENABLED, timeOut=800000L, priority = 6 )
	public void testUc27_07editExternalProduct() throws FormException, JSONException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			ProductsForm pdForm=new ProductsForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
			pdForm.openForm();
			pdForm.editProductByName("Products4");
			pdForm.setProductName("Products4Modified");
			pdForm.openCharacteristicsTab();
			pdForm.addCharacteristicButton();
			pdForm.setCharacteristicType("Related Products");
			pdForm.selectByXPathAndVisibleText("//td[text()='Products']//ancestor::tr[1]//select[@class='gwt-ListBox']", "Products");
			pdForm.characteristicOKButton();
			pdForm.openCostAndPriceTab();
			pdForm.setUnitaryCost("50");
			pdForm.setListPrice("40");
			pdForm.openAvailabilityTab();
			pdForm.setAvailabilityStock("30");
			pdForm.saveProductButton();
			pdForm.clickRefreshButton();
			Boolean stat=pdForm.isProductInList("Products4Modified");
			if(stat==true)
			{
				Assert.assertTrue(stat);
				logger.info("Modified External Product Succesfully:" + "Products4Modified");
			}
			else{
				Assert.fail("The Product modification Failed!");
				Reporter.log("Modification of Product Failed!",LOG_TO_STD_OUT);
			}
			
	}
	@Test( enabled=TEST_ENABLED, timeOut=800000L, priority = 6 )
	public void testUc27_08deleteExternalProduct() throws FormException, JSONException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			ProductsForm pdForm=new ProductsForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
			pdForm.openForm();
			pdForm.deleteProductByName("Products4Modified");
			pdForm.handleJavascriptAlertAcceptDismiss(true);
			pdForm.clickRefreshButton();
			Boolean stat=pdForm.isProductInList("Products4Modified");
			if(!stat)
			{
				Assert.assertTrue(true);
				logger.info("Deleted External Product Succesfully:" + "Products4Modified");
			}
			else{
				Assert.fail("The Product deletion Failed!");
				Reporter.log("Deletion of Product Failed!",LOG_TO_STD_OUT);
			}
			
	}
	@Test( enabled=TEST_ENABLED, timeOut=800000L, priority = 7 )
	public void testUc27_09editUsedProduct() throws FormException, JSONException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			ProductsForm pdForm=new ProductsForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
			pdForm.openForm();
			if(!pdForm.isProductEditable("Products"))
			{
				Assert.assertTrue(true);
				logger.info("A product being used is not editable!");
			}
			else{
				Assert.fail("The Product is editable when it should not be!");
				Reporter.log("The Product is editable when it should not be!",LOG_TO_STD_OUT);
			}
			
	}
	@Test( enabled=TEST_ENABLED, timeOut=800000L, priority = 8 )
	public void testUc27_10deleteUsedProduct() throws FormException, JSONException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			ProductsForm pdForm=new ProductsForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
			pdForm.openForm();
			if(!pdForm.isProductDeletable("Products"))
			{
				Assert.assertTrue(true);
				logger.info("A product being used cannot be deleted!");
			}
			else{
				Assert.fail("The Product is deletable when it should not be!");
				Reporter.log("The Product is deletable when it should not be!",LOG_TO_STD_OUT);
			}
			
	}		
}
