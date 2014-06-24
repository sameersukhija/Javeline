package com.lumata.e4o.gui.common;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.SalesChannelsForm;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.gui.catalogmanager.RulesForm;
import com.lumata.e4o.gui.catalogmanager.SuppliersForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.json.gui.administrationmanager.JSONSalesChannels;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
import com.lumata.e4o.json.gui.catalogmanager.JSONSuppliers;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;

/**
 * This class is an appender for "Basic Regression Suite"
 *
 * @author <a href="mailto:vincenzo.marrazzo@lumatagroup.com">Vincenzo Marrazzo</a>
 * 
 */
public class RegressionSuiteAppender extends ParentUITestCase {
	
	@Parameters({"supplierList"})
	@Test( priority = 1 )
	public void configureSuppliers( @Optional("supplierList") String supplierList ) throws FormException, JSONException, JSONSException {
		
		SuppliersForm suppliersForm = new SuppliersForm( seleniumWebDriver, new JSONSuppliers( "input/catalogmanager/suppliers", supplierList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( suppliersForm.open().addSuppliers().navigate() );
		
	}
	
	@Parameters({"salesChannelsList"})
	@Test(priority = 1 )
	public void configureSalesChannels( @Optional("salesChannelsList") String salesChannelsList ) throws FormException, JSONException, JSONSException {
		
		SalesChannelsForm salesChannelsForm = new SalesChannelsForm( seleniumWebDriver, new JSONSalesChannels( "input/administrationmanager/salesChannels", salesChannelsList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( salesChannelsForm.open().addSalesChannels().navigate() );
		
	}
	
	@Parameters({"tokenTypeList"})
	@Test(priority = 2 )
	public void configureTokeType( @Optional("tokenTypeList") String tokenTypeList ) throws FormException, JSONException, JSONSException {
		
		TokenTypeForm tokenTypeForm = new TokenTypeForm( seleniumWebDriver, new JSONTokenType( "input/catalogmanager/tokenTypes", tokenTypeList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( tokenTypeForm.open().addTokenTypes().close().navigate() );
		
	}
	
	@Parameters({"ruleList"})
	@Test(priority = 3 )
	public void configureRules( @Optional("ruleList") String ruleList ) throws FormException, JSONException, JSONSException {
		
		RulesForm rulesForm = new RulesForm( seleniumWebDriver, new JSONRules( "input/catalogmanager/rules", ruleList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( rulesForm.open().addRules().close().navigate() );
		
	}

	@Parameters({"campaignModelList"})
	@Test( enabled = true, priority = 4 )
	public void configureCampaignModel( @Optional("campaignModelList") String campaignModelList ) throws JSONSException, FormException, JSONException {

		CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver, new JSONCampaignModel( "input/campaignmanager/campaignModels", "campaignModelList" ), TIMEOUT, ATTEMPT_TIMEOUT );
						
		Assert.assertTrue( campaignModelForm.open()
							.addCampaignModels()
							.navigate() 
		);		
				
    }
}
