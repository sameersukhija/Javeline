package com.lumata.e4o.system.inmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.json.system.configuration.ConfigurationTypes;
import com.lumata.e4o.system.configuration.ConfigurationDAO;

public class TestINscalability {
	
	private static final Logger logger = LoggerFactory.getLogger(TestINscalability.class);
	
	NetworkEnvironment env;
	Mysql mysqlGlobal;
	Mysql mysqlTenant;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysqlGlobal = new Mysql( env.getDataSource( "global" ) );
		
		mysqlTenant = new Mysql( env.getDataSource( tenant ) );
						
	}
	
	@Parameters({"tenant"})
	@Test( priority = 1, enabled = true )
	public void setCfg( @Optional("tenant") String tenant ) {

		ConfigurationDAO asynchronousGlobalINPlugin = new ConfigurationDAO( ConfigurationTypes.IN_SCALABILITY, null );
		
		System.out.println( asynchronousGlobalINPlugin.toString() );
		
		asynchronousGlobalINPlugin.insert( mysqlTenant );
		
		Assert.assertTrue( asynchronousGlobalINPlugin.check( mysqlTenant ) );
		
		ConfigurationDAO asynchronousTenantINPlugin = new ConfigurationDAO( ConfigurationTypes.STANDARD_RETRY, null );
				
		System.out.println( asynchronousTenantINPlugin.toString() );
		
		asynchronousTenantINPlugin.insert( mysqlTenant );
		
		Assert.assertTrue( asynchronousTenantINPlugin.check( mysqlTenant ) );		
	
    }
	
	@AfterSuite
	public void closeMysqlConnections() {
		
		mysqlGlobal.close();
		
		mysqlTenant.close();
		
	}
	
}
