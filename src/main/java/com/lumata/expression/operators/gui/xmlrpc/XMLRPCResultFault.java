package com.lumata.expression.operators.gui.xmlrpc;

public class XMLRPCResultFault {

	int code;
	String message;
	
	XMLRPCResultFault() {}
	
	public int getCode() {
		
		return this.code;
		
	}
	
	public String getMessage() {
		
		return this.message;
		
	}
	
	public void setCode( int code ) {
		
		this.code = code;
		
	}
	
	public void setMessage( String message ) {
		
		this.message = message;
		
	}
	
}
