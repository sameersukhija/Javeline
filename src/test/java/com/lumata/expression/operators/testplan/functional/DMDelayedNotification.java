package com.lumata.expression.operators.testplan.functional;

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
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.configuration.ConfigurationDAO;
import com.lumata.expression.operators.pojo.configuration.ConfigurationTypes;

public class DMDelayedNotification {
	
	private static final Logger logger = LoggerFactory.getLogger(DMDelayedNotification.class);
	
	Environment env;
	Mysql mysqlGlobal;
	Mysql mysqlTenant;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant"})
	@BeforeSuite
	public void init( @Optional("E4O_QA") String environment, @Optional("qa") String tenant ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysqlGlobal = new Mysql( env.getDataSource( "global" ) );
		
		mysqlTenant = new Mysql( env.getDataSource( tenant ) );
						
	}
	
	@Parameters({"tenant"})
	@Test()
	public void setCfg( @Optional("tenant") String tenant ) {

		ConfigurationDAO dmDelayedNotification = new ConfigurationDAO( ConfigurationTypes.DM_DELAYED_NOTIFICATIONS, null );
		
		Assert.assertEquals( dmDelayedNotification.checkAll( mysqlTenant ), 0 );
		
		/*
		asynchronousGlobalINPlugin.insert( mysqlGlobal );
		
		Assert.assertTrue( asynchronousGlobalINPlugin.check( mysqlGlobal ) );
		
		ConfigurationDAO asynchronousTenantINPlugin = new ConfigurationDAO( ConfigurationTypes.STANDARD_RETRY, options );
				
		System.out.println( asynchronousTenantINPlugin.toString() );
		
		asynchronousTenantINPlugin.insert( mysqlTenant );
		
		Assert.assertTrue( asynchronousTenantINPlugin.check( mysqlTenant ) );		
		*/
    }
	
	@AfterSuite
	public void closeMysqlConnections() {
		
		mysqlGlobal.close();
		
		mysqlTenant.close();
		
	}
	
}
