package com.lumata.expression.operators.gui.xmlrpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.network.RestClient;


public class HTTPXMLRPCForm {

	private static final Logger logger = LoggerFactory.getLogger( HTTPXMLRPCForm.class );
	
	public enum ParamsType {
		
		authentication,
		msisdn,
		token;
		
	}
	
	public enum CallTypes {
		
		eventmanager_generateCustomEvent {
			
			public String getName() { return "eventmanager.generateCustomEvent"; }
			
		},
		offeroptimizer_allocate {
			
			public String getName() { return "offeroptimizer.allocate"; }
			
		},
		offeroptimizer_accept {
			
			public String getName() { return "offeroptimizer.accept"; }
			
		},
		offeroptimizer_refuseAll {
			
			public String getName() { return "offeroptimizer.refuseAll"; }
			
		};
		
		public abstract String getName();
		
		public ClientResponse<String> call( String url, ArrayList<String> params ) {
			
			Map<String, Object> options = new HashMap<String, Object>();
			options.put( RestClient.Options.REQUEST_CONTENT_TYPE.name(), RestClient.ContentTypes.APPLICATION_JSON.getValue() );
			options.put( RestClient.Options.REQUEST_BODY.name(), HTTPXMLRPCForm.getPOSTBody( this.getName(), params ) );
			
			ClientResponse<String> response = RestClient.post( url , options );
			
			return response;
			
		}
		
	}
	
	public enum EventTypes {
		
		revenue
		
	}
	
	public enum EventParameterTypes {
		
		recharge
		
	}
	
	public static String getPOSTBody( String call, ArrayList<String> params ) {
		
		StringBuilder postBody = new StringBuilder();
		StringBuilder paramsBody = new StringBuilder();
		
		for( int i = 0; i < params.size(); i++ ) {
			
			paramsBody.append( params.get( i ) );
			
		}
			
			
		postBody.append( "<methodCall><methodName>" ).append( call ).append( "</methodName><params>" )
				.append( paramsBody.toString() )
				.append( "</params></methodCall>" );
				
		return postBody.toString();
		
	}
	
	public static String getAuthenticationParam( String user, String password ) {
		
		StringBuilder authenticationPOSTBody = new StringBuilder();
		
		authenticationPOSTBody.append("<param><value><authentication>")
								.append("<login>").append( user ).append("</login>")
								.append("<password>").append( password ).append("</password>")
								.append("</authentication></value></param>");
		
		return 	authenticationPOSTBody.toString();
		
	}
	
	public static String getStringParam( String value ) {
		
		StringBuilder valuePOSTBody = new StringBuilder();
		
		valuePOSTBody.append( "<param><value><string>").append( value ).append("</string></value></param>" );
		
		return valuePOSTBody.toString();
		
	}
	
	public static String getCustoEventParam( String msisdn, EventTypes eventType, Map<EventParameterTypes, String> params ) {
		
		StringBuilder custoEventPOSTBody = new StringBuilder();
		StringBuilder paramsBody = new StringBuilder();
		
		Iterator<Entry<EventParameterTypes,String>> it = params.entrySet().iterator();
	    while( it.hasNext() ) {
	        
	    	Entry<EventParameterTypes,String> param = (Entry<EventParameterTypes,String>)it.next();
	    	paramsBody.append("<parameter>")
	    				.append("<name>").append( param.getKey().name() ).append("</name>")
	    				.append("<value>").append( param.getValue() ).append("</value>")
	    				.append("</parameter>");
	    	
	    }
		
		custoEventPOSTBody.append("<param><value><custoEvent>")
								.append("<msisdn>").append( msisdn ).append("</msisdn>")
								.append("<name>").append( eventType.name() ).append("</name>")
								.append("<parameters>").append( paramsBody ).append("</parameters>")
								.append("</custoEvent></value></param>");
		
		return 	custoEventPOSTBody.toString();
		
	}
	
}
