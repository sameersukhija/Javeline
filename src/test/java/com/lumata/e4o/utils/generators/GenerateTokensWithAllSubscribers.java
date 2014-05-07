package com.lumata.e4o.utils.generators;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.lumata.common.testing.orm.Query.*;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.expression.operators.gui.xmlrpc.HTTPXMLRPCForm;

public class GenerateTokensWithAllSubscribers {

	private static final Logger logger = LoggerFactory.getLogger( GenerateTokensWithAllSubscribers.class );
	
	Environment env;	
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
						
	}
	
	@Parameters({"tenant"})
	@Test
	public void generateTokens( @Optional("tenant") String tenant ) {
				
		Subscribers subscribers = new Subscribers();
		
		String query = select().from( subscribers ).orderBy( Subscribers.Fields.msisdn ).build();
		
		Mysql mysql = new Mysql( env.getDataSource(tenant) );
		
		ResultSet rs = mysql.execQuery( query );
		
		int calls_count = 0;
		
		final int MAX_CALLS = 10000;
		
		try {
			
			while( rs.next() && calls_count <= MAX_CALLS ) {
				
				String msisdn = rs.getString( Subscribers.Fields.msisdn.name() );
				
				ArrayList<String> params = new ArrayList<String>();
				params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
				params.add( HTTPXMLRPCForm.getCustoEventParam( msisdn, HTTPXMLRPCForm.EventTypes.revenue, new LinkedHashMap<HTTPXMLRPCForm.EventParameterTypes, String>() { 
					{ put( HTTPXMLRPCForm.EventParameterTypes.recharge, "1" ); put( HTTPXMLRPCForm.EventParameterTypes.event_storage_policy, "store" ); } 
				} ) );
				
				@SuppressWarnings("unused")
				ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.eventmanager_generateCustomEvent.call( env.getLink() + "xmlrpc/" , params );
				
				calls_count++;
							
				Thread.sleep( 100 );
				
			}
			
		} catch (SQLException | InterruptedException e) {  logger.error( e.getMessage(), e ); }
							
		System.out.println( "EXECUTED CALLS: " + calls_count );
	
	}
	
}
