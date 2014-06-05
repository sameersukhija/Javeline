package com.lumata.e4o.gui.xmlrpc.type;

import com.lumata.common.testing.system.Security;

public class XMLRPCParam {

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
	
	public static XMLRPCParam string( String value ) {
		
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
	
	
}
