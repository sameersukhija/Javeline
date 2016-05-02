package com.g4s.common.testing.orm;

import com.g4s.common.testing.orm.Statement.MysqlStatement;

public class Join implements IJoin {

	Statement statement;
	
	Join( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IOn on( IExprFF expr ) {
		
		this.statement.append( MysqlStatement.ON.getName() )
						.append( Statement.expr( expr ) );
		
		return new On(statement);
		
	}
	
	@Override
	public IOn on( IExprFF expr, ICond... cond ) {
		
		this.on( expr );
		
		for( int i = 0; i < cond.length; i++ ) {
			
			cond[ i ].setStatement( statement );
			
			this.statement.append( cond[ i ].build() );
							
		}
		
		return new On(statement);
		
	}
	
	@Override
	public ILimit limit( Integer... limit ) {
		
		this.statement.append( MysqlStatement.LIMIT.getName() );
		
		if( limit.length > 0 ) { this.statement.append( String.valueOf( limit[ 0 ] ) ); }
		
		if( limit.length > 1 ) { this.statement.append( ", " ).append( String.valueOf( limit[ 1 ] ) ); }
			
		return new Limit(statement);
		
	}

}
