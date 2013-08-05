package com.lumata.expression.operators.testing.offermanagement;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.EnvLoadingType;

public class Manage_Offers {

	private static final Logger logger = LoggerFactory.getLogger( Manage_Offers.class );
	
	Environment env;
	
	/* 	Initialize Environment */
	@Parameters({"environment"})
	@BeforeSuite
	public void init( @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, EnvLoadingType.RESOURCE );
						
	}
	
/*
	public void checkConfiguration( @Optional("qa") String tenant1 ) {
				
		// Load BDR storage configuration
		Map<String, Object> options = new HashMap<String, Object>();
		options.put( "tenant_name" , tenant1);
		options.put( "environment" , env );
		
		ArrayList<Configuration> bdrStorageCfg = TenantCfg.BDR_STORAGE.getCfg( options );
				
		// Check BDR storage configuration
		Assert.assertTrue( Configuration.check( bdrStorageCfg, tenant1, env ) );
		
	}
	*/
	
	@Parameters({"tenant1"})
	@Test
	public void checkDBSchema( @Optional("qa") String tenant1 ) {
				
		// Check Offer Management schema configuration
		
		Mysql mysql = new Mysql( env.getDataSource( tenant1 ) );
		
		boolean check = true;
		
		if( !MysqlUtils.isTable( "offoptim_reservation" , mysql ) ) { check = false; };
		if( !MysqlUtils.isTable( "offoptim_customer_pack" , mysql ) ) { check = false; };
		if( !MysqlUtils.isTable( "offoptim_customer_items" , mysql ) ) { check = false; };
		
		mysql.close();
		
		Assert.assertTrue( check );
		
	}
	
}
