package com.lumata.e4o.gui.xmlrpc.type;

public class XMLRPCComponent {

	Object component;
	
	XMLRPCComponent( Object component ) { 
		this.component = component; 
	}
	
	public static XMLRPCComponent xmlrpcBody( XMLRPCParam... params ) {
		
		StringBuilder xmlrpcBody = new StringBuilder(); 
		
		for( int p = 0; p < params.length; p++ ) {
			
			xmlrpcBody.append( params[ p ].value );
		
		}
		
		return new XMLRPCComponent( xmlrpcBody );
		
	}
	
}
