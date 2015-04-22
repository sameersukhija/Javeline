package com.lumata.e4o.testplan.functional.e2e;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.GUIConfigurator;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCSeleniumWebDriver
public class ConfiguratorGiveTokensABC extends ParentTestCase {

	@Parameters({"folder", "file"})
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void configureGUI( @Optional("input/gui/configurators/") String folder, @Optional("guiToGiveTokens") String file ) throws FormException, JSONException, JSONSException {
		
		Assert.assertTrue( GUIConfigurator.getInstance( seleniumWebDriver, user , TIMEOUT, ATTEMPT_TIMEOUT ).configureGUI( folder, file ) );
				
	}
	
}
