package com.lumata.common.testing.orm;

public interface IJoin {
	
	IOn on( IExprFF expr );
	
	//IOn on( IExprFF expr, ICondFF... cond );
	
	IOn on( IExprFF expr, ICond... cond );
	
	ILimit limit( Integer... limit );
		
}
