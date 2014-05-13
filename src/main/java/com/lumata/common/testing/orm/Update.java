package com.lumata.common.testing.orm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Update implements IUpdate {

	Statement statement;
	
	Update( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public ISet set() {
		
		this.statement.append( Statement.MysqlStatement.SET.getName() );
		
		Enum<?>[] fields = null;
		
		try {
			
			StringBuilder fields_values = new StringBuilder();
			
			for( Object key : this.statement.entities.keySet() ) {
				
				Object entity = this.statement.entities.get(key);
			    
				Method method = entity.getClass().getDeclaredMethod( "getEntityFields" );
				
				fields = (Enum<?>[])method.invoke( entity );
						
				for( int i = 0; i < fields.length; i++ ) {
					
					ExprFV expr = new ExprFV( fields[ i ], Op.Types.eq );
					
					fields_values.append( Statement.expr( expr ) ).append( ", " );
										
					this.statement.addPlaceHolder( fields[i], Statement.getPlaceHolder( fields[i] ).toString() );
					
				}
				
				fields_values.setLength( fields_values.length() - 2 );
				
			}
			
			this.statement.append( fields_values );
				
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		return new Set(statement);
		
	}
	
	@Override
	public ISet set( final IExprFV... expr ) {
		
		this.statement.append( Statement.MysqlStatement.SET.getName() )
						.append( Statement.expr( expr ) );
		
		for( int i = 0; i < expr.length; i++ ) {
			
			if( expr[i].getUsePlaceHolder() ) { this.statement.addPlaceHolder( expr[i].getField(), (String)expr[i].getValue() ); }
		
		}
		
		return new Set(statement);
		
	}

}
