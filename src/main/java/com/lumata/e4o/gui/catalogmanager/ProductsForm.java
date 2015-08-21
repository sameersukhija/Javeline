/**
 * 
 */
package com.lumata.e4o.gui.catalogmanager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.common.PlaceHolderDate;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.campaignmanager.CampaignsForm;
import com.lumata.e4o.gui.common.GWTCalendarForm;
import com.lumata.e4o.json.gui.catalogmanager.JSONProducts;
import com.lumata.e4o.json.gui.catalogmanager.JSONProducts.CharacteristicType;
import com.lumata.e4o.json.gui.catalogmanager.JSONProducts.JsonProdCharacteristicElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONProducts.JsonProductType;
import com.lumata.e4o.json.gui.catalogmanager.JSONProducts.JsonRelatedProducts;
import com.lumata.e4o.json.gui.catalogmanager.JSONProducts.JsonSpecificChar;

/**
 * @author parvinder.bhogra@lumatagroup.com
 *
 */
public class ProductsForm extends CatalogueManagerForm {
	
	// TO REVIEW
	private JSONProducts prdCfg;

	public enum ProductCharacteristicType {
		
		PSC("Product  Specific Characteristics"),
		PT("Product Types"),
		RP("Related Products");
			
		private String value;
		ProductCharacteristicType( String value ) {
			
			this.value = value;
			
		}
		public String value() {
			
			return this.value;
			
		}
			
	}
	
	// TO REVIEW
	public ProductsForm( SeleniumWebDriver selenium, JSONProducts prdCfg, long timeout, long interval ) {
			
		super(selenium, timeout, interval);

		this.prdCfg = prdCfg;
		
	}

	public ProductsForm( SeleniumWebDriver selenium,long timeout, long interval ) {
		
		super(selenium, timeout, interval);
	
	}
	
	//Navigate to Products tab on catalogue tab
	public ProductsForm openForm() throws FormException {
		
		super.openForm().clickId( "gwt-debug-actrule-catalog-products" );
		
		return this;
	}
	
	//Main Add button to open Product creation dialog
	public ProductsForm addProductButton() throws FormException {
		super.clickXPath("//button[@name='btn-add' and @title='Add Product']");
		return this;
	}
	
	public ProductsForm saveProductButton() throws FormException {
		super.clickXPath("//button[@name='btn-save' and @title='Save']");
		return this;
	}
	
	//Set supplier for external Product while configuring product definition
	public ProductsForm setSupplier(String supplierName) throws FormException {
		super.selectByIdAndVisibleText("gwt-debug-ListBox-VPProductEdit-LBDistribution", supplierName);
		return this;
	}
	
	//get supplier
	public String getSupplier() throws FormException {
		return super.getValueById("gwt-debug-ListBox-VPProductEdit-LBDistribution");
	}
	public ProductsForm setInternalProductName(String Name) throws FormException {
		super.selectByIdAndVisibleText("gwt-debug-ListBox-VPProductEdit-distributionBonusesLB", Name);
		return this;
	}
	public String getInternalProductName() throws FormException {
		return super.getValueById("gwt-debug-ListBox-VPProductEdit-distributionBonusesLB");
	}
	//Set Product Name while configuring product definition
	public ProductsForm setProductName(String productName) throws FormException {
		super.sendKeysById("gwt-debug-TextBox-VPProductEdit-productNameTB", productName);
		return this;
	}
	
	//get Product Name
	public String getProductName() throws FormException {
		return super.getValueById("gwt-debug-TextBox-VPProductEdit-productNameTB");
	}
	
	//set Product description while configuring product definition
	public ProductsForm setProductDescription(String description) throws FormException {
		super.sendKeysById("gwt-debug-TextBox-VPProductEdit-productDescTB", description);
		return this;
	}
	
	//get the Product Description
	public String getProductDescription() throws FormException {
		return super.getValueById("gwt-debug-TextBox-VPProductEdit-productDescTB");
	}
	
	//set the Product image url while configuring product definition
	public ProductsForm setProductImageUrl(String image_url) throws FormException {
		super.sendKeysById("gwt-debug-TextBox-VPProductEdit-productUrlImageTB", image_url);
		return this;
	}
	
