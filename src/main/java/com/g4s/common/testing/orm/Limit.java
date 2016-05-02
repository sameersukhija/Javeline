package com.g4s.common.testing.orm;

public class Limit implements ILimit {

	Statement statement;
	
	Limit( Statement statement ) {
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
	
	@Override
	public Statement statement() {
		
		return this.statement;
				
	}

}
