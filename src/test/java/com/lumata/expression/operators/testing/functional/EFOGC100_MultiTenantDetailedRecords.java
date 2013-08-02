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

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.EnvLoadingType;
import com.lumata.expression.operators.database.Configuration;
import com.lumata.expression.operators.database.TenantCfg;

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
		
		Assert.assertTrue( Configuration.check( bdrStorageCfg, tenant1, env) );
		
	}
	
}
