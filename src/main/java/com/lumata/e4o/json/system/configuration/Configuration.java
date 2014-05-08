package com.lumata.e4o.json.system.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import static com.lumata.common.testing.orm.Query.select;
import static com.lumata.common.testing.orm.Filter.*;
import com.lumata.common.testing.system.Environment;

/*
	CREATE TABLE `conf` (
	  `name` varchar(255) NOT NULL,
	  `position` tinyint(1) unsigned NOT NULL DEFAULT '0',
	  `section` enum('churn','report','db','system','user_datas','user_datas_interface','user','group','zte','zte_transport','alu','alu_transport','air','air_transport','snmptrap','scheduled_tasks','groupproperties','ldap','mail','script','xmlrpc','stats','inmanager','license','backup','notif','notiflog','sms','catalog','bdr_storage','user_stats','about','utiba','utiba_transport','bite','bite_transport','qa_in','global','voiceXML') NOT NULL,
	  `process_id` varchar(30) NOT NULL,
	  `auth_group` enum('Customer','Admin','Internal') DEFAULT 'Internal',
	  `current` varchar(512) DEFAULT NULL,
	  `previous` varchar(512) DEFAULT NULL,
	  `dyn_static` enum('RW','RW_INITIAL','RO') DEFAULT 'RO',
	  `time` datetime DEFAULT NULL,
	  `type` enum('Value','SQL','FILE','System') DEFAULT 'Value',
	  `description` varchar(512) DEFAULT NULL,
	  PRIMARY KEY (`name`,`position`,`section`,`process_id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
*/

public class Configuration {

	private static final Logger logger = LoggerFactory.getLogger( Configuration.class );
	
	public enum Fields { name, position, section, process_id, auth_group, current, previous, dyn_static, time, type, description }
	
	private String name;
	private String position;
	private String section;
	private String process_id;
	private String auth_group;
	private String current;
	private String previous;
	private String dyn_static;
	private String time;
	private String type;
	private String description;
	private boolean _validation;
	
	public Configuration() {
		
		this._validation = false;
		
	}
	
	public Configuration( ArrayList<String> cfg ) {
		
		this.name = cfg.get( 0 );
		this.position = cfg.get( 1 );
		this.section = cfg.get( 2 );
		this.process_id = cfg.get( 3 );
		this.auth_group = cfg.get( 4 );
		this.current = cfg.get( 5 );
		this.previous = cfg.get( 6 );
		this.dyn_static = cfg.get( 7 );
		this.time = cfg.get( 8 );
		this.type = cfg.get( 9 );
		this.description = cfg.get( 10 );
		this._validation = false;
		
	}
	
