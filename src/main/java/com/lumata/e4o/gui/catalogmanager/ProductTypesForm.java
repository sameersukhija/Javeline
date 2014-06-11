package com.lumata.e4o.gui.catalogmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
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
				// error check
				
				/**
				 * 
				 * 
				 * Start error handling refactoring
				 * 
				 * 
				 * 
				 */
				
				if ( containsErrorElement() )
					logger.error("Without click \"save\" panel is in error!");
				
				Boolean completed = Boolean.FALSE;
				
				do {
					
					/**
					 * Core save procedure
					 */
					
					clickXPath("//div[text()='Adding a new characteritic type']//ancestor::div[2]//button[@title='OK']");

					/**
					 * Core save procedure - END
					 */
					
					Boolean confirmed = Boolean.FALSE;

					confirmed = handleJavascriptAlertAcceptDismiss(true);

					/**
					 * End - Core save procedure
					 */
					
					// in case no confirmation was executed, check element in error
					if ( !confirmed && containsErrorElement() ) {

						logger.warn("After click \"Save\" panel is in error!");
						
						ElementErrorConditionType condition = null;
						
						searchByXPath("//div[@class='gwt-DialogBox']");
						
						// error condition
						//div[text()='Bonus name already used']
						List<WebElement> element = SeleniumUtils.findListForComponentDisplayed(	selenium, 
																								SearchBy.XPATH, 
																								lastWebElement, 
																								"//div[contains(text(),'Cannot add characteristic, name ')]"
																							);
						
						if ( element.size() != 0 )
							condition = ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
						else
							condition = ElementErrorConditionType.GENERAL_ERROR;
						
						ElementErrorActionType action = chElem.getErrorActions().getAction(condition);
						
						// abort insertion
						if ( action.equals(ElementErrorActionType.ABORT_CANCEL) ) {
							clickXPath( "//div[text()='Adding a new characteritic type']//ancestor::div[2]//button[@title='Cancel']" );
							
							completed = Boolean.TRUE;
						}
						// stop execution and return error
						else if ( action.equals(ElementErrorActionType.RETURN_ERROR) )
							throw new FormException(getClass().getSimpleName() + " cannot configure \""+chElem.getName()+"\" characteristic!");
						// add timestamp to name
						else if ( action.equals(ElementErrorActionType.ADD_TIMESTAMP_TO_FIELD) ) {
							
							// one click su input
							clickXPath( "//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']");
							
							// delete current text
							lastWebElement.clear();
							
							String actualName = chElem.getName();
							chElem.setObjectFromPath("name", actualName + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
							
							// send old text + timestamp
							typeById( "//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']", productTypesCfg.getName() );
							
							completed = Boolean.FALSE;
						}
					}
					else // all ok!
						completed = Boolean.TRUE;			
				}
				while ( !completed );

				/**
				 * 
				 * 
				 * End error handling refactoring
				 * 
				 * 
				 * 
				 */
				
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
		
		/**
		 * 
		 * 
		 * Start error handling refactoring
		 * 
		 * 
		 * 
		 */
		
		if ( containsErrorElement() )
			logger.error("Without click \"save\" panel is in error!");
		
		Boolean completed = Boolean.FALSE;
		
		do {
			
			/**
			 * Core save procedure
			 */
			
			super.clickXPath( "//div[text()='Create product type']//ancestor::div[2]//button[@title='Save']" );

			/**
			 * Core save procedure - END
			 */
			
			Boolean confirmed = Boolean.FALSE;

			confirmed = handleJavascriptAlertAcceptDismiss(true);

			/**
			 * End - Core save procedure
			 */
			
			// in case no confirmation was executed, check element in error
			if ( !confirmed && containsErrorElement() ) {

				JsonCurrentElement current = productTypesCfg.getCurrentElement();
				
				logger.warn("After click \"Save\" panel is in error!");
				
				ElementErrorConditionType condition = null;
				
				searchByXPath("//div[@class='gwt-DialogBox']");
				
				// error condition
				//div[text()='Bonus name already used']
				List<WebElement> element = SeleniumUtils.findListForComponentDisplayed(	selenium, 
																						SearchBy.XPATH, 
																						lastWebElement, 
																						"//div[text()='Cannot add product type, name is already used.']"
																					);
				
				if ( element.size() != 0 )
					condition = ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
				else
					condition = ElementErrorConditionType.GENERAL_ERROR;
				
				ElementErrorActionType action = current.getErrorActions().getAction(condition);
				
				// abort insertion
				if ( action.equals(ElementErrorActionType.ABORT_CANCEL) ) {
					clickXPath( "//div[text()='Create product type']//ancestor::div[2]//button[@title='Cancel']" );
					
					completed = Boolean.TRUE;
				}
				// stop execution and return error
				else if ( action.equals(ElementErrorActionType.RETURN_ERROR) )
					throw new FormException(getClass().getSimpleName() + " cannot configure \""+current.getStringFromPath("name")+"\" product type!");
				// add timestamp to name
				else if ( action.equals(ElementErrorActionType.ADD_TIMESTAMP_TO_FIELD) ) {
					
					// one click su input
					clickXPath( "//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']");
					
					// delete current text
					lastWebElement.clear();
					
					String actualName = current.getStringFromPath("name");
					current.setObjectFromPath("name", actualName + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
					
					// send old text + timestamp
					typeByXPath( "//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']", productTypesCfg.getName() );
					
					completed = Boolean.FALSE;
				}
			}
			else // all ok!
				completed = Boolean.TRUE;			
		}
		while ( !completed );
		
		/*
		 * 
		 * 
		 * End error handling refactoring
		 * 
		 * 
		 * 
		 */
		
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
		
			List<WebElement> ptLabels = getListByXPath(rootPath, rootPath + subPath);
			
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
}
