package com.lumata.common.testing.orm;

public class Insert implements IInsert {

	Statement statement;
	
	Insert( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IValues values() {
		
		this.statement.append( Statement.MysqlStatement.VALUES.getName() );
		
		return new Values(statement);
		
	}
	
	@Override
	public IValues values( final Object... values ) {
				
		this.statement.append( Statement.MysqlStatement.VALUES.getName() )
						.append( "( " )
						.append( Statement.expr( this.statement.fields, values ) )
						.append( " )" );
		
		return new Values(statement);
		
	}

}
