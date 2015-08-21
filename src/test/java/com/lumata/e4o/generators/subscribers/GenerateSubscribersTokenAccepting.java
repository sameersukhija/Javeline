package com.lumata.e4o.generators.subscribers;

import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;

@TCMysqlMaster
public class GenerateSubscribersTokenAccepting extends ParentTestCase {

	final boolean GENERATE_FIXED_SUBSCRIBER = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_WITH_OPTION = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE = false;
	final boolean GENERATE_INCREMENTAL_SUBSCRIBERS = false;
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
	@Test( enabled = true )
	public void allocateAllTokensWithFixedMultiSubscribers() throws GeneratorException, NumberFormatException, FieldException {
		
		//final Long[] FIXED_MSISDN = { 393669393643L, 393356848728L, 393280654379L  };
		final Long[] FIXED_MSISDN = { 3399900002L, 3399900012L, 3399900022L, 3399900032L, 3399900042L, 3399900052L, 3399900062L, 3399900072L, 3399900082L, 3399900092L  };
				
		for( Long msisdn : FIXED_MSISDN ) {
			
			Generator.subscribers()
						.environment( env )
						.mysql( mysqlMaster )
						.server( guiServer )
						.user( user )
						.msisdnFixed( msisdn )
						.xmlrpcAllTokenAccepting();
			
		}
		
	}
	
	@Test( enabled = GENERATE_INCREMENTAL_SUBSCRIBERS )
	public void acceptTokensRamdolyWithIncrementalSubscribers() throws GeneratorException, NumberFormatException, FieldException {
		
		final Long STARTED_MSISDN = 3399900001L;
		final Integer INCREMENT = 1;
		final Integer MIN_EVENTS = 20;
		final Integer MAX_EVENTS = 50;
		final Integer REPEAT = 23;
		
		Generator.subscribers()
					.environment( env )
					.mysql( mysqlMaster )
					.server( guiServer )
					.user( user )
					.msisdnIncremental( STARTED_MSISDN, INCREMENT )
					.minRandomEvents( MIN_EVENTS )
					.maxRandomEvents( MAX_EVENTS )
					.repeat( REPEAT )
					.xmlrpcRandomTokenAccepting();
		
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
