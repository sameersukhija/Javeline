package com.lumata.e4o.gui.customercare;

import java.util.ArrayList;
import java.util.List;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

import org.openqa.selenium.WebElement;

public class CustomerCareHistoryForm  extends CustomerCareForm {

	public CustomerCareHistoryForm ( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	public  CustomerCareHistoryForm openHistoryTab() throws FormException {
		
		clickId( "gwt-debug-BtnCCBarInfoHistory" );
		
		return this;
		
	}
	
	public String getNotificationHistoryStatus(String strCampaignName) throws FormException{
		return this.getTextByXPath("//table[contains(@class,'page-SubscriberHistoryPageView')]//td[text()='Notification History']//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//div[@class='gwt-Label' and text()='"+strCampaignName+"']//ancestor::tr[contains(@class,'cycle')]/td[6]");
	}
	
	public String getNotificationDate(String strCampaignName) throws FormException{
		return this.getTextByXPath("//table[contains(@class,'page-SubscriberHistoryPageView')]//td[text()='Notification History']//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//div[@class='gwt-Label' and text()='"+strCampaignName+"']//ancestor::tr[contains(@class,'cycle')]/td[1]");
	}
	

	public String getChannel(String strCampaignName) throws FormException{
		return this.getTextByXPath("//table[contains(@class,'page-SubscriberHistoryPageView')]//td[text()='Notification History']//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//div[@class='gwt-Label' and text()='"+strCampaignName+"']//ancestor::tr[contains(@class,'cycle')]/td[4]");
	}
	
	
	public CustomerCareHistoryForm clickNotificationHistoryRefreshButton() throws FormException{
		super.clickXPath("//table[contains(@class,'page-SubscriberHistoryPageView')]//td[text()='Notification History']//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//button[@name='btn-refresh']");
		return this;
	}
	
	public CustomerCareHistoryForm clickBonusDetailsRefreshButton() throws FormException{
		super.clickXPath("//table[contains(@class,'page-SubscriberHistoryPageView')]//td[text()='Bonus Details']//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//button[@name='btn-refresh']");
		return this;
	}
	
	public String getRewardName(String strCampaignName) throws FormException{
		return this.getTextByXPath("//table[contains(@class,'page-SubscriberHistoryPageView')]//td[text()='Bonus Details']//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//div[@class='gwt-Label' and text()='"+strCampaignName+"']//ancestor::tr[contains(@class,'cycle')]/td[2]");
		
	}
	
	public String getRewardQuantity(String strCampaignName) throws FormException{
		return this.getTextByXPath("//table[contains(@class,'page-SubscriberHistoryPageView')]//td[text()='Bonus Details']//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//div[@class='gwt-Label' and text()='"+strCampaignName+"']//ancestor::tr[contains(@class,'cycle')]/td[3]");
		
	}
	
	public String getOperationType(String strCampaignName) throws FormException{
		return this.getTextByXPath("//table[contains(@class,'page-SubscriberHistoryPageView')]//td[text()='Bonus Details']//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//div[@class='gwt-Label' and text()='"+strCampaignName+"']//ancestor::tr[contains(@class,'cycle')]/td[4]");
		
	}
	
	public String getOfferName() throws FormException{
		return this.getTextByXPath("//table[contains(@class,'page-SubscriberHistoryPageView')]//td[text()='Purchase Details']//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//div[@class='gwt-Label' and text()='superman']//ancestor::tr[contains(@class,'cycle')]/td[6]");
	}
	
	public CustomerCareHistoryForm clickPurchaseDetailsRefreshButton() throws FormException{
		super.clickXPath("//table[contains(@class,'page-SubscriberHistoryPageView')]//td[text()='Purchase Details']//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//button[@name='btn-refresh']");
		return this;
	}
	
	
public List<WebElement> getOfferNameList() throws FormException {
		
		
		
		List<WebElement> offerNameList = new ArrayList<WebElement>();
		
		try {
			
			offerNameList = super.searchListByXPath("//table[contains(@class,'page-SubscriberHistoryPageView')]//td[text()='Purchase Details']","//ancestor::table[contains(@class,'gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open')]//div[@class='gwt-Label' and text()='superman']//ancestor::tr[contains(@class,'cycle')]/td[6]" );
		
		} catch( FormException fe ) {
			
			/** in case of empty list **/;
		
		}
		
		return offerNameList;
		
	}
	
	public Boolean isOfferNameInList( String strOfferName ) throws FormException {
		
		List<WebElement> offerNameList = getOfferNameList();
				
		for( WebElement offerNameE1 : offerNameList ) {
			
			if( offerNameE1.getText().trim().equals( strOfferName ) ) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	} 

	
	
	
	
	
	
	
	public List<WebElement> getCampaignList() throws FormException{
		List<WebElement> campaignList = new ArrayList<WebElement>();
		
		try {
		
			campaignList = super.searchListByXPath( "//div[@class='gwt-Label' and text()='Campaign']","//ancestor::tr[contains(@class,'cycle')]/td[3]" );
		
		} catch( FormException fe ) {
			
			/** in case of empty list **/;
		
		}
		
		return campaignList;
		
	}
	
	
	@Override
	public CustomerCareHistoryForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
	
	}
	
	public CustomerCareHistoryForm sendKeysById( String id, String text ) throws FormException {
		
		super.sendKeysById( id, text );
		
		return this;
	
	}
	
	public CustomerCareHistoryForm selectByIdAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByIdAndVisibleText( name, text );	
		
		return this;
		
	}
	
}
