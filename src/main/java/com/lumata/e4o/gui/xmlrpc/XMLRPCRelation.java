package com.lumata.e4o.gui.xmlrpc;

public class XMLRPCRelation {

	public enum Params {
		type,
		sponsor;
	}
	
	String type;
	String sponsor;
			
	public XMLRPCRelation() {}
	
	public XMLRPCRelation( String type, String sponsor ) {
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
