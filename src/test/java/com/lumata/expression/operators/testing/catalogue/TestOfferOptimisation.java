package com.lumata.expression.operators.testing.catalogue;


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
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.gui.catalogue.TokenTypeForm;
import com.lumata.expression.operators.gui.security.Authorization;

public class TestOfferOptimisation {

	private static final Logger logger = LoggerFactory.getLogger( TestOfferOptimisation.class );
	private int TIMEOUT = 600000;
	private int ATTEMPT_INTERVAL = 500;
	
	SeleniumWebDriver seleniumWebDriver;
	Environment env;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
		seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowser( browser ), env.getLink() );
		seleniumWebDriver.windowMaximize();
		
		Assert.assertTrue(Authorization.login(seleniumWebDriver, env.getUserName( user ), env.getPassword( user ), 60000, 500));
		
	}
	
	@Parameters({"qa"})
	@Test
	public void createTokenType( @Optional("qa") String tenant1 ) {
		
		Assert.assertTrue( TokenTypeForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_INTERVAL) );
		
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

	/*
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
	*/
	
	
}
