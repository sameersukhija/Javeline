package com.lumata.common.testing.orm;

public interface ILt {
	
	IExprFV lt();
	
	IExprFV lt( Object value );
	
	IExprFF lt( Enum<?> field );
	
}
