package com.lumata.e4o.generators.subscribers;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter;

public interface IGeneratorSubscriberParameters {

	public IGeneratorSubscriberParameters environment( final NetworkEnvironment env );
	
	public IGeneratorSubscriberParameters mysql( final Mysql mysql );
	
	public IGeneratorSubscriberParameters server( final Server server );
	
	public IGeneratorSubscriberParameters user( final User user );
	
	public IGeneratorSubscriberParameters msisdnFixed( final Long msisdn );
	
	public IGeneratorSubscriberParameters msisdnIncremental( final Long msisdn, final Integer increment );
	
	public IGeneratorSubscriberParameters msisdnRandom( final Long leftMsisdn, final Long rightMsisdn );
	
	public IGeneratorSubscriberParameters subscriberHasSMSChannel( final Boolean hasChannel );
	
	public IGeneratorSubscriberParameters subscriberHasMAILChannel( final Boolean hasChannel );
	
	public IGeneratorSubscriberParameters minRandomEvents( final Integer minEvents );
	
	public IGeneratorSubscriberParameters maxRandomEvents( final Integer maxEvents );
	
	public IGeneratorSubscriberParameters repeat( final Integer repeat );
	
	public void insertIntoEnvironment( Long qtySubscribers ) throws GeneratorException;
	
	public void xmlrpcRecharge() throws GeneratorException;
	
	public void xmlrpcRecharge( final Long qtySubscribers ) throws GeneratorException;
	
	public void xmlrpcRecharge( final Long qtyRecharges, XMLRPCParameter... parameterList ) throws GeneratorException;
	
}
