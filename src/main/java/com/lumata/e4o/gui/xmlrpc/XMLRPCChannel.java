package com.lumata.e4o.gui.xmlrpc;

public class XMLRPCChannel {

	public enum Params {
		name,
		address,
		active;
	}
	
	public enum Type {
		SMS,
		MAIL;
	}
	
	String name;
	String address;
	String active;
		
	public XMLRPCChannel() {}
	
	public XMLRPCChannel( String name, String address, String active ) {
		this.setName(name);
		this.setAddress(address);
		this.setActive(active);
	}
	
	public XMLRPCChannel( Type name, String address, Boolean active ) {
		this.setName(name.name());
		this.setAddress(address);
		this.setActive(active.toString());
	}
	
	public String getName() {
		
		return this.name;
		
	}
	
	public String getAddress() {
		
		return this.address;
		
	}
	
	public String getActive() {
		
		return active;
		
	}
		
	public void setName( String name ) {
		
		this.name = name;
		
	}
	
	public void setAddress( String address ) {
		
		this.address = address;
		
	}
	
	public void setActive( String active ) {
		
		this.active = active;
		
	}
				
}
