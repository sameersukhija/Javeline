package com.lumata.e4o.webservices.response.types;

import org.hamcrest.Matcher;

import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseObject;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;

public class XMLRPCResponseToken extends XMLRPCResponseObject {
		
	private final String MSISDN_TAG_ = ".msisdn";
	private final String ID_TAG_ = ".id";
		
	public XMLRPCResponseToken() {}
	
	public XMLRPCResponseToken( String relativeRoot ) {
		super( relativeRoot + ".token" );
	}
	
	public XMLRPCResponseValidator msisdn( Matcher<Integer> matcher ) {
			
		return new XMLRPCResponseValidator( builPath( MSISDN_TAG_ ), matcher, Long.class, "token msisdn" );		
		
	}

	public XMLRPCResponseValidator id( Matcher<String> matcher ) {
		
		return new XMLRPCResponseValidator( builPath( ID_TAG_ ), matcher, Integer.class, "token id" );		
		
	}

}
