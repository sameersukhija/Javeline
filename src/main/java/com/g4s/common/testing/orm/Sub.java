package com.g4s.common.testing.orm;

public class Sub implements IRow {

	private String value;
	
	Sub( final String value ) {
		this.setValue( value );
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue( final String value ) {
		this.value = value;
	}

}
