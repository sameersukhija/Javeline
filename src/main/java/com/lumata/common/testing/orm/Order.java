package com.lumata.common.testing.orm;

public class Order implements IOrder {

	Statement statement;
	
	Order( Statement statement ) {
		this.statement = statement;
	}

	@Override
	public IQueryTemplate template() {
		
		return new QueryTemplate(statement);
				
	}
	
	@Override
	public String build(Object entity) {
		
		return this.statement.get( entity );
		
	}

}
