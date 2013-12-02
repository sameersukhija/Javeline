package com.lumata.common.testing.orm;

public interface IGt {
	
	IExprFV gt();
	
	IExprFV gt( Object value );
	
	IExprFF gt( Enum<?> field );
	
}
