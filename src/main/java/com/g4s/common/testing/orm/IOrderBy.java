package com.g4s.common.testing.orm;

public interface IOrderBy extends IQueryTemplate, IBuild, IStatement {

	ILimit limit( Integer... limit );
	
}
