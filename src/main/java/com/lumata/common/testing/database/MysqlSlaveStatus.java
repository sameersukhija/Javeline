package com.lumata.common.testing.database;

import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class MysqlSlaveStatus {

	private static final  Logger logger = LoggerFactory.getLogger( MysqlSlaveStatus.class );
		
	private String Master_Log_File = null;
	private String Read_Master_Log_Pos = null;
	private String Last_Error = null;
	private Integer Last_Errno = null;
	private Boolean Slave_IO_Running = null;	
	private Boolean Slave_SQL_Running = null;	
	private Boolean Last_IO_Errno = null;	
	private Boolean Last_SQL_Errno = null;
	
	
	private Mysql mysql;
	
	public MysqlSlaveStatus( Mysql mysql ) {
		
		this.mysql = mysql;
		
		String query = "SHOW SLAVE STATUS;";
		
		ResultSet rs = this.mysql.execQuery( query );
		
		try {
			
			while( rs.next() ) {
			
				this.Master_Log_File = rs.getString( "Master_Log_File" );
				this.Read_Master_Log_Pos = rs.getString( "Read_Master_Log_Pos" );
				this.Last_Error = rs.getString( "Last_Error" );
				this.Last_Errno = rs.getInt( "Last_Errno" );
				this.Slave_IO_Running = rs.getBoolean( "Slave_IO_Running" );	
				this.Slave_SQL_Running = rs.getBoolean( "Slave_SQL_Running" );	
				this.Last_IO_Errno = rs.getBoolean( "Last_IO_Errno" );	
				this.Last_SQL_Errno = rs.getBoolean( "Last_SQL_Errno" );
				
			}
			
		} catch (SQLException e) {

			logger.error( e.getMessage(), e );
			
		}
					
	}
	
	public String getMasterLogFile() {
		
		return this.Master_Log_File;
		
	}

	public String getReadMasterLogPos() {
		
		return this.Read_Master_Log_Pos;
		
	}
	
	public String getLastError() {
		
		return this.Last_Error;
		
	}
	
	public Integer getLastErrno() {
		
		return this.Last_Errno;
		
	}
	
	public Boolean getSlaveIORunning() {
		
		return this.Slave_IO_Running;
		
	}

	public Boolean getSlaveSQLRunning() {
		
		return this.Slave_SQL_Running;
		
	}

	public Boolean getLastIOErrno() {
		
		return this.Last_IO_Errno;
		
	}

	public Boolean getLastSQLErrno() {
		
		return this.Last_SQL_Errno;
		
	}

}
