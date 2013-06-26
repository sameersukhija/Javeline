package com.lumata.common.testing.database;

import java.sql.*;
import java.util.Map;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:arcangelo.dipasquale@gmail.com">Arcangelo Di Pasquale</a>
 * 
 */
public class Mysql {

	private static final  Logger logger = LoggerFactory.getLogger( Mysql.class );
		
	private Connection dbConn;
	private String dbHost;
	private String dbName;
	private int dbPort; 
	private String dbUser;
	private String dbPasswd;
		
	public Mysql() {
			
		try {
		
			this.dbConn = null;
			this.dbHost = System.getProperty("host");
			this.dbName = System.getProperty("name");
			this.dbPort = Integer.parseInt(System.getProperty("port"));
			this.dbUser = System.getProperty("user");
			this.dbPasswd = System.getProperty("password");		
			
			logger.debug( "Mysql parameters has been loaded");
			
			this.connect();
					
		} catch( Exception e ) {
			logger.error( e.getMessage());
		}
		
	}
	
	public Mysql( Properties dataSource ) {
					
		this.dbConn = null;
		this.dbHost = dataSource.getProperty("host");
		this.dbName = dataSource.getProperty("name");
		this.dbPort = Integer.parseInt(dataSource.getProperty("port"));
		this.dbUser = dataSource.getProperty("user");
		this.dbPasswd = dataSource.getProperty("password");		
			
		logger.debug( "Mysql parameters has been loaded");
		
		this.connect();
		
	}
	
	public Mysql( JSONObject dataSource ) {
		
		try {
			this.dbConn = null;
			this.dbHost = dataSource.getString("host");
			this.dbName = dataSource.getString("name");
			this.dbPort = dataSource.getInt("port");
			this.dbUser = dataSource.getString("user");
			this.dbPasswd = dataSource.getString("password");		
				
			logger.debug( "Mysql parameters has been loaded");
			
			this.connect();
		} catch( JSONException e ) {
			logger.error( e.getMessage());
		}
		
	}
	
	public void connect() {
		 
		String url = "jdbc:mysql://" + this.dbHost + ":" + this.dbPort + "/" + this.dbName;

		try {
			
			this.dbConn = DriverManager.getConnection( url, this.dbUser, this.dbPasswd);
			
			logger.debug( "The connection has been established");
			
		} catch (SQLException e) {			
			logger.error( e.getMessage());
		}	
		 
	}
	
	public ResultSet execQuery( String query ) {
				 
		ResultSet rs = null;
		
		try {
			
			Statement statement = this.dbConn.createStatement();
			rs = statement.executeQuery( query );
			
			logger.debug( "The statement has been executed");
			
		} catch (SQLException e) {
			logger.error( e.getMessage());		 
		}		
		 
		return rs;
		
	}
	
	public int execUpdate( String query ) {
		 
		int rs = -1;
		
		try {
			
			Statement statement = this.dbConn.createStatement();
			rs = statement.executeUpdate( query, Statement.RETURN_GENERATED_KEYS );		
			
			logger.debug( "The statement has been executed");
			
		} catch (SQLException e) {
			logger.error( e.getMessage());	 
		}		
		 
		return rs;
		
	}
	
	public void close() {
		 
		try { 
			
			this.dbConn.close();	
			
			logger.debug( "The connection has been closed");
			
		} catch (SQLException e) {
			logger.error( e.getMessage());
		}
				 
	}
	
}
