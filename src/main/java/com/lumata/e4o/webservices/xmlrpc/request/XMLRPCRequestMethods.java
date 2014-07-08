package com.lumata.e4o.webservices.xmlrpc.request;

import com.lumata.common.testing.system.Security;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter;
import com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCPrice;
import com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCProduct;

public class XMLRPCRequestMethods {

	public enum EventType {
		
		ussd, revenue
		
	}
	
	StringBuilder value;
	
	XMLRPCRequestMethods( StringBuilder param ) { 
		this.value = param; 
	}
	
	public static XMLRPCRequestMethods authentication( User user ) {
		
		return XMLRPCRequestMethods.authentication( user.getUsername(), user.getPassword() );
		
	}
	
	public static XMLRPCRequestMethods authentication( String user, String password ) {
		
		StringBuilder authenticationPOSTBody = new StringBuilder();
		
		authenticationPOSTBody.append("<param><value><authentication>");
		
		if( null != user ) { authenticationPOSTBody.append("<login>").append( user ).append("</login>"); }
		
		if( null != password ) { authenticationPOSTBody.append("<password>").append( Security.decrypt( password ) ).append("</password>"); }
								
		authenticationPOSTBody.append("</authentication></value></param>");
		
		return new XMLRPCRequestMethods( authenticationPOSTBody );
		
	}
	
	public static XMLRPCRequestMethods string( Object value ) {
		
		StringBuilder valuePOSTBody = new StringBuilder();
		
		valuePOSTBody.append( "<param><value><string>");
		
		if( null != value ) { valuePOSTBody.append( value ); }
		
		valuePOSTBody.append("</string></value></param>" );
		
		return new XMLRPCRequestMethods( valuePOSTBody );
		
	}
	
	public static XMLRPCRequestMethods integer( Integer value ) {
		
		StringBuilder valuePOSTBody = new StringBuilder();
		
		valuePOSTBody.append( "<param><value><int>");
		
		if( null != value ) { valuePOSTBody.append( value ); }
		
		valuePOSTBody.append("</int></value></param>" );
		
		return new XMLRPCRequestMethods( valuePOSTBody );
		
	}

	public static XMLRPCPrice price( Integer amount, String currencyName ) {
				
		return new XMLRPCPrice( amount, currencyName );
		
	}
	
	public static XMLRPCRequestMethods arrayProductPrices( XMLRPCPrice... prices ) {
		
		StringBuilder valuesPOSTBody = new StringBuilder();
		
		valuesPOSTBody.append( "<param><value><productPrices>");
		
		for( int p = 0; p < prices.length; p++ ) {
			
			if( null != prices[ p ] ) { 
				
				valuesPOSTBody.
					append( "<price>" ).
					append( ( null != prices[ p ].getAmount() ? "<amount>" + prices[ p ].getAmount() + "</amount>" : "" ) ).
					append( ( null != prices[ p ].getCurrencyName() ? "<currencyName>" + prices[ p ].getCurrencyName() + "</currencyName>" : "" ) ).
					append( "</price>" ); 
				
			}			
			
		}
		
		valuesPOSTBody.append("</productPrices></value></param>" );
	
		return new XMLRPCRequestMethods( valuesPOSTBody );
				
	}
	
	public static XMLRPCProduct product() {
		
		return new XMLRPCProduct();
		
	}
	
	public static XMLRPCRequestMethods arraySelectedProducts( XMLRPCProduct... products ) {
		
		StringBuilder valuesPOSTBody = new StringBuilder();
		
		valuesPOSTBody.append( "<param><value><selectedProducts>");
		
		for( int p = 0; p < products.length; p++ ) {
			
			if( null != products[ p ] ) { 
				
				valuesPOSTBody.
					append( "<product>" ).
					append( "</product>" ); 
				
			}			
			
		}
		
		valuesPOSTBody.append("</selectedProducts></value></param>" );
	
		return new XMLRPCRequestMethods( valuesPOSTBody );
				
	}
	
	public static XMLRPCRequestMethods arrayInt( Object... values ) {
		
		return XMLRPCRequestMethods.array( "int", values );
				
	}
	
