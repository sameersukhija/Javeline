package com.g4s.common.testing.orm;

public interface IGroupBy extends IQueryTemplate, IBuild, IStatement {

	IHaving having( IExprFV expr );
	
	IHaving having( IExprFV expr, ICond... cond );
	
	IOrderBy orderBy( Enum<?>... order );
	
	ILimit limit( Integer... limit );
	
}
