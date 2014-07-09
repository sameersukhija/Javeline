package com.lumata.common.testing.orm;

public interface INotIn {

	IExprFV not_in( Object... values );
	
	IExprFV not_in( Statement statement );
	
}