	private static XMLRPCRequestMethods array( String type, Object... values ) {
		
		StringBuilder valuesPOSTBody = new StringBuilder();
		
		valuesPOSTBody.append( "<param><value><array><data>");
		
		if( null != values ) { 
			
			for( int v = 0; v < values.length; v++ ) { 
				
				valuesPOSTBody.append( "<value><" ).append( type ).append( ">" ).append( values[ v ] ).append( "</" ).append( type ).append( "></value>" ); 
			
			}
			
		}
		
		valuesPOSTBody.append("</data></array></value></param>" );
		
		return new XMLRPCRequestMethods( valuesPOSTBody );
		
	}
	
	public static XMLRPCRequestMethods custoEvent( Long msisdn, EventType eventType, XMLRPCParameter... parameters ) {
		
		StringBuilder custoEventPOSTBody = new StringBuilder();
		
		StringBuilder paramsBody = new StringBuilder();
		
		for( int p = 0; p < parameters.length; p++ ) {
			
			paramsBody.append( parameters[ p ].getParameter() );
			
		}
		
		custoEventPOSTBody.append("<param><value><custoEvent>")
								.append("<msisdn>").append( msisdn ).append("</msisdn>")
								.append("<name>").append( eventType.name() ).append("</name>")
								.append("<parameters>").append( paramsBody ).append("</parameters>")
								.append("</custoEvent></value></param>");
		
		return new XMLRPCRequestMethods( custoEventPOSTBody );
		
	}	
	
	public static XMLRPCRequestMethods subscriber( Long msisdn ) {
		
		return XMLRPCRequestMethods.subscriber( String.valueOf( msisdn ), null, null, null, null, null, null, null, null );
		
	}
	
	public static XMLRPCRequestMethods subscriber( String msisdn, String subscriptionDate, String profile, String ratePlan, String status, String inTag, String network, XMLRPCParameter[] params, String[] services ) {
		
		StringBuilder subscriberPOSTBody = new StringBuilder();
		
		StringBuilder paramsBody = new StringBuilder();
		
		if( null != params ) {
						
			for( int p = 0; p < params.length; p++ ) {
				
				paramsBody.append( params[ p ].getParameter() );
				
			}
		
		}
		
		StringBuilder servicesBody = new StringBuilder();
		
		if( null != services ) {
			
			for( int s = 0; s < services.length; s++ ) {
				
				servicesBody.append( services[ s ] ).append( "," );
				
			}
			
			if( servicesBody.length() > 0 ) { servicesBody.setLength( servicesBody.length() - 1 ); }
		
		}
		
		subscriberPOSTBody.append("<param><value><subscriber>");
								if( null != msisdn ) { subscriberPOSTBody.append("<msisdn>").append( msisdn ).append("</msisdn>"); }
								if( null != subscriptionDate ) { subscriberPOSTBody.append("<subscription_date>").append( subscriptionDate ).append("</subscription_date>"); }
								if( null != profile ) { subscriberPOSTBody.append("<profile>").append( profile ).append("</profile>"); }
								if( null != ratePlan ) { subscriberPOSTBody.append("<rate_plan>").append( ratePlan ).append("</rate_plan>"); }
								if( null != status ) { subscriberPOSTBody.append("<status>").append( status ).append("</status>"); }
								if( null != inTag ) { subscriberPOSTBody.append("<in_tag>").append( inTag ).append("</in_tag>"); }
								if( null != network ) { subscriberPOSTBody.append("<network>").append( network ).append("</network>"); }
								if( null != params ) { subscriberPOSTBody.append("<params>").append( paramsBody ).append("</params>"); }
								if( null != services ) { subscriberPOSTBody.append("<services>").append( servicesBody ).append("</services>"); }
															
		subscriberPOSTBody.append("</subscriber></value></param>");
		
		return new XMLRPCRequestMethods( subscriberPOSTBody );
		
	}

	public static String[] services( String... services ) {
		
		return services;
		
	}

	public static XMLRPCParameter[] params( XMLRPCParameter... params ) {
		
		return params;
		
	}
		
}
