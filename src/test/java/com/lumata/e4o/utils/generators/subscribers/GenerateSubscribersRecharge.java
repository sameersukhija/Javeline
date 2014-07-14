package com.lumata.e4o.utils.generators.subscribers;

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
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;


public class GenerateSubscribersRecharge {

	private static final Logger logger = LoggerFactory.getLogger( GenerateSubscribersRecharge.class );
	
	final boolean GENERATE_FIXED_SUBSCRIBER = true;
	final boolean GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE = false;
	final boolean GENERATE_INCREMENTAL_SUBSCRIBERS = false;
	final boolean GENERATE_RANDOM_SUBSCRIBERS = false;
	
	NetworkEnvironment env;	
	Server guiServer;
	User superman;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		System.out.println( environment );
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
		guiServer = env.getServer( "actrule" );
		
		superman = guiServer.getUser( "superman" );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
			
	}

	@Test( enabled = GENERATE_FIXED_SUBSCRIBER )
	public void generateFixedSubscriber() throws GeneratorException {
		
		final Long FIXED_MSISDN = 3399900001L;
		final Long RECHARGE_TO_GENERATE = 10L;
				
		Generator.subscribers()
					.server( guiServer )
					.user( superman )
					.msisdnFixed( FIXED_MSISDN )
					.xmlrpcRecharge( RECHARGE_TO_GENERATE );
					
	}
	
	@Test( enabled = GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE )
	public void generateFixedSubscriberRandomRecharge() throws GeneratorException {
		
		final Long FIXED_MSISDN = 3399900001L;
		final Integer MIN_EVENTS = 1;
		final Integer MAX_EVENTS = 1;
				
		Generator.subscribers()
					.server( guiServer )
					.user( superman )
					.msisdnFixed( FIXED_MSISDN )
					.minRandomEvents( MIN_EVENTS )
					.maxRandomEvents( MAX_EVENTS )
					.xmlrpcRecharge();
					
	}
	
	/*
	@Test( enabled = GENERATE_INCREMENTAL_SUBSCRIBERS )
	public void generateIncrementalSubscribers() throws GeneratorException {
		
		final Long STARTED_MSISDN = 3399900001L;
		final Integer INCREMENT = 1;
		final Boolean HAS_SMS_CHANNEL = true;
		final Boolean HAS_MAIL_CHANNEL = true;
		final Long SUBSCRIBERS_TO_GENERATE = 100L;		
		
		Generator.subscribers()
					.environment( env )
					.mysql( mysql )
					.msisdnIncremental( STARTED_MSISDN, INCREMENT )
					.subscriberHasSMSChannel( HAS_SMS_CHANNEL )
					.subscriberHasMAILChannel( HAS_MAIL_CHANNEL )
					.insertIntoEnvironment( SUBSCRIBERS_TO_GENERATE );
		
	}
	
	@Test( enabled = GENERATE_RANDOM_SUBSCRIBERS )
	public void generateRandomSubscribers() throws GeneratorException {
		
		final Long LEFT_MSISDN = 3399900001L;
		final Long RIGHT_MSISDN = 3399910000L;
		final Boolean HAS_SMS_CHANNEL = true;
		final Boolean HAS_MAIL_CHANNEL = true;
		final Long SUBSCRIBERS_TO_GENERATE = 10L;
		
		
		Generator.subscribers()
					.environment( env )
					.mysql( mysql )
					.msisdnRandom( LEFT_MSISDN, RIGHT_MSISDN )
					.subscriberHasSMSChannel( HAS_SMS_CHANNEL )
					.subscriberHasMAILChannel( HAS_MAIL_CHANNEL )
					.insertIntoEnvironment( SUBSCRIBERS_TO_GENERATE );
		
	}
	*/
	@AfterSuite
	public void end() {
		mysql.close();
	}
	
}
