package com.lumata.expression.operators.gui.loyalty;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm;
import com.lumata.expression.operators.gui.catalogue.LoyaltyForm;

public class LoyaltyCreationForm {

	private static final Logger logger = LoggerFactory.getLogger(CampaignModelForm.class);
	
	private SeleniumWebDriver selenium;
	private Mysql mysql;
	private long timeout;
	private long interval;
	
	public LoyaltyCreationForm(SeleniumWebDriver selenium, Mysql mysql, long timeout, long interval) {
		this.selenium = selenium;
		this.mysql = mysql;
		this.timeout = timeout;
		this.interval = interval;
	}
	
	public boolean open() {
		
		if (LoyaltyForm.open(selenium, timeout, interval)
				&& click("subSectionTab", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table/tbody/tr/td[3]/table/tbody/tr[2]/td[2]/div/div/div")
				&& click("badgesAccordion", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table/tbody/tr[2]/td/table/tbody/tr[1]/td/a/table/tbody/tr/td[2]")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean create() {
		
		Integer loyaltyProgramsCount = 0;
		ResultSet rs = mysql.execQuery("SELECT COUNT(*) FROM loyalty_programs");
		try {
			while (rs.next()) {
				loyaltyProgramsCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "SQL error: " + e.getMessage()));
			return false;
		}
		
		if (click("addNewProgramPopup", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td/div/table/tbody/tr/td/table/tbody/tr[" + (2 + loyaltyProgramsCount) + "]/td/button")
				&& sendKeys("programNameInput", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input", "BadgesProgName")
				&& sendKeys("programDescInput", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input", "BadgesProgDesc")
				&& click("programSave", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button")
				&& click("addBadgeType", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[4]/td/button")
				&& sendKeys("badgeNameInput", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/input", "Gold")
				&& click("badgeTypeSave", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button")
				&& click("badgeTypeClose", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/button")) {
			return true;
		} else {
			return false;
		}		
	}

	public boolean manage() {

		if (click("subSectionTab", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/div/div/div")
				&& click("editProgram", "//*[text()='BadgesProgName']/../../../../../../../../../..//*[@title='Edit']")
				&& click("addBadge", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/div/div/table/tbody/tr/td/table/tbody/tr[8]/td/button")
				&& sendKeys("badgeDefinitionName", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/input", "DefName")
				&& sendKeys("badgeDefinitionDesc", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input", "DefDesc")
				&& click("next", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button")
				&& click("next2", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button")
				&& click("next3", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button")
				&& click("addAwarded", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/button")
				&& click("eventType", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/div/table/tbody/tr/td")
				&& click("selectRevenue", "html/body/div[8]/div/table/tbody/tr[2]/td[2]/div/div/table/tbody/tr[9]/td")
				&& click("addAction", "//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']")
				&& selectByVisibleText("selectUnitRecharge", "//*[@id='gwt-debug-ListCampaignModelCreationEAUnit']", "/USSD") // was /recharge
				&& sendKeys("points", "//*[@id='gwt-debug-TextCampaignModelCreationEAValue']", "1")
				&& click("saveBadge", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[4]/button")
				&& click("closeBadge", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/button")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean click(String forName, String xpath) {
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName));
		WebElement we = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				xpath, timeout, interval);
		if (we == null) { return false; }
		we.click();		
		
		return true;
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
	
	/*public SeleniumWebDriver getSelenium() {
		return selenium;
	}

	public long getTimeout() {
		return timeout;
	}

	public long getInterval() {
		return interval;
	}*/

	public static void main(String[] args) throws Exception {
		
		//System.out.println(com.lumata.common.testing.system.Security.decrypt("bGppYm1NSUhJMmB3"));
		//System.out.println(com.lumata.common.testing.system.Security.decrypt("cmdISnJjdQ=="));
		
		System.out.println(com.lumata.common.testing.system.Security.encrypt("mkt"));
	}
}
