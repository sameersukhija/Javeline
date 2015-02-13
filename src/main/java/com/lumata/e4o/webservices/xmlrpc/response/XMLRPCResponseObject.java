package com.lumata.e4o.webservices.xmlrpc.response;

public abstract class XMLRPCResponseObject {
	
	private String root = "params.param.value";
	protected String relativeRoot = "";
	
	protected XMLRPCResponseObject() {}
	
	protected XMLRPCResponseObject( String relativeRoot ) {
		setRelativeRoot( relativeRoot );
	}

	protected void setRoot( String root ) {
		this.root = root;
	} 

	protected String getRelativeRoot() {
		return this.relativeRoot;
	} 

	protected void setRelativeRoot( String relativeRoot ) {
		this.relativeRoot = relativeRoot;
	} 
	
	protected String builPath( String tag ) {
		return this.root + this.relativeRoot + tag;
	}

}
