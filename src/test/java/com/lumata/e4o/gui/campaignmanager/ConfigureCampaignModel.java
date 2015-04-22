package com.lumata.e4o.gui.campaignmanager;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

@TCSeleniumWebDriver
@TCMysqlMaster
public class ConfigureCampaignModel extends ParentTestCase {

	@Parameters({"campaignModelList"})
	@Test( enabled = TEST_ENABLED, priority = 4 )
	public void configureCampaignModel( @Optional("campaignModelList") String campaignModelList ) throws JSONSException, FormException, JSONException {

		CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver, new JSONCampaignModel( "input/campaignmanager/campaignModels", campaignModelList ), TIMEOUT, ATTEMPT_TIMEOUT );
						
		Assert.assertTrue( campaignModelForm.open().addCampaignModels().navigate() );		
				
    }
		
}
