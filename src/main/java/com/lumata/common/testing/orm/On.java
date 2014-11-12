package com.lumata.common.testing.orm;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class On implements IOn {

	Statement statement;
	
	public On( Statement statement ) {
		this.statement = statement;
	}

	@Override
	public IWhere where( IExprFV expr ) {
		
		this.statement.append( MysqlStatement.WHERE.getName() )
						.append( Statement.expr( expr ) );
		
		if( expr.getUsePlaceHolder() ) { this.statement.addPlaceHolder( expr.getField(), (String)expr.getValue() ); }
			
		return new Where(statement);
		
	}

	@Override
	public IWhere where( IExprFV expr, ICond... cond ) {
		
		this.where( expr );
		
		for( int i = 0; i < cond.length; i++ ) {
			
			this.statement.append( cond[ i ].build() );
			
			this.statement.addAllPlaceHolders( cond[ i ].getPlaceHolders() );
			
		}
		
		return new Where(statement);
		
	}

	@Override
	public IWhere where( final IExprFF expr ) {
		
		this.statement.append( MysqlStatement.WHERE.getName() )
						.append( Statement.expr( expr ) );
				
		return new Where(statement);
		
	}
	
	@Override
	public IWhere where( final IExprFF expr, final ICond... cond ) {
		
		this.where( expr );
		
		for( int i = 0; i < cond.length; i++ ) {
			
			this.statement.append( cond[ i ].build() );
			
			this.statement.addAllPlaceHolders( cond[ i ].getPlaceHolders() );
							
		}
		
		return new Where(statement);
		
	}
	
	@Override
	public IJoin join( Object entity ) {
		
		Table table = (Table)entity.getClass().getAnnotation( Table.class );
		
		this.statement.append( MysqlStatement.JOIN.getName() )
						.append( table.value() );
		
		return new Join(statement);
		
	}

	@Override
	public IGroupBy groupBy( Enum<?>... group ) {
		
		this.statement.append( Statement.MysqlStatement.GROUP_BY.getName() )
						.append( statement.fields( group ) );
		
		return new GroupBy(statement);
		
	}
	
	@Override
	public IHaving having( IExprFV expr ) {
		
		this.statement.append( MysqlStatement.HAVING.getName() )
						.append( Statement.expr( expr ) );
		
		if( expr.getUsePlaceHolder() ) { this.statement.addPlaceHolder( expr.getField(), (String)expr.getValue() ); }
			
		return new Having(statement);
		
	}

	@Override
	public IHaving having( IExprFV expr, ICond... cond ) {
		
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
	public String build() {
		
		return this.statement.build();
				
	}
	
	@Override
	public Statement statement() {
		
		return this.statement;
				
	}
	
}
