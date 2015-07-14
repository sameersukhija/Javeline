package com.lumata.e4o.webservices.response.types;

import java.util.ArrayList;
import java.util.Date;

import org.hamcrest.Matcher;

import com.lumata.e4o.gui.xmlrpc.XMLRPCSubscriber;
import com.lumata.e4o.schema.xmlrpc.XMLRPCOffer;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseObject;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;

public class XMLRPCResponseSubscriber extends XMLRPCResponseObject {

	private final String MSISDN = ".msisdn";
	private final String SUBSCRIPTION_DATE = ".subscription_date";
	private final String PROFILE = ".profile";
	private final String RATEPLAN = ".rate_plan";
	private final String STATUS = ".status";
	private final String INTAG = ".in_tag";

	public XMLRPCResponseValidator msisdn(Matcher<String> matcher) {
		return new XMLRPCResponseValidator(builPath(MSISDN), matcher,
				Integer.class, "msisdn");

	}
	
	public XMLRPCResponseValidator subscriptionDate(Matcher<String> matcher)
	{
		return new XMLRPCResponseValidator(builPath(SUBSCRIPTION_DATE), matcher,
				String.class, "subscription_date");
	}
	
	
	public XMLRPCResponseValidator profile(Matcher<String> matcher)
	{
		return new XMLRPCResponseValidator(builPath(PROFILE), matcher,
				String.class, "profile");
	}
	
	
	public XMLRPCResponseValidator rate_plan(Matcher<String> matcher)
	{
		return new XMLRPCResponseValidator(builPath(RATEPLAN), matcher,
				String.class, "rate_plan");
	}
	
	public XMLRPCResponseValidator status(Matcher<String>matcher)
	{
		return new XMLRPCResponseValidator(builPath(STATUS),matcher,String.class,"status");
	}
	
	public XMLRPCResponseValidator inTag(Matcher<String>matcher)
	{
		return new XMLRPCResponseValidator(builPath(INTAG),matcher,String.class,"in_tag");
	}
	
	
	

}
