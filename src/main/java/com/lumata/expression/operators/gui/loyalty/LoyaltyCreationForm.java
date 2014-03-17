package com.lumata.expression.operators.gui.loyalty;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm;
import com.lumata.expression.operators.gui.catalogue.LoyaltyForm;
import com.lumata.expression.operators.gui.common.Form;

public class LoyaltyCreationForm extends Form {

	private static final Logger logger = LoggerFactory.getLogger(CampaignModelForm.class);
	
	private Mysql mysql;
	
	public LoyaltyCreationForm(SeleniumWebDriver selenium, Mysql mysql, long timeout, long interval) {
		super(selenium, timeout, interval);
		this.mysql = mysql;
	}
	
	public boolean open() {
		
		return LoyaltyForm.open(selenium, timeout, interval)
				&& click("subSectionTab", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table//*[text()='Creation']")
				&& click("badgesAccordion", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table//*[text()='Badges']");
	}

	public boolean create() {
		
		return click("addNewProgramPopup", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table//*[text()='Badges']/../../../../../../..//*[@title='Add']")
				&& sendKeys("programNameInput", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input",
						"BadgesProgName")
				&& sendKeys("programDescInput", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input",
						"BadgesProgDesc")
				&& click("programSave", "html/body/div[5]/div/table//*[@title='Save']")
				&& click("addBadgeType", "html/body/div[5]/div/table//*[@title='Add']")
				&& sendKeys("badgeNameInput", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/input",
						"Gold")
				&& click("badgeTypeSave", "html/body/div[7]/div/table//*[@title='Save']")
				&& click("badgeTypeClose", "html/body/div[5]/div/table//*[@title='Close']");
	}
	
	public boolean manage() {

		return click("subSectionTab", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table//*[text()='Management']")
				&& click("editProgram", "//*[text()='BadgesProgName']/../../../../../../../../../..//*[@title='Edit']")
				&& click("addBadge", "html/body/div[5]/div/table//*[@title='Add']")
				&& sendKeys("badgeDefinitionName", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/input",
						"DefName")
				&& sendKeys("badgeDefinitionDesc", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input",
						"DefDesc")
				&& click("next", "html/body/div[7]/div/table//*[@title='Next']")
				&& click("next2", "html/body/div[7]/div/table//*[@title='Next']")
				&& click("next3", "html/body/div[7]/div/table//*[@title='Next']")
				&& click("addAwarded", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/button")
				&& click("eventType", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/div/table/tbody/tr/td")
				&& click("selectRevenue", "html/body/div[8]/div/table/tbody/tr[2]/td[2]/div/div/table/tbody/tr[9]/td")
				&& click("addAction", "//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']")
				&& selectByVisibleText("selectUnitRecharge", "//*[@id='gwt-debug-ListCampaignModelCreationEAUnit']",
						"/USSD") // was /recharge
				&& sendKeys("points", "//*[@id='gwt-debug-TextCampaignModelCreationEAValue']",
						"1")
				&& click("saveBadge", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[4]/button")
				&& click("closeBadge", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/button");
	}

	/*private Integer selectLoyaltyProgramsCount() {
		Integer loyaltyProgramsCount = 0;
		
		ResultSet rs = mysql.execQuery("SELECT COUNT(*) FROM loyalty_programs");
		try {
			while (rs.next()) {
				loyaltyProgramsCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "SQL error: " + e.getMessage()));
			return null;
		}
		
		return loyaltyProgramsCount;
	}*/
	
	public static void main(String[] args) throws Exception {
		
		//System.out.println(com.lumata.common.testing.system.Security.decrypt("bGppYm1NSUhJMmB3"));
		//System.out.println(com.lumata.common.testing.system.Security.decrypt("cmdISnJjdQ=="));
		
		System.out.println(com.lumata.common.testing.system.Security.encrypt("mkt"));
	}
}
