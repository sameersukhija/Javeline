package com.lumata.expression.operators.testing.gui.xmlrpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.e4o.exceptions.XMLRPCException;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCForm;
import com.lumata.expression.operators.json.configuration.XMLRPCCfg;
import com.lumata.expression.operators.pojo.configuration.Configuration;
import com.lumata.expression.operators.pojo.configuration.ConfigurationTypes;

public class Test_XMLRPCForm {

	private static final Logger logger = LoggerFactory.getLogger( Test_XMLRPCForm.class );
	
	private static final String XMLRPC_GUI_LINK = "/angular/xmlrpcTest/index.html#/xmlrpc";
	
	SeleniumWebDriver seleniumWebDriver;
	XMLRPCCfg xmlrpcCfgAccept;
	XMLRPCCfg xmlrpcCfgAllocate;
	XMLRPCCfg xmlrpcCfgRefuseAll;
	Environment env;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant ) throws EnvironmentException, XMLRPCException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
		// Load BDR storage configuration
		Map<String, Object> options = new HashMap<String, Object>();
		options.put( "tenant" , tenant);
		options.put( "environment" , env );
		
		ArrayList<Configuration> xmlrpcCfg = ConfigurationTypes.XMLRPC.getCfg( options );
				
		// Check XMLRPC configuration
		boolean check = Configuration.check( xmlrpcCfg, mysql, tenant );
		
		// If XMLRPC is not enabled then enable it
		if( !check ) { XMLRPCForm.setCfg( env.getDataSource( tenant ), tenant ); }
		
 		Assert.assertTrue( Configuration.check( xmlrpcCfg, mysql, tenant ) );
		
 		// Load XMLRPC CALLS configuration
 		xmlrpcCfgAccept = new XMLRPCCfg( "input/xmlrpc", "xmlrpc_template_accept", IOFileUtils.IOLoadingType.RESOURCE );
 		xmlrpcCfgAllocate = new XMLRPCCfg( "input/xmlrpc", "xmlrpc_template_allocate", IOFileUtils.IOLoadingType.RESOURCE );
 		xmlrpcCfgRefuseAll = new XMLRPCCfg( "input/xmlrpc", "xmlrpc_template_refuseAll", IOFileUtils.IOLoadingType.RESOURCE );
 		
 		// Open XMLRPC gui
		seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowser( browser ), env.getLink() );
		seleniumWebDriver.windowMaximize();
		
		Assert.assertTrue( XMLRPCForm.open(seleniumWebDriver, XMLRPC_GUI_LINK, 120000, 500) );
		
	}
	
	@Parameters({"tenant"})
	@Test(enabled=true, priority = 1)
	public void setURL( @Optional("qa") String tenant ) {
			
		//Assert.assertTrue( XMLRPC.setURL(seleniumWebDriver, "/xmlrpc/", 120000, 500) );
		
	}
	
	@Test(enabled=true, priority = 2)
	public void setAcceptCall() {
		
		String tokenCode = "gl-abcdef";
		
		xmlrpcCfgAccept.setParamData( 2, xmlrpcCfgAccept.createParamDataString( "gl-abcdef" ) );
		
		ArrayList<Integer> offer_ids = new ArrayList<Integer>();
		offer_ids.add( 1010 );
		offer_ids.add( 1011 );
		
		xmlrpcCfgAccept.setParamData( 3, xmlrpcCfgAccept.createParamDataOfferIDList( offer_ids ) );
				
		XMLRPCForm.set( seleniumWebDriver, xmlrpcCfgAccept, 120000, 500 );
				
	}
	
	@Test(enabled=true, priority = 2)
	public void setAllocateCall() {
		
		String tokenCode = "gl-abcdef";
		
		xmlrpcCfgAllocate.setParamData( 2, xmlrpcCfgAllocate.createParamDataString( "gl-abcdef" ) );
						
		XMLRPCForm.set( seleniumWebDriver, xmlrpcCfgAllocate, 120000, 500 );
				
	}
	
	@Test(enabled=true, priority = 3)
	public void setRefuseAllCall() {
		
		String tokenCode = "gl-abcdef";
		
		xmlrpcCfgRefuseAll.setParamData( 2, xmlrpcCfgRefuseAll.createParamDataString( "gl-abcdef" ) );
						
		XMLRPCForm.set( seleniumWebDriver, xmlrpcCfgRefuseAll, 120000, 500 );
				
	}
	
	@Test(enabled=true, priority = 4)
	public void sendCall() {
		
		XMLRPCForm.sendCall( seleniumWebDriver, 120000, 500);
				
	}
	
	@Test(enabled=true, priority = 5)
	public void getResult() {
		
		String result = XMLRPCForm.getResult(seleniumWebDriver, 120000, 500);
		
		logger.info( "###############################" );
		logger.info( result );
		logger.info( "###############################" );
				
	}
	
}
