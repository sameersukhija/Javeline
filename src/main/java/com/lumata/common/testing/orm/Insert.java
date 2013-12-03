package com.lumata.common.testing.orm;

public class Insert implements IInsert {

	Statement statement;
	boolean all_fields;
	
	Insert( Statement statement, boolean all_fields ) {
		this.statement = statement;
		this.all_fields = all_fields;
	}
	
	@Override
	public IValues values() {
		
		this.statement.append( Statement.MysqlStatement.VALUES.getName() );
		
		return new Values(statement);
		
	}
	
	@Override
	public IValues values( final Object... values) {
		
		this.statement.append( Statement.MysqlStatement.VALUES.getName() );
		
		return new Values(statement);
		
	}

}
