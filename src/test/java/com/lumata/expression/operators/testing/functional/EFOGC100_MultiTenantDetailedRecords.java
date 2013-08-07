package com.lumata.expression.operators.testing.functional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import com.lumata.expression.operators.dao.configuration.Configuration;
import com.lumata.expression.operators.dao.configuration.TenantCfg;

public class EFOGC100_MultiTenantDetailedRecords {

	private static final Logger logger = LoggerFactory.getLogger( EFOGC100_MultiTenantDetailedRecords.class );
	
	Environment env;
	
	/* 	Initialize Environment */
	@Parameters({"environment"})
	@BeforeSuite
	public void init( @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, EnvLoadingType.RESOURCE );
						
	}
	
	@Parameters({"tenant1"})
	@Test
	public void checkConfiguration( @Optional("qa") String tenant1 ) {
				
		// Load BDR storage configuration
		Map<String, Object> options = new HashMap<String, Object>();
		options.put( "tenant_name" , tenant1);
		options.put( "environment" , env );
		
		ArrayList<Configuration> bdrStorageCfg = TenantCfg.BDR_STORAGE.getCfg( options );
				
		// Check BDR storage configuration
		Assert.assertTrue( Configuration.check( bdrStorageCfg, tenant1, env ) );
		
	}
	
	@Parameters({"tenant1"})
	@Test
	public void checkDBSchema( @Optional("qa") String tenant1 ) {
				
		// Check BDR storage schema configuration
		
		Mysql mysql = new Mysql( env.getDataSource( tenant1 ) );
		
		boolean check = true;
		
		if( !MysqlUtils.isTable( "bdrs" , mysql ) ) { check = false; };
		if( !MysqlUtils.isTable( "bdr_files" , mysql ) ) { check = false; };
		if( !MysqlUtils.isTable( "bdr_format" , mysql ) ) { check = false; };
		
		mysql.close();
		
		Assert.assertTrue( check );
		
	}
	
	/*
	@Test
	public void testTestNGProgramatically(){
	
		Assert.assertTrue( TestNGUtils.run( new Class[] { TestFake.class, TestTenantCfg.class } ) );
				
	}
	*/
	
}
