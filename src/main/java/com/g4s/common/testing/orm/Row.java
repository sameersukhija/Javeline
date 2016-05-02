package com.g4s.common.testing.orm;

public class Row implements IRow {

	private Object[] values;
	
	Row( final Object... values ) {
		this.values = values;
	}
	
	public Object[] getValues() {
		return this.values;
	}

}
