package com.lumata.common.testing.network;

import java.util.HashMap;
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
	
	private String url;
	private ClientRequest request;
	private Map<String, String> header;
	private Map<String, String> body;
	
	public enum Options { REQUEST_CONTENT_TYPE, REQUEST_BODY }
	
	public enum ParamType { HEADER, BODY }
	
	public enum CallType { GET, POST }
	
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
	
	public RestClient() {
		
		this( null );
	
	}
	
	public RestClient( String url ) {
		
		this.url = url;
		
		this.header = new HashMap<String, String>();
		
		this.body = new HashMap<String, String>();
			
	}

	public String getUrl() {
		return this.url;
	}

	public Map<String, String> getHeader() {
		return this.header;
	}

	public Map<String, String> getBody() {
		return this.header;
	}

	public ClientRequest getRequest() {
		return this.request;
	}
	
	public void setUrl( String url ) {
		this.url = url;
	}

	public void setRequest() {
		this.request = new ClientRequest( url );
	}

	public void setRequest( String url ) {
		
		setUrl( url );
		
		setRequest();
	
	}

	public void header( String key, String value ) throws Exception {
		
		if( null != value ) { this.header.put( key, value ); }
		
	}
	
	public void body( String key, String value ) throws Exception {
		
		if( null != value ) { this.body.put( key, value ); }
		
	}
	
	public void cleanHeader() throws Exception {
		
		this.header = new HashMap<String, String>();
		
	}
	
	public void cleanBody() throws Exception {
		
		this.body = new HashMap<String, String>();
		
	}
		
	public void cleanHeaderKey( String key ) throws Exception {
		
		if( this.header.containsKey( key ) ) { this.header.remove( key ); }
		
	}
	
	public void cleanBodyKey( String key ) throws Exception {
		
		if( this.body.containsKey( key ) ) { this.body.remove( key ); }
		
	}
	
	private void setCallParameters() {
		
		for( String headerKey : header.keySet() ) {
			
			this.request.header( headerKey, header.get( headerKey ) ); 
			
		}
		
		for( String bodyKey : body.keySet() ) {
			
			this.request.header( bodyKey, body.get( bodyKey ) ); 
			
		}
		
	}
	
	private ClientResponse<String> call( CallType callType, String url ) throws Exception {
		
		ClientResponse<String> response = null;
		
		if( null != url ) {
			setRequest( url );
		} else {
			setRequest();
		}
		
		request.followRedirects( true );
		
		setCallParameters();
		
		switch( callType ) {
		
			case GET: {
				response = request.get(String.class);
				break;
			}
			case POST: {
				response = request.post(String.class);	
				break;
			}

		}
		
		return response;
		
	}
	
	public ClientResponse<String> get() throws Exception {
		
		return call( CallType.GET, null );
		
	}
	
	public ClientResponse<String> get( String url ) throws Exception {
		
		return call( CallType.GET, url );
		
	}
	
	public ClientResponse<String> post( String url, Map<String, Object> options ) {
		
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
