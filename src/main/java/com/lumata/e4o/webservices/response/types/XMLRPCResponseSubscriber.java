package com.lumata.e4o.webservices.response.types;

import org.hamcrest.Matcher;

import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseObject;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;

public class XMLRPCResponseSubscriber extends XMLRPCResponseObject {

	private final String MSISDN = ".msisdn";
	private final String SUBSCRIPTION_DATE = ".subscription_date";
	private final String PROFILE = ".profile";
	private final String RATEPLAN = ".rate_plan";
	private final String STATUS = ".status";
	private final String INTAG = ".in_tag";
	private final String IMSI = ".imsi";
	private final String IMEI = ".imei";
	
	public XMLRPCResponseSubscriber() {
		super( ".subscriber" );
	}
	
	public static XMLRPCResponseSubscriber subscriberResponse() {
		
		return new XMLRPCResponseSubscriber();
		
	}
	
	public XMLRPCResponseValidator msisdn(Matcher<Long> matcher) {
		
		return new XMLRPCResponseValidator(builPath(MSISDN), matcher, Long.class, "msisdn");

	}
	
	public XMLRPCResponseValidator subscriptionDate(Matcher<String> matcher) {
		
		return new XMLRPCResponseValidator(builPath(SUBSCRIPTION_DATE), matcher, String.class, "subscription_date");
	
	}
	
	public XMLRPCResponseValidator profile(Matcher<String> matcher) {
		
		return new XMLRPCResponseValidator(builPath(PROFILE), matcher, String.class, "profile");
	}
	
	public XMLRPCResponseValidator rate_plan(Matcher<String> matcher) {
		
		return new XMLRPCResponseValidator(builPath(RATEPLAN), matcher, String.class, "rate_plan");
	
	}
	
	public XMLRPCResponseValidator status(Matcher<String>matcher) {
		
		return new XMLRPCResponseValidator(builPath(STATUS), matcher, String.class, "status");
	
	}
	
	public XMLRPCResponseValidator inTag(Matcher<String>matcher) {
		
		return new XMLRPCResponseValidator(builPath(INTAG), matcher, String.class, "in_tag");
	
	}
	
	public XMLRPCResponseValidator imsi(Matcher<Long> matcher) {
		
		return new XMLRPCResponseValidator(builPath(IMSI), matcher, Long.class, "imsi");

	}
	
	public XMLRPCResponseValidator imei(Matcher<Long> matcher) {
		
		return new XMLRPCResponseValidator(builPath(IMEI), matcher, Long.class, "imei");

	}

}