	//get Product Image URL
	public String getProductImageUrl() throws FormException {
		return super.getValueById("gwt-debug-TextBox-VPProductEdit-productUrlImageTB");
	}
	
	//set Terms and Conditions while configuring product definition
	public ProductsForm setTermsAndCondition(String termsAndCondition) throws FormException {
		
		super.clickXPath("//td[contains(text(),'Terms and conditions')]//ancestor::tr[1]//button[@name='btn-edit']");
	
		super.sendKeysByXPath( "//textarea", termsAndCondition);
	
		super.clickXPath("//textarea//ancestor::div[1]//button[@title='Save']");
		
		return this;
	
	}
	
	//opens the Product Characteristics tab 
	public ProductsForm openCharacteristicsTab() throws FormException {
			
			super.clickId( "gwt-debug-Anchor-actrule-catalog-product-steps-characteristics" );
			
			return this;
			
	}
	
	//Add button to add Characteristics to the product
	public ProductsForm addCharacteristicButton() throws FormException {
		super.clickXPath("//div[text()='Product Characteristics']//ancestor::tr[6]//button[@name='btn-add']");
		return this;
	}
	
	//select the characteristic type from the drop down. The type can be Product Type, Specific characters and related products
	public ProductsForm setCharacteristicType(String type) throws FormException {
		super.selectByIdAndVisibleText("gwt-debug-ListBox-CharacteristicsEditionPopUp-lbType", type);
		return this;
	}
	
	//get the characteristic type selected
	public String getCharacteristicType() throws FormException {
		return ProductCharacteristicType.values()[Integer.valueOf(super.getValueById("gwt-debug-ListBox-CharacteristicsEditionPopUp-lbType"))].name();
	}
	
	//OK button after adding the characteristic
	public ProductsForm characteristicOKButton() throws FormException {
		super.clickBycssSelector("table.buttonPanel>tbody>tr>td>button[name=\"btn-ok\"]");
		return this;
	}
	
	//add a product from an external supplier
//	public ProductsForm addExternalProduct(String supplierName, String productName, String description, String termsAndCondition,String ImageUrl, List<JSONProducts.JsonProdCharacteristicElement> chars,String cost,String price,String stock,String startDate,String endDate) throws FormException {
//		configureDefinition(supplierName,productName,description,termsAndCondition,ImageUrl);
//		configureProductTypeCharacteristic(ProductType);
//		configureCostPrice(cost, price);
//		configureAvailability(stock,startDate,endDate);
//		saveProductButton();
//		return this;
//	}
	
	public ProductsForm configureExternalDefinition(String supplierName, String productName, String description, String termsAndCondition,String imageUrl) throws FormException {
		
		addProductButton();
		if(null != supplierName)
			setSupplier(supplierName);
		if(null != productName)
			setProductName(productName);
		if(null != description) {
			setProductDescription(description);
		}
		
		if (null != imageUrl) {
			setProductImageUrl(imageUrl);
		}
		
		if (null != termsAndCondition) {
			setTermsAndCondition(termsAndCondition);
		}
		
		return this;
	
	}
public ProductsForm configureInternalDefinition(String supplierName, String productName,String termsAndCondition,String imageUrl) throws FormException {
		
		addProductButton();
		if(null != supplierName)
			setSupplier(supplierName);
		if(null != productName)
			setInternalProductName(productName);
		if (null != imageUrl) {
			setProductImageUrl(imageUrl);
		}
		
		if (null != termsAndCondition) {
			setTermsAndCondition(termsAndCondition);
		}
		
		return this;
	
	}
	public ProductsForm configureProductCharacteristic(JsonProdCharacteristicElement chel) throws FormException,JSONSException {
		
		if(chel.getType().equals(CharacteristicType.ProductType))
		{
			JsonProductType prodTypes = chel.getProductTypes();
			List<String> values=prodTypes.getValue();
			for(int i=0;i<values.size();i++) {
				addCharacteristicButton();
				setCharacteristicType("Product Types");
				selectByXPathAndVisibleText("//td[text()='Product types']//ancestor::tr[1]//select[@class='gwt-ListBox']", values.get(i));
				characteristicOKButton();
			}
		}
		if(chel.getType().equals(CharacteristicType.RelatedProducts))
		{
			JsonRelatedProducts relProd = chel.getRelatedProducts();
			List<String> values=relProd.getValue();
			for(int i=0;i<values.size();i++) {
				addCharacteristicButton();
				setCharacteristicType("Related Products");
				selectByXPathAndVisibleText("//td[text()='Products']//ancestor::tr[1]//select[@class='gwt-ListBox']", values.get(i));
				characteristicOKButton();
			}
		}
		if(chel.getType().equals(CharacteristicType.ProductSpecific))
		{
			JsonSpecificChar specific = chel.getProductSpecific();
			addCharacteristicButton();
			setCharacteristicType("Product Specific Characteristics");
			sendKeysByXPath("//td[text()='Name']//ancestor::tr[1]//input[@class='gwt-TextBox']", specific.getName());
			sendKeysByXPath("//td[text()='Value']//ancestor::tr[1]//input[@class='gwt-TextBox']", specific.getValue());
			
			characteristicOKButton();
		}
		return this;
	}
	
