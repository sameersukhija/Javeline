package com.lumata.e4o.gui.xmlrpc;

import java.util.HashMap;
import java.util.Map;

public class XMLRPCSubscriber {

	public enum Params {
		msisdn,
		subscription_date,
		profile,
		subprofile,
		rate_plan,
		status,
		in_tag,
		network,
		last_param_name,
		params,
		relations,
		channels;
	}
	
	String msisdn;
	String subscription_date;
	String profile;
	String subprofile;
	String rate_plan;
	String status;
	String in_tag;
	String network;
	String last_param_name;
	Map<String, String> params;
	Map<String, XMLRPCChannel> channels;
	Map<String, XMLRPCRelation> relations;
	
	XMLRPCSubscriber() {
		params = new HashMap<String, String>();
	}
	
	public String getMsisdn() {
		
		return this.msisdn;
		
	}
	
	public String getSubscriptionDate() {
		
		return this.subscription_date;
		
	}
	
	public String getProfile() {
		
		return profile;
		
	}
	
	public String getSubprofile() {
		
		return subprofile;
		
	}
	
	public String getRatePlan() {
		
		return this.rate_plan;
		
	}
	
	public String getStatus() {
		
		return this.status;
		
	}
	
	public String getInTag() {
		
		return this.in_tag;
		
	}
	
	public String getNetwork() {
		
		return this.network;
		
	}
	
	public String getParam( String param_name ) {
		
		return this.params.get( param_name );
		
	}
	
	public Map<String, XMLRPCChannel> getChannels() {
		
		return this.channels;
		
	}

	public XMLRPCChannel getChannel( String channel_name ) {
		
		return this.channels.get( channel_name );
		
	}
	
	public void setMsisdn( String msisdn ) {
		
		this.msisdn = msisdn;
		
	}
	
	public void setSubscriptionDate( String subscription_date ) {
		
		this.subscription_date = subscription_date;
		
	}
	
	public void setProfile( String profile ) {
		
		this.profile = profile;
		
	}
	
	public void setSubprofile( String subprofile ) {
		
		this.subprofile = subprofile;
		
	}
	
	public void setRatePlan( String rate_plan ) {
		
		this.rate_plan = rate_plan;
		
	}
	
	public void setStatus( String status ) {
		
		this.status = status;
		
	}
	
	public void setInTag( String in_tag ) {
		
		this.in_tag = in_tag;
		
	}
	
	public void setNetwork( String network ) {
		
		this.network = network;
		
	}
	
	public void setParamName( String param_name ) {
		
		this.last_param_name = param_name;
		
		this.params.put( param_name, "" ); 
				
	}
	
	public void setParamValue( String param_value ) {
		
		this.params.put( this.last_param_name , param_value );
				
	}
		
}
