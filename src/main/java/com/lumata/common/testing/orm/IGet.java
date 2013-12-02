package com.lumata.common.testing.orm;

public interface IGet {
	
	IExprFV get();
	
	IExprFV get( Object value );
	
	IExprFF get( Enum<?> field );
	
}
