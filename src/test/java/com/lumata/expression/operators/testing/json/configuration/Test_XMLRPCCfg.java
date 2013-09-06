package com.lumata.expression.operators.testing.json.configuration;

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

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.EnvLoadingType;
import com.lumata.expression.operators.exceptions.XMLRPCException;
import com.lumata.expression.operators.json.configuration.XMLRPCCfg;
import com.lumata.expression.operators.json.configuration.XMLRPCCfg.ParamType;

public class Test_XMLRPCCfg {

	private static final Logger logger = LoggerFactory.getLogger( Test_XMLRPCCfg.class );
	
	private static final String XMLRPC_GUI_LINK = "/angular/xmlrpcTest/index.html#/xmlrpc";
	
	Environment env;
	XMLRPCCfg xmlrpcCfg;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, EnvLoadingType.RESOURCE );
		
	}
	
	@Test(enabled=true, priority = 1)
	public void load() throws XMLRPCException {
			
		xmlrpcCfg = new XMLRPCCfg( "input/xmlrpc", "xmlrpc_template_accept", IOFileUtils.IOLoadingType.RESOURCE );
				
	}
	
	@Test(enabled=true, priority = 2)
	public void configure() {
		
		xmlrpcCfg.setCall( "accept" );
		
		logger.info( xmlrpcCfg.toString() );
		
		//xmlrpcCfg.setParamData( 0, xmlrpcCfg.createParamDataLogin( "superman", "bGppYm1NSUhJMmB3" ) );
		
		//xmlrpcCfg.setParamData( 1, xmlrpcCfg.createParamDataString( "331234567" ) );		
		
		xmlrpcCfg.setParamData( 2, xmlrpcCfg.createParamDataString( "gl-abcdef" ) );
		
		ArrayList<Integer> offer_ids = new ArrayList<Integer>();
		offer_ids.add( 1010 );
		offer_ids.add( 1011 );
		xmlrpcCfg.setParamData( 3, xmlrpcCfg.createParamDataOfferIDList( offer_ids ) );
				
		logger.info( xmlrpcCfg.toString() );
 		
	}
	
}
