package com.lumata.common.testing.orm;

public interface IFrom extends IQueryTemplate, ISub, IBuild, IStatement {
	
	IWhere where( final IExprFV expr );
	
	IWhere where( final IExprFV expr, ICond... cond );
	
	IJoin join( final Object entity );
	
	IGroupBy groupBy( final Enum<?>... order );
	
	IHaving having( final IExprFV expr );
	
	IHaving having( final IExprFV expr, ICond... cond );
	
	IOrderBy orderBy( final Enum<?>... order );
	
	ILimit limit( final Integer... limit );
	
}
