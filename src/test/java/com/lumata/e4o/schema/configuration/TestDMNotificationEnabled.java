package com.lumata.e4o.schema.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.e4o.schema.tenant.FilesData;
import com.lumata.e4o.schema.tenant.FilesMeta;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

@TCMysqlMaster
public class TestDMNotificationEnabled extends ParentTestCase {
	
	private static final Logger logger = LoggerFactory.getLogger( TestDMNotificationEnabled.class );
	
	@Parameters({"tenant", "notifXMLFolder", "notifXMLFile"})
	@Test
	public void configureDMNotifications( @Optional("tenant") String tenant, @Optional("input/configuration") String notifXMLFolder, @Optional("notif.xml") String notifXMLFile ) throws SQLException, IOFileException {
		//notifXMLFile = "notif_EFOGC_4060_MC.xml";
		//notifXMLFile = "notif_EFOGC_4060_unsupported_tags.xml";
		//notifXMLFile = "notif_EFOGC_4060_ignore_unformatted_tags.xml";
		//notifXMLFile = "notif_EFOGC_4060_new_parameters_true.xml";
		//notifXMLFile = "notif_EFOGC_4091_channel_priority.xml";
		// check if notif.xml entry is present in the files_meta table
		FilesMeta fm = new FilesMeta();
		
		String query = select().from( fm ).where( op( FilesMeta.Fields.name ).eq( "notif.xml" ) ).build();
		
		ResultSet rs = mysqlMaster.execQuery( query );
		
		while( rs.next() ) { fm = new FilesMeta( rs ); }
		
		// add notif.xml entry if not exist
		if( fm.getId() == null ) {
		
			fm.setName( "notif.xml" );
			fm.setPath( "./data/notif/" );
			fm.setUpdatetime( Calendar.getInstance().getTimeInMillis() );
			
			query = insert( fm ).values().build();
			
			System.out.println( query );
			
			fm.setId( mysqlMaster.execUpdate( query ) );
			
		}
		
		// check if notif.xml entry is present in the files_data table
		FilesData fd = new FilesData();
		
		query = select().from( fd ).where( op( FilesData.Fields.id ).eq( fm.getId() ) ).build();
		
		rs = mysqlMaster.execQuery( query );
		
		while( rs.next() ) { fd = new FilesData( rs ); }
		
		String notifXMLContent = IOFileUtils.loadResourceAsString( notifXMLFolder, notifXMLFile );
		
		fd.setContent( notifXMLContent );
		
		// add notif.xml entry if not exist
		if( fd.getId() == null ) {
				
			fd.setId( fm.getId() );
						
			query = insert( fd ).values().build();
						
			mysqlMaster.execUpdate( query );
			System.out.println( query );
			logger.info( Log.PUTTING.createMessage( "notif.xml entered in files_data table" ) );
					
		} else {
			
			query = update( fd ).set( op( FilesData.Fields.content ).eq( fd.getContent() ) ).where( op( FilesData.Fields.id ).eq( fd.getId() ) ).build();
			
			mysqlMaster.execUpdate( query );
			System.out.println( query );
			logger.info( Log.PUTTING.createMessage( "notif.xml updated in files_data table" ) );
			
		}				
		
	}
	
}
