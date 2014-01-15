package com.lumata.common.testing.orm;

public interface IIsNot {
	
	IExprFV is_not();
	
	IExprFV is_not( Object value );
	
	IExprFF is_not( Enum<?> field );
	
}
