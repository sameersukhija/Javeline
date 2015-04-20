package com.lumata.e4o.gui.catalogmanager;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCSeleniumWebDriver
public class ConfigureRules extends ParentTestCase{

	@Parameters({"ruleList"})
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void configureRules( @Optional("ruleList") String ruleList ) throws FormException, JSONException, JSONSException {
		
		RulesForm rulesForm = new RulesForm( seleniumWebDriver, new JSONRules( "input/catalogmanager/rules", ruleList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( rulesForm.openForm().addRules().close().navigate() );
		
	}
		
}
