package com.lumata.e4o.webservices.response.types;

import org.hamcrest.Matcher;

import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;

public class XMLRPCResponseFault {
	
	private String root = "params.param.value.fault";
	
	public XMLRPCResponseFault() {}
	
	public XMLRPCResponseValidator code( Matcher<Integer> matcher ) {
			
		return new XMLRPCResponseValidator( root + ".code", matcher, Integer.class, "fault code" );		
		
	}

	public XMLRPCResponseValidator message( Matcher<String> matcher ) {
		
		return new XMLRPCResponseValidator( root + ".message", matcher, String.class, "fault message" );		
		
	}

}
