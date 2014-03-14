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
	private long timeout;
	private long interval;
	
	public LoyaltyCreationForm(SeleniumWebDriver selenium, long timeout, long interval) {
		this.selenium = selenium;
		this.timeout = timeout;
		this.interval = interval;
	}
	
	public static boolean open(LoyaltyCreationForm form) {
		
		if (LoyaltyForm.open(form.getSelenium(), form.getTimeout(), form.getInterval())
				&& form.click("subSectionTab", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table/tbody/tr/td[3]/table/tbody/tr[2]/td[2]/div/div/div")
				&& form.click("badgesAccordion", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table/tbody/tr[2]/td/table/tbody/tr[1]/td/a/table/tbody/tr/td[2]")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean create(LoyaltyCreationForm form, Mysql mysql) {
		
		Integer loyaltyProgramsCount = 0;
		ResultSet rs = mysql.execQuery("SELECT COUNT(*) FROM loyalty_programs");
		try {
			while (rs.next()) {
				loyaltyProgramsCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.info(Log.CHECKING.createMessage(form.getSelenium().getTestName(), "SQL error: " + e.getMessage()));
			return false;
		}
		
		if (form.click("addNewProgramPopup", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td/div/table/tbody/tr/td/table/tbody/tr[" + (2 + loyaltyProgramsCount) + "]/td/button") == false) { return false; }
		
		/*logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for addNewProgramPopup - Add"));
		
		System.out.println("TEST - size: " + addNewProgramPopup.findElements(By.xpath("//button[@title='Add']")).size());
		
		// skip error: Element is not currently visible and so may not be interacted with
		for (WebElement we : addNewProgramPopup.findElements(By.xpath("//button[@title='Add']"))) {
			try {
				we.click();
			} catch (Exception e) {
				// System.out.println("TEST - we.click() " + e.getMessage());
			}
		}*/

		if (form.sendKeys("programNameInput", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input", "BadgesProgName") == false) { return false; }

		if (form.sendKeys("programDescInput", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input", "BadgesProgDesc") == false) { return false; }

		if (form.click("programSave", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button") == false) { return false; }

		if (form.click("addBadgeType", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[4]/td/button") == false) { return false; }

		if (form.sendKeys("badgeNameInput", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/input", "Gold") == false) { return false; }
		
		if (form.click("badgeTypeSave", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button") == false) { return false; }
		
		if (form.click("badgeTypeClose", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/button") == false) { return false; }
		
		return true;
	}

	public static boolean manage(SeleniumWebDriver selenium, long timeout, long interval) {

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for subSectionTab"));
		WebElement subSectionTab = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/div/div/div", timeout, interval);
		if (subSectionTab == null) { return false; }
		subSectionTab.click();

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for editProgram"));
		WebElement editProgram = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				//"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[1]/table/tbody/tr[2]/td/div/div/table/tbody/tr[3]/td/table/tbody/tr[2]/td/div/table/tbody/tr/td/table/tbody/tr[6]/td[3]/table/tbody/tr/td[3]/button", timeout, interval);
				//"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[1]/table/tbody/tr[2]/td/div/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td/div/table/tbody/tr/td/table/tbody/tr[6]/td[3]/table/tbody/tr/td[3]/button", timeout, interval);
		  		//"//*[text()='BadgesProgName']/../../../../../../../../../../td/table/tbody/tr[2]/td/div/table/tbody/tr/td/table/tbody/tr[6]/td[3]/table/tbody/tr/td[3]/button", timeout, interval);
		  		//"//*[text()='BadgesProgName']/../../../../../../../../../..//*/td[3]/button", timeout, interval);
		  		  "//*[text()='BadgesProgName']/../../../../../../../../../..//*[@title='Edit']", timeout, interval);
		if (editProgram == null) { return false; }
		editProgram.click();

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for addBadge"));
		WebElement addBadge = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/div/div/table/tbody/tr/td/table/tbody/tr[8]/td/button", timeout, interval);
		if (addBadge == null) { return false; }
		addBadge.click();

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for badgeDefinitionName"));
		WebElement badgeDefinitionName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/input", timeout, interval);
		if (badgeDefinitionName == null) { return false; }
		badgeDefinitionName.sendKeys("DefName");

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for badgeDefinitionDesc"));
		WebElement badgeDefinitionDesc = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input", timeout, interval);
		if (badgeDefinitionDesc == null) { return false; }
		badgeDefinitionDesc.sendKeys("DefDesc");

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for next"));
		WebElement next = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button", timeout, interval);
		if (next == null) { return false; }
		next.click();

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for next2"));
		WebElement next2 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button", timeout, interval);
		if (next2 == null) { return false; }
		next2.click();
		
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for next3"));
		WebElement next3 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button", timeout, interval);
		if (next3 == null) { return false; }
		next3.click();

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for addAwarded"));
		WebElement addAwarded = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/button", timeout, interval);
		if (addAwarded == null) { return false; }
		addAwarded.click();

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for eventType"));
		WebElement eventType = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/div/table/tbody/tr/td", timeout, interval);
		if (eventType == null) { return false; }
		eventType.click();

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for selectRevenue"));
		WebElement selectRevenue = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[8]/div/table/tbody/tr[2]/td[2]/div/div/table/tbody/tr[9]/td", timeout, interval);
		if (selectRevenue == null) { return false; }
		selectRevenue.click();
		
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for addAction"));
		WebElement addAction = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']", timeout, interval);
		if (addAction == null) { return false; }
		addAction.click();
		
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for selectUnitRecharge"));
		WebElement selectUnitRecharge = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"//*[@id='gwt-debug-ListCampaignModelCreationEAUnit']", timeout, interval);
		if (selectUnitRecharge == null) { return false; }
		Select select = new Select(selectUnitRecharge);
		select.selectByVisibleText("/recharge");
		
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for points"));
		WebElement points = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"//*[@id='gwt-debug-TextCampaignModelCreationEAValue']", timeout, interval);
		if (points == null) { return false; }
		points.sendKeys("1");

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for saveBadge"));
		WebElement saveBadge = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[4]/button", timeout, interval);
		if (saveBadge == null) { return false; }
		saveBadge.click();
		
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for closeBadge"));
		WebElement closeBadge = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/button", timeout, interval);
		if (closeBadge == null) { return false; }
		closeBadge.click();
		
		return true;
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

	public SeleniumWebDriver getSelenium() {
		return selenium;
	}

	public long getTimeout() {
		return timeout;
	}

	public long getInterval() {
		return interval;
	}

	public static void main(String[] args) throws Exception {
		
		//System.out.println(com.lumata.common.testing.system.Security.decrypt("bGppYm1NSUhJMmB3"));
		//System.out.println(com.lumata.common.testing.system.Security.decrypt("cmdISnJjdQ=="));
		
		System.out.println(com.lumata.common.testing.system.Security.encrypt("mkt"));
	}
}
