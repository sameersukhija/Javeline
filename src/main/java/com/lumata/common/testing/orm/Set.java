package com.lumata.common.testing.orm;

import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class Set implements ISet {

	Statement statement;
	
	Set( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IWhere where( final IExprFV expr ) {
		
		this.statement.append( MysqlStatement.WHERE.getName() )
						.append( Statement.expr( expr ) );
		
		if( expr.getUsePlaceHolder() ) { this.statement.addPlaceHolder( expr.getField(), (String)expr.getValue() ); }
		
		return new Where(statement);
		
	}

	@Override
	public IWhere where( final IExprFV expr, final ICond... cond ) {
		
		this.where( expr );
		
		for( int i = 0; i < cond.length; i++ ) {
			
			this.statement.append( cond[ i ].build() );
			
			this.statement.addAllPlaceHolders( cond[ i ].getPlaceHolders() );
							
		}
		
		return new Where(statement);
		
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
