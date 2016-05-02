package com.g4s.common.testing.orm;

public interface IMysqlDump {
	
	IMysqlOption user( String user );
	IMysqlOption password( String password );
	IMysqlOption password();
	IMysqlOption host( String host );
	IMysqlOption port( String port );
	
}
