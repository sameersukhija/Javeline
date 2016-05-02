package com.lumata.e4o.generators.subscribers;

import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;

@TCMysqlMaster
public class GenerateSubscribersTokenRefusingRandomly extends ParentTestCase {

	final boolean GENERATE_FIXED_SUBSCRIBER = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_WITH_OPTION = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE = false;
	final boolean GENERATE_INCREMENTAL_SUBSCRIBERS = true;
	final boolean GENERATE_RANDOM_SUBSCRIBERS = false;
	
//	@Test( enabled = GENERATE_FIXED_SUBSCRIBER )
//	public void allocateAllTokensWithFixedSubscriber() throws GeneratorException, NumberFormatException, FieldException {
//		
//		final Long FIXED_MSISDN = 3399900001L;
//								
//		Generator.subscribers()
//					.environment( env )
//					.mysql( mysql )
//					.server( guiServer )
//					.user( superman )
//					.msisdnFixed( FIXED_MSISDN )
//					.xmlrpcAllTokenAllocation();
//					
//	}
//
//	@Test( enabled = false )
//	public void allocateAllTokensWithFixedMultiSubscribers() throws GeneratorException, NumberFormatException, FieldException {
//		
//		final Long[] FIXED_MSISDN = { 393669393643L, 393356848728L, 393280654379L  };
//		
//		for( Long msisdn : FIXED_MSISDN ) {
//			
//			Generator.subscribers()
//						.environment( env )
//						.mysql( mysql )
//						.server( guiServer )
//						.user( superman )
//						.msisdnFixed( msisdn )
//						.xmlrpcAllTokenAllocation();
//			
//		}
//		
//	}
	
	@Test( enabled = GENERATE_INCREMENTAL_SUBSCRIBERS )
	public void refuseTokensRamdolyWithIncrementalSubscribers() throws GeneratorException, NumberFormatException, FieldException {
		
		final Long STARTED_MSISDN = 3399900003L;
		final Integer INCREMENT = 1;
		final Integer MIN_EVENTS = 200;
		final Integer MAX_EVENTS = 500;
		final Integer REPEAT = 50;
		
		Generator.subscribers()
					.environment( env )
					.mysql( mysqlMaster )
					.server( guiServer )
					.user( user )
					.msisdnIncremental( STARTED_MSISDN, INCREMENT )
					.minRandomEvents( MIN_EVENTS )
					.maxRandomEvents( MAX_EVENTS )
					.repeat( REPEAT )
					.xmlrpcRandomTokenRefusing();
		
	}
	
//	@Test( enabled = GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE )
//	public void allocateAllTokensWithFixedSubscriberRandomRecharge() throws GeneratorException, NumberFormatException, FieldException {
//		
//		final Long FIXED_MSISDN = 3399900001L;
//		final Integer MIN_EVENTS = 1;
//		final Integer MAX_EVENTS = 1;
//				
//		Generator.subscribers()
//					.environment( env )
//					.mysql( mysql )
//					.server( guiServer )
//					.user( superman )
//					.msisdnFixed( FIXED_MSISDN )
//					.minRandomEvents( MIN_EVENTS )
//					.maxRandomEvents( MAX_EVENTS )
//					.xmlrpcAllTokenAllocation();
//					
//	}
	
}
