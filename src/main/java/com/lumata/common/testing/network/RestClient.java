package com.lumata.common.testing.network;

import java.util.Map;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class RestClient {

	private static final  Logger logger = LoggerFactory.getLogger( RestClient.class );
	
	public enum Options { REQUEST_CONTENT_TYPE, REQUEST_BODY }
	public enum ContentTypes { 
		
		APPLICATION_JSON("application/json");
		
		public String getValue() {
			
			return this.value;
			
		}
		
		private String value;
		
		private ContentTypes( String value ) {
			
			this.value = value;
			
		}
		
	}
	
	private RestClient() {}
	
	public static ClientResponse<String> get( String url ) {
		
		ClientResponse<String> response = null;
		
		try {
			
			ClientRequest request = new ClientRequest(url);
			request.followRedirects(true);
			response = request.get(String.class);			
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
		return response;
		
	}
	
	public static ClientResponse<String> post( String url, Map<String, Object> options ) {
		
		ClientResponse<String> response = null;
		
		try {
			
			ClientRequest request = new ClientRequest(url);
			request.followRedirects(true);
			
			if( options != null && options.containsKey( RestClient.Options.REQUEST_BODY.name() ) && options.containsKey( RestClient.Options.REQUEST_CONTENT_TYPE.name() ) ) { 
				
				request.body( (String)options.get( RestClient.Options.REQUEST_CONTENT_TYPE.name() ), options.get( RestClient.Options.REQUEST_BODY.name() ) );
				
			};
			
			response = request.post(String.class);			
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
		return response;
		
	}
	
}
