package com.lumata.e4o.testplan.functional.tokens;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOToken;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;

public class XLRPCRequest_OfferOptimizer_Allocate_Randomly {
	
	private static final Logger logger = LoggerFactory.getLogger( XLRPCRequest_OfferOptimizer_Allocate_Randomly.class );
	
	public enum ExtendedParameters {
		tongue, gender, salary, imei, imsi, hobbies 
	}
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
	Mysql mysql;
	DAOToken daoToken;
	DAOSubscribers daoSubscribers;
	
	/** VALID PARAMETERS */
	/** xmlrpc valid parameters */
	String msisdn;
	String token;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		actruleServer = env.getServer( gui_server );
		
		superman = actruleServer.getUser( user );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
		daoSubscribers = DAOSubscribers.getInstance( mysql );
		
		daoToken = DAOToken.getInstance( mysql );
			
	}

	@Test(enabled=true, priority = 1 )
	public void allocateActiveTokenRandomicaly() throws Exception {
		
		final Integer numberOfAllocations = 50;
		
		ArrayList<Subscribers> subscribers = DAOSubscribers.getInstance( mysql ).getSubscriberList();
		
		for( int allocation = 1; allocation <= numberOfAllocations; allocation++ ) {
		
			int subscriberIndex = (int)(Math.random() * subscribers.size() );
			
			Subscribers subscriber = subscribers.get( subscriberIndex );
			
			if( null != subscriber && null != subscriber.getMsisdn() ) { 
								
				ArrayList<Token> token = DAOToken.getInstance( mysql ).getAvailableActiveTokens( subscriber.getMsisdn() );
				
				if( null != token && token.size() > 0 ) {
				
					XMLRPCRequest.offeroptimizer_allocate().call( 
										actruleServer, 
										xmlrpcBody(
											authentication( superman ),
											string( subscriber.getMsisdn() ),
											string( token.get( 0 ).getTokenCode() )
										),
										xmlrpcValidator(
											//success()
										),
										xmlrpcOptions(
											sleep( 100L )	
										)
					
					);
								
				}
				
			}
		
		}
		
	}

}
