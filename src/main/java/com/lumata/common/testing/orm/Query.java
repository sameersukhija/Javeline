package com.lumata.common.testing.orm;

import java.util.List;

import com.lumata.common.testing.annotations.mysql.Table;

public class Query {

	public static ISelect select() {
		
		Statement statement = new Statement();
		
		statement.append( Statement.MysqlStatement.SELECT.getName() )
			.append( "( * )" );
		
		return new Select(statement);
		
	}

	public static ISelect select( final Enum<?>... fields ) {
		
		Statement statement = new Statement();
				
		statement.append( Statement.MysqlStatement.SELECT.getName() )
					.append( statement.fields( fields ) );
		
		return new Select(statement);
		
	}
	
	public static IInsert insert( final Object entity ) {
		
		Statement statement = new Statement();
		
		Table table = (Table)entity.getClass().getAnnotation( Table.class );
		
		statement.addEntity( entity, table.value() );
		
		statement.append( Statement.MysqlStatement.INSERT_INTO.getName() )
					.append( table.value() );
		
		return new Insert(statement, true);
		
	}
	
	public static IInsert insert( final Object entity, final Enum<?>... fields ) {
		
		Statement statement = new Statement();
		
		statement.append( Statement.MysqlStatement.INSERT_INTO.getName() )
					.append( "( " )			
					.append( statement.fields( fields ) )
					.append( " )" );
							
		return new Insert(statement, false);
		
	}
	
	public static IUpdate update() {
		
		return update( null );
		
	}

	public static IUpdate update( final List<String> fields ) {
		
		Statement statement = new Statement();
		//statement.setFields(fields);
		
		return new Update(statement);
		
	}
	
	public static IDelete delete() {
		
		Statement statement = new Statement();
		
		return new Delete(statement);
		
	}
	
}
