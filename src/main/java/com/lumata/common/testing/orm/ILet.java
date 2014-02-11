package com.lumata.common.testing.orm;

public interface ILet {
	
	IExprFV let();
	
	IExprFV let( Object value );
	
	IExprFV let( ISelect select );
		
	IExprFF let( Enum<?> field );
		
}
