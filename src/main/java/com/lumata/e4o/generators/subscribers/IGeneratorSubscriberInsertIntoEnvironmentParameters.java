package com.lumata.e4o.generators.subscribers;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.GeneratorException;

public interface IGeneratorSubscriberInsertIntoEnvironmentParameters {

	public IGeneratorSubscriberInsertIntoEnvironmentParameters environment( final NetworkEnvironment env );
	
	public IGeneratorSubscriberInsertIntoEnvironmentParameters mysql( final Mysql mysql );
	
	public IGeneratorSubscriberInsertIntoEnvironmentParameters server( final Server server );
	
	public IGeneratorSubscriberInsertIntoEnvironmentParameters user( final User user );
	
	public IGeneratorSubscriberInsertIntoEnvironmentParameters msisdnFixed( final Long msisdn );
	
	public IGeneratorSubscriberInsertIntoEnvironmentParameters msisdnIncremental( final Long msisdn, final Integer increment );
	
	public IGeneratorSubscriberInsertIntoEnvironmentParameters msisdnRandom( final Long leftMsisdn, final Long rightMsisdn );
	
	public IGeneratorSubscriberInsertIntoEnvironmentParameters subscriberHasSMSChannel( final Boolean hasChannel );
	
	public IGeneratorSubscriberInsertIntoEnvironmentParameters subscriberHasMAILChannel( final Boolean hasChannel );
	
	public IGeneratorSubscriberInsertIntoEnvironmentParameters minRandomEvents( final Integer minEvents );
	
	public IGeneratorSubscriberInsertIntoEnvironmentParameters maxRandomEvents( final Integer maxEvents );
	
	public void insertIntoEnvironment( Long qtySubscribers ) throws GeneratorException;
	
	public void xmlrpcRecharge() throws GeneratorException;
	
	public void xmlrpcRecharge( final Long qtySubscribers ) throws GeneratorException;
	
}
