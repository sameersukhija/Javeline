package com.lumata.expression.operators.testing.dao.administration;


import java.util.ArrayList;

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
import com.lumata.common.testing.system.Environment.EnvLoadingType;
import com.lumata.expression.operators.dao.administration.SalesChannelsList;

public class TestSalesChannels {

	private static final Logger logger = LoggerFactory.getLogger( TestSalesChannels.class );
	private int TIMEOUT = 600000;
	private int ATTEMPT_INTERVAL = 500;
	
	Environment env;
	SalesChannelsList salesChannelsList;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, EnvLoadingType.RESOURCE );
						
	}
	
	@Parameters({"qa"})
	@Test
	public void checkSalesChannel( @Optional("qa") String tenant ) {
		
		logger.info( Log.PUTTING.createMessage( "checkSalesChannel" , "Sales Channels" ) );
		
		salesChannelsList = new SalesChannelsList( env, tenant, null, "input/sales_channels", "offer_sales_channels.json", IOFileUtils.IOLoadingType.RESOURCE );
		
		
		/*
		if( !salesChannelsList.isSalesChannel( "Token Gold" ) ) {
			
			salesChannelsList.insert(env, tenant, null, "Token Gold", true );
			
		}
		*/
		
		
		//salesChannelsList.delete(env, tenant1, null, "Token Gold ", true );
		
		//System.out.println( salesChannelsList.size() );
		
	}	
	
}
