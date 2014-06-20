package com.lumata.e4o.gui.xmlrpc.type;

public class XMLRPCPrice {
	
	Integer amount;
	
	String currencyName;
	
	XMLRPCPrice( Integer amount, String currencyName ) {
		
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
