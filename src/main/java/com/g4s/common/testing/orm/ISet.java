package com.g4s.common.testing.orm;

public interface ISet extends IQueryTemplate, IBuild, IStatement {
	
	IWhere where( IExprFV expr );
	
	IWhere where( IExprFV expr, ICond... cond );
	
	IWhere where( final IExprFF expr );
	
	IWhere where( final IExprFF expr, ICond... cond );
	
}
