package com.lumata.common.testing.orm;

public interface IWhere extends IQueryTemplate, IBuild, IStatement {

	IGroupBy groupBy( Enum<?>... order );
	
	IHaving having( IExprFV expr );
	
	IHaving having( IExprFV expr, ICond... cond );
	
	IOrderBy orderBy( Enum<?>... order );
	
	ILimit limit( Integer... limit );
	
}
