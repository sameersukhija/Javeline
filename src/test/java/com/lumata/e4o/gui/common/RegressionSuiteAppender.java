package com.lumata.e4o.gui.common;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.CampaignException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.SalesChannelsForm;
import com.lumata.e4o.gui.campaignmanager.CampaignModelFormOld;
import com.lumata.e4o.gui.campaignmanager.CampaignsForm;
import com.lumata.e4o.gui.catalogmanager.RulesForm;
import com.lumata.e4o.gui.catalogmanager.SuppliersForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.json.gui.administrationmanager.JSONSalesChannels;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaigns;
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
	
	/**
	 * 
	 */
	private String resourcePath = null;
	
	/**
	 * 
	 */
	private String fileName = null;
	
	@Parameters({"supplierList"})
	@Test( priority = 1 )
	public void configureSuppliers( @Optional("supplierList") String supplierList ) throws FormException, JSONException, JSONSException {
		
		resourcePath = "input/catalogmanager/suppliers";
		fileName = supplierList;
		
		Reporter.log( "Configure Suppliers with reosurce file : ", PRINT2STDOUT__);
		Reporter.log( "Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log( "Resource file -> " + fileName, PRINT2STDOUT__);		
		
		SuppliersForm suppliersForm = new SuppliersForm( seleniumWebDriver, new JSONSuppliers( resourcePath, fileName), TIMEOUT, ATTEMPT_TIMEOUT );
		
		//Assert.assertTrue( suppliersForm.openForm().addSuppliers().navigate() );
	}
	
	@Parameters({"salesChannelsList"})
	@Test(priority = 1 )
	public void configureSalesChannels( @Optional("salesChannelsList") String salesChannelsList ) throws FormException, JSONException, JSONSException {
		
		resourcePath = "input/administrationmanager/salesChannels";
		fileName = salesChannelsList;
		
		Reporter.log( "Configure Sales Channels with reosurce file : ", PRINT2STDOUT__);
		Reporter.log( "Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log( "Resource file -> " + fileName, PRINT2STDOUT__);		
		
		SalesChannelsForm salesChannelsForm = new SalesChannelsForm( seleniumWebDriver, new JSONSalesChannels( resourcePath, fileName), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( salesChannelsForm.open().addSalesChannels().navigate() );
	}
	
	@Parameters({"tokenTypeList"})
	@Test(priority = 2 )
	public void configureTokeType( @Optional("tokenTypeList") String tokenTypeList ) throws FormException, JSONException, JSONSException {

		resourcePath = "input/catalogmanager/tokenTypes";
		fileName = tokenTypeList;
		
		Reporter.log( "Configure Token Type with reosurce file : ", PRINT2STDOUT__);
		Reporter.log( "Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log( "Resource file -> " + fileName, PRINT2STDOUT__);		
		
		TokenTypeForm tokenTypeForm = new TokenTypeForm( seleniumWebDriver, new JSONTokenType( resourcePath, fileName), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( tokenTypeForm.openForm().addTokenTypes().close().navigate() );
	}
	
	@Parameters({"ruleListFile"})
	@Test(priority = 3 )
	public void configureRules( @Optional("rulesetTemplate") String ruleListFile ) throws FormException, JSONException, JSONSException {

		resourcePath = "input/catalogmanager/rules";
		fileName = ruleListFile;
		
		Reporter.log( "Configure Rules with reosurce file : ", PRINT2STDOUT__);
		Reporter.log( "Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log( "Resource file -> " + fileName, PRINT2STDOUT__);		
		
		RulesForm rulesForm = new RulesForm( seleniumWebDriver, new JSONRules( resourcePath, fileName), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( rulesForm.openForm().addRules().close().navigate() );
	}

	@Parameters({"campaignModelList"})
	@Test( priority = 4 )
	public void configureCampaignModel( @Optional("campaignModelList") String campaignModelList ) throws JSONSException, FormException, JSONException {

		resourcePath = "input/campaignmanager/campaignModels";
		fileName = campaignModelList;
		
		Reporter.log( "Configure Campaign models with reosurce file : ", PRINT2STDOUT__);
		Reporter.log( "Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log( "Resource file -> " + fileName, PRINT2STDOUT__);		
		
		CampaignModelFormOld campaignModelForm = new CampaignModelFormOld( seleniumWebDriver, new JSONCampaignModel( resourcePath, fileName), TIMEOUT, ATTEMPT_TIMEOUT );
						
		Assert.assertTrue( campaignModelForm.openForm()
							.addCampaignModels()
							.navigate() 
		);		
				
    }
	
	@Parameters({"campaignFile"})
	@Test( priority = 5 )
	public void configureCampaign(@Optional("campaignCreationTemplate") String campaignFile) throws CampaignException, JSONSException, FormException, JSONException {

		resourcePath = "input/campaignmanager/campaigns";
		fileName = campaignFile;
		
		Reporter.log( "Configure Campaign with reosurce file : ", PRINT2STDOUT__);
		Reporter.log( "Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log( "Resource file -> " + fileName, PRINT2STDOUT__);		
		
		CampaignsForm campaignForm = new CampaignsForm( seleniumWebDriver, new JSONCampaigns( resourcePath, fileName ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( campaignForm.openForm().addCampaigns().navigate() );
	}
	
}
