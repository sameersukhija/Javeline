package com.lumata.common.testing.database;

import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class MysqlMasterStatus {

	private static final  Logger logger = LoggerFactory.getLogger( MysqlMasterStatus.class );
		
	private String File = null;
	private String Position = null;
	private String Binlog_Do_DB = null;
	private String Binlog_Ignore_DB = null; 
	private String Executed_Gtid_Set = null;
	private Mysql mysql;
	
	public MysqlMasterStatus( Mysql mysql ) {
		
		this.mysql = mysql;
		
		String query = "SHOW MASTER STATUS;";
		
		ResultSet rs = this.mysql.execQuery( query );
		
		try {
			
			while( rs.next() ) {
			
				this.File = rs.getString( "File" );
				this.Position = rs.getString( "Position" );
				this.Binlog_Do_DB = rs.getString( "Binlog_Do_DB" );
				this.Binlog_Ignore_DB = rs.getString( "Binlog_Ignore_DB" );
				this.Executed_Gtid_Set = rs.getString( "Executed_Gtid_Set" );	
						
			}
			
		} catch (SQLException e) {

			logger.error( e.getMessage(), e );
			
		}
					
	}
	
	public String getFile() {
		
		return this.File;
		
	}

	public String getPosition() {
		
		return this.Position;
		
	}

	public String getBinlogDoDB() {
		
		return this.Binlog_Do_DB;
		
	}

	public String getBinlogIgnoreDB() {
		
		return this.Binlog_Ignore_DB;
		
	}

	public String getExecutedGtidSet() {
		
		return this.Executed_Gtid_Set;
		
	}

}
