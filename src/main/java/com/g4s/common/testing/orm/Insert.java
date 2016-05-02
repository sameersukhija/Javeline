package com.g4s.common.testing.orm;

import java.util.Arrays;

public class Insert implements IInsert {

	Statement statement;
	
	Insert( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IValues values() {
		
		this.statement.append( Statement.MysqlStatement.VALUES.getName() )
						.append( "( " )
						.append( this.statement.expr( true, (Object)null ) )
						.append( " )" );
		
		return new Values(statement);
		
	}
	
	@Override
	public IValues values( final Object... values ) {
		
		this.statement.append( Statement.MysqlStatement.VALUES.getName() );		
		
		try {
			
			@SuppressWarnings("unused")
			Row rows = (Row)values[0];
			
			this.statement.append( this.statement.expr( Arrays.copyOf( values, values.length, Row[].class) ) );
			
		} catch ( ClassCastException e ) {
			
			this.statement.append( "( " )
							.append( this.statement.expr( false, values ) )
							.append( " )" );
			
		}
		
		return new Values(statement);
		
	}

}
