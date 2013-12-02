package com.lumata.common.testing.orm;

public interface IGroupBy extends IQueryTemplate, IBuild {

	IHaving having( IExprFV expr );
	
	IHaving having( IExprFV expr, ICondFV... cond );
	
	IOrderBy orderBy( Enum<?>... order );
	
	ILimit limit( Integer... limit );
	
}
