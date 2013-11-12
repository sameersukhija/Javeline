package com.lumata.common.testing.orm;

import java.util.List;

public class Where implements IWhere {

	Statement statement;
	
	Where( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IOrder order( List<String> order ) {
		
		this.statement.setOrder( order );
		
		return new Order(statement);
		
	}
	
	@Override
	public IQueryTemplate template() {
		
		return new QueryTemplate(statement);
				
	}
		
	@Override
	public String build( Object entity ) {
		
		return this.statement.get( entity );
				
	}
	
}
