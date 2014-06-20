package com.lumata.e4o.gui.xmlrpc.type;

public class XMLRPCComponent {

	public enum ComponentType {
		xmlrpcBody, xmlrpcValidator, xmlrpcOption
	}
	
	Object[] componentValues;	
	
	ComponentType componentType;
		
	
	XMLRPCComponent( ComponentType componentType, Object... componentValues ) { 
		
		this.componentType = componentType;
		
		this.componentValues = componentValues; 
	
	}
	
	public static XMLRPCComponent xmlrpcBody( XMLRPCParam... params ) {
		
		StringBuilder xmlrpcBody = new StringBuilder(); 
		
		for( int p = 0; p < params.length; p++ ) {
			
			xmlrpcBody.append( params[ p ].value );
		
		}
		
		return new XMLRPCComponent( ComponentType.xmlrpcBody, xmlrpcBody.toString() );
		
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
