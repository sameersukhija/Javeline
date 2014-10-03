package com.lumata.e4o.generators.subscribers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;

import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.ParameterType.*;


public class GenerateSubscribersRecharge {

	private static final Logger logger = LoggerFactory.getLogger( GenerateSubscribersRecharge.class );
	
	final boolean GENERATE_FIXED_SUBSCRIBER = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_WITH_OPTION = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE_AND_SUBSCRIBERS = true;
	final boolean GENERATE_INCREMENTAL_SUBSCRIBERS = false;
	final boolean GENERATE_RANDOM_SUBSCRIBERS = false;
	
	NetworkEnvironment env;	
	Server guiServer;
	User superman;
	
	/* 	Initialize Environment */
	@Parameters({"environment"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
		guiServer = env.getServer( "actrule" );
		
		superman = guiServer.getUser( "superman" );
		
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
	
	@Test( enabled = GENERATE_FIXED_SUBSCRIBER_WITH_OPTION )
	public void generateFixedSubscriberWithOptionalParameter() throws GeneratorException {
		
		final Long FIXED_MSISDN = 3399900001L;
		final Long RECHARGE_TO_GENERATE = 1L;
		final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar today = Calendar.getInstance(); 
				
		Generator.subscribers()
					.server( guiServer )
					.user( superman )
					.msisdnFixed( FIXED_MSISDN )
					.xmlrpcRecharge( RECHARGE_TO_GENERATE, parameter( recharge, true ), parameter( event_date, sdf.format( today.getTime() ) ) );
					
	}
	
	@Test( enabled = false )
	public void generateFixedMultiSubscribers() throws GeneratorException {
		
		final Long[] FIXED_MSISDN = { 393669393643L, 393356848728L, 393280654379L  };
		final Long RECHARGE_TO_GENERATE = 1L;
		final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar today = Calendar.getInstance(); 
		
		for( Long msisdn : FIXED_MSISDN ) {
			
			Generator.subscribers()
						.server( guiServer )
						.user( superman )
						.msisdnFixed( msisdn )
						.xmlrpcRecharge( RECHARGE_TO_GENERATE, parameter( recharge, true ), parameter( event_date, sdf.format( today.getTime() ) ) );
						
		}
		
	}
	
	@Test( enabled = GENERATE_INCREMENTAL_SUBSCRIBERS )
	public void generateIncrementalSubscribers() throws GeneratorException {
		
		final Long STARTED_MSISDN = 3399900001L;
		final Integer INCREMENT = 1;
		final Boolean HAS_SMS_CHANNEL = true;
		final Boolean HAS_MAIL_CHANNEL = true;
		final Integer REPEAT = 20;
		final Long RECHARGE_TO_GENERATE = 5L;
		
		Generator.subscribers()
					.server( guiServer )
					.user( superman )
					.msisdnIncremental( STARTED_MSISDN, INCREMENT )
					.subscriberHasSMSChannel( HAS_SMS_CHANNEL )
					.subscriberHasMAILChannel( HAS_MAIL_CHANNEL )
					.repeat( REPEAT )
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
	
	@Test( enabled = GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE_AND_SUBSCRIBERS )
	public void generateFixedSubscriberRandomRechargeAndSubscribers() throws GeneratorException {
		
		final Long MIN_MSISDN = 3399900001L;
		final Long MAX_MSISDN = 3399900020L;
		final Integer MIN_EVENTS = 5;
		final Integer MAX_EVENTS = 100;
				
		Generator.subscribers()
					.server( guiServer )
					.user( superman )
					.msisdnRandom( MIN_MSISDN, MAX_MSISDN )
					.minRandomEvents( MIN_EVENTS )
					.maxRandomEvents( MAX_EVENTS )
					.xmlrpcRecharge();
					
	}
	
	@AfterSuite
	public void end() {}
	
}
