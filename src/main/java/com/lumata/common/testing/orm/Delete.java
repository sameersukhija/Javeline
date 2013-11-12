package com.lumata.common.testing.orm;

public class Delete implements IDelete {

	Statement statement;
	
	Delete( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IFrom from() {
				
		return new From(statement);
	
	}

}
