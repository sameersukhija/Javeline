package com.lumata.e4o.webservices.xmlrpc.request;

import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;

public class XMLRPCComponent {

	public enum ComponentType {
		xmlrpcBody, xmlrpcValidator, xmlrpcOption
	}
	
	private Object[] componentValues;	
	
	private ComponentType componentType;
		
	
	XMLRPCComponent( ComponentType componentType, Object... componentValues ) { 
		
		this.componentType = componentType;
		
		this.componentValues = componentValues; 
	
	}
	
	public static XMLRPCComponent xmlrpcBody( XMLRPCRequestMethods... params ) {
		
		StringBuilder xmlrpcBody = new StringBuilder(); 
		
		for( int p = 0; p < params.length; p++ ) {
			
			xmlrpcBody.append( params[ p ].value );
		
		}
		
		return new XMLRPCComponent( ComponentType.xmlrpcBody, xmlrpcBody.toString() );
		
	}
	
	@SuppressWarnings("all")
	public static XMLRPCComponent xmlrpcValidator( XMLRPCResponseValidator... validators ) {
			
		return new XMLRPCComponent( ComponentType.xmlrpcValidator, validators );
		
	}
	
	public static XMLRPCComponent xmlrpcOptions( Object... options ) {
						
		return new XMLRPCComponent( ComponentType.xmlrpcOption, options );
		
	}
	
	public ComponentType getComponentType() {
		return this.componentType;
	}
	
	public Object[] getComponentValues() {
		return this.componentValues;
	}
		
}
