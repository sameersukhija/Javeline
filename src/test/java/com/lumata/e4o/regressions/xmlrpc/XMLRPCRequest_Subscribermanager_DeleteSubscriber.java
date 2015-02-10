package com.lumata.e4o.regressions.xmlrpc;

import static org.hamcrest.Matchers.*;

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
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;

public class XMLRPCRequest_Subscribermanager_DeleteSubscriber {
	
	final boolean TEST_ENABLED = true;
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
	Mysql mysql;
	DAOSubscribers daoSubscribers;
			
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
		
	}

	@Test(enabled=TEST_ENABLED, priority = 1 )
	public void deleteSubscriberWidthMsisdnNull() throws Exception {
	
		Long msisdn = null;
		
		XMLRPCRequest.subscribermanager_deleteSubscriber().call( 
				actruleServer, 
				xmlrpcBody(
					authentication( superman ),
					subscriber( 
							msisdn
					)
				),
				xmlrpcValidator(
					fault().code( equalTo( 100 ) ),
					fault().message( equalTo( "subscriber not found with msisdn " + msisdn ) )
				),
				xmlrpcOptions(
					sleep( 100L )	
				)
		);
		
	}
	
	@Test(enabled=TEST_ENABLED, priority = 2 )
	public void deleteSubscriberWidthNotExistingMsisdn() throws Exception {
	
		Long msisdn = 0L;
		
		if( !daoSubscribers.isSubscriber( msisdn ) ) {
		
			XMLRPCRequest.subscribermanager_deleteSubscriber().call( 
					actruleServer, 
					xmlrpcBody(
						authentication( superman ),
						subscriber( 
								msisdn
						)
					),
					xmlrpcValidator(
						fault().code( equalTo( 100 ) ),
						fault().message( equalTo( "subscriber not found with msisdn " + msisdn ) )
					),
					xmlrpcOptions(
						sleep( 100L )	
					)
			);
		
		}
		
	}

	@Test(enabled=TEST_ENABLED, priority = 3 )
	public void deleteSubscriberWidthExistingMsisdn() throws Exception {
	
		Subscribers subscriber = daoSubscribers.getAvailableSubscriber();
		
		if( null != subscriber ) {
		
			XMLRPCRequest.subscribermanager_deleteSubscriber().call( 
					actruleServer, 
					xmlrpcBody(
						authentication( superman ),
						subscriber( 
							subscriber.getMsisdn()
						)
					),
					xmlrpcValidator(
						success()
					),
					xmlrpcOptions(
						sleep( 100L )	
					)
			);
		
		}
		
	}
	
}
