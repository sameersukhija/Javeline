package com.g4s.common.testing.orm;

public interface IGt {
	
	IExprFV gt();
	
	IExprFV gt( Object value );
	
	IExprFV gt( ISelect select );
		
	IExprFF gt( Enum<?> field );
		
}
