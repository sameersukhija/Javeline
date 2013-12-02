package com.lumata.common.testing.orm;

public interface IOrderBy extends IQueryTemplate, IBuild {

	ILimit limit( Integer... limit );
	
}
