package com.lumata.unit.webservices.xmlrpc;

import org.testng.annotations.Test;

import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;


public class XMLRPCRequest_Offeroptimizer_Allocate extends ParentTestCase {
	
	@Test(enabled=TEST_ENABLED, priority = 1 )
	public void callXMLRPCCRequest() throws Exception {
		
		final String msisdn = "3399900001";
		final String token_code = "9YTJY";
		
		XMLRPCRequest.offeroptimizer_allocate().call( 	
			guiServer, 
			xmlrpcBody(
				authentication( user ),
				string( msisdn ),
				string( token_code )
			),
			xmlrpcOptions(
				storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
				storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
			)
		);
		
	}
	
}
