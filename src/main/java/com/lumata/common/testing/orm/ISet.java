package com.lumata.common.testing.orm;

public interface ISet extends IQueryTemplate, IBuild {
	
	IWhere where( IExprFV expr );
	
	IWhere where( IExprFV expr, ICondFV... cond );
	
}
