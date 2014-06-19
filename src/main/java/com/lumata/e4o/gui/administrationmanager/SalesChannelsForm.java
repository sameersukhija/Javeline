package com.lumata.e4o.gui.administrationmanager;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.administrationmanager.JSONSalesChannels;

public class SalesChannelsForm extends AdministrationForm {

	private JSONSalesChannels salesChannelsCfg;
	
    public enum ElementErrorActionType {

        RETURN_ERROR,
        ABORT,
        ADD_TIMESTAMP_TO_FIELD;

    }; 
	
	public SalesChannelsForm( SeleniumWebDriver selenium, JSONSalesChannels salesChannelsCfg, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
		this.salesChannelsCfg = salesChannelsCfg;
		
	}
	
	public SalesChannelsForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-catalog-channel" );
		
		return this;
		
	}	
	
	public SalesChannelsForm addSalesChannels() throws FormException, JSONException {
		
		JSONArray salesChannels = salesChannelsCfg.getList();
		
		for( int salesChannelsIndex = 0; salesChannelsIndex < salesChannels.length(); salesChannelsIndex++ ) {
			
			salesChannelsCfg.setSalesChannelById( salesChannelsIndex );
			
			if( salesChannelsCfg.getEnabled() ) {
				
				clickXPath( "//table[contains(@class,'page-ChannelPageView')]//button[@name='btn-add']" ).
				configureSalesChannel().
				saveSalesChannel().
				manageErrorAction( salesChannelsCfg.getErrorActions().getString( "ELEMENT_ALREADY_EXISTS" ) );
				
				Boolean isChannelActive = isSalesChannelActive( salesChannelsCfg.getName() ) ;
				
				if( !isChannelActive && salesChannelsCfg.getActive() ) {
					
					activateSalesChannel( salesChannelsCfg.getName() );
					
				}
				
				if( isChannelActive && !salesChannelsCfg.getActive() ) {
					
					deactivateSalesChannel( salesChannelsCfg.getName() );
					
				}
												
			}
					
		}
		
		return this;
		
	}
	
	public SalesChannelsForm configureSalesChannel() throws FormException {
		
		sendKeysById( "gwt-debug-TextBox-ChannelDialogBox-nameTextBox", salesChannelsCfg.getName() );
		
		return this;
		
	}
	
	public SalesChannelsForm activateSalesChannel( String salesChannel ) throws FormException {

		String tableListOfChannelXPath = "//table//div[text()='List of sales channels']//parent::td//parent::tr//parent::tbody";
		String rowSalesChannelXPath = tableListOfChannelXPath + "//div[text()='" + salesChannel + "']//parent::td//parent::tr";
		String btnActivateSalesChannelXPath = rowSalesChannelXPath + "//button[@name='btn-activate']";
		
		clickXPath( btnActivateSalesChannelXPath ).
		confirmDialog();
		
		return this;
		
	}

	public SalesChannelsForm deactivateSalesChannel( String salesChannel ) throws FormException {
		
		String tableListOfChannelXPath = "//table//div[text()='List of sales channels']//parent::td//parent::tr//parent::tbody";
		String rowSalesChannelXPath = tableListOfChannelXPath + "//div[text()='" + salesChannel + "']//parent::td//parent::tr";
		String btnDeactivateSalesChannelXPath = rowSalesChannelXPath + "//button[@name='btn-deactivate']";

		clickXPath( btnDeactivateSalesChannelXPath ).
		confirmDialog();
		
		return this;
		
	}

	public Boolean isSalesChannelActive( String salesChannel ) throws FormException {
		
		String tableListOfChannelXPath = "//table//div[text()='List of sales channels']//parent::td//parent::tr//parent::tbody";
		String rowSalesChannelXPath = tableListOfChannelXPath + "//div[text()='" + salesChannel + "']//parent::td//parent::tr";
		String btnActivateSalesChannelXPath = rowSalesChannelXPath + "//button[@name='btn-activate']";
		
		WebElement we = search( SeleniumUtils.SearchBy.XPATH, btnActivateSalesChannelXPath );
		
		if( we.getAttribute( "style" ).contains( "display: none;" ) ) { return true; }
				
		return false;
		
	}
	
	public SalesChannelsForm manageErrorAction( String errorAction ) throws FormException {
		
		try {
		
			searchByXPath( "//div[@class='gwt-PopupPanel']//div[contains(text(),'The name is already used')]", 2000, 50 );
		
		} catch( FormException fe ) {
			
			// no error to manage
			
		}
			
		if( status ) {
		
			switch( ElementErrorActionType.valueOf( errorAction ) ) {
			
				case RETURN_ERROR: {  
					
					throw new FormException( "Error in the form navigation" );
									
				}
				case ABORT: {  
										
					cancelSalesChannel();				
					
					break; 				
				}
				case ADD_TIMESTAMP_TO_FIELD: {  
					
					String name_with_timestamp = salesChannelsCfg.getName() + "_" + String.valueOf( TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) );
					
					salesChannelsCfg.setName( name_with_timestamp );					
					
					clearById( "gwt-debug-TextBox-ChannelDialogBox-nameTextBox" ).
					sendKeysById( "gwt-debug-TextBox-ChannelDialogBox-nameTextBox", salesChannelsCfg.getName() ).
					saveSalesChannel();					
					
					break; 				
				}
			
			}
		
		}
		
		status = true;
		
		return this;
		
	}
	
	public SalesChannelsForm saveSalesChannel() throws FormException {
		
		super.clickName( "btn-save" );
		
		return this;
		
	}
	
	public SalesChannelsForm cancelSalesChannel() throws FormException {
		
		super.clickName( "btn-cancel" );
		
		return this;
		
	}
	
	@Override
	public SalesChannelsForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
		
	}
	
	@Override
	public SalesChannelsForm clickXPath( String xpath ) throws FormException {
		
		super.clickXPath( xpath );
		
		return this;
		
	}
	
	@Override
	public SalesChannelsForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
	
	}
	
	@Override
	public SalesChannelsForm clearById( String id ) throws FormException {
		
		super.clearById( id );
		
		return this;
		
	}
	
	@Override
	public SalesChannelsForm confirmDialog() {
		
		super.confirmDialog();
		
		return this;
		
	}
	
}
