package com.lumata.expression.operators.gui.xmlrpc;

public class XMLRPCRelation {

	public enum Params {
		type,
		sponsor;
	}
	
	String type;
	String sponsor;
			
	XMLRPCRelation() {}
	
	XMLRPCRelation( String type, String sponsor ) {
		this.setType(type);
		this.setSponsor(sponsor);
	}
	
	public String getType() {
		
		return this.type;
		
	}
	
	public String getSponsor() {
		
		return this.sponsor;
		
	}
		
	public void setType( String type ) {
		
		this.type = type;
		
	}
	
	public void setSponsor( String sponsor ) {
		
		this.sponsor = sponsor;
		
	}
				
}
