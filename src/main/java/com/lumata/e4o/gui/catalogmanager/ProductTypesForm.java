package com.lumata.e4o.gui.catalogmanager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.CharacteristicType;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonUnit;

public class ProductTypesForm extends CatalogueManagerForm {

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
	public ProductTypesForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-catalog-productTypes" );
		
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
				
				// OK a new ch
				clickXPath("//div[text()='Adding a new characteritic type']//ancestor::div[2]//button[@title='OK']");
				
				// error check
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
		
		if ( type.equals(CharacteristicType.List) || type.equals(CharacteristicType.Choice) ) {
			
			String defaultValue = chElem.getDefault();
			List<String> values = type.equals(CharacteristicType.List) ? chElem.getList() : chElem.getChoice();
			
			for (int index = 0; index < values.size(); index++) 
				clickXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::div[2]//button[@title='Add']");		
			
			// element to be filled
			List<WebElement> inputElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='text']"));
			
			String secondColRule = "//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='";
			
			secondColRule = secondColRule + ( type.equals(CharacteristicType.List) ? "checkbox']" : "radio']" );
			
			List<WebElement> checkElements = selenium.getWrappedDriver().findElements(By.xpath(secondColRule));

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
	 */
	public ProductTypesForm saveProductType() throws FormException {
		
		super.clickXPath( "//div[text()='Create product type']//ancestor::div[2]//button[@title='Save']" );
		
		// gestione errori
		
		return this;
	}
}
