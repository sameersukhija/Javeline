package com.lumata.unit.webservices.xmlrpc;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.jboss.resteasy.client.ClientResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.gui.xmlrpc.type.XMLRPCRequest;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.Token;

import static com.lumata.e4o.gui.xmlrpc.type.XMLRPCParam.*;
import static com.lumata.e4o.gui.xmlrpc.type.XMLRPCParam.EventType.*;
import static com.lumata.e4o.gui.xmlrpc.type.XMLRPCComponent.*;
import static com.lumata.e4o.gui.xmlrpc.type.XMLRPCParameter.*;
import static com.lumata.e4o.gui.xmlrpc.type.XMLRPCParameter.ParameterType.*;
import static com.lumata.e4o.gui.xmlrpc.type.XMLRPCOption.*;

public class XMLRPCRequest_GenerateRecharges {
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
	Mysql mysql;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		actruleServer = env.getServer( gui_server );
		
		superman = actruleServer.getUser( user );
		
		mysql = new Mysql( env.getDataSource( "tenant" ) );
		
	}
	
	@Test(enabled=true, priority = 1 )
	public void generateTokens() throws Exception {

		final Boolean ALL_SUBSCRIBERS = true;
		
		/** set these values when ALL_SUBSCRIBERS = false */
		final Integer MIN_SUBSCRIBER_INDEX = 0;
		final Integer MAX_SUBSCRIBER_INDEX = 0;
		
		/** number of events to generate by subscriber */
		final Integer MIN_EVENTS = 1;
		final Integer MAX_EVENTS = 1;
				
		ArrayList<Long> subscribers = getSubscribers();
		
		Integer subscribersToElaborate = subscribers.size() - 1;
		
		if( !ALL_SUBSCRIBERS ) {  
			
			subscribersToElaborate = ( ( MAX_SUBSCRIBER_INDEX - MIN_SUBSCRIBER_INDEX ) < subscribersToElaborate ? MAX_SUBSCRIBER_INDEX - MIN_SUBSCRIBER_INDEX : subscribersToElaborate ); 
				
		}
				
		for( int s = ( ALL_SUBSCRIBERS ? 0 : MIN_SUBSCRIBER_INDEX ); s <= subscribersToElaborate; s++ ) {
		
			Long msisdn = subscribers.get( s );
			
			System.out.println( "MSISDN: " + msisdn );
			
			Integer randomEventsToGenerate = MIN_EVENTS + (int)( Math.random() * MAX_EVENTS );
			
			System.out.println( "Events to generate: " + randomEventsToGenerate );
			
			for( int e = 0; e < randomEventsToGenerate; e++ ) {
			
				System.out.println(
						
						XMLRPCRequest.eventmanager_generateCustomEvent
										.call( 	
												actruleServer, 
												xmlrpcBody(
													authentication( superman.getUsername(), superman.getPassword() ),
													custoEvent( msisdn, 
																revenue,
																parameter( recharge, true ),
																parameter( event_storage_policy, true )
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
