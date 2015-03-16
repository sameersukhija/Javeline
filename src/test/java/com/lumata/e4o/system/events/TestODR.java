package com.lumata.e4o.system.events;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.schema.tenant.Conf;
import com.lumata.e4o.schema.tenant.EventFormats;
import com.lumata.e4o.schema.tenant.OdrEvents;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

public class TestODR {
	
	private static final Logger logger = LoggerFactory.getLogger( TestODR.class );
	
	NetworkEnvironment env;	
	Mysql mysql;
		
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant" })
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant ) throws NetworkEnvironmentException, SQLException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
				
	}

	@Test
	public void checkODREvents() throws Exception {
		
		OdrEvents odrEventsTable = new OdrEvents();
		
		EventFormats eventFormatTable = new EventFormats();
		
		String query = select( EventFormats.Fields.format, OdrEvents.Fields.event_data ).
						from( eventFormatTable ).
						join( odrEventsTable ).
						on( op( EventFormats.Fields.id ).eq( OdrEvents.Fields.format ) ).
						build();
		
		ResultSet rs = mysql.execQuery( query );
		
		int eventsCount = 0;
		
		StringBuilder odrEventContent = new StringBuilder();
		
		while( rs.next() ) { 
								
			String odrFormat = rs.getString( EventFormats.Fields.format.name() );
			
			String odrEvent = rs.getString( OdrEvents.Fields.event_data.name() );
			
			if( null != odrFormat && null != odrEvent ) {
				
				String[] odrEventFields = odrFormat.split( ";" );
				
				String[] odrEventValues = odrEvent.split( ";" );
				
				if( null != odrEventFields && null != odrEventValues ) {
					
					eventsCount++;
					
					odrEventContent.append( "### ODR EVENTS ( " ).append( eventsCount ).append( " ) ###\n" );
					
					for( int oev = 0; oev < odrEventValues.length; oev++ ) {
						
						odrEventContent.append( odrEventFields[ oev ] ).append( " = " ).append( odrEventValues[ oev ] ).append( "\n" );
							
					}
					
					for( int oe = odrEventValues.length; oe < odrEventFields.length; oe++ ) {
						
						odrEventContent.append( odrEventFields[ oe ] ).append( " =\n" );
											
					}
					
					odrEventContent.append( "\n" );
					
				}
								
			}
						
		}
		
		IOFileUtils.saveResource( odrEventContent.toString(), "catalogmanager/odr", "odr.txt" );
		
	}
	
}
