package com.lumata.common.testing.orm;

public class QueryTemplate implements IQueryTemplate {
		
	Statement statement;
	
	QueryTemplate( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IQueryTemplate template() {
		
		return this;
				
	}
	
	@Override
	public String build() {
		
		return this.statement.build();
				
	}

}
