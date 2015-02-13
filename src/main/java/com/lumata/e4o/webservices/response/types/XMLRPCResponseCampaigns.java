package com.lumata.e4o.webservices.response.types;

import org.hamcrest.Matcher;

import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseObject;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;


public class XMLRPCResponseCampaigns extends XMLRPCResponseObject {
	
	private final String CAMPAIGN_TAG_ = ".campaignDetails";
	private final String SIZE_TAG_ = CAMPAIGN_TAG_ + ".size()";
		
	public XMLRPCResponseCampaigns() {
		super( ".campaigns" );
	}

	public XMLRPCResponseValidator size( Matcher<String> matcher ) {
		
		return new XMLRPCResponseValidator( builPath( SIZE_TAG_ ), matcher, Integer.class, "campaigns size" );
		
	}

}
