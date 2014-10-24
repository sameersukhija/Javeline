package com.lumata.e4o.generators.subscribers;

import java.util.Calendar;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.FieldException;
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
	
	public void insertIntoEnvironment( final Long qtySubscribers ) throws GeneratorException;
	
	public void insertDefaultHobbies() throws GeneratorException;
	
	public void insertHobbies( final String[] hobbies ) throws GeneratorException;
	
	public void insertOptions( final String prefix, final Long qtyOptions ) throws GeneratorException;
	
	public void xmlrpcAllTokenAllocation() throws GeneratorException, NumberFormatException, FieldException;
	
	public void xmlrpcAllTokenAllocation( Calendar event_date ) throws GeneratorException, NumberFormatException, FieldException;
	
	public void xmlrpcRandomTokenAccepting() throws GeneratorException, NumberFormatException, FieldException;
	
	//public void xmlrpcRandomTokenAccepting( Calendar event_date ) throws GeneratorException, NumberFormatException, FieldException;
	
	public void xmlrpcRandomTokenRefusing() throws GeneratorException, NumberFormatException, FieldException;
	
	public void xmlrpcRecharge() throws GeneratorException;
	
	public void xmlrpcRecharge( final Long qtySubscribers ) throws GeneratorException;
	
	public void xmlrpcRecharge( final Long qtyRecharges, XMLRPCParameter... parameterList ) throws GeneratorException;
	
}
