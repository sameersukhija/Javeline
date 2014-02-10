package com.lumata.common.testing.orm;

import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class CondFF implements ICondFF {

	private Statement statement;
	private StringBuilder condition;
		
	CondFF( final MysqlStatement type, final IExprFF... expr_list ) {
		
		this.condition = new StringBuilder();
		
		this.append( type, expr_list );
		
	}
	
	CondFF( final MysqlStatement type, final ICondFF... cond_list ) {
		
		this.condition = new StringBuilder();
		
		this.append( type, cond_list );
		
	}

	@Override
	public Statement getStatement() {
		return this.statement;
	}	

	@Override
	public void setStatement( Statement statement ) {
		this.statement = statement;
	}	
	
	@Override
	public ICondFF append( final MysqlStatement type, final IExprFF... expr_list ) {
		
		for( int i = 0; i < expr_list.length; i++ ) {
			
			this.condition.append( MysqlStatement.valueOf( type.name() ).getName() )
							.append( Statement.expr( expr_list[ i ] ) );
		}		
		
		return this;
	}
	
	@Override
	public ICondFF append( final MysqlStatement type, final ICondFF... cond ) {
		
		if( cond.length > 0 ) {
		
			StringBuilder cond_list = new StringBuilder();
						
			for( int i = 1; i < cond.length; i++ ) {
				
				cond_list.append( cond[ i ].build() ).append( ", " );
				
			}
						
			this.condition.append( MysqlStatement.valueOf( type.name() ).getName() )
							.append( "(" )
							.append( cond[ 0 ].build().replaceFirst( "AND " , "" ).replaceFirst( "OR " , "" ) )
							.append( ( cond_list.length() > 0 ? cond_list.substring( 0, cond_list.length() - 2 ) : "" ) )
							.append( " )");		
		
		}
		
		return this;
		
	}
	
	@Override
	public String build() {
		return this.condition.toString();
	}

}
