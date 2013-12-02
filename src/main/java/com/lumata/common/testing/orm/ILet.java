package com.lumata.common.testing.orm;

public interface ILet {
	
	IExprFV let();
	
	IExprFV let( Object value );
	
	IExprFF let( Enum<?> field );
	
}
