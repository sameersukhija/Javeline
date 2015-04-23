package com.lumata.e4o.regressions.gui;

import org.json.JSONException;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.FormException;

import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCOwners(
	@TCOwner( name="Arcangelo Di Pasquale", email="arcangelo.dipasquale@lumatagroup.com" )
)
@TCSeleniumWebDriver
public class BaseTest extends ParentTestCase {

	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void checkMandatoryFields1() throws FormException, JSONException, JSONSException {
		
		Assert.assertTrue( true );

	}
		
}
