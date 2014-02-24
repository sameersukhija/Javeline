package com.lumata.common.testing.orm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class Query {

	public static ISelect select() {
		
		Statement statement = new Statement();
		
		statement.setMysqlStatementType( MysqlStatement.SELECT );
		
		statement.append( Statement.MysqlStatement.SELECT.getName() )
			.append( "*" );
		
		return new Select(statement);
		
	}

	public static ISelect select( final Enum<?>... fields ) {
		
		Statement statement = new Statement();
			
		statement.setMysqlStatementType( MysqlStatement.SELECT );
		
		statement.append( Statement.MysqlStatement.SELECT.getName() )
					.append( statement.fields( fields ) );
		
		return new Select(statement);
		
	}
	
	public static IInsert insert( final Object entity ) {
				
		Enum<?>[] fields = null;
		
		try {
			
			Method method = entity.getClass().getDeclaredMethod( "getEntityFields" );
			
			fields = (Enum<?>[])method.invoke( entity );
		
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
		
		return Query.insert( entity, fields );
		
	}
	
	public static IInsert insert( final Object entity, final Enum<?>... fields ) {
		
		Statement statement = new Statement();
		
		statement.setMysqlStatementType( MysqlStatement.INSERT_INTO );
		
		Table table = (Table)entity.getClass().getAnnotation( Table.class );
		
		statement.addEntity( entity, table.value() );
		
		statement.addFields( fields );
		
		statement.append( Statement.MysqlStatement.INSERT_INTO.getName() )
					.append( table.value() )
					.append( " ( " )			
					.append( statement.fields( fields ) )
					.append( " )" );
							
		return new Insert(statement);
		
	}
		
	public static IUpdate update( final Object entity ) {
		
		Statement statement = new Statement();
		
		statement.setMysqlStatementType( MysqlStatement.UPDATE );
		
		Table table = (Table)entity.getClass().getAnnotation( Table.class );
		
		statement.addEntity( entity, table.value() );
		
		statement.append( Statement.MysqlStatement.UPDATE.getName() )
					.append( table.value() );
		
		return new Update(statement);
		
	}
	
	public static IDelete delete() {
		
		Statement statement = new Statement();
		
		statement.setMysqlStatementType( MysqlStatement.DELETE );
		
		statement.append( Statement.MysqlStatement.DELETE.getName().toString().trim() );
		
		return new Delete(statement);
		
	}
	
}
