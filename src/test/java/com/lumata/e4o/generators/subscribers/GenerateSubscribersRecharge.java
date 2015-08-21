package com.lumata.e4o.generators.subscribers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.testing.common.ParentTestCase;

import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.ParameterType.*;


public class GenerateSubscribersRecharge extends ParentTestCase {

	final boolean GENERATE_FIXED_SUBSCRIBER = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_WITH_OPTION = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE = false;
	final boolean GENERATE_INCREMENTAL_SUBSCRIBER_RANDOM_RECHARGE_AND_SUBSCRIBERS = false;
	final boolean GENERATE_INCREMENTAL_SUBSCRIBERS = true;
	final boolean GENERATE_RANDOM_SUBSCRIBERS = false;
	
	@Test( enabled = GENERATE_FIXED_SUBSCRIBER )
	public void generateFixedSubscriber() throws GeneratorException {
		
		final Long FIXED_MSISDN = 3399900001L;
		final Long RECHARGE_TO_GENERATE = 20L;
						
		Generator.subscribers()
					.server( guiServer )
					.user( user )
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
					.user( user )
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
						.user( user )
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
		final Integer REPEAT = 110;
		final Long RECHARGE_TO_GENERATE = 1L;
		
		Generator.subscribers()
					.server( guiServer )
					.user( user )
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
					.user( user )
					.msisdnFixed( FIXED_MSISDN )
					.minRandomEvents( MIN_EVENTS )
					.maxRandomEvents( MAX_EVENTS )
					.xmlrpcRecharge();
					
	}
	
	@Test( enabled = GENERATE_INCREMENTAL_SUBSCRIBER_RANDOM_RECHARGE_AND_SUBSCRIBERS )
	public void generateFixedSubscriberRandomRechargeAndSubscribers() throws GeneratorException {
		
		final Long STARTED_MSISDN = 3399900101L;
		final Integer INCREMENT = 1;
		final Integer REPEAT = 10;
		final Integer MIN_EVENTS = 20;
		final Integer MAX_EVENTS = 50;
				
		Generator.subscribers()
					.server( guiServer )
					.user( user )
					.msisdnIncremental( STARTED_MSISDN, INCREMENT )
					.minRandomEvents( MIN_EVENTS )
					.maxRandomEvents( MAX_EVENTS )
					.repeat( REPEAT )
					.xmlrpcRecharge();
					
	}
	
}
