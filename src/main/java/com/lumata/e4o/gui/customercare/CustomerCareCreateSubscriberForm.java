package com.lumata.e4o.gui.customercare;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class CustomerCareCreateSubscriberForm extends CustomerCareForm {

	public CustomerCareCreateSubscriberForm(SeleniumWebDriver selenium,
			long timeout, long interval) {
		super(selenium, timeout, interval);
		
	}
	
public CustomerCareCreateSubscriberForm open() throws FormException {
		
		clickId( "gwt-debug-BarCaptionHomeCustomerCare" );
		
		return this;
		
	}
	//Click Add button to add subscriber
	public CustomerCareCreateSubscriberForm clickCustomerCareAddButton() throws FormException
	{
		super.clickId("gwt-debug-BtnCCAdd");
		return this;
	}
	
	//Enter Subscriber id
	public CustomerCareCreateSubscriberForm inputSubscriberId(String msisdn) throws FormException 
	{
		super.sendKeysById("gwt-debug-InputCCSubscriberID",msisdn);
		return this;
	}
	
	//Enter Language
	public CustomerCareCreateSubscriberForm enterLanguage(String strLanguage) throws FormException
	{
		super.sendKeysById("gwt-debug-InputCCCreateLanguage", strLanguage);
		return this;
	}
	//Click createAdd button for Customer Care
	public CustomerCareCreateSubscriberForm clickCustomerCareCreateAdd() throws FormException
	{
		super.clickId("gwt-debug-BtnCCCreateAdd");
		return this;
	}
	
	
	
	
	

	

}
