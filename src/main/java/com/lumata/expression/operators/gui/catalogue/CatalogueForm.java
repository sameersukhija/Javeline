package com.lumata.expression.operators.gui.catalogue;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.gui.common.MenuBar;
import com.lumata.e4o.gui.common.SectionImpl;

public class CatalogueForm {
	
	protected static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		return MenuBar.select( selenium, new SectionImpl<MenuBar.HomeSections, String, String>(MenuBar.HomeSections.CATALOG, MenuBar.HomeSections.CATALOG.section_id_prefix, MenuBar.HomeSections.CATALOG.section_type), timeout, interval );
						
	}

}
