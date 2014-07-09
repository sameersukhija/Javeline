package com.lumata.common.testing.orm;

import java.util.HashMap;
import java.util.Map;

import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class Cond implements ICond {

	private Statement statement;
	private StringBuilder condition;
	private Map<Enum<?>, String> place_holders;
	
	Cond( final MysqlStatement type, final IExprFV... expr_list ) {
		
		this.condition = new StringBuilder();
		
		this.place_holders = new HashMap<Enum<?>, String >();
		
		this.append( type, expr_list );
		
	}
	
	Cond( final MysqlStatement type, final IExprFF... expr_list ) {
		
		this.condition = new StringBuilder();
		
		this.place_holders = new HashMap<Enum<?>, String >();
		
		this.append( type, expr_list );
		
	}
	
	Cond( final MysqlStatement type, final ICond... cond_list ) {
		
		this.condition = new StringBuilder();
		
		this.place_holders = new HashMap<Enum<?>, String >();
		
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
	public ICond append( final MysqlStatement type, final IExprFV... expr_list ) {
		
		for( int i = 0; i < expr_list.length; i++ ) {
			this.condition.append( MysqlStatement.valueOf( type.name() ).getName() )
							.append( Statement.expr( expr_list[ i ] ) );
			
			if( expr_list[ i ].getUsePlaceHolder() ) { this.place_holders.put( expr_list[ i ].getField(), (String)expr_list[ i ].getValue() ); }
			
		}		
		
		return this;
	}
	
	@Override
	public ICond append( final MysqlStatement type, final IExprFF... expr_list ) {
		
		for( int i = 0; i < expr_list.length; i++ ) {
			
			this.condition.append( MysqlStatement.valueOf( type.name() ).getName() )
							.append( Statement.expr( expr_list[ i ] ) );
		}		
		
		return this;
	}
	
	@Override
	public ICond append( final MysqlStatement type, final ICond... cond_list ) {
		
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
	public Map<Enum<?>, String> getPlaceHolders() {
		return this.place_holders;
	}
	
	@Override
	public String build() {
		return this.condition.toString();
	}

}
