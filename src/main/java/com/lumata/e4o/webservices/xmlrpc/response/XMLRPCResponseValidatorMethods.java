package com.lumata.e4o.webservices.xmlrpc.response;

import static org.hamcrest.Matchers.*;

import com.lumata.e4o.webservices.response.types.XMLRPCResponseFault;

public class XMLRPCResponseValidatorMethods {

	Boolean value;
	
	XMLRPCResponseValidatorMethods( Boolean value ) { 
		this.value = value; 
	}

	public static XMLRPCResponseValidator success() {
		
		return new XMLRPCResponseValidator( "params.param.value.boolean", equalTo( 0 ), Integer.class, "success" );
		
	}
	
	public static XMLRPCResponseFault fault() {
		
		return new XMLRPCResponseFault();
		
	}
	
}