	public Configuration( ResultSet cfg ) throws SQLException {
		
		this.name = cfg.getString( Configuration.Fields.name.toString() );
		this.position = cfg.getString( Configuration.Fields.position.toString() );
		this.section = cfg.getString( Configuration.Fields.section.toString() );
		this.process_id = cfg.getString( Configuration.Fields.process_id.toString() );
		this.auth_group = cfg.getString( Configuration.Fields.auth_group.toString() );
		this.current = cfg.getString( Configuration.Fields.current.toString() );
		this.previous = cfg.getString( Configuration.Fields.previous.toString() );
		this.dyn_static = cfg.getString( Configuration.Fields.dyn_static.toString() );
		this.time = cfg.getString( Configuration.Fields.time.toString() );
		this.type = cfg.getString( Configuration.Fields.type.toString() );
		this.description = cfg.getString( Configuration.Fields.description.toString() );
		this._validation = false;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
	
	public String getPosition() {
		
		return this.position;
		
	}

	public String getSection() {
		
		return this.section;
		
	}

	public String getProcessID() {
		
		return this.process_id;
		
	}

	public String getAuthGroup() {
		
		return this.auth_group;
		
	}
	
	public String getCurrent() {
		
		return this.current;
		
	}

	public String getPrevious() {
		
		return this.previous;
		
	}

	public String getDynStatic() {
		
		return this.dyn_static;
		
	}

	public String getTime() {
		
		return this.time;
		
	}

	public String getType() {
		
		return this.type;
		
	}

	public String getDescription() {
		
		return this.description;
		
	}
	
	public boolean getValidation() {
		
		return this._validation;
		
	}

	public void setName( String name ) {
		
		this.name = name;
		
	}
	
	public void setPosition( String position ) {
		
		this.position = position;
		
	}

	public void setSection( String section ) {
		
		this.section = section;
		
	}

	public void setProcessID( String process_id ) {
		
		this.process_id = process_id;
		
	}

	public void setAuthGroup( String auth_group ) {
		
		this.auth_group = auth_group;
		
	}
	
	public void setCurrent( String current ) {
		
		this.current = current;
		
	}

	public void setPrevious( String previous ) {
		
		this.previous = previous;
		
	}

	public void setDynStatic( String dyn_static ) {
		
		this.dyn_static = dyn_static;
		
	}

	public void setTime( String time ) {
		
		this.time = time;
		
	}

	public void setType( String type ) {
		
		this.type = type;
		
	}

	public void setDescription( String description ) {
		
		this.description = description;
		
	}
	
	public void setValidation( boolean validation ) {
		
		this._validation = validation;
		
	}

	public String toString() {
		
		StringBuffer configuration = new StringBuffer();
		
		configuration.append( "{ " ).
						append( "\"" ).append( Configuration.Fields.name.toString() ).append( "\": \"" ).append( this.getName() ).append( "\", " ).
						append( "\"" ).append( Configuration.Fields.position.toString() ).append( "\": \"" ).append( this.getPosition() ).append( "\", " ).
						append( "\"" ).append( Configuration.Fields.section.toString() ).append( "\": \"" ).append( this.getSection() ).append( "\", " ).
						append( "\"" ).append( Configuration.Fields.process_id.toString() ).append( "\": \"" ).append( this.getProcessID() ).append( "\", " ).
						append( "\"" ).append( Configuration.Fields.auth_group.toString() ).append( "\": \"" ).append( this.getAuthGroup() ).append( "\", " ).
						append( "\"" ).append( Configuration.Fields.current.toString() ).append( "\": \"" ).append( this.getCurrent() ).append( "\", " ).
						append( "\"" ).append( Configuration.Fields.previous.toString() ).append( "\": \"" ).append( this.getPrevious() ).append( "\", " ).
						append( "\"" ).append( Configuration.Fields.dyn_static.toString() ).append( "\": \"" ).append( this.getDynStatic() ).append( "\", " ).
						append( "\"" ).append( Configuration.Fields.time.toString() ).append( "\": \"" ).append( this.getTime() ).append( "\", " ).
						append( "\"" ).append( Configuration.Fields.type.toString() ).append( "\": \"" ).append( this.getType() ).append( "\", " ).
						append( "\"" ).append( Configuration.Fields.description.toString() ).append( "\": \"" ).append( this.getDescription() ).append( "\", " ).
						append( "\"" ).append( "_validation" ).append( "\": \"" ).append( this.getValidation() ).append( "\", " ).
						append( " }" );
		
		return configuration.toString();
		
	} 
	
	public static int insert( ArrayList<Configuration> cfgList, Mysql mysql, String schema ) {
		
		int index = -1;
		
		StringBuffer query = new StringBuffer();
		
		for( int i = 0; i < cfgList.size(); i++ ) {
			
			query = new StringBuffer();
			
			query.append( "INSERT INTO " ).append( schema ).
					append( ".conf VALUES ( " ).
					append( "'" ).append( cfgList.get( i ).getName() ).append( "', " ).
					append( cfgList.get( i ).getPosition() ).append( ", " ).
					append( "'" ).append( cfgList.get( i ).getSection() ).append( "', " ).
					append( "'" ).append( cfgList.get( i ).getProcessID() ).append( "', " ).
					append( "'" ).append( cfgList.get( i ).getAuthGroup() ).append( "', " ).
					append( "'" ).append( cfgList.get( i ).getCurrent() ).append( "', " ).
					append( "'" ).append( cfgList.get( i ).getPrevious() ).append( "', " ).
					append( "'" ).append( cfgList.get( i ).getDynStatic() ).append( "', " ).
					append( cfgList.get( i ).getTime() ).append( ", " ).
					append( "'" ).append( cfgList.get( i ).getType() ).append( "', " ).
					append( "'" ).append( cfgList.get( i ).getDescription() ).append( "' );" );
		
			logger.info( query.toString() );
			
		}
				
		return index;
		
	}
	
	public static int update( ArrayList<Configuration> cfgList, Mysql mysql, String schema ) {
		
		int index = -1;
		
		StringBuffer query = new StringBuffer();
		
		for( int i = 0; i < cfgList.size(); i++ ) {
			
			query = new StringBuffer();
			
			query.append( "UPDATE " ).append( schema ).
					append( ".conf SET " ).
					append( "position = " ).append( cfgList.get( i ).getPosition() ).append( ", " ).
					append( "section = '" ).append( cfgList.get( i ).getSection() ).append( "', " ).
					append( "process_id = '" ).append( cfgList.get( i ).getProcessID() ).append( "', " ).
					append( "auth_group = '" ).append( cfgList.get( i ).getAuthGroup() ).append( "', " ).
					append( "current = '" ).append( cfgList.get( i ).getCurrent() ).append( "', " ).
					append( "previous = '" ).append( cfgList.get( i ).getPrevious() ).append( "', " ).
					append( "dyn_static = '" ).append( cfgList.get( i ).getDynStatic() ).append( "', " ).
					append( "time = " ).append( cfgList.get( i ).getTime() ).append( ", " ).
					append( "type = '" ).append( cfgList.get( i ).getType() ).append( "', " ).
					append( "description = " ).append( cfgList.get( i ).getPosition() ).append( ", " ).
					append( "position = '" ).append( cfgList.get( i ).getDescription() ).
					append( "' WHERE name = '" ).append( cfgList.get( i ).getName() ).append( ";" );
				
			logger.info( query.toString() );
			
		}
				
		return index;
		
	}
	
	public static Boolean check( ArrayList<Configuration> cfgList, Mysql mysql, String schema ) {
				
		StringBuffer parameters = new StringBuffer(); 
		
		// Build query to get current database configuration
		for ( int i = 0; i < cfgList.size(); i++ ) {
		    
			parameters.append( "'" + cfgList.get( i ).getName() + "'" ).append( ", " );
			
		}
		
		String query = "SELECT * FROM " + schema + ".conf WHERE name in ( " + parameters.substring( 0, parameters.length() - 2 ) + " );";
		
		// Get Result
		ResultSet rs = mysql.execQuery( query );
		
		try {
		
			// Check if the configuration is present in the database
			while( rs.next() ) {
	            
	            Configuration dbCfg = new Configuration( rs );
	            
	            for( int j = 0; j < cfgList.size(); j++ ) {
	            		            	
	            	if( cfgList.get( j ).getValidation() == false ) { 
	            		
	            		cfgList.get( j ).setValidation( Configuration.compare( dbCfg, cfgList.get( j ) ) ); 
	            		
	            	}
	            	
	            }
	        
			}
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
		mysql.close();
		
		boolean check = true;
		
		for ( int i = 0; i < cfgList.size(); i++ ) {
			
			if( check == true && cfgList.get( i ).getValidation() == false ) { check = false; }
			logger.info( "The parameter " + cfgList.get( i ).getName() + " is " + ( cfgList.get( i ).getValidation() == true ? "present" : "not present" ) + " ( section : " + cfgList.get( i ).getSection() + " - current: " + cfgList.get( i ).getCurrent() + " )" );
			
		}
		
		return check;
				
	}
	
	public static Boolean compare( Configuration cfgActual, Configuration cfgExpected ) {
		
		if( !cfgActual.getName().equals( cfgExpected.getName() ) ) return false;
		if( !cfgActual.getSection().equals( cfgExpected.getSection() ) ) return false;
		if( !cfgActual.getCurrent().equals( cfgExpected.getCurrent() ) ) return false;
		
		return true;
		
	}
	
	public boolean compare( Configuration cfgExpected ) {
		
		if( !this.getName().equals( cfgExpected.getName() ) ) return false;
		if( !this.getSection().equals( cfgExpected.getSection() ) ) return false;
		if( !this.getCurrent().equals( cfgExpected.getCurrent() ) ) return false;
		
		return true;
		
	}
	
}
