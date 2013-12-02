package com.lumata.common.testing.orm;

public interface IEq {
	
	IExprFV eq();
	
	IExprFV eq( Object value );
	
	IExprFF eq( Enum<?> field );
	
}
