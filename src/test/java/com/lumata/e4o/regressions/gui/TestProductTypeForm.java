package com.lumata.e4o.regressions.gui;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.validating.Format;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.ProductTypesForm;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(
	@TCOwner( name="Parvinder Bhogra", email="parvinder.bhogra@lumatagroup.com" )
)
@TCSeleniumWebDriver
public class TestProductTypeForm extends ParentTestCase{
	
	private ProductTypesForm productTypesForm;
	private JSONProductTypes setupProductTypes = null;
	private String productTypeName=null;
	
	@Test(enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT,priority=1)
	@Parameters({"jsonFilePath","jsonFileName"})
	public void testUc26_01CreateNewProductType(@Optional("input/catalogmanager/productTypes/") String jsonFilePath, @Optional("productTypeList") String jsonFileName) throws JSONSException,FormException
	{
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Boolean status=false;
		Reporter.log("Creation of \"Product Types Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath;
		String resourceFile = jsonFileName;

		Reporter.log("\"Product Types\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		
		setupProductTypes = new JSONProductTypes(resourcePath, resourceFile);

		productTypesForm= new ProductTypesForm(seleniumWebDriver,setupProductTypes,TIMEOUT, ATTEMPT_TIMEOUT);
		
		productTypesForm.openForm();
		int numbProdType = setupProductTypes.getList().size();
		
		for (int index = 0; index < numbProdType; index++) {
			
			JsonCurrentElement current = setupProductTypes.getCurrentElementById(index);
			
			if ( current.getEnabled() ){
				setProductTypeName(setupProductTypes.getName());
				productTypesForm.configureProductType(getProductTypeName(),setupProductTypes.getDescription());
				for (JsonCharacteristicElement chElem : setupProductTypes.getCharacteristicsList()) {
			
					if ( chElem.getEnabled() ) {
				
						productTypesForm.addCharacteristicButton();
						productTypesForm.fillCharacteristicElement(chElem.getName(),chElem);
						productTypesForm.saveCharacteristic();
					}
				}
				productTypesForm.saveProductType();
				status=productTypesForm.isProductTypeInList(productTypeName);
				if(status==true)
				{
					Assert.assertTrue(status);
					Reporter.log("Product Types Created Succesfully!");
				}
				else
				{
					//Assert.assertTrue(status,"The creation of Product Failed!");
					Assert.fail("The Product Types creation Failed!");
					Reporter.log("Creation of Product Types Failed!");
				}
			}
	
		}
		
	}
	
	@Test(enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT,priority=2,dependsOnMethods = { "testUc26_01CreateNewProductType" })
	public void testUc26_02EditProductType() throws JSONSException,FormException
	{
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Boolean status=false;
		Reporter.log("Edit of \"Product Types Form\".", LOG_TO_STD_OUT);

		productTypesForm= new ProductTypesForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
		productTypesForm.openForm();
		productTypesForm.editProductTypeByName(getProductTypeName());
		setProductTypeName("ProductAMod");
		productTypesForm.editProductTypeName(getProductTypeName());
		productTypesForm.editCharacteristicButton();
		productTypesForm.setCharacteristicName("newText");
		productTypesForm.setCharacteristicType("Text");
		productTypesForm.setTextCharTypeValue("new");
		productTypesForm.saveCharacteristic();
		productTypesForm.saveProductType();
			
		productTypesForm.editProductTypeByName(getProductTypeName());
		status=productTypesForm.verifyCharacteristicAdditionForProductType("newText");
		if(status==true)
		{
			Assert.assertTrue(status);
			Reporter.log("Product Types Edited Succesfully!");
		}
		else
		{
			Assert.fail("The Product Type modification Failed!");
			Reporter.log("Modification of Product Types Failed!");
		}
		productTypesForm.productTypeCancelButton();
	}
	@Test(enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT,priority=3,dependsOnMethods = { "testUc26_01CreateNewProductType" })
	public void testUc26_04EditProductType() throws JSONSException
	{
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Edit of \"Product Types Form\".", LOG_TO_STD_OUT);

		productTypesForm= new ProductTypesForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
		try{
			productTypesForm.openForm();
			productTypesForm.editProductTypeByName(getProductTypeName());
			productTypesForm.deletecharacteristicByName(getProductTypeName(),"newText");
			productTypesForm.saveProductType();
		}catch(FormException e)
		{
			Assert.fail("Deletion of Product Type Characteristic failed!"+e.getMessage());
			Reporter.log("Deletion of \"Characteistic\" failed.", LOG_TO_STD_OUT);
		}
	}
	@Test(enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT,priority=4,dependsOnMethods = { "testUc26_01CreateNewProductType" })
	public void testUc26_05EditProductType() throws JSONSException
	{
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Edit of \"Product Types Form\".", LOG_TO_STD_OUT);

		productTypesForm= new ProductTypesForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
		try{
			productTypesForm.openForm();
			productTypesForm.editProductTypeByName(getProductTypeName());
			productTypesForm.editcharacteristicByName(getProductTypeName(),"list1");
			List<String> chars=new ArrayList<String>();
			chars.add("on");
			chars.add("to");
			chars.add("too");
			productTypesForm.editCharacteristicValueButton().setListCharacteristicValues(chars,null).saveCharacteristic();
			productTypesForm.saveProductType();
		}catch(FormException e)
		{
			Assert.fail("Modification of Product Type Characteristic failed!"+e.getMessage());
			Reporter.log("Modification of \"Characteistic\" failed.", LOG_TO_STD_OUT);
		}
	}
	@Test(enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT,priority=5,dependsOnMethods = { "testUc26_01CreateNewProductType" })
	public void testUc26_03DeleteProductType() throws JSONSException,FormException{
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Boolean status=false;
		Reporter.log("Delete\"Product Types\".", LOG_TO_STD_OUT);

		productTypesForm= new ProductTypesForm(seleniumWebDriver,TIMEOUT, ATTEMPT_TIMEOUT);
		
		productTypesForm.openForm();
		status=productTypesForm.deleteProductTypes(getProductTypeName());
		if(status==true)
		{
			Assert.assertTrue(status);
			Reporter.log("Product Types Deleted Succesfully!");
		}
		else
		{
			Assert.fail("The Product Types Deletion Failed!");
			Reporter.log("Deletion of Product Types Failed!");
		}
	}
	
	public void setProductTypeName(String name) throws FormException{
		this.productTypeName=name;
	}
	public String getProductTypeName() throws FormException{
		return this.productTypeName;
	}

}
