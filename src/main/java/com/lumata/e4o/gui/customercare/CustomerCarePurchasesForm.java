package com.lumata.e4o.gui.customercare;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class CustomerCarePurchasesForm  extends CustomerCareForm {

	public CustomerCarePurchasesForm ( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	public CustomerCarePurchasesForm openPurchasesTab() throws FormException {
		
		clickId( "gwt-debug-BtnCCBarInfoPurchases" );
		
		return this;
		
	}
	
	@Override
	public CustomerCarePurchasesForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
	
	}
	
	public CustomerCarePurchasesForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
	
	}
	
	public CustomerCarePurchasesForm selectByIdAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByIdAndVisibleText( name, text );	
		
		return this;
		
	}
	public CustomerCarePurchasesForm purchaseOffer(String offerName) throws FormException
	{
		String buyButton_xpath="//div[text()='"+ offerName +"']//ancestor::tr[1]//td[10]//button[@title='Buy']";
		this.clickXPath(buyButton_xpath);
		return this;
	}
	public CustomerCarePurchasesForm setPurchaseQuantity(String quan) throws FormException
	{
		this.sendKeysByXPath("//td[text()='Quantity']//ancestor::tr[1]//td[2]//input[@class='gwt-TextBox']",quan);
		return this;
	}
	public CustomerCarePurchasesForm getPurchaseQuantity() throws FormException
	{
		this.getValueByXPath("//td[text()='Quantity']//ancestor::tr[1]//td[2]//input[@class='gwt-TextBox']");
		return this;
	}
	public CustomerCarePurchasesForm setChannel(String ch) throws FormException{
		this.selectByXPathAndVisibleText("//td[text()='Channel']//ancestor::tr[1]//td[2]//select[@class='gwt-ListBox']", ch);
		return this;
	}
	public CustomerCarePurchasesForm getChannel() throws FormException{
		this.getValueByXPath("//td[text()='Channel']//ancestor::tr[1]//td[2]//select[@class='gwt-ListBox']");
		return this;
	}
	public CustomerCarePurchasesForm setPrice(String price) throws FormException{
		this.selectByXPathAndVisibleText("//td[text()='Prices']//ancestor::tr[1]//td[2]//select[@class='gwt-ListBox']", price);
		return this;
	}
	public CustomerCarePurchasesForm getPrice() throws FormException{
		this.getValueByXPath("//td[text()='Prices']//ancestor::tr[1]//td[2]//select[@class='gwt-ListBox']");
		return this;
	}
	public CustomerCarePurchasesForm clickOKButton() throws FormException{
		this.clickXPath("//div[text()='Edit your purchase']//ancestor::table[1]//tr[2]//td[2]//table[@class='buttonPanel']//td[2]//button[@title='OK']");
		
		return this;
	}
	public Boolean isPurchaseSuccessfull(String offer_name) throws FormException{
		
		String purchaseOffer_xpath="//div[text()='Subscriber Purchase List']//ancestor::table[1]//tr[contains(@class,'contentRow cycle')]//div[text()='"+offer_name+"']";
		List<WebElement> we=selenium.getWrappedDriver().findElements(By.xpath(purchaseOffer_xpath));
		if(we.size()!=0)
			return true;
		else
			return false;
		
		
	}
}
