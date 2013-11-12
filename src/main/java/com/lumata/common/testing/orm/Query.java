package com.lumata.common.testing.orm;

import java.util.List;

import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class Query {

	public static ISelect select() {
				
		return select( null );
		
	}

	public static ISelect select( final List<String> fields ) {
		
		Statement statement = new Statement(MysqlStatement.SELECT);
		statement.setFields(fields);
		
		return new Select(statement);
		
	}
	
	public static IInsert insert() {
		
		return insert( null );
		
	}

	public static IInsert insert( final List<String> fields ) {
		
		Statement statement = new Statement(MysqlStatement.INSERT_INTO);
		statement.setFields(fields);
		
		return new Insert(statement);
		
	}
	
	public static IUpdate update() {
		
		return update( null );
		
	}

	public static IUpdate update( final List<String> fields ) {
		
		Statement statement = new Statement(MysqlStatement.UPDATE);
		statement.setFields(fields);
		
		return new Update(statement);
		
	}
	
	public static IDelete delete() {
		
		Statement statement = new Statement(MysqlStatement.DELETE);
		
		return new Delete(statement);
		
	}
	
}
