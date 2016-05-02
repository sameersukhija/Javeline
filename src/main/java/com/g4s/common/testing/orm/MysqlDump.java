package com.g4s.common.testing.orm;

import com.g4s.common.testing.orm.MysqlOption.MysqlDumpOption;

public class MysqlDump implements IMysqlDump {

	Dump dump;
	
	MysqlDump( Dump dump ) {
		this.dump = dump;
	}
	
	@Override
	public IMysqlOption user( String user ) {
		return new MysqlOption( MysqlDumpOption.User, user, dump );
	}
	
	@Override
	public IMysqlOption password( String password ) {
		return new MysqlOption( MysqlDumpOption.Password, password, dump );
	}
	
	@Override
	public IMysqlOption password() {
		return new MysqlOption( MysqlDumpOption.Password, dump );
	}
	
	@Override
	public IMysqlOption host( String host ) {
		return new MysqlOption( MysqlDumpOption.Host, host, dump );
	}
	
	@Override
	public IMysqlOption port( String port ) {
		return new MysqlOption( MysqlDumpOption.Port, port, dump );
	}

}
