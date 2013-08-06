package com.lumata.expression.operators.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
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
		
		this.name = cfg.getString( "name" );
		this.position = cfg.getString( "position" );
		this.section = cfg.getString( "section" );
		this.process_id = cfg.getString( "process_id" );
		this.auth_group = cfg.getString( "auth_group" );
		this.current = cfg.getString( "current" );
		this.previous = cfg.getString( "previous" );
		this.dyn_static = cfg.getString( "dyn_static" );
		this.time = cfg.getString( "time" );
		this.type = cfg.getString( "type" );
		this.description = cfg.getString( "description" );
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
		
		configuration.append( "{ " );
		configuration.append( "name:" + this.getName() + ", " );
		configuration.append( "position:" + this.getPosition() + ", " );
		configuration.append( "section:" + this.getSection() + ", " );
		configuration.append( "process_id:" + this.getProcessID() + ", " );
		configuration.append( "auth_group:" + this.getAuthGroup() + ", " );
		configuration.append( "current:" + this.getCurrent() + ", " );
		configuration.append( "previous:" + this.getPrevious() + ", " );
		configuration.append( "dyn_static:" + this.getDynStatic() + ", " );
		configuration.append( "time:" + this.getTime() + ", " );
		configuration.append( "type:" + this.getType() + ", " );
		configuration.append( "description:" + this.getDescription() );
		configuration.append( "validation:" + this.getValidation() );
		configuration.append( " }" );
		
		return configuration.toString();
		
	} 
	
	public static Boolean check( ArrayList<Configuration> cfgList, String tenantName, Environment env ) {
		
		Mysql mysql = new Mysql( env.getDataSource( tenantName ) );
		
		StringBuffer parameters = new StringBuffer(); 
		
		// Build query to get current database configuration
		for ( int i = 0; i < cfgList.size(); i++ ) {
		    
			parameters.append( "'" + cfgList.get( i ).getName() + "'" ).append( ", " );
			
		}
		
		String query = "SELECT * FROM " + tenantName + ".conf WHERE name in ( " + parameters.substring( 0, parameters.length() - 2 ) + " );";
		
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
	
}
