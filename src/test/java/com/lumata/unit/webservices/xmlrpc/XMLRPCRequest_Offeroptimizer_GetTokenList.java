package com.lumata.unit.webservices.xmlrpc;

import org.testng.annotations.Test;

import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;

public class XMLRPCRequest_Offeroptimizer_GetTokenList extends ParentTestCase {
	
	@Test(enabled=true, priority = 1 )
	public void callXMLRPCCRequest() throws Exception {
		
		final String msisdn = "1000100";
		
		XMLRPCRequest.offeroptimizer_getTokensList().call( 	
			guiServer, 
			xmlrpcBody(
				authentication( user ),
				string( msisdn ),
				string(""),
				string(""),
				arrayString("consumed"),
				string("campaign"),
				bool(true)
			),
			xmlrpcOptions(
				storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
				storeResponseAsResource( "xmlrpc/response/", "response.xml" )
			)
		);
				
	}
	
}
