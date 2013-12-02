package com.lumata.common.testing.orm;

public class Values implements IValues {

	Statement statement;
	
	Values( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IQueryTemplate template() {
		
		return new QueryTemplate(statement);
				
	}
		
	@Override
	public String build() {
		
		return this.statement.build();
				
	}

}
