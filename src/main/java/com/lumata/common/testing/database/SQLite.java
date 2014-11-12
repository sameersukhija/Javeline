package com.lumata.common.testing.database;

import java.io.File;
import java.sql.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.DataBaseException;
import com.lumata.common.testing.system.DataSource;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class SQLite extends DataBase implements ISQLite {

	private static final  Logger logger = LoggerFactory.getLogger( SQLite.class );
		
	public SQLite() {
			
		super();
		
	}
	
	public SQLite( Properties dataSource ) {
					
		super( dataSource );
		
		this.init();
		
		logger.debug( "SQLite parameters has been loaded");
		
		this.connect();
		
	}
	
	public SQLite( JSONObject dataSource ) {
		
		this( new DataSource( dataSource ) );
				
	}
	
	public SQLite( DataSource dataSource ) {
		
		super( dataSource );
		
		this.init();
		
		this.connect();
		
	}
	
	private void init() {
		
		if( !this.dbHost.matches( "(^absolute|^relative):.*" ) ) { throw new DataBaseException( "the host parameter is not valid, only absolute:<path> or relative:<path> are allowed" ); }
		
		this.dbHost = this.dbHost.
						replace( "absolute:" , "" ).
						replace( "relative:" , System.getProperty( "user.dir" ) + ( new File( "/src/main/resources/databases/" ) ) );
				
		Pattern pattern = Pattern.compile( "[{](.+)[}]" );
		
		Matcher matcher = pattern.matcher( this.dbHost );
		
		while( matcher.find() ) {
			
			this.dbHost = this.dbHost.replace( matcher.group( 0 ), System.getProperty( matcher.group( 1 ) ) );
			
		}
				
	}
	
	/**
	 * Open connection with local SQLite database setting an absolute or relative path ( <usr.dir>/src/main/resources/databases/ ).
	 * 
	 * @throws DataBaseException in case of error
	 */
	public void connect() {
		 
		StringBuilder url = new StringBuilder();
				
		url.append( "jdbc:sqlite:" )
			.append( this.dbHost )
			.append( this.dbName );
				
		try {
			
			Class.forName("org.sqlite.JDBC");
			
			this.dbConn = DriverManager.getConnection( url.toString() );
			
			logger.debug( "The connection has been established ( " + url + " ) ");
			
		} catch ( ClassNotFoundException | SQLException e1 ) {	
			
			logger.error( e1.getMessage(), e1 );
						
			try {
				
				logger.error("Error during DB connection procedure.");
				
				logger.debug("Check connection and eventually close it.");
				
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
	
	@Override
	public ResultSet execQuery( final String query ) {
		return super.execQuery( query );
	}
	
	@Override
	public int execUpdate( final String query ) {
		return super.execUpdate( query );
	}
	
	@Override
	public void close() {
		super.close();
	}
	
	@Override
	public Connection getConnection() {
		return super.getConnection();
	}
	
	@Override
	public String getHost() {
		return super.getHost();
	}
	
	@Override
	public String getName() {
		return super.getName();
	}
	
	@Override
	public void setConnection( final Connection dbConn ) {
		super.setConnection( dbConn );
	}
	
	@Override
	public void setHost( final String dbHost ) {
		super.setHost( dbHost );
	}
	
	@Override
	public void setName( final String dbName ) {
		super.setName( dbName );
	}
	
}
