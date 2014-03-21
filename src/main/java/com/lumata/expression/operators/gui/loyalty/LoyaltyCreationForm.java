package com.lumata.expression.operators.gui.loyalty;

import java.util.List;

import org.json.JSONException;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.catalogue.LoyaltyForm;
import com.lumata.expression.operators.gui.common.Form;
import com.lumata.expression.operators.json.loyalty.LoyaltyCreateCfg;
import com.lumata.expression.operators.json.loyalty.LoyaltyManageCfg;

public class LoyaltyCreationForm extends Form {

	public LoyaltyCreationForm(SeleniumWebDriver selenium, long timeout, long interval) {
		super(selenium, timeout, interval);
	}
	
	public boolean open(LoyaltyCreateCfg createCfg) throws JSONException {
		
		return LoyaltyForm.open(selenium, timeout, interval)
				&& click("subSectionTab", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table//*[text()='Creation']")
				&& clickFormat("badgesAccordion", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table//*[text()='%s']",
						createCfg.getAccordionName());
	}

	public boolean create(LoyaltyCreateCfg createCfg) throws JSONException {
		
		return clickFormat("addNewProgramPopup", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table//*[text()='%s']/../../../../../../..//*[@title='Add']",
						createCfg.getAccordionName())
				&& sendKeys("programNameInput", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input",
						createCfg.getProgramName())
				&& sendKeys("programDescInput", "html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input",
						createCfg.getProgramDesc())
				&& click("programSave", "html/body/div[5]/div/table//*[@title='Save']")
				&& addBadgeTypeFromList(createCfg.getTypeNameList())
				&& click("badgeTypeClose", "html/body/div[5]/div/table//*[@title='Close']");
	}
	
	public boolean manage(LoyaltyCreateCfg createCfg, LoyaltyManageCfg manageCfg) throws JSONException {

		return click("subSectionTab", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table//*[text()='Management']")
				&& clickFormat("editProgram", "//*[text()='%s']/../../../../../../../../../..//*[@title='Edit']",
						createCfg.getProgramName())
				&& click("addBadge", "html/body/div[5]/div/table//*[@title='Add']")
				&& sendKeys("badgeDefinitionName", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/input",
						manageCfg.getDefinitionName())
				&& sendKeys("badgeDefinitionDesc", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input",
						manageCfg.getDefinitionDescription())
				&& click("next", "html/body/div[7]/div/table//*[@title='Next']")
				&& click("next2", "html/body/div[7]/div/table//*[@title='Next']")
				&& click("next3", "html/body/div[7]/div/table//*[@title='Next']")
				&& click("addAwarded", "html/body/div[7]/div/table//*[@title='Add']")
				&& click("eventType", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/div/table/tbody/tr/td")
				&& clickFormat("selectRevenue", "html/body/div[8]/div/table/tbody/tr[2]/td[2]/div/div/table//*[text()='%s']",
						manageCfg.getAwardedEventType())
				&& click("addAction", "//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']")
				&& selectByVisibleText("selectUnitRecharge", "//*[@id='gwt-debug-ListCampaignModelCreationEAUnit']",
						manageCfg.getAwardedActionUnit())
				&& sendKeys("points", "//*[@id='gwt-debug-TextCampaignModelCreationEAValue']",
						manageCfg.getAwardedActionPlus())
				&& click("saveBadge", "html/body/div[7]/div/table//*[@title='Save']")
				&& click("closeBadge", "html/body/div[5]/div/table//*[@title='Close']");
		
	}

	private boolean addBadgeTypeFromList(List<String> badgeTypeList) {
		boolean ok = true;
		
		for (String badgeType : badgeTypeList) {
			ok = click("addBadgeType", "html/body/div[5]/div/table//*[@title='Add']")
					&& sendKeys("badgeNameInput", "html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/input",
							badgeType)
					&& click("badgeTypeSave", "html/body/div[7]/div/table//*[@title='Save']");
		}
		
		return ok;
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
