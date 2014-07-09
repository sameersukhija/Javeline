package com.lumata.common.testing.orm;

import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class Having implements IHaving {

	Statement statement;
	
	Having( Statement statement ) {
		this.statement = statement;
	}

	@Override
	public IOrderBy orderBy( Enum<?>... order ) {
		
		this.statement.append( Statement.MysqlStatement.ORDER_BY.getName() )
						.append( statement.fields( order ) );
		
		return new OrderBy(statement);
		
	}
	
	@Override
	public ILimit limit( Integer... limit ) {
		
		this.statement.append( MysqlStatement.LIMIT.getName() );
		
		if( limit.length > 0 ) { this.statement.append( String.valueOf( limit[ 0 ] ) ); }
		
		if( limit.length > 1 ) { this.statement.append( ", " ).append( String.valueOf( limit[ 1 ] ) ); }
			
		return new Limit(statement);
		
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
