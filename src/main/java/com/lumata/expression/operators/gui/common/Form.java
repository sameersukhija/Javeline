package com.lumata.expression.operators.gui.common;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.exceptions.FormException;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm;

public abstract class Form {
	private static final Logger logger = LoggerFactory.getLogger(CampaignModelForm.class);
	
	protected SeleniumWebDriver selenium;
	protected long timeout;
	protected long interval;
	protected boolean status;
	
	public Form(SeleniumWebDriver selenium, long timeout, long interval) {
		this.selenium = selenium;
		this.timeout = timeout;
		this.interval = interval;
	}
	
	public void click(String forName, String xpath) throws Exception {
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName + ", xpath: " + xpath));
		WebElement we = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				xpath, timeout, interval);
		if (we == null) {
			throw new Exception("Element not found");
		}
		we.click();		
	}
	
	public void clickFormat(String forName, String xpath, Object ... params) throws Exception {
		click(forName, String.format(xpath, params));
	}
	
	public void sendKeys(String forName, String xpath, String text) throws Exception {
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName + ", xpath: " + xpath));
		WebElement we = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				xpath, timeout, interval);
		if (we == null) {
			throw new Exception("Element not found");
		}
		we.sendKeys(text);
	}

	public void selectByVisibleText(String forName, String xpath, String text) throws Exception {
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName + ", xpath: " + xpath));
		WebElement selectUnitRecharge = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				xpath, timeout, interval);
		if (selectUnitRecharge == null) {
			throw new Exception("Element not found");
		}
		Select select = new Select(selectUnitRecharge);
		select.selectByVisibleText(text);
	}
	
	public String getText(String forName, String xpath) throws Exception {
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName + ", xpath: " + xpath));
		WebElement we = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				xpath, timeout, interval);
		if (we == null) {
			throw new Exception("Element not found");
		}
		return we.getText();
	}
	
	public boolean isTrueKey(Map<String, String> map, String key) {
		return map.containsKey(key) && map.get(key).equalsIgnoreCase("true");
	}
	
	public boolean isTrueKeyOrMissing(Map<String, String> map, String key) {
		return map.containsKey(key) == false || map.get(key).equalsIgnoreCase("true");
	}
	
	private WebElement search( SeleniumUtils.SearchBy by, String value ) throws FormException {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for " + by.name().toLowerCase() + " = " + value ) );
		
		WebElement we = SeleniumUtils.findForComponentDisplayed( selenium, by, value, timeout, interval );
		if( we == null ) {
			status = false;
			throw new FormException( Log.FAILED.createMessage( selenium.getTestName() , "Element not found ( " + value + " )" ) ); 
		}	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for " + by.name().toLowerCase() + " = " + value ) );
		
		status = true;
		
		return we;
		
	}
	
	public boolean isDisplayed(  SeleniumUtils.SearchBy by, String value  ) throws FormException {
		
		return search( by, value ).isDisplayed();
		
	}
	
	public boolean isDisplayedById( String id  ) throws FormException {
		
		return isDisplayed( SeleniumUtils.SearchBy.ID, id );
		
	}
	
	private Form click( SeleniumUtils.SearchBy by, String value ) throws FormException {
		
		WebElement we = search( by, value );
		we.click();
				
		return this;
		
	}
	
	public Form clickId( String id ) throws FormException {
		
		return click( SeleniumUtils.SearchBy.ID, id );
		
	}
	
	private Form select( SeleniumUtils.SearchBy by, String value, String label ) throws FormException {
		
		search( by, value );
				
		selenium.select( by.name().toLowerCase() + "=" + value, "label=" + label );
		
		return this;
		
	}
	
	public Form selectById( String id, String label ) throws FormException {
		
		return select( SeleniumUtils.SearchBy.ID, id, label );
		
	}
	
	private Form type( SeleniumUtils.SearchBy by, String value, String label ) throws FormException {
		
		search( by, value );
				
		selenium.type( by.name().toLowerCase() + "=" + value, label );
		
		return this;
		
	}
	
	public Form typeById( String id, String label ) throws FormException {
		
		return type( SeleniumUtils.SearchBy.ID, id, label );
		
	}
	
	public boolean navigate() {
		return status;
	}
	
}
