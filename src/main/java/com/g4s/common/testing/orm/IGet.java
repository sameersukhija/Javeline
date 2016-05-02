package com.g4s.common.testing.orm;

public interface IGet {
	
	IExprFV get();
	
	IExprFV get( Object value );
	
	IExprFV get( ISelect select );
	
	IExprFF get( Enum<?> field );
	
}
