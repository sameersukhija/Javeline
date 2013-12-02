package com.lumata.common.testing.orm;

import java.util.List;

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
	
	public static IInsert insert() {
		
		return insert( null );
		
	}

	public static IInsert insert( final List<String> fields ) {
		
		Statement statement = new Statement();
		//statement.setFields(fields);
		
		return new Insert(statement);
		
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
