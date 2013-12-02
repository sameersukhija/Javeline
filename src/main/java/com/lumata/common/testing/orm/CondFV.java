package com.lumata.common.testing.orm;

import java.util.HashMap;
import java.util.Map;

import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class CondFV implements ICondFV {
	
	private StringBuilder condition;
	private Map<Object, String> place_holders;
		
	CondFV( final MysqlStatement type, final IExprFV... expr_list ) {
		
		this.condition = new StringBuilder();
		
		this.place_holders = new HashMap<Object, String >();
		
		this.append( type, expr_list );
		
	}
	
	CondFV( final MysqlStatement type, final ICondFV... cond_list ) {
		
		this.condition = new StringBuilder();
		
		this.place_holders = new HashMap<Object, String >();
		
		this.append( type, cond_list );
		
	}

	@Override
	public ICondFV append( final MysqlStatement type, final IExprFV... expr_list ) {
		
		for( int i = 0; i < expr_list.length; i++ ) {
			this.condition.append( MysqlStatement.valueOf( type.name() ).getName() )
							.append( Statement.expr( expr_list[ i ] ) );
			
			if( expr_list[ i ].getUsePlaceHolder() ) { this.place_holders.put( expr_list[ i ].getField(), (String)expr_list[ i ].getValue() ); }
			
		}		
		
		return this;
	}
	
	@Override
	public ICondFV append( final MysqlStatement type, final ICondFV... cond ) {
		
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
	
	public Map<Object, String> getPlaceHolders() {
		return this.place_holders;
	}
	
	@Override
	public String build() {
		return this.condition.toString();
	}

}
