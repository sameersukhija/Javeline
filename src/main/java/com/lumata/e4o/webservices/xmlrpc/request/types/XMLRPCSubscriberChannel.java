package com.lumata.e4o.webservices.xmlrpc.request.types;

public class XMLRPCSubscriberChannel {

	public enum ChannelType {
		SMS, MAIL, Outbound, VoiceXML, Invalid;
	}
	
	public enum Status {
		active, inactive, deactivated, terminated, invalid
	}	
		
	private StringBuilder channel;
		
	XMLRPCSubscriberChannel( StringBuilder channel ) {
		this.channel = channel;
	}
	
	public static XMLRPCSubscriberChannel channel( ChannelType channelType, String address, Status status ) {
		
		StringBuilder parameter = new StringBuilder();
		
		parameter.append("<channel>")
	    			.append("<name>").append( channelType.name() ).append("</name>")
	    			.append("<address>").append( address ).append("</address>")
	    			.append("<active>").append( status.name() ).append("</active>")
	    			.append("</channel>");
	    	
		return new XMLRPCSubscriberChannel( parameter );
		
	}

	public StringBuilder getParameter() {
		return this.channel;
	}
	
}
