package com.lumata.expression.operators.gui.loyalty;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.gui.common.Form;
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
		openSubsection(ImmutableMap.of("clickAccordion", "true"));
	}
	
	public void openSubsection(Map<String, String> map) throws Exception {
		
		click( "subSectionTab", "//div[contains(text(),'Loyalty')]");
		
		if (isTrueKeyOrMissing(map, "clickAccordion")) 
			clickFormat( "accordion", "//div//*[text()='%s']", createCfg.getAccordionName());
	}

	public void create() throws Exception {
		
		addNewProgram();
		
		addBadgeTypeFromList(createCfg.getTypeNameList());
		
		click("badgeTypeClose", "//div[contains(text(),'Edit program')]//ancestor::tbody//*[@title='Close']");
	}

	public String duplication() throws Exception {
		
		addNewProgram();
		
		return getText("errorMessage", "html/body/div[6]/div/div");
	}

	public void manage() throws Exception {
		
		click( "subSectionTab", "//div[contains(text(),'Loyalty')]");
		
		click("subSectionTab", "//table[contains(@class,'tab-LoyaltyTab')]//div[text()='Management']");
		
		clickFormat( "editProgram", "//*[text()='%s']//ancestor::tbody[2]//*[@title='Edit']", createCfg.getProgramName());
		
		click( "addBadge", "//div[text()='Badges']//ancestor::tbody[2]//*[@title='Add']");
		
		sendKeys("badgeDef, k2, v2initionName",
			"//td[contains(text(),'Name')]/ancestor::tr[1]//input",
			manageCfg.getDefinitionName());
		
		sendKeys("badgeDefinitionDesc",
			"//td[contains(text(),'Description')]/ancestor::tr[1]//input",
			manageCfg.getDefinitionDescription());
		
		click("next", "//div[text()='Badge creation']//ancestor::tbody//*[@title='Next']");
	
		click("next2", "//div[text()='Badge creation']//ancestor::tbody//*[@title='Next']");
		
		click("next3", "//div[text()='Badge creation']//ancestor::tbody//*[@title='Next']");
		
		click("addAwarded", "//div[text()='Badge creation']//ancestor::tbody//*[@title='Add']");
		
		click("eventType", "//td[@role='menuitem']");
		
		clickFormat("selectRevenue", "//td[@role='menuitem' and text()='%s']",
			manageCfg.getAwardedEventType());
		
		click("addAction", "//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']");
		
		click("saveBadge", "//div[text()='Badge creation']//ancestor::tbody//*[@title='Save']");
		
		click("closeBadge", "//div[text()='Badges']//ancestor::tbody//*[@title='Close']");
	}

	public void closeNewProgramPopup() throws Exception {
		
		click("closeProgramPopup",
			"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[1]/button");
	}
	
	private void addNewProgram() throws Exception {
		
		clickFormat("addNewProgramPopup",
				"//div//*[text()='%s']/ancestor::td[2]//button",
				createCfg.getAccordionName());

		sendKeys(	"programNameInput",
					"//td[contains(text(),'Program Name')]/ancestor::tr[1]//input",				
					createCfg.getProgramName());
		
		sendKeys(	"programDescInput",
					"//td[contains(text(),'Program Description')]/ancestor::tr[1]//input",
					createCfg.getProgramDesc());
		
		click(	"programSave",
				"//*[@title='Save']");
	}
	
	private void addBadgeTypeFromList(List<String> badgeTypeList) throws Exception {
		
		for (String badgeType : badgeTypeList) {
			
			click(	"addBadgeType",
					"//div[contains(text(),'Edit program')]//ancestor::tbody//button[@title='Add']");
			
			sendKeys(	"badgeNameInput",
					"//div[text()='New Badge Type']//ancestor::tbody//input",
					badgeType);
			
			click(	"badgeTypeSave", 
					"//div[text()='New Badge Type']//ancestor::tbody//*[@title='Save']");
		}
	}
	
	public void delete() throws Exception {
		clickFormat("delete",
			"//div[contains(text(),'%s')]//ancestor::tbody[2]//*[@title='Delete']",
			createCfg.getProgramName());
		
		// Wait GWT deletes the record from the GUI
		Thread.sleep(4);
		Boolean isInvisible = (new WebDriverWait(selenium.getWrappedDriver(), 10))
			.until(ExpectedConditions.invisibilityOfElementLocated(By.id(
				"html/body/table[2]//*[text()='"+createCfg.getProgramName()+"']/../..//*[@title='Delete']")));
		
		System.out.println("isInvisible: " + isInvisible);
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
