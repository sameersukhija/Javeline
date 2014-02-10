package com.lumata.common.testing.orm;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class Delete implements IDelete {

	Statement statement;
	
	Delete( Statement statement ) {
		this.statement = statement;
	}
	
	@Override
	public IFrom from( final Object entity ) {
		
		Table table = (Table)entity.getClass().getAnnotation( Table.class );
		
		this.statement.addEntity( entity, table.value() );
		
		this.statement.append( MysqlStatement.FROM.getName() )
						.append( table.value() );
		
		return new From(statement);
	
	}

}
