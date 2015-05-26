package com.lumata.e4o.gui.administrationmanager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.administrationmanager.JSONSalesChannels;

public class SalesChannelsForm extends AdministrationForm {

	private JSONSalesChannels salesChannelsCfg;
	private final String TABLE_SALES_CHANNELS_TITLE_= "List of sales channels";
	
	
    public enum ElementErrorActionType {

        RETURN_ERROR,
        ABORT,
        ADD_TIMESTAMP_TO_FIELD;

    }; 
	
	public SalesChannelsForm( SeleniumWebDriver selenium, JSONSalesChannels salesChannelsCfg, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
		this.salesChannelsCfg = salesChannelsCfg;
		
	}
	public SalesChannelsForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}
	
	public SalesChannelsForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-catalog-channel" );
		super.waitForPageLoad();
		
		return this;
		
	}	
	public SalesChannelsForm clickAddButton() throws FormException
	{
		this.clickXPath( "//table[contains(@class,'page-ChannelPageView')]//button[@name='btn-add' and @title='Add channel']" );
		return this;
	}
	public SalesChannelsForm addSalesChannels() throws FormException, JSONException {
		
		JSONArray salesChannels = salesChannelsCfg.getList();
		
		for( int salesChannelsIndex = 0; salesChannelsIndex < salesChannels.length(); salesChannelsIndex++ ) {
			
			salesChannelsCfg.setSalesChannelById( salesChannelsIndex );
			
			if( salesChannelsCfg.getEnabled() ) {
				
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
	
	public SalesChannelsForm setSalesChannelName(String name) throws FormException {
		
		sendKeysById( "gwt-debug-TextBox-ChannelDialogBox-nameTextBox", name);
		
		return this;
		
	}
public String getSalesChannelName() throws FormException {
		
		return this.getValueById("gwt-debug-TextBox-ChannelDialogBox-nameTextBox");
		
	}
	
	public SalesChannelsForm activateSalesChannel( String salesChannel ) throws FormException {

		String tableListOfChannelXPath = "//table//div[text()='" + TABLE_SALES_CHANNELS_TITLE_ + "']//parent::td//parent::tr//parent::tbody";
		String rowSalesChannelXPath = tableListOfChannelXPath + "//div[text()='" + salesChannel + "']//parent::td//parent::tr";
		String btnActivateSalesChannelXPath = rowSalesChannelXPath + "//button[@name='btn-activate']";
		
		clickXPath( btnActivateSalesChannelXPath ).
		confirmDialog();
		
		return this;
		
	}

	public SalesChannelsForm deactivateSalesChannel( String salesChannel ) throws FormException {
		
		String tableListOfChannelXPath = "//table//div[text()='" + TABLE_SALES_CHANNELS_TITLE_ + "']//parent::td//parent::tr//parent::tbody";
		String rowSalesChannelXPath = tableListOfChannelXPath + "//div[text()='" + salesChannel + "']//parent::td//parent::tr";
		String btnDeactivateSalesChannelXPath = rowSalesChannelXPath + "//button[@name='btn-deactivate']";

		clickXPath( btnDeactivateSalesChannelXPath ).
		confirmDialog();
		
		return this;
		
	}
public SalesChannelsForm editSalesChannel( String salesChannel ) throws FormException {
		
		String tableListOfChannelXPath = "//table//div[text()='" + TABLE_SALES_CHANNELS_TITLE_ + "']//parent::td//parent::tr//parent::tbody";
		String rowSalesChannelXPath = tableListOfChannelXPath + "//div[text()='" + salesChannel + "']//parent::td//parent::tr";
		String btnEditSalesChannelXPath = rowSalesChannelXPath + "//button[@name='btn-edit']";

		clickXPath( btnEditSalesChannelXPath );
		
		return this;
		
	}

public SalesChannelsForm deleteSalesChannel( String salesChannel ) throws FormException {
	
	String tableListOfChannelXPath = "//table//div[text()='" + TABLE_SALES_CHANNELS_TITLE_ + "']//parent::td//parent::tr//parent::tbody";
	String rowSalesChannelXPath = tableListOfChannelXPath + "//div[text()='" + salesChannel + "']//parent::td//parent::tr";
	String btnEditSalesChannelXPath = rowSalesChannelXPath + "//button[@name='btn-delete']";

	clickXPath( btnEditSalesChannelXPath );
	confirmDialog();
	return this;
	
}
	public Boolean isSalesChannelActive( String salesChannel ) throws FormException {
		
		String tableListOfChannelXPath = "//table//div[text()='" + TABLE_SALES_CHANNELS_TITLE_ + "']//parent::td//parent::tr//parent::tbody";
		String rowSalesChannelXPath = tableListOfChannelXPath + "//div[text()='" + salesChannel + "']//parent::td//parent::tr";
		String btnActivateSalesChannelXPath = rowSalesChannelXPath + "//button[@name='btn-activate']";
		String btnDeActivate=rowSalesChannelXPath + "//button[@name='btn-deactivate']";
		WebDriverWait wait=new WebDriverWait(selenium.getWrappedDriver(), 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnDeActivate)));
		WebElement we = search( SeleniumUtils.SearchBy.XPATH, btnActivateSalesChannelXPath );
		
		if( we.getAttribute( "style" ).contains( "display: none;" ) ) { return true; }
				
		return false;
		
	}
public Boolean isSalesChannelDeactivated( String salesChannel ) throws FormException {
		
		String tableListOfChannelXPath = "//table//div[text()='" + TABLE_SALES_CHANNELS_TITLE_ + "']//parent::td//parent::tr//parent::tbody";
		String rowSalesChannelXPath = tableListOfChannelXPath + "//div[text()='" + salesChannel + "']//parent::td//parent::tr";
		String btnActivateSalesChannelXPath = rowSalesChannelXPath + "//button[@name='btn-deactivate']";
		String btnActivate=rowSalesChannelXPath + "//button[@name='btn-activate']";
		WebDriverWait wait=new WebDriverWait(selenium.getWrappedDriver(), 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnActivate)));
		WebElement we = search( SeleniumUtils.SearchBy.XPATH, btnActivateSalesChannelXPath );
		
		if( we.getAttribute( "style" ).contains( "display: none;" ) ) { return true; }
				
		return false;
		
	}
public List<WebElement> getSalesChannelList() throws FormException {
	String rootPath = "//table//div[text()='" + TABLE_SALES_CHANNELS_TITLE_ + "']//parent::td//parent::tr//parent::tbody";
	String subPath = "//tr[contains(@class, 'contentRow cycle')]//td[@class='column_description']";
	WebDriverWait wait=new WebDriverWait(selenium.getWrappedDriver(), 20);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(rootPath)));
	List<WebElement> salesChannelList = getListByXPath(rootPath, rootPath + subPath);
	return salesChannelList;
	
}
	
public Boolean isSalesChannelExisting( String salesChannel ) throws FormException {
	
	List<WebElement> salesChannelList = getSalesChannelList();
	
	for( WebElement salesChannelWe : salesChannelList ) {
		
		if( salesChannelWe.getText().trim().equals(salesChannel)) {
			
			return true;
			
		}
		
	}
	
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
