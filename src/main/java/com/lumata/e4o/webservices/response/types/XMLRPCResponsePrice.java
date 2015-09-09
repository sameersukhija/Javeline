package com.lumata.e4o.webservices.response.types;

import org.hamcrest.Matcher;

import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseObject;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;

public class XMLRPCResponsePrice extends XMLRPCResponseObject {
		
	private final String PRICE_TAG_ = ".price";
	private final String CURRENCY_TAG_ = ".currency";
		
	public XMLRPCResponsePrice() {}
	
	public XMLRPCResponsePrice( String relativeRoot ) {
		super( relativeRoot + ".price" );
	}
	
	public XMLRPCResponseValidator price( Matcher<Integer> matcher ) {
			
		return new XMLRPCResponseValidator( buildPath( PRICE_TAG_ ), matcher, String.class, "price name" );		
		
	}

	public XMLRPCResponseValidator currency( Matcher<String> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( CURRENCY_TAG_ ), matcher, String.class, "price currency" );		
		
	}

}
