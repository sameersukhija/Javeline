package com.lumata.e4o.gui.catalogmanager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.catalogmanager.JSONSuppliers;

public class SuppliersForm extends CatalogueManagerForm {

	private JSONSuppliers supplierCfg;
	
    public enum ElementErrorActionType {

        RETURN_ERROR,
        ABORT,
        ADD_TIMESTAMP_TO_FIELD;

    }; 

    public SuppliersForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		
	}	
    
	public SuppliersForm( SeleniumWebDriver selenium, JSONSuppliers supplierCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		setProductTypesCfg( supplierCfg );
		
	}	
	
	public SuppliersForm openForm() throws FormException {
		
		super.openForm().clickId( "gwt-debug-actrule-catalog-supplier" );
		
		return this;
		
	}
	
	public SuppliersForm addButton() throws FormException {
		super.clickXPath( "//button[@name='btn-add' and @title='Add supplier']" );
		return this;
	}
	
	public SuppliersForm setSupplierName(String name) throws FormException {
		this.sendKeysBycssSelector("input#gwt-debug-TextBox-SupplierPageView-nameTextBox", name);
		return this;
	}
	
	public SuppliersForm setSupplierEmail(String email) throws FormException {
		this.sendKeysById( "gwt-debug-TextBox-SupplierPageView-emailTextBox", email);
		return this;
	}
	
	public SuppliersForm setSupplierPhone(String phone) throws FormException {
		this.sendKeysById( "gwt-debug-TextBox-SupplierPageView-phoneTextBox", phone);
		return this;
	}
	
	public SuppliersForm setSupplierWebSite(String website) throws FormException {
		this.sendKeysById( "gwt-debug-TextBox-SupplierPageView-websiteTextBox", website);
		return this;
	}
	
	public String getSupplierName() throws FormException {
		return this.getValueById("gwt-debug-TextBox-SupplierPageView-nameTextBox");
	}
	
	public String getSupplierEmail() throws FormException {
		return this.getValueById("gwt-debug-TextBox-SupplierPageView-emailTextBox");
	}
	
	public String getSupplierPhone(String phone) throws FormException {
		return this.getValueById("gwt-debug-TextBox-SupplierPageView-phoneTextBox");
	}
	
	public String getSupplierWebSite() throws FormException {
		return this.getValueById("gwt-debug-TextBox-SupplierPageView-websiteTextBox");
	}
	
	/*Clicks on add button and enters the data in all the textboxes*/
	public SuppliersForm configureSupplier(String name, String email, String phone, String website) throws FormException, JSONException {
		addButton();
		setSupplierName(name);
		if (null!=email)
		{
			setSupplierEmail(email);
		}
		if (null!=phone)
		{
			setSupplierPhone(phone);
		}
		if (null!=website)
		{
			setSupplierWebSite(website);
		}
		
		return this;
		
	}
	
	public SuppliersForm manageErrorAction( String errorAction ) throws FormException {
		
		try {
		
			searchByXPath( "//div[@class='gwt-PopupPanel']//div[@class='gwt-Label' and contains(text(), 'Name already used')]", 2000, 50 );
		
		} catch( FormException fe ) {
			
			// no error to manage
			
		}
			
		if( status ) {
		
			switch( ElementErrorActionType.valueOf( errorAction ) ) {
			
				case RETURN_ERROR: {  
					
					throw new FormException( "Error in the form navigation" );
									
				}
				case ABORT: {  
										
					cancelSupplier();				
					
					break; 				
				}
				case ADD_TIMESTAMP_TO_FIELD: {  
					
					String name_with_timestamp = supplierCfg.getName() + "_" + String.valueOf( TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) );
					
					supplierCfg.setName( name_with_timestamp );					
					
					clearByName( "name" ).
					sendKeysByName( "name", supplierCfg.getName() );
					saveSupplier();					
					
					break; 				
				}
			
			}
		
		}
		
		status = true;
		
		return this;
		
	}
	
	public SuppliersForm saveSupplier() throws FormException {
		
		super.clickXPath( "//div[@class='gwt-DialogBox']//button[@name='btn-save']" );
		
		return this;
		
	}
	
	public SuppliersForm cancelSupplier() throws FormException {
		
		super.clickXPath( "//div[@class='gwt-DialogBox']//button[@name='btn-cancel']" );
		
		return this;
		
	}
	
	//Gets the list of all the existing internal and external suppiers
	public List<WebElement> getSupplierList() throws FormException {
		String rootPath = "//div[text()='Supplier list']//ancestor::table[@class='tableList']";
		String subPath = "//tr[contains(@class, 'contentRow cycle')]//td[@class='column_description']";

		List<WebElement> supplierList = getListByXPath(rootPath, rootPath + subPath);
		return supplierList;
		
	}
	
	//verifies if the supplier created exists in the list of suppliers
	public Boolean isSupplierInList( String supplierName ) throws FormException {
		
		List<WebElement> supplierList = getSupplierList();
				
		for( WebElement supplierEl : supplierList ) {
			
			if( supplierEl.getText().trim().equals(supplierName)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	} 
	
	public JSONSuppliers getProductTypesCfg() {
		return this.supplierCfg;
	}
	
	public void setProductTypesCfg( JSONSuppliers supplierCfg ) {
		this.supplierCfg = supplierCfg;
	}
	
}
