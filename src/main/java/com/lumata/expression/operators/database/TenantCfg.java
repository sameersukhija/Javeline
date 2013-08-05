package com.lumata.expression.operators.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.system.Environment;

public enum TenantCfg {
	
	/* Sprint 2 - US: EFOGC-100 - SubTask: EFOGC-834 */
	BDR_STORAGE {
		
		public ArrayList<Configuration> getCfg( Map<String, Object> options ) {
			
			ArrayList<Configuration> cfgList = new ArrayList<Configuration>();
						
			try {
				
				String tenant_name = (String)options.get( "tenant_name" );
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
		
	};
	
	
	abstract public ArrayList<Configuration> getCfg( Map<String, Object> options );
	
	private static String getNow() {
				
		Date now = new Date();    
	    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-mm-dd HH:mm:ss" );   
	    String sdate= sdf.format(now).toString(); 
		
		return sdate;
		
	}
	
}
