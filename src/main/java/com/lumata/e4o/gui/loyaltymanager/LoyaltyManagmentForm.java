package com.lumata.e4o.gui.loyaltymanager;

import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

public class LoyaltyManagmentForm extends LoyaltyManagerForm {
	
	public LoyaltyManagmentForm(SeleniumWebDriver selenium, long timeout,
			long interval) {
		super(selenium, timeout, interval);
		// TODO Auto-generated constructor stub
	}

	public static boolean open(SeleniumWebDriver selenium, long timeout, long interval) {
		
		/* return MenuBar.select(selenium, new SectionImpl<MenuBar.HomeSections, String, String>(MenuBar.HomeSections.LOYALTY,
				MenuBar.HomeSections.LOYALTY.section_id_prefix,  // --> "gwt-debug-actrule-loyalty"  
				MenuBar.HomeSections.LOYALTY.section_type), timeout, interval); */
		
		WebElement sectionTab = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-actrule-loyalty", timeout, interval);
		sectionTab.click();
		
		return true;
	}
}
