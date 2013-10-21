package com.lumata.expression.operators.testing.dao.administration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.administration.BonusesDAO;

public class TestBonuses {

	private static final Logger logger = LoggerFactory.getLogger( TestBonuses.class );
	
	Environment env;
	BonusesDAO bonusesDAO;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
						
	}
	
	@Parameters({"qa"})
	@Test()
	public void createBonuses( @Optional("qa") String tenant ) {
		
		logger.info( Log.PUTTING.createMessage( "createBonuses" , "Create Bonuses" ) );
		
		bonusesDAO = new BonusesDAO( env, tenant, null, "input/bonuses", "bonuses.json", IOFileUtils.IOLoadingType.RESOURCE );
		
		Assert.assertTrue( bonusesDAO.size() >= 1 );
				
	}	
	
	
	/*
	@Parameters({"qa"})
	@Test( enabled = false )
	public void deleteSalesChannel( @Optional("qa") String tenant ) {
		
		logger.info( Log.PUTTING.createMessage( "checkSalesChannel" , "Sales Channels" ) );
		
		salesChannelsList = new SalesChannelsList( env, tenant, null, "input/sales_channels", "offer_sales_channels.json", IOFileUtils.IOLoadingType.RESOURCE );
		
		int salesChannelsSize = salesChannelsList.size();
		
		salesChannelsList.delete(env, tenant, null, "Token Gold ", true );
		
		int newSalesChannelsSize = salesChannelsList.size();
		
		Assert.assertEquals( newSalesChannelsSize, ( salesChannelsSize - 1 ) );
		
	}	
	
	@Parameters({"qa"})
	@Test( enabled = false )
	public void insertSalesChannel( @Optional("qa") String tenant ) {
		
		logger.info( Log.PUTTING.createMessage( "checkSalesChannel" , "Sales Channels" ) );
		
		salesChannelsList = new SalesChannelsList( env, tenant, null, "input/sales_channels", "offer_sales_channels.json", IOFileUtils.IOLoadingType.RESOURCE );
		
		int salesChannelsSize = salesChannelsList.size();
		
		salesChannelsList.insert(env, tenant, null, "Token Gold ", true );
		
		int newSalesChannelsSize = salesChannelsList.size();
		
		Assert.assertEquals( newSalesChannelsSize, ( salesChannelsSize - 1 ) );
		
	}	
	*/
}
