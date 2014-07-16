package com.lumata.e4o.webservices.response.types;

import java.util.List;

import org.hamcrest.Matcher;

import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseObject;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;

public class XMLRPCResponseOffers extends XMLRPCResponseObject {
	
	private final String SIZE_TAG_ = ".offer.size()";
		
	public XMLRPCResponseOffers() {
		super( ".offers" );
	}

	public void offer() {
				
		//".offer.findAll {}"
		
	}
	
	public XMLRPCResponseValidator size( Matcher<String> matcher ) {
		
		return new XMLRPCResponseValidator( builPath( SIZE_TAG_ ), matcher, Integer.class, "offers size" );
		
	}
	
	/*
	public XMLRPCResponseValidator id( Matcher<Integer> matcher ) {
		
		return new XMLRPCResponseValidator( builPath( ID_TAG_ ), matcher, Integer.class, "offerPack id" );		
		
	}

	public XMLRPCResponseToken token( Matcher<Integer> matcher ) {
			
		return new XMLRPCResponseToken( getRelativeRoot() );		
		
	}
	*/
}
