package com.lumata.common.testing.orm;

public class Select implements ISelect {

	Statement statement;
	
	Select( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IFrom from() {
				
		return new From(statement);
	
	}

}
