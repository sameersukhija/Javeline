package com.lumata.e4o.gui.catalogmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.e4o.exceptions.FormException;
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
	
	/* constructor for initializing without product type json configuration*/ 
	public ProductTypesForm(SeleniumWebDriver selenium,long timeout, long interval) {
		
		super(selenium, timeout, interval);
		
	}
	/**
	 * 
	 * @param selenium
	 * @param timeout
	 * @param interval
	 */
	public ProductTypesForm(SeleniumWebDriver selenium, JSONProductTypes productTypesCfg, long timeout, long interval) {
		
		super(selenium, timeout, interval);
		
		setProductTypesCfg( productTypesCfg );
		
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
//	public ProductTypesForm addProductTypes(String name,String description) throws FormException, JSONSException {
//	
//		
//				configureProductType(name, description);
//		
//		return this;
//	}	
	
	public ProductTypesForm addProductTypeButton() throws FormException {
		super.clickXPath("//button[@name='btn-add' and @title='Add Product Type']");
		return this;
	}
	
	public ProductTypesForm setProductTypeName(String name) throws FormException {
		super.sendKeysByXPath("//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']", name);
		return this;
	}
	public ProductTypesForm editProductTypeName(String name) throws FormException {
		super.sendKeysByXPath("//div[text()='Edit product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']", name);
		return this;
	}
	
	public String getProductTypeName() throws FormException {
		return super.getValueByXPath("//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']");
	}
	
	public ProductTypesForm setProductTypeDescription(String description) throws FormException {
		super.sendKeysByXPath("//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-descriptionTextBox']", description);
		return this;
	}
	
	public String getProductTypeDescription() throws FormException {
		return super.getValueByXPath("//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-descriptionTextBox']");
	}
	
	public ProductTypesForm addCharacteristicButton() throws FormException {
		super.clickXPath("//div[text()='Create product type']//ancestor::div[2]//button[@title='Add']");
		return this;
	}
	public ProductTypesForm editCharacteristicButton() throws FormException {
		super.clickXPath("//div[text()='Edit product type']//ancestor::div[2]//button[@title='Add']");
		return this;
	}
	
	public ProductTypesForm setCharacteristicName(String ch_name) throws FormException {
		super.sendKeysByXPath("//td[contains(text(),'Characteristic Name')]//ancestor::tr[1]//input", ch_name);
		return this;
	}
	
	public String getCharacteristicName() throws FormException {
		return super.getValueByXPath("//td[contains(text(),'Characteristic Name')]//ancestor::tr[1]//input");
	}
	
	public ProductTypesForm setCharacteristicType(String ch_type) throws FormException {
		super.selectByXPathAndVisibleText("//td[contains(text(),'Type')]//ancestor::tr[1]//select", ch_type);
		return this;
	}
	
	public String getCharacteristicType() throws FormException {
		return super.getValueByXPath("//td[contains(text(),'Type')]//ancestor::tr[1]//select");
	}
	
	public ProductTypesForm addCharacteristicValueButton() throws FormException {
		super.clickXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::div[2]//button[@title='Add']");
		return this;
	}
	public ProductTypesForm editCharacteristicValueButton() throws FormException {
		super.clickXPath("//div[contains(text(),'Editing a characteritic type')]//ancestor::div[2]//button[@title='Add']");
		return this;
	}
	
	public ProductTypesForm setListCharacteristicValues(List<String> values,List<String> defaultValue) throws FormException {
		List<WebElement> inputElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='text']"));
		
		// checkboxes
		List<WebElement> checkElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='checkbox']"));
		
		// fill
		for (int index = 0; index < values.size(); index++) { 
			
			WebElement input = inputElements.get(index);
			String value = values.get(index);
			
			input.sendKeys(value);
			
			if ( defaultValue != null && defaultValue.contains(values.get(index)) ) {
				checkElements.get(index).click();
			}
		}
		return this;
	}
	
	public ProductTypesForm setChoiceCharacteristicValues(List<String> values,String defaultValue) throws FormException {
		// element to be filled
		List<WebElement> inputElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='text']"));
		
		// ratio
		List<WebElement> radioElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='radio']"));
		
		// fill
		for (int index = 0; index < values.size(); index++) { 
			
			WebElement input = inputElements.get(index);
			String value = values.get(index);
			
			input.sendKeys(value);
			
			if ( defaultValue != null && defaultValue.equals(values.get(index)) ) {
				radioElements.get(index).click();
			}							
		}
		return this;
	}
	
	public ProductTypesForm setTextCharTypeValue(String value) throws FormException {
		super.sendKeysByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-defaultValueTextBox']", value);
		return this;
	}
	
	public String getTextCharTypeValue() throws FormException {
		return super.getValueByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-defaultValueTextBox']");
	}
	
	public ProductTypesForm setUnitTypeName(String unitName) throws FormException {
		super.sendKeysByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-unitTextBox']", unitName);
		return this;
	}
	
	public String getUnitTypeName() throws FormException {
		return super.getValueByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-unitTextBox']");
	}
	
	public ProductTypesForm setUnitTypeValue(String unitValue) throws FormException {
		super.sendKeysByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-defaultValueTextBox']", unitValue);
		return this;
	}
	
	public String getUnitTypeValue() throws FormException {
		return super.getValueByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-defaultValueTextBox']");
	}
	
