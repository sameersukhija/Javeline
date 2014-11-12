package com.lumata.common.testing.database;

import java.sql.*;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.DataBaseException;
import com.lumata.common.testing.system.DataSource;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public abstract class DataBase {

	private static final  Logger logger = LoggerFactory.getLogger( DataBase.class );
		
	protected Connection dbConn;
	protected String dbHost;
	protected String dbName;
	protected Integer dbPort; 
	protected String dbUser;
	protected String dbPasswd;
				
	public DataBase() {
			
		this.dbConn = null;
		this.dbHost = null;
		this.dbName = null;
		
	}
	
	public DataBase( Properties dataSource ) {
					
		this.dbConn = null;
		this.dbHost = dataSource.getProperty("host");
		this.dbName = dataSource.getProperty("name");
				
	}
	
	public DataBase( JSONObject dataSource ) {
		
		this( new DataSource( dataSource ) );
				
	}
	
	public DataBase( DataSource dataSource ) {
		
		try {
			
			if( null != dataSource ) { 
			
				this.setConnection( null );
				this.setHost( ( null != dataSource.getHostAddress() ? dataSource.getHostAddress() : null ) );
				this.setName( ( null != dataSource.getHostName() ? dataSource.getHostName() : null ) );
							
			}
		
		} catch( JSONException e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}

	/**
	 * Open connection with remote MySql database.
	 * 
	 * @throws DataBaseException in case of error
	 */
	protected abstract void connect();
	
	/**
	 * Executes query on remote Database and returns ResultSet.
	 * 
	 * @throws DataBaseException in case of error
	 */
	protected ResultSet execQuery( String query ) {
				 
		ResultSet rs = null;
		Statement statement = null;
		
		try {
			
			statement = this.dbConn.createStatement();
			
			rs = statement.executeQuery( query );
			
			logger.debug( "The statement has been executed ( " + query + " )" );
			
		} catch ( SQLException e1 ) {	
			
			if( statement != null ) { 
				try {
					
					logger.error("Try to recover statment status.");
					statement.close(); 
					
				} catch ( SQLException e2 ) {
					
					logger.error("Error during statment recovery : ");
					logger.error( e2.getMessage(), e2 );
					
					throw new DataBaseException("Execution of query error : " + e2.getMessage());
				} 
			}
						
			logger.error( e1.getMessage(), e1 );
					
			/**
			 * Error during execution of query -> throw DataBaseException
			 */
			throw new DataBaseException("Execution of query error : " + e1.getMessage());
		}		
		 
		return rs;
	}
	
	/**
	 * Executes update query on remote Database and returns ResultSet.
	 * 
	 * @throws DataBaseException in case of error
	 */	
	protected int execUpdate( String query ) {
		 
		int index = -1;
		
		Statement statement = null;
		
		try {
			
			statement = this.dbConn.createStatement();
			
			if( this.equals( Mysql.class ) ) { statement.executeUpdate( query, Statement.RETURN_GENERATED_KEYS ); }
			else { statement.executeUpdate( query );  }
			
			ResultSet rs = statement.getGeneratedKeys();
			
			while( rs.next() ) {
				
				index = rs.getInt( 1 );
			
			}			
			
			logger.debug( "The statement has been executed ( " + query + " )" );
			
		} catch ( SQLException e1 ) {	
			
			if( statement != null ) { 
				try {
					
					logger.error("Try to recover statment status.");
					statement.close(); 
					
				} catch ( SQLException e2 ) {
					
					logger.error("Error during statment recovery : ");
					logger.error( e2.getMessage(), e2 );
					
					throw new DataBaseException("Execution of update query error : " + e2.getMessage());
				} 
			}
			
			logger.error( e1.getMessage(), e1 );
			
			/**
			 * Error during execution of query -> throw DataBaseException
			 */
			throw new DataBaseException("Execution of update query error : " + e1.getMessage());
		}			
		 
		return index;
		
	}
	
	protected void close() {
		 
		try { 
			
			this.dbConn.close();	
			
			logger.debug( "The connection has been closed");
			
		} catch (SQLException e) {

			logger.error( e.getMessage(), e );
			
			/**
			 * Error during close connection -> throw DataBaseException
			 */
			throw new DataBaseException("Execution closing connection error : " + e.getMessage());
		}
				 
	}
	
	protected Connection getConnection() {
		return this.dbConn;
	}
	
	protected String getHost() {
		return this.dbHost;
	}
	
	protected String getName() {
		return this.dbName;
	}
	
	protected int getPort() {
		return this.dbPort;
	}
	
	protected String getUser() {
		return this.dbUser;
	}
	
	protected String getPassword() {
		return this.dbPasswd;
	}
	
	protected void setConnection( Connection dbConn ) {
		this.dbConn = dbConn;
	}
	
	protected void setHost( String dbHost ) {
		this.dbHost = dbHost;
	}
	
	protected void setName( String dbName ) {
		this.dbName = dbName;
	}
	
	protected void setPort( int dbPort ) {
		this.dbPort = dbPort;
	}
	
	protected void setUser( String dbUser ) {
		this.dbUser = dbUser;
	}
	
	protected void setPassword( String dbPasswd ) {
		this.dbPasswd = dbPasswd;
	}
	
}
