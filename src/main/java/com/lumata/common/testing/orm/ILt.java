package com.lumata.common.testing.orm;

public interface ILt {
	
	IExprFV lt();
	
	IExprFV lt( Object value );
	
	IExprFV lt( ISelect select );

	IExprFF lt( Enum<?> field );
		
}
