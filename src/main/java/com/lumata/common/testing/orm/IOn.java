package com.lumata.common.testing.orm;

public interface IOn extends IBuild, IStatement {

	IWhere where( IExprFV expr );
	
	IWhere where( IExprFV expr, ICond... cond );
	
	IJoin join( Object entity );
	
	IGroupBy groupBy( Enum<?>... order );
	
	IHaving having( IExprFV expr );
	
	IHaving having( IExprFV expr, ICond... cond );
	
	IOrderBy orderBy( Enum<?>... order );
	
	ILimit limit( Integer... limit );
	
}
