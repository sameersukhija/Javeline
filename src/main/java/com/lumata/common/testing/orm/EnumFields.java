package com.lumata.common.testing.orm;

import java.lang.reflect.Type;

import com.lumata.common.testing.annotations.mysql.Column;
import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.orm.Statement.MysqlSelectFuncType;

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
	
	@Override
	public String function() {
		
		final String FUNC_PLACEHOLDER = "::expr::";
		
		String function = MysqlSelectFuncType.valueOf( field.name() ).name() + "( " + FUNC_PLACEHOLDER + " )";
		
		switch( MysqlSelectFuncType.valueOf( field.name() ).getValueType() ) {
		
			case Single_Field: {
				return function.replace( FUNC_PLACEHOLDER, Statement.field( MysqlSelectFuncType.valueOf( field.name() ).getField() ).toString() );
			}
			case Single_Value: {
				return function.replace( FUNC_PLACEHOLDER, MysqlSelectFuncType.valueOf( field.name() ).getValue() );				
			}
			case Multiple_Values: {
				return function.replace( FUNC_PLACEHOLDER, Statement.field( null, MysqlSelectFuncType.valueOf( field.name() ).getObjectArray() ) );				
			}
			default: { return ""; }
		}
					
	}
	
	@Override
	public Boolean isFunction() {
		
		Type typeFunc = MysqlSelectFuncType.class;
		
		return typeFunc.equals( field.getClass() );
				
	}
		
}
