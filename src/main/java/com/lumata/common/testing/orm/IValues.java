package com.lumata.common.testing.orm;

public interface IValues extends IQueryTemplate, IBuild {

	IOnDuplicateKeyUpdate on_duplicate_key_update( final IExprFV... expr );
	
}
