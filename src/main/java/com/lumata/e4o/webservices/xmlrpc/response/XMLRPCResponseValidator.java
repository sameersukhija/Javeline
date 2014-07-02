package com.lumata.e4o.webservices.xmlrpc.response;

import org.hamcrest.Matcher;

public class XMLRPCResponseValidator {
		
	private String path;
	private Matcher<?> matcher;
	private Class<?> matcherArgumentType;
	private String tag;

	public XMLRPCResponseValidator( String path, Matcher<?> matcher, Class<?> matcherArgumentType ) {
		
		this( path, matcher, matcherArgumentType, null );
		
	}
	
	public XMLRPCResponseValidator( String path, Matcher<?> matcher, Class<?> matcherArgumentType, String tag ) {
		
		this.path = path;
		
		this.matcher = matcher;
		
		this.matcherArgumentType = matcherArgumentType;
		
		this.tag = tag;
		
	}
	
	public String getPath() {
		return path;
	}

	public Matcher<?> getMatcher() {
		return matcher;
	}
	
	public Class<?> getMatcherArgumentType() {
		return matcherArgumentType;
	}
	
	public String getTag() {
		return ( null != tag ? "\"" + tag + "\"" : "" );
	}


}
