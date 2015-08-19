package com.lumata.e4o.gui.campaignmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

public class CampaignManagerForm extends Form {

	public CampaignManagerForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	protected Form open() throws FormException {
		WebDriverWait wait=new WebDriverWait(selenium.getWrappedDriver(), 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("gwt-debug-BarCaptionHomeCampaign")));
		return clickId( "gwt-debug-BarCaptionHomeCampaign" );
		
	}
	
}
