package com.lumata.e4o.generators;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.exceptions.GeneratorException;

public interface IGeneratorSubscriberParameters {

	public IGeneratorSubscriberParameters environment( NetworkEnvironment env );
	
	public IGeneratorSubscriberParameters mysql( Mysql mysql );
	
	public IGeneratorSubscriberParameters msisdnFixed( Long msisdn );
	
	public IGeneratorSubscriberParameters msisdnIncremental( Long msisdn, Integer increment );
	
	public IGeneratorSubscriberParameters msisdnRandom( Long leftMsisdn, Long rightMsisdn );
	
	public IGeneratorSubscriberParameters subscriberHasSMSChannel( Boolean hasChannel );
	
	public IGeneratorSubscriberParameters subscriberHasMAILChannel( Boolean hasChannel );
	
	public void insertIntoEnvironment( Long qtySubscribers ) throws GeneratorException;
	
}
