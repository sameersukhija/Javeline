package com.lumata.e4o.regressions.gui;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.SalesChannelsForm;
import com.lumata.e4o.json.gui.administrationmanager.JSONSalesChannels;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;
@TCOwners(
		@TCOwner( name="Parvinder Bhogra", email="parvinder.bhogra@lumatagroup.com" )
	)
	@TCSeleniumWebDriver
public class TestSalesChannel extends ParentTestCase{
	
	private String channel_name=null;
	private JSONSalesChannels salesChannelsCfg=null;
	
	@Parameters({"channel_jsonFilePath","channel_jsonFileName"})
	@Test( enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT, priority = 1 )
	public void testSupplierCreation( @Optional("input/administrationmanager/salesChannels") String channel_jsonFilePath, @Optional("salesChannelsList") String channel_jsonFileName) throws FormException, JSONException, JSONSException {
		Boolean status=false;
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Sales Channel Form\".", LOG_TO_STD_OUT);

		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + channel_jsonFilePath;
		String resourceFile = channel_jsonFileName;

		Reporter.log("\"Sales Channel Form\" is filled with resource file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);
		salesChannelsCfg=new JSONSalesChannels(resourcePath, resourceFile);
		SalesChannelsForm salesChannelForm = new SalesChannelsForm( seleniumWebDriver,salesChannelsCfg, TIMEOUT, ATTEMPT_TIMEOUT );
		
		salesChannelForm.open();
		JSONArray salesChannels = salesChannelsCfg.getList();
		
		for( int salesChannelsIndex = 0; salesChannelsIndex < salesChannels.length(); salesChannelsIndex++ ) {
			
			salesChannelsCfg.setSalesChannelById( salesChannelsIndex );
			
			if( salesChannelsCfg.getEnabled() ) {
				salesChannelForm.clickAddButton().setSalesChannelName(salesChannelsCfg.getName());
				salesChannelForm.waitForPageLoad();
				salesChannelForm.saveSalesChannel();
				salesChannelForm.waitForPageLoad();
				salesChannelForm.activateSalesChannel(salesChannelsCfg.getName());
				status=salesChannelForm.isSalesChannelExisting(salesChannelsCfg.getName());
				
				if(status==true)
				{
					Assert.assertTrue(status);
					Reporter.log("Sales Channel created and Activated Succesfully!",LOG_TO_STD_OUT);
					if(salesChannelsIndex == (salesChannels.length()-1))
					{
						setSalesChannelName(salesChannelsCfg.getName());
					}
				}
				else{
					Assert.fail("Sales Channel Creation Failed!");
					Reporter.log("Sales Channel Creation Failed!",LOG_TO_STD_OUT);
					}
			}
		}
	}
	
	@Test( enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT, priority = 2 )
	public void testDeactivateSalesChannel() throws FormException, JSONException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Reporter.log("Deactivation of an existing Sales Channel", LOG_TO_STD_OUT);
		SalesChannelsForm salesChannelForm = new SalesChannelsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		salesChannelForm.open();
			salesChannelForm.deactivateSalesChannel(getSalesChannelName());
			if(salesChannelForm.isSalesChannelDeactivated(getSalesChannelName()))
			{
				Assert.assertTrue(true);
				Reporter.log("Sales Channel De-Activated Succesfully!",LOG_TO_STD_OUT);
			}
			else{
				Assert.fail("Sales Channel De-Activation Failed!");
				Reporter.log("Sales Channel De-Activation Failed!",LOG_TO_STD_OUT);
				}
	}
	@Test( enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT, priority = 3 )
	public void testEditSalesChannel() throws FormException, JSONException, JSONSException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Editing an existing Sales Channel", LOG_TO_STD_OUT);
		SalesChannelsForm salesChannelForm = new SalesChannelsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		setSalesChannelName("Ch C");
		salesChannelForm.open();
		if(salesChannelForm.isSalesChannelExisting(getSalesChannelName()))
		{
			salesChannelForm.editSalesChannel(getSalesChannelName());
			setSalesChannelName("NewChannel");
			salesChannelForm.setSalesChannelName(getSalesChannelName());
			salesChannelForm.saveSalesChannel();
			if(salesChannelForm.isSalesChannelExisting(getSalesChannelName()))
			{
				Assert.assertTrue(true);
				Reporter.log("Sales Channel Edited Succesfully!",LOG_TO_STD_OUT);
			}
			else{
				Assert.fail("Sales Channel Editing Failed!");
				Reporter.log("Sales Channel Editing Failed!",LOG_TO_STD_OUT);
				}
		}
	}
	@Test( enabled=TEST_ENABLED, timeOut=TESTNG_TIMEOUT, priority = 4 )
	public void testDeleteSalesChannel() throws FormException {
		
		seleniumWebDriver.getWrappedDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Reporter.log("Editing an existing Sales Channel", LOG_TO_STD_OUT);
		SalesChannelsForm salesChannelForm = new SalesChannelsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		try{
			salesChannelForm.open();
			if(salesChannelForm.isSalesChannelExisting(getSalesChannelName()))
			{
				salesChannelForm.deleteSalesChannel(getSalesChannelName());
			}
		}catch(FormException e)
		{
			Reporter.log("Deletion of SalesChannel Failed!"+e.getMessage(), LOG_TO_STD_OUT);
		}
	}
	public void setSalesChannelName(String name) throws FormException{
		this.channel_name=name;
	}
	public String getSalesChannelName() throws FormException{
		return this.channel_name;
	}
}
