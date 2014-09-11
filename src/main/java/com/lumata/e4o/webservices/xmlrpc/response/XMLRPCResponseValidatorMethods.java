package com.lumata.e4o.webservices.xmlrpc.response;

import static org.hamcrest.Matchers.*;

import com.lumata.e4o.webservices.response.types.XMLRPCResponseFault;
import com.lumata.e4o.webservices.response.types.XMLRPCResponseOfferPack;

public class XMLRPCResponseValidatorMethods {

	Boolean value;
	
	XMLRPCResponseValidatorMethods( Boolean value ) { 
		this.value = value; 
	}

	public static XMLRPCResponseValidator success() {
		
		return new XMLRPCResponseValidator( "params.param.value.boolean", equalTo( 0 ), Integer.class, "success" );
		
	}
	
	public static XMLRPCResponseValidator successFault() {
		
		return new XMLRPCResponseValidator( "params.param.value.fault.code", equalTo( 0 ), Integer.class, "Success" );
		
	}
	
	public static XMLRPCResponseFault fault() {
		
		return new XMLRPCResponseFault();
		
	}
	
	public static XMLRPCResponseOfferPack offerPack() {
		
		return new XMLRPCResponseOfferPack();
		
	}
	
}
