package com.lumata.e4o.generators.subscribers;

import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;

@TCMysqlMaster
public class GenerateSubscribers extends ParentTestCase {

	final boolean GENERATE_FIXED_SUBSCRIBER = false;
	final boolean GENERATE_INCREMENTAL_SUBSCRIBERS = true;
	final boolean GENERATE_RANDOM_SUBSCRIBERS = false;

	@Test( enabled = GENERATE_FIXED_SUBSCRIBER )
	public void generateFixedSubscriber() throws GeneratorException {
		
		final Long FIXED_MSISDN = 3399900001L;
		final Boolean HAS_SMS_CHANNEL = true;
		final Boolean HAS_MAIL_CHANNEL = true;
		final Long SUBSCRIBERS_TO_GENERATE = 1L;
		
		
		Generator.subscribers()
					.environment( env )
					.mysql( mysqlMaster )
					.msisdnFixed( FIXED_MSISDN )
					.subscriberHasSMSChannel( HAS_SMS_CHANNEL )
					.subscriberHasMAILChannel( HAS_MAIL_CHANNEL )
					.insertIntoEnvironment( SUBSCRIBERS_TO_GENERATE );
		
	}
	
	@Test( enabled = GENERATE_INCREMENTAL_SUBSCRIBERS )
	public void generateIncrementalSubscribers() throws GeneratorException {
		
		final Long STARTED_MSISDN = 3399900201L;
		final Integer INCREMENT = 1;
		final Boolean HAS_SMS_CHANNEL = true;
		final Boolean HAS_MAIL_CHANNEL = true;
		final Long SUBSCRIBERS_TO_GENERATE = 100L;
				
		Generator.subscribers()
					.environment( env )
					.mysql( mysqlMaster )
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
					.mysql( mysqlMaster )
					.msisdnRandom( LEFT_MSISDN, RIGHT_MSISDN )
					.subscriberHasSMSChannel( HAS_SMS_CHANNEL )
					.subscriberHasMAILChannel( HAS_MAIL_CHANNEL )
					.insertIntoEnvironment( SUBSCRIBERS_TO_GENERATE );
		
	}
	
}
