package com.lumata.common.testing.orm;

public interface IHaving extends IQueryTemplate, IBuild {

	IOrderBy orderBy( Enum<?>... order );
	
	ILimit limit( Integer... limit );
	
}
