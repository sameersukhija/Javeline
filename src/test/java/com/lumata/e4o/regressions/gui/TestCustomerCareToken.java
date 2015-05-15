package com.lumata.e4o.regressions.gui;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.customercare.CustomerCareTokensForm;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;
@TCOwners(
		@TCOwner( name="Parvinder Bhogra", email="parvinder.bhogra@lumatagroup.com" )
	)
	@TCSeleniumWebDriver
public class TestCustomerCareToken extends ParentTestCase{
	private static final Logger logger = LoggerFactory.getLogger( TestCustomerCareToken.class );

	@Test(enabled=TEST_ENABLED)
	public void testCustomerCareTokenTab() throws FormException,InterruptedException
	{
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Start of Customer Care TestCase", LOG_TO_STD_OUT);
		CustomerCareTokensForm ccTokenForm=new CustomerCareTokensForm(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		Reporter.log("Open CustomerCare Tab.", LOG_TO_STD_OUT);
		ccTokenForm.open();
		Reporter.log("Start search of subscriber", LOG_TO_STD_OUT);
		ccTokenForm.searchMsisdnByPhoneNumber(null, "9910201121");
		Reporter.log("Open Token Tab.", LOG_TO_STD_OUT);
		ccTokenForm.openTokenTab(60000L,200L);
		Reporter.log("Load offers allocated for the token", LOG_TO_STD_OUT);
		ccTokenForm.loadOffersForCampaignName("capaignstartmodel_20_2");
		if(ccTokenForm.verifyRanksOfOffers())
		{
			Assert.assertTrue(true, "Ranks are sorted!");
			Reporter.log("Ranks are in sorted order!", LOG_TO_STD_OUT);
		}
		else{
			Assert.fail("Ranks are not appearing in sorted order!");
			Reporter.log("Ranks are not in sorted order!", LOG_TO_STD_OUT);
		}
		ccTokenForm.close();
	}
}
