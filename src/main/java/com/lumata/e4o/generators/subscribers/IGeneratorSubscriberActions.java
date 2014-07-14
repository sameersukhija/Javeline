package com.lumata.e4o.generators.subscribers;

import com.lumata.e4o.exceptions.GeneratorException;

public interface IGeneratorSubscriberActions {

	public void insertIntoEnvironment( Long qtySubscribers ) throws GeneratorException;
	
	public void xmlrpcRecharge() throws GeneratorException;
	
	public void xmlrpcRecharge( final Long qtySubscribers ) throws GeneratorException;
	
}
