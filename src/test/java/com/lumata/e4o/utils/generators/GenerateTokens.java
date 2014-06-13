package com.lumata.e4o.utils.generators;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.gui.xmlrpc.HTTPXMLRPCForm;

public class GenerateTokens {

	private static final Logger logger = LoggerFactory.getLogger( GenerateTokens.class );
	
	public GenerateTokens() {}
		// TODO Auto-generated constructor stub
	
	NetworkEnvironment env;	
	Server actruleServer;
	User superman;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
		actruleServer = env.getServer( "actrule" );
		
		superman = actruleServer.getUser( "superman" );
		
		final StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();

		System.out.println(binder.getLoggerFactory());
		System.out.println(binder.getLoggerFactoryClassStr());
				
	}
	
	@Test
	public void generateTokens() {
				
		int tokens_to_generate = 10;
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( superman.getUsername(), superman.getPassword() ) );
		params.add( HTTPXMLRPCForm.getCustoEventParam( "393669393643", HTTPXMLRPCForm.EventTypes.revenue, new LinkedHashMap<HTTPXMLRPCForm.EventParameterTypes, String>() { { put( HTTPXMLRPCForm.EventParameterTypes.recharge, "1" ); put( HTTPXMLRPCForm.EventParameterTypes.event_storage_policy, "store" ); } } ) );
		//params.add( HTTPXMLRPCForm.getCustoEventParam( "393409429107", HTTPXMLRPCForm.EventTypes.revenue, new LinkedHashMap<HTTPXMLRPCForm.EventParameterTypes, String>() { { put( HTTPXMLRPCForm.EventParameterTypes.recharge, "1" ); put( HTTPXMLRPCForm.EventParameterTypes.event_storage_policy, "store" ); } } ) );
		
		long startTime = System.currentTimeMillis();
				
		for( int i = 0; i < tokens_to_generate; i++ ) {
						
			ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.eventmanager_generateCustomEvent.call( actruleServer.getLink() + "xmlrpc/" , params );
						
			//logger.info( "TOKEN RESPONSE: " + response.getEntity().toString() );
			
			try {
			
				Thread.sleep( 100 );
			
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
