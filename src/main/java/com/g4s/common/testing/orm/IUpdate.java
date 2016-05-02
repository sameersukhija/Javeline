package com.g4s.common.testing.orm;

public interface IUpdate {
	
	ISet set();
	
	ISet set( final IExprFV... expr );
	
}
