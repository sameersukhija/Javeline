package com.lumata.unit.webservices.xmlrpc;

import org.testng.annotations.Test;

import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.storeResponseAsResource;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;

public class XMLRPCRequest_Offeroptimizer_RefuseAll extends ParentTestCase {
	
	@Test(enabled=true, priority = 1 )
	public void callXMLRPCCRequest() throws Exception {
		
		final String msisdn = "3399900001";
		final String token_code = "T6GQA";
		final String userAcceptChannel = "web";
		
		XMLRPCRequest.offeroptimizer_refuseAll().call( 	
			guiServer, 
			xmlrpcBody(
				authentication( user ),
				string( msisdn ),
				string( token_code ),
				string( userAcceptChannel )
			),
			xmlrpcOptions(
				storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
			)
		);
		
	}
	
}
