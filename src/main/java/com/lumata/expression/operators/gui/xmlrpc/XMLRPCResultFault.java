package com.lumata.expression.operators.gui.xmlrpc;

public class XMLRPCResultFault {

	String code;
	String message;
	
	XMLRPCResultFault() {}
	
	public String getCode() {
		
		return this.code;
		
	}
	
	public String getMessage() {
		
		return this.message;
		
	}
	
	public void setCode( String code ) {
		
		this.code = code;
		
	}
	
	public void setMessage( String message ) {
		
		this.message = message;
		
	}
	
}
