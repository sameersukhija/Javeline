package com.lumata.expression.operators.gui.xmlrpc;

public class XMLRPCChannel {

	public enum Params {
		name,
		address,
		active;
	}
	
	String name;
	String address;
	String active;
		
	XMLRPCChannel() {}
	
	XMLRPCChannel( String name, String address, String active ) {
		this.setName(name);
		this.setAddress(address);
		this.setActive(active);
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
