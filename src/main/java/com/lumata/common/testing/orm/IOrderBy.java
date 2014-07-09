package com.lumata.common.testing.orm;

public interface IOrderBy extends IQueryTemplate, IBuild, IStatement {

	ILimit limit( Integer... limit );
	
}
