package com.lumata.e4o.gui.customercare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.OfferOptimisationForm;
import com.lumata.e4o.gui.common.AngularFrame;

public class CustomerCareTokensForm  extends CustomerCareForm {

	public CustomerCareTokensForm ( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	public CustomerCareTokensForm openTokenTab() throws FormException {
		
		super.open().clickId( "gwt-debug-BtnCCBarInfoTokens" );
		super.waitForPageLoad();
		switchToFrameByClassName("GNAVB3LDA");
		
		return this;
		
	}
	
	@Override
	public CustomerCareTokensForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
	
	}
	
	public CustomerCareTokensForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
	
	}
	protected CustomerCareTokensForm openAngularFrame() throws FormException {
		
		AngularFrame.getInstance( selenium, timeout, interval ).open();
		
		return this;
		
	}
public CustomerCareTokensForm close() throws FormException {
		
		closeAngularFrame();
			
		return this;
		
	}
	
	protected CustomerCareTokensForm closeAngularFrame() throws FormException {
		
		AngularFrame.getInstance( selenium, timeout, interval ).close();
		
		return this;
	}
	public CustomerCareTokensForm selectByIdAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByIdAndVisibleText( name, text );	
		
		return this;
		
	}
	public CustomerCareTokensForm loadOffersForCampaignName(String campaignName) throws FormException{
		//WebDriverWait wait=new WebDriverWait(selenium.getWrappedDriver(), 10);
		
		//clickId("tokens");
		
		String loadOfferElement="//div[contains(text(),'"+campaignName+"')]//ancestor::div[1]//div[8]//a[@name='btn-loadOffers' and @title='Load offers']";
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loadOfferElement)));
		clickXPath(loadOfferElement);
		return this;
	}
	public CustomerCareForm scrolltoOffers() throws FormException{
		WebElement element=selenium.getWrappedDriver().findElement(By.id("offers"));
		super.scrollToElement(element);
		return this;
	}
	public Boolean verifyRanksOfOffers() throws FormException{
		Boolean status=false;
		List<String> ranks_displayed=new ArrayList<String>();
		List<String> ranks_sorted=new ArrayList<String>();
		List<WebElement> element=selenium.getWrappedDriver().findElements(By.xpath(".//*[@id='offers']/div[2]/div"));
		for(int i=0;i<(element.size()-1);i++)
		{
			int n=i+2;
			ranks_displayed.add(selenium.getWrappedDriver().findElement(By.xpath(".//*[@id='offers']/div[2]/div["+n+"]/div[3]")).getText());
			ranks_sorted.add(selenium.getWrappedDriver().findElement(By.xpath(".//*[@id='offers']/div[2]/div["+n+"]/div[3]")).getText());
			
		}
		Collections.sort(ranks_sorted);
		if(ranks_displayed.equals(ranks_sorted))
				status=true;
		
			return status;
	}
}
