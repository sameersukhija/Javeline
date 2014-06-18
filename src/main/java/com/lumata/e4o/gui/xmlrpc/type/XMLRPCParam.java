package com.lumata.e4o.gui.xmlrpc.type;

import com.lumata.common.testing.system.Security;

public class XMLRPCParam {

	public enum EventType {
		
		ussd, revenue
		
	}
	
	StringBuilder value;
	
	XMLRPCParam( StringBuilder param ) { 
		this.value = param; 
	}
	
	public static XMLRPCParam authentication( String user, String password ) {
		
		StringBuilder authenticationPOSTBody = new StringBuilder();
		
		authenticationPOSTBody.append("<param><value><authentication>");
		
		if( null != user ) { authenticationPOSTBody.append("<login>").append( user ).append("</login>"); }
		
		if( null != password ) { authenticationPOSTBody.append("<password>").append( Security.decrypt( password ) ).append("</password>"); }
								
		authenticationPOSTBody.append("</authentication></value></param>");
		
		return new XMLRPCParam( authenticationPOSTBody );
		
	}
	
	public static XMLRPCParam string( Object value ) {
		
		StringBuilder valuePOSTBody = new StringBuilder();
		
		valuePOSTBody.append( "<param><value><string>");
		
		if( null != value ) { valuePOSTBody.append( value ); }
		
		valuePOSTBody.append("</string></value></param>" );
		
		return new XMLRPCParam( valuePOSTBody );
		
	}
	
	public static XMLRPCParam integer( Integer value ) {
		
		StringBuilder valuePOSTBody = new StringBuilder();
		
		valuePOSTBody.append( "<param><value><int>");
		
		if( null != value ) { valuePOSTBody.append( value ); }
		
		valuePOSTBody.append("</int></value></param>" );
		
		return new XMLRPCParam( valuePOSTBody );
		
	}

	public static XMLRPCPrice price( Integer amount, String currencyName ) {
				
		return new XMLRPCPrice( amount, currencyName );
		
	}
	
	public static XMLRPCParam arrayProductPrices( XMLRPCPrice... prices ) {
		
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
	
		return new XMLRPCParam( valuesPOSTBody );
				
	}
	
	public static XMLRPCParam arrayInt( Object... values ) {
		
		return XMLRPCParam.array( "int", values );
				
	}
	
	private static XMLRPCParam array( String type, Object... values ) {
		
		StringBuilder valuesPOSTBody = new StringBuilder();
		
		valuesPOSTBody.append( "<param><value><array><data>");
		
		if( null != values ) { 
			
			for( int v = 0; v < values.length; v++ ) { 
				
				valuesPOSTBody.append( "<value><" ).append( type ).append( ">" ).append( values[ v ] ).append( "</" ).append( type ).append( "></value>" ); 
			
			}
			
		}
		
		valuesPOSTBody.append("</data></array></value></param>" );
		
		return new XMLRPCParam( valuesPOSTBody );
		
	}
	
	public static XMLRPCParam custoEvent( Long msisdn, EventType eventType, XMLRPCParameter... parameters ) {
		
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
		
		return new XMLRPCParam( custoEventPOSTBody );
		
	}	
	
}
