package com.lumata.common.testing.orm;

public interface IIsNot {
	
	IExprFV is_not();
	
	IExprFV is_not( Object value );
	
	IExprFV is_not( ISelect select );
	
	IExprFF is_not( Enum<?> field );
	
}
