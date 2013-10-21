package com.lumata.expression.operators.operations;

import java.util.ArrayList;
import java.util.HashMap;
import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.gui.xmlrpc.HTTPXMLRPCForm;

public class GenerateTokens {

	private static final Logger logger = LoggerFactory.getLogger( GenerateTokens.class );
	
	Environment env;	
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
						
	}
	
	@Test
	public void generateTokens() {
				
		int tokens_to_generate = 10;
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getCustoEventParam( "331234561", HTTPXMLRPCForm.EventTypes.revenue, new HashMap<HTTPXMLRPCForm.EventParameterTypes, String>() { { put( HTTPXMLRPCForm.EventParameterTypes.recharge, "1" ); } } ) );
		
		for( int i = 0; i < tokens_to_generate; i++ ) {
			
			ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.eventmanager_generateCustomEvent.call( env.getLink() + "xmlrpc/" , params );
		
			logger.info( "TOKEN RESPONSE: " + response.getEntity().toString() );
			
		}
				
	}
	
}
