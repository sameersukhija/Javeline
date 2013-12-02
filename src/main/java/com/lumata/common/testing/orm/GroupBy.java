package com.lumata.common.testing.orm;

import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class GroupBy implements IGroupBy {

	Statement statement;
	
	GroupBy( Statement statement ) {
		this.statement = statement;
	}

	@Override
	public IHaving having( IExprFV expr ) {
		
		this.statement.append( MysqlStatement.HAVING.getName() )
						.append( Statement.expr( expr ) );
		
		if( expr.getUsePlaceHolder() ) { this.statement.addPlaceHolder( expr.getField(), (String)expr.getValue() ); }
			
		return new Having(statement);
		
	}

	@Override
	public IHaving having( IExprFV expr, ICondFV... cond ) {
		
		this.having( expr );
		
		for( int i = 0; i < cond.length; i++ ) {
			
			this.statement.append( cond[ i ].build() );
			
			this.statement.addAllPlaceHolders( cond[ i ].getPlaceHolders() );
							
		}
		
		return new Having(statement);
		
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

}
