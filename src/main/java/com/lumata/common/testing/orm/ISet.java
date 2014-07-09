package com.lumata.common.testing.orm;

public interface ISet extends IQueryTemplate, IBuild, IStatement {
	
	IWhere where( IExprFV expr );
	
	IWhere where( IExprFV expr, ICond... cond );
	
}
