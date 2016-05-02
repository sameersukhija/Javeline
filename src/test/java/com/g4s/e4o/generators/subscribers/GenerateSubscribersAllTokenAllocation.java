package com.lumata.e4o.generators.subscribers;

import java.util.Calendar;

import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;


@TCMysqlMaster
public class GenerateSubscribersAllTokenAllocation extends ParentTestCase {

	final boolean GENERATE_FIXED_SUBSCRIBER = true;
	final boolean GENERATE_FIXED_SUBSCRIBER_WITH_OPTION = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE = false;
	final boolean GENERATE_INCREMENTAL_SUBSCRIBERS = false;
	final boolean GENERATE_RANDOM_SUBSCRIBERS = false;
	final boolean GENERATE_RANDOM_SUBSCRIBER_RANDOM_RECHARGE = false;
	
	@Test( enabled = GENERATE_FIXED_SUBSCRIBER )
	public void allocateAllTokensWithFixedSubscriber() throws GeneratorException, NumberFormatException, FieldException {
		
		final Long FIXED_MSISDN = 3399900001L;
								
		Generator.subscribers()
					.environment( env )
					.mysql( mysqlMaster )
					.server( guiServer )
					.user( user )
					.msisdnFixed( FIXED_MSISDN )
					.xmlrpcAllTokenAllocation();
					
	}

	@Test( enabled = false )
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
					.mysql( mysqlMaster )
					.server( guiServer )
					.user( user )
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
					.mysql( mysqlMaster )
					.server( guiServer )
					.user( user )
					.msisdnFixed( FIXED_MSISDN )
					.minRandomEvents( MIN_EVENTS )
					.maxRandomEvents( MAX_EVENTS )
					.xmlrpcAllTokenAllocation();
					
	}
	
	@Test( enabled = GENERATE_RANDOM_SUBSCRIBER_RANDOM_RECHARGE )
	public void allocateAllTokensWithRandomSubscriberRandomRecharge() throws GeneratorException, NumberFormatException, FieldException {
		
		final Long LEFT_MSISDN = 3399900001L;
		final Long RIGHT_MSISDN = 3399900041L;
		final Integer REPEAT = 15;
		final Integer MIN_EVENTS = 20;
		final Integer MAX_EVENTS = 50;
				
		Generator.subscribers()
					.environment( env )
					.mysql( mysqlMaster )
					.server( guiServer )
					.user( user )
					.msisdnRandom( LEFT_MSISDN, RIGHT_MSISDN )
					.minRandomEvents( MIN_EVENTS )
					.maxRandomEvents( MAX_EVENTS )
					.repeat( REPEAT )
					.xmlrpcRandomTokenAllocation();
					
	}
	
}
