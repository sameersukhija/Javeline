package com.lumata.expression.operators.dao.configuration;

import static com.lumata.common.testing.orm.Filter.and;
import static com.lumata.common.testing.orm.Filter.op;
import static com.lumata.common.testing.orm.Query.select;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.orm.Val;
import com.lumata.expression.operators.entities.Conf;
import com.lumata.expression.operators.pojo.configuration.Configuration;
import com.lumata.expression.operators.pojo.configuration.ConfigurationTypes;

public class ConfigurationDAO {

	private static final Logger logger = LoggerFactory.getLogger( Configuration.class );
	
	private ArrayList<Configuration> list;
	
	public ConfigurationDAO() {
		
		this.list = new ArrayList<Configuration>();
		
	}
	
	public ConfigurationDAO( ConfigurationTypes configurationTypes, Map<String, Object> options ) {
		
		this.list = configurationTypes.getCfg( options );
		
	}
	
	public ConfigurationDAO( ArrayList<Map<ConfigurationTypes, Map<String, Object>>> configurationTypeList ) {}
	
	public void addAll( ConfigurationTypes configurationTypes, Map<String, Object> options ) {
		
		this.list.addAll( configurationTypes.getCfg( options ) );
		
	}
	
	public void retainAll( ConfigurationTypes configurationTypes, Map<String, Object> options ) {
		
		this.list.retainAll( configurationTypes.getCfg( options ) );
		
	}
	
	public int insert( Mysql mysql ) {
		
		int index = -1;
		
		this.check( mysql );
		
		StringBuffer query = new StringBuffer();
		
		for( int i = 0; i < this.list.size(); i++ ) {
			
			if( !this.list.get( i ).getValidation() ) {
				
				query = new StringBuffer();
				
				query.append( "INSERT INTO conf VALUES ( " ).
						append( "'" ).append( this.list.get( i ).getName() ).append( "', " ).
						append( this.list.get( i ).getPosition() ).append( ", " ).
						append( "'" ).append( this.list.get( i ).getSection() ).append( "', " ).
						append( "'" ).append( this.list.get( i ).getProcessID() ).append( "', " ).
						append( "'" ).append( this.list.get( i ).getAuthGroup() ).append( "', " ).
						append( "'" ).append( this.list.get( i ).getCurrent() ).append( "', " ).
						append( "'" ).append( this.list.get( i ).getPrevious() ).append( "', " ).
						append( "'" ).append( this.list.get( i ).getDynStatic() ).append( "', " ).
						append( this.list.get( i ).getTime() ).append( ", " ).
						append( "'" ).append( this.list.get( i ).getType() ).append( "', " ).
						append( "'" ).append( this.list.get( i ).getDescription() ).append( "' );" );
			
				logger.info( query.toString() );
				
				index = mysql.execUpdate( query.toString() );
				
			}
			
		}
				
		return index;
		
	}
	
	public boolean check( Mysql mysql ) {
		
		StringBuffer parameters = new StringBuffer(); 
		
		// Build query to get current database configuration
		for ( int i = 0; i < this.list.size(); i++ ) {
		    
			parameters.append( "'" + this.list.get( i ).getName() + "'" ).append( ", " );
			
		}
		
		String query = "SELECT * FROM conf WHERE name in ( " + parameters.substring( 0, parameters.length() - 2 ) + " );";
		
		// Get Result
		ResultSet rs = mysql.execQuery( query );
		
		try {
		
			// Check if the configuration is present in the database
			while( rs.next() ) {
	            
	            Configuration dbCfg = new Configuration( rs );
	            
	            for( int j = 0; j < this.list.size(); j++ ) {
	            		            	
	            	if( this.list.get( j ).getValidation() == false ) { 
	            		
	            		this.list.get( j ).setValidation( this.list.get( j ).compare( dbCfg ) ); 
	            		
	            	}
	            	
	            }
	        
			}
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
		
		}
				
		boolean check = true;
		
		for ( int i = 0; i < this.list.size(); i++ ) {
			
			if( check == true && this.list.get( i ).getValidation() == false ) { check = false; }
			logger.info( "The parameter " + this.list.get( i ).getName() + " is " + ( this.list.get( i ).getValidation() == true ? "present" : "not present" ) + " ( " + Configuration.Fields.section.toString() + ": " + this.list.get( i ).getSection() + " - " + Configuration.Fields.current.toString() + ": " + this.list.get( i ).getCurrent() + " )" );
			
		}
		
		return check;
				
	}
	
	public void checkAll( Mysql mysql ) {
		
		Conf confTable = new Conf();
		
		for( int i = 0; i < this.list.size(); i++ ) {
			
			Configuration conf = this.list.get( i );
				
			String query = select().
							from( confTable ).
							where( 
									op( Conf.Fields.name ).eq( conf.getName().replaceAll( "\"", "\\\\\"" ) ),
									and( op( Conf.Fields.position ).eq( conf.getPosition().replaceAll( "([0-9]+)[.].+", "$1" )) ),
									and( op( Conf.Fields.section ).eq( conf.getSection().replaceAll( "\"", "\\\\\"" ) ) ),
									and( op( Conf.Fields.process_id ).eq( conf.getProcessID().replaceAll( "\"", "\\\\\"" ) ) ),
									and( op( Conf.Fields.auth_group ).eq( conf.getAuthGroup().replaceAll( "\"", "\\\\\"" ) ) ),
									and( op( Conf.Fields.current ).eq( conf.getCurrent().replaceAll( "\"", "\\\\\"" ) ) ),
									and( op( Conf.Fields.previous ).eq( conf.getPrevious().replaceAll( "\"", "\\\\\"" ) ) ),
									and( op( Conf.Fields.dyn_static ).eq( conf.getDynStatic().replaceAll( "\"", "\\\\\"" ) ) ),
									and( op( Conf.Fields.time ).eq( conf.getTime().replaceAll( "\"", "\\\\\"" ) ) ),
									and( op( Conf.Fields.type ).eq( conf.getType().replaceAll( "\"", "\\\\\"" ) ) ),
									and( op( Conf.Fields.description ).eq( conf.getDescription().replaceAll( "\"", "\\\\\"" ) ) )
							).
							build();
			System.out.println( query );
			ResultSet rs = mysql.execQuery( query );
			
			try {
			
				if( rs.first() ) { conf.setValidation( true ); }
			
			} catch( SQLException e ) {
				
				logger.error( e.getMessage(), e );
			
			}
			
		}		
				
	}
	
}
