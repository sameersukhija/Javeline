package com.lumata.e4o.gui.catalogmanager;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.catalogmanager.JSONSuppliers;

public class SuppliersForm extends CatalogueManagerForm {

	private static final Logger logger = LoggerFactory.getLogger(SuppliersForm.class);

	private JSONSuppliers supplierCfg;
	
    public enum ElementErrorActionType {

        RETURN_ERROR,
        ABORT,
        ADD_TIMESTAMP_TO_FIELD;

    }; 
    
	public SuppliersForm( SeleniumWebDriver selenium, JSONSuppliers supplierCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.supplierCfg = supplierCfg;
		
	}	
	
	public SuppliersForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-catalog-supplier" );
		
		return this;
		
	}
	
	public SuppliersForm addSuppliers() throws FormException, JSONException {
		
		JSONArray suppliers = supplierCfg.getList();
		
		for( int supplierIndex = 0; supplierIndex < suppliers.length(); supplierIndex++ ) {
			
			supplierCfg.setSupplierById( supplierIndex );
			
			if( supplierCfg.getEnabled() ) {
				
				clickXPath( "//button[@name='btn-add' and @title='Add supplier']" ).
				configureSupplier();
				saveSupplier().
				manageErrorAction( supplierCfg.getErrorActions().getString( "ELEMENT_ALREADY_EXISTS" ) );
				
			}
					
		}
		
		return this;
		
	}
	
	public SuppliersForm configureSupplier() throws FormException, JSONException {
		
		sendKeysById( "gwt-debug-TextBox-SupplierPageView-nameTextBox", supplierCfg.getName() ).
		sendKeysById( "gwt-debug-TextBox-SupplierPageView-emailTextBox", supplierCfg.getEmail() ).
		sendKeysById( "gwt-debug-TextBox-SupplierPageView-phoneTextBox", supplierCfg.getPhone() ).
		sendKeysById( "gwt-debug-TextBox-SupplierPageView-websiteTextBox", supplierCfg.getWebsite() );		
		
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
					sendKeysByName( "name", supplierCfg.getName() ).
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
	
	@Override
	public SuppliersForm execJavascript( String command ) {
		
		super.execJavascript( command );
		
		return this;
		
	}
	
	@Override
	public SuppliersForm clickName( String name ) throws FormException {
		
		super.clickName( name );
		
		return this;
		
	}
	
	@Override
	public SuppliersForm clickXPath( String xpath ) throws FormException {
		
		super.clickXPath( xpath );
		
		return this;
		
	}
	
	@Override
	public SuppliersForm clickLink( String link ) throws FormException {
		
		super.clickLink( link );
		
		return this;
		
	}
	
	@Override
	public SuppliersForm sendKeysByName( String name, String text ) throws FormException {
		
		super.sendKeysByName( name, text );
		
		return this;
	
	}
	
	@Override
	public SuppliersForm sendKeysByXPath( String xpath, String text ) throws FormException {
		
		super.sendKeysByXPath( xpath, text );
		
		return this;
	
	}
	
	@Override
	public SuppliersForm sendKeysByLink( String link, String text ) throws FormException {
		
		super.sendKeysByLink( link, text );
		
		return this;
	
	}
	
	@Override
	public SuppliersForm selectByName( String name, String label ) throws FormException {
		
		super.selectByName( name, label );
		
		return this;
		
	}
	
	@Override
	public SuppliersForm clearByName( String xpath ) throws FormException {
		
		super.clearByName( xpath );
		
		return this;
		
	}
	
	@Override
	public SuppliersForm typeByName( String name, String text ) throws FormException {
		
		super.typeByName( name, text );
		
		return this;
	
	}
	
	@Override
	public SuppliersForm multiselectByXPathAndVisibleText( String xpath, JSONArray list ) throws FormException {
		
		super.multiselectByVisibleText( SearchBy.XPATH, xpath, list );	
		
		return this;
		
	}
	
	@Override
	public SuppliersForm selectByNameAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByNameAndVisibleText( name, text );	
		
		return this;
		
	}
	
}
