package com.lumata.e4o.gui.customercare;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class CustomerCareProfileForm extends CustomerCareForm {

	public CustomerCareProfileForm(SeleniumWebDriver selenium, long timeout,
			long interval) {

		super(selenium, timeout, interval);

	}

	protected CustomerCareProfileForm openProfileTab() throws FormException {

		clickId("gwt-debug-BtnCCBarInfoProfile");

		return this;

	}

	public CustomerCareProfileForm clickChannel() throws FormException {
		super.clickXPath("//table[contains(@class,'page-SubscriberInformationPageView')]//td[text()='Channels']");
		return this;
	}

	public CustomerCareProfileForm clickAddChannelButton() throws FormException {
		super.clickXPath("//table[contains(@class,'page-SubscriberInformationPageView')]//td[text()='Channels']//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//button[contains(@name,'btn-add')]");
		return this;
	}

	public CustomerCareProfileForm addChannel(String strChannelType,
			String strAddress, String strStatus) throws FormException {
		selectChannelType(strChannelType);
		enterAddress(strAddress);
		selectStatusType(strStatus);
		clickSaveButton();
		return this;

	}

	@Override
	public CustomerCareProfileForm clickId(String id) throws FormException {

		super.clickId(id);

		return this;

	}

	public CustomerCareProfileForm sendKeysById(String id, String text)
			throws FormException {

		super.sendKeysById(id, text);

		return this;

	}

	public CustomerCareProfileForm selectByIdAndVisibleText(String name,
			String text) throws FormException {

		super.selectByIdAndVisibleText(name, text);

		return this;

	}

	public CustomerCareProfileForm selectChannelType(String strText)
			throws FormException {

		super.selectByXPathAndVisibleText(
				"//div[contains(@class,'gwt-DialogBox')]//select[@class = 'gwt-ListBox']",
				strText);
		return this;
	}

	public CustomerCareProfileForm selectStatusType(String strText)
			throws FormException {

		super.selectByXPathAndVisibleText(
				"//div[contains(@class,'gwt-DialogBox')]//tr[3]//select[@class = 'gwt-ListBox']",
				strText);
		return this;
	}

	public CustomerCareProfileForm enterAddress(String strAddress)
			throws FormException {
		sendKeysByXPath(
				"//div[contains(@class,'gwt-DialogBox')]//input[@class='gwt-TextBox']",
				strAddress);
		return this;
	}

	public CustomerCareProfileForm clickSaveButton() throws FormException {
		super.clickName("btn-save");
		return this;
	}

}
