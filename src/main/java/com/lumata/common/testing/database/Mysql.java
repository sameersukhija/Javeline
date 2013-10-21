package com.lumata.common.testing.database;

import java.sql.*;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.system.Security;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
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
			this.dbPasswd = Security.decrypt( System.getProperty("password") );		
			
			logger.debug( "Mysql parameters has been loaded");
			
			this.connect();
					
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
	}
	
	public Mysql( Properties dataSource ) {
					
		this.dbConn = null;
		this.dbHost = dataSource.getProperty("host");
		this.dbName = dataSource.getProperty("name");
		this.dbPort = Integer.parseInt(dataSource.getProperty("port"));
		this.dbUser = dataSource.getProperty("user");
		this.dbPasswd = Security.decrypt( dataSource.getProperty("password") );		
			
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
			this.dbPasswd = Security.decrypt( dataSource.getString("password") );		
				
			logger.debug( "Mysql parameters has been loaded");
			
			this.connect();
		
		} catch( JSONException e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public void connect() {
		 
		String url = "jdbc:mysql://" + this.dbHost + ":" + this.dbPort + "/" + this.dbName;

		try {
			
			this.dbConn = DriverManager.getConnection( url, this.dbUser, this.dbPasswd);
			
			logger.debug( "The connection has been established ( " + url + " ) ");
			
		} catch ( SQLException e1 ) {	
			
			logger.error( e1.getMessage(), e1 );
						
			try {
				
				if( this.dbConn != null ) { this.dbConn.close(); }
				
			} catch ( SQLException e2 ) {
				
				logger.error( e2.getMessage(), e2 );
				
			}
			
		}	
		 
	}
	
	public ResultSet execQuery( String query ) {
				 
		ResultSet rs = null;
		Statement statement = null;
		
		try {
			
			statement = this.dbConn.createStatement();
			
			rs = statement.executeQuery( query );
			
			logger.debug( "The statement has been executed ( " + query + " )" );
			
		} catch ( SQLException e1 ) {	
			
			if( statement != null ) { try { statement.close(); } catch ( SQLException e2 ) {} }
						
			logger.error( e1.getMessage(), e1 );
					
		}		
		 
		return rs;
		
	}
	
	public int execUpdate( String query ) {
		 
		int index = -1;
		
		Statement statement = null;
		
		try {
			
			statement = this.dbConn.createStatement();
			
			statement.executeUpdate( query, Statement.RETURN_GENERATED_KEYS );
			
			ResultSet rs = statement.getGeneratedKeys();
			
			while( rs.next() ) {
				
				index = rs.getInt( 1 );
			
			}			
			
			logger.debug( "The statement has been executed ( " + query + " )" );
			
		} catch ( SQLException e1 ) {	
			
			if( statement != null ) { try { statement.close(); } catch ( SQLException e2 ) {} }
						
			logger.error( e1.getMessage(), e1 );
			
		}			
		 
		return index;
		
	}
	
	public void close() {
		 
		try { 
			
			this.dbConn.close();	
			
			logger.debug( "The connection has been closed");
			
		} catch (SQLException e) {

			logger.error( e.getMessage(), e );
		
		}
				 
	}
	
}
