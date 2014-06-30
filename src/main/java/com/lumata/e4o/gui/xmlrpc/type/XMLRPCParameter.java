package com.lumata.e4o.gui.xmlrpc.type;

public class XMLRPCParameter {

	public enum ParameterType {
		recharge, event_storage_policy, event_date, amount_recharge, balance_main_account;
	}
	
	private StringBuilder parameter;
		
	XMLRPCParameter( StringBuilder parameter ) {
		this.parameter = parameter;
	}
	
	public static XMLRPCParameter parameter( ParameterType parameterType, Object value ) {
		
		StringBuilder parameter = new StringBuilder();
		
		parameter.append("<parameter>")
	    			.append("<name>").append( parameterType.name() ).append("</name>")
	    			.append("<value>").append( value.toString() ).append("</value>")
	    			.append("</parameter>");
	    	
		return new XMLRPCParameter( parameter );
		
	}
	
	public StringBuilder getParameter() {
		return this.parameter;
	}
	
}
