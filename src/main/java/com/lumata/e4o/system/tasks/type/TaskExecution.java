package com.lumata.e4o.system.tasks.type;

import com.lumata.common.testing.system.Server;

public enum TaskExecution {

	ExpiredData,
	AggregateData;
	
	
	public Boolean execute( Server server, Object parameters ) throws Exception {
		
		
		
		return false;
			
	};
	
	
	
	/*
	public ClientResponse<String> execute( Server server, Object parameters ) throws Exception {
		
		
		
		return null;
			
	};

	public ClientResponse<String> execute( Server server, Object parameters ) throws Exception {
		
		
		
		return null;
			
	};
	
	
	/*
	public ClientResponse<String> call( Server server, XMLRPCComponent... xmlrpcComponents ) throws Exception {
		
		String url = server.getLink() + "xmlrpc/";
		System.out.println( url );
		RestClient restClient = new RestClient( url );
		
		parseComponents( restClient, xmlrpcComponents );
		
		ClientResponse<String> response = restClient.post();
				
		return response;
		
	};
	
	private String body( String xmlrpcBody ) {
		
		StringBuilder body = new StringBuilder();
		
		body.append( "<?xml version=\"1.0\"?><methodCall><methodName>" )
			.append( this.name().replace( "_", "." ) )
			.append( "</methodName><params>" )
			.append( xmlrpcBody )
			.append( "</params></methodCall>" );
		
		return body.toString();
		
	}
	
	private void parseComponents( RestClient restClient, XMLRPCComponent... xmlrpcComponents ) {
		
		try {
			
			for( int c = 0; c < xmlrpcComponents.length; c++ ) {
				
				Object[] componentValues = xmlrpcComponents[ c ].getComponentValues();
								
				switch( xmlrpcComponents[ c ].getComponentType() ) {
				
					case xmlrpcBody: {
						
						restClient.body( RestClient.ContentTypes.APPLICATION_JSON.getValue(), body( ( null != componentValues && componentValues.length > 0 ? (String)componentValues[ 0 ] : "" ) ) );
						
						break;
						
					}
					case xmlrpcOption: {
						
						parseOptions( componentValues );
												
						break;
						
					}
					default: { break; }
					
				}
				
			}
			
		} catch( Exception e ) {
			
			System.out.println( e.getMessage() );
		
		}
		
	}
	
	private void parseOptions( Object... options ) {
		
		for( int opt = 0; opt < options.length; opt++ ) {
						
			XMLRPCOption option = (XMLRPCOption)options[ opt ];
			
			try {
				
				switch( option.getOptionType() ) {
				
					case sleep: {
						
						sleep( (Long)option.getOptionValue() );
						
						break;
					
					}
					default: break;
					
				}
				
			} catch ( Exception e ) {
				
				System.out.println( e.getMessage() );
			
			}
			
		}
		
	}
	
	private void sleep( Long time ) {
		
		try {
			
			Thread.sleep( time );
			
		} catch(  InterruptedException e ) {}
			
	}
	*/
}
