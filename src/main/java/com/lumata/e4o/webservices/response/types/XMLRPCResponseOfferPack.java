package com.lumata.e4o.webservices.response.types;

import org.hamcrest.Matcher;

import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseObject;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;

public class XMLRPCResponseOfferPack extends XMLRPCResponseObject {
	
	private final String ID_TAG_ = ".id";
		
	public XMLRPCResponseOfferPack() {
		super( ".offerPack" );
	}

	public XMLRPCResponseValidator id( Matcher<Integer> matcher ) {
		
		return new XMLRPCResponseValidator( builPath( ID_TAG_ ), matcher, Integer.class, "offerPack id" );		
		
	}

	public XMLRPCResponseToken token( Matcher<Integer> matcher ) {
			
		return new XMLRPCResponseToken( getRelativeRoot() );		
		
	}

}
