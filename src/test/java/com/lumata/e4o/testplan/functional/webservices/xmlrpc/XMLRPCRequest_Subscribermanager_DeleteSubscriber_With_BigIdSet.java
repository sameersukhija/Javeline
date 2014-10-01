package com.lumata.e4o.testplan.functional.webservices.xmlrpc;

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

public class XMLRPCRequest_Subscribermanager_DeleteSubscriber_With_BigIdSet {
	
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

	@Test(enabled=true, priority = 3 )
	public void deleteSubscriberWidthExistingMsisdn() throws Exception {
	
		Subscribers subscriber = daoSubscribers.getAvailableSubscriber();
		
		subscriber.setMsisdn( 3931975305L );
		
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