	public ProductsForm openCostAndPriceTab() throws FormException {
		super.clickId("gwt-debug-Anchor-actrule-catalog-product-steps-cost");
		return this;
	}
	
	public ProductsForm openAvailabilityTab() throws FormException {
		super.clickId("gwt-debug-Anchor-actrule-catalog-product-steps-stockValidity");
		return this;
	}
	
	public ProductsForm setUnitaryCost(String cost) throws FormException {
		super.sendKeysById("gwt-debug-TextBox-ProductPriceFlexTable-tbUnitaryCost", cost);
		return this;
	}
	
	public String getUnitaryCost() throws FormException {
		return super.getValueById("gwt-debug-TextBox-ProductPriceFlexTable-tbUnitaryCost");
	}
	
	public ProductsForm setListPrice(String price) throws FormException {
		super.sendKeysById("gwt-debug-TextBox-ProductPriceFlexTable-tbListPrice", price);
		return this;
	}
	
	public String getListPrice() throws FormException {
		return super.getValueById("gwt-debug-TextBox-ProductPriceFlexTable-tbListPrice");
	}
	
	public ProductsForm configureCostPrice(String cost, String price) throws FormException {
		openCostAndPriceTab();
		setUnitaryCost(cost);
		setListPrice(price);
		return this;
	}
	
	public ProductsForm configureAvailability(String stock,String startDate, String endDate) throws FormException {
		
		openAvailabilityTab();
		
		setAvailabilityStock( stock );
	
		if ( startDate != null) {
			
			setProductAvailabilityStartDate( startDate );

		}
			
		if ( endDate != null) {
		
			setProductAvailabilityEndDate( endDate );
				
		}
				
		return this;
		
	}
		
	// TO ADD the corresponding get method
	public ProductsForm setAvailabilityStock( String stock ) throws FormException {
		
		sendKeysById("gwt-debug-TextBox-VPProductEdit-maxUnitTB", stock );
		
		return this;
		
	}
	
	// TO ADD the corresponding get method
	public ProductsForm setAvailabilityStock( Integer stock ) throws FormException {
		
		this.setAvailabilityStock( String.valueOf( stock ) );
		
		return this;
		
	}
	public ProductsForm editProductByName(String name) throws FormException{
		String editButtonXpath= "//table[contains(@class,'page-ProductPageView')]//tr[3]//table[contains(@class,'tableList')]//div[text()='"+ name +"']//ancestor::tr[1]//td[6]//button[@name='btn-edit']";
		this.clickXPath(editButtonXpath);
		return this;
	}
	public ProductsForm deleteProductByName(String name) throws FormException{
		String editButtonXpath= "//table[contains(@class,'page-ProductPageView')]//tr[3]//table[contains(@class,'tableList')]//div[text()='"+ name +"']//ancestor::tr[1]//td[6]//button[@name='btn-delete']";
		this.clickXPath(editButtonXpath);
		return this;
	}
	
	public Boolean isProductEditable(String name) throws FormException{
		String editButtonXpath= "//table[contains(@class,'page-ProductPageView')]//tr[3]//table[contains(@class,'tableList')]//div[text()='"+ name +"']//ancestor::tr[1]//td[6]//button[@name='btn-edit']";
		
		return selenium.getWrappedDriver().findElement(By.xpath(editButtonXpath)).isEnabled();
	}
	
