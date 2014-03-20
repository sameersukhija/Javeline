package com.lumata.expression.operators.gui.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm;

public abstract class Form {
	private static final Logger logger = LoggerFactory.getLogger(CampaignModelForm.class);
	
	protected SeleniumWebDriver selenium;
	protected long timeout;
	protected long interval;
	
	public Form(SeleniumWebDriver selenium, long timeout, long interval) {
		this.selenium = selenium;
		this.timeout = timeout;
		this.interval = interval;
	}
	
	public boolean click(String forName, String xpath) {
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName));
		WebElement we = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				xpath, timeout, interval);
		if (we == null) { return false; }
		we.click();		
		
		return true;
	}
	
	public boolean clickFormat(String forName, String xpath, Object ... params) {
		return click(forName, String.format(xpath, params));
	}
	
	public boolean sendKeys(String forName, String xpath, String text) {
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName));
		WebElement we = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				xpath, timeout, interval);
		if (we == null) { return false; }
		we.sendKeys(text);
		
		return true;
	}

	public boolean selectByVisibleText(String forName, String xpath, String text) {
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName));
		WebElement selectUnitRecharge = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				xpath, timeout, interval);
		if (selectUnitRecharge == null) { return false; }
		Select select = new Select(selectUnitRecharge);
		select.selectByVisibleText(text);
		
		return true;
	}
}
