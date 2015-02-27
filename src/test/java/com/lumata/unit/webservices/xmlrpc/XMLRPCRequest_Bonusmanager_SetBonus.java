package com.lumata.unit.webservices.xmlrpc;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;


//import static org.hamcrest.Matchers.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
//import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;


public class XMLRPCRequest_Bonusmanager_SetBonus {
	
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
		
		final String msisdn = "3399900001";
		final String bonus_name = "Points";
		final String number = "10";
		final String origin = "internal";
		final String relation_name = "account";
		
		XMLRPCRequest.bonusmanager_setBonus().call( 	
			actruleServer, 
			xmlrpcBody(
				authentication( superman ),
				string( msisdn ),
				string( bonus_name ),
				string( number ),
				string( origin ),
				string( relation_name )
			),
			xmlrpcOptions(
				storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
				storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
			)
		);
		
	}
	
}
