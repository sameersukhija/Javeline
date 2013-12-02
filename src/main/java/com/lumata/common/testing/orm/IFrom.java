package com.lumata.common.testing.orm;

public interface IFrom extends IQueryTemplate, IBuild {
	
	IWhere where( IExprFV expr );
	
	IWhere where( IExprFV expr, ICondFV... cond );
	
	IJoin join( Object entity );
	
	IGroupBy groupBy( Enum<?>... order );
	
	IHaving having( IExprFV expr );
	
	IHaving having( IExprFV expr, ICondFV... cond );
	
	IOrderBy orderBy( Enum<?>... order );
	
	ILimit limit( Integer... limit );
	
}
