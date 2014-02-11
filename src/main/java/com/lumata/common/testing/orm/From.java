package com.lumata.common.testing.orm;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class From implements IFrom {

	Statement statement;
	
	From( Statement statement ) {
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
	public IWhere where( final IExprFV expr, final ICondFV... cond ) {
		
		this.where( expr );
		
		for( int i = 0; i < cond.length; i++ ) {
			
			this.statement.append( cond[ i ].build() );
			
			this.statement.addAllPlaceHolders( cond[ i ].getPlaceHolders() );
							
		}
		
		return new Where(statement);
		
	}
	
	@Override
	public IJoin join( final Object entity ) {
		
		Table table = (Table)entity.getClass().getAnnotation( Table.class );
		
		this.statement.addEntity( entity, table.value() );
		
		this.statement.append( MysqlStatement.JOIN.getName() )
						.append( table.value() );
		
		return new Join(statement);
		
	}

	@Override
	public IGroupBy groupBy( final Enum<?>... group ) {
		
		this.statement.append( Statement.MysqlStatement.GROUP_BY.getName() )
						.append( statement.fields( group ) );
		
		return new GroupBy(statement);
		
	}
	
	@Override
	public IHaving having( final IExprFV expr ) {
		
		this.statement.append( MysqlStatement.HAVING.getName() )
						.append( Statement.expr( expr ) );
		
		if( expr.getUsePlaceHolder() ) { this.statement.addPlaceHolder( expr.getField(), (String)expr.getValue() ); }
		
		return new Having(statement);
		
	}

	@Override
	public IHaving having( final IExprFV expr, final ICondFV... cond ) {
		
		this.having( expr );
		
		for( int i = 0; i < cond.length; i++ ) {
				
			this.statement.append( cond[ i ].build() );
			
			this.statement.addAllPlaceHolders( cond[ i ].getPlaceHolders() );
							
		}
		
		return new Having(statement);
		
	}

	@Override
	public IOrderBy orderBy( final Enum<?>... order ) {
		
		this.statement.append( Statement.MysqlStatement.ORDER_BY.getName() )
						.append( statement.fields( order ) );
		
		return new OrderBy(statement);
		
	}
	
	@Override
	public ILimit limit( final Integer... limit ) {
		
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
	public Sub sub() {
		
		return new Sub( this.statement.build() );
				
	}
	
	@Override
	public String build() {
		
		return this.statement.build();
				
	}
	
}
