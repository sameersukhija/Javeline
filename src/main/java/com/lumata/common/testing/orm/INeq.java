package com.lumata.common.testing.orm;

public interface INeq {
	
	IExprFV neq();
	
	IExprFV neq( Object value );
	
	IExprFV neq( ISelect select );
	
	IExprFF neq( Enum<?> field );
	
}
