package com.lumata.e4o.schema.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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
import com.lumata.e4o.schema.tenant.FilesData;
import com.lumata.e4o.schema.tenant.FilesMeta;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

public class ConfigureXMLFileMetaData {
	
	private static final Logger logger = LoggerFactory.getLogger( ConfigureXMLFileMetaData.class );
	
	NetworkEnvironment env;	
	
//	public TestDMNotificationEnabled( NetworkEnvironment env ) {
//		this.env = env;
//	}
//	
//	public static TestDMNotificationEnabled getInstance( NetworkEnvironment env ) {
//		return new TestDMNotificationEnabled( env );
//	}
 	
	/* 	Initialize Environment */
	@Parameters({"environment"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
					
	}
	
	@Parameters({"tenant", "xmlCfgPath", "xmlFolder", "xmlFile"})
	@Test
	public void configureDMNotifications( @Optional("tenant") String tenant, @Optional("") String xmlCfgPath, @Optional("input/configuration") String xmlFolder, @Optional("") String xmlFile ) throws SQLException, IOFileException {
		
		xmlCfgPath = "./conf/product/";
		xmlFolder = "input/configuration";
		xmlFile = "reports.xml";
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		// check if notif.xml entry is present in the files_meta table
		FilesMeta fm = new FilesMeta();
		
		String query = select().from( fm ).where( op( FilesMeta.Fields.name ).eq( xmlFile ) ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) { fm = new FilesMeta( rs ); }
		
		// add xml file entry if not exist
		if( fm.getId() == null ) {
		
			fm.setName( xmlFile );
			fm.setPath( xmlFolder );
			fm.setUpdatetime( Calendar.getInstance().getTimeInMillis() );
			
			query = insert( fm ).values().build();
						
			fm.setId( mysql.execUpdate( query ) );
			
		}
		
		// check if xml file entry is present in the files_data table
		FilesData fd = new FilesData();
		
		query = select().from( fd ).where( op( FilesData.Fields.id ).eq( fm.getId() ) ).build();
		
		rs = mysql.execQuery( query );
		
		while( rs.next() ) { fd = new FilesData( rs ); }
		
		String notifXMLContent = IOFileUtils.loadResourceAsString( xmlFolder, xmlFile );
		
		fd.setContent( notifXMLContent );
		
		// add xml file entry if not exist
		if( fd.getId() == null ) {
				
			fd.setId( fm.getId() );
						
			query = insert( fd ).values().build();
						
			mysql.execUpdate( query );
			
			logger.info( Log.PUTTING.createMessage( xmlFile + " entered in files_data table" ) );
					
		} else {
			
			query = update( fd ).set( op( FilesData.Fields.content ).eq( fd.getContent() ) ).where( op( FilesData.Fields.id ).eq( fd.getId() ) ).build();
			
			mysql.execUpdate( query );
			
			logger.info( Log.PUTTING.createMessage( xmlFile + " updated in files_data table" ) );
			
		}				
		
	}
	
}
