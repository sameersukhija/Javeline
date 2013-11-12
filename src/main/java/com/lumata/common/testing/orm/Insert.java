package com.lumata.common.testing.orm;

public class Insert implements IInsert {

	Statement statement;
	
	Insert( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IValues values() {
		
		return new Values(statement);
		
	}

}
