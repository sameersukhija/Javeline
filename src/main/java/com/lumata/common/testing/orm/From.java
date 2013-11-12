package com.lumata.common.testing.orm;

import java.util.List;

public class From implements IFrom {

	Statement statement;
	
	From( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IWhere where( List<String> filter ) {
		
		this.statement.setSearch( filter );
		
		return new Where(statement);
		
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
