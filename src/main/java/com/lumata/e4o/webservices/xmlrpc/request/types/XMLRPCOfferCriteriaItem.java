package com.lumata.e4o.webservices.xmlrpc.request.types;


public class XMLRPCOfferCriteriaItem {

	private StringBuilder offerCriteriaItem;
		
	XMLRPCOfferCriteriaItem( StringBuilder offerCriteriaItem ) {
		this.offerCriteriaItem = offerCriteriaItem;
	}

	public static XMLRPCOfferCriteriaItem offerCriteriaItem( String name, String operator, String value ) {
		
		StringBuilder offerCriteriaItem = new StringBuilder();
		
		offerCriteriaItem.append("<offerCriteriaItem>");
	    if( null != name ) { offerCriteriaItem.append("<name>").append( name ).append("</name>"); }
	    if( null != operator ) { offerCriteriaItem.append("<operator>").append( operator ).append("</operator>"); }
	    if( null != value ) { offerCriteriaItem.append("<value>").append( value ).append("</value>"); }
	    offerCriteriaItem.append("</offerCriteriaItem>");
	    	    
		return new XMLRPCOfferCriteriaItem( offerCriteriaItem );
		
	}

	public StringBuilder getOfferCriteriaItem() {
		return this.offerCriteriaItem;
	}
	
}