public Boolean verifyCharacteristicAdditionForProductType(String charName) throws FormException
{
	Boolean status=false;
	String rootXpath="//div[text()='Product Type Characteristics']//ancestor::tbody[1]";
	String subPath="//tr[contains(@class,'contentRow cycle')]//td[contains(@class,'column_description')][1]";
	List<WebElement> characteristics = getListByXPath(rootXpath, rootXpath + subPath);
	for( WebElement charEl : characteristics ) {
		System.out.println("The text from the webelement is:" +charEl.getText());
		
		if( charEl.getText().trim().equals(charName)) {
			
			status=true;
			
		}
	}
	return status;
}
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public ProductTypesForm configureProductType(String name, String description) throws FormException, JSONSException {
		
		addProductTypeButton();
		setProductTypeName(name);
		
		
		if ( description != null && description.length() != 0 ) {
			setProductTypeDescription(description);
		}
		return this;
	}
	
	/**
	 * 
	 * @param chElem
	 * @throws FormException 
	 * @throws JSONSException 
	 */
	public void fillCharacteristicElement(String chname,JsonCharacteristicElement chElem) throws FormException, JSONSException {

		CharacteristicType type = chElem.getType();
		
		setCharacteristicName(chname);
		setCharacteristicType(type.toString());

		if ( type.equals(CharacteristicType.List) ) {
			
			List<String> defaultValue = chElem.getList().getDefault();
			List<String> values = chElem.getList().getValue();
			
			for (int index = 0; index < values.size(); index++) 
				addCharacteristicValueButton();
				setListCharacteristicValues(values,defaultValue);
			// element to be filled
			
		}
		else if ( type.equals(CharacteristicType.Choice) ) {
			
			String defaultValue = chElem.getChoice().getDefault();
			List<String> values = chElem.getChoice().getValue();
			
			for (int index = 0; index < values.size(); index++) { 
				addCharacteristicValueButton();
			}
			setChoiceCharacteristicValues(values,defaultValue);
			
		}
		else if ( type.equals(CharacteristicType.Text) ) {
			
			String textValue = chElem.getText();
			if ( textValue != null && textValue.length() != 0 ) {
				setTextCharTypeValue(textValue);
			}
		}
		else if ( type.equals(CharacteristicType.Unit) ) {

			JsonUnit unit = chElem.getUnit();
			String unitName = unit.getUnit();
			String unitValue = unit.getValue();
			
			setUnitTypeName(unitName);			
			if ( unitValue != null && unitValue.length() != 0 ) {
				setUnitTypeValue(unitValue);	
			}
		} 
	}

	public ProductTypesForm saveCharacteristic() throws FormException {
		super.clickName( "btn-ok" );
		return this;
	}
	
	public ProductTypesForm saveProductType() throws FormException {
		super.clickName( "btn-save" );
		
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
		
		if ( productTypeNames != null && productTypeNames.length != 0 ) {
			productTypesLabel = Arrays.asList(productTypeNames);
		} else { // fetch every product types present on UI
			
			productTypesLabel = new ArrayList<String>();			
		
			List<WebElement> ptLabels = getProductTypeList();
			
			for (WebElement webElement : ptLabels) {
				productTypesLabel.add(webElement.getText());
			}
			
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
	
	public List<WebElement> getProductTypeList() throws FormException {
		
		String rootPath = "//table[contains(@class, 'page-ProductTypePageView')]";
		String subPath = "//tr[contains(@class, 'contentRow cycle')]//td[@class='column_description']";
	
		List<WebElement> productTypeList = getListByXPath(rootPath, rootPath + subPath);
		return productTypeList;
			
	}
	
	public Boolean isProductTypeInList( String productTypeName ) throws FormException {
		
		List<WebElement> productTypeList = getProductTypeList();
				
		for( WebElement productTypeEl : productTypeList ) {
			
			if( productTypeEl.getText().trim().equals(productTypeName)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	} 
	
	public WebElement getProductTypeInListByName( String productTypeName ) throws FormException {
		
		List<WebElement> productTypeList = getProductTypeList();
		
		for( WebElement productTypeEl : productTypeList ) {
			System.out.println("The text from the webelement is:" +productTypeEl.getText());
			
			if( productTypeEl.getText().trim().equals(productTypeName)) {
				
				return productTypeEl;
				
			}
			
		}
		
		return null;
		
	}
	
	public ProductTypesForm editProductTypeByName( String productTypeName ) throws FormException {
		
		List<WebElement> productTypeList = getProductTypeList();
		
		for( int el = 0; el < productTypeList.size(); el++ ) {
						
			if( productTypeList.get( el ).getText().trim().equals( productTypeName ) ) {
	
				WebElement editBtn = super.search( SearchBy.XPATH , "//div[text()='"+productTypeName+"']//ancestor::tr[1]//*[@name='btn-edit']");
				
				if( null != editBtn ) {
					
					editBtn.click();
					WebDriverWait wait= new WebDriverWait(selenium.getWrappedDriver(), 15);
					wait.until(ExpectedConditions.elementToBeClickable(super.search( SearchBy.XPATH,"//div[text()=\"Edit product type\"]")));
	
				}
				
			}
			
		}
			
		return this;
		
	}
	
	public List<WebElement> getcharListForProductType(String productTypeName) throws FormException {
		
		//editProductTypeByName(productTypeName);
		String rootPath = "//table[contains(@class, 'tableList')]";
		String subPath = "//tr[contains(@class,\"contentRow cycle\")]//td[@class=\"column_description\"][1]";
	
		List<WebElement>charList = getListByXPath(rootPath, rootPath + subPath);
		return charList;
			
	}
	
	public ProductTypesForm editcharacteristicByName(String productTypeName, String charName ) throws FormException {
		
		List<WebElement> charList = getcharListForProductType(productTypeName);
		
		for( int el = 0; el < charList.size(); el++ ) {
						
			if( charList.get( el ).getText().trim().equals( charName ) ) {
	
				WebElement editBtn = super.search( SearchBy.XPATH , "//div[text()='"+charName+"']//ancestor::tr[1]//*[@name='btn-edit']");
				
				if( null != editBtn ) {
					
					editBtn.click();
					WebDriverWait wait= new WebDriverWait(selenium.getWrappedDriver(), 15);
					wait.until(ExpectedConditions.elementToBeClickable(super.search( SearchBy.XPATH,"//div[text()=\"Editing a characteritic type\"]")));
	
				}
				
			}
			
		}
			
		return this;
		
	}
	
public ProductTypesForm deletecharacteristicByName(String productTypeName, String charName ) throws FormException {
		
		List<WebElement> charList = getcharListForProductType(productTypeName);
		
		for( int el = 0; el < charList.size(); el++ ) {
						
			if( charList.get( el ).getText().trim().equals( charName ) ) {
	
				WebElement editBtn = super.search( SearchBy.XPATH , "//div[text()='"+charName+"']//ancestor::tr[1]//*[@name='btn-delete']");
				
				if( null != editBtn ) {
					
					editBtn.click();
	
				}
				
			}
			
		}
			
		return this;
		
	}
	
	public ProductTypesForm characteristicCancelButton() throws FormException {
		super.clickXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::div[@class='gwt-DialogBox']//button[@name='btn-cancel']");
		return this;
	}
	
	public ProductTypesForm productTypeCancelButton() throws FormException {
		super.clickXPath("//button[@name='btn-cancel' and @title='Cancel']");
		return this;
	}
	
	public Boolean isProductTypeDuplicated() throws FormException {
		
		Boolean isProductTypeDuplicated = false;
		
		final String DIALOG_XPATH = "//div[@class='gwt-PopupPanel']";
		final String DIALOG_ERROR_MESSAGE_XPATH = DIALOG_XPATH + "//div[contains(text(), 'Cannot add product type, name is already used.')]"; 
				
		WebElement errorMessage = super.search( SeleniumUtils.SearchBy.XPATH, DIALOG_ERROR_MESSAGE_XPATH, 1000L, 200L );
		
		if( null != errorMessage ) { 
			
			isProductTypeDuplicated = true;
			
			clickXPath( DIALOG_ERROR_MESSAGE_XPATH );
			productTypeCancelButton();
			
		}
							
		return isProductTypeDuplicated;
		
	}
	
	public Boolean isCharTypeDuplicated() throws FormException {
				
		Boolean isCharTypeDuplicated = false;
		
		final String DIALOG_XPATH = "//div[@class='gwt-PopupPanel']";
		final String DIALOG_ERROR_MESSAGE_XPATH = DIALOG_XPATH + "//div[contains(text(), 'Cannot add characteristic, name {0} is already used.')]"; 
				
		WebElement errorMessage = super.search( SeleniumUtils.SearchBy.XPATH, DIALOG_ERROR_MESSAGE_XPATH, 1000L, 200L );
		
		if( null != errorMessage ) { 
			
			isCharTypeDuplicated = true;
			
			clickXPath( DIALOG_ERROR_MESSAGE_XPATH );
			characteristicCancelButton();
			
		}
						
		return isCharTypeDuplicated;
		
	}
	
	public JSONProductTypes getProductTypesCfg() {
		return this.productTypesCfg;
	}
	
	public void setProductTypesCfg( JSONProductTypes productTypesCfg ) {
		this.productTypesCfg = productTypesCfg;
	}

}
