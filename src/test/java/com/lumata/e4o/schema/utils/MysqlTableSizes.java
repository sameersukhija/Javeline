package com.lumata.e4o.schema.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;

@TCMysqlMaster
public class MysqlTableSizes extends ParentTestCase {

	@Test(enabled=false)
	public void getTableSizes() throws SQLException, JSONSException {
		
		JSONObject tableSizes = new JSONObject(); 
		
		ArrayList<String> tables = MysqlUtils.getSchema( mysqlMaster );
			
		for( String tableName : tables ) {
			
			int tableSize = MysqlUtils.getTableSize( tableName, mysqlMaster );
			
			tableSizes.put( tableName, tableSize );
						
		}
		
		//JSONUtils.saveJSONResource( tableSizes, "schema", "poc_after_expireddata_task.json" );
		//JSONUtils.saveJSONResource( tableSizes, "schema", "poc_after_aggregatedata_task.json" );
		JSONUtils.saveJSONResource( tableSizes, "schema", "poc_after_subscriberdelayedremoval_task.json" );
				
	}

	@Test(enabled=true)
	public void compareTableSizes() throws SQLException, JSONSException {
		
		JSONObject jo1 = JSONUtils.loadJSONFile( System.getProperty( "user.dir" ) + "/output/schema/", "poc_after_expireddata_task.json" );		
		JSONObject jo2 = JSONUtils.loadJSONFile( System.getProperty( "user.dir" ) + "/output/schema/", "poc_after_aggregatedata_task.json" );

//		JSONObject jo1 = JSONUtils.loadJSONFile( System.getProperty( "user.dir" ) + "/output/schema/", "poc_before_aggregate_data_task.json" );		
//		JSONObject jo2 = JSONUtils.loadJSONFile( System.getProperty( "user.dir" ) + "/output/schema/", "poc_before_subscriberdelayedremoval_data_task.json" );

//		JSONObject jo1 = JSONUtils.loadJSONFile( System.getProperty( "user.dir" ) + "/output/schema/", "poc_before_subscriberdelayedremoval_data_task.json" );		
//		JSONObject jo2 = JSONUtils.loadJSONFile( System.getProperty( "user.dir" ) + "/output/schema/", "poc_before_subscriberdelayedremoval_data_past_task.json" );

		JSONObject diff = new JSONObject();
		
		@SuppressWarnings("unchecked")
		Iterator<String> keys = jo1.keys();
		
		while( keys.hasNext() ) {
			
			String key = keys.next();
			
			Integer value1 = jo1.getInt( key );
			
			Integer value2 = jo2.getInt( key );
			
			if( !value1.equals( value2 ) ) {
				
				diff.put( key, value1 + " - " + value2 );
				
			}
					
		}
		
		System.out.println( diff.toString() );
		
		JSONUtils.saveJSONResource( diff, "schema", "poc_diff_expireddata_aggregatedata.json" );
		
//		
//		
//		JSONObject tableSizes = new JSONObject(); 
//		
//		ArrayList<String> tables = MysqlUtils.getSchema( mysqlMaster );
//			
//		for( String tableName : tables ) {
//			
//			int tableSize = MysqlUtils.getTableSize( tableName, mysqlMaster );
//			
//			tableSizes.put( tableName, tableSize );
//						
//		}
//		
//		JSONUtils.saveJSONResource( tableSizes, "schema", "poc_before_aggregate_data_task.json" );
				
	}

}
