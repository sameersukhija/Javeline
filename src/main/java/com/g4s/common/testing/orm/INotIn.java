package com.g4s.common.testing.orm;

public interface INotIn {

	IExprFV not_in( Object... values );
	
	IExprFV not_in( Statement statement );
	
}
