package com.lumata.e4o.gui.catalogmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.ErrorModificableElement;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.CharacteristicType;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonUnit;

public class ProductTypesForm extends CatalogueManagerForm {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProductTypesForm.class);
	
	/**
	 * 
	 */
	private JSONProductTypes productTypesCfg;
	
	/**
	 * 
	 * @param selenium
	 * @param timeout
	 * @param interval
	 */
	public ProductTypesForm(SeleniumWebDriver selenium, JSONProductTypes configuration, long timeout, long interval) {
		
		super(selenium, timeout, interval);
		
		this.productTypesCfg = configuration;
	}

	/**
	 * 
	 */
	public ProductTypesForm openForm() throws FormException {
		
		super.openForm().clickId( "gwt-debug-actrule-catalog-productTypes" );
		
		return this;
	}
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public ProductTypesForm addProductTypes() throws FormException, JSONSException {
	
		int numbProdType = productTypesCfg.getList().size();
		
		for (int index = 0; index < numbProdType; index++) {
			
			JsonCurrentElement current = productTypesCfg.getCurrentElementById(index);
			
			if ( current.getEnabled() ) {
				
				configureProductType( 	productTypesCfg.getName(), 
										productTypesCfg.getDescription(),
										productTypesCfg.getCharacteristicsList()).
				saveProductType();
			}
		}
		
		return this;
	}	
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public ProductTypesForm configureProductType(String name, String description, List<JsonCharacteristicElement> chList) throws FormException, JSONSException {
		
		clickXPath("//button[@name='btn-add' and @title='Add Product Type']");
		
		sendKeysByXPath("//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']", name);
		
		if ( description != null && description.length() != 0 )
			sendKeysByXPath("//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-descriptionTextBox']", description);
		
		for (JsonCharacteristicElement chElem : chList) {
			
			if ( chElem.getEnabled() ) {
				
				// add a new ch
				clickXPath("//div[text()='Create product type']//ancestor::div[2]//button[@title='Add']");
				
				fillCharacteristicElement(chElem);
				
				
				CharacteristicsSaveHandler handler = new CharacteristicsSaveHandler( 	selenium.getWrappedDriver(), 
																						chElem);
				handler.saveAction();
			}
			
		}
		
		return this;
	}
	
	/**
	 * 
	 * @param chElem
	 * @throws FormException 
	 * @throws JSONSException 
	 */
	private void fillCharacteristicElement(JsonCharacteristicElement chElem) throws FormException, JSONSException {

		CharacteristicType type = chElem.getType();
		
		sendKeysByXPath("//td[contains(text(),'Characteristic Name')]//ancestor::tr[1]//input", chElem.getName());
		selectByXPathAndVisibleText("//td[contains(text(),'Type')]//ancestor::tr[1]//select", type.toString());

		if ( type.equals(CharacteristicType.List) ) {
			
			List<String> defaultValue = chElem.getList().getDefault();
			List<String> values = chElem.getList().getValue();
			
			for (int index = 0; index < values.size(); index++) 
				clickXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::div[2]//button[@title='Add']");
			
			// element to be filled
			List<WebElement> inputElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='text']"));
			
			// checkboxes
			List<WebElement> checkElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='checkbox']"));
			
			// fill
			for (int index = 0; index < values.size(); index++) { 
				
				WebElement input = inputElements.get(index);
				String value = values.get(index);
				
				input.sendKeys(value);
				
				if ( defaultValue != null && defaultValue.contains(values.get(index)) ) 
					checkElements.get(index).click();
			}
		}
		else if ( type.equals(CharacteristicType.Choice) ) {
			
			String defaultValue = chElem.getChoice().getDefault();
			List<String> values = chElem.getChoice().getValue();
			
			for (int index = 0; index < values.size(); index++) 
				clickXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::div[2]//button[@title='Add']");
			
			// element to be filled
			List<WebElement> inputElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='text']"));
			
			// ratio
			List<WebElement> checkElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='radio']"));
			
			// fill
			for (int index = 0; index < values.size(); index++) { 
				
				WebElement input = inputElements.get(index);
				String value = values.get(index);
				
				input.sendKeys(value);
				
				if ( defaultValue != null && defaultValue.equals(values.get(index)) ) 
					checkElements.get(index).click();
			}
		}
		else if ( type.equals(CharacteristicType.Text) ) {
			
			String textValue = chElem.getText();
			if ( textValue != null && textValue.length() != 0 )
				sendKeysByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-defaultValueTextBox']", textValue);
		}
		else if ( type.equals(CharacteristicType.Unit) ) {

			JsonUnit unit = chElem.getUnit();
			String unitName = unit.getUnit();
			String unitValue = unit.getValue();
			
			sendKeysByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-unitTextBox']", unitName);	
			
			if ( unitValue != null && unitValue.length() != 0 )
				sendKeysByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-defaultValueTextBox']", unitValue);		
		} 
	}

	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException 
	 */
	public ProductTypesForm saveProductType() throws FormException, JSONSException {
		
		ProductTypesSaveHandler handler = new ProductTypesSaveHandler( 	selenium.getWrappedDriver(), 
																		productTypesCfg.getCurrentElement());
		handler.saveAction();
		
		return this;
	}

	/**
	 * This method delete a list of "Product Types" into running system.
	 * If input var-args is empty or null, this method deletes each Product Types
	 * 
	 * @param productTypeNames
	 * 
	 * @return true if wanted product types are corrected removed
	 * 
	 * @throws FormException 
	 */
	public boolean deleteProductTypes(String... productTypeNames) throws FormException {

		List<String> productTypesLabel = null;
		Boolean resp = Boolean.FALSE;
		
		if ( productTypeNames != null && productTypeNames.length != 0 )
			productTypesLabel = Arrays.asList(productTypeNames);
		else { // fetch every product types present on UI
			
			productTypesLabel = new ArrayList<String>();
			
			String rootPath = "//table[contains(@class, \"page-ProductTypePageView\")]";
			String subPath = "//tr[contains(@class,\"contentRow cycle\")]//td[@class=\"column_description\"][1]";
		
			List<WebElement> ptLabels = searchListByXPath(rootPath, rootPath + subPath);
			
			for (WebElement webElement : ptLabels)
				productTypesLabel.add(webElement.getText());
		}

		logger.debug("Product Types element to be deleted : " + productTypesLabel);
		
		try {
			
			for (String cnName : productTypesLabel) {
				
				String singleRule = "//div[text()='"+cnName+"']//ancestor::tr[1]//*[@name='btn-delete']";

				logger.debug("Try to delete \"Product Types\" with name + \""+cnName+"\".");
				
				clickXPath(singleRule);
				
				handleJavascriptAlertAcceptDismiss(true);
			}
			
			resp = Boolean.TRUE;
			
		} catch ( FormException e ) {

			logger.error("Error during delete \"Product Types\" : " + e.getMessage());
			
			resp = Boolean.FALSE;
		}

		return resp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private class ProductTypesSaveHandler extends FormSaveConfigurationHandler {

		protected ProductTypesSaveHandler(	WebDriver inDriver,
											JsonCurrentElement inCurrentElement) {
			
			super(inDriver, inCurrentElement);
		}

		@Override
		protected Boolean containsErrorElement() {

			Integer numbers = null;
			Boolean resp = Boolean.TRUE;
	
			// error condition
			//*[contains(@class,'errorBackground')]
			
			List<WebElement> elements = getWebDriver().findElements(By.xpath("//*[contains(@class,'errorBackground')]"));
	
			if ( elements == null )
				numbers = 0;
			else
				numbers = elements.size();
			
			if ( numbers != 0 )
				resp = true;
			else
				resp = false;
			
			return resp;
		}

		/**
		 * 
		 */
		private WebElement saveElement = null;
		
		@Override
		protected WebElement getSaveWebElement() {

			if ( saveElement == null )
				saveElement = getWebDriver().findElement(By.xpath("//div[text()='Create product type']//ancestor::div[2]//button[@title='Save']")); 
			
			return saveElement;
		}

		@Override
		protected ElementErrorConditionType defineErrorCondition() {

			ElementErrorConditionType condition = null;
			
			WebElement dialogBox = getWebDriver().findElement(By.xpath("//div[@class='gwt-DialogBox']"));
			
			// error condition
			//div[text()='Bonus name already used']			
			List<WebElement> element = dialogBox.findElements(By.xpath("//div[text()='Cannot add product type, name is already used.']"));
								
			if ( element.size() != 0 )
				condition = ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
			else
				condition = ElementErrorConditionType.GENERAL_ERROR;

			return condition;
		}

		@Override
		protected Boolean cancelAction() {

			Boolean resp = Boolean.FALSE;

			try {

				getWebDriver().findElement(By.xpath("//div[text()='Create product type']//ancestor::div[2]//button[@title='Cancel']")).click();
					
				resp = Boolean.TRUE;
			}
			catch ( NoSuchElementException e ) {
			
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}

		@Override
		protected Boolean addTimestampAction() {

			Boolean resp = Boolean.FALSE;
			
			try {
				
				WebElement nameElem = getWebDriver().findElement(By.xpath("//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']"));
				nameElem.click();
				nameElem.clear();
				
				String name = getCurrentElement().getStringFromPath("name");
				name += TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
				getCurrentElement().modifyStringFromPath("name", name);
				
				nameElem.sendKeys(getCurrentElement().getStringFromPath("name"));
				
				saveAction();
				
				resp = Boolean.TRUE;
				
			} catch ( NoSuchElementException | FormException | JSONSException e ) {
				
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private class CharacteristicsSaveHandler extends FormSaveConfigurationHandler {

		protected CharacteristicsSaveHandler(	WebDriver inDriver,
												ErrorModificableElement inCurrentElement) {
			
			super(inDriver, inCurrentElement);
		}

		@Override
		protected Boolean containsErrorElement() {

			Integer numbers = null;
			Boolean resp = Boolean.TRUE;
	
			// error condition
			//*[contains(@class,'errorBackground')]
			
			List<WebElement> elements = getWebDriver().findElements(By.xpath("//*[contains(@class,'errorBackground')]"));
	
			if ( elements == null )
				numbers = 0;
			else
				numbers = elements.size();
			
			if ( numbers != 0 )
				resp = true;
			else
				resp = false;
			
			return resp;
		}

		/**
		 * 
		 */
		private WebElement saveElement = null;
		
		@Override
		protected WebElement getSaveWebElement() {

			if ( saveElement == null )
				saveElement = getWebDriver().findElement(By.xpath("//div[text()='Adding a new characteritic type']//ancestor::div[2]//button[@title='OK']")); 
			
			return saveElement;
		}

		@Override
		protected ElementErrorConditionType defineErrorCondition() {

			ElementErrorConditionType condition = null;
			
			WebElement dialogBox = getWebDriver().findElement(By.xpath("//div[@class='gwt-DialogBox']"));
			
			// error condition
			//div[text()='Bonus name already used']			
			List<WebElement> element = dialogBox.findElements(By.xpath("//div[contains(text(),'Cannot add characteristic, name ')]"));
								
			if ( element.size() != 0 )
				condition = ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
			else
				condition = ElementErrorConditionType.GENERAL_ERROR;

			return condition;
		}

		@Override
		protected Boolean cancelAction() {

			Boolean resp = Boolean.FALSE;

			try {

				getWebDriver().findElement(By.xpath("//div[text()='Adding a new characteritic type']//ancestor::div[2]//button[@title='Cancel']")).click();
					
				resp = Boolean.TRUE;
			}
			catch ( NoSuchElementException e ) {
			
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}

		@Override
		protected Boolean addTimestampAction() {
			
			Boolean resp = Boolean.FALSE;
			
			try {

				WebElement nameElem = getWebDriver().findElement(By.xpath("//td[contains(text(),'Characteristic Name')]//ancestor::tr[1]//input"));
				nameElem.click();
				nameElem.clear();
				
				String name = getCurrentElement().getStringFromPath("name");
				name += TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
				getCurrentElement().modifyStringFromPath("name", name);
				
				nameElem.sendKeys(getCurrentElement().getStringFromPath("name"));
				
				saveAction();
				
				resp = Boolean.TRUE;
				
			} catch ( NoSuchElementException | FormException | JSONSException e ) {
				
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}
		
	}
}
