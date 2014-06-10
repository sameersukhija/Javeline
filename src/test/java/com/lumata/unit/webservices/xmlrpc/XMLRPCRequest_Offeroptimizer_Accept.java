package com.lumata.unit.webservices.xmlrpc;

import org.jboss.resteasy.client.ClientResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.gui.xmlrpc.type.XMLRPCRequest;

import static com.lumata.e4o.gui.xmlrpc.type.XMLRPCParam.*;
import static com.lumata.e4o.gui.xmlrpc.type.XMLRPCComponent.*;

public class XMLRPCRequest_Offeroptimizer_Accept {
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		actruleServer = env.getServer( gui_server );
		
		superman = actruleServer.getUser( user );
		
	}
	
	@Test(enabled=true, priority = 1 )
	public void callXMLRPCCRequest() throws Exception {
		
		final String msisdn = "3399900007";
		final String token_code = "gl-0b3b5";
		final Object[] offer_id = new Integer[]{ 1005 };		
		
		ClientResponse<String> response = XMLRPCRequest.offeroptimizer_accept.call( 	actruleServer, 
																						xmlrpcBody(
																							authentication( superman.getUsername(), superman.getPassword() ),
																							string( msisdn ),
																							string( token_code ),
																							arrayInt( offer_id )
																						)
																			);
		
		System.out.println( response.getEntity().toString() );
		
	}
	
}
