package com.lumata.e4o.gui.catalogmanager;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;
import com.lumata.e4o.gui.common.MenuBar;
import com.lumata.e4o.gui.common.SectionImpl;

public class CatalogueManagerForm extends Form {
	
	public CatalogueManagerForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}

	protected Form open() throws FormException {
		
		return clickId( "gwt-debug-BarCaptionHomeCatalog" );
		
	}
	
	protected static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		return MenuBar.select( selenium, new SectionImpl<MenuBar.HomeSections, String, String>(MenuBar.HomeSections.CATALOG, MenuBar.HomeSections.CATALOG.section_id_prefix, MenuBar.HomeSections.CATALOG.section_type), timeout, interval );
						
	}

}