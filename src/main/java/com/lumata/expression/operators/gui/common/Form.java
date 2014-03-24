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
	
	public void click(String forName, String xpath) throws Exception {
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName));
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
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName));
		WebElement we = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				xpath, timeout, interval);
		if (we == null) {
			throw new Exception("Element not found");
		}
		we.sendKeys(text);
	}

	public void selectByVisibleText(String forName, String xpath, String text) throws Exception {
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName));
		WebElement selectUnitRecharge = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				xpath, timeout, interval);
		if (selectUnitRecharge == null) {
			throw new Exception("Element not found");
		}
		Select select = new Select(selectUnitRecharge);
		select.selectByVisibleText(text);
	}
}