	public Boolean isProductDeletable(String name) throws FormException{
		String editButtonXpath= "//table[contains(@class,'page-ProductPageView')]//tr[3]//table[contains(@class,'tableList')]//div[text()='"+ name +"']//ancestor::tr[1]//td[6]//button[@name='btn-delete']";
		
		return selenium.getWrappedDriver().findElement(By.xpath(editButtonXpath)).isEnabled();
	}
	// TO ADD the corresponding get method
	public ProductsForm setProductAvailabilityStartDate( String provisioningStartDate ) throws FormException {
		
		String productAvailabilityStartDateXPath = ".//*[@id='gwt-debug-DatePicker-VPProductEdit-startDateDB']";
		
			
			//configureGWTCalendarByXPath( productAvailabilityStartDateXPath, resolveDateField(Format.getMysqlDate( provisioningStartDate )) );
			configureGWTCalendarByXPath( productAvailabilityStartDateXPath, resolveDateField(provisioningStartDate ));
//		} catch (ParseException e) {
//			
//			throw new FormException( e.getMessage(), e );
//			
//		}
				
		return this;
		
	}
	public ProductsForm configureGWTCalendarByXPath( String xpath, Calendar date ) throws FormException, JSONException {
		
		GWTCalendarForm.
			create( selenium, timeout, interval ).
			openByXPath( xpath ).
			setDate( date );
		
		return this;
		
	}
	
	// TO ADD the corresponding get method
//	public ProductsForm setProductAvailabilityStartDate( String provisioningStartDate ) throws FormException {
//		
//		setProductAvailabilityStartDate( super.getDate( provisioningStartDate ) );
//		
//		return this;
//		
//	}
//	
	// TO ADD the corresponding get method
	public ProductsForm setProductAvailabilityEndDate( String provisioningEndDate ) throws FormException {
		
		String productAvailabilityEndDateXPath = ".//*[@id='gwt-debug-DatePicker-VPProductEdit-endDateDB']";
		
		//try {
			
			configureGWTCalendarByXPath( productAvailabilityEndDateXPath, resolveDateField(provisioningEndDate ) );
			lastWebElement.sendKeys( Keys.RETURN );
		
		//} catch (ParseException e) {
			
	//		throw new FormException( e.getMessage(), e );
			
		//}
				
		return this;
		
	}
	
	// TO ADD the corresponding get method
//	public ProductsForm setProductAvailabilityEndDate( String provisioningEndDate ) throws FormException {
//		
//		setProductAvailabilityEndDate( super.getDate( provisioningEndDate ) );
//		
//		return this;
//		
//	}
	
	public List<WebElement> getProductList() throws FormException {
		
		String rootPath = "//table[contains(@class,'page-ProductPageView')]//tr[3]//table[contains(@class,'tableList')]";
	
		String subPath = "//tr[contains(@class, 'contentRow cycle')]//td[@class='column_description']";
	
		List<WebElement> productList = getListByXPath(rootPath, rootPath + subPath);
		
		return productList;
		
	}
	public ProductsForm clickRefreshButton() throws FormException
	{	WebDriverWait wait = new WebDriverWait(selenium.getWrappedDriver(), 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[contains(@class,'page-ProductPageView')]//tr[1]//button[@name='btn-refresh' and @title='Refresh']")));
		super.clickXPath("//table[contains(@class,'page-ProductPageView')]//tr[1]//button[@name='btn-refresh' and @title='Refresh']");
		return this;
	}
	
	public Boolean isProductInList( String productName ) throws FormException {
		
		List<WebElement> productList = getProductList();
				
		for( WebElement productEl : productList ) {
			
			if( productEl.getText().trim().equals(productName)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	} 
private Calendar resolveDateField(String StringDateField) throws FormException {
		
		Calendar date = Calendar.getInstance();
		if( PlaceHolderDate.getInstance( StringDateField ).isPlaceHolderDate() )
			date = PlaceHolderDate.getInstance( StringDateField ).parse();						
		else 		
			try {
				date.setTime( new SimpleDateFormat("yyyy-MM-dd").parse( StringDateField ) );
			} catch (ParseException e) {
				throw new FormException("Error during SingleExecutionStart parsing " + StringDateField);
			}
				
		return date;
	}
	// TO DO - adding get and set json product configuration
	
}
