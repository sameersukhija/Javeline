package com.lumata.expression.operators.pojo.configuration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.ExcelUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.validating.Format;

public enum ConfigurationTypes {
			
	/* Sprint 2 - US: EFOGC-100 - SubTask: EFOGC-834 */
	BDR_STORAGE {
		
		public ArrayList<Configuration> getCfg( Map<String, Object> options ) {
			
			ArrayList<Configuration> cfgList = new ArrayList<Configuration>();
						
			try {
				
				String tenant_name = (String)options.get( "tenant" );
				Environment env = (Environment)options.get( "environment" );
				JSONObject dataSource = env.getDataSource( tenant_name );
				
				Configuration cfg;
				
				cfg = new Configuration( new ArrayList<String>(Arrays.asList("bdr_storage_class", "0", "report", "NULL", "Internal", "com.act750.bdr.application.EmbeddedBdrStorage", "", "RO", getNow(), "Value", "Implementation of the BDR logger")) );
				cfgList.add( cfg );
				
				cfg = new Configuration( new ArrayList<String>(Arrays.asList("qostype", "13", "system", "NULL", "Internal", "BDR_STORAGE", "", "RW", getNow(), "Value", "Defines the type of the QoS which will be displayed in the QoS Page")) );
				cfgList.add( cfg );
				
				cfg = new Configuration( new ArrayList<String>(Arrays.asList("JDBC_url", "0", "bdr_storage", "NULL", "Internal", "jdbc:mysql://" + dataSource.getString( "host" ) + ":" + dataSource.getString( "port" ) +  "/" + tenant_name + "?connectTimeout=10000", "", "RO", getNow(), "Value", "JDBC connection URL")) );
				cfgList.add( cfg );
				
				cfg = new Configuration( new ArrayList<String>(Arrays.asList("task_15", "0", "system", "NULL", "Internal", "BdrRotatePartitionTask,com.act750.bdr.database.BdrRotatePartitionTask", "", "RW_INITIAL", getNow(), "Value", "Scheduled task")) );
				cfgList.add( cfg );			
			
			} catch( JSONException e ) {}
			
			return cfgList;
			
		}
		
	},
	
	/* Sprint 2 - US: EFOGC-793,  EFOGC-11 */
	XMLRPC {
		
		public ArrayList<Configuration> getCfg( Map<String, Object> options ) {
			
			ArrayList<Configuration> cfgList = new ArrayList<Configuration>();
						
			Configuration cfg;
			
			cfg = new Configuration( new ArrayList<String>(Arrays.asList("allow_list", "0", "xmlrpc", "NULL", "Internal", "true", "", "RW", getNow(), "Value", "Do we support the listing of XMLRPC signatures")) );
			cfgList.add( cfg );			
			
			return cfgList;
			
		}
		
	},
	
	/* Sprint 5 - US: EFOGC-861 */
	STANDARD_RETRY {
		
		public ArrayList<Configuration> getCfg( Map<String, Object> options ) {
			
			ArrayList<Configuration> cfgList = new ArrayList<Configuration>();
						
			Configuration cfg;
			
			cfg = new Configuration( new ArrayList<String>(Arrays.asList("waiting_time", "0", "inmanager", "NULL", "Admin", "30000", "NULL", "RW", "NULL", "Value", "")) );
			cfgList.add( cfg );	
			
			//cfg = new Configuration( new ArrayList<String>(Arrays.asList("sql_attempt_clause", "0", "inmanager", "NULL", "Admin", "(=1) {1  HOUR},(<3){8 HOUR},(=3){1 DAY},(>4){1 WEEK}", "NULL", "RW", "NULL", "Value", "")) );
			cfg = new Configuration( new ArrayList<String>(Arrays.asList("sql_attempt_clause", "0", "inmanager", "NULL", "Admin", "(>0) {1  MINUTE},(>2) {2 MINUTE}", "NULL", "RW", "NULL", "Value", "")) );
			cfgList.add( cfg );	
			
			return cfgList;
			
		}
		
	},
	
	/* Sprint 5 - US: EFOGC-861 */
	QA_UNKNOWN_MSISDN {
		
		public ArrayList<Configuration> getCfg( Map<String, Object> options ) {
			
			ArrayList<String> subscribers = (ArrayList<String>)options.get( "subscribers" );
			
			ArrayList<Configuration> cfgList = new ArrayList<Configuration>();
			
			Configuration cfg;
			
			for( int i = 0; i < subscribers.size(); i++ ) {
			
				cfg = new Configuration( new ArrayList<String>(Arrays.asList("QA_UNKNOWN_MSISDN", String.valueOf( i ), "qa_in", "NULL", "Admin", subscribers.get( i ), "NULL", "RW", "NULL", "Value", "")) );
				cfgList.add( cfg );	
			
			}
			
			return cfgList;
			
		}
		
	},
	
