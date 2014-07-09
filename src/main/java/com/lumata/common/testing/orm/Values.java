package com.lumata.common.testing.orm;

public class Values implements IValues {

	Statement statement;
	
	Values( Statement statement ) {
		this.statement = statement;		
	}
	
	@Override
	public IOnDuplicateKeyUpdate on_duplicate_key_update( final IExprFV... expr ) {
		
		this.statement.append( Statement.MysqlStatement.ON_DUPLICATE_KEY_UPDATE.getName() )
						.append( Statement.expr( expr ) );			
		
		return new OnDuplicateKeyUpdate(statement);
		
	}
	
	@Override
	public IQueryTemplate template() {
		
		return new QueryTemplate(statement);
				
	}
		
	@Override
	public String build() {
		
		return this.statement.build();
				
	}

	@Override
	public Statement statement() {
		
		return this.statement;
				
	}
	
}
