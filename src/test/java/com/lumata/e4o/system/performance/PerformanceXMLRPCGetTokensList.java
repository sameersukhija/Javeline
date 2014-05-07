package com.lumata.e4o.system.performance;

import java.util.ArrayList;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.gui.xmlrpc.HTTPXMLRPCForm;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultFault;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultParser;

public class PerformanceXMLRPCGetTokensList {

	private static final Logger logger = LoggerFactory.getLogger( PerformanceXMLRPCGetTokensList.class );
	
	Environment env;
	int user_id = 0;
	
	final String default_msisdn = "331234561";
		
	@Parameters({"browser", "environment", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
								
	}
	
	// Create subscriber with wrong xmlrpc requests
	@Parameters( "msisdn" )
	@Test( priority = 1, enabled = true )
	public void getTokensList( @Optional( default_msisdn ) String msisdn ) {
			
		XMLRPCResultParser responseParser;
		XMLRPCResultFault resultFault;
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getStringParam( default_msisdn ) );
		params.add( HTTPXMLRPCForm.getStringParam( "" ) );
		params.add( HTTPXMLRPCForm.getStringParam( "" ) );
		
		long execution_time_average = 0;
		
		final int EXECUTIONS = 100;
		
		ArrayList<Long> values = new ArrayList<Long>(); 
		
		for( int i = 0; i <= EXECUTIONS; i++ ) {
		
			long start_time = System.currentTimeMillis();
			
			ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.offeroptimizer_getTokensList.call( env.getLink() + "xmlrpc/" , params );
			
			long end_time = System.currentTimeMillis();
						
			long execution_time = end_time - start_time;
			
			values.add( execution_time );
			
			if( i > 0 ) { 
							
				execution_time_average = execution_time_average + execution_time; 
				
			}
			
			try { Thread.sleep( 100 ); } catch( InterruptedException e ) {}
			
		}
		
		execution_time_average = execution_time_average / EXECUTIONS;
		
		System.out.println( "Values: " + values.toString() );
		
		System.out.println( "Execution time average: " + execution_time_average );
					
	}	
	
}
