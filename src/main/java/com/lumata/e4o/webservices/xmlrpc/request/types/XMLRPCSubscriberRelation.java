package com.lumata.e4o.webservices.xmlrpc.request.types;

public class XMLRPCSubscriberRelation {

	public enum ParameterType {
		recharge, event_storage_policy, event_date, event_time,amount_recharge, balance_main_account;
	}
	
	private StringBuilder parameter;
		
	XMLRPCSubscriberRelation( StringBuilder parameter ) {
		this.parameter = parameter;
	}
/*	
	public static XMLRPCSubscriberRelation parameter( ParameterType parameterType, Object value ) {
		
		StringBuilder parameter = new StringBuilder();
		
		parameter.append("<parameter>")
	    			.append("<name>").append( parameterType.name() ).append("</name>")
	    			.append("<value>").append( value.toString() ).append("</value>")
	    			.append("</parameter>");
	    	
		return new XMLRPCSubscriberRelation( parameter );
		
	}
	
	public static XMLRPCSubscriberRelation param( Object paramName, Object paramValue ) {
		
		StringBuilder param = new StringBuilder();
		
		param.append("<param>");
					if( null != paramName ) { param.append("<paramName>").append( paramName ).append("</paramName>"); }
					if( null != paramValue ) { param.append("<paramValue>").append( paramValue ).append("</paramValue>"); }
		
		param.append("</param>");
	    	
		return new XMLRPCSubscriberRelation( param );
		
	}
*/	
	public StringBuilder getParameter() {
		return this.parameter;
	}
	
}
