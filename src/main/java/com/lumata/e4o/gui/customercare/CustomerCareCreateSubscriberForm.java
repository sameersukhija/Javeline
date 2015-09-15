package com.lumata.e4o.gui.customercare;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

public class CustomerCareCreateSubscriberForm extends Form {

	public CustomerCareCreateSubscriberForm(SeleniumWebDriver selenium,
			long timeout, long interval) {
		super(selenium, timeout, interval);

	}

	
	// Enter Language
	public CustomerCareCreateSubscriberForm enterLanguage(String strLanguage)
			throws FormException {
		super.sendKeysById("gwt-debug-InputCCCreateLanguage", strLanguage);
		return this;
	}

	// Click createAdd button for Customer Care
	public CustomerCareCreateSubscriberForm clickCustomerCareCreateAdd()
			throws FormException {
		super.clickId("gwt-debug-BtnCCCreateAdd");
		return this;
	}

	// Select RatePlan
	public CustomerCareCreateSubscriberForm selectRatePlan(String text)
			throws FormException {
		super.selectByIdAndVisibleText("gwt-debug-ListCCCreateRatePlan", text);
		return this;
	}

	// Select Status
	public CustomerCareCreateSubscriberForm selectStatus(String text)
			throws FormException {
		super.selectByIdAndVisibleText("gwt-debug-ListCCCreateStatus", text);
		return this;
	}

	// Select InTag
	public CustomerCareCreateSubscriberForm selectInTag(String text)
			throws FormException {
		super.selectByIdAndVisibleText("gwt-debug-ListCCCreateInTag", text);
		return this;
	}

	// Enter IMSI
	public CustomerCareCreateSubscriberForm enterImsi(String strImsi)
			throws FormException {

		super.sendKeysById("gwt-debug-InputCCCreateIMSI", strImsi);
		return this;
	}

	// Enter IMEI
	public CustomerCareCreateSubscriberForm enterImei(String strImei)
			throws FormException {
		super.sendKeysById("gwt-debug-InputCCCreateIMEI", strImei);
		return this;
	}

	// Click Cancel Button
	public CustomerCareCreateSubscriberForm clickCustomerCareCancel()
			throws FormException {
		super.clickId("gwt-debug-BtnCCCreateCancel");
		return this;
	}

	public CustomerCareCreateSubscriberForm editRatePlan(String strRatePlan)
			throws FormException {
		super.clickId("gwt-debug-BtnCCInfoEdit");
		selectRatePlan(strRatePlan);
		super.clickName("btn-save");
		handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
		return this;
	}

}
