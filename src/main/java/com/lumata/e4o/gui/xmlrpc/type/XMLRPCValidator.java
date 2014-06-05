package com.lumata.e4o.gui.xmlrpc.type;

import com.lumata.common.testing.system.Security;

public class XMLRPCValidator {

	StringBuilder params;
	
	XMLRPCValidator() { 
		params = new StringBuilder(); 
	}
	
	public static XMLRPCValidator xmlrpcBody() {
		return new XMLRPCValidator();		
	}
	
	public XMLRPCValidator authentication( String user, String password ) {
		
		StringBuilder authenticationPOSTBody = new StringBuilder();
		
		authenticationPOSTBody.append("<param><value><authentication>");
		
		if( null != user ) { authenticationPOSTBody.append("<login>").append( user ).append("</login>"); }
		
		if( null != password ) { authenticationPOSTBody.append("<password>").append( Security.decrypt( password ) ).append("</password>"); }
								
		authenticationPOSTBody.append("</authentication></value></param>");
		
		params.append( authenticationPOSTBody.toString() );
		
		return this;
		
	}
	
	public XMLRPCValidator string( String value ) {
		
		StringBuilder valuePOSTBody = new StringBuilder();
		
		valuePOSTBody.append( "<param><value><string>");
		
		if( null != value ) { valuePOSTBody.append( value ); }
		
		valuePOSTBody.append("</string></value></param>" );
		
		params.append( valuePOSTBody.toString() );
		
		return  this;
		
	}
	
	public XMLRPCValidator integer( Integer value ) {
		
		StringBuilder valuePOSTBody = new StringBuilder();
		
		valuePOSTBody.append( "<param><value><int>");
		
		if( null != value ) { valuePOSTBody.append( value ); }
		
		valuePOSTBody.append("</int></value></param>" );
		
		params.append( valuePOSTBody.toString() );
		
		return  this;
		
	}
	
	
	public String build() {
		return params.toString();
	}
	
}
