package com.lumata.expression.operators.gui.loyalty;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.catalogue.LoyaltyForm;
import com.lumata.expression.operators.gui.common.Form;
import com.lumata.expression.operators.json.loyalty.LoyaltyCreateCfg;
import com.lumata.expression.operators.json.loyalty.LoyaltyManageCfg;

public class LoyaltyCreationForm extends Form {

	private LoyaltyCreateCfg createCfg;
	private LoyaltyManageCfg manageCfg;
	
	public LoyaltyCreationForm(SeleniumWebDriver selenium, long timeout, long interval,
			LoyaltyCreateCfg createCfg, LoyaltyManageCfg manageCfg) {
		
		super(selenium, timeout, interval);
		
		this.createCfg = createCfg;
		this.manageCfg = manageCfg;
	}
	
	public void open() throws Exception {
		
		LoyaltyForm.open(selenium, timeout, interval);
		openSubsection(ImmutableMap.of(
				"clickAccordion", "true"));
	}
	
	public void openSubsection(Map<String, String> map) throws Exception {
		
		click("subSectionTab",
			"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table//*[text()='Creation']");
		
		if (isTrueKeyOrMissing(map, "clickAccordion")) {
		
			/* Wrong XPATH we have only one submenu ("Creation" and not "Management") 
			clickFormat("accordion",
				"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table//*[text()='%s']",
				createCfg.getAccordionName());*/
			
			clickFormat("accordion",
					"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div//*[text()='%s']",
					createCfg.getAccordionName());
		}
	}

	public void create() throws Exception {
		
		addNewProgram();
		
		addBadgeTypeFromList(createCfg.getTypeNameList());
		
		click("badgeTypeClose",
			"html/body/div[5]/div/table//*[@title='Close']");
	}

	public String duplication() throws Exception {
		
		addNewProgram();
		
		return getText("errorMessage", "html/body/div[6]/div/div");
	}

	public void manage() throws Exception {

		click("subSectionTab",
			"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table//*[text()='Management']");
		
		clickFormat("editProgram",
			"//*[text()='%s']/../../../../../../../../../..//*[@title='Edit']",
			createCfg.getProgramName());
		
		click("addBadge",
			"html/body/div[5]/div/table//*[@title='Add']");
		
		sendKeys("badgeDef, k2, v2initionName",
			"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/input",
			manageCfg.getDefinitionName());
		
		sendKeys("badgeDefinitionDesc",
			"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input",
			manageCfg.getDefinitionDescription());
		
		click("next", "html/body/div[7]/div/table//*[@title='Next']");
		
		click("next2", "html/body/div[7]/div/table//*[@title='Next']");
		
		click("next3", "html/body/div[7]/div/table//*[@title='Next']");
		
		click("addAwarded", "html/body/div[7]/div/table//*[@title='Add']");
		
		click("eventType",
			"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[1]/div/table/tbody/tr/td");
		
		clickFormat("selectRevenue",
			"html/body/div[8]/div/table/tbody/tr[2]/td[2]/div/div/table//*[text()='%s']",
			manageCfg.getAwardedEventType());
		
		click("addAction", "//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']");
		
		// Wrong XPATH, missing select value and input field (hidden)
		//selectByVisibleText("selectUnitRecharge",
		//	"//*[@id='gwt-debug-ListCampaignModelCreationEAUnit']",
		//	manageCfg.getAwardedActionUnit());
		//
		//sendKeys("points",
		//	"//*[@id='gwt-debug-TextCampaignModelCreationEAValue']",
		//	manageCfg.getAwardedActionPlus());
		
		click("saveBadge", "html/body/div[7]/div/table//*[@title='Save']");
		
		click("closeBadge", "html/body/div[5]/div/table//*[@title='Close']");
	}

	public void closeNewProgramPopup() throws Exception {
		
		click("closeProgramPopup",
			"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[1]/button");
	}
	
	private void addNewProgram() throws Exception {
		
		/* Wrong XPATH we have only one submenu ("Creation" and not "Management")
		clickFormat("addNewProgramPopup",
			"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table//*[text()='%s']/../../../../../../..//*[@title='Add']",
			createCfg.getAccordionName());*/

		clickFormat("addNewProgramPopup",
				"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div//*[text()='%s']/../../../../../../..//*[@title='Add']",
				createCfg.getAccordionName());

		sendKeys("programNameInput",
			"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input",
			createCfg.getProgramName());
		
		sendKeys("programDescInput",
			"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input",
			createCfg.getProgramDesc());
		
		click("programSave",
			"html/body/div[5]/div/table//*[@title='Save']");
	}
	
	private void addBadgeTypeFromList(List<String> badgeTypeList) throws Exception {
		
		for (String badgeType : badgeTypeList) {
			
			click("addBadgeType",
				"html/body/div[5]/div/table//*[@title='Add']");
			
			sendKeys("badgeNameInput",
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/input",
				badgeType);
			
			click("badgeTypeSave", "html/body/div[7]/div/table//*[@title='Save']");
		}
	}
	
	public void delete() throws Exception {
		clickFormat("delete",
			"html/body/table[2]//*[text()='%s']/../..//*[@title='Delete']",
			createCfg.getProgramName());
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
