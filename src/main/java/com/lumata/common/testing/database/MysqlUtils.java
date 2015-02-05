package com.lumata.common.testing.database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.exceptions.DataBaseException;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class MysqlUtils {

	private static final  Logger logger = LoggerFactory.getLogger( MysqlUtils.class );
	
	Mysql mysql;
	
	public MysqlUtils( Mysql mysql ) {
		this.mysql = mysql;
	}
	
	public Boolean startSlave() {
		
		String query = "START SLAVE;";
		
		mysql.execQuery( query );
		
		return isSlaveRunning();
		
	}
	
	public Boolean stopSlave() {
		
		String query = "STOP SLAVE;";
		
		mysql.execQuery( query );
		
		return !isSlaveRunning();
		
	}
	
	public Boolean resetSlave() {
		
		String query = "RESET SLAVE;";
		
		mysql.execQuery( query );
		
		return true;
		
	}
	
	public Boolean isSlaveRunning() throws DataBaseException {
		
		String query = "SHOW SLAVE STATUS;";
		
		boolean Slave_IO_Running = false;
		
		boolean Slave_SQL_Running = false;
		
		boolean Last_IO_Errno = true;
		
		boolean Last_SQL_Errno = true;
		
		try {
			
			ResultSet rs = mysql.execQuery( query );
		
			while( rs.next() ) { 
				
				String lastIOError = rs.getString( "Last_IO_Error" );
				
				String lastSQLError = rs.getString( "Last_SQL_Error" );
				
				if( rs.getString( "Slave_IO_Running" ).equals( "Yes" ) ) { Slave_IO_Running = true; }
				
				if( rs.getString( "Slave_SQL_Running" ).equals( "Yes" ) ) { Slave_SQL_Running = true; }
				
				if( rs.getString( "Last_IO_Errno" ).equals( "0" ) && ( null == lastIOError || lastIOError.length() == 0 ) ) { Last_IO_Errno = false; }
				
				if( rs.getString( "Last_SQL_Errno" ).equals( "0" ) && ( null == lastSQLError || lastSQLError.length() == 0 ) ) { Last_SQL_Errno = false; }
				
			}
					
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new DataBaseException( e.getMessage() );
			
		}
		
		return ( Slave_IO_Running && Slave_SQL_Running && !Last_IO_Errno && !Last_SQL_Errno );
		
	}
	
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
				
		String query = "SHOW COLUMNS FROM " + table + " WHERE " + MysqlColumn.Fields.COLUMN_TYPE + " IN ( " + whereCond + " );";
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
		
			while( rs.next() ) { 
					
				columns.add( rs.getString( MysqlColumn.Fields.COLUMN_NAME.name() ) );
				
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
	
	public static Calendar getCurrentDate( Mysql mysql ) throws SQLException, ParseException {
		
		Calendar currentDate = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String query = "SELECT NOW() AS currentDate";
		
		try {
		
			ResultSet rs = mysql.execQuery( query );
		
			while( rs.next() ) { 
								
				currentDate.setTime( sdf.parse( rs.getString( "currentDate" ) ) ); 
				
			}
					
			logger.info( "The keys has been loaded" );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return currentDate;

		
	}
	
	public static void clone( Mysql mysqlOrigin, Mysql mysqlDestination, final String bckFile, final ArrayList<Object> tables ) {
		
		if( MysqlUtils.dump( mysqlOrigin, bckFile, tables ) ) {
		
			MysqlUtils.restore( mysqlDestination, bckFile );
		
		}
		
	}
	
	public static Boolean dump( Mysql mysql, final String bckFile, final ArrayList<Object> tables ) {
		
		mysql.execUpdate( "SET GLOBAL max_allowed_packet=1073741824;" );
		
		StringBuilder mysqldumpCmd = new StringBuilder();
		
		mysqldumpCmd.append( "mysqldump " ).
					append( "-h" ).append( mysql.getHost() ).append( " " ).
					append( "-P" ).append( mysql.getPort() ).append( " " ).
					append( "-u" ).append( mysql.getUser() ).append( " " ).
					append( "-p" ).append( mysql.getPassword() ).append( " " ).
					append( mysql.getName() ).append( " " );
		
		for( Object table : tables ) {
			
			Table tableName = (Table)table.getClass().getAnnotation( Table.class );
			
			mysqldumpCmd.append( tableName.value() ).append( " " );
			
		}
		
		mysqldumpCmd.append( "-r" ).append( System.getProperty( "user.dir" ) ).append( "/output/mysqldump/" ).append( bckFile );
		
		return MysqlUtils.exec( mysqldumpCmd.toString() );
		
	}
	
	public static Boolean restore( Mysql mysql, final String fileName ) {
		
		mysql.execUpdate( "SET GLOBAL max_allowed_packet=1073741824;" );
		
		StringBuilder mysqlCmd = new StringBuilder();
		
		mysqlCmd.append( "mysql " ).
					append( "-h" ).append( mysql.getHost() ).append( " " ).
					append( "-P" ).append( mysql.getPort() ).append( " " ).
					append( "-u" ).append( mysql.getUser() ).append( " " ).
					append( "-p" ).append( mysql.getPassword() ).append( " " ).
					append( mysql.getName() ).append( " " );
		
		mysqlCmd.append( "< " ).append( System.getProperty( "user.dir" ) ).append( "/output/mysqldump/" ).append( fileName );
		
		String[] mysqlRestoreCommand = new String[]{ "/bin/sh", "-c", mysqlCmd.toString() };
		
		return MysqlUtils.exec( mysqlRestoreCommand );
		
	}
	
	public static Boolean exec( String mysqlCommand ) {
		
		try {
			
			Process runtimeProcess = Runtime.getRuntime().exec( mysqlCommand );
			
			int processComplete = runtimeProcess.waitFor();
			
			if( processComplete == 0 ){
	
				logger.info( "Mysql command execution successfully" );
	
				return true;
				
			} else {
	
				throw new DataBaseException( "Could not execute mysql command ( " + mysqlCommand + " )" );
				
			}
			
		} catch( IOException | InterruptedException | DataBaseException e ) {
					
			logger.error( e.getMessage(), e );
			
		}
		
		return false;
		
	}
	
	public static Boolean exec( String[] mysqlCommand ) {
		
		try {
			
			Process runtimeProcess = Runtime.getRuntime().exec( mysqlCommand );
			
			int processComplete = runtimeProcess.waitFor();
			
			if( processComplete == 0 ){
	
				logger.info( "Mysql command execution successfully" );
	
				return true;
				
			} else {
	
				throw new DataBaseException( "Could not execute mysql command ( " + mysqlCommand.toString() + " )" );
				
			}
			
		} catch( IOException | InterruptedException | DataBaseException e ) {
					
			logger.error( e.getMessage(), e );
			
		}
		
		return false;
		
	}
	
}
