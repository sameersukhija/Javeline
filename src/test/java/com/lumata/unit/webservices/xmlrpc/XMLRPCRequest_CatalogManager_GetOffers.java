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

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.sleep;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.storeRequestAsResource;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.storeResponseAsResource;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCOfferCriteriaItem.*;

public class XMLRPCRequest_CatalogManager_GetOffers {
	
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
	
	@Test(enabled=false, priority = 1 )
	public void callBasicXMLRPCCatalogmanagerGetOffers() throws Exception {
				
		XMLRPCRequest.catalogmanager_getOffers().call( 
			actruleServer, 
			xmlrpcBody(
				authentication( superman )
			),
			xmlrpcOptions(
				sleep( 100L ),
				storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
				storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
			)
		);
		
	}

	@Test(enabled=true, priority = 2 )
	public void callXMLRPCCatalogmanagerGetOffers() throws Exception {
				
		XMLRPCRequest.catalogmanager_getOffers().call( 
			actruleServer, 
			xmlrpcBody(
				authentication( superman ),
				offerParameters( 
					"3399900001",
					offerCriteriaItem( "Bonus_1_balance", "&lt;", "10" )
				)
			),
			xmlrpcOptions(
				sleep( 100L ),
				storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
				storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
			)
		);
		
	}

}
