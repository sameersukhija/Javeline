package com.lumata.common.testing.orm;

public class Update implements IUpdate {

	Statement statement;
	
	Update( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public ISet set() {
		
		return new Set(statement);
		
	}

}
