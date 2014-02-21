package com.lumata.expression.operators.gui.catalogue;

import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.common.MenuBar;
import com.lumata.expression.operators.gui.common.SectionImpl;

public class LoyaltyForm {
	
	public static boolean open(SeleniumWebDriver selenium, long timeout, long interval) {
		
		/* return MenuBar.select(selenium, new SectionImpl<MenuBar.HomeSections, String, String>(MenuBar.HomeSections.LOYALTY,
				MenuBar.HomeSections.LOYALTY.section_id_prefix,  // --> "gwt-debug-actrule-loyalty"  
				MenuBar.HomeSections.LOYALTY.section_type), timeout, interval); */
		
		WebElement sectionTab = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-actrule-loyalty", timeout, interval);
		sectionTab.click();
		
		return true;
	}
}
