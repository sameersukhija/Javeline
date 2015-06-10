package com.lumata.unit.webservices.xmlrpc;

import org.testng.annotations.Test;

import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.storeRequestAsResource;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.storeResponseAsResource;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;

public class XMLRPCRequest_Offeroptimizer_Accept extends ParentTestCase {
	
	@Test(enabled=true, priority = 1 )
	public void callXMLRPCCRequest() throws Exception {
		
		final String msisdn = "3399900001";
		final String token_code = "WQG67";
		final Object[] offer_id = new Integer[]{ 1000 };		
		final String userAcceptChannel = "web";
		
		XMLRPCRequest.offeroptimizer_accept().call( 	
			guiServer, 
			xmlrpcBody(
				authentication( user ),
				string( msisdn ),
				string( token_code ),
				arrayInt( offer_id ),
				string( userAcceptChannel )
			),
			xmlrpcOptions(
				storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
				storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
			)
		);
		
		
	}
	
}
