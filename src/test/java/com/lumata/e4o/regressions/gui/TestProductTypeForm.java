package com.lumata.e4o.regressions.gui;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.junit.internal.runners.statements.Fail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.validating.Format;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.ProductTypesForm;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;

public class TestProductTypeForm extends ParentUITestCase{
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 200;
	
	private final boolean testEnabled = true;
	
//	private SeleniumWebDriver seleniumWebDriver;
	
	private ProductTypesForm productTypesForm;
	private JSONProductTypes setupProductTypes = null;
//	@BeforeMethod
//	protected void startSession(Method method) throws Exception {
//		seleniumWebDriver.setTestName(method.getName());
//	}
	@Test(enabled=testEnabled)
	@Parameters({"jsonFilePath","jsonFileName"})
	public void testProductTypeCreation(@Optional String jsonFilePath, @Optional String jsonFileName) throws JSONSException,FormException
	{
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Boolean status=false;
		Reporter.log("Creation of \"Product Types Form\".", PRINT2STDOUT__);

		String resourcePath = currentResourceStartPath + jsonFilePath;
		String resourceFile = jsonFileName;

		Reporter.log("\"Product Types\" is filled with reosurce file : ",
				PRINT2STDOUT__);
		Reporter.log("Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log("Resource file -> " + resourceFile, PRINT2STDOUT__);
		
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
