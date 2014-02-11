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
	public void setStatement( final Statement statement ) {
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
	public ICondFF append( final MysqlStatement type, final ICondFF... cond_list ) {
		
		if( cond_list.length > 0 ) {
		
			StringBuilder conditions = new StringBuilder();
						
			for( int i = 1; i < cond_list.length; i++ ) {
				
				conditions.append( cond_list[ i ].build() ).append( ", " );
				
			}
						
			this.condition.append( MysqlStatement.valueOf( type.name() ).getName() )
							.append( "(" )
							.append( cond_list[ 0 ].build().replaceFirst( "AND " , "" ).replaceFirst( "OR " , "" ) )
							.append( ( conditions.length() > 0 ? conditions.substring( 0, conditions.length() - 2 ) : "" ) )
							.append( " )");		
		
		}
		
		return this;
		
	}
	
	@Override
	public String build() {
		return this.condition.toString();
	}

}
