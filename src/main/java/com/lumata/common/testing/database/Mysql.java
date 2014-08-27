package com.lumata.common.testing.database;

import java.sql.*;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.DataBaseException;
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
	private Integer dbPort; 
	private String dbUser;
	private String dbPasswd;
				
	public Mysql() {
			
		try {
		
			this.dbConn = null;
			this.dbHost = null;
			this.dbName = null;
			this.dbPort = null;
			this.dbUser = null;
			this.dbPasswd = null;		
								
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
	
	/**
	 * Open connection with remote MySql database.
	 * 
	 * @throws DataBaseException in case of error
	 */
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
				
				logger.error("Error during DB connection procedure.");
				logger.error("Check connection and eventually close it.");
				
				if( this.dbConn != null ) { 
					this.dbConn.close(); 
					logger.error("DB connectio closed.");
				}
				
			} catch ( SQLException e2 ) {
				
				logger.error("During closing unstable DB connection, error occurs : ");
				logger.error( e2.getMessage(), e2 );
				
				/**
				 * Error during closing unstable connection.
				 */
				throw new DataBaseException("Error during closing unstable connection : " + e2.getMessage());
			}
			
			/**
			 * Error during connection and connection is closed -> throw DataBaseException
			 */
			throw new DataBaseException("DB Connection error : " + e1.getMessage());
		}
		 
	}
	
	/**
	 * Executes query on remote MySql database and returns ResultSet.
	 * 
	 * @throws DataBaseException in case of error
	 */
	public ResultSet execQuery( String query ) {
				 
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
	 * Executes update query on remote MySql database and returns ResultSet.
	 * 
	 * @throws DataBaseException in case of error
	 */	
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
	
	public void close() {
		 
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
	
	public MysqlUtils getCommands() {
		return new MysqlUtils( this );
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
