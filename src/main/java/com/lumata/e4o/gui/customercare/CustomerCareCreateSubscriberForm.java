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
	
	//Select RatePlan
	public CustomerCareCreateSubscriberForm selectRatePlan(String text) throws FormException
	{
		super.selectByIdAndVisibleText("gwt-debug-ListCCCreateRatePlan", text);
		return this;
	}
	//Select Status
	public CustomerCareCreateSubscriberForm selectStatus(String text) throws FormException
	{
		super.selectByIdAndVisibleText("gwt-debug-ListCCCreateStatus", text);
		return this;
	}
	
	//Select InTag
	public CustomerCareCreateSubscriberForm selectInTag(String text) throws FormException
	{
		super.selectByIdAndVisibleText("gwt-debug-ListCCCreateInTag", text);
		return this;
	}
	
	//Enter IMSI
	public CustomerCareCreateSubscriberForm enterImsi(String strImsi) throws FormException
	{
		
		super.sendKeysById("gwt-debug-InputCCCreateIMSI",strImsi);
		return this;
	}
	
	//Enter IMEI
	public CustomerCareCreateSubscriberForm enterImei(String strImei) throws FormException
	{
		super.sendKeysById("gwt-debug-InputCCCreateIMEI",strImei);
		return this;
	}
	
	//Click Cancel Button
	public CustomerCareCreateSubscriberForm clickCustomerCareCancel() throws FormException
	{
		super.clickId("gwt-debug-BtnCCCreateCancel");
		return this;
	}
	

	
	
	
	
	

	

}
