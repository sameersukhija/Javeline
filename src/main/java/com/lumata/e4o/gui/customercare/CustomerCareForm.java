package com.lumata.e4o.gui.customercare;


import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

public class CustomerCareForm extends Form {

	public enum Tab {
		CustomerCare, CustomerCare_Profile, CustomerCare_Service, CustomerCare_Bonus, CustomerCare_Campaign, CustomerCare_Purchase, CustomerCare_Token, CustomerCare_Loyalty, CustomerCare_History, CustomerCare_Script
	}

	public CustomerCareForm(SeleniumWebDriver selenium, long timeout,
			long interval) {

		super(selenium, timeout, interval);

	}

	public CustomerCareForm open() throws FormException {

		clickId("gwt-debug-BarCaptionHomeCustomerCare");

		return this;

	}

	public CustomerCareForm setphoneNumberOption() throws FormException {
		clickId("gwt-debug-CheckCCPhoneNumber-input");
		return this;
	}

	public CustomerCareForm setIdentifierOption() throws FormException {
		clickId("gwt-debug-CheckCCIdentifier-input");
		return this;
	}

	public CustomerCareForm setPrefix(String prefix) throws FormException {
		super.selectByIdAndVisibleText("gwt-debug-ListCCPrefix", prefix);
		return this;
	}

	public String getPrefix() throws FormException {
		return super.getValueById("gwt-debug-ListCCPrefix");
	}

	public CustomerCareForm setSubscriberMsisdn(String msisdn)
			throws FormException {
		super.sendKeysById("gwt-debug-InputCCSubscriberID", msisdn);
		return this;
	}

	public String getSubscriberMsisdn() throws FormException {
		return super.getValueById("gwt-debug-InputCCSubscriberID");
	}

	public CustomerCareForm clickSearchButton() throws FormException {
		super.clickId("gwt-debug-BtnCCSearch");
		return this;
	}

	public CustomerCareForm clickClearButton() throws FormException{
		super.clickId( "gwt-debug-BtnCCClean" );
		return this;
	}
	public Boolean subscriberPhoneNumberExists(String prefix, String msisdn) throws FormException
	{
		Boolean status=false;
		searchMsisdnByPhoneNumber(prefix,msisdn);
		status=this.searchById("gwt-debug-BtnCCInfoEdit").isDisplayed();
		return status;
	}
	public CustomerCareForm searchMsisdnByPhoneNumber( String prefix, String msisdn ) throws FormException {
		
		//setphoneNumberOption();
		if(prefix !=null)
		{

			setPrefix(prefix);
		}
		WebDriverWait wait=new WebDriverWait(selenium.getWrappedDriver(), 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("gwt-debug-InputCCSubscriberID")));
		setSubscriberMsisdn(msisdn).clickSearchButton();

		return this;

	}

	public CustomerCareForm searchMsisdnByIdentifier(String msisdn)
			throws FormException {

		setIdentifierOption();

		setSubscriberMsisdn(msisdn).clickSearchButton();


		setSubscriberMsisdn(msisdn).
		clickSearchButton();

		return this;

	}

	@Override
	public CustomerCareForm clickId(String id) throws FormException {

		super.clickId(id);

		return this;

	}

	public CustomerCareForm sendKeysById(String id, String text)
			throws FormException {

		super.sendKeysById(id, text);

		return this;

	}

	public CustomerCareForm selectByIdAndVisibleText(String name, String text)
			throws FormException {

		super.selectByIdAndVisibleText(name, text);

		return this;

	}

	public CustomerCareForm scrollToElement(WebElement element)
			throws FormException {
		String command = "arguments[0].scrollIntoView();" + element;
		super.execJavascript(command);

		return this;

	}

}
