package com.lumata.e4o.generators.subscribers;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;


public class GenerateSubscribersAllTokenAllocation {

	private static final Logger logger = LoggerFactory.getLogger( GenerateSubscribersAllTokenAllocation.class );
	
	final boolean GENERATE_FIXED_SUBSCRIBER = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_WITH_OPTION = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE = false;
	final boolean GENERATE_INCREMENTAL_SUBSCRIBERS = true;
	final boolean GENERATE_RANDOM_SUBSCRIBERS = false;
	
	NetworkEnvironment env;	
	Server guiServer;
	User superman;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
		guiServer = env.getServer( "actrule" );
		
		superman = guiServer.getUser( "superman" );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
	}

	@Test( enabled = GENERATE_FIXED_SUBSCRIBER )
	public void allocateAllTokensWithFixedSubscriber() throws GeneratorException, NumberFormatException, FieldException {
		
		final Long FIXED_MSISDN = 3399900001L;
								
		Generator.subscribers()
					.environment( env )
					.mysql( mysql )
					.server( guiServer )
					.user( superman )
					.msisdnFixed( FIXED_MSISDN )
					.xmlrpcAllTokenAllocation();
					
	}

	@Test( enabled = false )
	public void allocateAllTokensWithFixedMultiSubscribers() throws GeneratorException, NumberFormatException, FieldException {
		
		final Long[] FIXED_MSISDN = { 393669393643L, 393356848728L, 393280654379L  };
		
		for( Long msisdn : FIXED_MSISDN ) {
			
			Generator.subscribers()
						.environment( env )
						.mysql( mysql )
						.server( guiServer )
						.user( superman )
						.msisdnFixed( msisdn )
						.xmlrpcAllTokenAllocation();
			
		}
		
	}
	
	@Test( enabled = GENERATE_INCREMENTAL_SUBSCRIBERS )
	public void allocateAllTokensWithIncrementalSubscribers() throws GeneratorException, NumberFormatException, FieldException {
		
		final Long STARTED_MSISDN = 3399900001L;
		final Integer INCREMENT = 1;
		final Integer REPEAT = 200;
		final Boolean CUSTOM_DATE = true;
		
		Calendar event_date = Calendar.getInstance();
		
		if( CUSTOM_DATE ) {
			
			event_date.set( Calendar.YEAR, 2014 );
			event_date.set( Calendar.MONTH, Calendar.OCTOBER );
			event_date.set( Calendar.DAY_OF_MONTH, 26 );
			event_date.set( Calendar.HOUR_OF_DAY, 13 );
			event_date.set( Calendar.MINUTE, 00 );
			event_date.set( Calendar.SECOND, 00 );
				
		}
			
		Generator.subscribers()
					.environment( env )
					.mysql( mysql )
					.server( guiServer )
					.user( superman )
					.msisdnIncremental( STARTED_MSISDN, INCREMENT )
					.repeat( REPEAT )
					.xmlrpcAllTokenAllocation();
		
	}
	
	@Test( enabled = GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE )
	public void allocateAllTokensWithFixedSubscriberRandomRecharge() throws GeneratorException, NumberFormatException, FieldException {
		
		final Long FIXED_MSISDN = 3399900001L;
		final Integer MIN_EVENTS = 1;
		final Integer MAX_EVENTS = 1;
				
		Generator.subscribers()
					.environment( env )
					.mysql( mysql )
					.server( guiServer )
					.user( superman )
					.msisdnFixed( FIXED_MSISDN )
					.minRandomEvents( MIN_EVENTS )
					.maxRandomEvents( MAX_EVENTS )
					.xmlrpcAllTokenAllocation();
					
	}
	
	@AfterSuite
	public void end() {
		
		mysql.close();
		
	}
	
}
