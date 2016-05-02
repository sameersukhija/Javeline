package com.g4s.common.testing.orm;

public interface ISelect {
	
	IFrom from( Object entity );
	
	IFrom from( Object entity, String suffix );
	
}
