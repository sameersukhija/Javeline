package com.lumata.common.testing.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class MysqlUtils {

	private static final  Logger logger = LoggerFactory.getLogger( MysqlUtils.class );
	
	public static ArrayList<String> getSchema( Mysql mysql ) throws SQLException {
		
		ArrayList<String> schema = new ArrayList<String>();
		
		String query = "SELECT TABLE_NAME FROM information_schema.tables WHERE table_schema = DATABASE();";
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
		
			while( rs.next() ) { 
				
				schema.add( rs.getString( "TABLE_NAME" ) ); 
				
			}
					
			logger.info( "The schema has been loaded" );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return schema;
		
	}
	
	public static int getTableSize( String table, Mysql mysql ) throws SQLException {
		
		int table_size = -1;
		
		String query = "SELECT COUNT(*) as COUNT FROM " + table + ";";
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
		
			if( rs.next() ) { 
				
				table_size = rs.getInt( "COUNT" ); 
				
			}
					
			logger.debug( "The table " + table + " has size " + table_size );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return table_size;
		
	}

	public static boolean isTable( String table, Mysql mysql ) {
		
		String query = "SHOW TABLES LIKE '" + table + "';";
		
		boolean check = false; 
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
		
			if( rs.next() ) { check = true; }
					
			logger.debug( "The table " + table + " is " + ( check == true ? "present" : "not present" ) );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return check;  
				
	}
	
	public static Integer getMaxID( String table, String column, Mysql mysql ) throws SQLException {
				
		String query = "SELECT MAX( " + column + " ) MAXID FROM " + table + ";";
		
		Integer maxID = -1; 
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
		
			if( rs.next() ) { maxID = rs.getInt( "MAXID" ); }
					
			logger.debug( "The column of the table " + table + " has max id equals to " + maxID.toString() );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return maxID;
		
	}
	
	public static ArrayList<MysqlKeys> getKeys( String table, Mysql mysql ) throws SQLException {
		
		ArrayList<MysqlKeys> keys = new ArrayList<MysqlKeys>();
		
		String query = "SHOW KEYS FROM " + table + " WHERE Key_name = 'PRIMARY'";
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
		
			while( rs.next() ) { 
				
				keys.add( new MysqlKeys( rs ) ); 
				
			}
					
			logger.info( "The keys has been loaded" );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return keys;
		
	}
	
}
