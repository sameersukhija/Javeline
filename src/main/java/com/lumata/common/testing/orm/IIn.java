package com.lumata.common.testing.orm;

public interface IIn {

	IExprFV in( Object... values );
	
	IExprFV in( Statement statement );
	
}
