package com.lumata.e4o.webservices.xmlrpc.request.types;

public class XMLRPCPrice {
	
	Integer amount;
	
	String currencyName;
	
	public XMLRPCPrice( Integer amount, String currencyName ) {
		
		this.amount = amount;
		
		this.currencyName = currencyName; 
	
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public Object getCurrencyName() {
		return currencyName;
	}

}
