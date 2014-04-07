package com.lumata.common.testing.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
	
	public static int getTableUpdatesCount( String table, String dateField, Calendar minReferenceDate, Calendar maxReferenceDate, Mysql mysql ) throws SQLException {
		
		int table_size_updates = -1;
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		
		String query = "SELECT COUNT(*) as COUNT FROM " + table + " WHERE " + dateField + " >= '" + sdf.format( minReferenceDate.getTime() ) + "' AND " + dateField  + " <= '" + sdf.format( maxReferenceDate.getTime() ) + "' ;";
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
		
			if( rs.next() ) { 
				
				table_size_updates = rs.getInt( "COUNT" ); 
				
			}
					
			logger.debug( "The table " + table + " has size " + table_size_updates );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return table_size_updates;
		
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
	
	public static boolean tableHasColumn( String table, String column, Mysql mysql ) {
		
		String query = "SHOW COLUMNS FROM " + table + " LIKE '" + column + "';";
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
		
			if( rs.next() ) { 
					
				logger.debug( "The table " + table + "has column ( " + column + " )" );
		
				return true;
			}
			
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		logger.debug( "The table " + table + "has not column ( " + column + " )" );
				
		return false;  
				
	}
	
	public static ArrayList<String> getTableColumnsByTypes( String table, ArrayList<MysqlColumn.MysqlTypes> columnTypes, Mysql mysql ) {
		
		ArrayList<String> columns = new ArrayList<String>();
		
		StringBuilder whereCond = new StringBuilder();
		
		for( int cltIndex = 0; cltIndex < columnTypes.size(); cltIndex++ ) {
			
			whereCond.append( "'" ).append( columnTypes.get( cltIndex ).name().toLowerCase() ).append( "', " );
			
		}
		
		whereCond.setLength( whereCond.length() - 2 );
				
		String query = "SHOW COLUMNS FROM " + table + " WHERE " + MysqlColumn.Fields.TYPE + " IN ( " + whereCond + " );";
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
		
			while( rs.next() ) { 
					
				columns.add( rs.getString( MysqlColumn.Fields.FIELD.name() ) );
				
			}
			
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
			
		return columns;
		
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
