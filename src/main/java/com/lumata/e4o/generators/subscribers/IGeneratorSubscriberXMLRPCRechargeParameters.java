package com.lumata.e4o.generators.subscribers;

import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;

public interface IGeneratorSubscriberXMLRPCRechargeParameters {

	public IGeneratorSubscriberParameters server( final Server server );
	
	public IGeneratorSubscriberParameters user( final User user );
	
}
