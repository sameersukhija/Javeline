package com.g4s.common.testing.orm;

public interface IJoin {
	
	IOn on( IExprFF expr );
	
	//IOn on( IExprFF expr, ICondFF... cond );
	
	IOn on( IExprFF expr, ICond... cond );
	
	ILimit limit( Integer... limit );
		
}
