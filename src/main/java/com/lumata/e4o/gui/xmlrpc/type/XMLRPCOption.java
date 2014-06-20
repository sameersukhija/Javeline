package com.lumata.e4o.gui.xmlrpc.type;

public class XMLRPCOption {
	
	public enum XMLRPCOptionType { sleep }
	
	Object value;
	
	XMLRPCOptionType optionType;
	
	XMLRPCOption() {}
	
	XMLRPCOption( XMLRPCOptionType optionType, Object value ) {
		
		this.optionType = optionType;
		
		this.value = value; 
	
	}
	
	public static XMLRPCOption sleep( Long time ) {
				
		return new XMLRPCOption( XMLRPCOptionType.sleep, time );
		
	}
	
	public XMLRPCOptionType getOptionType() {
		return optionType;
	}
	
	public Object getOptionValue() {
		return value;
	}

}
