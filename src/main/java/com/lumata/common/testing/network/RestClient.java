package com.lumata.common.testing.network;

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
	
	public static ClientResponse<String> get( String url) {
		
		ClientResponse<String> response = null;
		
		try {
			
			ClientRequest request = new ClientRequest(url);
			request.followRedirects(true);
			response = request.get(String.class);			
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage() );
		
		}
		
		return response;
		
	}
	
}
