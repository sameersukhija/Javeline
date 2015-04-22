package com.lumata.e4o.regressions.gui;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
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
	@BeforeMethod
	public void initProductTypeForm( Method method ) throws NetworkEnvironmentException, FormException {		
	
		seleniumWebDriver.setTestName( method.getName() );
		
	}
	@Test(enabled=TEST_ENABLED)
	@Parameters({"jsonFilePath","jsonFileName"})
	public void testProductTypeCreation(@Optional String jsonFilePath, @Optional String jsonFileName) throws JSONSException,FormException
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
				String productTypeName=Format.addTimestamp(setupProductTypes.getName() + "_");
				productTypesForm.configureProductType(productTypeName,setupProductTypes.getDescription());
				for (JsonCharacteristicElement chElem : setupProductTypes.getCharacteristicsList()) {
			
					if ( chElem.getEnabled() ) {
				
						productTypesForm.addCharacteristicButton();
						productTypesForm.fillCharacteristicElement(Format.addTimestamp(chElem.getName()),chElem);
						productTypesForm.saveCharacteristic();
					}
				}
				productTypesForm.saveProductType();
				status=productTypesForm.isProductTypeInList(productTypeName);
				if(status==true)
				{
					Assert.assertTrue(status);
					Reporter.log("Product Created Succesfully!");
				}
				else
				{
					//Assert.assertTrue(status,"The creation of Product Failed!");
					Assert.fail("The product creation Failed!");
					Reporter.log("Creation of Product Failed!");
				}
			}
	
		}
		
	}

}
