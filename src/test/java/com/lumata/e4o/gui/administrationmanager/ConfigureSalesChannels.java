package com.lumata.e4o.gui.administrationmanager;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.administrationmanager.JSONSalesChannels;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCSeleniumWebDriver
public class ConfigureSalesChannels extends ParentTestCase {

	@Parameters({"salesChannelsList"})
	@Test( enabled=TEST_ENABLED, priority = 1 )
	public void configureSalesChannels( @Optional("salesChannelsList") String salesChannelsList ) throws FormException, JSONException, JSONSException {
		
		SalesChannelsForm salesChannelsForm = new SalesChannelsForm( seleniumWebDriver, new JSONSalesChannels( "input/administrationmanager/salesChannels", salesChannelsList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( salesChannelsForm.open().addSalesChannels().navigate() );
				
	}
		
}
