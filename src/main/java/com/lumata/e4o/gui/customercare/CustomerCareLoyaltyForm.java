package com.lumata.e4o.gui.customercare;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lumata.e4o.gui.catalogmanager.OfferOptimisationForm;
import com.lumata.e4o.gui.common.AngularFrame;
import com.lumata.e4o.gui.common.Form;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class CustomerCareLoyaltyForm  extends CustomerCareForm {

	private WebDriver webDriver;
	private WebDriver driver;

	public CustomerCareLoyaltyForm ( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	
	public CustomerCareLoyaltyForm openLoyaltyTab() throws FormException {
		
		clickId( "gwt-debug-BtnCCBarInfoLoyalty" );
		waitForPageLoad();
		return this;
		
	}
	
	
	public Boolean isRedeemStatusPresent(String strBadgeName) throws FormException {
		
		waitForPageLoad();
		
		List<WebElement> CustloyaltyList = getCustloyaltyList(strBadgeName);

		for( WebElement CustloyaltyListE1 : CustloyaltyList ) {

			if( CustloyaltyListE1.getText().trim().equals( strBadgeName) ) {
		
				return true;

			}	
		}

		return false;	
	}

	
	public CustomerCareLoyaltyForm openAngularFrame() throws FormException {
		
		AngularFrame.getInstance( selenium, timeout, interval ).open();
		
		return this;
		
	}
	
	public List<WebElement> getCustloyaltyList(String strBadgeName)  throws FormException {
		
		//clickXPath ("//table[@class='page-SubscriberLoyaltyPageView']//div[@class='angularIframe']/iframe[@class='GNAVB3LDA']");
		//selenium.getWrappedDriver().switchTo().defaultContent(); 
		
		//selenium.getWrappedDriver().switchTo().parentFrame();
		
		String rootPath ="//div[@class='e4ol-list__row ng-scope e4ol-list__row--odd']/div[@class='e4ol-list__cell e4ol-list__cell--text']"; 
				//"//div[text()='" + strBadgeName + "']//ancestor::div[contains(@class,'e4ol-list__row ng-scope e4ol-list__row')]";
		String subPath = "/span[text()='" + strBadgeName + "']";
		openAngularFrame();
		List<WebElement> CustloyaltyList = getListByXPath(rootPath, rootPath + subPath);
		System.out.println(CustloyaltyList);
		return CustloyaltyList;
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
	
}
