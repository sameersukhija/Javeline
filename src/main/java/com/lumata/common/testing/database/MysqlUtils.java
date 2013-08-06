package com.lumata.common.testing.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class MysqlUtils {

	private static final  Logger logger = LoggerFactory.getLogger( MysqlUtils.class );
		
	public static boolean isTable( String table, Mysql mysql ) {
		
		String query = "SHOW TABLES LIKE '" + table + "';";
		
		boolean check = false; 
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
		
			if( rs.next() ) { check = true; }
					
			logger.info( "The table " + table + " is " + ( check == true ? "present" : "not present" ) );
		
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
					
			logger.info( "The column of the table " + table + " has max id equals to " + maxID.toString() );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return maxID;
		
	}
	
}
