package com.g4s.common.testing.database;

import java.sql.Connection;
import java.sql.ResultSet;

public interface ISQLite {

	public void connect();
	public ResultSet execQuery( final String query );
	public int execUpdate( final String query );
	public void close();
	public Connection getConnection();
	public String getHost();
	public String getName();
	public void setConnection( final Connection dbConn );
	public void setHost( final String dbHost );
	public void setName( final String dbName );
	
}
