package com.lumata.e4o.gui.campaignmanager;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.CampaignException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaigns;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCSeleniumWebDriver
public class ConfigureCampaign extends ParentTestCase {

	@Parameters({"campaignFile"})
	@Test(enabled=TEST_ENABLED, priority = 1 )
	public void loadCampaigns(@Optional("campaignCMTemplate") String campaignFile) throws CampaignException, JSONSException, FormException, JSONException {
		
		CampaignsForm campaignForm = new CampaignsForm( seleniumWebDriver, new JSONCampaigns( "input/campaignmanager/campaigns", campaignFile ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( campaignForm.openForm().addCampaigns().navigate() );
	}
		
}
