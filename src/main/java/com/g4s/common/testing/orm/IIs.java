package com.g4s.common.testing.orm;

public interface IIs {
	
	IExprFV is();
	
	IExprFV is( Object value );
	
	IExprFV is( Sub select );
	
	IExprFF is( Enum<?> field );
	
}
