package com.lumata.expression.operators.testing.generators;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
				
		int tokens_to_generate = 10000;
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getCustoEventParam( "393409429107", HTTPXMLRPCForm.EventTypes.revenue, new LinkedHashMap<HTTPXMLRPCForm.EventParameterTypes, String>() { { put( HTTPXMLRPCForm.EventParameterTypes.recharge, "1" ); put( HTTPXMLRPCForm.EventParameterTypes.event_storage_policy, "store" ); } } ) );
		
		long startTime = System.currentTimeMillis();
				
		for( int i = 0; i < tokens_to_generate; i++ ) {
						
			ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.eventmanager_generateCustomEvent.call( env.getLink() + "xmlrpc/" , params );
						
			//logger.info( "TOKEN RESPONSE: " + response.getEntity().toString() );
			
			try {
			
				Thread.sleep( 2 );
			
			} catch(  InterruptedException e ) {
				logger.error( e.getMessage(), e );				  
			}
			
		}
		
		long diffTime = System.currentTimeMillis() - startTime;  
        int decSeconds = (int)(diffTime % 1000 / 100);  
        int seconds = (int)(diffTime / 1000 % 60);  
        int minutes = (int)(diffTime / 60000 % 60);  
        int hours = (int)(diffTime / 3600000);
        String s = String.format("%d:%02d:%02d.%d", hours, minutes, seconds, decSeconds);
				
	}
	
}
