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
		subSectionTab.click();
		
		WebElement badgesAccordion = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table/tbody/tr[2]/td/table/tbody/tr[1]/td/a/table/tbody/tr/td[2]", timeout, interval);
		badgesAccordion.click();
				
		return true;
	}

	public static boolean create(SeleniumWebDriver selenium, long timeout, long interval) {
				
		WebElement addNewProgramPopup = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[2]/td/div/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td/div/table/tbody/tr/td/table/tbody/tr[5]/td/button", timeout, interval);
		addNewProgramPopup.click();

		WebElement programNameInput = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input", timeout, interval);
		programNameInput.sendKeys("Name");

		WebElement programDescInput = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input", timeout, interval);
		programDescInput.sendKeys("Desc");

		WebElement programSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button", timeout, interval);
		programSave.click();
		
		// TODO ...
		
		return true;
	}

	public static void main(String[] args) throws Exception {
		
		System.out.println(com.lumata.common.testing.system.Security.decrypt("bGppYm1NSUhJMmB3"));
	}
}
