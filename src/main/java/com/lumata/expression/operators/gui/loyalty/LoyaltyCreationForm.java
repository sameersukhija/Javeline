package com.lumata.expression.operators.gui.loyalty;

import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.catalogue.LoyaltyForm;

public class LoyaltyCreationForm {

	public static boolean open(SeleniumWebDriver selenium, long timeout, long interval) {
		
		if( !LoyaltyForm.open(selenium, timeout, interval) ) { return false; }
		
		WebElement subSectionTab = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table/tbody/tr/td[3]/table/tbody/tr[2]/td[2]/div/div/div", timeout, interval);
		if (subSectionTab == null) { return false; }
		subSectionTab.click();
		
		WebElement badgesAccordion = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table/tbody/tr[2]/td/table/tbody/tr[1]/td/a/table/tbody/tr/td[2]", timeout, interval);
		if (badgesAccordion == null) { return false; }
		badgesAccordion.click();
				
		return true;
	}

	public static boolean create(SeleniumWebDriver selenium, long timeout, long interval) {
				
		WebElement addNewProgramPopup = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td/div/table/tbody/tr/td/table/tbody/tr[5]/td/button", timeout, interval);
		if (addNewProgramPopup == null) { return false; }
		addNewProgramPopup.click();

		WebElement programNameInput = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input", timeout, interval);
		if (programNameInput == null) { return false; }
		programNameInput.sendKeys("BadgesProgName");

		WebElement programDescInput = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input", timeout, interval);
		if (programDescInput == null) { return false; }
		programDescInput.sendKeys("BadgesProgDesc");

		WebElement programSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button", timeout, interval);
		if (programSave == null) { return false; }
		programSave.click();
		
		WebElement addBadgeType = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[4]/td/button", timeout, interval);
		if (addBadgeType == null) { return false; }
		addBadgeType.click();

		WebElement badgeNameInput = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/input", timeout, interval);
		if (badgeNameInput == null) { return false; }
		badgeNameInput.sendKeys("Gold");

		WebElement badgeTypeSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button", timeout, interval);
		if (badgeTypeSave == null) { return false; }
		badgeTypeSave.click();
		
		WebElement badgeTypeClose = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/button", timeout, interval);
		if (badgeTypeClose == null) { return false; }
		badgeTypeClose.click();
		
		return true;
	}

	public static boolean manage(SeleniumWebDriver selenium, long timeout, long interval) {

		WebElement subSectionTab = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/div/div/div", timeout, interval);
		if (subSectionTab == null) { return false; }
		subSectionTab.click();

		WebElement editProgram = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[1]/table/tbody/tr[2]/td/div/div/table/tbody/tr[3]/td/table/tbody/tr[2]/td/div/table/tbody/tr/td/table/tbody/tr[6]/td[3]/table/tbody/tr/td[3]/button", timeout, interval);
		if (editProgram == null) { return false; }
		editProgram.click();

		WebElement addBadge = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/div/div/table/tbody/tr/td/table/tbody/tr[8]/td/button", timeout, interval);
		if (addBadge == null) { return false; }
		addBadge.click();

		WebElement badgeDefinitionName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/input", timeout, interval);
		if (badgeDefinitionName == null) { return false; }
		badgeDefinitionName.sendKeys("DefName");

		WebElement badgeDefinitionDesc = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input", timeout, interval);
		if (badgeDefinitionDesc == null) { return false; }
		badgeDefinitionDesc.sendKeys("DefDesc");

		WebElement next = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button", timeout, interval);
		if (next == null) { return false; }
		next.click();

		WebElement next2 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button", timeout, interval);
		if (next2 == null) { return false; }
		next2.click();
		
		WebElement next3 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button", timeout, interval);
		if (next3 == null) { return false; }
		next3.click();

		WebElement addAwarded = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/button", timeout, interval);
		if (addAwarded == null) { return false; }
		addAwarded.click();

		// TODO...
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(com.lumata.common.testing.system.Security.decrypt("bGppYm1NSUhJMmB3"));
	}
}
