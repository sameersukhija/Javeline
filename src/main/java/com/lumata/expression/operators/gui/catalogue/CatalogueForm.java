package com.lumata.expression.operators.gui.catalogue;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.common.MenuBar;
import com.lumata.expression.operators.gui.common.SectionImpl;

public class CatalogueForm {
	
	protected static boolean select( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		return MenuBar.select( selenium, new SectionImpl(MenuBar.HomeSections.CATALOG, MenuBar.HomeSections.CATALOG.section_id_prefix, MenuBar.HomeSections.CATALOG.section_type), timeout, interval );
						
	}

}
