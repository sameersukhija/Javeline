package com.lumata.expression.operators.testing.dao.catalogue;


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
import com.lumata.expression.operators.dao.administration.SalesChannelsList;
import com.lumata.expression.operators.dao.catalog.TokenTypeDAO;
import com.lumata.expression.operators.dao.catalog.TokenTypeDAOList;

public class TestTokenType {

	private static final Logger logger = LoggerFactory.getLogger( TestTokenType.class );
	private int TIMEOUT = 600000;
	private int ATTEMPT_INTERVAL = 500;
	
	Environment env;
	TokenTypeDAOList tokenTypesList;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
						
	}
	
	@Parameters({"qa"})
	@Test
	public void checkTokenType( @Optional("qa") String tenant ) {
		
		logger.info( Log.PUTTING.createMessage( "checkTokenType" , "Token Type" ) );
		
		tokenTypesList = new TokenTypeDAOList( env, tenant, null, "input/catalogue/offer_optimisation", "token_type_list_all.json", IOFileUtils.IOLoadingType.RESOURCE );
		
		
		//tokenTypesList = new TokenTypeList( env, tenant, null );
		
		
		
		/*
		ArrayList<String> salesChannels = new ArrayList<String>();
		
		salesChannels.add( "New Channel" );
		
		salesChannels.add( "Token Gold" );
		
		int index = tokenTypesList.insert( env, tenant, null, "Token Gold", 100, "DAYS", 100, 0, "sl", "", salesChannels );
		
		System.out.println( index );
		
		*/
		
	}	
	
}
