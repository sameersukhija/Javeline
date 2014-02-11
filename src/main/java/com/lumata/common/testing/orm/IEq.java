package com.lumata.common.testing.orm;

public interface IEq {
	
	IExprFV eq();
	
	IExprFV eq( Object value );
	
	IExprFV eq( ISelect select );
	
	IExprFF eq( Enum<?> field );
	
}