	ALL_STANDARD_PARAMETERS_FROM_FILE {
				
		public ArrayList<Configuration> getCfg( Map<String, Object> options ) {
				
			ArrayList<Configuration> cfgList = new ArrayList<Configuration>();
					
			final int CATEGORY = 0;
			final int SECTION = 1;
			final int NAME = 2;
			final int POSITION = 3;
			final int PROCESS_ID = 4;
			final int AUTH_GROUP = 5;
			final int CURRENT = 6;
			final int DYN_STATIC = 7;
			final int TYPE = 8;
			final int DESCRIPTION = 9;
			final int CHAR = 10;
			final int ON_LINE_HELP = 11;
			
			try {
				
				Workbook workbook = ExcelUtils.load( "input/configuration", "properties_default_fields_1.xls" );
				
				List<List<String>> sheet = ExcelUtils.loadSheet( workbook.getSheetAt( 0 ) );
								
				String numeric_pattern = "([0-9]+)[.]{0,1}.[0-9]*";
								
				for( int i = 1; i < sheet.size(); i++ ) {
										
					Configuration cfg = new Configuration();
					cfg.setSection( sheet.get( i ).get( SECTION ).replaceAll( "\"", "\\\\\"" ) );
					cfg.setName( sheet.get( i ).get( NAME ).replaceAll( "\"", "\\\\\"" ) );
					cfg.setPosition( sheet.get( i ).get( POSITION ).replaceAll( numeric_pattern, "$1" ) );
					cfg.setProcessID( sheet.get( i ).get( PROCESS_ID ).replaceAll( "\"", "\\\\\"" ) );
					cfg.setAuthGroup( sheet.get( i ).get( AUTH_GROUP ).replaceAll( "\"", "\\\\\"" ) );
					String current = sheet.get( i ).get( CURRENT ).replaceAll( "\"", "\\\\\"" );
					cfg.setCurrent( ( Format.isNumeric( current ) ? current.replaceAll( numeric_pattern, "$1" ) : current ) );
					cfg.setPrevious( "NULL" );
					cfg.setDynStatic( sheet.get( i ).get( DYN_STATIC ) );
					cfg.setTime( "NULL" );
					cfg.setType( sheet.get( i ).get( TYPE ).replaceAll( "\"", "\\\\\"" ) );
					cfg.setDescription( sheet.get( i ).get( DESCRIPTION ).replaceAll( "\"", "\\\\\"" ) );
										
					cfgList.add( cfg );	
															
				}
			
			} catch( IOFileException | IOException e ) {
				
				System.out.println( e.getMessage() );
				
			}	
						
			return cfgList;
			
		}
		
	};
		
	/*
	 mysql> select * from qa.conf where name like 'throttling%';
		+----------------------------------+----------+---------+------------+------------+---------+----------+------------+---------------------+-------+-------------------------------------------------------+
		| name                             | position | section | process_id | auth_group | current | previous | dyn_static | time                | type  | description                                           |
		+----------------------------------+----------+---------+------------+------------+---------+----------+------------+---------------------+-------+-------------------------------------------------------+
		| throttling_drop_feature          |        0 | xmlrpc  | NULL       | Internal   | true    | NULL     | RO         | NULL                | Value | Enable the drop strategy when applying the throttling |
		| throttling_max_number_per_second |        0 | xmlrpc  | NULL       | Internal   | 1000    | 0        | RO         | 2013-11-20 15:33:24 | Value | Max number of accepted XMLRPC messages per second     |
		+----------------------------------+----------+---------+------------+------------+---------+----------+------------+---------------------+-------+-------------------------------------------------------+
		2 rows in set (0.00 sec)
	 */
	
	abstract public ArrayList<Configuration> getCfg( Map<String, Object> options );
	
	private static String getNow() {
				
		Date now = new Date();    
	    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-mm-dd HH:mm:ss" );   
	    String sdate= sdf.format(now).toString(); 
		
		return sdate;
		
	}
	
	private static String getSchemaName( Map<String, Object> options ) {
		
		return (String)options.get( "schema" );
		
	}
	
}
