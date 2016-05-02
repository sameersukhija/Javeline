package com.g4s.common.testing.plan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.g4s.common.testing.database.SQLite;
import com.g4s.common.testing.exceptions.NetworkEnvironmentException;
import com.g4s.common.testing.io.IOFileUtils;
import com.g4s.common.testing.system.NetworkEnvironment;

public class TestSQLite {
	
	private NetworkEnvironment env = null;	
	
	@BeforeClass
	public void init() throws NetworkEnvironmentException 
	{		
		env = new NetworkEnvironment( "input/environments/", "local_ne", IOFileUtils.IOLoadingType.RESOURCE );
		
		Assert.assertNotNull( env , "NetworkEnvironment is null during init phase!");
	}
	
	@Test( enabled = true, priority = 1 )
	public void connectionWithRelativePath() throws NetworkEnvironmentException, SQLException {		
		
		SQLite sql = new SQLite( env.getDataSource( "local_relative" ) );
		
		Assert.assertFalse( sql.getConnection().isClosed() );
		
		sql.close();
		
	}
	
	@Test( enabled = true, priority = 2 )
	public void connectionWithAbsolutePath() throws NetworkEnvironmentException, SQLException {		
		
		SQLite sql = new SQLite( env.getDataSource( "local_absolute" ) );
		
		Assert.assertFalse( sql.getConnection().isClosed() );
		
		sql.close();
		
	}

	@Test( enabled = true, priority = 3 )
	public void createTable() throws NetworkEnvironmentException, SQLException {		
		
		SQLite sql = new SQLite( env.getDataSource( "local_relative" ) );
		
		String query = "CREATE TABLE IF NOT EXISTS company (" +
							" id 			INT PRIMARY KEY NOT NULL," +
							" name          TEXT    NOT NULL, " + 
							" age           INT     NOT NULL, " + 
							" address       CHAR(50), " + 
							" salary        REAL " + 
						")";
		
		Assert.assertEquals( sql.execUpdate( query ), 0 );
				
		sql.close();
		
	}
	
	@Test( enabled = true, priority = 4 )
	public void showTables() throws NetworkEnvironmentException, SQLException {		
		
		SQLite sql = new SQLite( env.getDataSource( "local_relative" ) );
		
		String query = "SELECT name FROM sqlite_master WHERE type='table'";
		
		ResultSet rs = sql.execQuery( query );
		
		ArrayList<String> tables = new ArrayList<String>();
		
		while( rs.next() ) {
			
			tables.add( rs.getString( "name" ) );
			
		}
		
		Assert.assertEquals( tables.size(), 1 );
		
		sql.close();
		
	}
	
	@Test( enabled = true, priority = 5 )
	public void insertIntoTable() throws NetworkEnvironmentException, SQLException {		
		
		SQLite sql = new SQLite( env.getDataSource( "local_relative" ) );
		
		String query = "INSERT OR IGNORE INTO company VALUES ( 1, 'Company', 4, 'address', 'user1' )";
		
		Assert.assertEquals( sql.execUpdate( query ), 1 );
		
		sql.close();
		
	}
	
	@Test( enabled = true, priority = 6 )
	public void selectFromTable() throws NetworkEnvironmentException, SQLException {		
		
		SQLite sql = new SQLite( env.getDataSource( "local_relative" ) );
		
		String query = "SELECT * FROM company";
		
		ResultSet rs = sql.execQuery( query );
		
		ArrayList<String> companies = new ArrayList<String>();
		
		while( rs.next() ) {
			
			companies.add( rs.getString( "name" ) );
			
		}
		
		Assert.assertEquals( companies.size(), 1 );
		
		sql.close();
		
	}
	
	
	
	
//    Connection c = null;
//    Statement stmt = null;
//    try {
//      Class.forName("org.sqlite.JDBC");
//      c = DriverManager.getConnection("jdbc:sqlite:test.db");
//      stmt = c.createStatement();
//      String sql = "CREATE TABLE COMPANY " +
//                   "(ID INT PRIMARY KEY     NOT NULL," +
//                   " NAME           TEXT    NOT NULL, " + 
//                   " AGE            INT     NOT NULL, " + 
//                   " ADDRESS        CHAR(50), " + 
//                   " SALARY         REAL)"; 
//      stmt.executeUpdate(sql);
//      stmt.close();
//      c.close();
//    } catch ( Exception e ) {
//      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//      System.exit(0);
//    }
//    System.out.println("Opened database successfully");

	
}
