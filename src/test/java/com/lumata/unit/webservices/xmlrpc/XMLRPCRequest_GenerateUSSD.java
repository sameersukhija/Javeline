package com.lumata.unit.webservices.xmlrpc;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;

import static com.lumata.common.testing.orm.Query.*;

import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.gui.xmlrpc.XMLRPCRequestOld;
import com.lumata.e4o.schema.tenant.Subscribers;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.EventType.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.ParameterType.*;

public class XMLRPCRequest_GenerateUSSD {
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
	Mysql mysql;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM_NE") String environment, @Optional("tenant") String tenant, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		actruleServer = env.getServer( gui_server );
		
		superman = actruleServer.getUser( user );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
	}
	
	public static XMLRPCRequest_GenerateUSSD run() {
		return new XMLRPCRequest_GenerateUSSD();
	}
	
	@Test(enabled=true, priority = 1 )
	public void generateUSSD() throws Exception {

		/**
		 *	FIXED_SUBSCRIBERS = true -> fixed msisdn will be used and ALL_SUBSCRIBERS will not have effect  
		 *  FIXED_SUBSCRIBERS = false -> some msisdn will be created following ALL_SUBSCRIBERS setting 		 *   
		 */
		final Boolean FIXED_SUBSCRIBERS = true;
		
		final Long FIXED_MSISDN = 3399900001L;
		
		
		/** 
		 * 	ALL_SUBSCRIBERS = true -> number of subscribers = MAX_SUBSCRIBER_INDEX
		 * 	ALL_SUBSCRIBERS = false -> number of subscribers = MAX( subscribers table size, MAX_SUBSCRIBER_INDEX - MIN_SUBSCRIBER_INDEX )
		*/
		final Boolean ALL_SUBSCRIBERS = true;
		
		final Integer MIN_SUBSCRIBER_INDEX = 0;
		final Integer MAX_SUBSCRIBER_INDEX = 0;
		
		
		/** 
		 * 	ALL_EVENTS = true -> number of events = MAX_EVENTS
		 * 	ALL_EVENTS = false -> number of events = random number between [ MIN_EVENTS, MAX_EVENTS - MIN_EVENTS ]
		*/
		final Boolean ALL_EVENTS = true;
		
		final Integer MIN_EVENTS = 1;
		final Integer MAX_EVENTS = 5;
		
		
		ArrayList<Long> subscribers = new ArrayList<Long>();
		
		Integer subscribersToElaborate = 0;
				
		if( FIXED_SUBSCRIBERS ) {
		
			subscribers.add( FIXED_MSISDN );
			
			subscribersToElaborate = subscribers.size() - 1;
			
		} else {
			
			subscribers = getSubscribers();
			
			subscribersToElaborate = subscribers.size() - 1;
				
			if( !ALL_SUBSCRIBERS ) {  
				
				subscribersToElaborate = ( ( MAX_SUBSCRIBER_INDEX - MIN_SUBSCRIBER_INDEX ) < subscribersToElaborate ? MAX_SUBSCRIBER_INDEX - MIN_SUBSCRIBER_INDEX : subscribersToElaborate ); 
					
			}
			
		}
				
		for( int s = ( ( ALL_SUBSCRIBERS || FIXED_SUBSCRIBERS ) ? 0 : MIN_SUBSCRIBER_INDEX ); s <= subscribersToElaborate; s++ ) {
		
			Long msisdn = subscribers.get( s );
			
			System.out.println( "MSISDN: " + msisdn );
			
			Integer randomEventsToGenerate = ( ALL_EVENTS ? MAX_EVENTS : MIN_EVENTS + (int)( Math.random() * ( MAX_EVENTS - MIN_EVENTS ) ) );
			
			System.out.println( "Events to generate: " + randomEventsToGenerate );
			System.out.println( superman.getUsername() );
			for( int e = 0; e < randomEventsToGenerate; e++ ) {
			
				System.out.println(
						
						XMLRPCRequestOld.eventmanager_generateCustomEvent
										.call( 	
												actruleServer, 
												xmlrpcBody(
													authentication( superman ),
													custoEvent( msisdn, 
																USSD,
																parameter( ussd_code, "555" )
													)
												),
												xmlrpcOptions( 
													sleep( 100L ) 
												)
										)
						.getEntity().toString()
						
				);
			
			}
		
		}			
						
	}
			
	public ArrayList<Long> getSubscribers() throws Exception {
	
		ArrayList<Long> subscribers = new ArrayList<Long>();
		
		Subscribers subscribersTable = new Subscribers();
		
		String query = select().
						from( subscribersTable ).
						orderBy( Subscribers.Fields.msisdn ).
						build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
			
			subscribers.add( rs.getLong( Subscribers.Fields.msisdn.name() ) );
			
		}
		
		return subscribers;
		
	}
	
	@AfterClass
	public void end() {
		
		mysql.close();
		
	}
	
	
}
