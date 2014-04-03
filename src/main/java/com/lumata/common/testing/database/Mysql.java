package com.lumata.common.testing.database;

import java.sql.*;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.system.DataSource;
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
		
		this( new DataSource( dataSource ) );
				
	}
	
	public Mysql( DataSource dataSource ) {
		
		try {
			if( dataSource == null ) { System.out.println( "NULL" ); }
			this.setConnection( null );
			this.setHost( dataSource.getHostAddress() );
			this.setName( dataSource.getHostName() );
			this.setPort( dataSource.getHostPort() );
			this.setUser( dataSource.getUser() );
			this.setPassword( Security.decrypt( dataSource.getPassword() ) );
							
			logger.debug( "Mysql parameters has been loaded");
			
			this.connect();
		
		} catch( JSONException e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public void connect() {
		 
		StringBuilder url = new StringBuilder();
		
		url.append( "jdbc:mysql://" )
			.append( this.dbHost ).append( ":" )
			.append( this.dbPort ).append( "/" )
			.append( this.dbName );
			
		try {
			
			this.dbConn = DriverManager.getConnection( url.toString(), this.dbUser, this.dbPasswd);
			
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
	
	public Connection getConnection() {
		return this.dbConn;
	}
	
	public String getHost() {
		return this.dbHost;
	}
	
	public String getName() {
		return this.dbName;
	}
	
	public int getPort() {
		return this.dbPort;
	}
	
	public String getUser() {
		return this.dbUser;
	}
	
	public String getPassword() {
		return this.dbPasswd;
	}
	
	public void setConnection( Connection dbConn ) {
		this.dbConn = dbConn;
	}
	
	public void setHost( String dbHost ) {
		this.dbHost = dbHost;
	}
	
	public void setName( String dbName ) {
		this.dbName = dbName;
	}
	
	public void setPort( int dbPort ) {
		this.dbPort = dbPort;
	}
	
	public void setUser( String dbUser ) {
		this.dbUser = dbUser;
	}
	
	public void setPassword( String dbPasswd ) {
		this.dbPasswd = dbPasswd;
	}
	
}
