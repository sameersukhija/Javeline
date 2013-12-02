package com.lumata.common.testing.orm;

import com.lumata.common.testing.annotations.mysql.Column;
import com.lumata.common.testing.annotations.mysql.Table;

public class EnumFields<F extends Enum<?>> implements IEnumFields {

	private F field;
	
	public EnumFields( F field ) {
		this.field = field;
	}
	
	@Override
	public String name() {
		
		StringBuilder value = new StringBuilder();
		
		value.append( this.table() ).append( "." ).append( this.simpleName() );
		
		return value.toString();
		
	}

	@Override
	public String simpleName() {
		
		return field.name();
		
	}

	@Override
	public String table() {
		
		return field.getClass().getEnclosingClass().getAnnotation( Table.class ).value();
		
	}
	
	@Override
	public Column col() {
		
		try {
			
			return field.getClass().getEnclosingClass().getDeclaredField( field.name() ).getAnnotation( Column.class );
		
		} catch( NoSuchFieldException e ) {}
		
		return null;
		
	}	
	
}
