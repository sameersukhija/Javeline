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
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.EnvLoadingType;
import com.lumata.expression.operators.configuration.Configuration;
import com.lumata.expression.operators.configuration.TenantCfg;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPC;

public class Test_XMLRPC {

	private static final Logger logger = LoggerFactory.getLogger( Test_XMLRPC.class );
	
	private static final String XMLRPC_GUI_LINK = "/angular/xmlrpcTest/index.html#/xmlrpc";
	
	SeleniumWebDriver seleniumWebDriver;
	Environment env;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant1"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant1 ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, EnvLoadingType.RESOURCE );
		
		// Load BDR storage configuration
		Map<String, Object> options = new HashMap<String, Object>();
		options.put( "tenant_name" , tenant1);
		options.put( "environment" , env );
		
		ArrayList<Configuration> xmlrpcCfg = TenantCfg.XMLRPC.getCfg( options );
				
		// Check XMLRPC configuration
		boolean check = Configuration.check( xmlrpcCfg, tenant1, env );
		
		if( !check ) { XMLRPC.setCfg( env.getDataSource( tenant1 ), tenant1 ); }
		
 		Assert.assertTrue( Configuration.check( xmlrpcCfg, tenant1, env ) );
		
		seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowser( browser ), env.getLink() );
		seleniumWebDriver.windowMaximize();
		
		Assert.assertTrue( XMLRPC.open(seleniumWebDriver, XMLRPC_GUI_LINK, 120000, 500) );
		
	}
	
	@Parameters({"tenant1"})
	@Test(priority = 1)
	public void checkConfiguration( @Optional("qa") String tenant1 ) {
			
		//Assert.assertTrue( XMLRPC.setURL(seleniumWebDriver, "/xmlrpc/", 120000, 500) );
		
	}
	
	@Test(priority = 2)
	public void setCall() {
		
		Assert.assertTrue( XMLRPC.setCall(seleniumWebDriver, "campaignmanager.getCampaigns", 120000, 500) );
		
	}
	
	@Test(priority = 3)
	public void addLoginParameter() {
		
		Assert.assertTrue( XMLRPC.addParameter(seleniumWebDriver, 120000, 500) );
		 
		int index = XMLRPC.getParametersCount(seleniumWebDriver, 120000, 500);
		
		Assert.assertTrue( index > 0 );
		
		Assert.assertTrue( XMLRPC.setLoginParameter( seleniumWebDriver, index, "superman", "super2010Man", 120000, 500) );
		
	}
	
	@Test(priority = 4)
	public void sendCall() {
		
		Assert.assertTrue( XMLRPC.sendCall(seleniumWebDriver, 120000, 500) );
		
	}
	
}
