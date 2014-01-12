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
			
		},
		offeroptimizer_getTokensList {
			
			public String getName() { return "offeroptimizer.getTokensList"; }
			
		},
		subscribermanager_getSubscriber {
			
			public String getName() { return "subscribermanager.getSubscriber"; }
			
		},
		subscribermanager_createSubscriber {
			
			public String getName() { return "subscribermanager.createSubscriber"; }
			
		},
		subscribermanager_deleteSubscriber {
			
			public String getName() { return "subscribermanager.deleteSubscriber"; }
			
		},
		user_create {
			
			public String getName() { return "user.create"; }
			
		},
		user_update {
			
			public String getName() { return "user.update"; }
			
		},
		user_delete {
			
			public String getName() { return "user.delete"; }
			
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
		
		recharge,
		event_storage_policy;
		
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

	public static String getUser( String user, String password, String agency, Map<String, String> groups ) {
		
		StringBuilder userBody = new StringBuilder();
		
		StringBuilder agencyTag = new StringBuilder();
		
		if( agency != null ) { agencyTag.append( "<agency>" ).append( agency ).append( "</agency>" ); }
		
		userBody.append( "<param><value><user>" )
					.append( "<name>" ).append( user ).append( "</name>" )
					.append( "<password>" ).append( password ).append( "</password>" )
					.append( agencyTag )
					.append( HTTPXMLRPCForm.getGroups( groups ) )
					.append( "</user></value></param>" );		
		
		return userBody.toString();		
		
	}
	
	
	public static String getGroups( Map<String, String> groups ) {
		
		StringBuilder groupsBody = new StringBuilder();
		StringBuilder paramsBody = new StringBuilder();
		
		for( Map.Entry<String, String> group : groups.entrySet()) {
			
			paramsBody.append( HTTPXMLRPCForm.getGroup( group.getKey(), group.getValue() ) );
			
		}
	 		
		groupsBody.append( "<groups>" )
	      			.append( paramsBody )
	    			.append( "</groups>" );
		
		return groupsBody.toString();
		
	}
	
	public static String getGroup( String group_name, String access_level ) {
		
		StringBuilder groupBody = new StringBuilder();
		
		groupBody.append( "<group>" )
	      			.append( "<groupName>" ).append( group_name ).append( "</groupName>" )
	      			.append( "<accessLevel>" ).append( access_level ).append( "</accessLevel>" )
	    			.append( "</group>" );
		
		return groupBody.toString();
		
	}
	
	public static String getSubscriber( Map<String, Object> params ) {
		
		StringBuilder subscriberBody = new StringBuilder();
		
		subscriberBody.append( "<param><value><subscriber>" );
		
		if( params.containsKey( XMLRPCSubscriber.Params.msisdn.name() ) ) { subscriberBody.append( "<msisdn>" ).append( params.get( XMLRPCSubscriber.Params.msisdn.name() ) ).append( "</msisdn>" ); }
		
		if( params.containsKey( XMLRPCSubscriber.Params.subscription_date.name() ) ) { subscriberBody.append( "<subscription_date>" ).append( params.get( XMLRPCSubscriber.Params.subscription_date.name() ) ).append( "</subscription_date>" ); }
		
		if( params.containsKey( XMLRPCSubscriber.Params.profile.name() ) ) { subscriberBody.append( "<profile>" ).append( params.get( XMLRPCSubscriber.Params.profile.name() ) ).append( "</profile>" ); }
		
		if( params.containsKey( XMLRPCSubscriber.Params.subprofile.name() ) ) { subscriberBody.append( "<subprofile>" ).append( params.get( XMLRPCSubscriber.Params.subprofile.name() ) ).append( "</subprofile>" ); }
		
		if( params.containsKey( XMLRPCSubscriber.Params.rate_plan.name() ) ) { subscriberBody.append( "<rate_plan>" ).append( params.get( XMLRPCSubscriber.Params.rate_plan.name() ) ).append( "</rate_plan>" ); }
		
		if( params.containsKey( XMLRPCSubscriber.Params.status.name() ) ) { subscriberBody.append( "<status>" ).append( params.get( XMLRPCSubscriber.Params.status.name() ) ).append( "</status>" ); }
		
		if( params.containsKey( XMLRPCSubscriber.Params.in_tag.name() ) ) { subscriberBody.append( "<in_tag>" ).append( params.get( XMLRPCSubscriber.Params.in_tag.name() ) ).append( "</in_tag>" ); }
		
		if( params.containsKey( XMLRPCSubscriber.Params.network.name() ) ) { subscriberBody.append( "<network>" ).append( params.get( XMLRPCSubscriber.Params.network.name() ) ).append( "</network>" ); }
				
		subscriberBody.append( "</subscriber></value></param>" );		
		
		return subscriberBody.toString();		
		
	}
	
}
