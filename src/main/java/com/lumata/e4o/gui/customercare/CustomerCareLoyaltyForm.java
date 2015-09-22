package com.lumata.e4o.gui.customercare;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.OfferOptimisationForm;
import com.lumata.e4o.gui.common.AngularFrame;

public class CustomerCareLoyaltyForm  extends CustomerCareForm {

	public CustomerCareLoyaltyForm ( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	public CustomerCareLoyaltyForm openLoyaltyTab() throws FormException {
		
		clickId( "gwt-debug-BtnCCBarInfoLoyalty" );
		openAngularFrame();
		
		return this;
		
	}
	public String getStatus(String strBadgeName) throws FormException{
			return this.getTextByXPath("//div[text()='" + strBadgeName + "']//ancestor::div[contains(@class,'e4ol-list__row ng-scope e4ol-list__row')]//div[4]");
	
	}
	
	public boolean isRedeemStatusPresent(String strBadgeName) throws FormException{
		String strStatus = getStatus(strBadgeName);
		if(strStatus.equals("Redeemed"))
		{
			return true;
		}
		return false;
	}
	
	
	@Override
	public CustomerCareLoyaltyForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
	
	}
	
	public CustomerCareLoyaltyForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
	
	}
	
	public CustomerCareLoyaltyForm selectByIdAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByIdAndVisibleText( name, text );	
		
		return this;
		
	}
protected CustomerCareLoyaltyForm openAngularFrame() throws FormException {
		
		AngularFrame.getInstance( selenium, timeout, interval ).openByXPath("//iframe[contains(@src,'angular/customerCare/index.html#/loyalty')]");
		
		return this;
		
	}
	
	public CustomerCareLoyaltyForm closeAngularFrame() throws FormException {
		
		AngularFrame.getInstance( selenium, timeout, interval ).close();
		
		return this;
		
	}
	
}
