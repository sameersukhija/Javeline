package com.g4s.common.testing.orm;

public interface IIn {

	IExprFV in( Object... values );
	
	IExprFV in( Statement statement );
	
}
