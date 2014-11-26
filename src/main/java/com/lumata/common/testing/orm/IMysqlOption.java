package com.lumata.common.testing.orm;

public interface IMysqlOption {
	
	IMysqlOption user( String user );
	IMysqlOption password( String password );
	IMysqlOption password();
	IMysqlOption host( String host );
	IMysqlOption port( String port );
	
}
